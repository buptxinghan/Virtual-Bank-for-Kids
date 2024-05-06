package com.virtualbankv2.control;

import com.virtualbankv2.boundary.PageOpen;
import com.virtualbankv2.entity.ChildLock;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChildLockManager {

    public void addButtonWithChildLock(JFrame frame, JButton button, PageOpen pageOpen) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 创建并显示验证界面
                ChildLock childLock = new ChildLock();

                // 在验证界面中的 "Check" 按钮的点击事件中，检查用户的答案
                JButton checkButton = childLock.getCheckButton();
                JTextField userText = childLock.getUserText();
                checkButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // 如果用户的答案正确，就执行跳转操作
                        if ("1998".equals(userText.getText().trim())) {
                            frame.dispose();
                            childLock.dispose();
                            pageOpen.openAccount();
                        } else {
                            JOptionPane.showMessageDialog(childLock, "答案错误，请重试！");
                        }
                    }
                });
            }
        });
    }


}
