package com.virtualbankv2.boundary;

import com.virtualbankv2.entity.Goal;
import com.virtualbankv2.entity.RoundedButton;
import com.virtualbankv2.entity.ReturnButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.virtualbankv2.control.VirtualBankApplication.currentUser;

public class CreateGoalUI extends JFrame {
    private JTextField goalNameField = new JTextField();
    private JTextField descriptionField = new JTextField();
    private JTextField targetAmountField = new JTextField();
    private RoundedButton saveButton;
    private JButton returnButton;
    private String currentUsername = currentUser.getUsername();

    public CreateGoalUI() {
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
        JLabel headerLabel = new JLabel("<html><font size=7 color=" + getColorHex(new Color(93, 97, 195)) + ">Create a Goal</font></html>", JLabel.CENTER);
        topPanel.add(headerLabel);
        add(topPanel, BorderLayout.NORTH);

        // Middle panel
        JPanel middlePanel = new JPanel(new GridLayout(6, 1, 10, 10));
        middlePanel.setBackground(new Color(199, 220, 247));

        addLabelAndTextField("Goal Name", goalNameField, middlePanel, font2);
        addLabelAndTextField("Target Amount", targetAmountField, middlePanel, font2);
        addLabelAndTextField("Description", descriptionField, middlePanel, font2);

        add(middlePanel, BorderLayout.CENTER);

        // Bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(199, 220, 247));
        saveButton = new RoundedButton("<html><font size='6'>Save</font></html>");
        saveButton.setBackground(new Color(70, 130, 180));
        saveButton.setForeground(Color.WHITE);
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.setPreferredSize(new Dimension(200,50));
        saveButton.setMinimumSize(new Dimension(200, 50));
        saveButton.setMaximumSize(new Dimension(200, 50));
        returnButton = ReturnButton.createReturnButton(this, "GoalOverviewUI");
        bottomPanel.add(saveButton);
        bottomPanel.add(returnButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Action Listener for Save Button
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveGoal();
            }
        });

        setVisible(true);
    }

    private void addLabelAndTextField(String labelText, JTextField textField, JPanel panel, Font font) {
        JLabel label = new JLabel("<html><font size=5 color=" + getColorHex(new Color(93, 97, 195)) + ">" + labelText + "</font></html>", JLabel.LEFT);
        label.setFont(font);
        panel.add(label);
        textField.setFont(font);
        panel.add(textField);
    }

//    private void addLabelAndComponent(String labelText, JComponent component, JPanel panel, Font font, boolean isTextArea) {
//        JLabel label = new JLabel("<html><font size=5 color=" + getColorHex(new Color(93, 97, 195)) + ">" + labelText + "</font></html>", JLabel.LEFT);
//        label.setFont(font);
//        panel.add(label);
//
//        if (isTextArea) {
//            descriptionField = new JTextField();
//            descriptionField.setFont(font);
//        }
//        panel.add(descriptionField);
//    }

    /**
     * Retrieves the color hex code of the specified color.
     *
     * @param color The Color object.
     * @return A String representing the color hex code.
     */
    private String getColorHex(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    private void saveGoal() {
        try {
            String goalName = goalNameField.getText();
            String description = descriptionField.getText();
            double targetAmount = Double.parseDouble(targetAmountField.getText());
            double currentAmount = 10; // Assuming the logic to fetch current amount is defined elsewhere

            // Create a Goal object
            Goal goal = new Goal(goalName, description, targetAmount, currentAmount, currentUsername);

            // Logic to save the goal (e.g., database or file system)
            Writer writer = new Writer();
            writer.writeSingleGoal(goal);

            JOptionPane.showMessageDialog(this, "Goal saved successfully!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for target amount.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error saving the goal: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
