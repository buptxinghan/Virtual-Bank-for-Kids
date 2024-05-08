package com.virtualbankv2.boundary;

import javax.swing.*;
import java.awt.*;

import com.virtualbankv2.entity.User;

import static com.virtualbankv2.control.VirtualBankApplication.currentUser;
import static com.virtualbankv2.boundary.Reader.users;

public class LoginPage {

    private JFrame frame;
    private JTextField nameField;
    private JPasswordField passwordField;
    private JButton loginButton, signUpButton;
    private JLabel usernameLabel, passwordLabel, titleLabel;

    public LoginPage() {
        // Set a consistent font for all labels and buttons
        Font fieldFont = new Font("Arial", Font.BOLD, 25);

        // Create and set up the window
        frame = new JFrame("Bank Login System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1200, 900));
        frame.setLayout(new BorderLayout());

// Create a panel for the components
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(199, 220, 247)); // Light blue background
        mainPanel.add(Box.createVerticalStrut(120));
// Title label
        titleLabel = new JLabel("Login", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36)); // Larger font for the title
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);

// Add vertical strut for spacing
        mainPanel.add(Box.createVerticalStrut(20)); // Adds 20 pixels of space

// Username panel
        JPanel usernamePanel = new JPanel();
        usernamePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        usernamePanel.setBackground(new Color(199, 220, 247)); // Consistent with main panel
        usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(fieldFont);
        nameField = new JTextField(20);
        nameField.setFont(fieldFont);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(nameField);
        mainPanel.add(usernamePanel);

// Password panel
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        passwordPanel.setBackground(new Color(199, 220, 247));
        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(fieldFont);
        passwordField = new JPasswordField(20);
        passwordField.setFont(fieldFont);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        mainPanel.add(passwordPanel);

// Add vertical strut for spacing
        mainPanel.add(Box.createVerticalStrut(20)); // Adds 20 pixels of space

// Login button panel
        JPanel loginButtonPanel = new JPanel();
        loginButtonPanel.setLayout(new FlowLayout());
        loginButtonPanel.setBackground(new Color(199, 220, 247));
        loginButton = new JButton("Login");
        loginButton.setFont(fieldFont);
        loginButton.setBackground(new Color(93, 97, 195)); // Deep blue for buttons
        loginButton.setForeground(Color.WHITE); // White text on buttons
        loginButtonPanel.add(loginButton);
        mainPanel.add(loginButtonPanel);

// Add vertical strut for spacing
        mainPanel.add(Box.createVerticalStrut(0)); // Adds 20 pixels of space

// Sign Up button panel
        JPanel signUpButtonPanel = new JPanel();
        signUpButtonPanel.setLayout(new FlowLayout());
        signUpButtonPanel.setBackground(new Color(199, 220, 247));
        signUpButton = new JButton("Sign Up");
        signUpButton.setFont(fieldFont);
        signUpButton.setBackground(new Color(93, 97, 195));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.addActionListener(e -> new com.virtualbankv2.boundary.SignUpPage());
        signUpButtonPanel.add(signUpButton);
        mainPanel.add(signUpButtonPanel);


        // Login button action
        loginButton.addActionListener(e -> {
            String userName = nameField.getText();
            String password = new String(passwordField.getPassword());
            if (checkCredentials(userName, password)) {
                frame.setVisible(false);
                new com.virtualbankv2.boundary.HomePage();
            } else {
                JOptionPane.showMessageDialog(frame, "Error: Incorrect Username or Password!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add main panel to frame
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

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
        Reader reader = new Reader();
        SwingUtilities.invokeLater(() -> new LoginPage());
    }

    public JFrame getFrame() {
        return frame;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getSignUpButton() {
        return signUpButton;
    }
}