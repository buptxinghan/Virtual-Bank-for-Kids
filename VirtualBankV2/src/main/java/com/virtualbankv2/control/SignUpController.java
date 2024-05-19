package com.virtualbankv2.control;

import com.virtualbankv2.entity.User;
import com.virtualbankv2.boundary.Reader;
import com.virtualbankv2.boundary.Writer;

import java.util.List;

public class SignUpController {

    private List<User> users;

    public SignUpController() {
        this.users = Reader.users;
    }

    public boolean performSignUp(String userName, String password, String confirmPassword) {
        if (userName.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            return false;
        }

        if (!password.equals(confirmPassword)) {
            return false;
        }

        User newUser = new User(userName, password);
        Writer writer = new Writer();
        writer.writeSingleUser(newUser);
        users.add(newUser);
        return true;
    }
}
