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
        this.overviewUI = new TaskOverviewUI();
        overviewUI.setVisible(false);
        this.view = view;
    }


    public void createTask(){
        String title = view.getTitleField().getText();
        String re = view.getReward().getText();
        String description = view.getContentArea().getText();
        String start = view.getStart();
        String end = view.getEnd();
        String name = currentUser.getUsername();

        if (titleIsValid() && rewardIsValid()) {
            totalcounter = totalcounter+1;
            Double reward = Double.parseDouble(re);
            Task temptask = new Task(description,reward,title,start,end,name);
            writer.writeSingleTask(temptask);
            UIManager.put("OptionPane.cancelButtonText", "Cancel");
            UIManager.put("OptionPane.okButtonText", "OK");
            JOptionPane.showMessageDialog(view, "Task created successfully!");
        }
        else{
            UIManager.put("OptionPane.cancelButtonText", "Cancel");
            UIManager.put("OptionPane.okButtonText", "OK");
            JOptionPane.showMessageDialog(view, "Please fill in the required field.");
        }

    }

    public void initializeController() {
        addReturnListenerToButton(view.getSaveButton(), overviewUI);

    }

    private void addReturnListenerToButton(RoundedButton button, JFrame newPage) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createTask();
                if(titleIsValid() && rewardIsValid()){
                    view.dispose();
                    newPage.setVisible(true);
                }
            }
        });
    }
    private boolean titleIsValid() {
        String title = view.getTitleField().getText();
        return title != null && !title.trim().isEmpty();//not equal to null string and empty value
    }

    private boolean rewardIsValid() {
        String re = view.getReward().getText();
        return re != null && !re.trim().isEmpty();
    }

}