package com.virtualbankv2.boundary;

import com.virtualbankv2.entity.Goal;
import com.virtualbankv2.entity.User;
import com.virtualbankv2.control.VirtualBankApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GoalOverviewUITest {

    private GoalOverviewUI goalOverviewUI;

    @BeforeEach
    public void setUp() throws Exception {
        new Reader();
        new VirtualBankApplication();
        VirtualBankApplication.currentUser = new User("testUser", "password");

        Reader.goals = new ArrayList<>();
        Goal goal = new Goal("testUser", "Test Goal", 1000, 0, "A test goal description");
        goal.setCurrentAmount(500);
        Reader.goals.add(goal);

        goalOverviewUI = new GoalOverviewUI();
    }

    @Test
    public void testHeaderPanelInitialization() {
        JPanel headerPanel = (JPanel) goalOverviewUI.getContentPane().getComponent(0);
        assertNotNull(headerPanel);
        assertEquals(new Color(199, 220, 247), headerPanel.getBackground());
        assertTrue(headerPanel.getComponent(0) instanceof JLabel);
    }

    @Test
    public void testGoalInfoPanelInitialization() {
        Container contentPane = goalOverviewUI.getContentPane();
        Component[] components = contentPane.getComponents();
        assertTrue(components.length >= 2);

        JPanel goalInfoPanel = (JPanel) components[1];

        assertNotNull(goalInfoPanel);
        assertEquals(BoxLayout.Y_AXIS, ((BoxLayout) goalInfoPanel.getLayout()).getAxis());
        assertEquals(3, goalInfoPanel.getComponentCount());
    }


    @Test
    public void testButtonPanelInitialization() {
        JPanel buttonPanel = (JPanel) goalOverviewUI.getContentPane().getComponent(2);
        assertNotNull(buttonPanel);
        assertEquals(new Color(199, 220, 247), buttonPanel.getBackground());
        assertEquals(FlowLayout.class, buttonPanel.getLayout().getClass());

        Component[] components = buttonPanel.getComponents();
        assertEquals(2, components.length);

        assertTrue(components[0] instanceof JButton);
        assertTrue(components[1] instanceof JButton);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testHandleCreateGoal_NoGoals() throws Exception {
        Field userGoalsField = GoalOverviewUI.class.getDeclaredField("userGoals");
        userGoalsField.setAccessible(true);
        List<Goal> userGoals = (List<Goal>) userGoalsField.get(goalOverviewUI);
        userGoals.clear();

        Method handleCreateGoalMethod = GoalOverviewUI.class.getDeclaredMethod("handleCreateGoal");
        handleCreateGoalMethod.setAccessible(true);
        handleCreateGoalMethod.invoke(goalOverviewUI);

        assertFalse(goalOverviewUI.isVisible()); // Ensure the window is closed
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testHandleCreateGoal_GoalAchieved() throws Exception {
        Goal currentGoal = new Goal("testUser", "Test Goal", 1000, 0, "A test goal description");
        currentGoal.setCurrentAmount(1000.0);

        Field currentGoalField = GoalOverviewUI.class.getDeclaredField("currentGoal");
        currentGoalField.setAccessible(true);
        currentGoalField.set(goalOverviewUI, currentGoal);

        Method handleCreateGoalMethod = GoalOverviewUI.class.getDeclaredMethod("handleCreateGoal");
        handleCreateGoalMethod.setAccessible(true);
        handleCreateGoalMethod.invoke(goalOverviewUI);

        assertFalse(goalOverviewUI.isVisible());
    }
}
