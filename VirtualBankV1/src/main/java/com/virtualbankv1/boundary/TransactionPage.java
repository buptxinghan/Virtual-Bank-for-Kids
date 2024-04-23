package com.virtualbankv1.boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public TransactionPage(Account account) {
        initializeFrame();
        addComponents(account);
    }

    private void initializeFrame() {
        setTitle("TransferPage");
        setPreferredSize(new Dimension(1200, 900));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(199, 220, 247));
    }

    private void addComponents(Account account) {
        addTitleLabel();
        addCenterPanel();
        addButtonsPanel(account);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addTitleLabel() {
        JLabel titleLabel = new JLabel("<html><div style='text-align: center; color: #5D61C3;'><font style='font-size: 35px;'>Transfer details</font></div></html>", SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);
    }

    private void addCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerPanel.setBackground(new Color(199, 220, 247));

        addTransferToDropdown(centerPanel);
        addAmountField(centerPanel);
        addDescriptionArea(centerPanel);

        add(centerPanel, BorderLayout.CENTER);
    }

    private void addTransferToDropdown(JPanel panel) {
        String[] accountIDs = accounts.stream()
                .map(Account::getAccountID)
                .toArray(String[]::new);

        transferToDropdown = new JComboBox<>(accountIDs);
        transferToDropdown.setBorder(BorderFactory.createTitledBorder("<html><font color='#8595BC' style='font-size: 20px;'>Transfer to</font></html>"));
        transferToDropdown.setMaximumSize(new Dimension(1100, 90));
        panel.add(transferToDropdown);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    private void addAmountField(JPanel panel) {
        amountField = new JTextField();
        amountField.setBorder(BorderFactory.createTitledBorder("<html><font color='#8595BC' style='font-size: 20px;'>Amount</font></html>"));
        amountField.setMaximumSize(new Dimension(1100, 60));
        panel.add(amountField);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    private void addDescriptionArea(JPanel panel) {
        descriptionArea = new JTextArea();
        descriptionArea.setBorder(BorderFactory.createTitledBorder("<html><font color='#8595BC' style='font-size: 20px;'>Description</font></html>"));
        descriptionArea.setMaximumSize(new Dimension(1100, 100));
        panel.add(descriptionArea);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
    }

    private void addButtonsPanel(Account account) {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.setBackground(new Color(199, 220, 247));

        addButton(clearButton, "Clear", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        }, buttonsPanel);

        addButton(submitButton, "Submit", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit(account);
            }
        }, buttonsPanel);

        JButton returnButton = ReturnButton.createReturnButton(this, "accountInformationPage");
        buttonsPanel.add(returnButton);

        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 200)));
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void addButton(JButton button, String text, ActionListener actionListener, JPanel panel) {
        button = new RoundedButton("<html><font style='font-size: 18px;'>" + text + "</font></html>");
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(200, 50));
        button.addActionListener(actionListener);
        panel.add(button);
        panel.add(Box.createRigidArea(new Dimension(100, 0)));
    }

    private void clearFields() {
        transferToDropdown.setSelectedIndex(0);
        amountField.setText("");
        descriptionArea.setText("");
    }

    private void handleSubmit(Account account) {
        String transferTo = (String) transferToDropdown.getSelectedItem();
        double amount = 0.00;
        try {
            amount = Double.parseDouble(amountField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a valid amount.");
            return;
        }
        String description = descriptionArea.getText();

        int num = transactions.size() + 1;
        String transactionID = "00" + String.valueOf(num);

        JOptionPane.showMessageDialog(null, "Transfer to: " + transferTo + "\nAmount: " + amount + "\nDescription: " + description);

        Transaction tempTransaction = new Transaction(transactionID, account.getAccountID(), transferTo, amount);
        transactions.add(tempTransaction);
        new Writer().writeSingleTransaction(tempTransaction);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Reader();
            new TransactionPage(accounts.get(0));
        });
    }
}
