package com.virtualbankv2.boundary;

import org.junit.jupiter.api.Test;
import javax.swing.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class represents the GUI for displaying child lock functionality.
 * It provides a user interface to set up and manage child lock settings.
 *
 * @version 3.0
 * @since 2024-04-20
 * @author Ji Zheng
 */

public class ChildLockTest {

    /**
     * Tests the constructor of ChildLock.
     */
    @Test
    void testChildLockConstructor() {
        // Instantiate ChildLock
        ChildLock childLock = new ChildLock();
        // Check if the instance is not null
        assertNotNull(childLock);
        // Check if the width matches the expected value
        assertEquals(600, childLock.getWidth());
        // Check if the height matches the expected value
        assertEquals(450, childLock.getHeight());
        // Check if the content pane is not null
        assertNotNull(childLock.getContentPane());
    }

    /**
     * Tests the components of ChildLock.
     */
    @Test
    void testChildLockComponents() {
        // Instantiate ChildLock
        ChildLock childLock = new ChildLock();
        // Check if the instance is not null
        assertNotNull(childLock);

        // Get the userText JTextField
        JTextField userText = childLock.getUserText();
        // Get the checkButton JButton
        JButton checkButton = childLock.getCheckButton();
        // Check if the userText is not null
        assertNotNull(userText);
        // Check if the checkButton is not null
        assertNotNull(checkButton);

        // Check if the number of columns of userText matches the expected value
        assertEquals(20, userText.getColumns());
        // Check if the text of checkButton matches the expected value
        assertEquals("Check", checkButton.getText());
    }
}
