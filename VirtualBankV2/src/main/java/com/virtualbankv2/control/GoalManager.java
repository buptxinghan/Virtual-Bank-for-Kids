package com.virtualbankv2.control;

import com.virtualbankv2.boundary.Reader;
import com.virtualbankv2.entity.*;
import javax.swing.*;
import java.awt.*;

public class GoalManager extends JFrame {
    private String currentUsername; // 当前用户的用户名

    public GoalManager(String username) {
        this.currentUsername = username;

        // 设置窗口基本属性
        setTitle("Goal Manager");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 构建GUI
        buildGUI();
    }

    private void buildGUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // 筛选与当前用户相关的目标
        for (Goal goal : reader.goals) {
            if (goal.getUsername().equals(currentUsername)) {
                double completionRate = goal.getCurrentAmount() / goal.getTargetAmount();
                String goalDetails = String.format("Goal Name: %s, Completion: %.2f%%, Start Date: %s, End Date: %s",
                        goal.getGoalName(), completionRate * 100, goal.getStartDate(), goal.getEndDate());

                // 为每个目标创建一个标签并添加到面板
                JLabel label = new JLabel(goalDetails);
                panel.add(label);
            }
        }

        JScrollPane scrollPane = new JScrollPane(panel); // 添加滚动条
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane); // 将滚动条面板添加到窗体
    }

    public static void main(String[] args) {
         Reader reader = new Reader(); // 使用Reader类读取数据
        GoalManager gm = new GoalManager("user1");
        gm.setVisible(true);
    }
}
