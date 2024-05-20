package com.virtualbankv2.control;

import com.virtualbankv2.entity.Interest;
import com.virtualbankv2.entity.User;
import com.virtualbankv2.boundary.Writer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.virtualbankv2.boundary.Reader.users;

public class SignUpController {

    public SignUpController() {}

    public boolean performSignUp(String userName, String password, String confirmPassword) {
        if (userName.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            return false;
        }

        if (!password.equals(confirmPassword)) {
            return false;
        }

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
