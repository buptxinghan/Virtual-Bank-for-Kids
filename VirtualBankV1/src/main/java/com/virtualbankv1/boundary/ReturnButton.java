package com.virtualbankv1.boundary;

import com.virtualbankv1.control.AccountOverviewPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReturnButton {

    public static JButton createReturnButton(JFrame frame, String lastPage) {
        RoundedButton button = new RoundedButton("<html><font size ='6'>Return</font></html>");
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(200, 50));
        button.setMinimumSize(new Dimension(200, 50));
        button.setMaximumSize(new Dimension(200, 50));
        addReturnListenerToButton(button, frame, lastPage); // 添加监听器
        return button;
    }

    // 为返回按钮添加动作监听器
    private static void addReturnListenerToButton(JButton button, JFrame frame, String lastPage) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // 关闭窗口
                if (lastPage.equals("accountOverviewPage")) {
                    AccountOverviewPage aop = new AccountOverviewPage(); //生成上一页面
                    aop.ui.setPage(aop.ui);
                }
                else if (lastPage.equals(" ")) {
                    //添加你想跳转的页面
                }

            }
        });
    }
}
