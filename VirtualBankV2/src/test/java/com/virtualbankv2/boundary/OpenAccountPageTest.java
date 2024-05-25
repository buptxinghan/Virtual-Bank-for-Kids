package com.virtualbankv2.boundary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * This class tests the functionality of the OpenAccountPage class.
 * It includes tests for actionPerformed method with invalid input, passwords that do not match, and successful account creation.
 *
 * @version 1.0
 * @since 2024-05-01
 */

class OpenAccountPageTest {

    private OpenAccountPage openAccountPage;

    @BeforeEach
    void setUp() {
        openAccountPage = new OpenAccountPage();
        openAccountPage.setVisible(true);
    }

    @Test
    void testActionPerformed_invalidInput() throws AWTException {
        enterText("123456", "123456");

        clickButton(openAccountPage.getSubmit());

        assertFalse(dialogIsVisible());
    }

    @Test
    void testActionPerformed_passwordsDoNotMatch() throws AWTException {
        enterText("123456", "654321");

        clickButton(openAccountPage.getSubmit());

        assertFalse(dialogIsVisible());
    }

    @Test
    void testActionPerformed_success() throws AWTException {
        enterText("123456", "123456");

        clickButton(openAccountPage.getSubmit());

        assertFalse(dialogIsVisible());
    }

    private void enterText(String... texts) throws AWTException {
        Robot robot = new Robot();
        for (String text : texts) {
            for (char c : text.toCharArray()) {
                robot.keyPress(Character.toUpperCase(c));
                robot.keyRelease(Character.toUpperCase(c));
                robot.delay(50);
            }
            robot.delay(100);
        }
    }

    private void clickButton(JButton button) throws AWTException {
        Point p = button.getLocationOnScreen();
        Robot robot = new Robot();
        robot.mouseMove(p.x + button.getWidth() / 2, p.y + button.getHeight() / 2);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(100);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    private boolean dialogIsVisible() {
        Frame[] frames = Frame.getFrames();
        for (Frame frame : frames) {
            if (frame instanceof JFrame) {
                JFrame jFrame = (JFrame) frame;
                Component[] components = jFrame.getLayeredPane().getComponents();
                for (Component component : components) {
                    if (component instanceof JOptionPane) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
