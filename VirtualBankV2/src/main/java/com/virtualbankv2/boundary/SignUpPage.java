package com.virtualbankv2.boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.virtualbankv2.entity.User;
import com.virtualbankv2.entity.*;

import static com.virtualbankv2.boundary.Reader.users;


import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.virtualbankv2.entity.User;

import static com.virtualbankv2.boundary.Reader.users;

public class SignUpPage extends JFrame {

    private JTextField nameField;
    private JPasswordField passwordField, confirmPasswordField;
    private JButton submitButton, returnButton;
    private JLabel nameLabel, passwordLabel, confirmPasswordLabel;
    private Font fieldFont = new Font("Arial", Font.BOLD, 25); // Font for fields and buttons
    private Color lightBlue = new Color(199, 220, 247); // Light blue background
    private Color deepBlue = new Color(93, 97, 195); // Deep blue for buttons

    public SignUpPage() {
        setTitle("Sign Up");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1200, 900));
        setLayout(new BorderLayout());

        // Create a main panel with GridBagLayout to hold the login panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(199, 220, 247)); // Different color from the login panel
        GridBagConstraints gbcMain = new GridBagConstraints();
        gbcMain.fill = GridBagConstraints.BOTH;
        gbcMain.weightx = 1;
        gbcMain.weighty = 1;
        gbcMain.insets = new Insets(200, 200, 200, 200); // Margin around the login panel

        RoundedPanel loginPanel = new RoundedPanel(20);
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setPreferredSize(new Dimension(400,300));
        loginPanel.setMaximumSize(new Dimension(400,300));
        loginPanel.setMinimumSize(new Dimension(400,300));
        loginPanel.setBackground(new Color(133, 149, 188)); // Set the login panel color
        GridBagConstraints gbcLogin = new GridBagConstraints();

        // Username panel
        nameLabel = new JLabel("Username *");
        nameField = new RoundedTextField(10);
        setupField(nameLabel, nameField, loginPanel, gbcLogin, 0);

        // Password panel
        passwordLabel = new JLabel("Password *");
        passwordField = new RoundedPasswordField(10);
        setupField(passwordLabel, passwordField, loginPanel, gbcLogin, 1);

        // Confirm Password panel
        confirmPasswordLabel = new JLabel("Confirm password *");
        confirmPasswordField = new RoundedPasswordField(10);
        setupField(confirmPasswordLabel, confirmPasswordField, loginPanel, gbcLogin, 2);

        // Submit button
        submitButton = new RoundedButton("Submit");
        submitButton.setPreferredSize(new Dimension(200,50));
        setupButton(submitButton, loginPanel, gbcLogin, 0,3);

        //Return button
        returnButton = ReturnButton.createReturnButton(this,"LoginPage");
        setupButton(returnButton,loginPanel,gbcLogin,1,3);

        // Add the login panel to the main panel
        mainPanel.add(loginPanel, gbcMain);
        add(mainPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        submitButton.addActionListener(e -> performSignUp());
    }

    private void setupField(JLabel label, JTextField field, JPanel panel, GridBagConstraints gbc, int gridy) {
        label.setFont(fieldFont);
        field.setFont(fieldFont);
        gbc.gridx = 0;
        gbc.gridy = gridy;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(label, gbc);
        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    private void setupButton(JButton button, JPanel panel, GridBagConstraints gbc, int gridx,int gridy) {
        button.setFont(fieldFont);
        button.setBackground(deepBlue);
        button.setForeground(Color.WHITE);
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(button, gbc);
    }

    private void performSignUp() {
        String userName = nameField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (userName.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Simulate adding user and show success
        User newUser = new User(userName, password);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String currentDate = dateFormatter.format(LocalDate.now());
        Interest interest = new Interest(newUser.getUsername(), currentDate);

        Writer writer = new Writer();
        writer.writeSingleUser(newUser);
        writer.writeSingleInterest(interest);

        // Normally here you would interact with a data manager or database
        JOptionPane.showMessageDialog(this, "Account created successfully!");
    }

    public void setNameFieldText(String text) {
        nameField.setText(text);
    }

    public void setPasswordFieldText(String text) {
        passwordField.setText(text);
    }

    public void setConfirmPasswordFieldText(String text) {
        confirmPasswordField.setText(text);
    }

    public void clickSubmitButton() {
        submitButton.doClick();
    }

    public boolean isSubmitButtonPressed() {
        return submitButton.getModel().isPressed();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SignUpPage::new);
    }
}

