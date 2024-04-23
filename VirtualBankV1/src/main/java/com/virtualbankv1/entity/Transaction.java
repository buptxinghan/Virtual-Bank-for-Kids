package com.virtualbankv1.entity;

public class Transaction {

    private String transactionID;
    private String accountFrom;
    private String accountTo;
    private double amount;

    // 转账
    public Transaction(String transactionID, String userFrom, String userTo, double amount) {
        this.transactionID = transactionID;
        this.accountFrom = userFrom;
        this.accountTo = userTo;
        this.amount = amount;
    }

    // Getter and Setter methods
    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(String userFrom) {
        this.accountFrom = userFrom;
    }

    public String getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(String userTo) {
        this.accountTo = userTo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
