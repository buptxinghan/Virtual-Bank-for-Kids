import com.virtualbankv2.entity.Goal;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GoalTest {

    @Test
    void testGoalConstructor() {
        String goalID = "123456";
        String description = "Buy a new bicycle";
        double targetAmount = 200.0;
        double currentAmount = 50.0;
        String childUsername = "JohnDoe";

        Goal goal = new Goal(goalID, description, targetAmount, currentAmount, childUsername);

        assertNotNull(goal);
        assertEquals(goalID, goal.getGoalID());
        assertEquals(description, goal.getDescription());
        assertEquals(targetAmount, goal.getTargetAmount());
        assertEquals(currentAmount, goal.getCurrentAmount());
        assertEquals(childUsername, goal.getChildUsername());
    }

    @Test
    void testGoalSettersAndGetters() {
        Goal goal = new Goal("", "", 0.0, 0.0, "");

        String goalID = "987654";
        String description = "Learn to play the guitar";
        double targetAmount = 500.0;
        double currentAmount = 100.0;
        String childUsername = "JaneDoe";

        goal.setGoalID(goalID);
        goal.setDescription(description);
        goal.setTargetAmount(targetAmount);
        goal.setCurrentAmount(currentAmount);
        goal.setChildUsername(childUsername);

        assertEquals(goalID, goal.getGoalID());
        assertEquals(description, goal.getDescription());
        assertEquals(targetAmount, goal.getTargetAmount());
        assertEquals(currentAmount, goal.getCurrentAmount());
        assertEquals(childUsername, goal.getChildUsername());
    }
}
