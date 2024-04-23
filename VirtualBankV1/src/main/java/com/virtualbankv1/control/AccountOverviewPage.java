package com.virtualbankv1.control;

import com.virtualbankv1.boundary.AccountInformationPage;
import com.virtualbankv1.boundary.AccountOverviewUI;
import com.virtualbankv1.boundary.OpenAccountPage;
import com.virtualbankv1.entity.Account;
import com.virtualbankv1.entity.User;

import javax.swing.*;
import java.awt.*;

import static com.virtualbankv1.boundary.Reader.accounts;
import static com.virtualbankv1.boundary.Reader.users;
import static com.virtualbankv1.control.VirtualBankApplication.currentUser;

public class AccountOverviewPage{
    public AccountOverviewUI ui;

    public AccountOverviewPage() {
        this.ui = new AccountOverviewUI(this);
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
                ui.updatePage(account, Color.WHITE);
            }
        }
    }

    // Open the account page
    public void openAccount() {
        OpenAccountPage openAccount = new OpenAccountPage();
        openAccount.setVisible(true);
    }

    // Show the account information
    public void showAccountInfo(Account account) {
        AccountInformationPage am = new AccountInformationPage(account);
    }

    // Create a new JPanel with some default settings
    public JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        return panel;
    }
}