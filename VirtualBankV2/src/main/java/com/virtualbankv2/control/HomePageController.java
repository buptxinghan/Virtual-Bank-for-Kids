package com.virtualbankv2.control;
import com.virtualbankv2.boundary.*;

public class HomePageController {
    private HomePage view;

    /**
     * Constructor for HomePageController class.
     * @param view The view associated with the controller.
     */
    public HomePageController(HomePage view) {

        new InterestController().getInterest();
        this.view = view;
    }

    /**
     * Initializes the controller by adding event listeners to buttons in the view.
     */
    public void initializeController() {
        // Adds event listeners to buttons in the view
        view.getAccountButton().addActionListener(e -> {
            view.dispose();
            new AccountOverviewPage();
        });

        view.getGoalsButton().addActionListener(e -> {
            view.dispose();
            new  GoalOverviewUI();
        });

        view.getManualButton().addActionListener(e -> {
            view.dispose();
            new UserManual();
        });

        view.getTasksButton().addActionListener(e -> {
            view.dispose();
            new TaskOverviewUI();
        });
    }

}
