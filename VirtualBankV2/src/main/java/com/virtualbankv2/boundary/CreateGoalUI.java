package com.virtualbankv2.boundary;

import com.virtualbankv2.entity.Goal;
import com.virtualbankv2.entity.RoundedButton;
import com.virtualbankv2.entity.ReturnButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.virtualbankv2.control.VirtualBankApplication.currentUser;

/**
 * The {@code CreateGoalUI} class represents the user interface for creating a new goal
 * in the Virtual Bank application. It allows users to input the goal name, description,
 * and target amount, and then save the goal.
 *
 * @version 2.0
 * @since 2024-05-10
 * @author Tianzhi Li
 *
 */
public class CreateGoalUI extends JFrame {
    private JTextField goalNameField = new JTextField();
    private JTextField descriptionField = new JTextField();
    private JTextField targetAmountField = new JTextField();
    private RoundedButton saveButton;
    private JButton returnButton;
    private String currentUsername = currentUser.getUsername();

    /**
     * Constructs a new {@code CreateGoalUI} object and initializes the user interface.
     */
    public CreateGoalUI() {
        createUI();
    }

    /**
     * Initializes the user interface for creating a new goal.
     */
    private void createUI() {
        setTitle("Create My Goal");
        getContentPane().setBackground(new Color(199, 220, 247));
        setSize(1200, 900); // Set frame size
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); // Set layout manager

        Font font1 = new Font("Arial", Font.BOLD, 40);
        Font font2 = new Font("Arial", Font.BOLD, 40);

        // Top panel
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(199, 220, 247));
        JLabel headerLabel = new JLabel("<html><font size=12 color=" + getColorHex(new Color(93, 97, 195)) + ">Create a Goal</font></html>", JLabel.CENTER);
        topPanel.add(headerLabel);
        add(topPanel, BorderLayout.NORTH);

        // Middle panel
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridBagLayout());
        middlePanel.setBackground(new Color(199, 220, 247));
        middlePanel.setBorder(BorderFactory.createEmptyBorder(0, 150, 50, 150));
        addLabelAndTextField("Goal Name", goalNameField, middlePanel, font2);
        addLabelAndTextField("Target Amount", targetAmountField, middlePanel, font2);
        addLabelAndTextField("Description", descriptionField, middlePanel, font2);

        add(middlePanel, BorderLayout.CENTER);

        // Bottom panel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 80, 10));
        bottomPanel.setBackground(new Color(199, 220, 247));
        saveButton = new RoundedButton("<html><font size='6'>Save</font></html>");
        saveButton.setBackground(new Color(70, 130, 180));
        saveButton.setForeground(Color.WHITE);
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.setPreferredSize(new Dimension(200, 50));
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

    /**
     * Adds a label and text field to the specified panel.
     *
     * @param labelText the text for the label
     * @param textField the text field to add
     * @param panel the panel to add the components to
     * @param font the font to use for the label and text field
     */
    private void addLabelAndTextField(String labelText, JTextField textField, JPanel panel, Font font) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Padding: top, left, bottom, right
        gbc.gridx = 0;  // X coordinate
        gbc.gridy = GridBagConstraints.RELATIVE;  // Automatically increment Y coordinate
        gbc.weightx = 1.0;  // Weight in X direction, 1.0 means it can stretch

        JLabel label = new JLabel("<html><font size=6 color=" + getColorHex(new Color(93, 97, 195)) + ">" + labelText + "</font></html>", JLabel.LEFT);
        label.setFont(font);
        panel.add(label, gbc);

        gbc.gridy = GridBagConstraints.RELATIVE;  // Continue to automatically increment Y coordinate
        textField.setFont(font);
        panel.add(textField, gbc);
    }

    /**
     * Retrieves the color hex code of the specified color.
     *
     * @param color the Color object
     * @return a String representing the color hex code
     */
    private String getColorHex(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    /**
     * Saves the goal entered by the user. Validates the input fields and saves the goal
     * to persistent storage if the inputs are valid.
     */
    private void saveGoal() {
        try {
            String goalName = goalNameField.getText();
            String description = descriptionField.getText();
            double targetAmount = Double.parseDouble(targetAmountField.getText());
            double currentAmount = 0; // Assuming the logic to fetch current amount is defined elsewhere

            // Create a Goal object
            Goal goal = new Goal(goalName, description, targetAmount, currentAmount, currentUsername);

            // Logic to save the goal (e.g., database or file system)
            Writer writer = new Writer();
            writer.writeSingleGoal(goal);
            int response = JOptionPane.showOptionDialog(
                    this,
                    "Goal saved successfully!",
                    "Success",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    new String[] {"OK"},
                    "OK"
            );
            goalNameField.setText("");
            descriptionField.setText("");
            targetAmountField.setText("");

            // Check if "OK" was pressed
            if (response == 0) {
                dispose();
                new GoalOverviewUI();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showOptionDialog(
                    this,
                    "Please enter a valid number for target amount.",
                    "Error",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE,
                    null,
                    new String[] {"OK"},
                    "OK"
            );
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error saving the goal: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Gets the text field for entering the goal name.
     *
     * @return the goal name text field
     */
    public JTextField getGoalNameField() {
        return goalNameField;
    }

    /**
     * Gets the text field for entering the description.
     *
     * @return the description text field
     */
    public JTextField getDescriptionField() {
        return descriptionField;
    }

    /**
     * Gets the text field for entering the target amount.
     *
     * @return the target amount text field
     */
    public JTextField getTargetAmountField() {
        return targetAmountField;
    }

    /**
     * Gets the save button.
     *
     * @return the save button
     */
    public JButton getSaveButton() {
        return saveButton;
    }
}
