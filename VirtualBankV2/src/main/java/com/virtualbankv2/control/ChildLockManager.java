package com.virtualbankv2.control;

import com.virtualbankv2.boundary.PageOpen;
import com.virtualbankv2.boundary.ChildLock;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 设置默认语言环境为英语
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
                        if ("111".equals(userText.getText().trim())) {
                            frame.dispose();
                            childLock.dispose();
                            pageOpen.openAccount();
                        } else {
                            try {
                                // 设置系统的 Look and Feel
                                //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                            } catch (Exception ee) {
                                ee.printStackTrace();
                            }
                            JOptionPane.showOptionDialog(
                                    childLock,
                                    "Answer incorrect, please try again!",
                                    "Error",
                                    JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.ERROR_MESSAGE,
                                    null,
                                    new String[] {"OK"},
                                    "OK"
                            );

                        }
                    }
                });
            }
        });
    }


}
