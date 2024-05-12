package com.virtualbankv2.control;
import com.virtualbankv2.boundary.GoalOverviewUI;
import com.virtualbankv2.boundary.HomePage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePageController {
    private HomePage view;
<<<<<<< Updated upstream
=======
    private TaskOverviewUI task;
    // GoalOverviewUI goal;
>>>>>>> Stashed changes

    /**
     * Constructor for HomePageController class.
     * @param view The view associated with the controller.
     */
    public HomePageController(HomePage view) {
<<<<<<< Updated upstream
=======

        this.task = new TaskOverviewUI(); // 在构造函数中初始化hp
       // task.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        task.setVisible(false);
//        this.goal = new GoalOverviewUI();
//        goal.setVisible(false);
>>>>>>> Stashed changes
        this.view = view;
    }

    /**
     * Initializes the controller by adding event listeners to buttons in the view.
     */
    public void initializeController() {
        // Adds event listeners to buttons in the view
<<<<<<< Updated upstream
=======
        view.getAccountButton().addActionListener(e -> {
            view.dispose();
            new AccountOverviewPage();
        });
        view.getGoalsButton().addActionListener(e -> {
            view.dispose();
            GoalOverviewUI goal = new  GoalOverviewUI();
        });

        addReturnListenerToButton(view.getTasksButton(), task);
        //addReturnListenerToButton(view.getGoalsButton(), new testpage3("VB"));
>>>>>>> Stashed changes
        //addReturnListenerToButton(view.getAccountButton(), new AccountOverviewPage());
        //addReturnListenerToButton(view.getTasksButton(), new testpage2("VB"));
        //addReturnListenerToButton(view.getGoalsButton(),goal);
        //addReturnListenerToButton(view.getManualButton(), new testpage4("VB"));
    }

    /**
     * Adds an ActionListener to a button.
     * @param button The button to which the ActionListener will be added.
     * @param newPage The new page to be displayed when the button is clicked.
     */
    private void addReturnListenerToButton(JButton button, JFrame newPage) {
        newPage.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setVisible(false);
                newPage.setVisible(true);
            }
        });
    }
}
