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

/**
 * This class tests the functionality of the VirtualBankApplication class.
 * It includes tests for constructor initialization and retrieving current user accounts.
 *
 * @version 1.0
 * @since 2024-05-10
 * @author Ji Zheng
 */
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

    /**
     * Tests the constructor of VirtualBankApplication for proper initialization.
     */
    @Test
    public void testConstructorInitialization() {
        User expectedUser = users.get(1);
        assertNotNull(VirtualBankApplication.currentUser);
        assertEquals(expectedUser, VirtualBankApplication.currentUser);

        int expectedTotalCounter = reader.readTotalCounter("src/Data/Tasks.csv");
        assertEquals(expectedTotalCounter, totalcounter);
    }

    /**
     * Tests the getCurrentUserAccounts method for retrieving accounts of the current user.
     */
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
