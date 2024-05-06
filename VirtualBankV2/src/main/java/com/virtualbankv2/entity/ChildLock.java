package com.virtualbankv2.entity;

import javax.swing.*;
import java.awt.*;

public class ChildLock extends JFrame{

    private JPanel mainPanel;
    private JTextField userText = new JTextField(20);
    private JButton checkButton = new JButton("Check");

    public JTextField getUserText() {
        return userText;
    }

    public JButton getCheckButton() {
        return checkButton;
    }

    public ChildLock() {
        this.setSize(600, 450);
        this.mainPanel = new JPanel(new GridBagLayout());
        this.mainPanel.setBackground(new Color(199, 220, 247));
        this.setLocationRelativeTo(null);
        this.setContentPane(this.mainPanel);
        displayCreateLockPanel(this.mainPanel);
        this.setVisible(true);
    }

    private void displayCreateLockPanel(JPanel panel) {

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel lockLabel = new JLabel(new ImageIcon("src/Materials/Lock.png"));
        JLabel mathQuestion = new JLabel("999 + 999 = ?");

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
