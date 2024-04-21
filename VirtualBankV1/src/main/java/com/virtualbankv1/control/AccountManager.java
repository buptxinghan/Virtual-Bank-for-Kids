package com.virtualbankv1.control;
// Account management interface class

import com.virtualbankv1.boundary.AccountInformationPage;
import com.virtualbankv1.entity.Account;

import java.util.List;

public class AccountManager {

    public void displayAccountInformation(Account account) {
        new AccountInformationPage(account);
    }

    public Account getAccountById(List<Account> accounts, String accountID) {
        for (Account account : accounts) {
            if (account.getAccountID().equals(accountID)) {
                return account; // 找到匹配的accountID，返回对应的Account对象
            }
        }
        return null; // 如果没有找到，返回null
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

    public void unfreezeAccount(Account account) {
        account.setStatus("Active");
    }

    public void deleteAccount(Account account) {
        account.setStatus("Deleted");
    }

    public boolean isFrozen(Account account) {
        return account.getStatus().equals("Frozen");
    }

    public boolean isDeleted(Account account) {
        return account.getStatus().equals("Deleted");
    }

}

