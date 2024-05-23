package com.virtualbankv2.boundary;

import com.virtualbankv2.control.GoalManager;
import com.virtualbankv2.control.VirtualBankApplication;
import com.virtualbankv2.entity.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The {@code GoalOverviewUI} class represents the goal overview interface for the Virtual Bank application.
 * It displays the user's current goals, their progress, and provides options to create new goals.
 *
 * @version 2.0
 * @since 2024-05-10
 * @author Tianzhi Li
 *
 */
public class GoalOverviewUI extends JFrame {
    private String currentUsername;
    private List<Goal> userGoals;
    private JPanel headerPanel;
    private JPanel goalInfoPanel;
    private JPanel buttonPanel;
    private Goal currentGoal;
    private GoalManager goalManager = new GoalManager();

    /**
     * Constructs a new {@code GoalOverviewUI} object and initializes the user interface.
     */
    public GoalOverviewUI() {
        // Set the current user
        this.currentUsername = VirtualBankApplication.currentUser.getUsername();

        // Load goals from Reader and filter them for the current user
        Reader reader = new Reader();
        this.userGoals = reader.goals.stream()
                .filter(goal -> goal.getUsername().equals(currentUsername))
                .collect(Collectors.toList());

        // If user has goals, set the current goal and calculate its current amount
        if (!userGoals.isEmpty()) {
            this.currentGoal = this.userGoals.get(0);
            this.currentGoal.setCurrentAmount(goalManager.calculateCurrentAmount(currentUsername));
            goalManager.updateGoalCurrentAmount(currentGoal); // Update the goal's current amount in the CSV file
        } else {
            this.currentGoal = null; // or other appropriate handling
        }

        initializeUI();
    }

    /**
     * Initializes the user interface for the goal overview page.
     */
    private void initializeUI() {
        setTitle("Goal Management");
        getContentPane().setBackground(new Color(199, 220, 247));
        setSize(1200, 900);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Define fonts
        Font font1 = new Font("Arial", Font.BOLD, 40);
        Font font2 = new Font("Arial", Font.BOLD, 100);
        Font font3 = new Font("Arial", Font.BOLD, 30);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(30, 20, 30, 20);

        // Header Panel
        headerPanel = new JPanel();
        headerPanel.setBackground(new Color(199, 220, 247));
        headerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        JLabel headerLabel = new JLabel("Goal Management", JLabel.CENTER);
        headerLabel.setFont(font1);
        headerLabel.setForeground(new Color(93, 97, 195));
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Goal Info Panel
        goalInfoPanel = new JPanel();
        goalInfoPanel.setBackground(new Color(199, 220, 247));
        goalInfoPanel.setLayout(new BoxLayout(goalInfoPanel, BoxLayout.Y_AXIS));  // Vertical box layout
        goalInfoPanel.add(Box.createVerticalStrut(50)); // Adds initial vertical space

        // Check if user has any goals
        if (userGoals.isEmpty()) {
            // Display message if no goals exist
            JPanel noGoalPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 150, 100));
            noGoalPanel.setOpaque(false);

