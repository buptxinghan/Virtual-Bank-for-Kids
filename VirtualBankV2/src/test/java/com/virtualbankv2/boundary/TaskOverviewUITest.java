package com.virtualbankv2.boundary;

import static org.junit.Assert.*;
import com.virtualbankv2.control.VirtualBankApplication;
import com.virtualbankv2.entity.User;
import org.junit.Test;

/**
 * This class tests the functionality of the TaskOverviewUI class.
 *
 * @version 1.0
 * @since 2024-05-01
 */

public class TaskOverviewUITest {

    @Test
    public void testTaskOverviewUIInitialization() {
        new Reader();
        new VirtualBankApplication();
        TaskOverviewUI taskOverviewUI = new TaskOverviewUI();
        assertNotNull(taskOverviewUI);
    }

    @Test
    public void testTaskCompletionButton() {
        new Reader();
        new VirtualBankApplication();
        TaskOverviewUI taskOverviewUI = new TaskOverviewUI();
        assertNotNull(taskOverviewUI.getCntButton());
        assertTrue(taskOverviewUI.getCntButton().isEnabled());
        taskOverviewUI.getCntButton().doClick();
    }
}
