package com.virtualbankv2.boundary;

import com.virtualbankv2.entity.RoundedButton;

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

        // Set background color of content pane
        frame.getContentPane().setBackground(new Color(199, 220, 247));

        // Main panel to hold all interest panels
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(199, 220, 247));

        // Add a top margin
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Add each interest to the main panel
        for (String accountInterest : accountInterests) {
            JPanel interestPanel = new JPanel();
            interestPanel.setLayout(new BoxLayout(interestPanel, BoxLayout.Y_AXIS));
            interestPanel.setBackground(new Color(199, 220, 247));
            interestPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel interestLabel = new JLabel(accountInterest);
            interestLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            interestLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            interestLabel.setHorizontalAlignment(SwingConstants.CENTER);

            interestPanel.add(interestLabel);
            interestPanel.add(Box.createRigidArea(new Dimension(0, 5)));
            JSeparator separator = new JSeparator();
            separator.setForeground(Color.GRAY);
            interestPanel.add(separator);

            mainPanel.add(interestPanel);
        }

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove border to make it cleaner
        scrollPane.setBackground(new Color(199, 220, 247)); // Set background color to match the frame
        scrollPane.getViewport().setBackground(new Color(199, 220, 247)); // Set viewport background color to match the frame

        frame.add(scrollPane, BorderLayout.CENTER);

        // Set up the confirm button
        JButton closeButton = new RoundedButton("Confirm");
        closeButton.setFont(new Font("Arial", Font.BOLD, 16));
        closeButton.setBackground(new Color(70, 130, 180));
        closeButton.setForeground(Color.WHITE); // White text
        closeButton.setOpaque(false); // Ensure the button is not opaque
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
        buttonPanel.setBackground(new Color(199, 220, 247)); // Set the background color to match the frame

        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
