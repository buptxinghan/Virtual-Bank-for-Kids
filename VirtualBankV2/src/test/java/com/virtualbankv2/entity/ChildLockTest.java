package com.virtualbankv2.entity;
import com.virtualbankv2.boundary.ChildLock;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import static org.junit.jupiter.api.Assertions.*;

public class ChildLockTest {

    @Test
    void testChildLockConstructor() {
        ChildLock childLock = new ChildLock();
        assertNotNull(childLock);
        assertEquals(600, childLock.getWidth());
        assertEquals(450, childLock.getHeight());

        // Check if mainPanel is not null
        assertNotNull(childLock.getContentPane());
    }

    @Test
    void testChildLockComponents() {
        ChildLock childLock = new ChildLock();
        assertNotNull(childLock);

        // Check if userText and checkButton are not null
        JTextField userText = childLock.getUserText();
        JButton checkButton = childLock.getCheckButton();
        assertNotNull(userText);
        assertNotNull(checkButton);

        // Check if the userText has a proper size
        assertEquals(20, userText.getColumns());
        // Check if the button text is correct
        assertEquals("Check", checkButton.getText());
    }
}
