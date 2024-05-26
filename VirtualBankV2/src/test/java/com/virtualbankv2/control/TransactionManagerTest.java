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
        sourceAccount = new Account("001", "Saving", "user1", "password", 500.0, "Active");
        targetAccount = new Account("002", "Saving", "user2", "password", 200.0, "Active");
    }

    @Test
    void testTransferInsufficientBalance() {
        boolean result = transactionManager.transfer(sourceAccount, targetAccount, 600.0, "Transfer to target");
        assertFalse(result);
        assertEquals(500.0, sourceAccount.getBalance());
        assertEquals(200.0, targetAccount.getBalance());
        assertEquals(0, transactions.size());
    }

    @Test
    void testTransferToFrozenAccount() {
        targetAccount.setStatus("Frozen");
        boolean result = transactionManager.transfer(sourceAccount, targetAccount, 100.0, "Transfer to target");
        assertFalse(result);
        assertEquals(500.0, sourceAccount.getBalance());
        assertEquals(200.0, targetAccount.getBalance());
        assertEquals(0, transactions.size());
    }

    @Test
    void testTransferToDeletedAccount() {
        targetAccount.setStatus("Deleted");
        boolean result = transactionManager.transfer(sourceAccount, targetAccount, 100.0, "Transfer to target");
        assertFalse(result);
        assertEquals(500.0, sourceAccount.getBalance());
        assertEquals(200.0, targetAccount.getBalance());
        assertEquals(0, transactions.size());
    }

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

    @Test
    void testFilterTransactionsByAccount() {
        transactions.add(new Transaction("001", "001", "002", 100.0, "2024/01/01", "Description"));
        transactions.add(new Transaction("002", "002", "001", 200.0, "2024/01/02", "Description"));

        List<Transaction> filteredTransactions = transactionManager.filterTransactionsByAccount(sourceAccount);
        assertEquals(2, filteredTransactions.size());

        filteredTransactions = transactionManager.filterTransactionsByAccount(targetAccount);
        assertEquals(2, filteredTransactions.size());
    }
}
