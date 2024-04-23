package com.virtualbankv1.control;
import com.virtualbankv1.boundary.AccountOverviewUI;
import com.virtualbankv1.boundary.HomePage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePageController {
    private HomePage view;

    public HomePageController(HomePage view) {
        this.view = view;
    }

    public void initializeController() {
        // 为view中的按钮添加事件监听器
        //addReturnListenerToButton(view.getAccountButton(), new AccountOverviewPage());
        //addReturnListenerToButton(view.getTasksButton(), new testpage2("VB"));
        //addReturnListenerToButton(view.getGoalsButton(), new testpage3("VB"));
        //addReturnListenerToButton(view.getManualButton(), new testpage4("VB"));
    }

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