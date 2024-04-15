package com.virtualbankv1;
// Account management interface class

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AccountManager {

    // Create an account

    public AccountManager(Account account) {
        accountManagerGUI(account);

    }

    public void accountManagerGUI(Account account) {

        JFrame frame = new JFrame("Account Information");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1600, 900));
        frame.setLayout(new BorderLayout(10, 10)); // Set margins for the layout

        // Set a solid light blue background
        frame.getContentPane().setBackground(new Color(199, 220, 247)); // Light blue background

        // Create the title label with HTML formatting
        JLabel titleLabel = new JLabel("<html><div style='text-align: center; color: black;'><font size=8>Account Information</font></div></html>", SwingConstants.CENTER);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Top and bottom padding

        // Create the form panel with labels for displaying information
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setOpaque(false); // Transparent background
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50)); // Padding around the form

        // Add display labels with fixed information and larger font size
        formPanel.add(createLabel("Account holder:"));
        formPanel.add(createDisplayLabel("Sam Smith")); // Larger font size
        formPanel.add(createLabel("Account number:"));
        formPanel.add(createDisplayLabel("123456789")); // Larger font size
        formPanel.add(createLabel("Account Type:"));
        formPanel.add(createDisplayLabel("Checking")); // Larger font size
        formPanel.add(createLabel("Account Balance:"));
        formPanel.add(createDisplayLabel("1000")); // Larger font size

        // Create the buttons panel for the "Withdraw" and "Transfer In" buttons
        JPanel transactionButtonsPanel = new JPanel();
        transactionButtonsPanel.setLayout(new BoxLayout(transactionButtonsPanel, BoxLayout.Y_AXIS));
        transactionButtonsPanel.setOpaque(false); // Transparent background
        transactionButtonsPanel.add(Box.createRigidArea(new Dimension(0, 100))); // Spacer between buttons
        transactionButtonsPanel.add(createStyledButton("Withdraw", new Color(70, 130, 180), Color.WHITE, new Dimension(200, 50)));
        transactionButtonsPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer between buttons
        transactionButtonsPanel.add(createStyledButton("Transfer In", new Color(70, 130, 180), Color.WHITE, new Dimension(200, 50)));

        // Create the bottom buttons panel with custom button styling
        JPanel bottomButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        bottomButtonsPanel.setOpaque(false); // Transparent background
        bottomButtonsPanel.add(createStyledButton("Freeze Account", new Color(255, 255, 0), Color.BLACK, new Dimension(250, 50))); // Yellow button with black text
        bottomButtonsPanel.add(Box.createRigidArea(new Dimension(50, 10))); // Spacer between buttons
        bottomButtonsPanel.add(createStyledButton("Delete Account", new Color(255, 69, 0), Color.WHITE, new Dimension(250, 50))); // Red button

        // Add components to the window
        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(formPanel, BorderLayout.WEST); // Align the form panel to the left
        frame.add(transactionButtonsPanel, BorderLayout.CENTER); // Place the transaction buttons in the center
        frame.add(bottomButtonsPanel, BorderLayout.SOUTH); // Place the bottom buttons at the bottom

        // Pack the frame and display the window
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }

    // Helper method to create styled buttons
    private static JButton createStyledButton(String text, Color bgColor, Color textColor, Dimension size) {
        JButton button = new JButton("<html><font size ='6'>" + text + "</font></html>");
        button.setBackground(bgColor);
        button.setForeground(textColor); // Set the text color
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button text
        button.setPreferredSize(size); // Set the preferred size
        button.setMinimumSize(size); // Set the minimum size
        button.setMaximumSize(size); // Set the maximum size
        return button;
    }

    // Helper method to create labels with HTML formatting and larger font size
    private static JLabel createLabel(String text) {
        return new JLabel("<html><font color='black' size='7'>" + text + "</font></html>");
    }

    // Helper method to create display labels with fixed size and larger font size
    private static JLabel createDisplayLabel(String text) {
        JLabel label = new JLabel("<html><font size ='6'>" + text + "</font></html>");
        label.setMaximumSize(new Dimension(300, 50)); // Adjusted size for display labels
        label.setMinimumSize(new Dimension(300, 50)); // Adjusted size for display labels
        label.setPreferredSize(new Dimension(300, 50)); // Adjusted size for display labels
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(Color.WHITE);
        return label;
    }

    // 存款
    public boolean deposit(User user, double amount) {
        // 存款逻辑
        // 更新账户余额
        // 记录交易
        return true;
    }

    // 取款
    public boolean withdraw(User user, double amount) {
        // 取款逻辑
        // 更新账户余额
        // 记录交易
        return true;
    }
}
