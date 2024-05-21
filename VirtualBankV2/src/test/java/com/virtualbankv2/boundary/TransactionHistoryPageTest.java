package com.virtualbankv2.boundary;

import com.virtualbankv2.control.TransactionManager;
import com.virtualbankv2.entity.Account;
import com.virtualbankv2.entity.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static com.virtualbankv2.boundary.Reader.transactions;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransactionHistoryPageTest {
    private TransactionHistoryPage transactionHistoryPage;

    @BeforeEach
    public void setUp() {
        new Reader();
        Account account = new Account("testUser", "testAccount", "Test Account", "testpassword", 1000, "testUser");
        transactions = new ArrayList<>();
        transactions.add(new Transaction("T001", "123456789", "987654321", 100.00, "2024/05/27", "Des1"));
        transactions.add(new Transaction("T002", "123456789", "987654321", 100.00, "2024/05/27", "Des2"));
        transactions.add(new Transaction("T003", "123456789", "987654321", 100.00, "2024/05/27", "Des3"));

        TransactionManager transactionManager = new TransactionManager();

        transactionHistoryPage = new TransactionHistoryPage(account);
        transactionHistoryPage.setTransactionManager(transactionManager);
    }

    @Test
    public void testShowAllTransactions() {
        waitForTransactionsToLoad("All transactions are visible.");
    }

    @Test
    public void testFilterTransactionsByDate() {
        JComboBox<String> yearComboBox = transactionHistoryPage.getYearComboBox();
        JComboBox<String> monthComboBox = transactionHistoryPage.getMonthComboBox();
        JComboBox<String> dayComboBox = transactionHistoryPage.getDayComboBox();

        yearComboBox.setSelectedItem("2024");
        monthComboBox.setSelectedItem("05");
        dayComboBox.setSelectedItem("19");

        transactionHistoryPage.triggerShowButton();

        waitForTransactionsToLoad("All transactions are visible after filtering by date.");
    }

    private void waitForTransactionsToLoad(String message) {
        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (waitForTransaction("T001") && waitForTransaction("T002") && waitForTransaction("T003")) {
                    assertTrue(true, message);
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();

        long startTime = System.currentTimeMillis();
        while (timer.isRunning() && System.currentTimeMillis() - startTime < 2000) {
        }

        if (timer.isRunning()) {
            timer.stop();
            assertTrue(false, "Timeout: " + message);
        }
    }

    private boolean waitForTransaction(String transactionID) {
        Component[] components = transactionHistoryPage.getContentPane().getComponents();
        for (Component component : components) {
            if (component instanceof JPanel) {
                JPanel transactionsPanel = (JPanel) component;
                Component[] transactionPanels = transactionsPanel.getComponents();
                for (Component transactionPanel : transactionPanels) {
                    if (transactionPanel instanceof JPanel) {
                        JPanel panel = (JPanel) transactionPanel;
                        Component[] labels = panel.getComponents();
                        for (Component label : labels) {
                            if (label instanceof JLabel) {
                                JLabel transactionLabel = (JLabel) label;
                                if (transactionLabel.getText().contains(transactionID)) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}