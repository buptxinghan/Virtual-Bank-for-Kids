package com.virtualbankv2.boundary;

import com.virtualbankv2.entity.Goal;
import com.virtualbankv2.entity.RoundedButton;
import com.virtualbankv2.entity.ReturnButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.virtualbankv2.control.VirtualBankApplication.currentUser;

public class CreateGoalUI extends JFrame {
    private JTextField goalNameField = new JTextField();
    private JTextField descriptionField = new JTextField();
    private JTextField targetAmountField = new JTextField();
    private RoundedButton saveButton;
    private JButton returnButton;
    private String currentUsername = currentUser.getUsername();

    public CreateGoalUI() {
        createUI();
    }

    private void createUI() {
        setTitle("Create My Goal");
        getContentPane().setBackground(new Color(199, 220, 247));
        setSize(1200, 900); // Set frame size
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); // Set layout manager

        Font font1 = new Font("Arial", Font.BOLD, 40);
        Font font2 = new Font("Arial",Font.BOLD,40);

        // Top panel
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(199, 220, 247));
        JLabel headerLabel = new JLabel("<html><font size=12 color=" + getColorHex(new Color(93, 97, 195)) + ">Create a Goal</font></html>", JLabel.CENTER);
        topPanel.add(headerLabel);
        add(topPanel, BorderLayout.NORTH);

        // Middle panel
//        JPanel middlePanel = new JPanel(new GridLayout(6, 1, 10, 10));
//        middlePanel.setBackground(new Color(199, 220, 247));
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridBagLayout());
        middlePanel.setBackground(new Color(199, 220, 247));
        middlePanel.setBorder(BorderFactory.createEmptyBorder(0,150,50,150));
        addLabelAndTextField("Goal Name", goalNameField, middlePanel, font2);
        addLabelAndTextField("Target Amount", targetAmountField, middlePanel, font2);
        addLabelAndTextField("Description", descriptionField, middlePanel, font2);

        add(middlePanel, BorderLayout.CENTER);

        // Bottom panel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,80,10));
        bottomPanel.setBackground(new Color(199, 220, 247));
        saveButton = new RoundedButton("<html><font size='6'>Save</font></html>");
        saveButton.setBackground(new Color(70, 130, 180));
        saveButton.setForeground(Color.WHITE);
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.setPreferredSize(new Dimension(200,50));
        saveButton.setMinimumSize(new Dimension(200, 50));
        saveButton.setMaximumSize(new Dimension(200, 50));
        returnButton = ReturnButton.createReturnButton(this, "GoalOverviewUI");
        bottomPanel.add(saveButton);
        bottomPanel.add(returnButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Action Listener for Save Button
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveGoal();
            }
        });
        setVisible(true);
    }

    private void addLabelAndTextField(String labelText, JTextField textField, JPanel panel, Font font) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // 上、左、下、右的间距
        gbc.gridx = 0;  // X坐标
        gbc.gridy = GridBagConstraints.RELATIVE;  // 自动递增Y坐标
        gbc.weightx = 1.0;  // X方向的权重，1.0意味着可以拉伸

        JLabel label = new JLabel("<html><font size=6 color=" + getColorHex(new Color(93, 97, 195)) + ">" + labelText + "</font></html>", JLabel.LEFT);
        label.setFont(font);
        panel.add(label, gbc);

        gbc.gridy = GridBagConstraints.RELATIVE;  // 继续自动递增Y坐标
        textField.setFont(font);
//        textField.setPreferredSize(textFieldSize);
//        textField.setMinimumSize(textFieldSize);
//        textField.setMaximumSize(textFieldSize);
        panel.add(textField, gbc);
    }

//    private void addLabelAndTextField(String labelText, JTextField textField, JPanel panel, Font font,Dimension textFieldSize) {
//        JLabel label = new JLabel("<html><font size=6 color=" + getColorHex(new Color(93, 97, 195)) + ">" + labelText + "</font></html>", JLabel.LEFT);
//        label.setFont(font);
//        panel.add(label);
//        textField.setFont(font);
//        textField.setPreferredSize(textFieldSize);
//        textField.setMinimumSize(textFieldSize);
//        textField.setMaximumSize(textFieldSize);
//        panel.add(textField);
//    }

    /**
     * Retrieves the color hex code of the specified color.
     *
     * @param color The Color object.
     * @return A String representing the color hex code.
     */
    private String getColorHex(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    private void saveGoal() {
        try {
            String goalName = goalNameField.getText();
            String description = descriptionField.getText();
            double targetAmount = Double.parseDouble(targetAmountField.getText());
            double currentAmount = 0; // Assuming the logic to fetch current amount is defined elsewhere

            // Create a Goal object
            Goal goal = new Goal(goalName, description, targetAmount, currentAmount, currentUsername);

            // Logic to save the goal (e.g., database or file system)
            Writer writer = new Writer();
            writer.writeSingleGoal(goal);
            JOptionPane.showOptionDialog(
                    this,
                    "Goal saved successfully!",
                    "Success",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    new String[] {"OK"},
                    "OK"
            );
            goalNameField.setText("");
            descriptionField.setText("");
            targetAmountField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showOptionDialog(
                    this,
                    "Please enter a valid number for target amount.",
                    "Error",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE,
                    null,
                    new String[] {"OK"},
                    "OK"
            );
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error saving the goal: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public JTextField getGoalNameField() {
        return goalNameField;
    }

    public JTextField getDescriptionField() {
        return descriptionField;
    }

    public JTextField getTargetAmountField() {
        return targetAmountField;
    }

    public JButton getSaveButton() {
        return saveButton;
    }
}
