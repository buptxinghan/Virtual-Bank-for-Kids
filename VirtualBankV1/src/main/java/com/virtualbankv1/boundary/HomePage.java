package com.virtualbankv1.boundary;
// 主页界面类

import com.virtualbankv1.control.HomePageController;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JFrame {

    private JButton accountButton;
    private JButton tasksButton;
    private JButton goalsButton;
    private JButton manualButton;

    public HomePage(String title) {
        super(title);
        initializeComponents();
        HomePageController controller = new HomePageController(this);
        controller.initializeController();
    }

    private void initializeComponents() {
        // 设置窗口大小
        setSize(new Dimension(1000, 600));
        // 设置背景颜色
        Color bg = new Color(199, 220, 247);
        Color z1 = new Color(93,97,195);
        Color z2 = new Color(133,149,188);

        getContentPane().setBackground(bg);

        // 初始化按钮
        accountButton = new JButton("<html><font size=5 color=" + getColorHex(z1) + ">My account</font><br><font color=" + getColorHex(z2) + ">Check your account information</font></html>");
        tasksButton = new JButton("<html><font size=5 color=" + getColorHex(z1) + ">My tasks</font><br><font color=" + getColorHex(z2) + ">Check the tasks assigned</font></html>");
        goalsButton = new JButton("<html><font size=5 color=" + getColorHex(z1) + ">My goals</font><br><font color=" + getColorHex(z2) + ">Check and manage your goal</font></html>");
        manualButton = new JButton("<html><font size=5 color=" + getColorHex(z1) + ">Instruction manual</font><br><font color=" + getColorHex(z2) + ">Learn how to use the software or contact us</font></html>");

        accountButton.setHorizontalAlignment(SwingConstants.LEFT);
        tasksButton.setHorizontalAlignment(SwingConstants.LEFT);
        goalsButton.setHorizontalAlignment(SwingConstants.LEFT);
        manualButton.setHorizontalAlignment(SwingConstants.LEFT);

        accountButton.setBackground(bg);
        tasksButton.setBackground(bg);
        goalsButton.setBackground(bg);
        manualButton.setBackground(bg);

        //初始化组件
        JLabel l1 = new JLabel("<html><font size=7 color=" + getColorHex(z1) + ">Welcome to Your Virtual Bank!</font></html>",JLabel.CENTER);
        ImageIcon icon = new ImageIcon("D:\\Project\\Virtualbank\\Virtual-Bank-for-Kids\\Data\\pic.png");
        JLabel pic = new JLabel(icon,JLabel.CENTER);

        // 设置布局

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

//添加组件上去
        Centerdown.add(accountButton);
        Centerdown.add(tasksButton);
        Centerdown.add(goalsButton);
        Centerdown.add(manualButton);

        Center.add(pic);
        Center.add(Centerdown);

        add(right, BorderLayout.EAST);
        add(l1, BorderLayout.NORTH);
        add(down, BorderLayout.SOUTH);
        add(left, BorderLayout.WEST);
        add(Center);

        setLocationRelativeTo(null); //窗口居中
        setVisible(true);
    }

    // 辅助方法：将Color对象转换为HTML颜色字符串
    private String getColorHex(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    // Getters for buttons
    public JButton getAccountButton() {
        return accountButton;
    }

    public JButton getTasksButton() {
        return tasksButton;
    }

    public JButton getGoalsButton() {
        return goalsButton;
    }

    public JButton getManualButton() {
        return manualButton;
    }



}