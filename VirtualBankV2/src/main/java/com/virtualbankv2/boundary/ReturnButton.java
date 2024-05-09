package com.virtualbankv2.boundary;

import com.virtualbankv2.control.AccountOverviewPage;
import com.virtualbankv2.entity.Account;

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

    public static JButton createReturnButton(JFrame frame, String lastPage, Dimension dimension) {
        RoundedButton button = new RoundedButton("<html><font size ='6'>Return</font></html>");
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(dimension);
        button.setMinimumSize(dimension);
        button.setMaximumSize(dimension);
        addReturnListenerToButton(button, frame, lastPage); // 添加监听器
        return button;
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

    // 为返回按钮添加动作监听器
    private static void addReturnListenerToButton(JButton button, JFrame frame, String lastPage) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // 关闭窗口

                // Save data
//                Writer writer = new Writer();
//                writer.writeAccounts("src/Data/Accounts.csv", accounts);
//                writer.writeGoals("src/Data/Goals.csv", goals);
//                writer.writeTasks("src/Data/Tasks.csv", tasks);
//                writer.writeTransactions("src/Data/Transactions.csv", transactions);

                if (lastPage.equals("accountOverviewPage")) {
                     new AccountOverviewPage(); //生成上一页面
                }
                else if (lastPage.equals("HomePage")) {
                    //添加你想跳转的页面
                     new HomePage();
                }
                else if (lastPage.equals("taskOverviewPage")) {
                    new TaskOverviewUI();
                }

            }
        });
    }

    // 为返回按钮添加动作监听器
    private static void addReturnListenerToButton(JButton button, JFrame frame, String lastPage, Account account) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // 关闭窗口

                // Save data
//                Writer writer = new Writer();
//                writer.writeAccounts("src/Data/Accounts.csv", accounts);
//                writer.writeGoals("src/Data/Goals.csv", goals);
//                writer.writeTasks("src/Data/Tasks.csv", tasks);
//                writer.writeTransactions("src/Data/Transactions.csv", transactions);

                if (lastPage.equals("accountOverviewPage")) {
                    AccountOverviewPage aop = new AccountOverviewPage(); //生成上一页面
                }
                else if (lastPage.equals("accountInformationPage")) {
                    new AccountInformationPage(account);
                }
                else if (lastPage.equals("taskOverviewPage")) {
                    //添加你想跳转的页面
                    new TaskOverviewUI();
                }


            }
        });
    }
}
