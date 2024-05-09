package com.virtualbankv2.control;

import com.virtualbankv2.entity.Account;

import java.util.ArrayList;
import java.util.List;

import static com.virtualbankv2.boundary.Reader.accounts;

public class InterestController {

    public void getInterest() {
        List<Account> tempAccounts = getSavingAccounts();
        for (Account tempAccount : tempAccounts) {
            
        }
    }

    public List<Account> getSavingAccounts() {
        List<Account> tempAccounts = new ArrayList<>();

        for (Account tempAccount : accounts) {
            if (tempAccount.getAccountType().equals("Saving Account")) {
                tempAccounts.add(tempAccount);
            }
        }

        return  tempAccounts;
    }
}
