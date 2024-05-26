package com.virtualbankv2.control;

import com.virtualbankv2.entity.Account;
import com.virtualbankv2.entity.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the functionality of the AccountManager class.
 * It includes tests for displaying account information, retrieving accounts by ID, validating passwords, and checking account status (frozen or deleted).
 *
 * @version 1.0
 * @since 2024-05-08
 * @author Ji Zheng
 */
class AccountManagerTest {

    private AccountManager accountManager;
    private TestWriter writer;
    private List<Account> accounts;
    private Account account;

    static class TestWriter extends com.virtualbankv2.boundary.Writer {
        List<Account> writtenAccounts = new ArrayList<>();
        List<Transaction> writtenTransactions = new ArrayList<>();

        @Override
        public void writeAccounts(List<Account> accounts) {
            if (accounts != null) {
                writtenAccounts = new ArrayList<>(accounts);
            }
        }

        @Override
        public void writeSingleTransaction(Transaction transaction) {
            writtenTransactions.add(transaction);
        }
    }

    @BeforeEach
    void setUp() {
        writer = new TestWriter();
        accountManager = new AccountManager();
        accountManager.setWriter(writer);
        accounts = new ArrayList<>();
        account = new Account("12345", "Active", "testUser", "password", 1000.00, "Checking");
        accounts.add(account);
    }

    /**
     * Tests the displayAccountInformation method of AccountManager.
     */
    @Test
    void testDisplayAccountInformation() {
        accountManager.displayAccountInformation(account);
    }

    /**
     * Tests the getAccountById method of AccountManager.
     */
    @Test
    void testGetAccountById() {
        Account foundAccount = accountManager.getAccountById(accounts, "12345");
        assertEquals(account, foundAccount);

        Account notFoundAccount = accountManager.getAccountById(accounts, "67890");
        assertNull(notFoundAccount);
    }

    /**
     * Tests the validatePassword method of AccountManager.
     */
    @Test
    void testValidatePassword() {
        assertTrue(accountManager.validatePassword("password", account));
        assertFalse(accountManager.validatePassword("wrongPassword", account));
    }

    /**
     * Tests the isFrozen method of AccountManager.
     */
    @Test
    void testIsFrozen() {
        assertFalse(accountManager.isFrozen(account));
        account.setStatus("Frozen");
        assertTrue(accountManager.isFrozen(account));
    }

    /**
     * Tests the isDeleted method of AccountManager.
     */
    @Test
    void testIsDeleted() {
        assertFalse(accountManager.isDeleted(account));
        account.setStatus("Deleted");
        assertTrue(accountManager.isDeleted(account));
    }
}
