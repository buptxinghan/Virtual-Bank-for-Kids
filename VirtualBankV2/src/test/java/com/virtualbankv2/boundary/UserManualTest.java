package com.virtualbankv2.boundary;

import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the functionality of the UserManual class.
 *
 * @version 1.0
 * @since 2024-05-01
 */

public class UserManualTest {

    @Test
    public void testUserManualCreation() {
        UserManual userManual = new UserManual();
        assertNotNull(userManual);
        assertEquals("Virtual Bank", userManual.getTitle());
        assertTrue(userManual.isVisible());
        assertEquals(JFrame.EXIT_ON_CLOSE, userManual.getDefaultCloseOperation());
    }

    @Test
    public void testUserManualTopPanel() {
        UserManual userManual = new UserManual();
        JPanel topPanel = (JPanel) userManual.getContentPane().getComponent(0);
        assertNotNull(topPanel);
        assertEquals(GridBagLayout.class, topPanel.getLayout().getClass());
    }

    @Test
    public void testUserManualMiddlePanel() {
        UserManual userManual = new UserManual();
        JPanel middlePanel = (JPanel) userManual.getContentPane().getComponent(1);
        assertNotNull(middlePanel);
        assertEquals(BoxLayout.class, middlePanel.getLayout().getClass());
    }

    @Test
    public void testUserManualBottomPanel() {
        UserManual userManual = new UserManual();
        JPanel bottomPanel = (JPanel) userManual.getContentPane().getComponent(2);
        assertNotNull(bottomPanel);
        assertTrue(bottomPanel.getComponent(0) instanceof JButton);
    }
}
