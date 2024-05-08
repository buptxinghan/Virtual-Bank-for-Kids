import com.virtualbankv2.entity.Goal;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GoalTest {

    @Test
    void testGoalConstructor() {
        String goalName = "123456"; // Update variable name
        String description = "Buy a new bicycle";
        double targetAmount = 200.0;
        double currentAmount = 50.0;
        String childUsername = "JohnDoe";
        String startDate = ""; // Add missing parameters
        String endDate = ""; // Add missing parameters
        String targetAccount = ""; // Add missing parameters

        Goal goal = new Goal(goalName, description, targetAmount, currentAmount, childUsername, startDate, endDate, targetAccount); // Update constructor call

        assertNotNull(goal);
        assertEquals(goalName, goal.getGoalName());
        assertEquals(description, goal.getDescription());
        assertEquals(targetAmount, goal.getTargetAmount());
        assertEquals(currentAmount, goal.getCurrentAmount());
        assertEquals(childUsername, goal.getUsername());
    }

    @Test
    void testGoalSettersAndGetters() {
        Goal goal = new Goal("", "", 0.0, 0.0, "", "", "", ""); // Update constructor call

        String goalName = "987654"; // Update variable name
        String description = "Learn to play the guitar";
        double targetAmount = 500.0;
        double currentAmount = 100.0;
        String childUsername = "JaneDoe";
        String startDate = ""; // Add missing parameters
        String endDate = ""; // Add missing parameters
        String targetAccount = ""; // Add missing parameters

        goal.setGoalName(goalName);
        goal.setDescription(description);
        goal.setTargetAmount(targetAmount);
        goal.setCurrentAmount(currentAmount);
        goal.setUsername(childUsername);
        goal.setStartDate(startDate); // Add missing setter call
        goal.setEndDate(endDate); // Add missing setter call
        goal.setTargetAccount(targetAccount); // Add missing setter call

        assertEquals(goalName, goal.getGoalName());
        assertEquals(description, goal.getDescription());
        assertEquals(targetAmount, goal.getTargetAmount());
        assertEquals(currentAmount, goal.getCurrentAmount());
        assertEquals(childUsername, goal.getUsername());
    }
}
