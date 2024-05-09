package com.virtualbankv2.control;

import com.virtualbankv2.boundary.*;
import com.virtualbankv2.boundary.Reader;
import com.virtualbankv2.entity.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.util.List;
import java.util.stream.Collectors;


public class GoalManager extends JFrame {
    private String currentUsername;
    private List<Goal> userGoals;
    Reader reader = new Reader();

    public GoalManager(String username) {
        // Set the current user
        this.currentUsername = username;

        // Load goals from Reader
        this.userGoals = Reader.goals.stream()
                .filter(goal -> goal.getUsername().equals(currentUsername))
                .collect(Collectors.toList());

        initializeUI();
    }

    private void initializeUI() {
        setTitle("Goal Management");
        getContentPane().setBackground(new Color(199, 220, 247));
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Font font1 = new Font("Arial", Font.BOLD, 40);
        Font font2 = new Font("Arial",Font.BOLD,60);
        Font font3 = new Font("Arial",Font.BOLD,30);
        Font font4 = new Font("Arial",Font.BOLD,24);
        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(199, 220, 247));
        JLabel headerLabel = new JLabel("Goal Management", JLabel.CENTER);
        headerLabel.setFont(font1);
        headerLabel.setForeground(new Color(93, 97, 195));
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Goal Info Panel
        RoundedPanel goalInfoPanel = new RoundedPanel(30);
        goalInfoPanel.setBackground(new Color(199,220,247));
        goalInfoPanel.setLayout(new BoxLayout(goalInfoPanel, BoxLayout.Y_AXIS));  // Vertical box layout
        goalInfoPanel.add(Box.createVerticalStrut(50)); // Adds initial vertical space

        if (userGoals.isEmpty()) {
            JLabel noGoalLabel = new JLabel("No Goal", JLabel.CENTER);
            noGoalLabel.setFont(font2);
            noGoalLabel.setForeground(new Color(93, 97, 195));
            goalInfoPanel.add(noGoalLabel);

        } else {
            Goal currentGoal = userGoals.get(0); // Assumes only one goal per user for simplicity
            JLabel goalNameLabel = new JLabel("GoalName: " + currentGoal.getGoalName(),JLabel.CENTER);
            goalNameLabel.setFont(font3);
            goalNameLabel.setForeground(new Color(112, 172, 249));
            goalInfoPanel.add(goalNameLabel);

            goalInfoPanel.add(Box.createVerticalStrut(30)); // Space between labels

            JLabel descriptionLabel = new JLabel("Description: " + currentGoal.getDescription());
            descriptionLabel.setFont(font3);
            descriptionLabel.setForeground(new Color(112, 172, 249));
            goalInfoPanel.add(descriptionLabel);

            goalInfoPanel.add(Box.createVerticalStrut(30)); // Space between labels

            JLabel statusLabel = new JLabel(String.format("Status: %.2f/%.2f   ",
                    currentGoal.getCurrentAmount(), currentGoal.getTargetAmount()));
            statusLabel.setFont(font3);
            statusLabel.setForeground(new Color(112, 172, 249));
            goalInfoPanel.add(statusLabel);

            double percentage = (currentGoal.getCurrentAmount() / currentGoal.getTargetAmount()) * 100;
            JPanel pieChartPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;
                    int size = Math.min(getWidth(), getHeight());
                    int x = (getWidth() - size) / 2;
                    int y = (getHeight() - size) / 2;
                    double angle = 360 * (currentGoal.getCurrentAmount() / currentGoal.getTargetAmount());

                    // Background circle
                    g2d.setColor(new Color(112, 172, 249));
                    g2d.fill(new Arc2D.Double(x, y, size, size, 90, -angle, Arc2D.PIE));

                    // Remaining part
                    g2d.setColor(new Color(199, 220, 247));
                    g2d.fill(new Arc2D.Double(x, y, size, size, 90 - angle, angle - 360, Arc2D.PIE));
                }
            };
            pieChartPanel.setPreferredSize(new Dimension(200, 200)); // Set preferred size for the pie chart
            goalInfoPanel.add(pieChartPanel);
            //goalInfoPanel.add(Box.createVerticalStrut(30)); // Space after the pie chart
        }
        goalInfoPanel.add(Box.createVerticalGlue()); // Pushes everything up
        add(goalInfoPanel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(199,220,247));
        RoundedButton createButton = new RoundedButton("Create a goal");
        createButton.setFont(font4);
        createButton.addActionListener(e -> handleCreateGoal());
        buttonPanel.add(createButton);

        JButton returnButton =  ReturnButton.createReturnButton(this,"HomePage");
        buttonPanel.add(returnButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void handleCreateGoal() {
        if (userGoals.isEmpty()) {
            // Logic to create a new goal
            this.dispose();
            new CreateGoalPage(this.currentUsername);
        } else {
            // Existing goal found
            JOptionPane.showMessageDialog(this, "Current goal not achieved yet.");
        }
    }

    public static void main(String[] args) {
        // Initialize the Reader to load data
        new Reader();
        // Example usage with a specific username
        new GoalManager("li");
    }
}
