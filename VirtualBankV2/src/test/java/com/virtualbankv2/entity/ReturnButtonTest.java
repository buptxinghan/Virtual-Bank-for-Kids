package com.virtualbankv2.entity;

import com.virtualbankv2.control.VirtualBankApplication;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ReturnButtonTest {

    @BeforeEach
    void setUp() {
        VirtualBankApplication.currentUser = new User("username", "password");
        List<User> users = new ArrayList<>();
        users.add(new User("username", "password"));
        try {
            VirtualBankApplication.class.getDeclaredField("users").set(null, users);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        // Mock Reader.users as well
        try {
            Class<?> readerClass = Class.forName("com.virtualbankv2.boundary.Reader");
            readerClass.getDeclaredField("users").set(null, users);
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testCreateReturnButton_NoAccount_DefaultSize() {
        JFrame frame = new JFrame();
        JButton returnButton = ReturnButton.createReturnButton(frame, "accountOverviewPage");
        assertNotNull(returnButton);
        assertEquals("<html><font size ='6'>Return</font></html>", returnButton.getText());
        assertEquals(new Dimension(200, 50), returnButton.getPreferredSize());
    }

    @Test
    void testCreateReturnButton_NoAccount_CustomSize() {
        JFrame frame = new JFrame();
        Dimension customSize = new Dimension(300, 100);
        JButton returnButton = ReturnButton.createReturnButton(frame, "accountOverviewPage", customSize);
        assertNotNull(returnButton);
        assertEquals("<html><font size ='6'>Return</font></html>", returnButton.getText());
        assertEquals(customSize, returnButton.getPreferredSize());
    }

    @Test
    void testCreateReturnButton_WithAccount() {
        JFrame frame = new JFrame();
        Account account = new Account("12345", "Savings", "username", "password", 100.0, "Active");
        JButton returnButton = ReturnButton.createReturnButton(frame, "accountOverviewPage", new Dimension(200, 50), account);
        assertNotNull(returnButton);
        assertEquals("<html><font size ='6'>Return</font></html>", returnButton.getText());
    }
}
