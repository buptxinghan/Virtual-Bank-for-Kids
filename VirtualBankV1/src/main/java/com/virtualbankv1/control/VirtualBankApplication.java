package com.virtualbankv1.control;

import com.virtualbankv1.boundary.Reader;
import com.virtualbankv1.entity.*;

import java.util.List;

public class VirtualBankApplication {
    protected List<User> users; // 存储所有用户账户的信息
    protected List<Account> accounts;
    protected List<Transaction> transactions; // 存储所有账户的交易记录
    protected List<Task> tasks; // 存储所有设置的任务
    protected List<Goal> goals; // 存储所有用户设定的长期目标
    protected List<SupportSystem> support; // 提供用户支持和帮助信息

    protected User currentUser; // 表示当前进入的用户账户

    public VirtualBankApplication() {
        Reader reader = new Reader();
        users = reader.readUsers("src/Data/User.csv");
        accounts = reader.readAccounts("src/Data/Accounts.csv");
        transactions = reader.readTransactions("src/Data/Transactions.csv");
        tasks = reader.readTasks("src/Data/Tasks.csv");
        goals = reader.readGoals("src/Data/Goals.csv");
        support = reader.readSupport("src/Data/Support.csv");

        Account tempAccount = accounts.get(0);
        AccountManager accountManager = new AccountManager();
        accountManager.displayAccountInformation(tempAccount);
        //Home home = new Home("zzh");
    }

    // 主函数，用于测试
    public static void main(String[] args) {
        VirtualBankApplication application = new VirtualBankApplication();
        // 测试系统功能
    }
}
