package com.virtualbankv2.control;

import com.virtualbankv2.boundary.CreateTaskPage;
import com.virtualbankv2.entity.RoundedButton;
import com.virtualbankv2.boundary.TaskOverviewUI;
import com.virtualbankv2.boundary.Writer;
import com.virtualbankv2.entity.Task;


import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static com.virtualbankv2.control.VirtualBankApplication.currentUser;
import static com.virtualbankv2.entity.Task.totalcounter;

public class CreateTaskController {

    private CreateTaskPage view;
    Writer writer = new Writer();

    public CreateTaskController(CreateTaskPage view) {
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
        addReturnListenerToButton(view.getSaveButton());
        view.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                returnToTaskOverview();
            }
        });

    }

    private void returnToTaskOverview() {
        view.dispose();
        new TaskOverviewUI();
    }

    private void addReturnListenerToButton(RoundedButton button) {
        button.addActionListener(e -> {
            createTask();
            if(titleIsValid() && rewardIsValid()){
                view.dispose();
                new TaskOverviewUI();
            }
        });
    }
    private boolean titleIsValid() {
        String title = view.getTitleField().getText();
        return title != null && !title.trim().isEmpty();
    }

    private boolean rewardIsValid() {
        String re = view.getReward().getText();
        return re != null && !re.trim().isEmpty();
    }


}