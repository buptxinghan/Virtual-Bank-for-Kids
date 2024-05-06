package com.virtualbankv2.entity;
// 用户账户类

public class User {
    private String username;
    private String password;

    // Setter methods
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    // Getter methods
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }


    public User(){}
    public User(String username, String password) {
        setUsername(username);
        setPassword(password);
    }
}
