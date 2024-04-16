package com.virtualbankv1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenAccount extends JFrame implements ActionListener{//9 assets
    public JLabel title;
    public JLabel att;
    public JLabel atd;
    String options[]={"Saving","Current"};
    JComboBox<String> ats=new JComboBox<>(options);//account type
    public JLabel pp;
    public JLabel ppd;
    public JTextField pw=new JTextField(6);//password
    public JLabel cp;
    public JTextField pw2=new JTextField(6);//password confirm
    //密码隐藏收入
    public JButton submit=new JButton("submit");

    public OpenAccount(){
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);;
        title = new JLabel("Open Account");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 24)); // 设置最大字号
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(title, gbc);

        // 添加 Account Type 相关组件
        att = new JLabel("Account Type (single-select):");
        att.setFont(att.getFont().deriveFont(Font.BOLD, 14)); // 设置字号
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(att, gbc);

        atd = new JLabel("Basically, the only difference between these two types of account is whether users can get a yield by their savings.");
        atd.setFont(atd.getFont().deriveFont(Font.PLAIN, 12)); // 设置字号
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(atd, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(ats, gbc);

        // 添加 Payment Password 相关组件
        pp = new JLabel("Payment Password:");
        pp.setFont(pp.getFont().deriveFont(Font.BOLD, 14)); // 设置字号
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(pp, gbc);

        ppd = new JLabel("A 6-digit password is allowed.");
        ppd.setFont(ppd.getFont().deriveFont(Font.PLAIN, 12)); // 设置字号
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(ppd, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(pw, gbc);

        // 添加 Confirm Password 相关组件
        cp = new JLabel("Confirm Password:");
        cp.setFont(cp.getFont().deriveFont(Font.BOLD, 14)); // 设置字号
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(cp, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(pw2, gbc);

        // 添加按钮
        gbc.gridx = 0;
        gbc.gridy = 9;
        panel.add(submit, gbc);

        add(panel);
        pack();
        setLocationRelativeTo(null); // 设置窗口居中
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        submit.addActionListener(this);
    }
    public static void main(String[] args) {
        OpenAccount openAccount =new OpenAccount();
        openAccount.setSize(1000, 500);
        openAccount.setVisible(true);
    }
    public void actionPerformed(ActionEvent event){
        String str1=pw.getText();
        String str2=pw2.getText();
        String str3 = (String) ats.getSelectedItem();
        if(str1.equals("") ||str2.equals("")||str1.length()!=6||str2.length()!=6)
        {
//弹窗？invalid input
        }
        else if (!str1.equals(str2)) {
            //弹窗密码不一致
        }
        else {
            Account account =new Account(str1,str3,"a","b", 0.1, "s");
            //弹窗成功创建
            //存储进csv
        }

    }
}
