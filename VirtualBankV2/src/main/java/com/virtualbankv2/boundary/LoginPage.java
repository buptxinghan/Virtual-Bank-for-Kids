package com.virtualbankv2.boundary;

import javax.swing.*;
import java.awt.*;

import com.virtualbankv2.entity.RoundedButton;
import com.virtualbankv2.entity.RoundedPanel;
import com.virtualbankv2.entity.RoundedPasswordField;
import com.virtualbankv2.control.LoginController;
import com.virtualbankv2.entity.RoundedTextField;

public class LoginPage extends JFrame {

    private JFrame frame;
    private JTextField nameField;
    private JPasswordField passwordField;
    private RoundedButton loginButton, signUpButton;
    private JLabel usernameLabel, passwordLabel, titleLabel;
    private LoginController loginController;

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


    public LoginPage() {
        loginController = new LoginController();

        // Set a consistent font for all labels and buttons
        Font fieldFont = new Font("Arial", Font.BOLD, 24);
        // Create and set up the window
        frame = new JFrame("Bank Login System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1200, 900));
        frame.setLayout(new BorderLayout());

        // Create a login panel with GridBagLayout
        RoundedPanel loginPanel = new RoundedPanel(20);
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setPreferredSize(new Dimension(600,450));
        loginPanel.setMaximumSize(new Dimension(400,300));
        loginPanel.setBackground(new Color(133, 149, 188)); // Set the login panel color
        GridBagConstraints gbcLogin = new GridBagConstraints();

        // Title label
        titleLabel = new JLabel(" Login ", JLabel.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 36)); // Larger font for the title
        gbcLogin.gridx = 0;
        gbcLogin.gridy = 0;
        gbcLogin.gridwidth = 4;
        gbcLogin.insets = new Insets(20, 0, 20, 0);
        loginPanel.add(titleLabel, gbcLogin);

        // Username label and text field
        usernameLabel = new JLabel("Username:  ");
        usernameLabel.setFont(fieldFont);
        gbcLogin.gridx = 0;
        gbcLogin.gridy = 1;
        gbcLogin.gridwidth = 1;
        gbcLogin.anchor = GridBagConstraints.LINE_END;
        loginPanel.add(usernameLabel, gbcLogin);

        nameField = new RoundedTextField(10);
        nameField.setFont(fieldFont);
        gbcLogin.gridx = 1;
        gbcLogin.gridy = 1;
        gbcLogin.gridwidth = 1;
        gbcLogin.gridheight = 1;
        loginPanel.add(nameField, gbcLogin);

        // Password label and text field
        passwordLabel = new JLabel("Password:  ");
        passwordLabel.setFont(fieldFont);
        gbcLogin.gridx = 0;
        gbcLogin.gridy = 2;
        gbcLogin.gridwidth = 1;
        gbcLogin.gridheight = 1;
        gbcLogin.anchor = GridBagConstraints.LINE_END;
        loginPanel.add(passwordLabel, gbcLogin);

        passwordField = new RoundedPasswordField(10);
        passwordField.setFont(fieldFont);
        passwordField.addActionListener(e -> performLogin());
        gbcLogin.gridx = 1;
        gbcLogin.gridy = 2;
        gbcLogin.gridwidth = 1; // 例如，这里设置为2，文本框将占据2列
        gbcLogin.gridheight = 1;
        loginPanel.add(passwordField, gbcLogin);

        // Login and Sign Up buttons
        loginButton = new RoundedButton("Login");
        loginButton.setPreferredSize(new Dimension(150,40));
        loginButton.setFont(fieldFont);
        loginButton.setBackground(new Color(93, 97, 195));
        loginButton.setForeground(Color.WHITE);
        gbcLogin.gridx = 0;
        gbcLogin.gridy = 3;
        gbcLogin.insets = new Insets(10, 0, 10, 20);
        loginPanel.add(loginButton, gbcLogin);

        signUpButton = new RoundedButton("Sign Up");
        signUpButton.setPreferredSize(new Dimension(150,40));
        signUpButton.setFont(fieldFont);
        signUpButton.setBackground(new Color(93, 97, 195));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.addActionListener(e -> {
            frame.dispose();
            new SignUpPage();
        });
        gbcLogin.gridx = 1;
        gbcLogin.gridy = 3;
        gbcLogin.insets = new Insets(10, 5, 10, 0);
        loginPanel.add(signUpButton, gbcLogin);

        // Create a main panel with GridBagLayout to hold the login panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(199, 220, 247)); // Different color from the login panel
        GridBagConstraints gbcMain = new GridBagConstraints();
        gbcMain.fill = GridBagConstraints.BOTH;
        gbcMain.weightx = 1;
        gbcMain.weighty = 1;
        gbcMain.insets = new Insets(40, 40, 40, 40); // Margin around the login panel

        // Add the login panel to the main panel
        mainPanel.add(loginPanel, gbcMain);

        // Add main panel to frame
        JPanel outerPanel = new JPanel(new GridBagLayout());
        outerPanel.setBackground(new Color(199, 220, 247));
        outerPanel.add(loginPanel, new GridBagConstraints());

        // Login button action
        loginButton.addActionListener(e -> {
            String userName = nameField.getText();
            String password = new String(passwordField.getPassword());
            if (loginController.checkCredentials(userName, password)) {
                frame.dispose();
                new HomePage();
            } else {
                JOptionPane.showOptionDialog(
                        this,
                        "Error: Incorrect Username or Password!",
                        "Error",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.ERROR_MESSAGE,
                        null,
                        new String[] {"OK"},
                        "OK"
                );
            }
        });

        frame.add(outerPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    private void performLogin() {
        String userName = nameField.getText();
        String password = new String(passwordField.getPassword());
        if (loginController.checkCredentials(userName, password)) {
            frame.dispose();
            new HomePage();
        } else {
            JOptionPane.showOptionDialog(
                    this,
                    "Error: Incorrect Username or Password!",
                    "Error",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE,
                    null,
                    new String[]{"OK"},
                    "OK"
            );
        }
    }

}
