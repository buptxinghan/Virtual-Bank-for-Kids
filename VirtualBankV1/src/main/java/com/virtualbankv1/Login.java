package com.virtualbankv1;
// ... 登录、登出和注册方法

import java.util.List;
import java.util.ArrayList;

public class Login {
    private List<User> users; // 存储所有用户账户的信息

    public Login() {
        this.users = new ArrayList<>(); // 初始化账户列表
    }

    // 用户注册
    public boolean signUp(String username, String password) {
        // 检查用户名是否已存在
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                // 用户名已存在
                return false;
            }
        }
        // 创建新账户并添加到列表
        User newUser = new User(username, password);
        users.add(newUser);
        return true;
    }

    // 用户登录
    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                // 登录成功
                return user;
            }
        }
        // 登录失败
        return null;
    }

    // 用户登出
    public void logout(User user) {
        // 处理用户登出逻辑，例如更新用户状态或清除会话信息
        // ...
    }

    // ... 可能还需要其他辅助方法，例如检查用户名是否有效等
}
