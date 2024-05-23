package com.virtualbankv2.boundary;

import javax.swing.*;
import java.awt.*;

import com.virtualbankv2.entity.ReturnButton;
import com.virtualbankv2.entity.RoundedButton;
import com.virtualbankv2.entity.RoundedPanel;
import com.virtualbankv2.entity.RoundedPasswordField;
import com.virtualbankv2.control.SignUpController;
import com.virtualbankv2.entity.*;

/**
 * The {@code SignUpPage} class represents the sign-up page for the Virtual Bank application.
 * It extends {@link JFrame} and provides the GUI components and logic for user sign-up.
 *
 * @version 2.0
 * @since 2024-04-20
 * @author Tianzhi Li
 *
 */
public class SignUpPage extends JFrame {

    private JTextField nameField;
    private JPasswordField passwordField, confirmPasswordField;
    private JButton submitButton, returnButton;
    private JLabel nameLabel, passwordLabel, confirmPasswordLabel;
    private Font fieldFont = new Font("Arial", Font.BOLD, 25); // Font for fields and buttons
    private Color lightBlue = new Color(199, 220, 247); // Light blue background
    private Color deepBlue = new Color(93, 97, 195); // Deep blue for buttons
    private SignUpController signUpController;

    /**
     * Constructs a new {@code SignUpPage} object and initializes the GUI components.
     */
    public SignUpPage() {
        signUpController = new SignUpController();

        setTitle("Sign Up");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1200, 900));
        setLayout(new BorderLayout());

        // Create a main panel with GridBagLayout to hold the login panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(lightBlue); // Different color from the login panel
        GridBagConstraints gbcMain = new GridBagConstraints();
        gbcMain.fill = GridBagConstraints.BOTH;
        gbcMain.weightx = 1;
        gbcMain.weighty = 1;
        gbcMain.insets = new Insets(200, 200, 200, 200); // Margin around the login panel

        RoundedPanel loginPanel = new RoundedPanel(20);
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setPreferredSize(new Dimension(400, 300));
        loginPanel.setMaximumSize(new Dimension(400, 300));
        loginPanel.setMinimumSize(new Dimension(400, 300));
        loginPanel.setBackground(new Color(133, 149, 188)); // Set the login panel color
        GridBagConstraints gbcLogin = new GridBagConstraints();

        // Username panel
        nameLabel = new JLabel("Username *");
        nameField = new RoundedTextField(10);
        setupField(nameLabel, nameField, loginPanel, gbcLogin, 0);

        // Password panel
        passwordLabel = new JLabel("Password *");
        passwordField = new RoundedPasswordField(10);
        setupField(passwordLabel, passwordField, loginPanel, gbcLogin, 1);

        // Confirm Password panel
        confirmPasswordLabel = new JLabel("Confirm password *");
        confirmPasswordField = new RoundedPasswordField(10);
        setupField(confirmPasswordLabel, confirmPasswordField, loginPanel, gbcLogin, 2);

        // Submit button
        submitButton = new RoundedButton("Submit");
        submitButton.setPreferredSize(new Dimension(200, 50));
        setupButton(submitButton, loginPanel, gbcLogin, 0, 3);

        // Return button
        returnButton = ReturnButton.createReturnButton(this, "LoginPage");
        setupButton(returnButton, loginPanel, gbcLogin, 1, 3);

        // Add the login panel to the main panel
        mainPanel.add(loginPanel, gbcMain);
        add(mainPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        submitButton.addActionListener(e -> performSignUp());
    }

    /**
     * Sets up a label and text field within the given panel.
     *
     * @param label the label for the field
     * @param field the text field
     * @param panel the panel to add the components to
     * @param gbc the GridBagConstraints for layout
     * @param gridy the row position in the grid
     */
    private void setupField(JLabel label, JTextField field, JPanel panel, GridBagConstraints gbc, int gridy) {
        label.setFont(fieldFont);
        field.setFont(fieldFont);
        gbc.gridx = 0;
        gbc.gridy = gridy;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(label, gbc);
        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    /**
     * Sets up a button within the given panel.
     *
     * @param button the button to set up
     * @param panel the panel to add the button to
     * @param gbc the GridBagConstraints for layout
     * @param gridx the column position in the grid
     * @param gridy the row position in the grid
     */
    private void setupButton(JButton button, JPanel panel, GridBagConstraints gbc, int gridx, int gridy) {
        button.setFont(fieldFont);
        button.setBackground(deepBlue);
        button.setForeground(Color.WHITE);
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(button, gbc);
    }

    /**
     * Performs the sign-up action when the user presses the submit button.
     * Validates the input fields and shows a success or error message.
     * If successful, clears the fields and navigates back to the login page.
     */
    private void performSignUp() {
        String userName = nameField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        boolean success = signUpController.performSignUp(userName, password, confirmPassword);
        if (success) {
            int response = JOptionPane.showOptionDialog(
                    this,
                    "User created successfully!",
                    "Success",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    new String[] {"OK"},
                    "OK"
            );
            // Clear the text fields
            nameField.setText("");
            passwordField.setText("");
            confirmPasswordField.setText("");

            // Check if "OK" was pressed
            if (response == 0) {
                dispose(); // Close the SignUpPage
                new LoginPage(); // Open the LoginPage
            }
        } else {
            JOptionPane.showOptionDialog(
                    this,
                    "Error: All fields are required and passwords must match.",
                    "Error",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE,
                    null,
                    new String[] {"OK"},
                    "OK"
            );

        }
    }

    /**
     * Sets the text of the name field.
     *
     * @param text the text to set in the name field
     */
    public void setNameFieldText(String text) {
        nameField.setText(text);
    }

    /**
     * Sets the text of the password field.
     *
     * @param text the text to set in the password field
     */
    public void setPasswordFieldText(String text) {
        passwordField.setText(text);
    }

    /**
     * Sets the text of the confirm password field.
     *
     * @param text the text to set in the confirm password field
     */
    public void setConfirmPasswordFieldText(String text) {
        confirmPasswordField.setText(text);
    }

    /**
     * Simulates a click on the submit button.
     */
    public void clickSubmitButton() {
        submitButton.doClick();
    }

    /**
     * Checks if the submit button is pressed.
     *
     * @return {@code true} if the submit button is pressed, {@code false} otherwise
     */
    public boolean isSubmitButtonPressed() {
        return submitButton.getModel().isPressed();
    }
}
