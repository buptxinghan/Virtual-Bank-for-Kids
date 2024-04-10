package com.virtualbankv1;
// 主页界面类

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;

public class Home extends Frame{
    private Account currentUser;
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Home fr = new Home("Virtual Bank"); //构造方法
        fr.setSize(240,240);  //设置Frame的大小
        fr.setBackground(new Color(201, 219, 247)); //设置Frame的背景色//设置Frame的背景色

        //frame.setLayout(new BorderLayout()); //设置 frame的布局为BorderLayout,默认也是此布局

        Button btn1 = new Button("button1");
        Button btn2 = new Button("button2");
        Button btn3 = new Button("button3");
        Button btn4 = new Button("button4");
        Button btn5 = new Button("button5");

        btn1.setBackground(Color.blue);
        btn2.setBackground(Color.yellow);
        btn3.setBackground(Color.pink);
        btn4.setBackground(Color.green);
        btn5.setBackground(Color.red);

        fr.add(btn1,BorderLayout.EAST);
        fr.add(btn2,BorderLayout.NORTH);
        fr.add(btn3,BorderLayout.SOUTH);
        fr.add(btn4,BorderLayout.WEST);
        fr.add(btn5);


        fr.setVisible(true); //设置Frame为可见，默认不可见
    }

    public Home(String str){
        super(str);
    }
}
