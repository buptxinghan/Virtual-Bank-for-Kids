package com.virtualbankv1.entity;

public class Transaction {

    private String transactionID;
    private String userFrom;
    private String userTo;
    private double amount;

    // 转账
    public Transaction(String transactionID, String userFrom, String userTo, double amount) {
        this.transactionID = transactionID;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.amount = amount;
    }

    // Getter and Setter methods
    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(String userFrom) {
        this.userFrom = userFrom;
    }

    public String getUserTo() {
        return userTo;
    }

    public void setUserTo(String userTo) {
        this.userTo = userTo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
