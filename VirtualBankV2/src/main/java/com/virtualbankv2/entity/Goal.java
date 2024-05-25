package com.virtualbankv2.entity;

/**
 * The Goal class represents a financial goal with relevant details such as
 * goal name, description, target amount, current amount, and the associated username.
 *
 * @version 2.0
 * @since 2024-04-13
 * @author Zhenghan Zhong
 */
public class Goal {
    /**
     * The name of the goal.
     */
    private String goalName;

    /**
     * The description of the goal.
     */
    private String description;

    /**
     * The target amount to be achieved for the goal.
     */
    private double targetAmount;

    /**
     * The current amount saved towards the goal.
     */
    private double currentAmount;

    /**
     * The username associated with the goal.
     */
    private String username;

    /**
     * Constructs a new Goal with the specified details.
     *
     * @param goalName      the name of the goal
     * @param description   the description of the goal
     * @param targetAmount  the target amount to be achieved for the goal
     * @param currentAmount the current amount saved towards the goal
     * @param username      the username associated with the goal
     */
    public Goal(String goalName, String description, double targetAmount, double currentAmount, String username) {
        this.goalName = goalName;
        this.description = description;
        this.targetAmount = targetAmount;
        this.currentAmount = currentAmount;
        this.username = username;
    }

    /**
     * Returns the name of the goal.
     *
     * @return the goal name
     */
    public String getGoalName() {
        return goalName;
    }

    /**
     * Sets the name of the goal.
     *
     * @param goalName the new goal name
     */
    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    /**
     * Returns the description of the goal.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the goal.
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the target amount to be achieved for the goal.
     *
     * @return the target amount
     */
    public double getTargetAmount() {
        return targetAmount;
    }

    /**
     * Sets the target amount to be achieved for the goal.
     *
     * @param targetAmount the new target amount
     */
    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }

    /**
     * Returns the current amount saved towards the goal.
     *
     * @return the current amount
     */
    public double getCurrentAmount() {
        return currentAmount;
    }

    /**
     * Sets the current amount saved towards the goal.
     *
     * @param currentAmount the new current amount
     */
    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }

    /**
     * Returns the username associated with the goal.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username associated with the goal.
     *
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }


}
