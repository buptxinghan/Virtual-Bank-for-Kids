package com.virtualbankv1;
// 主页界面类

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame {

    public Home(String str) {
        super(str);
        setSize(1000, 600);

        Color bg = new Color(199, 220, 247);
        Color z1 = new Color(93,97,195);
        Color z2 = new Color(133,149,188);

        getContentPane().setBackground(bg);

        JButton btn1 = new JButton("<html><font size=5 color=" + getColorHex(z1) + ">My account</font><br><font color=" + getColorHex(z2) + ">Check your account information</font></html>");
        btn1.setHorizontalAlignment(SwingConstants.LEFT);

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new testpage("Visual bank");//打开页面2，
                setVisible(false);//关闭页面1
            }
        });

        JButton btn2 = new JButton("<html><font size=5 color=" + getColorHex(z1) + ">My tasks</font><br><font color=" + getColorHex(z2) + ">Check the tasks assigned</font></html>");
        btn2.setHorizontalAlignment(SwingConstants.LEFT);
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new testpage2("Visual bank");//打开页面2，
                setVisible(false);//关闭页面1
            }
        });

        JButton btn3 = new JButton("<html><font size=5 color=" + getColorHex(z1) + ">My goals</font><br><font color=" + getColorHex(z2) + ">Check and manage your goal</font></html>");
        btn3.setHorizontalAlignment(SwingConstants.LEFT);
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new testpage3("Visual bank");//打开页面2，
                setVisible(false);//关闭页面1
            }
        });

        JButton btn4 = new JButton("<html><font size=5 color=" + getColorHex(z1) + ">Instruction manual</font><br><font color=" + getColorHex(z2) + ">Learn how to use the software or contact us</font></html>");
        btn4.setHorizontalAlignment(SwingConstants.LEFT);
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new testpage4("Visual bank");//打开页面2，
                setVisible(false);//关闭页面1
            }
        });

        btn1.setBackground(bg);
        btn2.setBackground(bg);
        btn3.setBackground(bg);
        btn4.setBackground(bg);

        JLabel l1 = new JLabel("<html><font size=7 color=" + getColorHex(z1) + ">Welcome to Your Virtual Bank!</font></html>",JLabel.CENTER);
        ImageIcon icon = new ImageIcon("D:\\Project\\Virtualbank\\Virtual-Bank-for-Kids\\Data\\pic.png");
        JLabel pic = new JLabel(icon,JLabel.CENTER);


        JPanel Center = new JPanel(new GridLayout(2, 1));
        JPanel right = new JPanel();
        JPanel left = new JPanel();
        JPanel down = new JPanel();
        JPanel Centerdown = new JPanel(new GridLayout(4, 1));

        Center.setBackground(bg);
        right.setBackground(bg);
        left.setBackground(bg);
        down.setBackground(bg);
        Centerdown.setBackground(bg);

        right.setPreferredSize(new Dimension(100, 100));
        left.setPreferredSize(new Dimension(100, 100));


        Centerdown.add(btn1);
        Centerdown.add(btn2);
        Centerdown.add(btn3);
        Centerdown.add(btn4);

        Center.add(pic);
        Center.add(Centerdown);

        add(right, BorderLayout.EAST);
        add(l1, BorderLayout.NORTH);
        add(down, BorderLayout.SOUTH);
        add(left, BorderLayout.WEST);
        add(Center);

        setVisible(true);
    }
    // 辅助方法：将Color对象转换为HTML颜色字符串
    private String getColorHex(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }
}
