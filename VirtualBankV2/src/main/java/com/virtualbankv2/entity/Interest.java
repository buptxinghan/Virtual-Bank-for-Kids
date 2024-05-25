package com.virtualbankv2.entity;

/**
 * The Interest class represents the interest details associated with a user's account.
 * It contains the username and the last update date of the interest calculation.
 *
 * @version 1.0
 * @since 2024-05-13
 * @author Zhenghan Zhong
 */
public class Interest {
    /**
     * The last date when the interest was updated.
     */
    private String lastUpdate;

    /**
     * The username associated with the interest.
     */
    private String username;

    /**
     * Constructs a new Interest object with the specified username and last update date.
     *
     * @param username   the username associated with the interest
     * @param lastUpdate the last date when the interest was updated
     */
    public Interest (String username, String lastUpdate) {
        this.lastUpdate = lastUpdate;
        this.username = username;
    }

    /**
     * Returns the last update date of the interest.
     *
     * @return the last update date
     */
    public String getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Sets the last update date of the interest.
     *
     * @param lastUpdate the new last update date
     */
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Returns the username associated with the interest.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username associated with the interest.
     *
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
