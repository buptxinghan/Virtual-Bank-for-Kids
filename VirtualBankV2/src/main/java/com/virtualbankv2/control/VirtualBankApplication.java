package com.virtualbankv2.control;

import com.virtualbankv2.boundary.*;
import com.virtualbankv2.entity.*;

import java.util.ArrayList;
import java.util.List;

import static com.virtualbankv2.boundary.Reader.*;
import static com.virtualbankv2.entity.Task.totalcounter;

/**
 * VirtualBankApplication class is the main class that initializes and runs the virtual bank application.
 *
 * @version 4.0
 * @since 2024-04-13
 * @author Zhenghan Zhong
 */
public class VirtualBankApplication {
    /**
     * The current user account.
     */
    public static User currentUser;

    /**
     * Constructs a new VirtualBankApplication.
     * Initializes the application by reading data and setting up the login page.
     */
    public VirtualBankApplication() {

        new Reader();
        totalcounter = readTotalCounter("src/Data/Tasks.csv");
        currentUser = users.get(1);
        LoginPage login = new LoginPage();
        //HomePage hp = new HomePage();
    }

    /**
     * Retrieves the accounts associated with the current user.
     *
     * @param currentUser The current user.
     * @return A list of accounts belonging to the current user.
     */
    public List<Account> getCurrentUserAccounts(User currentUser) {
        List<Account> currentUserAccounts = new ArrayList<>();
        for (Account account : accounts) {
            if (account.getUsername().equals(currentUser.getUsername())){
                currentUserAccounts.add(account);
            }
        }

        return currentUserAccounts;
    }

    /**
     * The main method to start the virtual bank application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        VirtualBankApplication application = new VirtualBankApplication();
    }
}
