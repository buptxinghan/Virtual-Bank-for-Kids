package com.virtualbankv2.boundary;

import com.virtualbankv2.control.VirtualBankApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.virtualbankv2.entity.Account;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the functionality of the TransactionPage class.
 *
 * @version 1.0
 * @since 2024-05-01
 */

public class TransactionPageTest {

    private TransactionPage transactionPage;

    @BeforeEach
    public void setUp() {
        new Reader();
        new VirtualBankApplication();
        Account testAccount = new Account("1234567890", "Active", "TestUser", "TestPassword", 1000.0, "Active");
        transactionPage = new TransactionPage(testAccount);
    }

    @Test
    public void testTransactionPageCreation() {
        assertNotNull(transactionPage);
        assertEquals("TransferPage", transactionPage.getTitle());
        assertTrue(transactionPage.isVisible());
        assertEquals(JFrame.EXIT_ON_CLOSE, transactionPage.getDefaultCloseOperation());
    }

    @Test
    public void testClearFields() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        JComboBox<String> transferToDropdown = transactionPage.getTransferToDropdown();
        JTextField amountField = transactionPage.getAmountField();
        JTextArea descriptionArea = transactionPage.getDescriptionArea();

        transferToDropdown.setSelectedIndex(1);
        amountField.setText("500.0");
        descriptionArea.setText("Test description");

        try {
            Method clearFieldsMethod = TransactionPage.class.getDeclaredMethod("clearFields");
            clearFieldsMethod.setAccessible(true);
            clearFieldsMethod.invoke(transactionPage);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        assertEquals(0, transferToDropdown.getSelectedIndex());
        assertTrue(amountField.getText().isEmpty());
        assertTrue(descriptionArea.getText().isEmpty());
    }
}
