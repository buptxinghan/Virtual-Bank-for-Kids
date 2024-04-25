package com.virtualbankv1.control;
import com.virtualbankv1.boundary.AccountOverviewUI;
import com.virtualbankv1.boundary.HomePage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePageController {
    private HomePage view;

    /**
     * Constructor for HomePageController class.
     * @param view The view associated with the controller.
     */
    public HomePageController(HomePage view) {
        this.view = view;
    }

    /**
     * Initializes the controller by adding event listeners to buttons in the view.
     */
    public void initializeController() {
        // Adds event listeners to buttons in the view
        //addReturnListenerToButton(view.getAccountButton(), new AccountOverviewPage());
        //addReturnListenerToButton(view.getTasksButton(), new testpage2("VB"));
        //addReturnListenerToButton(view.getGoalsButton(), new testpage3("VB"));
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
