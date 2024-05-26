package com.virtualbankv2.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the functionality of the Goal class.
 * It includes tests for the constructor and the setter and getter methods.
 *
 * @version 1.0
 * @since 2024-05-10
 * @author Ji Zheng
 */
public class GoalTest {

    /**
     * Tests the constructor of the Goal class.
     */
    @Test
    void testGoalConstructor() {
        String goalName = "123456";
        String description = "Buy a new bicycle";
        double targetAmount = 200.0;
        double currentAmount = 50.0;
        String username = "JohnDoe";

        Goal goal = new Goal(goalName, description, targetAmount, currentAmount, username);

        assertNotNull(goal);
        assertEquals(goalName, goal.getGoalName());
        assertEquals(description, goal.getDescription());
        assertEquals(targetAmount, goal.getTargetAmount());
        assertEquals(currentAmount, goal.getCurrentAmount());
        assertEquals(username, goal.getUsername());
    }

    /**
     * Tests the setter and getter methods of the Goal class.
     */
    @Test
    void testGoalSettersAndGetters() {
        Goal goal = new Goal("", "", 0.0, 0.0, "");

        String goalName = "987654";
        String description = "Learn to play the guitar";
        double targetAmount = 500.0;
        double currentAmount = 100.0;
        String username = "JaneDoe";

        goal.setGoalName(goalName);
        goal.setDescription(description);
        goal.setTargetAmount(targetAmount);
        goal.setCurrentAmount(currentAmount);
        goal.setUsername(username);

        assertEquals(goalName, goal.getGoalName());
        assertEquals(description, goal.getDescription());
        assertEquals(targetAmount, goal.getTargetAmount());
        assertEquals(currentAmount, goal.getCurrentAmount());
        assertEquals(username, goal.getUsername());
    }
}
