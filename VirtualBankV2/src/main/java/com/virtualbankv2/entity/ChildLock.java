package com.virtualbankv2.entity;

import com.virtualbankv2.boundary.RoundedTextField;

import javax.swing.*;
import java.awt.*;

public class ChildLock extends JFrame{

    private JPanel mainPanel;
    private JTextField userText = new RoundedTextField(20);
    private RoundedButton checkButton = new RoundedButton("Check");

    public JTextField getUserText() {
        return userText;
    }

    public JButton getCheckButton() {
        return checkButton;
    }

    public ChildLock() {
        this.setSize(600, 450);
        this.setResizable(false);
        this.mainPanel = new JPanel(new GridBagLayout());
        this.mainPanel.setBackground(new Color(199, 220, 247));
        this.setLocationRelativeTo(null);
        this.setContentPane(this.mainPanel);
        displayCreateLockPanel(this.mainPanel);
        this.setVisible(true);
    }

    private void displayCreateLockPanel(JPanel panel) {

        checkButton.setBackground(new Color(79,143,230));
        checkButton.setForeground(Color.WHITE);
        checkButton.setPreferredSize(new Dimension(100,50));
        checkButton.setMaximumSize(new Dimension(100,50));
        checkButton.setFont(new Font("Arial", Font.BOLD, 18));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        userText.setBorder(BorderFactory.createLineBorder(Color.GRAY, 6));  // 添加黑色的线条边框，宽度为2
        JLabel lockLabel = new JLabel(new ImageIcon("src/Materials/Lock.png"));
        JLabel mathQuestion = new JLabel("Please enter the administrator account password");
        mathQuestion.setFont(new Font("Arial", Font.BOLD, 18));

        panel.add(Box.createRigidArea(new Dimension(0, 30)),gbc);
        panel.add(lockLabel, gbc);
        panel.add(Box.createRigidArea(new Dimension(0, 20)),gbc);
        panel.add(mathQuestion, gbc);
        panel.add(Box.createRigidArea(new Dimension(0, 20)),gbc);
        panel.add(userText, gbc);
        panel.add(Box.createRigidArea(new Dimension(0, 20)),gbc);
        panel.add(checkButton, gbc);

        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.weighty = 1;
        panel.add(new JLabel(), gbc);
    }

}
