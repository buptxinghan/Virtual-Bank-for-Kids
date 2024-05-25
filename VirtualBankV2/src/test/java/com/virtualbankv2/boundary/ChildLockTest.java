package com.virtualbankv2.boundary;
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

        assertNotNull(childLock.getContentPane());
    }

    @Test
    void testChildLockComponents() {
        ChildLock childLock = new ChildLock();
        assertNotNull(childLock);

        JTextField userText = childLock.getUserText();
        JButton checkButton = childLock.getCheckButton();
        assertNotNull(userText);
        assertNotNull(checkButton);

        assertEquals(20, userText.getColumns());
        assertEquals("Check", checkButton.getText());
    }
}