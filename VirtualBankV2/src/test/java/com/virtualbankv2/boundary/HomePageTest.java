package com.virtualbankv2.boundary;

import com.virtualbankv2.control.VirtualBankApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the functionality of the HomePage class.
 * It includes tests for the account button, tasks button, goals button, manual button,
 * and window visibility.
 *
 * @version 2.0
 * @since 2024-04-15
 * @author Ji Zheng
 */

public class HomePageTest {
    private HomePage homePage;

    @BeforeEach
    public void setUp() {
        new Reader();
        new VirtualBankApplication();
        homePage = new HomePage();
    }

    @Test
    public void testAccountButton() {
        JButton accountButton = homePage.getAccountButton();
        assertNotNull(accountButton, "Account button should not be null");
        String buttonText = getButtonText(accountButton);
        assertTrue(buttonText.contains("My account") && buttonText.contains("Check your account information"),
                "Account button text is not as expected: " + buttonText);
    }

    @Test
    public void testTasksButton() {
        JButton tasksButton = homePage.getTasksButton();
        assertNotNull(tasksButton, "Tasks button should not be null");
        String buttonText = getButtonText(tasksButton);
        assertTrue(buttonText.contains("My tasks") && buttonText.contains("Check the tasks assigned"),
                "Tasks button text is not as expected: " + buttonText);
    }

    @Test
    public void testGoalsButton() {
        JButton goalsButton = homePage.getGoalsButton();
        assertNotNull(goalsButton, "Goals button should not be null");
        String buttonText = getButtonText(goalsButton);
        assertTrue(buttonText.contains("My goals") && buttonText.contains("Check and manage your goal"),
                "Goals button text is not as expected: " + buttonText);
    }

    @Test
    public void testManualButton() {
        JButton manualButton = homePage.getManualButton();
        assertNotNull(manualButton, "Manual button should not be null");
        String buttonText = getButtonText(manualButton);
        assertTrue(buttonText.contains("Instruction manual") && buttonText.contains("Learn how to use the software or contact us"),
                "Manual button text is not as expected: " + buttonText);
    }

    @Test
    public void testWindowVisibility() {
        assertTrue(homePage.isVisible(), "HomePage window should be visible");
    }

    private String getButtonText(JButton button) {
        return button.getText().replaceAll("\\<.*?\\>", "").trim();
    }
}
