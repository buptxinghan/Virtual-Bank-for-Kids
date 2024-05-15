package com.virtualbankv2.boundary;

import static org.junit.Assert.*;
import org.junit.Test;
import com.virtualbankv2.boundary.TaskOverviewUI;
import com.virtualbankv2.entity.User;

public class TaskOverviewUITest {

    @Test
    public void testTaskCreation() {
        User currentUser = new User("testUser", "testPassword");
        TaskOverviewUI taskOverviewUI = new TaskOverviewUI(currentUser);
        assertNotNull(taskOverviewUI);
    }

    @Test
    public void testTaskCompletionButton() {
        User currentUser = new User("testUser", "testPassword");
        TaskOverviewUI taskOverviewUI = new TaskOverviewUI(currentUser);
        taskOverviewUI.setCurrentUser(currentUser);
        assertNotNull(taskOverviewUI.getCreateTaskButton());
        assertTrue(taskOverviewUI.getCreateTaskButton().isEnabled());
        taskOverviewUI.getCreateTaskButton().doClick();
    }
    
    @Test
    public void testTaskCompletionInteraction() {
        User currentUser = new User("testUser", "testPassword");
        TaskOverviewUI taskOverviewUI = new TaskOverviewUI(currentUser);
    }
}