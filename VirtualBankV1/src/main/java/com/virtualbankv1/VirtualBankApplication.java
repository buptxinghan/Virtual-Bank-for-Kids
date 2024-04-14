package com.virtualbankv1;

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
        users = reader.readUsers("src/main/Data/User.csv");
        accounts = reader.readAccounts("src/main/Data/Accounts.csv");
        transactions = reader.readTransactions("src/main/Data/Transactions.csv");
        tasks = reader.readTasks("src/main/Data/Tasks.csv");
        goals = reader.readGoals("src/main/Data/Goals.csv");
        support = reader.readSupport("src/main/Data/Support.csv");

        Account tempAccount = accounts.get(0);
        AccountManager accountManager = new AccountManager(tempAccount);
    }

    // ... 其他必要的方法

    // 主函数，用于测试
    public static void main(String[] args) {
        VirtualBankApplication application = new VirtualBankApplication();

        // 测试系统功能
    }
}
