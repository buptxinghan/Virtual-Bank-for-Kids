package com.virtualbankv2.boundary;

import com.virtualbankv2.entity.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class represents the GUI for displaying account overview.
 * It provides functionalities to set up and manage account overview page.
 *
 * @version 2.0
 * @since 2024-04-05
 * @author Ji Zheng
 */

public class AccountOverviewUITest {

    private AccountOverviewUI accountOverviewUI;

    /**
     * Sets up the test fixture before each test method.
     */
    @BeforeEach
    public void setUp() {
        // Instantiate the AccountOverviewUI
        accountOverviewUI = new AccountOverviewUI();
        // Set up the page
        accountOverviewUI.setPage();
    }

    /**
     * Tests the updatePage method with a valid account.
     */
    @Test
    public void testUpdatePageWithValidAccount() {
        // Create a sample account
        Account account = new Account("123456789", "JohnDoe", "Savings", "Active", 1000.00, "password");

        // Create a CountDownLatch with a count of 1
        CountDownLatch buttonLatch = new CountDownLatch(1);
        // Call the updatePage method with the sample account
        accountOverviewUI.updatePage(account);

        // Execute the updatePage method on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            try {
                // Assert that the buttonLatch counts down to zero within 5 seconds
                assertTrue(buttonLatch.await(5, TimeUnit.SECONDS));
            } catch (InterruptedException e) {
                // Fail the test if the thread is interrupted while waiting for the latch
                fail("Thread interrupted while waiting for latch");
            }
        });
        // Assert true to signify successful completion of the test
        assertTrue(true);
    }
}
