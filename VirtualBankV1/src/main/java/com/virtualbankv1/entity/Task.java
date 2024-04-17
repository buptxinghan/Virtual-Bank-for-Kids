package com.virtualbankv1.entity;
// 任务查看、布置、管理

public class Task {
    private String taskID;
    private String description;
    private double reward;
    private boolean isCompleted;
    private int counter;

    public Task(String taskID, String description, double reward, boolean isCompleted, int counter) {
        this.taskID = taskID;
        this.description = description;
        this.reward = reward;
        this.isCompleted = isCompleted;
        this.counter = counter;
    }

    // 这里可以添加getter和setter方法
    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getReward() {
        return reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
