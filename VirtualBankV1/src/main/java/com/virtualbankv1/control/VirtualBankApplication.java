package com.virtualbankv1.control;

import com.virtualbankv1.boundary.HomePage;
import com.virtualbankv1.boundary.Reader;
import com.virtualbankv1.boundary.Writer;
import com.virtualbankv1.entity.*;

import java.util.ArrayList;
import java.util.List;

public class VirtualBankApplication {
    public static List<User> users; // 存储所有用户账户的信息
    public static List<Account> accounts;
    public static List<Transaction> transactions; // 存储所有账户的交易记录
    public static List<Task> tasks; // 存储所有设置的任务
    public static List<Goal> goals; // 存储所有用户设定的长期目标
    public static List<SupportSystem> support; // 提供用户支持和帮助信息

    protected User currentUser; // 表示当前进入的用户账户

    public VirtualBankApplication() {
        new Reader();

        currentUser = users.get(0);
        List<Account> tempAccounts = getCurrentUserAccounts(currentUser);

        Account tempAccount = accounts.get(0);
        AccountManager accountManager = new AccountManager();
        accountManager.displayAccountInformation(tempAccount);
        HomePage home = new HomePage("zzh");  

        Writer writer = new Writer();
//        writer.writeUsers("src/Data/Users.csv", users);
        writer.writeAccounts("src/Data/Accounts.csv", accounts);
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
