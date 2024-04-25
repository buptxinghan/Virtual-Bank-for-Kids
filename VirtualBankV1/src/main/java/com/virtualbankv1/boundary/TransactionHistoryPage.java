package com.virtualbankv1.boundary;

import com.virtualbankv1.control.TransactionManager;
import com.virtualbankv1.entity.Account;
import com.virtualbankv1.entity.Transaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static com.virtualbankv1.boundary.Reader.accounts;
import static com.virtualbankv1.boundary.Reader.transactions;

public class TransactionHistoryPage extends JFrame {
    private JPanel datePanel;
    private JPanel dateFieldsPanel; // Panel for date fields
    private JPanel buttonPanel; // Panel for buttons
    private JPanel transactionsPanel;
    private JButton showButton;
    private JButton showAllButton;
    private JButton returnButton;
    private JComboBox<String> yearComboBox;
    private JComboBox<String> monthComboBox;
    private JComboBox<String> dayComboBox;

    TransactionManager transactionManager = new TransactionManager();

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

        // Year ComboBox
        String[] years = {"2020", "2021", "2022", "2023", "2024", "2025"};
        yearComboBox = new JComboBox<>(years);
        yearComboBox.setPreferredSize(new Dimension(55, 30));

        // Month ComboBox
        String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        monthComboBox = new JComboBox<>(months);
        monthComboBox.setPreferredSize(new Dimension(40, 30));

        // Day ComboBox
        String[] days = new String[31];
        for (int i = 1; i <= 31; i++) {
            days[i - 1] = String.format("%02d", i);
        }
        dayComboBox = new JComboBox<>(days);
        dayComboBox.setPreferredSize(new Dimension(40, 30));

        dateFieldsPanel.add(yearComboBox);
        dateFieldsPanel.add(new JLabel("<html><font color= #8595BC style='font-size: 25px;'>Year</font></html>"));
        dateFieldsPanel.add(monthComboBox);
        dateFieldsPanel.add(new JLabel("<html><font color= #8595BC style='font-size: 25px;'>Month</font></html>"));
        dateFieldsPanel.add(dayComboBox);
        dateFieldsPanel.add(new JLabel("<html><font color= #8595BC style='font-size: 25px;'>Day</font></html>"));
        dateFieldsPanel.setBackground(new Color(199, 220, 247));
        datePanel.add(dateFieldsPanel);

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
                String year = (String) yearComboBox.getSelectedItem();
                String month = (String) monthComboBox.getSelectedItem();
                String day = (String) dayComboBox.getSelectedItem();

                List<Transaction> tempTransactions = transactionManager.filterTransactionsByAccount(account);
                showTransactions(filterTransactionsByDate(year, month, day, tempTransactions));
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
                showTransactions(transactionManager.filterTransactionsByAccount(account));
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

        // Transactions panel
        transactionsPanel = new JPanel();
        transactionsPanel.setLayout(new BoxLayout(transactionsPanel, BoxLayout.Y_AXIS));
        transactionsPanel.setBackground(new Color(199, 220, 247));

        JScrollPane scrollPane = new JScrollPane(transactionsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // 总是显示垂直滚动条
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // 不显示水平滚动条
        scrollPane.setBackground(new Color(199, 220, 247));
        scrollPane.setPreferredSize(new Dimension(600, 650));
        add(scrollPane, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void showTransactions(List<Transaction> tempTransactions) {
        transactionsPanel.removeAll(); // 清空之前的记录
        transactionsPanel.add(Box.createRigidArea(new Dimension(0, 15))); // 添加20像素的空白
        for (Transaction transaction : tempTransactions) {
            addTransaction(transaction);
        }
        transactionsPanel.revalidate();
        transactionsPanel.repaint();
    }

    private void addTransaction(Transaction transaction) {
        JPanel transactionPanel = new RoundedPanel(45);
        transactionPanel.setLayout(new BoxLayout(transactionPanel, BoxLayout.Y_AXIS));
        transactionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);  // Set horizontal alignment to center
        transactionPanel.setBackground(new Color(112, 172, 249));

        DecimalFormat df = new DecimalFormat("#,##0.00");
        String formattedAmount = df.format(transaction.getAmount());

        JLabel transactionLabel = new JLabel("<html>" +
                "<span style='color:#fcfc98; font-size: 20px;'>Transaction ID:</span> <font color=#FFFFFF size=6>" + transaction.getTransactionID() + "</font>" +
                "<span style='color:#fcfc98; font-size: 20px;'> | Date:</span> <font color=#FFFFFF size=6>" + transaction.getDate() + "</font>" +
                "<br/><span style='color:#fcfc98; font-size: 20px;'><b>From account:</b></span> <font color=#FFFFFF size=6>" + transaction.getAccountFrom() + "</font>" +
                "<span style='color:#fcfc98; font-size: 20px;'><b> | To account:</b></span> <font color=#FFFFFF size=6>" + transaction.getAccountTo() + "</font>" +
                "<span style='color:#fcfc98; font-size: 20px;'><b> | Amount:</b></span> <font color=#FFFFFF size=6>$" + formattedAmount + "</font>" +
                "<br/><span style='color:#fcfc98; font-size: 20px;'><b>Description:</b></span> <font color=#FFFFFF size=6>" + transaction.getDescription() + "</font>" +
                "</html>");

        transactionLabel.setHorizontalAlignment(SwingConstants.CENTER);  // Set horizontal alignment to center
        transactionLabel.setVerticalAlignment(SwingConstants.CENTER);    // Set vertical alignment to center

        transactionLabel.setMaximumSize(new Dimension(1100, 120));
        transactionLabel.setPreferredSize(new Dimension(1100, 120));
        transactionLabel.setMinimumSize(new Dimension(1100, 120));
        transactionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);  // Set horizontal alignment to center

        transactionPanel.add(transactionLabel);
        transactionsPanel.add(transactionPanel);
        transactionsPanel.add(Box.createRigidArea(new Dimension(0, 15)));
    }


    private List<Transaction> filterTransactionsByDate(String year, String month, String day, List<Transaction> tempTransactions) {
        List<Transaction> filteredTransactions = new ArrayList<>();

        for (Transaction tempTransaction : tempTransactions) {
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