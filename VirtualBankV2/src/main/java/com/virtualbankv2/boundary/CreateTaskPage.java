package com.virtualbankv2.boundary;
import com.toedter.calendar.JDateChooser;//需要加入Jcalendar jar到项目路径
import com.virtualbankv2.control.CreateTaskController;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateTaskPage extends JFrame{
    private JTextArea contentArea;
    private JTextField title;
    private JTextField reward;
    private JDateChooser chooseStart;
    private JDateChooser chooseEnd;
    private RoundedButton saveButton;

    public CreateTaskPage(){

        initializeComponents();
        CreateTaskController controller = new CreateTaskController(this);
        controller.initializeController();
    }

    private void initializeComponents() {
        // 设置窗口大小
        setSize(new Dimension(1200, 900));
        setTitle("Virtual Bank");
        // 设置背景颜色
        Color bg = new Color(199, 220, 247);
        Color z1 = new Color(93, 97, 195);
        Color z2 = new Color(133, 149, 188);

        getContentPane().setBackground(bg);

        //生成组件
        //按钮
        saveButton = new RoundedButton("Save");
        saveButton.setBackground(z1); // 设置按钮背景颜色为紫色
        saveButton.setForeground(Color.WHITE); // 设置文本颜色为白色
        //saveButton.setPreferredSize(new Dimension(5, 5)); // 设置按钮的首选大小

        //Label
        JLabel head = new JLabel("<html><font size=7 color=" + getColorHex(z1) + ">Create Task</font></html>",JLabel.CENTER);
        JLabel l1 = new JLabel("<html><font size=5 color=" + getColorHex(z1) + ">Title*</font></html>",JLabel.LEFT);
        JLabel l2 = new JLabel("<html><font size=5 color=" + getColorHex(z1) + ">Reward*(/CNY)</font></html>",JLabel.LEFT);
        JLabel l3 = new JLabel("<html><font size=5 color=" + getColorHex(z1) + ">Start time</font></html>",JLabel.LEFT);
        JLabel l4 = new JLabel("<html><font size=5 color=" + getColorHex(z1) + ">End time</font></html>",JLabel.LEFT);
        JLabel l5 = new JLabel("<html><font size=5 color=" + getColorHex(z1) + ">Content</font></html>",JLabel.LEFT);
        JLabel tip = new JLabel("<html><font size=4 color=" + getColorHex(z1) + ">*Required fields</font></html>",JLabel.LEFT);
        //content文本域
        contentArea = new JTextArea();
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true); // 确保单词不会被拆分
        JScrollPane scrollPane = new JScrollPane(contentArea);
        // 设置滚动条始终可见
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(250, 150));

        //文本框
        title = new JTextField();
        reward = new JTextField();
        //time
        chooseStart = new JDateChooser();
        chooseStart.setDateFormatString("yyyy-MM-dd");
        //Date start = chooseStart.getDate();
        //String strDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
        chooseEnd = new JDateChooser();
        chooseEnd.setDateFormatString("yyyy-MM-dd");
        //Date end = chooseEnd.getDate();
        //String strDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);

        //布局
        JPanel Center = new JPanel(new GridLayout(2, 1));
        JPanel C1 = new JPanel(new GridLayout(9,1));
        JPanel C2 = new JPanel(new GridLayout(3,1));

        JPanel S = new JPanel();
        JPanel W = new JPanel();
        JPanel E = new JPanel();
        JPanel b = new JPanel();



        W.setPreferredSize(new Dimension(200, 200));
        E.setPreferredSize(new Dimension(200, 200));
        S.setPreferredSize(new Dimension(100, 100));

        Center.setBackground(bg);
        C1.setBackground(bg);
        C2.setBackground(bg);
        S.setBackground(bg);
        W.setBackground(bg);
        E.setBackground(bg);
        b.setBackground(bg);



        //往布局添加组件
        C1.add(tip);
        C1.add(l1);
        C1.add(title);
        C1.add(l2);
        C1.add(reward);
        C1.add(l3);
        C1.add(chooseStart);
        C1.add(l4);
        C1.add(chooseEnd);



        b.setLayout(null);
        saveButton.setBounds(500, 20, 80, 40);
        b.add(saveButton);
        C2.add(l5);
        C2.add(contentArea);
        C2.add(b);

        Center.add(C1);
        Center.add(C2);


        add(E,BorderLayout.EAST);
        add(head,BorderLayout.NORTH);
        add(S,BorderLayout.SOUTH);
        add(W,BorderLayout.WEST);
        add(Center);

        //显示
        setLocationRelativeTo(null); // 居中显示
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


    }

    // 辅助方法：将Color对象转换为HTML颜色字符串
    private String getColorHex(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    public JTextArea getContentArea() {
        return contentArea;
    }

    public JTextField getTitles() {
        return title;
    }

    public JTextField getReward() {
        return reward;
    }

    public String getStart() {
        Date s = chooseStart.getDate();
        String start = new SimpleDateFormat("yyyy-MM-dd").format(s);
        return start;
    }

    public String getEnd() {
        Date e = chooseEnd.getDate();
        String end = new SimpleDateFormat("yyyy-MM-dd").format(e);
        return end;
    }

    public RoundedButton getSaveButton() {
        return saveButton;
    }
}