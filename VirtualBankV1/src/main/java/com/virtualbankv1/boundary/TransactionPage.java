package com.virtualbankv1.boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;


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
        String[] accountIDs = accounts.stream()
                .map(Account::getAccountID)  // Assuming getAccountID returns String
                .toArray(String[]::new);

        // Create JComboBox with accountIDs
        transferToDropdown = new JComboBox<>(accountIDs);

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
                    return; // Exit the method if the amount is invalid
                }
                String description = descriptionArea.getText();

                if (accountManager.withdraw(account, amount)) {
                    for (Account tempAccount : accounts) {
                        if (tempAccount.getAccountID().equals(transferTo)) {
                            // 检查账户状态
                            if (accountManager.isFrozen(tempAccount) || accountManager.isDeleted(tempAccount)) {
                                JOptionPane.showMessageDialog(null, "账户状态异常，无法进行交易", "错误", JOptionPane.ERROR_MESSAGE);
                                return; // 账户状态异常，中断操作
                            }
                            accountManager.transferIn(tempAccount, amount);
                        }
                    }

                    // Submit logic, saving data to a csv
                    JOptionPane.showMessageDialog(null, "Transfer to: " + transferTo + "\nAmount: " + amount + "\nDescription: " + description);
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    Transaction tempTransaction = new Transaction("00"+transactionManager.getTransactionID(), account.getAccountID(), transferTo, amount, dateFormatter.format(LocalDate.now()), description);
                    transactions.add(tempTransaction);

                    Writer writer = new Writer();
                    writer.writeSingleTransaction(tempTransaction);
                } else {
                    // 余额不足，弹出提示窗口
                    JOptionPane.showMessageDialog(null, "账户余额不足，请重新输入金额。", "错误", JOptionPane.ERROR_MESSAGE);
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