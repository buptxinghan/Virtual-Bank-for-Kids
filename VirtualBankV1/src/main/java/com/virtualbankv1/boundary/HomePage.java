package com.virtualbankv1.boundary;
// Homepage interface class

import com.virtualbankv1.control.AccountOverviewPage;
import com.virtualbankv1.control.HomePageController;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JFrame {

    private JButton accountButton; // Button for accessing the user's account
    private JButton tasksButton; // Button for accessing user's tasks
    private JButton goalsButton; // Button for accessing user's goals
    private JButton manualButton; // Button for accessing instruction manual

    /**
     * Constructor for HomePage class.
     * Initializes components and sets up the controller.
     */
    public HomePage() {
        initializeComponents();
        HomePageController controller = new HomePageController(this);
        controller.initializeController();
    }

    /**
     * Initializes the components of the homepage.
     * Sets up buttons, labels, and layout.
     */
    private void initializeComponents() {
        // Set window size
        setSize(new Dimension(1200, 900));
        setTitle("Virtual Bank");
        // Set background color
        Color bg = new Color(199, 220, 247);
        Color z1 = new Color(93,97,195);
        Color z2 = new Color(133,149,188);

        getContentPane().setBackground(bg);

        // Initialize buttons
        accountButton = new JButton("<html><font size=5 color=" + getColorHex(z1) + ">My account</font><br><font color=" + getColorHex(z2) + ">Check your account information</font></html>");
        tasksButton = new JButton("<html><font size=5 color=" + getColorHex(z1) + ">My tasks</font><br><font color=" + getColorHex(z2) + ">Check the tasks assigned</font></html>");
        goalsButton = new JButton("<html><font size=5 color=" + getColorHex(z1) + ">My goals</font><br><font color=" + getColorHex(z2) + ">Check and manage your goal</font></html>");
        manualButton = new JButton("<html><font size=5 color=" + getColorHex(z1) + ">Instruction manual</font><br><font color=" + getColorHex(z2) + ">Learn how to use the software or contact us</font></html>");

        accountButton.setHorizontalAlignment(SwingConstants.LEFT);
        tasksButton.setHorizontalAlignment(SwingConstants.LEFT);
        goalsButton.setHorizontalAlignment(SwingConstants.LEFT);
        manualButton.setHorizontalAlignment(SwingConstants.LEFT);

        accountButton.setBackground(bg);
        tasksButton.setBackground(bg);
        goalsButton.setBackground(bg);
        manualButton.setBackground(bg);

        // Initialize components
        JLabel l1 = new JLabel("<html><font size=7 color=" + getColorHex(z1) + ">Welcome to Your Virtual Bank!</font></html>",JLabel.CENTER);
        ImageIcon icon = new ImageIcon("src/Materials/pig.png");
        JLabel pic = new JLabel(icon,JLabel.CENTER);

        // Set layout

//        JPanel Center = new JPanel(new GridLayout(2, 1));
        RoundedPanel Center = new RoundedPanel(15);
        Center.setLayout(new GridLayout(2,1));
        RoundedPanel right = new RoundedPanel(15);
        RoundedPanel left = new RoundedPanel(15);
        RoundedPanel down = new RoundedPanel(15);
        RoundedPanel Centerdown = new RoundedPanel(15);
        Centerdown.setLayout(new GridLayout(4,1));
//        JPanel right = new JPanel();
//        JPanel left = new JPanel();
//        JPanel down = new JPanel();
//        JPanel Centerdown = new JPanel(new GridLayout(4, 1));

        Center.setBackground(bg);
        right.setBackground(bg);
        left.setBackground(bg);
        down.setBackground(bg);
        Centerdown.setBackground(bg);

        right.setPreferredSize(new Dimension(100, 100));
        left.setPreferredSize(new Dimension(100, 100));

        accountButton.addActionListener(e -> {
            this.dispose();
            new AccountOverviewPage();
        });

        Centerdown.add(accountButton);
        Centerdown.add(tasksButton);
        Centerdown.add(goalsButton);
        Centerdown.add(manualButton);

        Center.add(pic);
        Center.add(Centerdown);

        add(right, BorderLayout.EAST);
        add(l1, BorderLayout.NORTH);
        add(down, BorderLayout.SOUTH);
        add(left, BorderLayout.WEST);
        add(Center);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    /**
     * Converts a Color object to its corresponding hexadecimal representation.
     * @param color The Color object to convert.
     * @return The hexadecimal representation of the Color.
     */
    private String getColorHex(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    /**
     * Getter for the account button.
     * @return The account button.
     */
    public JButton getAccountButton() {
        return accountButton;
    }

    /**
     * Getter for the tasks button.
     * @return The tasks button.
     */
    public JButton getTasksButton() {
        return tasksButton;
    }

    /**
     * Getter for the goals button.
     * @return The goals button.
     */
    public JButton getGoalsButton() {
        return goalsButton;
    }

    /**
     * Getter for the manual button.
     * @return The manual button.
     */
    public JButton getManualButton() {
        return manualButton;
    }

}
