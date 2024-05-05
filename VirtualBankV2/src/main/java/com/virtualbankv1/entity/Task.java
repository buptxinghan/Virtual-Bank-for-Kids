package com.virtualbankv1.entity;
// 任务查看、布置、管理

public class Task {
    private String taskID;
    private String description;
    private String title;
    private String start;
    private String end;

    private double reward;
    private boolean isCompleted;
    public static int totalcounter;
    private int counter;

    public Task(String description, double reward, String title, String start, String end) {
        this.taskID = String.valueOf(totalcounter);
        this.description = description;
        this.reward = reward;
        this.isCompleted = false;
        this.title = title;
        this.start = start;
        this.end = end;
    }

    public Task(String value, String value1, double v, boolean b, int i, String s, String e, String value2) {
        this.taskID = value;
        this.description = value1;
        this.reward = v;
        this.isCompleted = b;
        this.counter = i;
        this.title = value2;
        this.start = s;
        this.end = e;
    }

    // 这里可以添加getter和setter方法
    public String getTaskID() {
        return taskID;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title){this.title=title;}
    public String getStart(){return start;}
    public void setStart(String start){this.start=start;}
    public String getEnd(){return end;}
    public void setEnd(String end){this.end=end;}

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
