package com.virtualbankv2.boundary;

import com.virtualbankv2.entity.Goal;
import com.virtualbankv2.entity.RoundedButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateGoalPage extends JFrame {
    private JTextField goalNameField;
    private JTextArea descriptionArea;
    private JTextField targetAmountField;
    private RoundedButton saveButton;
    private String currentUsername;

    public CreateGoalPage(String username) {
        this.currentUsername = username;
        createUI();
    }

    private void createUI() {
        setTitle("Create My Goal");
        getContentPane().setBackground(new Color(199, 220, 247));
        setSize(800, 600); // Set frame size
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); // Set layout manager

        Font font1 = new Font("Arial", Font.BOLD, 40);
        Font font2 = new Font("Arial",Font.BOLD,24);
        // Top panel
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(199, 220, 247));
        JLabel headerLabel = new JLabel("Create a Goal", JLabel.CENTER);
        headerLabel.setFont(font1);
        headerLabel.setForeground(new Color(93, 97, 195));
        topPanel.add(headerLabel);
        add(topPanel, BorderLayout.NORTH);

        // Middle panel
        JPanel middlePanel = new JPanel(new GridLayout(3, 2, 10, 10));
        middlePanel.setBackground(new Color(199, 220, 247));

        Font labelFont = new Font("Arial", Font.BOLD, 28); // Font for labels
        Font inputFont = new Font("Arial", Font.BOLD, 24); // Font for input fields

        // Goal Name Label
        JLabel goalNameLabel = new JLabel("Goal Name");
        goalNameLabel.setFont(labelFont);
        goalNameLabel.setForeground(new Color(93, 97, 195)); // Set color of the label
        middlePanel.add(goalNameLabel);

        // Goal Name Field
        goalNameField = new JTextField();
        goalNameField.setFont(inputFont);
        goalNameField.setPreferredSize(new Dimension(100, 20)); // Set size of the input field
        middlePanel.add(goalNameField);

        // Description Label
        JLabel descriptionLabel = new JLabel("Description");
        descriptionLabel.setFont(labelFont);
        descriptionLabel.setForeground(new Color(93, 97, 195)); // Set color of the label
        middlePanel.add(descriptionLabel);

        // Description Area
        descriptionArea = new JTextArea(5, 20);
        descriptionArea.setFont(inputFont);
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        scrollPane.setPreferredSize(new Dimension(100, 20)); // Set size of the scroll pane
        middlePanel.add(scrollPane);

        // Target Amount Label
        JLabel targetAmountLabel = new JLabel("Target Amount");
        targetAmountLabel.setFont(labelFont);
        targetAmountLabel.setForeground(new Color(93, 97, 195)); // Set color of the label
        middlePanel.add(targetAmountLabel);

        // Target Amount Field
        targetAmountField = new JTextField();
        targetAmountField.setFont(inputFont);
        targetAmountField.setPreferredSize(new Dimension(100, 20)); // Set size of the input field
        middlePanel.add(targetAmountField);

        add(middlePanel, BorderLayout.CENTER);


        // Bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(199, 220, 247));
        saveButton = new RoundedButton("Save");
        saveButton.setFont(font2);
        bottomPanel.add(saveButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Action Listener for Save Button
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveGoal();
            }
        });

        setVisible(true);
    }

    public JTextField getGoalNameField() {
        return goalNameField;
    }

    public JTextArea getDescriptionArea() {
        return descriptionArea;
    }

    public JTextField getTargetAmountField() {
        return targetAmountField;
    }

    public RoundedButton getSaveButton() {
        return saveButton;
    }

    private void saveGoal() {
        try {
            String goalName = goalNameField.getText();
            String description = descriptionArea.getText();
            double targetAmount = Double.parseDouble(targetAmountField.getText());
            double currentAmount = Reader.accounts.get(4).getBalance();

            // Create a Goal object
            Goal goal = new Goal(goalName, description, targetAmount, currentAmount, currentUsername);

            // Use Writer to save the goal
            Writer writer = new Writer();
            writer.writeSingleGoal(goal);

            JOptionPane.showMessageDialog(this, "Goal saved successfully!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for target amount.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error saving the goal: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                new CreateGoalPage(); // Run the constructor
//            }
//        });
//    }
}
