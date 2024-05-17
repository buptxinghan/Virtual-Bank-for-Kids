package com.virtualbankv2.boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InterestMessagePage {

    public void showInterestWindow(List<String> accountInterests) {
        JFrame frame = new JFrame("Interest Display");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Set an iOS-like look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set up the text area with modern font and padding
        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("San Francisco", Font.PLAIN, 16)); // Use a modern sans-serif font
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        for (String accountInterest : accountInterests) {
            textArea.append(accountInterest + "\n");
        }
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove border to make it cleaner
        frame.add(scrollPane, BorderLayout.CENTER);

        // Set up the confirm button with iOS-like style
        JButton closeButton = new JButton("Confirm");
        closeButton.setFont(new Font("San Francisco", Font.BOLD, 16)); // Use a modern sans-serif font
        closeButton.setBackground(new Color(0, 122, 255)); // iOS blue
        closeButton.setForeground(Color.WHITE); // White text
        closeButton.setFocusPainted(false);
        closeButton.setBorderPainted(false);
        closeButton.setOpaque(true);
        closeButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Add padding

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        // Add a panel for the button to add some padding around it
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Center the button
        buttonPanel.add(closeButton);
        buttonPanel.setBackground(Color.WHITE); // Set the background color to white

        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
