package com.virtualbankv2.boundary;

import com.virtualbankv2.entity.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

class ReaderTest {

    private Reader reader;

    @BeforeEach
    void setUp() {
        reader = new Reader();
    }

    @Test
    void testReadUsers() {
        List<User> users = reader.readUsers("src/Data/Users.csv");
        assertNotNull(users);
        assertFalse(users.isEmpty());
        // Add more assertions to validate the contents of the users list if needed
    }

    @Test
    void testReadAccounts() {
        List<Account> accounts = reader.readAccounts("src/Data/Accounts.csv");
        assertNotNull(accounts);
        assertFalse(accounts.isEmpty());
        // Add more assertions to validate the contents of the accounts list if needed
    }

    @Test
    void testReadGoals() {
        List<Goal> goals = reader.readGoals("src/Data/Goals.csv");
        assertNotNull(goals);
        assertFalse(goals.isEmpty());
        // Add more assertions to validate the contents of the goals list if needed
    }

    @Test
    void testReadTasks() {
        List<Task> tasks = reader.readTasks("src/Data/Tasks.csv");
        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());
        // Add more assertions to validate the contents of the tasks list if needed
    }

    @Test
    void testReadSupport() {
        List<SupportSystem> support = reader.readSupport("src/Data/Support.csv");
        assertNotNull(support);
        assertFalse(support.isEmpty());
        // Add more assertions to validate the contents of the support list if needed
    }

    @Test
    void testReadTransactions() {
        List<Transaction> transactions = reader.readTransactions("src/Data/Transactions.csv");
        assertNotNull(transactions);
        assertFalse(transactions.isEmpty());
        // Add more assertions to validate the contents of the transactions list if needed
    }
}
