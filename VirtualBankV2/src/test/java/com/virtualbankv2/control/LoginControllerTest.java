package com.virtualbankv2.control;

import com.virtualbankv2.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.virtualbankv2.boundary.Reader.users;
import static com.virtualbankv2.control.VirtualBankApplication.currentUser;
import static org.junit.jupiter.api.Assertions.*;

public class LoginControllerTest {

    private LoginController loginController;

    @BeforeEach
    void setUp() {
        users = new ArrayList<>();
        users.add(new User("testUser", "password"));
        users.add(new User("anotherUser", "anotherPassword"));
        loginController = new LoginController();
        currentUser = null; // Reset currentUser before each test
    }

    @Test
    void testValidCredentials() {
        boolean result = loginController.checkCredentials("testUser", "password");
        assertTrue(result, "Valid credentials should return true");
        assertNotNull(currentUser, "Current user should not be null after successful login");
        assertEquals("testUser", currentUser.getUsername(), "Current user username should match");
    }

    @Test
    void testInvalidCredentials() {
        boolean result = loginController.checkCredentials("testUser", "wrongPassword");
        assertFalse(result, "Invalid credentials should return false");
        assertNull(currentUser, "Current user should be null after failed login");
    }

    @Test
    void testNonExistentUser() {
        boolean result = loginController.checkCredentials("nonExistentUser", "password");
        assertFalse(result, "Non-existent user should return false");
        assertNull(currentUser, "Current user should be null after failed login");
    }

    @Test
    void testEmptyUsername() {
        boolean result = loginController.checkCredentials("", "password");
        assertFalse(result, "Empty username should return false");
        assertNull(currentUser, "Current user should be null after failed login");
    }

    @Test
    void testEmptyPassword() {
        boolean result = loginController.checkCredentials("testUser", "");
        assertFalse(result, "Empty password should return false");
        assertNull(currentUser, "Current user should be null after failed login");
    }

    @Test
    void testEmptyCredentials() {
        boolean result = loginController.checkCredentials("", "");
        assertFalse(result, "Empty credentials should return false");
        assertNull(currentUser, "Current user should be null after failed login");
    }
}
