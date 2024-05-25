package com.virtualbankv2.boundary;

import com.virtualbankv2.control.VirtualBankApplication;
import com.virtualbankv2.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.lang.reflect.Field;

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
        users.add(new User("root", "111"));

        loginPage = new LoginPage();
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
        nameField.setText("root");
        passwordField.setText("111");

        loginButton.doClick();

        assertNotNull(VirtualBankApplication.currentUser, "Current user should be set after successful login");
        assertEquals("root", VirtualBankApplication.currentUser.getUsername());
    }

    @Test
    public void testLoginInvalidCredentials() {
        nameField.setText("wrongUser");
        passwordField.setText("wrongPass");

        VirtualBankApplication.currentUser = null;

        loginButton.doClick();

        assertNull(VirtualBankApplication.currentUser, "Current user should be null after failed login");
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