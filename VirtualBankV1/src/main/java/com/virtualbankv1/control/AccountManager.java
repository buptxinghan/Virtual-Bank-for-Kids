package com.virtualbankv1.control;
// Account management interface class

import com.virtualbankv1.boundary.AccountInformationPage;
import com.virtualbankv1.entity.Account;

public class AccountManager {

    public void displayAccountInformation(Account account) {
        new AccountInformationPage(account);
    }

    public void transferIn(Account account, double amount) {
        account.setBalance(account.getBalance() + amount);
    }

    public boolean withdraw(Account account, double amount) {
        if (account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            return true;
        }
        else {
            return false;
        }
    }

    public void freezeAccount(Account account) {
        account.setStatus("Frozen");
    }

    public void deleteAccount(Account account) {
        account.setStatus("Deleted");
    }

}

