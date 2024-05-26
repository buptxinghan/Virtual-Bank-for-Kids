package com.virtualbankv2.control;

import com.virtualbankv2.boundary.Reader;
import com.virtualbankv2.entity.Account;
import com.virtualbankv2.entity.Transaction;
import com.virtualbankv2.boundary.Writer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static com.virtualbankv2.boundary.Reader.transactions;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the functionality of the TransactionManager class.
 * It includes tests for transferring funds between accounts, generating transaction IDs,
 * formatting transaction IDs, and filtering transactions by account.
 *
 * @version 1.0
 * @since 2024-05-10
 */
public class TransactionManagerTest {

    private TransactionManager transactionManager;
    private Account sourceAccount;
    private Account targetAccount;

    @BeforeEach
    void setUp() {
        new Reader();
        new VirtualBankApplication();
        transactionManager = new TransactionManager();
        transactions = new ArrayList<>();
        sourceAccount = new Account("001", "Saving Account", "user1", "password", 500.0, "Active");
        targetAccount = new Account("002", "Saving Account", "user2", "password", 200.0, "Active");
    }

    /**
     * Tests the transfer method when the source account has insufficient balance.
     */
    @Test
    void testTransferInsufficientBalance() {
        boolean result = transactionManager.transfer(sourceAccount, targetAccount, 600.0, "Transfer to target");
        assertFalse(result);
        assertEquals(500.0, sourceAccount.getBalance());
        assertEquals(200.0, targetAccount.getBalance());
        assertEquals(0, transactions.size());
    }

    /**
     * Tests the transfer method when the target account is frozen.
     */
    @Test
    void testTransferToFrozenAccount() {
        targetAccount.setStatus("Frozen");
        boolean result = transactionManager.transfer(sourceAccount, targetAccount, 100.0, "Transfer to target");
        assertFalse(result);
        assertEquals(500.0, sourceAccount.getBalance());
        assertEquals(200.0, targetAccount.getBalance());
        assertEquals(0, transactions.size());
    }

    /**
     * Tests the transfer method when the target account is deleted.
     */
    @Test
    void testTransferToDeletedAccount() {
        targetAccount.setStatus("Deleted");
        boolean result = transactionManager.transfer(sourceAccount, targetAccount, 100.0, "Transfer to target");
        assertFalse(result);
        assertEquals(500.0, sourceAccount.getBalance());
        assertEquals(200.0, targetAccount.getBalance());
        assertEquals(0, transactions.size());
    }

    /**
     * Tests the transfer method when the transfer is successful.
     */
    @Test
    void testSuccessfulTransfer() {
        boolean result = transactionManager.transfer(sourceAccount, targetAccount, 100.0, "Transfer to target");
        assertTrue(result);
        assertEquals(400.0, sourceAccount.getBalance());
        assertEquals(300.0, targetAccount.getBalance());
        assertEquals(1, transactions.size());
        assertEquals("001", transactions.get(0).getAccountFrom());
        assertEquals("002", transactions.get(0).getAccountTo());
        assertEquals(100.0, transactions.get(0).getAmount());
    }

    /**
     * Tests the getTransactionID method for generating unique transaction IDs.
     */
    @Test
    void testGetTransactionID() throws Exception {
        Method getTransactionIDMethod = TransactionManager.class.getDeclaredMethod("getTransactionID");
        getTransactionIDMethod.setAccessible(true);
        int transactionID = (int) getTransactionIDMethod.invoke(transactionManager);
        assertEquals(1, transactionID);

        transactions.add(new Transaction("001", "001", "002", 100.0, "2024/01/01", "Description"));
        transactionID = (int) getTransactionIDMethod.invoke(transactionManager);
        assertEquals(2, transactionID);
    }

    /**
     * Tests the getFormatTransactionID method for formatting transaction IDs.
     */
    @Test
    void testGetFormatTransactionID() throws Exception {
        Method getTransactionIDMethod = TransactionManager.class.getDeclaredMethod("getTransactionID");
        getTransactionIDMethod.setAccessible(true);
        String formattedID = transactionManager.getFormatTransactionID();
        assertEquals("0001", formattedID);

        transactions.add(new Transaction("001", "001", "002", 100.0, "2024/01/01", "Description"));
        int transactionID = (int) getTransactionIDMethod.invoke(transactionManager);
        assertEquals("0002", transactionManager.getFormatTransactionID());
    }

    /**
     * Tests the filterTransactionsByAccount method for filtering transactions by a given account.
     */
    @Test
    void testFilterTransactionsByAccount() {
        transactions.add(new Transaction("001", "001", "002", 100.0, "2024/01/01", "Description"));
        transactions.add(new Transaction("002", "002", "001", 200.0, "2024/01/02", "Description"));

        List<Transaction> filteredTransactions = transactionManager.filterTransactionsByAccount(sourceAccount);
        assertEquals(2, filteredTransactions.size());

        filteredTransactions = transactionManager.filterTransactionsByAccount(targetAccount);
        assertEquals(2, filteredTransactions.size());
    }

    /**
     * Tests that the source account balance is correctly updated after a successful transfer.
     */
    @Test
    void testSourceAccountBalanceAfterTransfer() {
        double initialBalance = sourceAccount.getBalance();
        double transferAmount = 100.0;
        boolean result = transactionManager.transfer(sourceAccount, targetAccount, transferAmount, "Transfer to target");
        assertTrue(result);
        assertEquals(initialBalance - transferAmount, sourceAccount.getBalance());
    }

    /**
     * Tests that the target account balance is correctly updated after a successful transfer.
     */
    @Test
    void testTargetAccountBalanceAfterTransfer() {
        double initialBalance = targetAccount.getBalance();
        double transferAmount = 100.0;
        boolean result = transactionManager.transfer(sourceAccount, targetAccount, transferAmount, "Transfer to target");
        assertTrue(result);
        assertEquals(initialBalance + transferAmount, targetAccount.getBalance());
    }
}
