package com.virtualbankv2.entity;

public class Account {
    private String accountID;
    private String accountType;
    private String username;
    private String password;
    private double balance;
    private String status;

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