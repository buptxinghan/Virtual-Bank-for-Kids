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
}
