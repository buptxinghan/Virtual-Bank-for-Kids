package com.virtualbankv1.boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class LoginPage {

    private JFrame frame;
    private JTextField userIdField;
    private JPasswordField passwordField;
    private JButton loginButton, signUpButton;

    public LoginPage() {
        // Create and set up the window
        frame = new JFrame("Bank Login System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        // Create and populate the panel
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        panel.add(new JLabel("User ID:"));
        userIdField = new JTextField();
        panel.add(userIdField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        loginButton = new JButton("Login");
        panel.add(loginButton);

        signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the sign-up form
                new SignUpPage();
                // You can also choose to hide the login window if desired
                // frame.setVisible(false);
            }
        });
        panel.add(signUpButton);

        // Add action listener to the login button
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userId = userIdField.getText();
                String password = new String(passwordField.getPassword());

                if (checkCredentials(userId, password)) {
                    JOptionPane.showMessageDialog(frame, "Login successful!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Error: Incorrect User ID or Password!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Set the size of the window and make it visible
        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(350, 150);
        frame.setVisible(true);
    }

    private boolean checkCredentials(String userId, String password) {
        try (Scanner scanner = new Scanner(new File("User.csv"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] credentials = line.split(",");
                if (credentials[0].equals(userId) && credentials[3].equals(password)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(frame, "Error: User file not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public static void main(String[] args) {
        // Schedule a job for the event dispatch thread: creating and showing this application's GUI
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginPage();
            }
        });
    }
}
