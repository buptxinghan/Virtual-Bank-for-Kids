package com.virtualbankv2.entity;

public class Goal {
    // 设置目标

    private String goalName;
    private String description;
    private double targetAmount;
    private double currentAmount;
    private String username;
    private String startDate;
    private String endDate;
    private String targetAccount;

    public Goal(String goalName, String description, double targetAmount, double currentAmount, String username, String startDate, String endDate, String targetAccount) {
        this.goalName = goalName;
        this.description = description;
        this.targetAmount = targetAmount;
        this.currentAmount = currentAmount;
        this.username = username;
        this.startDate = startDate;
        this.endDate = endDate;
        this.targetAccount = targetAccount;
    }

    //get and set
    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalID) {
        this.goalName = goalID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String childUsername) {
        this.username = childUsername;
    }

    public String getStartDate() {
        return  startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(String targetAccount) {
        this.targetAccount = targetAccount;
    }
}
