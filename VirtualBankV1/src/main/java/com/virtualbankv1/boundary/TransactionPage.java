package com.virtualbankv1.boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.virtualbankv1.control.AccountManager;
import com.virtualbankv1.control.TransactionManager;
import com.virtualbankv1.entity.Account;
import com.virtualbankv1.entity.Transaction;

import static com.virtualbankv1.boundary.Reader.accounts;
import static com.virtualbankv1.boundary.Reader.transactions;

public class TransactionPage extends JFrame {
    private JComboBox<String> transferToDropdown;
    private JTextField amountField;
    private JTextArea descriptionArea;
    private JButton clearButton;
    private JButton submitButton;
    private TransactionManager transactionManager = new TransactionManager();
    private AccountManager accountManager = new AccountManager();

    public TransactionPage(Account account) {
        // Create the frame
        setTitle("TransferPage");
        setPreferredSize(new Dimension(1200, 900));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Set a solid light blue background
        getContentPane().setBackground(new Color(199, 220, 247)); // Light blue background

        // Title label
        JLabel titleLabel = new JLabel("<html><div style='text-align: center; color: #5D61C3;'><font style='font-size: 35px;'>Transfer details</font></div></html>", SwingConstants.CENTER);
        titleLabel.setHorizontalAlignment(JLabel.LEFT);
        add(titleLabel, BorderLayout.NORTH);

        // Center panel for form elements
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerPanel.setBackground(new Color(199, 220, 247));

        // Transfer to dropdown
        centerPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        // Convert accounts to an array of account IDs
        String[] accountDetails = accounts.stream()
                .map(acc -> "Account ID: " + acc.getAccountID() + " | Account Owner: " + acc.getUsername())
                .toArray(String[]::new);

        // Create JComboBox with accountIDs
        transferToDropdown = new JComboBox<>(accountDetails);

        transferToDropdown.setBorder(BorderFactory.createTitledBorder("<html><font color='#8595BC' style='font-size: 20px;'>Transfer to</font></html>"));
        transferToDropdown.setMaximumSize(new Dimension(1100, 90));
        centerPanel.add(transferToDropdown);

        // Spacer
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Amount field
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        amountField = new JTextField();
        amountField.setBorder(BorderFactory.createTitledBorder("<html><font color='#8595BC' style='font-size: 20px;'>Amount</font></html>"));
        amountField.setMaximumSize(new Dimension(1100, 60)); // Fixed size
        centerPanel.add(amountField);

        // Spacer
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Description area
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        descriptionArea = new JTextArea();
        descriptionArea.setBorder(BorderFactory.createTitledBorder("<html><font color='#8595BC' style='font-size: 20px;'>Description</font></html>"));
        descriptionArea.setMaximumSize(new Dimension(1100, 100)); // Fixed size
        centerPanel.add(descriptionArea);

        add(centerPanel, BorderLayout.CENTER);

        // Buttons panel for horizontal layout
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.setBackground(new Color(199, 220, 247));

        // Clear button
        clearButton = new RoundedButton("<html><font style='font-size: 18px;'>Clear</font></html>");
        clearButton.setBackground(new Color(70, 130, 180));
        clearButton.setForeground(Color.WHITE);
        clearButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        clearButton.setPreferredSize(new Dimension(200, 50));
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear all fields
                transferToDropdown.setSelectedIndex(0);
                amountField.setText("");
                descriptionArea.setText("");
            }
        });
        buttonsPanel.add(clearButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(100, 0)));

        // Submit button
        submitButton = new RoundedButton("<html><font style='font-size: 18px;'>Submit</font></html>");
        submitButton.setBackground(new Color(70, 130, 180));
        submitButton.setForeground(Color.WHITE);
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.setPreferredSize(new Dimension(200, 50));
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Submit action
                String transferTo = (String) transferToDropdown.getSelectedItem();
                double amount = 0.00;
                try {
                    amount = Double.parseDouble(amountField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid amount.");
                    return;
                }
                String description = descriptionArea.getText();

                for (Account tempAccount : accounts) {
                    if (("Account ID: " + tempAccount.getAccountID() + " | Account Owner: " + tempAccount.getUsername()).equals(transferTo)) {
                        if (transactionManager.transfer(account, tempAccount, amount, description)) {
                            JOptionPane.showMessageDialog(null, "Transfer to: " + transferTo + "\nAmount: " + amount + "\nDescription: " + description);
                        } else {
                            JOptionPane.showMessageDialog(null, "Error during transaction. Please check account balance or account status.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
        buttonsPanel.add(submitButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(100, 0)));

        // Return button
        JButton returnButton = ReturnButton.createReturnButton(this, "accountInformationPage", new Dimension(200, 50), account);
        buttonsPanel.add(returnButton);

        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 200)));
        add(buttonsPanel, BorderLayout.SOUTH);

        // Display the frame
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Reader();
                new TransactionPage(accounts.get(0));
            }
        });
    }
}