import com.virtualbankv2.entity.Account;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    @Test
    void testAccountConstructor() {
        String accountID = "123456789";
        String accountType = "Savings";
        String username = "JohnDoe";
        String password = "password123";
        double balance = 1000.0;
        String status = "Active";

        Account account = new Account(accountID, accountType, username, password, balance, status);

        assertNotNull(account);
        assertEquals(accountID, account.getAccountID());
        assertEquals(accountType, account.getAccountType());
        assertEquals(username, account.getUsername());
        assertEquals(password, account.getPassword());
        assertEquals(balance, account.getBalance());
        assertEquals(status, account.getStatus());
    }

    @Test
    void testAccountSetterGetterMethods() {
        Account account = new Account("123456789", "Savings", "JohnDoe", "password123", 1000.0, "Active");

        // Test setter methods
        account.setAccountID("987654321");
        account.setAccountType("Checking");
        account.setUsername("JaneDoe");
        account.setPassword("newpassword123");
        account.setBalance(1500.0);
        account.setStatus("Inactive");

        // Test getter methods
        assertEquals("987654321", account.getAccountID());
        assertEquals("Checking", account.getAccountType());
        assertEquals("JaneDoe", account.getUsername());
        assertEquals("newpassword123", account.getPassword());
        assertEquals(1500.0, account.getBalance());
        assertEquals("Inactive", account.getStatus());
    }

    @Test
    void testAccountToStringMethod() {
        String expectedString = "Account{accountID='123456789', accountType='Savings', username='JohnDoe', password='password123', balance=1000.0, status='Active'}";
        Account account = new Account("123456789", "Savings", "JohnDoe", "password123", 1000.0, "Active");
        assertEquals(expectedString, account.toString());
    }
}
