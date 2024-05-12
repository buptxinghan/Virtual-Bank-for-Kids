package com.virtualbankv2.boundary;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class SignUpPageTest {

    private static SignUpPage signUpPage;

    @BeforeAll
    public static void setUp() {
        signUpPage = new SignUpPage();
    }

    @Test
    public void testSignUpPageInitialization() {
        assertNotNull(signUpPage);
    }

    @Test
    public void testUserRegistrationSuccess() {
        JTextField nameField = signUpPage.getNameField();
        JTextField passwordField = signUpPage.getPasswordField();
        JTextField confirmPasswordField = signUpPage.getConfirmPasswordField();
        JButton submitButton = signUpPage.getSubmitButton();

        nameField.setText("TestUser");
        passwordField.setText("TestPassword");
        confirmPasswordField.setText("TestPassword");

        submitButton.doClick();

        assertTrue(signUpPage.isSuccessMessageShown());
        assertFalse(signUpPage.isErrorMessageShown());
    }

    @Test
    public void testPasswordMismatchError() {
        JTextField nameField = signUpPage.getNameField();
        JTextField passwordField = signUpPage.getPasswordField();
        JTextField confirmPasswordField = signUpPage.getConfirmPasswordField();
        JButton submitButton = signUpPage.getSubmitButton();

        nameField.setText("TestUser");
        passwordField.setText("TestPassword");
        confirmPasswordField.setText("MismatchedPassword");

        submitButton.doClick();

        assertFalse(signUpPage.isSuccessMessageShown());
        assertTrue(signUpPage.isErrorMessageShown());
    }
}
