package com.virtualbankv2.boundary;

import com.virtualbankv2.control.GoalManager;
import com.virtualbankv2.control.VirtualBankApplication;
import com.virtualbankv2.entity.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.util.List;
import java.util.stream.Collectors;

public class GoalOverviewUI extends JFrame {
    private String currentUsername;
    private List<Goal> userGoals;
    //Reader reader = new Reader();
    private JPanel headerPanel;
    private JPanel goalInfoPanel;
    private JPanel buttonPanel;
    private Goal currentGoal;
    GoalManager goalManager = new GoalManager();

    public GoalOverviewUI() {
        // Set the current user
        this.currentUsername = VirtualBankApplication.currentUser.getUsername();
        // Load goals from Reader
        this.userGoals = Reader.goals.stream()
                .filter(goal -> goal.getUsername().equals(currentUsername))
                .collect(Collectors.toList());
        if (!userGoals.isEmpty()) {
            this.currentGoal = this.userGoals.get(0);
        } else {
            this.currentGoal = null; // 或其他适当的处理
        }
        initializeUI();
    }
    private void initializeUI() {
        setTitle("Goal Management");
        getContentPane().setBackground(new Color(199, 220, 247));
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFrame.setDefaultLookAndFeelDecorated(true);
        setLayout(new BorderLayout());

        Font font1 = new Font("Arial", Font.BOLD, 40);
        Font font2 = new Font("Arial",Font.BOLD,60);
        Font font3 = new Font("Arial",Font.BOLD,30);
        Font font4 = new Font("Arial",Font.BOLD,24);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(30, 20, 30,20);
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
        //RoundedPanel goalInfoPanel = new RoundedPanel(30);
        goalInfoPanel = new JPanel();
        goalInfoPanel.setBackground(new Color(199,220,247));
        goalInfoPanel.setLayout(new BoxLayout(goalInfoPanel, BoxLayout.Y_AXIS));  // Vertical box layout
        goalInfoPanel.add(Box.createVerticalStrut(50)); // Adds initial vertical space

        if (userGoals.isEmpty()) {
            JLabel noGoalLabel = new JLabel("No Goal", JLabel.CENTER);
            noGoalLabel.setFont(font2);
            noGoalLabel.setForeground(new Color(93, 97, 195));
            goalInfoPanel.add(noGoalLabel,JPanel.CENTER_ALIGNMENT);
        } else {
            //Goal currentGoal = userGoals.get(0); // Assumes only one goal per user for simplicity
            JLabel goalNameLabel = new JLabel("GoalName: " + currentGoal.getGoalName(),JLabel.CENTER);
            goalNameLabel.setFont(font3);
            goalNameLabel.setForeground(new Color(112, 172, 249));
            goalInfoPanel.add(goalNameLabel);

            goalInfoPanel.add(Box.createVerticalStrut(30)); // Space between labels

            JLabel descriptionLabel = new JLabel("Description: " + currentGoal.getDescription());
            descriptionLabel.setFont(new Font("Arial",Font.BOLD,20));
            descriptionLabel.setForeground(new Color(15, 108, 229));
            goalInfoPanel.add(descriptionLabel);

            goalInfoPanel.add(Box.createVerticalStrut(30)); // Space between labels

            JLabel statusLabel = new JLabel(String.format("Status: %.2f/%.2f   ",
                    currentGoal.getCurrentAmount(), currentGoal.getTargetAmount()));
            statusLabel.setFont(new Font("Arial",Font.BOLD,26));
            statusLabel.setForeground(new Color(112, 172, 249));
            goalInfoPanel.add(statusLabel);

            double percentage = (currentGoal.getCurrentAmount() / currentGoal.getTargetAmount()) * 100;
            JPanel pieChartPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (percentage >= 100) {
                        // 设置左侧图像
                        try {
                            Image img = Toolkit.getDefaultToolkit().getImage("src/Materials/heart.png");
                            g.drawImage(img, 0, 0, getWidth() / 2, getHeight(), this);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        // 设置右侧文本
                        Graphics2D g2d = (Graphics2D) g;
                        g2d.setColor(new Color(0, 102, 204)); // 设置文字颜色
                        g2d.setFont(new Font("Arial", Font.BOLD, 16)); // 设置字体样式和大小
                        FontMetrics fm = g2d.getFontMetrics();
                        String text = "Congratulations! You have achieved your goal!";
                        int textWidth = fm.stringWidth(text);
                        int x = getWidth() / 2 + (getWidth() / 2 - textWidth) / 2;
                        int y = getHeight() / 2 + fm.getAscent() / 2;
                        g2d.drawString(text, x, y);
                    } else {
                        // 绘制原有的饼图
                        Graphics2D g2d = (Graphics2D) g;
                        int size = Math.min(getWidth(), getHeight());
                        int x = (getWidth() - size) / 2;
                        int y = (getHeight() - size) / 2;
                        double angle = 360 * (currentGoal.getCurrentAmount() / currentGoal.getTargetAmount());

                        // Background circle
                        g2d.setColor(new Color(112, 172, 249));
                        g2d.fill(new Arc2D.Double(x, y, size, size, 90, -angle, Arc2D.PIE));

                        // Remaining part
                        g2d.setColor(new Color(221, 224, 229));
                        g2d.fill(new Arc2D.Double(x, y, size, size, 90 - angle, angle - 360, Arc2D.PIE));
                    }
                }
            };
            pieChartPanel.setPreferredSize(new Dimension(300, 150)); // Set preferred size for the pie chart
            pieChartPanel.setBackground(new Color(199, 220, 247));
            goalInfoPanel.add(pieChartPanel);
        }
        goalInfoPanel.add(Box.createVerticalGlue()); // Pushes everything up
        add(goalInfoPanel, BorderLayout.CENTER);

        // Button Panel
        buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(199,220,247));
        RoundedButton createButton = new RoundedButton("<html><font size ='6'>Create a goal</font></html>");
        createButton.setBackground(new Color(70, 130, 180));
        createButton.setForeground(Color.WHITE);
        createButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        createButton.setPreferredSize(new Dimension(200,50));
        createButton.setMinimumSize(new Dimension(200, 50));
        createButton.setMaximumSize(new Dimension(200, 50));
        //createButton.setFont(font4);
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
            new CreateGoalUI();
        } else if (currentGoal.getCurrentAmount()>=currentGoal.getTargetAmount()) {
            this.dispose();
            goalManager.removeGoalIfComplete();
            new CreateGoalUI();
        } else {
            // Existing goal found
            JOptionPane.showMessageDialog(this, "Current goal not achieved yet.");
        }
    }

//    public static void main(String[] args) {
//        // Initialize the Reader to load data
//        new Reader();
//        // Example usage with a specific username
//        new GoalOverviewUI("theo");
//    }
}