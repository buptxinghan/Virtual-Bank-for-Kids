package com.virtualbankv2.control;

import com.virtualbankv2.boundary.Reader;
import com.virtualbankv2.entity.Interest;
import com.virtualbankv2.entity.User;
import com.virtualbankv2.boundary.Writer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static com.virtualbankv2.boundary.Reader.users;
import static org.junit.jupiter.api.Assertions.*;

public class SignUpControllerTest {

    private SignUpController signUpController;

    @BeforeEach
    void setUp() {
        new Reader();
        new VirtualBankApplication();
        users = new ArrayList<>();
        users.add(new User("existingUser", "password"));
        signUpController = new SignUpController();

        UIManager.put("OptionPane.messageDialogTitle", "Test");
    }

    @Test
    void testEmptyUsername() {
        boolean result = signUpController.performSignUp("", "password", "password");
        assertFalse(result);
        assertEquals("Please enter a valid username.", CustomJOptionPane.getLastMessage());
    }

    @Test
    void testExistingUsername() {
        boolean result = signUpController.performSignUp("existingUser", "password", "password");
        assertFalse(result);
        assertEquals("Username already exists.", CustomJOptionPane.getLastMessage());
    }

    @Test
    void testEmptyPassword() {
        boolean result = signUpController.performSignUp("newUser", "", "");
        assertFalse(result);
        assertEquals("Password fields cannot be empty.", CustomJOptionPane.getLastMessage());
    }

    @Test
    void testPasswordMismatch() {
        boolean result = signUpController.performSignUp("newUser", "password", "differentPassword");
        assertFalse(result);
        assertEquals("Passwords do not match.", CustomJOptionPane.getLastMessage());
    }

    @Test
    void testPasswordTooShort() {
        boolean result = signUpController.performSignUp("newUser", "1234", "1234");
        assertFalse(result);
        assertEquals("Password must be at least 5 characters long.", CustomJOptionPane.getLastMessage());
    }

    @Test
    void testSuccessfulSignUp() {
        boolean result = signUpController.performSignUp("newUser", "password", "password");
        assertTrue(result);
        assertNotNull(users.stream().filter(user -> user.getUsername().equals("newUser")).findFirst().orElse(null));
    }

    public static class CustomJOptionPane extends JOptionPane {
        private static String lastMessage;

        public static String getLastMessage() {
            return lastMessage;
        }

        public static void showMessageDialog(Component parentComponent, Object message, String title, int messageType) {
            lastMessage = message.toString();
            System.out.println("Captured message: " + lastMessage);
        }

        public static int showOptionDialog(Component parentComponent, Object message, String title, int optionType, int messageType, Icon icon, Object[] options, Object initialValue) {
            lastMessage = message.toString();
            System.out.println("Captured option message: " + lastMessage);
            return JOptionPane.OK_OPTION;
        }
    }
}
