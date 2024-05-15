package com.virtualbankv2.control;

import com.virtualbankv2.boundary.*;
import com.virtualbankv2.entity.*;

import java.util.ArrayList;
import java.util.List;

import static com.virtualbankv2.boundary.Reader.accounts;
import static com.virtualbankv2.boundary.Reader.users;
import static com.virtualbankv2.entity.Task.totalcounter;

public class VirtualBankApplication {
    public static User currentUser; // 表示当前进入的用户账户

    public VirtualBankApplication() {

        new Reader();
        totalcounter = 0;
        currentUser = users.get(1);
//        LoginPage login = new LoginPage();
        HomePage hp = new HomePage();


        //CreateTaskPage ct = new CreateTaskPage();
    }

    public List<Account> getCurrentUserAccounts(User currentUser) {

        List<Account> currentUserAccounts = new ArrayList<>();
        for (Account account : accounts) {
            if (account.getUsername().equals(currentUser.getUsername())){
                currentUserAccounts.add(account);
            }
        }

        return currentUserAccounts;
    }

    public static void main(String[] args) {
        VirtualBankApplication application = new VirtualBankApplication();
    }
}
