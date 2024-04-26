package com.virtualbankv1.boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import com.virtualbankv1.entity.User;

import static com.virtualbankv1.control.VirtualBankApplication.currentUser;
import static com.virtualbankv1.boundary.Reader.users;

public class LoginPage {

    private JFrame frame;
    private JTextField nameField;
    private JPasswordField passwordField;
    private JButton loginButton, signUpButton;
    private JLabel usernameLabel, passwordLabel;

    public LoginPage() {
        // Create and set up the window
        frame = new JFrame("Bank Login System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1200, 900));
        frame.setLayout(new BorderLayout(10, 10));
        frame.getContentPane().setBackground(new Color(199, 220, 247)); // Light blue background

        // Create and populate the panel
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBackground(new Color(199, 220, 247)); // Light blue background

        usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(new Color(133, 149, 188)); // Mid-gray for label text
        panel.add(usernameLabel);

        nameField = new JTextField();
        nameField.setBackground(new Color(112, 172, 249)); // Mid-blue for text field
        nameField.setForeground(Color.BLACK); // Adjust text color as needed
        panel.add(nameField);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(new Color(133, 149, 188)); // Mid-gray for label text
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBackground(new Color(112, 172, 249)); // Mid-blue for password field
        passwordField.setForeground(Color.BLACK); // Adjust text color as needed
        panel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBackground(new Color(93, 97, 195)); // Deep blue for buttons
        loginButton.setForeground(Color.WHITE); // White text on buttons
        panel.add(loginButton);

        signUpButton = new JButton("Sign Up");
        signUpButton.setBackground(new Color(93, 97, 195)); // Deep blue for buttons
        signUpButton.setForeground(Color.WHITE); // White text on buttons
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new com.virtualbankv1.boundary.SignUpPage();
            }
        });
        panel.add(signUpButton);
        // Add action listener to the login button
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userName = nameField.getText();
                String password = new String(passwordField.getPassword());

                if (checkCredentials(userName, password)) {
                    frame.setVisible(false); // Hide or dispose login frame
                    new com.virtualbankv1.boundary.HomePage(); // Open the HomePage
                    //JOptionPane.showMessageDialog(frame, "Login successful!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Error: Incorrect Username or Password!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Set the size of the window and make it visible
        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }

//    private boolean checkCredentials(String userName, String password) {
//        try (Scanner scanner = new Scanner(new File("src/Data/Users.csv"))) {
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//                String[] credentials = line.split(",");
//                if (credentials[0].equals(userName) && credentials[1].equals(password)) {
//
//                    return true;
//                }
//            }
//        } catch (FileNotFoundException e) {
//            JOptionPane.showMessageDialog(frame, "Error: User file not found!", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//        return false;
//    }

    private boolean checkCredentials(String userName, String password) {
        for (User user : users) {
            if (user.getUsername().equals(userName) && user.getPassword().equals(password)) {
                currentUser = user;
                return true;
            }
        }
        JOptionPane.showMessageDialog(frame, "Error: User not found!", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    public static void main(String[] args) {
        // Schedule a job for the event dispatch thread: creating and showing this application's GUI
        SwingUtilities.invokeLater(new Runnable() {
            Reader reader = new Reader();
            public void run() {
                new LoginPage();
            }
        });
    }
}

