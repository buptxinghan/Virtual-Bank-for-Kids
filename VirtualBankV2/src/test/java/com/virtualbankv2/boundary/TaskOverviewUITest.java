package com.virtualbankv2.boundary;

import static org.junit.Assert.*;
import org.junit.Test;

public class TaskOverviewUITest {

    @Test
    public void testTaskOverviewUIInitialization() {
        TaskOverviewUI taskOverviewUI = new TaskOverviewUI();
        assertNotNull(taskOverviewUI);
    }

    @Test
    public void testTaskCompletionButton() {
        TaskOverviewUI taskOverviewUI = new TaskOverviewUI();
        assertNotNull(taskOverviewUI.getCntButton());
        assertTrue(taskOverviewUI.getCntButton().isEnabled());
        taskOverviewUI.getCntButton().doClick();
    }
}
