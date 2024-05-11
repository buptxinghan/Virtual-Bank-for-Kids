package com.virtualbankv2.boundary;

import javax.swing.*;
import java.awt.*;

import com.virtualbankv2.control.TransactionManager;
import com.virtualbankv2.entity.Account;
import com.virtualbankv2.entity.ReturnButton;
import com.virtualbankv2.entity.RoundedButton;

import static com.virtualbankv2.boundary.Reader.accounts;

/**
 * TransactionPage class represents the GUI for performing transactions.
 * Allows users to transfer money between accounts.
 */
public class TransactionPage extends JFrame {
    private JComboBox<String> transferToDropdown;
    private JTextField amountField;
    private JTextArea descriptionArea;
    private JButton clearButton;
    private JButton submitButton;
    private TransactionManager transactionManager = new TransactionManager();

    /**
     * Constructs a new TransactionPage with the specified account.
     *
     * @param account The account used for the transaction.
     */
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
        titleLabel.setBorder(BorderFactory.createEmptyBorder(60, 40, 0, 0)); // Top and bottom padding
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
        clearButton.addActionListener(e -> clearFields());
        buttonsPanel.add(clearButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(100, 0)));

        // Submit button
        submitButton = new RoundedButton("<html><font style='font-size: 18px;'>Submit</font></html>");
        submitButton.setBackground(new Color(70, 130, 180));
        submitButton.setForeground(Color.WHITE);
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.setPreferredSize(new Dimension(200, 50));
        submitButton.addActionListener(e -> submitTransaction(account));
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

    /**
     * Clears all input fields.
     */
    private void clearFields() {
        transferToDropdown.setSelectedIndex(0);
        amountField.setText("");
        descriptionArea.setText("");
    }

    /**
     * Submits a transaction based on the user input.
     *
     * @param account The account from which the transaction is made.
     */
    private void submitTransaction(Account account) {
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
}