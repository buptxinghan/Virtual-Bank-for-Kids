package com.virtualbankv1.boundary;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.virtualbankv1.control.VirtualBankApplication;
import com.virtualbankv1.entity.Account;
import com.virtualbankv1.entity.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TransactionPageTest {

        private TransactionPage yourMainClass; // 你的主类
        private Account account; // 你的账户类
        private JComboBox<String> transferToDropdown; // 转账下拉框
        private JTextField amountField; // 金额输入框
        private JTextField descriptionArea; // 描述输入框
        private List<Account> accounts; // 账户列表
        private List<Transaction> transactions; // 交易列表

        @BeforeEach
        void setUp() {
            account = new Account("testAccount", 100.00); // 初始化账户
            yourMainClass = new TransactionPage(account); // 初始化你的主类
            transferToDropdown = new JComboBox<String>(new String[]{"testAccount2", "testAccount3"}); // 初始化转账下拉框
            amountField = new JTextField("50.00"); // 初始化金额输入框
            descriptionArea = new JTextField("Test transfer"); // 初始化描述输入框

            accounts = new ArrayList<>(); // 初始化账户列表
            accounts.add(account);

            transactions = new ArrayList<>(); // 初始化交易列表
        }

        @Test
        void testActionPerformedSufficientBalance() {
            // Set up your mock or actual data as needed

            // Simulate user action
            transferToDropdown.setSelectedItem("testAccount2");
            amountField.setText("50.00");
            descriptionArea.setText("Test transfer");

            // Trigger action
            yourMainClass.actionPerformed(null); // Assuming you trigger the action directly

            // Assertions
            assertEquals(50.00, account.getBalance()); // Check the account balance after transfer
            assertEquals(150.00, accounts.get(1).getBalance()); // Check the balance of the receiving account
            assertEquals(1, transactions.size()); // Check the number of transactions
        }

        @Test
        void testActionPerformedInsufficientBalance() {
            // Set up your mock or actual data as needed

            // Simulate user action
            transferToDropdown.setSelectedItem("testAccount2");
            amountField.setText("150.00");
            descriptionArea.setText("Test transfer");

            // Trigger action
            yourMainClass.actionPerformed(null); // Assuming you trigger the action directly

            // Assertions
            assertEquals(100.00, account.getBalance()); // Check the account balance after insufficient transfer
            assertEquals(100.00, accounts.get(1).getBalance()); // Check the balance of the receiving account
            assertEquals(0, transactions.size()); // Check the number of transactions
        }
    }

}