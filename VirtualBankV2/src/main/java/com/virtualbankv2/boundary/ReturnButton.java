package com.virtualbankv2.boundary;

import com.virtualbankv2.control.AccountOverviewPage;
import com.virtualbankv2.entity.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReturnButton {

    public static JButton createReturnButton(JFrame frame, String lastPage) {
        return createReturnButton(frame, lastPage, new Dimension(200, 50), null);
    }

    public static JButton createReturnButton(JFrame frame, String lastPage, Dimension dimension) {
        return createReturnButton(frame, lastPage, dimension, null);
    }

    public static JButton createReturnButton(JFrame frame, String lastPage, Dimension dimension, Account account) {
        RoundedButton button = new RoundedButton("<html><font size ='6'>Return</font></html>");
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(dimension);
        button.setMinimumSize(dimension);
        button.setMaximumSize(dimension);
        addReturnListenerToButton(button, frame, lastPage, account); // 添加监听器
        return button;
    }

    private static void addReturnListenerToButton(JButton button, JFrame frame, String lastPage, Account account) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // 关闭窗口

                if (lastPage.equals("accountOverviewPage")) {
                    new AccountOverviewPage(); // 生成上一页面
                } else if (lastPage.equals("HomePage")) {
                    new HomePage();
                } else if (lastPage.equals("taskOverviewPage")) {
                    new TaskOverviewUI();
                } else if (lastPage.equals("accountInformationPage")) {
                    new AccountInformationPage(account);
                }
            }
        });
    }
}