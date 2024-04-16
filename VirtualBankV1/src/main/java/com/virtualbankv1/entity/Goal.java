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
}
