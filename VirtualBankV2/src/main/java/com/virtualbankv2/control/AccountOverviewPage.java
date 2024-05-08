package com.virtualbankv2.control;


import com.virtualbankv2.boundary.AccountOverviewUI;
import com.virtualbankv2.entity.Account;
import com.virtualbankv2.entity.User;


import static com.virtualbankv2.boundary.Reader.accounts;
import static com.virtualbankv2.boundary.Reader.users;
import static com.virtualbankv2.control.VirtualBankApplication.currentUser;

public class AccountOverviewPage{
    public AccountOverviewUI ui;

    public AccountOverviewPage() {
        this.ui = new AccountOverviewUI();
        boolean userExists = checkUserExists(currentUser.getUsername());
        if (userExists) {
            displayAccountInfo(currentUser.getUsername());
        }
    }

    // Check if the user exists
    private boolean checkUserExists(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    // Display the account information for the given username
    private void displayAccountInfo(String username) {
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                ui.updatePage(account);
            }
        }
    }
}