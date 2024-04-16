package com.virtualbankv1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Random;

public class SignUp {

    private JFrame frame;
    private JTextField firstNameField, lastNameField;
    private JPasswordField passwordField, confirmPasswordField;
    private JButton submitButton;

    public SignUp() {
        // Create and set up the window
        frame = new JFrame("Sign Up");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        // Create and populate the panel
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        panel.add(new JLabel("First name *"));
        firstNameField = new JTextField();
        panel.add(firstNameField);

        panel.add(new JLabel("Last name *"));
        lastNameField = new JTextField();
        panel.add(lastNameField);

        panel.add(new JLabel("Password *"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        panel.add(new JLabel("Confirm password *"));
        confirmPasswordField = new JPasswordField();
        panel.add(confirmPasswordField);

        submitButton = new JButton("Submit");
        panel.add(submitButton);

        // Add action listener to the button
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                if (password.equals(confirmPassword)) {
                    String userId = generateRandomId();
                    writeUserToCsv(userId, firstName, lastName, password);
                    JOptionPane.showMessageDialog(frame, "Account created successfully! Your user ID is " + userId);
                } else {
                    JOptionPane.showMessageDialog(frame, "Error: Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Set the size of the window and make it visible
        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(350, 200);
        frame.setVisible(true);
    }

    private String generateRandomId() {
        return String.format("%08d", new Random().nextInt(100_000_000));
    }

    private void writeUserToCsv(String userId, String firstName, String lastName, String password) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("User.csv", true)))) {
            out.println(userId + "," + lastName + "," + firstName + "," + password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Schedule a job for the event dispatch thread: creating and showing this application's GUI
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SignUp();
            }
        });
    }
}
