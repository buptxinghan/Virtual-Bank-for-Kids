package com.virtualbankv2.boundary;

import com.virtualbankv2.control.HomePageController;
import com.virtualbankv2.entity.RoundedButton;

import javax.swing.*;
import java.awt.*;
/**
 * HomePage class provides a home page for the Virtual Bank application.
 * It contains buttons to navigate to different sections such as account information,
 * tasks, goals, and an instruction manual.
 * This class is part of the boundary layer in the application architecture,
 * interacting with the user and delegating user actions to the control layer.
 *
 * @author Feng Shiyu
 * @since 1.0
 */

public class HomePage extends JFrame {

    private JButton accountButton; // Button to access the user's account information
    private JButton tasksButton;   // Button to access the user's tasks
    private JButton goalsButton;   // Button to access and manage user's goals
    private JButton manualButton;  // Button to access the instruction manual

    /**
     * Constructor for HomePage class.
     * Initializes components and sets up the controller.
     */
    public HomePage() {
        initializeComponents(); // Initialize the UI components
        HomePageController controller = new HomePageController(this); // Set up the controller
        controller.initializeController(); // Initialize the controller
    }

    /**
     * Initializes the components of the homepage.
     * Sets up buttons, labels, and layout.
     */
    private void initializeComponents() {
        setSize(new Dimension(1200, 900)); // Set the window size
        setTitle("Virtual Bank"); // Set the window title
        Color bg = new Color(199, 220, 247); // Background color
        Color z1 = new Color(93, 97, 195); // Primary color for button text
        Color z2 = new Color(133, 149, 188); // Secondary color for button text

        getContentPane().setBackground(bg); // Set the background color of the content pane

        // Create buttons with specified titles and subtitles
        accountButton = createButton("My account", "Check your account information", z1, z2, bg);
        tasksButton = createButton("My tasks", "Check the tasks assigned", z1, z2, bg);
        goalsButton = createButton("My goals", "Check and manage your goal", z1, z2, bg);
        manualButton = createButton("Instruction manual", "Learn how to use the software or contact us", z1, z2, bg);

        // Create welcome label
        JLabel welcomeLabel = createLabel("Welcome to Your Virtual Bank!", z1);
        // Create image label
        JLabel picLabel = createImageLabel("src/Materials/pig.png", bg);

        // Create center panel to hold the main content
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical alignment
        centerPanel.setBackground(bg); // Set background color

        // Create panel to hold buttons
        JPanel buttonPanel = createButtonPanel(bg, accountButton, tasksButton, goalsButton, manualButton);

        // Add spacing and components to center panel
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(picLabel);
        centerPanel.add(buttonPanel);

        // Add components to the main frame
        add(createPanel(new Dimension(250, 250), bg), BorderLayout.EAST); // Add empty panel on the right
        add(welcomeLabel, BorderLayout.NORTH); // Add welcome label at the top
        add(createPanel(new Dimension(0, 100), bg), BorderLayout.SOUTH); // Add empty panel at the bottom
        add(createPanel(new Dimension(250, 250), bg), BorderLayout.WEST); // Add empty panel on the left
        add(centerPanel); // Add center panel in the middle

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true); // Make the window visible
    }

    /**
     * Creates a button with specified title, subtitle, and colors.
     *
     * @param title The main title of the button.
     * @param subtitle The subtitle of the button.
     * @param titleColor The color of the title text.
     * @param subtitleColor The color of the subtitle text.
     * @param bgColor The background color of the button.
     * @return The created JButton.
     */
    private JButton createButton(String title, String subtitle, Color titleColor, Color subtitleColor, Color bgColor) {
        JButton button = new RoundedButton(String.format(
                "<html><font size=7 color=%s>%s</font><br><font size=6 color=%s>%s</font></html>",
                getColorHex(titleColor), title, getColorHex(subtitleColor), subtitle));
        button.setHorizontalAlignment(SwingConstants.LEFT); // Align text to the left
        button.setBackground(bgColor); // Set button background color
        return button;
    }

    /**
     * Creates a label with specified text and color.
     *
     * @param text The text to be displayed on the label.
     * @param color The color of the text.
     * @return The created JLabel.
     */
    private JLabel createLabel(String text, Color color) {
        JLabel label = new JLabel(String.format("<html><font color=%s>%s</font></html>", getColorHex(color), text), JLabel.CENTER);
        label.setFont(new Font("Serif", Font.BOLD, 60)); // Set font style and size
        return label;
    }

    /**
     * Creates a label with an image from the specified path.
     *
     * @param imagePath The path to the image.
     * @param bgColor The background color of the label's container.
     * @return The created JLabel with the image.
     */
    private JLabel createImageLabel(String imagePath, Color bgColor) {
        JLabel label = new JLabel(new ImageIcon(imagePath), JLabel.CENTER);
        JPanel picPanel = new JPanel();
        picPanel.add(label); // Add image label to panel
        picPanel.setBackground(bgColor); // Set background color
        picPanel.setMaximumSize(new Dimension(300, 100)); // Set maximum size
        return label;
    }

    /**
     * Creates a panel with the specified size and background color.
     *
     * @param size The preferred size of the panel.
     * @param bgColor The background color of the panel.
     * @return The created JPanel.
     */
    private JPanel createPanel(Dimension size, Color bgColor) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(size); // Set preferred size
        panel.setBackground(bgColor); // Set background color
        return panel;
    }

    /**
     * Creates a panel containing the specified buttons.
     *
     * @param bgColor The background color of the button panel.
     * @param buttons The buttons to be added to the panel.
     * @return The created JPanel containing the buttons.
     */
    private JPanel createButtonPanel(Color bgColor, JButton... buttons) {
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1)); // Use GridLayout with 4 rows
        buttonPanel.setBackground(bgColor); // Set background color

        for (JButton button : buttons) {
            JPanel buttonContainer = new JPanel();
            buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical alignment
            buttonContainer.setBackground(bgColor); // Set background color
            buttonContainer.add(button); // Add button to container
            buttonContainer.add(new JSeparator(SwingConstants.HORIZONTAL)); // Add separator below button
            buttonPanel.add(buttonContainer); // Add container to button panel
        }
        return buttonPanel;
    }

    /**
     * Converts a Color object to its corresponding hexadecimal representation.
     *
     * @param color The Color object to convert.
     * @return The hexadecimal representation of the Color.
     */
    private String getColorHex(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    /**
     * Getter for the account button.
     *
     * @return The account button.
     */
    public JButton getAccountButton() {
        return accountButton;
    }

    /**
     * Getter for the tasks button.
     *
     * @return The tasks button.
     */
    public JButton getTasksButton() {
        return tasksButton;
    }

    /**
     * Getter for the goals button.
     *
     * @return The goals button.
     */
    public JButton getGoalsButton() {
        return goalsButton;
    }

    /**
     * Getter for the manual button.
     *
     * @return The manual button.
     */
    public JButton getManualButton() {
        return manualButton;
    }
}
