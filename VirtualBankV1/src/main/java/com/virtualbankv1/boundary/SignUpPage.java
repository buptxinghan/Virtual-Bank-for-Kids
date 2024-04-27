package com.virtualbankv1.boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import com.virtualbankv1.entity.User;
import com.virtualbankv1.boundary.Writer;

import static com.virtualbankv1.boundary.Reader.users;

public class SignUpPage {

    private JFrame frame;
    private JTextField nameField;
    private JPasswordField passwordField, confirmPasswordField;
    private JButton submitButton;
    private Color lightBlue = new Color(199, 220, 247); // Light blue background
    private Color deepBlue = new Color(93, 97, 195); // Deep blue for buttons
    private Color white = Color.WHITE; // White text on buttons
    private Font fieldFont = new Font("Arial", Font.BOLD, 25); // Font for fields and buttons

    public SignUpPage() {
        // Create and set up the window
        frame = new JFrame("Sign Up");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1200, 900));
        frame.setLayout(new BorderLayout());

        // Main panel with box layout for vertical stacking
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(lightBlue);
        frame.add(mainPanel, BorderLayout.CENTER);

        // Add vertical strut for spacing
        mainPanel.add(Box.createVerticalStrut(100));

        // Username panel
        JPanel usernamePanel = createFieldPanel("Username *", true);
        mainPanel.add(usernamePanel);

        // Password panel
        JPanel passwordPanel = createFieldPanel("Password *", false);
        mainPanel.add(passwordPanel);

        // Confirm Password panel
        JPanel confirmPasswordPanel = createFieldPanel("Confirm password *", false);
        mainPanel.add(confirmPasswordPanel);

        // Submit button panel
        JPanel submitButtonPanel = new JPanel();
        submitButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        submitButtonPanel.setBackground(lightBlue);
        submitButton = new JButton("Submit");
        submitButton.setFont(fieldFont);
        submitButton.setBackground(deepBlue);
        submitButton.setForeground(white);
        submitButtonPanel.add(submitButton);
        mainPanel.add(submitButtonPanel);

        // Add action listener to the button
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performSignUp();
            }
        });

        // Set the size of the window and make it visible
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }

    private JPanel createFieldPanel(String label, boolean isTextField) {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        fieldPanel.setBackground(lightBlue);

        JLabel fieldLabel = new JLabel(label);
        fieldLabel.setFont(fieldFont);

        JTextField textField;
        if (isTextField) {
            textField = new JTextField(20);
        } else {
            textField = new JPasswordField(20);
        }
        textField.setFont(fieldFont);

        fieldPanel.add(fieldLabel);
        fieldPanel.add(textField);

        return fieldPanel;
    }

    private void performSignUp() {
        String userName = nameField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (password.equals(confirmPassword)) {
            User tempUser = new User(userName, password);
            Writer writer = new Writer();
            writer.writeSingleUser(tempUser);
            users.add(tempUser);
            JOptionPane.showMessageDialog(frame, "Account created successfully!");
        } else {
            JOptionPane.showMessageDialog(frame, "Error: Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Schedule a job for the event dispatch thread: creating and showing this application's GUI
        SwingUtilities.invokeLater(new Runnable() {
            Reader reader = new Reader();
            public void run() {
                new SignUpPage();
            }
        });
    }
}
