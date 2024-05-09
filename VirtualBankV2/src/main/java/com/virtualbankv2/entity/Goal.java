package com.virtualbankv2.entity;

public class Goal {
    // 设置目标

    private String goalName;
    private String description;
    private double targetAmount;
    private double currentAmount;
    private String username;

    public Goal(String goalName, String description, double targetAmount, double currentAmount, String username) {
        this.goalName = goalName;
        this.description = description;
        this.targetAmount = targetAmount;
        this.currentAmount = currentAmount;
        this.username = username;
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


}
