package com.virtualbankv2.entity;
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
        String username = "JohnDoe";


        Goal goal = new Goal(goalName,description,targetAmount,currentAmount,username);

        assertNotNull(goal);
        assertEquals(goalName, goal.getGoalName());
        assertEquals(description, goal.getDescription());
        assertEquals(targetAmount, goal.getTargetAmount());
        assertEquals(currentAmount, goal.getCurrentAmount());
        assertEquals(username, goal.getUsername());
    }

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
