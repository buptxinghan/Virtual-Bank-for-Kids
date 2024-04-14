package com.virtualbankv1;

public class Transaction {

    // 存款
    public boolean deposit(SoftwareAccount softwareAccount, double amount) {
        // 存款逻辑
        // 更新账户余额
        // 记录交易
        return true;
    }

    // 取款
    public boolean withdraw(SoftwareAccount softwareAccount, double amount) {
        // 取款逻辑
        // 更新账户余额
        // 记录交易
        return true;
    }

    // 转账
    public boolean transfer(SoftwareAccount fromSoftwareAccount, SoftwareAccount toSoftwareAccount, double amount) {
        // 转账逻辑
        // 更新两个账户的余额
        // 记录交易
        return true;
    }
}
