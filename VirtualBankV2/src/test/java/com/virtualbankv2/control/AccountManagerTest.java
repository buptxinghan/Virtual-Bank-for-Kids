package com.virtualbankv2.control;

import com.virtualbankv2.entity.Account;
import com.virtualbankv2.entity.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void testDisplayAccountInformation() {
        accountManager.displayAccountInformation(account);
    }

    @Test
    void testGetAccountById() {
        Account foundAccount = accountManager.getAccountById(accounts, "12345");
        assertEquals(account, foundAccount);

        Account notFoundAccount = accountManager.getAccountById(accounts, "67890");
        assertNull(notFoundAccount);
    }

    @Test
    void testValidatePassword() {
        assertTrue(accountManager.validatePassword("password", account));
        assertFalse(accountManager.validatePassword("wrongPassword", account));
    }

    @Test
    void testIsFrozen() {
        assertFalse(accountManager.isFrozen(account));
        account.setStatus("Frozen");
        assertTrue(accountManager.isFrozen(account));
    }

    @Test
    void testIsDeleted() {
        assertFalse(accountManager.isDeleted(account));
        account.setStatus("Deleted");
        assertTrue(accountManager.isDeleted(account));
    }
}
