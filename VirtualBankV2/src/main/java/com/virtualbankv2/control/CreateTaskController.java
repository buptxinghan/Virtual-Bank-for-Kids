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

/**
 * The CreateTaskController class is responsible for managing the interactions
 * between the CreateTaskPage view and the underlying task creation logic.
 * It handles user input from the view, validates the input fields, and if valid,
 * creates a task, writes it using the Writer class, and displays a success message.
 * If the input validation fails, it displays an error message.
 * This class also initializes the controller by setting up event listeners and
 * window closing behavior, as well as closing the current view and returning to
 * the TaskOverviewUI. It includes methods to validate the task title and reward
 * input to ensure the data entered by the user is valid.
 *
 * @author Feng Shiyu
 * @since 2.0
 */
public class CreateTaskController {

    private CreateTaskPage view;
    Writer writer = new Writer();

    /**
     * Constructs a CreateTaskController with the specified view.
     *
     * @param view the CreateTaskPage view to be controlled
     */
    public CreateTaskController(CreateTaskPage view) {
        this.view = view;
    }

    /**
     * Creates a new task based on the user input from the view.
     * Validates the input fields and, if valid, creates a task, writes it using
     * the Writer class, and displays a success message.
     * If input validation fails, displays an error message.
     */
    public void createTask() {
        String title = view.getTitleField().getText();
        String re = view.getReward().getText();
        String description = view.getContentArea().getText();
        String start = view.getStart();
        String end = view.getEnd();
        String name = currentUser.getUsername();

        if (titleIsValid() && rewardIsValid()) {
            totalcounter = totalcounter + 1;
            double reward = Double.parseDouble(re);
            Task temptask = new Task(description, reward, title, start, end, name);
            writer.writeSingleTask(temptask);
            UIManager.put("OptionPane.cancelButtonText", "Cancel");
            UIManager.put("OptionPane.okButtonText", "OK");
            JOptionPane.showMessageDialog(view, "Task created successfully!");
        } else {
            UIManager.put("OptionPane.cancelButtonText", "Cancel");
            UIManager.put("OptionPane.okButtonText", "OK");
            JOptionPane.showMessageDialog(view, "Please fill in the required field with the correct format.");
        }
    }

    /**
     * Initializes the controller by setting up event listeners and window closing
     * behavior.
     */
    public void initializeController() {
        view.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addReturnListenerToButton(view.getSaveButton());
        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                view.dispose();
                new TaskOverviewUI();
            }
        });
    }

    /**
     * Adds an action listener to the specified button that triggers task creation
     * and returns to the TaskOverviewUI if the task creation is successful.
     *
     * @param button the button to which the listener is added
     */
    private void addReturnListenerToButton(RoundedButton button) {
        button.addActionListener(e -> {
            createTask();
            if (titleIsValid() && rewardIsValid()) {
                view.dispose();
                new TaskOverviewUI();
            }
        });
    }

    /**
     * Validates the task title input.
     *
     * @return true if the title is not null and not empty; false otherwise
     */
    private boolean titleIsValid() {
        String title = view.getTitleField().getText();
        return title != null && !title.trim().isEmpty();
    }

    /**
     * Validates the task reward input.
     *
     * @return true if the reward is not null, not empty, and a valid number; false otherwise
     */

    private boolean rewardIsValid() {
        String re = view.getReward().getText();
        if (re == null || re.trim().isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(re);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
