package com.virtualbankv2.control;
import com.virtualbankv2.boundary.*;

/**
 * The HomePageController class is responsible for handling user interactions on the home page
 * of the Virtual Bank application. It manages the navigation between different sections of the
 * application such as account overview, goals, user manual, and tasks by responding to button
 * clicks.
 * Upon construction, the controller initializes the interest calculations by invoking the
 * InterestController. It then sets up the main view of the home page and adds action listeners
 * to the buttons, ensuring that the appropriate views are displayed when a user interacts with
 * the interface.
 * This class plays a pivotal role in the user experience by providing a seamless transition
 * between different functionalities of the application, contributing to an intuitive and
 * efficient navigation system.
 *
 * @author Feng Shiyu
 * @since 1.0
 */

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
