package com.virtualbankv2.entity;

public class Interest {
    private String lastUpdate;
    private String username;

    public Interest (String username, String lastUpdate) {
        this.lastUpdate = lastUpdate;
        this.username = username;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
