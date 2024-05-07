package com.virtualbankv2.control;

import com.virtualbankv2.boundary.CreateTaskPage;
import com.virtualbankv2.boundary.RoundedButton;
import com.virtualbankv2.boundary.TaskOverviewUI;
import com.virtualbankv2.boundary.Writer;
import com.virtualbankv2.entity.Task;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.virtualbankv2.control.VirtualBankApplication.currentUser;
import static com.virtualbankv2.entity.Task.totalcounter;

public class CreateTaskController {

    private CreateTaskPage view;
    private TaskOverviewUI overviewUI;
    Writer writer = new Writer();

    public CreateTaskController(CreateTaskPage view) {

        this.overviewUI = new TaskOverviewUI(); // 在构造函数中初始化hp
       // overviewUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        overviewUI.setVisible(false);
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
        String name = currentUser.getUsername();

        Task temptask = new Task(description,reward,title,start,end,name);
        writer.writeSingleTask(temptask);
        JOptionPane.showMessageDialog(view, "Task created successfully!");

    }

    public void initializeController() {
        // 为view中的按钮添加事件监听器
        addReturnListenerToButton(view.getSaveButton(), overviewUI);

    }

    private void addReturnListenerToButton(RoundedButton button, JFrame newPage) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createTask();
                view.dispose();
                newPage.setVisible(true);
            }
        });
    }

    //测试createtask
//    public static void main(String[] args) {
//        totalcounter = 0;
//        CreateTaskPage fr1 = new CreateTaskPage();
//        fr1.setVisible(true);
//
//    }
}