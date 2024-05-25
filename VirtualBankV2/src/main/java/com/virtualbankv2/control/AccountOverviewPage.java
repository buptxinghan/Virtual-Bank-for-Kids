package com.virtualbankv2.control;

import com.virtualbankv2.boundary.AccountOverviewUI;
import com.virtualbankv2.entity.Account;
import com.virtualbankv2.entity.User;
import static com.virtualbankv2.boundary.Reader.accounts;
import static com.virtualbankv2.boundary.Reader.users;
import static com.virtualbankv2.control.VirtualBankApplication.currentUser;

/**
 * The AccountOverviewPage class is part of the control package of the VirtualBankV2 application.
 * It is responsible for displaying the account information of the current user.
 *
 * @author Botong Wu
 * @version 1.0
 * @since 2024-04-13
 */
public class AccountOverviewPage{
    public AccountOverviewUI ui;

    /**
     * Constructor for the AccountOverviewPage.
     * Initializes the UI and checks if the current user exists.
     * If the user exists, it displays the account information.
     */
    public AccountOverviewPage() {
        this.ui = new AccountOverviewUI();
        boolean userExists = checkUserExists(currentUser.getUsername());
        if (userExists) {
            displayAccountInfo(currentUser.getUsername());
        }
    }

    /**
     * Checks if the user exists in the system.
     *
     * @param username The username of the user.
     * @return Returns true if the user exists, false otherwise.
     */
    private boolean checkUserExists(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Displays the account information for the given username.
     *
     * @param username The username of the user.
     */
    private void displayAccountInfo(String username) {
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                ui.updatePage(account);
            }
        }
    }
}
