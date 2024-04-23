package com.virtualbankv1.boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Random;

import com.virtualbankv1.entity.User;
import com.virtualbankv1.boundary.Writer;

public class SignUpPage {

    private JFrame frame;
    private JTextField nameField;
    private JPasswordField passwordField, confirmPasswordField;
    private JButton submitButton;

    public SignUpPage() {
        // Create and set up the window
        frame = new JFrame("Sign Up");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(350, 200));
        frame.setLayout(new BorderLayout(10, 10));

        // Create and populate the panel
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        panel.add(new JLabel("Username *"));
        nameField = new JTextField();
        panel.add(nameField);

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
                String userName = nameField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                if (password.equals(confirmPassword)) {
                    User tempUser = new User(userName,password);
                    Writer writer = new Writer();
                    writer.writeSingleUser(tempUser);
                    JOptionPane.showMessageDialog(frame, "Account created successfully!" );
                } else {
                    JOptionPane.showMessageDialog(frame, "Error: Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Set the size of the window and make it visible
        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }

//    private void writeUserToCsv(User tempUser) {
//        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/Data/Users.csv", true)))) {
//            users.add(tempUser);
//            out.println(tempUser.getUsername() + "," + tempUser.getPassword() );
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) {
        // Schedule a job for the event dispatch thread: creating and showing this application's GUI
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SignUpPage();
            }
        });
    }
}
