package com.virtualbankv2.boundary;
import com.virtualbankv2.entity.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    void testUserConstructor() {
        User user = new User("testuser", "password123");
        assertNotNull(user);
        assertEquals("testuser", user.getUsername());
        assertEquals("password123", user.getPassword());
    }

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
