package com.virtualbankv2.control;

import com.virtualbankv2.boundary.*;
import com.virtualbankv2.entity.Interest;
import com.virtualbankv2.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static com.virtualbankv2.boundary.Reader.interests;
import static com.virtualbankv2.control.VirtualBankApplication.currentUser;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the functionality of the HomePageController class.
 * It includes tests for the actions of the Goals, Manual, and Tasks buttons.
 *
 * @version 1.0
 * @since 2024-04-25
 * @author Ji Zheng
 */

public class HomePageControllerTest {

    private HomePage view;
    private HomePageController controller;

    @BeforeEach
    void setUp() {
        new Reader();
        new VirtualBankApplication();
        interests = new ArrayList<>();  // Initialize the interests list
        interests.add(new Interest("testUser", "2024/01/01"));

        User user = new User("testUser", "password");
        currentUser = user;

        view = new HomePage();
        controller = new HomePageController(view);
        controller.initializeController();
    }

    /**
     * Tests the action of the Goals button on the HomePage.
     */
    @Test
    void testGoalsButtonAction() {
        JButton goalsButton = view.getGoalsButton();
        for (ActionListener listener : goalsButton.getActionListeners()) {
            listener.actionPerformed(new ActionEvent(goalsButton, ActionEvent.ACTION_PERFORMED, null));
        }
        assertFalse(view.isVisible());
        assertTrue(isPageOpened(GoalOverviewUI.class));
    }

    /**
     * Tests the action of the Manual button on the HomePage.
     */
    @Test
    void testManualButtonAction() {
        JButton manualButton = view.getManualButton();
        for (ActionListener listener : manualButton.getActionListeners()) {
            listener.actionPerformed(new ActionEvent(manualButton, ActionEvent.ACTION_PERFORMED, null));
        }
        assertFalse(view.isVisible());
        assertTrue(isPageOpened(UserManual.class));
    }

    /**
     * Tests the action of the Tasks button on the HomePage.
     */
    @Test
    void testTasksButtonAction() {
        JButton tasksButton = view.getTasksButton();
        for (ActionListener listener : tasksButton.getActionListeners()) {
            listener.actionPerformed(new ActionEvent(tasksButton, ActionEvent.ACTION_PERFORMED, null));
        }
        assertFalse(view.isVisible());
        assertTrue(isPageOpened(TaskOverviewUI.class));
    }

    /**
     * Helper method to check if a specific page is opened.
     *
     * @param pageClass the class of the page to check
     * @return true if the page is opened, false otherwise
     */
    private boolean isPageOpened(Class<?> pageClass) {
        for (java.awt.Window window : java.awt.Window.getWindows()) {
            if (pageClass.isInstance(window)) {
                return true;
            }
        }
        return false;
    }
}
