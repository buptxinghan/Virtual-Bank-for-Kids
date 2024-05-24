package com.virtualbankv2.control;

import com.virtualbankv2.boundary.Reader;
import com.virtualbankv2.entity.Account;
import com.virtualbankv2.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.virtualbankv2.boundary.Reader.accounts;
import static com.virtualbankv2.boundary.Reader.users;
import static com.virtualbankv2.entity.Task.totalcounter;
import static org.junit.jupiter.api.Assertions.*;

public class VirtualBankApplicationTest {

    private VirtualBankApplication application;
    private User testUser;

    @BeforeEach
    void setUp() {
        new Reader();
        users = new ArrayList<>();
        accounts = new ArrayList<>();

        testUser = new User("testUser", "password");
        users.add(testUser);
        users.add(new User("anotherUser", "password"));

        accounts.add(new Account("001", "Saving", "testUser", "password", 1000.0, "Active"));
        accounts.add(new Account("002", "Checking", "testUser", "password", 1500.0, "Active"));
        accounts.add(new Account("003", "Saving", "anotherUser", "password", 2000.0, "Active"));

        totalcounter = 10;

        VirtualBankApplication.currentUser = testUser;

        application = new VirtualBankApplication();
    }

    @Test
    void testInitialization() {
        assertNotNull(application);
        assertEquals(10, totalcounter);
        assertNotNull(VirtualBankApplication.currentUser);
        assertEquals("testUser", VirtualBankApplication.currentUser.getUsername());
    }

    @Test
    void testGetCurrentUserAccounts() {
        VirtualBankApplication.currentUser = testUser;

        List<Account> currentUserAccounts = application.getCurrentUserAccounts(testUser);
        assertNotNull(currentUserAccounts);
        assertEquals(2, currentUserAccounts.size());

        Account account1 = currentUserAccounts.get(0);
        Account account2 = currentUserAccounts.get(1);

        assertEquals("001", account1.getAccountID());
        assertEquals("002", account2.getAccountID());

        assertEquals("Saving", account1.getAccountType());
        assertEquals("Checking", account2.getAccountType());

        assertEquals(1000.0, account1.getBalance());
        assertEquals(1500.0, account2.getBalance());
    }

    @Test
    void testGetCurrentUserAccountsNoAccounts() {
        User noAccountUser = new User("noAccountUser", "password");
        users.add(noAccountUser);
        VirtualBankApplication.currentUser = noAccountUser;

        List<Account> currentUserAccounts = application.getCurrentUserAccounts(noAccountUser);
        assertNotNull(currentUserAccounts);
        assertEquals(0, currentUserAccounts.size());
    }
}
