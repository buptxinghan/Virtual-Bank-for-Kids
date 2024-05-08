package com.virtualbankv2.boundary;
import com.virtualbankv2.entity.Goal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GoalTest {

    @Test
    void testGoalConstructor() {
        String goalName = "123456";
        String description = "Buy a new bicycle";
        double targetAmount = 200.0;
        double currentAmount = 50.0;
        String childUsername = "JohnDoe";
        String startDate = "";
        String endDate = "";

        Goal goal = new Goal(goalName, description, targetAmount, currentAmount, childUsername, startDate, endDate);

        assertNotNull(goal);
        assertEquals(goalName, goal.getGoalName());
        assertEquals(description, goal.getDescription());
        assertEquals(targetAmount, goal.getTargetAmount());
        assertEquals(currentAmount, goal.getCurrentAmount());
        assertEquals(childUsername, goal.getUsername());
    }

    @Test
    void testGoalSettersAndGetters() {
        Goal goal = new Goal("", "", 0.0, 0.0, "", "", "");

        String goalName = "987654";
        String description = "Learn to play the guitar";
        double targetAmount = 500.0;
        double currentAmount = 100.0;
        String childUsername = "JaneDoe";
        String startDate = "";
        String endDate = "";

        goal.setGoalName(goalName);
        goal.setDescription(description);
        goal.setTargetAmount(targetAmount);
        goal.setCurrentAmount(currentAmount);
        goal.setUsername(childUsername);
        goal.setStartDate(startDate);
        goal.setEndDate(endDate);

        assertEquals(goalName, goal.getGoalName());
        assertEquals(description, goal.getDescription());
        assertEquals(targetAmount, goal.getTargetAmount());
        assertEquals(currentAmount, goal.getCurrentAmount());
        assertEquals(childUsername, goal.getUsername());
    }
}
