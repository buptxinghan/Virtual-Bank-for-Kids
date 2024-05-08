import com.virtualbankv2.boundary.HomePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import static org.junit.jupiter.api.Assertions.*;

public class HomePageTest {

    private HomePage homePage;

    @BeforeEach
    public void setUp() {
        homePage = new HomePage();
    }

    @Test
    public void testAccountButton() {
        JButton accountButton = homePage.getAccountButton();
        assertNotNull(accountButton);
        String buttonText = getButtonText(accountButton);
        assertTrue(buttonText.contains("My account") && buttonText.contains("Check your account information"), "Account button text is not as expected");
    }

    @Test
    public void testTasksButton() {
        JButton tasksButton = homePage.getTasksButton();
        assertNotNull(tasksButton);
        String buttonText = getButtonText(tasksButton);
        assertTrue(buttonText.contains("My tasks") && buttonText.contains("Check the tasks assigned"), "Tasks button text is not as expected");
    }

    @Test
    public void testGoalsButton() {
        JButton goalsButton = homePage.getGoalsButton();
        assertNotNull(goalsButton);
        String buttonText = getButtonText(goalsButton);
        assertTrue(buttonText.contains("My goals") && buttonText.contains("Check and manage your goal"), "Goals button text is not as expected");
    }

    @Test
    public void testManualButton() {
        JButton manualButton = homePage.getManualButton();
        assertNotNull(manualButton);
        String buttonText = getButtonText(manualButton);
        assertTrue(buttonText.contains("Instruction manual") && buttonText.contains("Learn how to use the software or contact us"), "Manual button text is not as expected");
    }

    @Test
    public void testWindowVisibility() {
        assertTrue(homePage.isVisible(), "Window is not visible");
    }

    private String getButtonText(JButton button) {
        return button.getText().replaceAll("\\<.*?\\>", "").trim();
    }
}
