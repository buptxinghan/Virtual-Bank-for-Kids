package com.virtualbankv2.boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SignUpPage::new);
    }
}


//public class SignUpPage {
//
//    private JFrame frame;
//    private JTextField nameField;
//    private JPasswordField passwordField, confirmPasswordField;
//    private JButton submitButton;
//    private Color lightBlue = new Color(199, 220, 247); // Light blue background
//    private Color deepBlue = new Color(93, 97, 195); // Deep blue for buttons
//    private Color white = Color.WHITE; // White text on buttons
//    private Font fieldFont = new Font("Arial", Font.BOLD, 25); // Font for fields and buttons
//    private boolean successMessageShown;
//    private boolean errorMessageShown;
//
//    public SignUpPage() {
//        // Create and set up the window
//        frame = new JFrame("Sign Up");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setPreferredSize(new Dimension(1200, 900));
//        frame.setLayout(new BorderLayout());
//
//        // Main panel with box layout for vertical stacking
//        JPanel mainPanel = new JPanel();
//        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
//        mainPanel.setBackground(lightBlue);
//        frame.add(mainPanel, BorderLayout.CENTER);
//
//        // Add vertical strut for spacing
//        mainPanel.add(Box.createVerticalStrut(100));
//
//        // Username panel
//        JPanel usernamePanel = createFieldPanel("Username *", true);
//        mainPanel.add(usernamePanel);
//
//        // Password panel
//        JPanel passwordPanel = createFieldPanel("Password *", false);
//        mainPanel.add(passwordPanel);
//
//        // Confirm Password panel
//        JPanel confirmPasswordPanel = createFieldPanel("Confirm password *", false);
//        mainPanel.add(confirmPasswordPanel);
//
//        // Submit button panel
//        JPanel submitButtonPanel = new JPanel();
//        submitButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
//        submitButtonPanel.setBackground(lightBlue);
//        submitButton = new JButton("Submit");
//        submitButton.setFont(fieldFont);
//        submitButton.setBackground(deepBlue);
//        submitButton.setForeground(white);
//        submitButtonPanel.add(submitButton);
//        mainPanel.add(submitButtonPanel);
//
//        // Add action listener to the button
//        submitButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                performSignUp();
//            }
//        });
//
//        // Set the size of the window and make it visible
//        frame.pack();
//        frame.setLocationRelativeTo(null); // Center the window
//        frame.setVisible(true);
//
//        // Initialize flags
//        successMessageShown = false;
//        errorMessageShown = false;
//    }
//
//    private JPanel createFieldPanel(String label, boolean isTextField) {
//        JPanel fieldPanel = new JPanel();
//        fieldPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
//        fieldPanel.setBackground(lightBlue);
//
//        JLabel fieldLabel = new JLabel(label);
//        fieldLabel.setFont(fieldFont);
//
//        JTextField textField;
//        if (isTextField) {
//            textField = new JTextField(20);
//        } else {
//            textField = new JPasswordField(20);
//        }
//        textField.setFont(fieldFont);
//
//        fieldPanel.add(fieldLabel);
//        fieldPanel.add(textField);
//
//        // 将textField赋值给相应的类变量
//        if (label.equals("Username *")) {
//            nameField = textField;
//        } else if (label.equals("Password *")) {
//            passwordField = (JPasswordField) textField;
//        } else if (label.equals("Confirm password *")) {
//            confirmPasswordField = (JPasswordField) textField;
//        }
//
//        return fieldPanel;
//    }
//
//
//    private void performSignUp() {
//        String userName = nameField.getText();
//        String password = new String(passwordField.getPassword());
//        String confirmPassword = new String(confirmPasswordField.getPassword());
//
//        if (password.equals(confirmPassword)) {
//            User tempUser = new User(userName, password);
//            Writer writer = new Writer();
//            writer.writeSingleUser(tempUser);
//            users.add(tempUser);
//            JOptionPane.showMessageDialog(frame, "Account created successfully!");
//        } else {
//            JOptionPane.showMessageDialog(frame, "Error: Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    public static void main(String[] args) {
//        // Schedule a job for the event dispatch thread: creating and showing this application's GUI
//        SwingUtilities.invokeLater(new Runnable() {
//            Reader reader = new Reader();
//            public void run() {
//                new SignUpPage();
//            }
//        });
//    }
//
//    public JTextField getNameField() {
//        return nameField;
//    }
//
//    public JPasswordField getPasswordField() {
//        return passwordField;
//    }
//
//    public JPasswordField getConfirmPasswordField() {
//        return confirmPasswordField;
//    }
//
//    public JButton getSubmitButton() {
//        return submitButton;
//    }
//
//    public boolean isSuccessMessageShown() {
//        return successMessageShown;
//    }
//
//    public boolean isErrorMessageShown() {
//        return errorMessageShown;
//    }
//}
