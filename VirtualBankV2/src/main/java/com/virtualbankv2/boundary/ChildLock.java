package com.virtualbankv2.boundary;

import com.virtualbankv2.entity.RoundedButton;
import com.virtualbankv2.entity.RoundedPasswordField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents a child lock window for a virtual bank application.
 * Provides a password field and a "Check" button for authentication.
 *
 * @author Botong Wu
 * @version 2.0
 * @since 2024/05/05
 */
public class ChildLock extends JFrame {

    private JPanel mainPanel;
    private JPasswordField userText = new RoundedPasswordField(20);
    private RoundedButton checkButton = new RoundedButton("Check");

    /**
     * Gets the user password input field.
     *
     * @return The JPasswordField for user input.
     */
    public JPasswordField getUserText() {
        return userText;
    }

    /**
     * Gets the "Check" button.
     *
     * @return The JButton for checking the password.
     */
    public JButton getCheckButton() {
        return checkButton;
    }

    /**
     * Creates a new ChildLock window.
     */
    public ChildLock() {
        this.setSize(600, 450);
        this.setResizable(false);
        this.mainPanel = new JPanel(new GridBagLayout());
        this.mainPanel.setBackground(new Color(199, 220, 247));
        this.setLocationRelativeTo(null);
        this.setContentPane(this.mainPanel);
        displayCreateLockPanel(this.mainPanel);
        this.setVisible(true);
        userText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform the same action as clicking the "Check" button
                checkButton.doClick();
            }
        });
    }
    /**
     * Create the Child Lock
     *
     * @param panel The panel for displaying a child lock.
     */
    private void displayCreateLockPanel(JPanel panel) {
        checkButton.setBackground(new Color(79, 143, 230));
        checkButton.setForeground(Color.WHITE);
        checkButton.setPreferredSize(new Dimension(100, 40));
        checkButton.setMaximumSize(new Dimension(100, 45));
        checkButton.setFont(new Font("Arial", Font.BOLD, 18));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        userText.setBorder(BorderFactory.createLineBorder(Color.GRAY, 6));
        JLabel lockLabel = new JLabel(new ImageIcon("src/Materials/Lock.png"));
        JLabel mathQuestion = new JLabel("Please enter the administrator account password");
        mathQuestion.setFont(new Font("Arial", Font.BOLD, 18));

        panel.add(Box.createRigidArea(new Dimension(0, 30)), gbc);
        panel.add(lockLabel, gbc);
        panel.add(Box.createRigidArea(new Dimension(0, 20)), gbc);
        panel.add(mathQuestion, gbc);
        panel.add(Box.createRigidArea(new Dimension(0, 20)), gbc);
        panel.add(userText, gbc);
        panel.add(Box.createRigidArea(new Dimension(0, 20)), gbc);
        panel.add(checkButton, gbc);

        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.weighty = 1;
        panel.add(new JLabel(), gbc);
    }
}
