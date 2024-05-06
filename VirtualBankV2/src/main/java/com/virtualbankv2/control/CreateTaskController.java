package com.virtualbankv2.control;

import com.virtualbankv2.boundary.CreateTaskPage;
import com.virtualbankv2.boundary.RoundedButton;
import com.virtualbankv2.boundary.Writer;
import com.virtualbankv2.entity.Task;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.virtualbankv2.entity.Task.totalcounter;

public class CreateTaskController {

    private CreateTaskPage view;
    //private testpage hp;
    Writer writer = new Writer();

    public CreateTaskController(CreateTaskPage view) {

        //this.hp = new testpage(1); // 在构造函数中初始化hp
        //hp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //保证新跳转的页面构造取消 setVisible(true) 否则会同时弹出来；
        this.view = view;
    }


    public void createTask(){
        String title = view.getTitles().getText();
        String re = view.getReward().getText();
        String description = view.getContentArea().getText();
        Double reward = Double.parseDouble(re);
        String start = view.getStart();
        String end = view.getEnd();
        totalcounter = totalcounter+1;

        Task temptask = new Task(description,reward,title,start,end);
        writer.writeSingleTask(temptask);
        JOptionPane.showMessageDialog(view, "Task created successfully!");

    }

    public void initializeController() {
        // 为view中的按钮添加事件监听器
       // addReturnListenerToButton(view.getSaveButton(), hp);

    }

    private void addReturnListenerToButton(RoundedButton button, JFrame newPage) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createTask();
                view.setVisible(false);
                newPage.setVisible(true);
            }
        });
    }

    //测试createtask
    public static void main(String[] args) {
        totalcounter = 0;
        CreateTaskPage fr1 = new CreateTaskPage();
        fr1.setVisible(true);

    }
}