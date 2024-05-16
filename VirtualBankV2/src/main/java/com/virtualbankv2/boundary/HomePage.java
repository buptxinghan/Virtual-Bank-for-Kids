package com.virtualbankv2.boundary;

import com.virtualbankv2.control.HomePageController;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JFrame {

    private JButton accountButton;
    private JButton tasksButton;
    private JButton goalsButton;
    private JButton manualButton;

    public HomePage() {
        initializeComponents();
        HomePageController controller = new HomePageController(this);
        controller.initializeController();
    }

    private void initializeComponents() {
        setSize(new Dimension(1200, 900));
        setTitle("Virtual Bank");
        Color bg = new Color(199, 220, 247);
        Color z1 = new Color(93, 97, 195);
        Color z2 = new Color(133, 149, 188);

        getContentPane().setBackground(bg);

        accountButton = createButton("My account", "Check your account information", z1, z2, bg);
        tasksButton = createButton("My tasks", "Check the tasks assigned", z1, z2, bg);
        goalsButton = createButton("My goals", "Check and manage your goal", z1, z2, bg);
        manualButton = createButton("Instruction manual", "Learn how to use the software or contact us", z1, z2, bg);

        JLabel welcomeLabel = createLabel("Welcome to Your Virtual Bank!", z1);
        JLabel picLabel = createImageLabel("src/Materials/pig.png", bg);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(bg);

        JPanel buttonPanel = createButtonPanel(bg, accountButton, tasksButton, goalsButton, manualButton);

        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(picLabel);
        centerPanel.add(buttonPanel);

        add(createPanel(new Dimension(250, 250), bg), BorderLayout.EAST);
        add(welcomeLabel, BorderLayout.NORTH);
        add(createPanel(new Dimension(0, 100), bg), BorderLayout.SOUTH);
        add(createPanel(new Dimension(250, 250), bg), BorderLayout.WEST);
        add(centerPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton createButton(String title, String subtitle, Color titleColor, Color subtitleColor, Color bgColor) {
        JButton button = new RoundedButton(String.format(
                "<html><font size=7 color=%s>%s</font><br><font size=6 color=%s>%s</font></html>",
                getColorHex(titleColor), title, getColorHex(subtitleColor), subtitle));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBackground(bgColor);
        return button;
    }

    private JLabel createLabel(String text, Color color) {
        JLabel label = new JLabel(String.format("<html><font color=%s>%s</font></html>", getColorHex(color), text), JLabel.CENTER);
        label.setFont(new Font("Serif", Font.BOLD, 60));
        return label;
    }

    private JLabel createImageLabel(String imagePath, Color bgColor) {
        JLabel label = new JLabel(new ImageIcon(imagePath), JLabel.CENTER);
        JPanel picPanel = new JPanel();
        picPanel.add(label);
        picPanel.setBackground(bgColor);
        picPanel.setMaximumSize(new Dimension(300, 100));
        return label;
    }

    private JPanel createPanel(Dimension size, Color bgColor) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(size);
        panel.setBackground(bgColor);
        return panel;
    }

    private JPanel createButtonPanel(Color bgColor, JButton... buttons) {
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1));
        buttonPanel.setBackground(bgColor);

        for (JButton button : buttons) {
            JPanel buttonContainer = new JPanel();
            buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.Y_AXIS));
            buttonContainer.setBackground(bgColor);
            buttonContainer.add(button);
            buttonContainer.add(new JSeparator(SwingConstants.HORIZONTAL));
            buttonPanel.add(buttonContainer);
        }
        return buttonPanel;
    }

    private String getColorHex(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    public JButton getAccountButton() {
        return accountButton;
    }

    public JButton getTasksButton() {
        return tasksButton;
    }

    public JButton getGoalsButton() {
        return goalsButton;
    }

    public JButton getManualButton() {
        return manualButton;
    }
}
