package com.virtualbankv2.control;

import com.virtualbankv2.entity.User;

import static com.virtualbankv2.boundary.Reader.users;
import static com.virtualbankv2.control.VirtualBankApplication.currentUser;

/**
 * The {@code LoginController} class provides the logic to check user credentials
 * during the login process for the Virtual Bank application.
 *
 * @version 1.0
 * @since 2024-05-23
 * @author Tianzhi Li
 *
 */
public class LoginController {

    /**
     * Checks the provided credentials against the list of registered users.
     *
     * @param userName the username entered by the user
     * @param password the password entered by the user
     * @return {@code true} if the credentials are valid and a user is found; {@code false} otherwise
     */
    public boolean checkCredentials(String userName, String password) {
        for (User user : users) {
            if (user.getUsername().equals(userName) && user.getPassword().equals(password)) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }
}
