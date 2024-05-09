package com.virtualbankv2.control;
import com.virtualbankv2.boundary.HomePage;
import com.virtualbankv2.boundary.TaskOverviewUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePageController {
    private HomePage view;
    private TaskOverviewUI task;

    /**
     * Constructor for HomePageController class.
     * @param view The view associated with the controller.
     */
    public HomePageController(HomePage view) {

        this.task = new TaskOverviewUI(); // 在构造函数中初始化hp
       // task.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        task.setVisible(false);
        this.view = view;
    }

    /**
     * Initializes the controller by adding event listeners to buttons in the view.
     */
    public void initializeController() {
        // Adds event listeners to buttons in the view
        //addReturnListenerToButton(view.getAccountButton(), new AccountOverviewPage());
        //addReturnListenerToButton(view.getTasksButton(), new testpage2("VB"));
        //addReturnListenerToButton(view.getGoalsButton(), new GoalManager("ltz"));
        //addReturnListenerToButton(view.getManualButton(), new testpage4("VB"));
    }

    /**
     * Adds an ActionListener to a button.
     * @param button The button to which the ActionListener will be added.
     * @param newPage The new page to be displayed when the button is clicked.
     */
    private void addReturnListenerToButton(JButton button, JFrame newPage) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                newPage.setVisible(true);
            }
        });
    }
}
