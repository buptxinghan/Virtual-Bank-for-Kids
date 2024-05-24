package com.virtualbankv2.control;

import com.virtualbankv2.boundary.ChildLock;
import com.virtualbankv2.boundary.CreateTaskPage;
import com.virtualbankv2.boundary.OpenAccountPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.junit.jupiter.api.Assertions.*;

public class ChildLockManagerTest {

    private ChildLockManager childLockManager;
    private JFrame frame;
    private JButton button;

    @BeforeEach
    void setUp() {
        childLockManager = new ChildLockManager();
        frame = new JFrame();
        button = new JButton("Test Button");

        childLockManager.addButtonWithChildLock(frame, button, "OpenAccountPage");
    }

    @Test
    void testChildLockCorrectAnswer() {
        button.doClick();

        ChildLock childLock = getCurrentChildLock();
        assertNotNull(childLock);

        JTextField userText = childLock.getUserText();
        JButton checkButton = childLock.getCheckButton();

        userText.setText("111");
        checkButton.doClick();

        assertFalse(frame.isVisible());
        assertFalse(childLock.isVisible());
        assertTrue(isPageOpened(OpenAccountPage.class));
    }

    @Test
    void testChildLockIncorrectAnswer() {
        button.doClick();

        ChildLock childLock = getCurrentChildLock();
        assertNotNull(childLock);

        JTextField userText = childLock.getUserText();
        JButton checkButton = childLock.getCheckButton();

        userText.setText("wrong");
        checkButton.doClick();

        assertTrue(childLock.isVisible());
        JOptionPane pane = getJOptionPane();
        assertNotNull(pane);
        assertEquals("Answer incorrect, please try again!", pane.getMessage());
        assertEquals(JOptionPane.ERROR_MESSAGE, pane.getMessageType());
    }

    private ChildLock getCurrentChildLock() {
        for (Window window : Window.getWindows()) {
            if (window instanceof ChildLock) {
                return (ChildLock) window;
            }
        }
        return null;
    }

    private boolean isPageOpened(Class<?> pageClass) {
        for (Window window : Window.getWindows()) {
            if (pageClass.isInstance(window)) {
                return true;
            }
        }
        return false;
    }

    private JOptionPane getJOptionPane() {
        for (Window window : Window.getWindows()) {
            if (window instanceof JDialog) {
                JDialog dialog = (JDialog) window;
                if (dialog.isVisible() && dialog.getContentPane() instanceof JOptionPane) {
                    return (JOptionPane) dialog.getContentPane();
                }
            }
        }
        return null;
    }
}
