package com.virtualbankv2.control;

import com.virtualbankv2.entity.User;

import static com.virtualbankv2.boundary.Reader.users;
import static com.virtualbankv2.control.VirtualBankApplication.currentUser;

public class LoginController {
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
