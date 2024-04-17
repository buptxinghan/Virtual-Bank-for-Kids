package com.virtualbankv1.boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReturnButton {

    public static JButton createReturnButton(String text, Color bgColor, Color textColor, Dimension size, JFrame frame) {
        RoundedButton button = new RoundedButton("<html><font size ='6'>" + text + "</font></html>");
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(size);
        button.setMinimumSize(size);
        button.setMaximumSize(size);
        addReturnListenerToButton(button, frame); // 添加监听器
        return button;
    }

    // 为返回按钮添加动作监听器
    private static void addReturnListenerToButton(JButton button, JFrame frame) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // 关闭窗口
                AccountOverviewPage aop = new AccountOverviewPage(); //生成上一页面
                aop.setPage(aop);
            }
        });
    }
}
