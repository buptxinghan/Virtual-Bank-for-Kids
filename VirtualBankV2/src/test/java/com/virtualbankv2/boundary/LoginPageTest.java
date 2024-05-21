package com.virtualbankv2.boundary;
import com.virtualbankv2.boundary.LoginPage;
import com.virtualbankv2.control.VirtualBankApplication;
import com.virtualbankv2.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

import static com.virtualbankv2.boundary.Reader.users;
import static org.junit.jupiter.api.Assertions.*;

public class LoginPageTest {

    private LoginPage loginPage;
    private JFrame frame;
    private JTextField nameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        new Reader();
        new VirtualBankApplication();
        // Prepare mock data before creating a LoginPage instance
        users.add(new User("root", "111"));  // Add a test user

        loginPage = new LoginPage();  // Create a LoginPage instance
        frame = retrieveAccessibleJFrame(loginPage);
        nameField = retrieveAccessibleField(loginPage, "nameField", JTextField.class);
        passwordField = retrieveAccessibleField(loginPage, "passwordField", JPasswordField.class);
        loginButton = retrieveAccessibleField(loginPage, "loginButton", JButton.class);
    }

    @Test
    public void testLoginPageComponents() {
        assertNotNull(loginPage);
        assertTrue(frame.isVisible(), "Login frame should be visible upon initialization");
        assertEquals("Bank Login System", frame.getTitle());
    }

    @Test
    public void testLoginFunctionality() {
        // Simulate user inputting valid username and password
        nameField.setText("root");
        passwordField.setText("111");

        // Simulate clicking the login button
        loginButton.doClick();

        // Verify that the currentUser is set correctly after a successful login
        assertNotNull(VirtualBankApplication.currentUser, "Current user should be set after successful login");
        assertEquals("root", VirtualBankApplication.currentUser.getUsername());
    }

    @Test
    public void testLoginInvalidCredentials() {
        // Simulate user inputting invalid username and password
        nameField.setText("wrongUser");
        passwordField.setText("wrongPass");

        // Simulate clicking the login button
        loginButton.doClick();

        // Verify that the currentUser is still null after a failed login
        assertNull(VirtualBankApplication.currentUser, "Error: Incorrect Username or Password!");
    }

    private <T> T retrieveAccessibleField(Object obj, String fieldName, Class<T> fieldType) throws NoSuchFieldException, IllegalAccessException {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return fieldType.cast(field.get(obj));
    }

    private JFrame retrieveAccessibleJFrame(Object obj) throws NoSuchFieldException, IllegalAccessException {
        Field frameField = obj.getClass().getDeclaredField("frame");
        frameField.setAccessible(true);
        return (JFrame) frameField.get(obj);
    }
}