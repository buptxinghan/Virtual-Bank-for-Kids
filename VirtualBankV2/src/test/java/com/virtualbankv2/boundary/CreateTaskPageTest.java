package com.virtualbankv2.boundary;
import com.virtualbankv2.boundary.CreateTaskPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class CreateTaskPageTest {

    private CreateTaskPage createTaskPage;

    @BeforeEach
    public void setUp() {
        createTaskPage = new CreateTaskPage();
    }

    @Test
    public void testGetTitle() {
        JTextField titleField = createTaskPage.getTitleField();
        assertNotNull(titleField);
        assertEquals("Task Title", titleField.getText(), "Title field text is not as expected");
    }

    @Test
    public void testGetReward() {
        JTextField rewardField = createTaskPage.getReward();
        assertNotNull(rewardField);
        assertEquals("100", rewardField.getText(), "Reward field text is not as expected");
    }

    @Test
    public void testSaveButtonClicked() {
        // Set default values for title and reward fields
        JTextField titleField = createTaskPage.getTitleField();
        titleField.setText("Task Title");

        JTextField rewardField = createTaskPage.getReward();
        rewardField.setText("100");

        // Simulate clicking the save button
        createTaskPage.getSaveButton().doClick();

        // Sleep for a short duration to allow the save action to complete
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify that the task was created successfully
        // Here you can add assertions based on the behavior of the application after clicking the save button
        // For example, checking if the task was added to a list or database
        // For demonstration purpose, let's assume the task is added to a list
        //assertEquals(1, TaskManager.getTasks().size(), "Task was not created successfully");

        // Clear the tasks list for other tests (assuming tasks list is static)
        //TaskManager.getTasks().clear();
    }
}
