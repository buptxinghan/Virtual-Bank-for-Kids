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
    void testTransferIn() {
        double initialBalance = account.getBalance();
        double amount = 500.00;
        accountManager.transferIn(account, amount);

        assertEquals(initialBalance + amount, account.getBalance());

        assertEquals(1, writer.writtenTransactions.size());
        Transaction capturedTransaction = writer.writtenTransactions.get(0);

        assertEquals("System", capturedTransaction.getAccountFrom());
        assertEquals(account.getAccountID(), capturedTransaction.getAccountTo());
        assertEquals(amount, capturedTransaction.getAmount());
        assertEquals(LocalDate.now(), LocalDate.parse(capturedTransaction.getDate(), DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        assertEquals("Transfer In", capturedTransaction.getDescription());
    }

    @Test
    void testWithdraw() {
        double initialBalance = account.getBalance();
        double amount = 200.00;
        assertTrue(accountManager.withdraw(account, amount));
        assertEquals(initialBalance - amount, account.getBalance());

        assertFalse(accountManager.withdraw(account, initialBalance + 100));
        assertEquals(initialBalance - amount, account.getBalance());
    }

    @Test
    void testFreezeAccount() {
        accountManager.freezeAccount(account);
        assertNotNull(writer.writtenAccounts);
        assertTrue(writer.writtenAccounts.contains(account));
        assertEquals("Frozen", account.getStatus());
        assertEquals(1, writer.writtenAccounts.size());
    }

    @Test
    void testUnfreezeAccount() {
        account.setStatus("Frozen");
        accountManager.unfreezeAccount(account);
        assertTrue(writer.writtenAccounts.contains(account));
        assertEquals("Active", account.getStatus());
        assertEquals(1, writer.writtenAccounts.size());
    }

    @Test
    void testDeleteAccount() {
        accountManager.deleteAccount(account);
        assertTrue(writer.writtenAccounts.contains(account));
        assertEquals("Deleted", account.getStatus());
        assertEquals(0.00, account.getBalance());
        assertEquals("---", account.getAccountType());
        assertEquals("---", account.getUsername());
        assertEquals("---", account.getPassword());
        assertEquals("---", account.getAccountID());
        assertEquals(1, writer.writtenAccounts.size());
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
