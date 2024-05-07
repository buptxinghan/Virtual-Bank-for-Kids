package com.virtualbankv2.boundary;

import com.virtualbankv2.entity.Goal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CreateGoalPage extends JFrame {
    private JTextField goalNameField;
    private JTextArea descriptionArea;
    private JTextField targetAmountField;
    private JTextField startDateField;
    private JTextField endDateField;
    private JButton saveButton;

    public CreateGoalPage() {
        createUI();
    }

    private void createUI() {
        setTitle("Create my goal");
        setSize(300, 400); // set frame size
        setLayout(new GridLayout(6, 2, 10, 10)); // set layout

        // Initialize components
        goalNameField = new JTextField();
        descriptionArea = new JTextArea(5, 20);
        targetAmountField = new JTextField();
        startDateField = new JTextField();
        endDateField = new JTextField();
        saveButton = new JButton("Save");

        // Adding components to JFrame
        add(new JLabel("Goal Name"));
        add(goalNameField);
        add(new JLabel("Description"));
        add(new JScrollPane(descriptionArea));
        add(new JLabel("Target Amount"));
        add(targetAmountField);
        add(new JLabel("Start Date (yyyy/MM/dd)"));
        add(startDateField);
        add(new JLabel("End Date (yyyy/MM/dd)"));
        add(endDateField);
        add(saveButton);

        // Action Listener for Save Button
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveGoal();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void saveGoal() {
        try {
            String goalName = goalNameField.getText();
            String description = descriptionArea.getText();
            double targetAmount = Double.parseDouble(targetAmountField.getText());
            double currentAmount = Reader.accounts.get(4).getBalance();
            LocalDate startDate = LocalDate.parse(startDateField.getText(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            LocalDate endDate = LocalDate.parse(endDateField.getText(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            String username = Reader.users.get(0).getUsername();

            // Create a Goal object
            Goal goal = new Goal(goalName, description, targetAmount, currentAmount, username, startDate.toString(), endDate.toString());

            // Use Writer to save the goal
            Writer writer = new Writer();
            writer.writeSingleGoal(goal);

            JOptionPane.showMessageDialog(this, "Goal saved successfully!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for target amount.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Please enter the date in the correct format (yyyy/MM/dd).", "Date Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error saving the goal: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CreateGoalPage(); // Run the constructor
            }
        });
    }
}