            JLabel noGoalLabel = new JLabel("NO Goal!");
            noGoalLabel.setFont(font2);
            noGoalLabel.setForeground(new Color(93, 97, 195));
            noGoalPanel.add(noGoalLabel);
            goalInfoPanel.add(noGoalPanel, JPanel.CENTER_ALIGNMENT);
        } else {
            // Display current goal information
            JLabel goalNameLabel = new JLabel("GoalName: " + currentGoal.getGoalName(), JLabel.CENTER);
            goalNameLabel.setFont(font3);
            goalNameLabel.setForeground(new Color(112, 172, 249));
            goalInfoPanel.add(goalNameLabel);

            goalInfoPanel.add(Box.createVerticalStrut(30)); // Space between labels

            JLabel descriptionLabel = new JLabel("Description: " + currentGoal.getDescription());
            descriptionLabel.setFont(new Font("Arial", Font.BOLD, 20));
            descriptionLabel.setForeground(new Color(15, 108, 229));
            goalInfoPanel.add(descriptionLabel);

            goalInfoPanel.add(Box.createVerticalStrut(30)); // Space between labels

            JLabel statusLabel = new JLabel(String.format("Status: %.2f/%.2f   ",
                    currentGoal.getCurrentAmount(), currentGoal.getTargetAmount()));
            statusLabel.setFont(new Font("Arial", Font.BOLD, 26));
            statusLabel.setForeground(new Color(112, 172, 249));
            goalInfoPanel.add(statusLabel);

            double percentage = (currentGoal.getCurrentAmount() / currentGoal.getTargetAmount()) * 100;
            // Create a panel to display the goal progress as a pie chart
            JPanel pieChartPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (percentage >= 100) {
                        // Display congratulatory message and image if goal is achieved
                        try {
                            Image img = Toolkit.getDefaultToolkit().getImage("src/Materials/heart.png");
                            g.drawImage(img, 0, 0, getWidth() / 2, getHeight(), this);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Graphics2D g2d = (Graphics2D) g;
                        g2d.setColor(new Color(0, 102, 204));
                        g2d.setFont(new Font("Arial", Font.BOLD, 16));
                        FontMetrics fm = g2d.getFontMetrics();
                        String text = "Congratulations! You have achieved your goal!";
                        int textWidth = fm.stringWidth(text);
                        int x = getWidth() / 2 + (getWidth() / 2 - textWidth) / 2;
                        int y = getHeight() / 2 + fm.getAscent() / 2;
                        g2d.drawString(text, x, y);
                    } else {
                        // Display progress pie chart if goal is not yet achieved
                        Graphics2D g2d = (Graphics2D) g;
                        int size = Math.min(getWidth(), getHeight());
                        int x = (getWidth() - size) / 2;
                        int y = (getHeight() - size) / 2;
                        double angle = 360 * (currentGoal.getCurrentAmount() / currentGoal.getTargetAmount());

                        g2d.setColor(new Color(112, 172, 249));
                        g2d.fill(new Arc2D.Double(x, y, size, size, 90, -angle, Arc2D.PIE));

                        g2d.setColor(new Color(221, 224, 229));
                        g2d.fill(new Arc2D.Double(x, y, size, size, 90 - angle, angle - 360, Arc2D.PIE));
                    }
                }
            };
            pieChartPanel.setPreferredSize(new Dimension(300, 150));
            pieChartPanel.setBackground(new Color(199, 220, 247));
            goalInfoPanel.add(pieChartPanel);
        }
        goalInfoPanel.add(Box.createVerticalGlue()); // Adds vertical space to push components up
        add(goalInfoPanel, BorderLayout.CENTER);

        // Button Panel
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 80, 10));
        buttonPanel.setBackground(new Color(199, 220, 247));
        RoundedButton createButton = new RoundedButton("<html><font size ='6'>Create a goal</font></html>");
        createButton.setBackground(new Color(70, 130, 180));
        createButton.setForeground(Color.WHITE);
        createButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        createButton.setPreferredSize(new Dimension(200, 50));
        createButton.setMinimumSize(new Dimension(200, 50));
        createButton.setMaximumSize(new Dimension(200, 50));
        createButton.addActionListener(e -> handleCreateGoal());
        buttonPanel.add(createButton);

        // Add return button
        JButton returnButton = ReturnButton.createReturnButton(this, "HomePage");
        buttonPanel.add(returnButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Handles the action of creating a new goal. If the current goal is not achieved,
     * it displays an error message. If the current goal is achieved, it removes the
     * completed goal and navigates to the goal creation interface.
     */
    private void handleCreateGoal() {
        if (userGoals.isEmpty()) {
            // No goals exist, open the goal creation UI
            this.dispose();
            new CreateGoalUI();
        } else if (currentGoal.getCurrentAmount() >= currentGoal.getTargetAmount()) {
            // Current goal is achieved, remove it and open the goal creation UI
            this.dispose();
            goalManager.removeGoalIfComplete();
            new CreateGoalUI();
        } else {
            // Current goal is not yet achieved, show an error message
            JOptionPane.showOptionDialog(
                    this,
                    "Current goal not achieved yet.",
                    "Error",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE,
                    null,
                    new String[] {"OK"},
                    "OK"
            );
        }
    }
}
