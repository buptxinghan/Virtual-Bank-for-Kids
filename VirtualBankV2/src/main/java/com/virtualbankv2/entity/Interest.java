package com.virtualbankv2.entity;

public class Interest {
    private String interestId;
    private String lastUpdate;
    private String currentDate;
    private String accountId;
    private double amount;

    public Interest(String interestId, String lastUpdate, String currentDate, String accountId, double amount) {
        this.interestId = interestId;
        this.lastUpdate = lastUpdate;
        this.currentDate = currentDate;
        this.accountId = accountId;
        this.amount = amount;
    }

    public String getInterestId() {
        return interestId;
    }

    public void setInterestId(String interestId) {
        this.interestId = interestId;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
