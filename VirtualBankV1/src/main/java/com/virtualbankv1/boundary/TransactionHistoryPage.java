package com.virtualbankv1.boundary;

import com.virtualbankv1.entity.Account;
import com.virtualbankv1.entity.Transaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static com.virtualbankv1.boundary.Reader.accounts;
import static com.virtualbankv1.boundary.Reader.transactions;

public class TransactionHistoryPage extends JFrame {
    private JPanel datePanel;
    private JPanel dateFieldsPanel; // Panel for date fields
    private JPanel buttonPanel; // Panel for buttons
    private JPanel transactionsPanel;
    private JTextField yearField;
    private JTextField monthField;
    private JTextField dayField;
    private JButton showButton;
    private JButton showAllButton;
    private JButton returnButton;

    public TransactionHistoryPage(Account account) {
        setTitle("Transaction History");
        setSize(1200, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Set a solid light blue background
        getContentPane().setBackground(new Color(199, 220, 247)); // Light blue background

        // Title label
        JLabel titleLabel = new JLabel("<html><div style='text-align: center; color: #5D61C3;'><font style='font-size: 45px;'>History</font></div></html>", SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Date panel
        datePanel = new JPanel();
        datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.Y_AXIS));

        // Date fields panel
        dateFieldsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        yearField = new JTextField(4);
        yearField.setPreferredSize(new Dimension(60, 30));
        monthField = new JTextField(2);
        monthField.setPreferredSize(new Dimension(40, 30));
        dayField = new JTextField(2);
        dayField.setPreferredSize(new Dimension(40, 30));
        dateFieldsPanel.add(yearField);
        dateFieldsPanel.add(new JLabel("<html><font color= #8595BC style='font-size: 25px;'>Year</font></html>"));
        dateFieldsPanel.add(monthField);
        dateFieldsPanel.add(new JLabel("<html><font color= #8595BC style='font-size: 25px;'>Month</font></html>"));
        dateFieldsPanel.add(dayField);
        dateFieldsPanel.add(new JLabel("<html><font color= #8595BC style='font-size: 25px;'>Day</font></html>"));
        dateFieldsPanel.setBackground(new Color(199, 220, 247));
        datePanel.add(dateFieldsPanel);

        // Transactions panel
        transactionsPanel = new JPanel();
        transactionsPanel.setLayout(new BoxLayout(transactionsPanel, BoxLayout.Y_AXIS));
        transactionsPanel.setBackground(new Color(199, 220, 247));
        transactionsPanel.setPreferredSize(new Dimension(600, 650));

        JScrollPane scrollPane = new JScrollPane(transactionsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // 总是显示垂直滚动条
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // 不显示水平滚动条
        scrollPane.setBackground(new Color(199, 220, 247));
        add(scrollPane, BorderLayout.SOUTH);
        showTransactions(transactions);

        // Button panel
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        showButton = new RoundedButton("<html><font style='font-size: 18px;'>Show</font></html>");
        showButton.setBackground(new Color(70, 130, 180));
        showButton.setForeground(Color.WHITE);
        showButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        showButton.setPreferredSize(new Dimension(250, 50));
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String year = yearField.getText();
                String month = monthField.getText();
                String day = dayField.getText();

                List<Transaction> tempTransactions = filterTransactionsByDate(year, month, day);
                showTransactions(tempTransactions);
            }
        });
        showAllButton = new RoundedButton("<html><font style='font-size: 18px;'>Show All</font></html>");
        showAllButton.setBackground(new Color(70, 130, 180));
        showAllButton.setForeground(Color.WHITE);
        showAllButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        showAllButton.setPreferredSize(new Dimension(250, 50));
        showAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTransactions(transactions);
            }
        });
        returnButton = ReturnButton.createReturnButton(this, "accountInformationPage", new Dimension(250, 50), account);
        buttonPanel.add(showButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(30, 0)));
        buttonPanel.add(showAllButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(30, 0)));
        buttonPanel.add(returnButton);
        buttonPanel.setBackground(new Color(199, 220, 247));
        datePanel.add(buttonPanel);

        add(datePanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void showTransactions(List<Transaction> tempTransactions) {
        transactionsPanel.removeAll(); // 清空之前的记录
        for (Transaction transaction : tempTransactions) {
            addTransaction(transaction);
        }
        transactionsPanel.revalidate();
        transactionsPanel.repaint();
    }

    private void addTransaction(Transaction transaction) {
        JPanel transactionPanel = new JPanel();
        transactionPanel.setLayout(new GridLayout(1, 1));

        // Create a JLabel to display transaction details
        JLabel transactionLabel = new JLabel("<html><font color=#333333><b>Transaction ID:</b> " + transaction.getTransactionID() +
                "<br><b>From:</b> " + transaction.getAccountFrom() +
                "<br><b>To:</b> " + transaction.getAccountTo() +
                "<br><b>Amount:</b> $" + transaction.getAmount() +
                "<br><b>Date:</b> " + transaction.getDate() +
                "<br><b>Description:</b> " + transaction.getDescription() + "</font></html>");

        transactionLabel.setMaximumSize(new Dimension(600, 100));
        transactionLabel.setPreferredSize(new Dimension(600, 100));
        transactionLabel.setMinimumSize(new Dimension(600, 100));
        transactionPanel.add(transactionLabel);
        transactionsPanel.add(transactionPanel);
        transactionsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    private List<Transaction> filterTransactionsByDate(String year, String month, String day) {
        List<Transaction> filteredTransactions = new ArrayList<>();

        for (Transaction tempTransaction : transactions) {
            String[] dateParts = tempTransaction.getDate().split("/"); // Split by '/'

            if (dateParts.length == 3) {
                String transactionYear = dateParts[0];
                String transactionMonth = dateParts[1];
                String transactionDay = dateParts[2];

                if (year.isEmpty() || year.equals(transactionYear)) {
                    if (month.isEmpty() || month.equals(transactionMonth)) {
                        if (day.isEmpty() || day.equals(transactionDay)) {
                            filteredTransactions.add(tempTransaction);
                        }
                    }
                }
            }
        }

        return filteredTransactions;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Reader();
                new TransactionHistoryPage(accounts.get(0));
            }
        });
    }
}
