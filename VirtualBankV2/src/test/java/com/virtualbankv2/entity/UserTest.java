package com.virtualbankv2.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the functionality of the User class.
 * It includes tests for the constructor, getter, and setter methods.
 *
 * @version 1.0
 * @since 2024-05-10
 * @author Ji Zheng
 */
public class UserTest {

    /**
     * Tests the constructor of the User class.
     */
    @Test
    void testUserConstructor() {
        User user = new User("testuser", "password123");
        assertNotNull(user);
        assertEquals("testuser", user.getUsername());
        assertEquals("password123", user.getPassword());
    }

    /**
     * Tests the getter and setter methods of the User class.
     */
    @Test
    void testUserGettersAndSetters() {
        User user = new User();
        assertNotNull(user);

        user.setUsername("newuser");
        assertEquals("newuser", user.getUsername());

        user.setPassword("newpassword");
        assertEquals("newpassword", user.getPassword());
    }
}
