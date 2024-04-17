package com.virtualbankv1.control;
// Account management interface class

import com.virtualbankv1.boundary.AccountInformationPage;
import com.virtualbankv1.entity.Account;

public class AccountManager {

    public void displayAccountInformation(Account account) {
        AccountInformationPage accountInformationPage = new AccountInformationPage();
        accountInformationPage.accountInformationGUI(account);
    }

    public void transferIn(Account account, double amount) {
        account.setBalance(account.getBalance() + amount);
    }

    public void withdraw(Account account, double amount) {
        account.setBalance(account.getBalance() - amount);
    }

    public void freezeAccount(Account account) {
        account.setStatus("Frozen");
    }

    public void deleteAccount(Account account) {
        account.setStatus("Deleted");
    }

}

