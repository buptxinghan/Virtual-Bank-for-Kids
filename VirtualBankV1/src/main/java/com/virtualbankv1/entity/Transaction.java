package com.virtualbankv1.entity;

public class Transaction {

    private String transactionID;
    private String accountFrom;
    private String accountTo;
    private double amount;
    private String date;
    private String description;

    // 转账
    public Transaction(String transactionID, String accountFrom, String accountTo, double amount, String date, String description) {
        this.transactionID = transactionID;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
        this.date = date;
        this.description = description;
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

    public String getDate() {return date;}

    public String getDescription() {return description;}

    public void setDescription() {this.description = description;}

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionID='" + transactionID + '\'' +
                ", accountFrom='" + accountFrom + '\'' +
                ", accountTo='" + accountTo + '\'' +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
