package com.virtualbankv2.entity;

/**
 * The Account class represents a bank account with relevant details such as
 * account ID, type, username, password, balance, and status.
 *
 * @version 1.0
 * @since 2024-04-13
 * @author Zhenghan Zhong
 */
public class Account {
    /**
     * The unique identifier for the account.
     */
    private String accountID;

    /**
     * The type of the account (e.g., Savings, Checking).
     */
    private String accountType;

    /**
     * The username associated with the account.
     */
    private String username;

    /**
     * The password for accessing the account.
     */
    private String password;

    /**
     * The current balance of the account.
     */
    private double balance;

    /**
     * The status of the account (e.g., Active, Frozen, Closed).
     */
    private String status;

    /**
     * Constructs a new Account with the specified details.
     *
     * @param accountID   the unique identifier for the account
     * @param accountType the type of the account
     * @param username    the username associated with the account
     * @param password    the password for accessing the account
     * @param balance     the current balance of the account
     * @param status      the status of the account
     */
    public Account(String accountID, String accountType, String username, String password, double balance, String status) {
        this.accountID = accountID;
        this.accountType = accountType;
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.status = status;
    }

    /**
     * Returns the account ID.
     *
     * @return the account ID
     */
    public String getAccountID() {
        return accountID;
    }

    /**
     * Sets the account ID.
     *
     * @param accountID the new account ID
     */
    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    /**
     * Returns the account type.
     *
     * @return the account type
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Sets the account type.
     *
     * @param accountType the new account type
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    /**
     * Returns the username associated with the account.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username associated with the account.
     *
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password for accessing the account.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for accessing the account.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the current balance of the account.
     *
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the current balance of the account.
     *
     * @param balance the new balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Returns the status of the account.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the account.
     *
     * @param status the new status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns a string representation of the Account object.
     *
     * @return a string representation of the Account object
     */
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