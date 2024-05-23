package com.virtualbankv2.entity;

/**
 * The Task class represents a task in the system, including details such as task ID, description,
 * title, start and end dates, associated user, reward, and completion status.
 *
 * @version 2.0
 * @since 2024-04-13
 * @author Zhenghan Zhong
 */
public class Task {
    /**
     * The ID of the task.
     */
    private String taskID;

    /**
     * The description of the task.
     */
    private String description;

    /**
     * The title of the task.
     */
    private String title;

    /**
     * The start date of the task.
     */
    private String start;

    /**
     * The end date of the task.
     */
    private String end;

    /**
     * The username of the user associated with the task.
     */
    private String userName;

    /**
     * The reward for completing the task.
     */
    private double reward;

    /**
     * Indicates whether the task is completed.
     */
    private boolean isCompleted;

    /**
     * The total counter for all tasks.
     */
    public static int totalcounter;

    /**
     * The counter for the specific task.
     */
    private int counter;

    /**
     * Constructs a new Task with the specified description, reward, title, start date, end date, and username.
     *
     * @param description the description of the task
     * @param reward      the reward for completing the task
     * @param title       the title of the task
     * @param start       the start date of the task
     * @param end         the end date of the task
     * @param userName    the username of the user associated with the task
     */
    public Task(String description, double reward, String title, String start, String end, String userName) {
        this.taskID = String.valueOf(totalcounter);
        this.description = description;
        this.reward = reward;
        this.isCompleted = false;
        this.title = title;
        this.start = start;
        this.end = end;
        this.userName = userName;
    }

    /**
     * Constructs a new Task with the specified values.
     *
     * @param taskID      the ID of the task
     * @param description the description of the task
     * @param reward      the reward for completing the task
     * @param isCompleted whether the task is completed
     * @param counter     the counter for the specific task
     * @param title       the title of the task
     * @param start       the start date of the task
     * @param end         the end date of the task
     * @param userName    the username of the user associated with the task
     */
    public Task(String taskID, String description, double reward, boolean isCompleted, int counter, String start, String end, String title, String userName) {
        this.taskID = taskID;
        this.description = description;
        this.reward = reward;
        this.isCompleted = isCompleted;
        this.counter = counter;
        this.title = title;
        this.start = start;
        this.end = end;
        this.userName = userName;
    }

    /**
     * Returns the ID of the task.
     *
     * @return the ID of the task
     */
    public String getTaskID() {
        return taskID;
    }

    /**
     * Sets the ID of the task.
     *
     * @param taskID the new ID of the task
     */
    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }


    /**
     * Returns the title of the task.
     *
     * @return the title of the task
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the task.
     *
     * @param title the new title of the task
     */
    public void setTitle(String title){this.title=title;}

    /**
     * Returns the start date of the task.
     *
     * @return the start date of the task
     */
    public String getStart(){return start;}

    /**
     * Sets the start date of the task.
     *
     * @param start the new start date of the task
     */
    public void setStart(String start){this.start=start;}

    /**
     * Returns the end date of the task.
     *
     * @return the end date of the task
     */
    public String getEnd(){return end;}

    /**
     * Sets the end date of the task.
     *
     * @param end the new end date of the task
     */
    public void setEnd(String end){this.end=end;}

    /**
     * Returns the description of the task.
     *
     * @return the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the task.
     *
     * @param description the new description of the task
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the reward for completing the task.
     *
     * @return the reward for completing the task
     */
    public double getReward() {
        return reward;
    }

    /**
     * Sets the reward for completing the task.
     *
     * @param reward the new reward for completing the task
     */
    public void setReward(double reward) {
        this.reward = reward;
    }

    /**
     * Returns whether the task is completed.
     *
     * @return true if the task is completed, false otherwise
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param isCompleted the new completion status of the task
     */
    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    /**
     * Returns the counter for the specific task.
     *
     * @return the counter for the specific task
     */
    public int getCounter() {
        return counter;
    }

    /**
     * Sets the counter for the specific task.
     *
     * @param counter the new counter for the specific task
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }

    /**
     * Sets the username of the user associated with the task.
     *
     * @param userName the new username of the user associated with the task
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Returns the username of the user associated with the task.
     *
     * @return the username of the user associated with the task
     */
    public String getUserName() {
        return this.userName;
    }
}
