package com.virtualbankv1.boundary;

import com.virtualbankv1.control.AccountOverviewPage;
import com.virtualbankv1.entity.Account;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountOverviewUI extends JFrame {

    private JPanel mainPanel;
    private AccountOverviewPage controller;

    // Constructor
    public AccountOverviewUI(AccountOverviewPage controller) {
        this.controller = controller;
        this.mainPanel = new JPanel(new GridLayout(2,2));
        setContentPane(this.mainPanel);
        displayCreateAccountPanel();
    }

    public void setPage(AccountOverviewUI aop) {
        aop.setSize(1200, 900);
        aop.setLocationRelativeTo(null); // Center the window
        aop.setVisible(true);
        aop.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    // Display the panel for creating a new account
    private void displayCreateAccountPanel() {
        JPanel panel = controller.createPanel();

        JLabel titleLabel = new JLabel("Create new account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(titleLabel);

        JButton addButton = new JButton("+");
        addButton.addActionListener(e -> controller.openAccount());

        panel.add(addButton);

        add(panel);
        pack();
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Modify the close behavior
    }

    // Update the page with the given account information
    // Update the page with the given account information
    public void updatePage(Account account, Color color) {
//        if (account == null || color == null) {
//            throw new IllegalArgumentException("Account or color cannot be null");
//        }

        JPanel panel = controller.createPanel();
        panel.setBackground(color);

        //JPanel contentPanel = new JPanel();
        //contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

//        contentPanel.add(createLabel(account.getAccountID(), Font.BOLD, 14));
//        contentPanel.add(createLabel("Account status: " + account.getStatus()));
//        contentPanel.add(createLabel("Account type: " + account.getAccountType()));
        panel.add(createLabel(account.getAccountID(), Font.BOLD, 14));
        panel.add(createLabel("Account status: " + account.getStatus()));
        panel.add(createLabel("Account type: " + account.getAccountType()));

        JButton selectButton = new JButton("Select");
        selectButton.addActionListener(e -> controller.showAccountInfo(account));
        selectButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center alignment
        //contentPanel.add(selectButton);
        panel.add(selectButton);
        //panel.add(contentPanel, BorderLayout.CENTER); // Add content to CENTER

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.weighty = 1;

        SwingUtilities.invokeLater(() -> {
            mainPanel.add(panel, gbc);
            pack();
            revalidate();
        });
    }

    private JLabel createLabel(String text, int fontStyle, int fontSize) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", fontStyle, fontSize));
        label.setAlignmentX(Component.CENTER_ALIGNMENT); // Center alignment
        return label;
    }

    private JLabel createLabel(String text) {
        return createLabel(text, Font.PLAIN, 12);
    }


}