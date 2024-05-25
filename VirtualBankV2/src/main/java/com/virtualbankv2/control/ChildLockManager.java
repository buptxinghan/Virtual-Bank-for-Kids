package com.virtualbankv2.control;

import com.virtualbankv2.boundary.*;
import com.virtualbankv2.boundary.ChildLock;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The ChildLockManager class is part of the control package of the VirtualBankV2 application.
 * It is responsible for managing the child lock functionality of the application.
 *
 * @author Botong Wu
 * @version 2.0
 * @since 2024-05-01
 */
public class ChildLockManager {
    /**
     * Adds a button with child lock functionality to the given frame.
     * When the button is clicked, a ChildLock is created and displayed.
     * If the user enters the correct answer in the ChildLock, the frame is disposed and the specified page is opened.
     *
     * @param frame The frame to which the button is added.
     * @param button The button to which the child lock functionality is added.
     * @param pageName The name of the page to open if the user enters the correct answer in the ChildLock.
     */
    public void addButtonWithChildLock(JFrame frame, JButton button, String pageName) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChildLock childLock = new ChildLock();

                JButton checkButton = childLock.getCheckButton();
                JTextField userText = childLock.getUserText();
                checkButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if ("111".equals(userText.getText().trim())) {
                            frame.dispose();
                            childLock.dispose();
                            switch (pageName) {
                                case "OpenAccountPage" -> new OpenAccountPage(); // Generate the previous page
                                case "CreateTaskPage" -> new CreateTaskPage();
                            }
                        } else {
                            try {
                                //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                            } catch (Exception ee) {
                                ee.printStackTrace();
                            }
                            JOptionPane.showOptionDialog(
                                    childLock,
                                    "Answer incorrect, please try again!",
                                    "Error",
                                    JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.ERROR_MESSAGE,
                                    null,
                                    new String[] {"OK"},
                                    "OK"
                            );

                        }
                    }
                });
            }
        });
    }


}
