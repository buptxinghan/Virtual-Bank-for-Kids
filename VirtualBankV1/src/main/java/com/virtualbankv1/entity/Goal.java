package com.virtualbankv1.entity;

public class Goal {
    // 设置目标

    private String goalID;
    private String description;
    private double targetAmount;
    private double currentAmount;
    private String childUsername;

    public Goal(String goalID, String description, double targetAmount, double currentAmount, String childUsername) {
        this.goalID = goalID;
        this.description = description;
        this.targetAmount = targetAmount;
        this.currentAmount = currentAmount;
        this.childUsername = childUsername;
    }

    //get and set
    public String getGoalID() {
        return goalID;
    }

    public void setGoalID(String goalID) {
        this.goalID = goalID;
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

    public String getChildUsername() {
        return childUsername;
    }

    public void setChildUsername(String childUsername) {
        this.childUsername = childUsername;
    }
}
