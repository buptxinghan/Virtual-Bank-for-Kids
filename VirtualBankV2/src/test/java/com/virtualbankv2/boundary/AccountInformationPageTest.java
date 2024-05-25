package com.virtualbankv2.boundary;

import com.virtualbankv2.entity.Account;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.Component;
import java.awt.Container;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the functionality of the AccountInformationPage class.
 * It includes tests for the constructor, components, and balance label.
 *
 * @version 2.0
 * @since 2024-04-05
 * @author Ji Zheng
 */

public class AccountInformationPageTest {

    /**
     * Tests the constructor of AccountInformationPage.
     */
    @Test
    void testAccountInformationPageConstructor() {
        // Create a sample account
        Account account = new Account("123456789", "JohnDoe", "Savings", "password", 1000.00, "Active");
        // Instantiate AccountInformationPage with the sample account
        AccountInformationPage page = new AccountInformationPage(account);
        // Check if the page is not null
        assertNotNull(page);
        // Check if the page title is not null
        assertNotNull(page.getTitle());
        // Check if the content pane is not null
        assertNotNull(page.getContentPane());
        // Check if the account balance label is not null
        assertNotNull(page.getAccountBalanceLabel());
        // Check if the title matches
        assertEquals("Account Information", page.getTitle());
    }

    /**
     * Tests the components of AccountInformationPage.
     */
    @Test
    void testAccountInformationPageComponents() {
        // Create a sample account
        Account account = new Account("123456789", "JohnDoe", "Savings", "password", 1000.00, "Deleted");
        // Instantiate AccountInformationPage with the sample account
        AccountInformationPage page = new AccountInformationPage(account);
        // Check if the page is not null
        assertNotNull(page);

        // Get the content pane of the page
        Container contentPane = page.getContentPane();
        // Check if the number of components in the content pane is as expected
        assertEquals(4, contentPane.getComponentCount());

        // Check if components are added in the West panel
        Component westPanel = contentPane.getComponent(1);
        assertTrue(westPanel instanceof JPanel);
        Component[] westComponents = ((JPanel) westPanel).getComponents();
        // Print out the number of components in the West panel
        System.out.println("Number of components in West panel: " + westComponents.length);
        // Print out the details of each component in the West panel
        for (Component component : westComponents) {
            System.out.println(component.getClass().getName() + ": " + component.getName());
        }
    }

    /**
     * Tests the balance label of AccountInformationPage.
     */
    @Test
    void testAccountInformationPageBalanceLabel() {
        // Create a sample account
        Account account = new Account("123456789", "JohnDoe", "Savings", "password", 1000.00, "Frozen");
        // Instantiate AccountInformationPage with the sample account
        AccountInformationPage page = new AccountInformationPage(account);
        // Check if the page is not null
        assertNotNull(page);

        // Get the balance label from the page
        JLabel balanceLabel = page.getAccountBalanceLabel();
        // Check if the balance label is not null
        assertNotNull(balanceLabel);

        // Check if the text of the balance label matches the expected format
        assertEquals("<html><font color='Red' style='font-size: 20px;'>1,000.00</font></html>", balanceLabel.getText());
    }

}
