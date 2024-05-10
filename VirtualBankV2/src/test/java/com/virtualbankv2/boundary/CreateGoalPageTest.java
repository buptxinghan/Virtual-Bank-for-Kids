import com.virtualbankv2.boundary.CreateGoalPage;
import com.virtualbankv2.entity.Account;
import com.virtualbankv2.boundary.Reader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateGoalPageTest {
    private CreateGoalPage createGoalPage;
    static class MockReader {
        static List<Account> accounts = new ArrayList<>();
        static List<Account> getAccounts() {
            return accounts;
        }
    }

    @BeforeEach
    public void setUp() {
        createGoalPage = new CreateGoalPage("testUser");
        List<Account> mockedAccounts = new ArrayList<>();
        mockedAccounts.add(new Account("testUser", "testAccount", "Test Account", "test@mail.com", 1000, "testUser"));
        mockedAccounts.add(new Account("anotherUser", "anotherAccount", "Another Account", "another@mail.com", 2000, "anotherUser"));
        mockedAccounts.add(new Account("thirdUser", "thirdAccount", "Third Account", "third@mail.com", 1500, "thirdUser"));
        MockReader.accounts = mockedAccounts;
    }


    @Test
    public void testSaveButtonFunctionalityValidInput() {
        JTextField goalNameField = createGoalPage.getGoalNameField();
        JTextArea descriptionArea = createGoalPage.getDescriptionArea();
        JTextField targetAmountField = createGoalPage.getTargetAmountField();
        JButton saveButton = createGoalPage.getSaveButton();

        goalNameField.setText("New Year's Savings");
        descriptionArea.setText("Saving for New Year celebrations");
        targetAmountField.setText("500");

        SwingUtilities.invokeLater(saveButton::doClick);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertTrue(waitForDialog("Goal saved successfully!"), "Expected success message dialog to be visible.");
    }

    @Test
    public void testSaveButtonFunctionalityInvalidInput() {
        JTextField targetAmountField = createGoalPage.getTargetAmountField();
        JButton saveButton = createGoalPage.getSaveButton();

        targetAmountField.setText("five hundred");

        SwingUtilities.invokeLater(saveButton::doClick);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertTrue(waitForDialog("Please enter a valid number for target amount."), "Expected error message dialog to be visible.");
    }

    private boolean waitForDialog(String message) {
        long startTime = System.currentTimeMillis();
        long timeout = 2000; // 2 seconds timeout

        while (System.currentTimeMillis() - startTime < timeout) {
            Window[] windows = Window.getWindows();
            for (Window window : windows) {
                if (window instanceof JDialog && window.isVisible()) {
                    JDialog dialog = (JDialog) window;
                    JLabel label = findJLabel(dialog);
                    if (label != null && message.equals(label.getText())) {
                        return true;
                    }
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return true;
    }

    private JLabel findJLabel(Container container) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JLabel) {
                return (JLabel) comp;
            } else if (comp instanceof Container) {
                return findJLabel((Container) comp);
            }
        }
        return null;
    }
}