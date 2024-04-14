package com.virtualbankv1;

import java.util.List;

public class Account {
    private String accountID;
    private String accountType;
    private String username;
    private String password;
    private double balance;
    private String status;

    public Account(){}
    public Account(String accountID, String accountType, String username, String password, double balance, String status) {
        this.accountID = accountID;
        this.accountType = accountType;
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.status = status;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Account getAccountById(List<Account> accounts, String accountID) {
        for (Account account : accounts) {
            if (account.getAccountID().equals(accountID)) {
                return account; // 找到匹配的accountID，返回对应的Account对象
            }
        }
        return null; // 如果没有找到，返回null
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountID='" + accountID + '\'' +
                ", accountType='" + accountType + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", status='" + status + '\'' +
                '}';
    }
}