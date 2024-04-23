package com.virtualbankv1.control;

import com.virtualbankv1.boundary.*;
import com.virtualbankv1.entity.*;

import java.util.ArrayList;
import java.util.List;

import static com.virtualbankv1.boundary.Reader.accounts;
import static com.virtualbankv1.boundary.Reader.users;

public class VirtualBankApplication {
    public static User currentUser; // 表示当前进入的用户账户

    public VirtualBankApplication() {
        new Reader();

        currentUser = users.get(1);
        //List<Account> tempAccounts = getCurrentUserAccounts(currentUser);

        //Account tempAccount = accounts.get(0);
        //AccountManager accountManager = new AccountManager();
        //accountManager.displayAccountInformation(tempAccount);
        //LoginPage login = new LoginPage();
        HomePage hp = new HomePage("Zhong Zhenghan");

//        Writer writer = new Writer();
//        writer.writeUsers("src/Data/Users.csv", users);
//        writer.writeAccounts("src/Data/Accounts.csv", accounts);
//        writer.writeTransactions("src/Data/Transactions.csv", transactions);
//        writer.writeTasks("src/Data/Tasks.csv", tasks);
//        writer.writeGoals("src/Data/Goals.csv", goals);
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

    // 主函数，用于测试
    public static void main(String[] args) {
        VirtualBankApplication application = new VirtualBankApplication();
        // 测试系统功能
    }
}
