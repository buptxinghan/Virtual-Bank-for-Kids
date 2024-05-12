package com.virtualbankv2.entity;

import com.virtualbankv2.boundary.AccountInformationPage;
import com.virtualbankv2.boundary.GoalOverviewUI;
import com.virtualbankv2.boundary.HomePage;
import com.virtualbankv2.boundary.TaskOverviewUI;
import com.virtualbankv2.control.AccountOverviewPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Utility class for creating a return button with custom functionalities.
 */
public class ReturnButton {

    /**
     * Creates a return button with default size and no associated account.
     *
     * @param frame     The JFrame to which the button will be added.
     * @param lastPage  The identifier of the last page.
     * @return JButton  The created return button.
     */
    public static JButton createReturnButton(JFrame frame, String lastPage) {
        return createReturnButton(frame, lastPage, new Dimension(200, 50), null);
    }

    /**
     * Creates a return button with custom size and no associated account.
     *
     * @param frame     The JFrame to which the button will be added.
     * @param lastPage  The identifier of the last page.
     * @param dimension The preferred size of the button.
     * @return JButton  The created return button.
     */
    public static JButton createReturnButton(JFrame frame, String lastPage, Dimension dimension) {
        return createReturnButton(frame, lastPage, dimension, null);
    }

    /**
     * Creates a return button with custom size and an associated account.
     *
     * @param frame     The JFrame to which the button will be added.
     * @param lastPage  The identifier of the last page.
     * @param dimension The preferred size of the button.
     * @param account   The associated account.
     * @return JButton  The created return button.
     */
    public static JButton createReturnButton(JFrame frame, String lastPage, Dimension dimension, Account account) {
        RoundedButton button = new RoundedButton("<html><font size ='6'>Return</font></html>");
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(dimension);
        button.setMinimumSize(dimension);
        button.setMaximumSize(dimension);
        addReturnListenerToButton(button, frame, lastPage, account);
        return button;
    }

    /**
     * Adds an ActionListener to the provided button to handle return functionality.
     *
     * @param button    The button to which the ActionListener will be added.
     * @param frame     The JFrame associated with the button.
     * @param lastPage  The identifier of the last page.
     * @param account   The associated account.
     */
    private static void addReturnListenerToButton(JButton button, JFrame frame, String lastPage, Account account) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the current window

                // Determine the next page based on the last page identifier
                if (lastPage.equals("accountOverviewPage")) {
                    new AccountOverviewPage(); // Generate the previous page
                } else if (lastPage.equals("HomePage")) {
                    new HomePage();
                } else if (lastPage.equals("TaskOverviewUI")) {
                    new TaskOverviewUI();
                } else if (lastPage.equals("GoalOverviewUI")) {
                    new GoalOverviewUI();
                }
                if (lastPage.equals("accountInformationPage")) {
                    new AccountInformationPage(account);
                }
            }
        });
    }
}