package com.virtualbankv2.control;

import com.virtualbankv2.boundary.CreateTaskPage;
import com.virtualbankv2.boundary.TaskOverviewUI;
import com.virtualbankv2.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static com.virtualbankv2.control.VirtualBankApplication.currentUser;
import static com.virtualbankv2.entity.Task.totalcounter;
import static org.junit.jupiter.api.Assertions.*;

public class CreateTaskControllerTest {

    private CreateTaskPage view;
    private CreateTaskController controller;
    private User user;

    @BeforeEach
    void setUp() throws Exception {
        view = new CreateTaskPage();
        controller = new CreateTaskController(view);
        user = new User("testUser", "password");
        currentUser = user;

        Field defaultOptionPaneField = JOptionPane.class.getDeclaredField("defaultOptionPane");
        defaultOptionPaneField.setAccessible(true);
        defaultOptionPaneField.set(null, new CustomJOptionPane());
    }

    @Test
    void testCreateTaskWithValidInput() throws Exception {
        view.getTitleField().setText("Test Task");
        view.getReward().setText("10.0");
        view.getContentArea().setText("Task Description");

        view.setStart("2024-01-01");
        view.setEnd("2024-01-31");

        int initialCounter = totalcounter;

        controller.createTask();

        assertEquals(initialCounter + 1, totalcounter);
        assertEquals("Task created successfully!", CustomJOptionPane.getLastMessage());
        assertEquals(JOptionPane.INFORMATION_MESSAGE, CustomJOptionPane.getLastMessageType());
    }

    @Test
    void testCreateTaskWithInvalidInput() {
        view.getTitleField().setText("");
        view.getReward().setText("");

        controller.createTask();

        assertEquals("Please fill in the required field.", CustomJOptionPane.getLastMessage());
        assertEquals(JOptionPane.WARNING_MESSAGE, CustomJOptionPane.getLastMessageType());
    }

    @Test
    void testInitializeController() {
        controller.initializeController();

        assertNotNull(view.getSaveButton().getActionListeners());
        assertTrue(view.getDefaultCloseOperation() == JFrame.DO_NOTHING_ON_CLOSE);
        assertNotNull(view.getWindowListeners());
    }

    private void returnToTaskOverview(CreateTaskController controller) throws Exception {
        Method returnToTaskOverviewMethod = controller.getClass().getDeclaredMethod("returnToTaskOverview");
        returnToTaskOverviewMethod.setAccessible(true);
        returnToTaskOverviewMethod.invoke(controller);
    }

    @Test
    void testReturnToTaskOverview() throws Exception {
        returnToTaskOverview(controller);
        assertFalse(view.isVisible());
        assertTrue(isPageOpened(TaskOverviewUI.class));
    }

    private boolean isPageOpened(Class<?> pageClass) {
        for (Window window : Window.getWindows()) {
            if (pageClass.isInstance(window)) {
                return true;
            }
        }
        return false;
    }

    private static class CustomJOptionPane extends JOptionPane {
        private static String lastMessage;
        private static int lastMessageType;

        public static String getLastMessage() {
            return lastMessage;
        }

        public static int getLastMessageType() {
            return lastMessageType;
        }

        public static void showMessageDialog(Component parentComponent, Object message, String title, int messageType) {
            lastMessage = message.toString();
            lastMessageType = messageType;
        }
    }
}
