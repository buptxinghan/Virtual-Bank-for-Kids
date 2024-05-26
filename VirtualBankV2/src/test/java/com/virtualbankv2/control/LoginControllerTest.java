package com.virtualbankv2.control;

import com.virtualbankv2.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.virtualbankv2.boundary.Reader.users;
import static com.virtualbankv2.control.VirtualBankApplication.currentUser;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the functionality of the LoginController class.
 * It includes tests for validating user credentials, handling non-existent users,
 * and checking for empty usernames and passwords.
 *
 * @version 1.0
 * @since 2024-05-10
 * @author Ji Zheng
 */
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

    /**
     * Tests the checkCredentials method with valid credentials.
     */
    @Test
    void testValidCredentials() {
        boolean result = loginController.checkCredentials("testUser", "password");
        assertTrue(result, "Valid credentials should return true");
        assertNotNull(currentUser, "Current user should not be null after successful login");
        assertEquals("testUser", currentUser.getUsername(), "Current user username should match");
    }

    /**
     * Tests the checkCredentials method with invalid credentials.
     */
    @Test
    void testInvalidCredentials() {
        boolean result = loginController.checkCredentials("testUser", "wrongPassword");
        assertFalse(result, "Invalid credentials should return false");
        assertNull(currentUser, "Current user should be null after failed login");
    }

    /**
     * Tests the checkCredentials method with a non-existent user.
     */
    @Test
    void testNonExistentUser() {
        boolean result = loginController.checkCredentials("nonExistentUser", "password");
        assertFalse(result, "Non-existent user should return false");
        assertNull(currentUser, "Current user should be null after failed login");
    }

    /**
     * Tests the checkCredentials method with an empty username.
     */
    @Test
    void testEmptyUsername() {
        boolean result = loginController.checkCredentials("", "password");
        assertFalse(result, "Empty username should return false");
        assertNull(currentUser, "Current user should be null after failed login");
    }

    /**
     * Tests the checkCredentials method with an empty password.
     */
    @Test
    void testEmptyPassword() {
        boolean result = loginController.checkCredentials("testUser", "");
        assertFalse(result, "Empty password should return false");
        assertNull(currentUser, "Current user should be null after failed login");
    }

    /**
     * Tests the checkCredentials method with empty credentials.
     */
    @Test
    void testEmptyCredentials() {
        boolean result = loginController.checkCredentials("", "");
        assertFalse(result, "Empty credentials should return false");
        assertNull(currentUser, "Current user should be null after failed login");
    }
}
