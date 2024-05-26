package com.virtualbankv2.control;

import com.virtualbankv2.boundary.Reader;
import com.virtualbankv2.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.ArrayList;

import static com.virtualbankv2.boundary.Reader.users;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the functionality of the SignUpController class.
 * It includes tests for successful sign-up.
 *
 * @version 1.0
 * @since 2024-05-10
 * @author Ji Zheng
 */
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

    /**
     * Tests the performSignUp method for a successful sign-up.
     */
    @Test
    void testSuccessfulSignUp() {
        boolean result = signUpController.performSignUp("newUser", "password", "password");
        assertTrue(result);
        assertNotNull(users.stream().filter(user -> user.getUsername().equals("newUser")).findFirst().orElse(null));
    }
}
