package com.virtualbankv2.boundary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.virtualbankv2.control.VirtualBankApplication;
import com.virtualbankv2.entity.User;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class CreateTaskPageTest {

    private CreateTaskPage createTaskPage;

    @BeforeEach
    public void setUp() {
        VirtualBankApplication.currentUser = new User("TestUser", "TestPassword");
        createTaskPage = new CreateTaskPage();
    }

    @Test
    public void testGetTitle() {
        JTextField titleField = createTaskPage.getTitleField();
        assertNotNull(titleField);
        assertEquals("", titleField.getText(), "Title field text is not empty initially");
    }

    @Test
    public void testGetReward() {
        JTextField rewardField = createTaskPage.getReward();
        assertNotNull(rewardField);
        assertEquals("", rewardField.getText(), "Reward field text is not empty initially");
    }

    @Test
    public void testSaveButtonClicked() {
        new Reader();
        JTextField titleField = createTaskPage.getTitleField();
        titleField.setText("Task Title");

        JTextField rewardField = createTaskPage.getReward();
        rewardField.setText("100");

        createTaskPage.getSaveButton().doClick();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
