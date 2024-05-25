package com.virtualbankv2.control;

import com.virtualbankv2.boundary.Reader;
import com.virtualbankv2.entity.Account;
import com.virtualbankv2.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.virtualbankv2.boundary.Reader.users;
import static com.virtualbankv2.entity.Task.totalcounter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class VirtualBankApplicationTest {

    private VirtualBankApplication app;
    private Reader reader;

    @BeforeEach
    public void setUp() {
        reader = new Reader();
        reader.readUsers("src/Data/Users.csv");
        reader.readAccounts("src/Data/Accounts.csv");

        app = new VirtualBankApplication();
    }

    @Test
    public void testConstructorInitialization() {
        User expectedUser = users.get(1);
        assertNotNull(VirtualBankApplication.currentUser);
        assertEquals(expectedUser, VirtualBankApplication.currentUser);

        int expectedTotalCounter = reader.readTotalCounter("src/Data/Tasks.csv");
        assertEquals(expectedTotalCounter, totalcounter);
    }

    @Test
    public void testGetCurrentUserAccounts() {
        User currentUser = users.get(1);
        List<Account> accounts = app.getCurrentUserAccounts(currentUser);
        assertNotNull(accounts);
        for (Account account : accounts) {
            assertEquals(currentUser.getUsername(), account.getUsername());
        }
    }
}
