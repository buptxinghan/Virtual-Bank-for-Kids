package com.virtualbankv1.boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountOverviewPage extends JFrame {
    public JButton createNewAccountButton = new JButton("+");

    public AccountOverviewPage() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Create new account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(titleLabel);

        JButton addButton = new JButton("+");
        panel.add(addButton);

        add(panel);
        pack();
        setLocationRelativeTo(null); // 设置窗口居中
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 修改关闭行为

        // 添加按钮点击事件
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 新建并显示“开户”界面
                OpenAccountPage openAccountPage = new OpenAccountPage();
                openAccountPage.setSize(new Dimension(1200, 900));
                openAccountPage.setVisible(true);
                openAccountPage.setLocationRelativeTo(null); // 设置窗口居中
                openAccountPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 修改关闭行为
            }
        });
    }

    public void setPage(AccountOverviewPage aop) {
        aop.setSize(new Dimension(1200, 900));
        aop.setLocationRelativeTo(null);
        aop.setVisible(true);
    }

//    public void updatePage(Account account, Color color) {
//        JPanel panel = new JPanel();
//        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//        panel.setBackground(color);
//
//        JLabel titleLabel = new JLabel(account.getAccountID());
//        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
//        panel.add(titleLabel);
//
//        JLabel numberLabel = new JLabel("Account number: " + account.getStatus());
//        panel.add(numberLabel);
//
//        JLabel typeLabel = new JLabel("Account type: " + account.getAccountType());
//        panel.add(typeLabel);
//
//        JButton selectButton = new JButton("Select");
//        selectButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // 显示账户的详细信息
//                AccountManager am = new AccountManager(account);
//            }
//        });
//        panel.add(selectButton);
//        // 在这里添加更新原窗口的代码
//        setContentPane(panel);
//        revalidate();
//    }
}
