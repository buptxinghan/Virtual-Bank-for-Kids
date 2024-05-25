package com.virtualbankv2.control;

import com.virtualbankv2.entity.Interest;
import com.virtualbankv2.entity.User;
import com.virtualbankv2.boundary.Writer;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.virtualbankv2.boundary.Reader.users;

/**
 * The {@code SignUpController} class handles the logic for the user sign-up process
 * in the Virtual Bank application.
 *
 * @version 2.0
 * @since 2024-04-20
 */
public class SignUpController {

    /**
     * Constructs a new {@code SignUpController} object.
     */
    public SignUpController() {}

    /**
     * Performs the sign-up operation for a new user. Validates the input fields and
     * creates a new user if the inputs are valid.
     *
     * @param userName the username entered by the user
     * @param password the password entered by the user
     * @param confirmPassword the password confirmation entered by the user
     * @return {@code true} if the sign-up is successful; {@code false} otherwise
     */
    public boolean performSignUp(String userName, String password, String confirmPassword) {
        if (userName.isEmpty()) {
            JOptionPane.showOptionDialog(
                    null,
                    "Please enter a valid username.",
                    "Error",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE,
                    null,
                    new String[]{"OK"},
                    "OK"
            );
            return false;
        } else {
            for (User user : users) {
                if (user.getUsername().equals(userName)) {
                    JOptionPane.showOptionDialog(
                            null,
                            "Username already exists.",
                            "Error",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.ERROR_MESSAGE,
                            null,
                            new String[]{"OK"},
                            "OK"
                    );
                    return false;
                }
            }
            if (password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showOptionDialog(
                        null,
                        "Password fields cannot be empty.",
                        "Error",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.ERROR_MESSAGE,
                        null,
                        new String[]{"OK"},
                        "OK"
                );
                return false;
            } else if (!password.equals(confirmPassword)) {
                JOptionPane.showOptionDialog(
                        null,
                        "Passwords do not match.",
                        "Error",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.ERROR_MESSAGE,
                        null,
                        new String[]{"OK"},
                        "OK"
                );
                return false;
            } else if (password.equals(confirmPassword)) {
                if (password.length() < 5) {
                    JOptionPane.showOptionDialog(
                            null,
                            "Password must be at least 5 characters long.",
                            "Error",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.ERROR_MESSAGE,
                            null,
                            new String[]{"OK"},
                            "OK"
                    );
                    return false;
                } else {
                    User newUser = new User(userName, password);

                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    String currentDate = dateFormatter.format(LocalDate.now());
                    Interest interest = new Interest(newUser.getUsername(), currentDate);

                    Writer writer = new Writer();
                    writer.writeSingleUser(newUser);
                    writer.writeSingleInterest(interest);
                    users.add(newUser);
                    return true;
                }
            }
        }
        return true;
    }
}
