package com.virtualbankv2.entity;

/**
 * The Transaction class represents a financial transaction between two accounts in the system.
 *
 * @version 2.0
 * @since 2024-04-13
 * @author Zhenghan Zhong
 */
public class Transaction {
    /**
     * The ID of the transaction.
     */
    private String transactionID;

    /**
     * The ID of the account from which the money is transferred.
     */
    private String accountFrom;

    /**
     * The ID of the account to which the money is transferred.
     */
    private String accountTo;

    /**
     * The amount of money transferred.
     */
    private double amount;

    /**
     * The date of the transaction.
     */
    private String date;

    /**
     * The description of the transaction.
     */
    private String description;

    /**
     * Constructs a new Transaction with the specified details.
     *
     * @param transactionID the ID of the transaction
     * @param accountFrom   the ID of the account from which the money is transferred
     * @param accountTo     the ID of the account to which the money is transferred
     * @param amount        the amount of money transferred
     * @param date          the date of the transaction
     * @param description   the description of the transaction
     */
    public Transaction(String transactionID, String accountFrom, String accountTo, double amount, String date, String description) {
        this.transactionID = transactionID;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    /**
     * Returns the ID of the transaction.
     *
     * @return the ID of the transaction
     */
    public String getTransactionID() {
        return transactionID;
    }

    /**
     * Sets the ID of the transaction.
     *
     * @param transactionID the new ID of the transaction
     */
    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    /**
     * Returns the ID of the account from which the money is transferred.
     *
     * @return the ID of the account from which the money is transferred
     */
    public String getAccountFrom() {
        return accountFrom;
    }

    /**
     * Sets the ID of the account from which the money is transferred.
     *
     * @param accountFrom the new ID of the account from which the money is transferred
     */
    public void setAccountFrom(String accountFrom) {
        this.accountFrom = accountFrom;
    }

    /**
     * Returns the ID of the account to which the money is transferred.
     *
     * @return the ID of the account to which the money is transferred
     */
    public String getAccountTo() {
        return accountTo;
    }

    /**
     * Sets the ID of the account to which the money is transferred.
     *
     * @param accountTo the new ID of the account to which the money is transferred
     */
    public void setAccountTo(String accountTo) {
        this.accountTo = accountTo;
    }

    /**
     * Returns the amount of money transferred.
     *
     * @return the amount of money transferred
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the amount of money transferred.
     *
     * @param amount the new amount of money transferred
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Returns the date of the transaction.
     *
     * @return the date of the transaction
     */
    public String getDate() {return date;}

    /**
     * Sets the date of the transaction.
     *
     * @param date the new date of the transaction
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Returns the description of the transaction.
     *
     * @return the description of the transaction
     */
    public String getDescription() {return description;}

    /**
     * Sets the description of the transaction.
     *
     * @param description the new description of the transaction
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns a string representation of the transaction.
     *
     * @return a string representation of the transaction
     */
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
