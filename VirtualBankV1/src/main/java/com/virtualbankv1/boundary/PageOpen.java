package com.virtualbankv1.boundary;

import com.virtualbankv1.entity.Account;

public class PageOpen {
    // Open the account page
    public void openAccount() {
        OpenAccountPage openAccount = new OpenAccountPage();
        //openAccount.setVisible(true);
    }

    // Show the account information
    public void showAccountInfo(Account account) {
        AccountInformationPage am = new AccountInformationPage(account);
    }
}
