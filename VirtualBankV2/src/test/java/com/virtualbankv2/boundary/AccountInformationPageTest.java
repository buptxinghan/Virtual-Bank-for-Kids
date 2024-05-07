import com.virtualbankv2.boundary.AccountInformationPage;
import com.virtualbankv2.entity.Account;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.Component;
import java.awt.Container;

import static org.junit.jupiter.api.Assertions.*;

public class AccountInformationPageTest {

    @Test
    void testAccountInformationPageConstructor() {
        Account account = new Account("123456789", "JohnDoe", "Savings", "Active", 1000.00, "password");
        AccountInformationPage page = new AccountInformationPage(account);
        assertNotNull(page);
        assertNotNull(page.getTitle());
        assertNotNull(page.getContentPane());
        assertNotNull(page.getAccountBalanceLabel());
        assertEquals("Account Information", page.getTitle());
    }

    @Test
    void testAccountInformationPageComponents() {
        Account account = new Account("123456789", "JohnDoe", "Savings", "Active", 1000.00, "password");
        AccountInformationPage page = new AccountInformationPage(account);
        assertNotNull(page);

        Container contentPane = page.getContentPane(); // Get the contentPane of the JFrame
        assertEquals(3, contentPane.getComponentCount()); // Check if there are 3 panels: North, Center, South

        // Check if components are added in the Center panel
        Component centerPanel = contentPane.getComponent(1); // Assuming the center panel is the second component
        assertTrue(centerPanel instanceof JPanel);
        Component[] centerComponents = ((JPanel) centerPanel).getComponents();
        System.out.println("Number of components in Center panel: " + centerComponents.length); // Debugging output
        for (Component component : centerComponents) {
            System.out.println("Component in Center panel: " + component); // Debugging output
        }
        // Print out the details of each component in the center panel
        for (Component component : centerComponents) {
            System.out.println(component.getClass().getName() + ": " + component.getName());
        }
    }

    @Test
    void testAccountInformationPageBalanceLabel() {
        Account account = new Account("123456789", "JohnDoe", "Savings", "Active", 1000.00, "password");
        AccountInformationPage page = new AccountInformationPage(account);
        assertNotNull(page);

        JLabel balanceLabel = page.getAccountBalanceLabel();
        assertNotNull(balanceLabel);

        assertEquals("<html><font color='Red' style='font-size: 20px;'>1,000.00</font></html>", balanceLabel.getText());
    }
}