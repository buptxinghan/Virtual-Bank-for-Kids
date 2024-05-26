package com.virtualbankv2.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the functionality of the Account class.
 * It includes tests for the constructor, setter and getter methods, and the toString method.
 *
 * @version 1.0
 * @since 2024-05-10
 * @author Ji Zheng
 */
public class AccountTest {

    /**
     * Tests the constructor of the Account class.
     */
    @Test
    void testAccountConstructor() {
        String accountID = "123456789";
        String accountType = "Savings";
        String username = "JohnDoe";
        String password = "password123";
        double balance = 1000.0;
        String status = "Active";

        Account account = new Account(accountID, accountType, username, password, balance, status);

        assertNotNull(account);
        assertEquals(accountID, account.getAccountID());
        assertEquals(accountType, account.getAccountType());
        assertEquals(username, account.getUsername());
        assertEquals(password, account.getPassword());
        assertEquals(balance, account.getBalance());
        assertEquals(status, account.getStatus());
    }

    /**
     * Tests the setter and getter methods of the Account class.
     */
    @Test
    void testAccountSetterGetterMethods() {
        Account account = new Account("123456789", "Savings", "JohnDoe", "password123", 1000.0, "Active");

        // Test setter methods
        account.setAccountID("987654321");
        account.setAccountType("Checking");
        account.setUsername("JaneDoe");
        account.setPassword("newpassword123");
        account.setBalance(1500.0);
        account.setStatus("Inactive");

        assertEquals("987654321", account.getAccountID());
        assertEquals("Checking", account.getAccountType());
        assertEquals("JaneDoe", account.getUsername());
        assertEquals("newpassword123", account.getPassword());
        assertEquals(1500.0, account.getBalance());
        assertEquals("Inactive", account.getStatus());
    }

    /**
     * Tests the toString method of the Account class.
     */
    @Test
    void testAccountToStringMethod() {
        String expectedString = "Account{accountID='123456789', accountType='Savings', username='JohnDoe', password='password123', balance=1000.0, status='Active'}";
        Account account = new Account("123456789", "Savings", "JohnDoe", "password123", 1000.0, "Active");
        assertEquals(expectedString, account.toString());
    }
}
