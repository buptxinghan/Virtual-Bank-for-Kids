package com.virtualbankv2.boundary;

import com.virtualbankv2.control.AccountManager;
import com.virtualbankv2.entity.Account;
import com.virtualbankv2.entity.ReturnButton;
import com.virtualbankv2.entity.RoundedButton;
import com.virtualbankv2.entity.Task;
import static com.virtualbankv2.boundary.Reader.accounts;
import static com.virtualbankv2.control.VirtualBankApplication.currentUser;
import static com.virtualbankv2.boundary.Reader.tasks;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * TaskOverviewUI class represents the user interface for managing tasks.
 * This class extends JFrame and implements ActionListener.
 *
 * @author Sun Lichen
 */

public class TaskOverviewUI extends JFrame implements ActionListener {
    private JPanel middlePanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    /**
     * Create a new rounded button with specific text and style.
     * @return JButton A new rounded button.
     */
    public static JButton createRoundButton() {
        RoundedButton button = new RoundedButton("<html><font size ='6'>Create new task</font></html>");
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(300, 50));
        button.setMinimumSize(new Dimension(200, 50));
        button.setMaximumSize(new Dimension(200, 50));
        return button;
    }
    private RoundedButton cnt = (RoundedButton) createRoundButton();
    /**
     * Constructor to initialize the TaskOverviewUI.
     */
    public TaskOverviewUI() {
        // Set frame properties
        setPreferredSize(new Dimension(1200,900));
        getContentPane().setBackground(new Color(199, 220, 247));
        getContentPane().setLayout(new BorderLayout());
        // Create top panel for title and buttons
        topPanel = new JPanel(new GridBagLayout());
        topPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        topPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(30, 20, 30,20);
        // Create bottom panel for return button
        bottomPanel = new JPanel();
        bottomPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        JLabel title = new JLabel("Task Management");
        title.setForeground(new Color(93, 97, 195));
        title.setFont(new Font("Arial", Font.BOLD, 45));
        addComponent(topPanel, gbc,title,2, 0, 0,GridBagConstraints.CENTER);
        JButton returnButton = ReturnButton.createReturnButton(this,"HomePage",new Dimension(120,50));
        addComponent(topPanel, gbc, cnt, 2,0,1,GridBagConstraints.CENTER);
        bottomPanel.add(returnButton);
        bottomPanel.setOpaque(false);
        // Create middle panel for displaying tasks
        middlePanel = new JPanel();
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
        middlePanel.setOpaque(false);
        setMiddlePanel(middlePanel);
        JScrollPane scrollPane = new JScrollPane(middlePanel);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBackground(new Color(199, 220, 247));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        // Add components to content pane
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        // Finalize frame setup
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cnt.addActionListener(this);
        this.setVisible(true);

    }
    /**
     * Add a component to the panel with GridBagConstraints.
     * @param panel The panel to add the component to.
     * @param gbc GridBagConstraints to set component position and alignment.
     * @param component The component to add.
     * @param gridwidth The number of cells the component will occupy horizontally.
     * @param gridx The column position of the component.
     * @param gridy The row position of the component.
     * @param horizontalAlignment The horizontal alignment of the component within its cell.
     */
    private void addComponent(JPanel panel,GridBagConstraints gbc,JComponent component,int gridwidth,int gridx,int gridy,int horizontalAlignment){
        gbc.gridwidth = gridwidth;
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.anchor = horizontalAlignment;
        panel.add(component, gbc);
    }
    /**
     * Sets up the middle panel with tasks for the current user.
     * If there are no tasks, it displays a "No Task" message.
     * If there are tasks, it creates and adds components for each task to the panel.
     *
     * @param panel The JPanel to be set up as the middle panel.
     */
    private void setMiddlePanel(JPanel panel) {
        panel.setOpaque(false);
        Reader reader = new Reader();

        // Check if the tasks list is empty
        if (tasks.isEmpty()) {
            JPanel noTaskPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
            noTaskPanel.setOpaque(false);

            // Display "No Task" message
            JLabel noTask = new JLabel("No Task");
            noTask.setForeground(new Color(93, 97, 195));
            noTask.setFont(new Font("Arial", Font.BOLD, 96));
            noTaskPanel.add(noTask);
            panel.add(noTaskPanel);
        } else {
            int i = 0;
            String currentUserUserName = currentUser.getUsername();

            // Iterate through the tasks and create components for tasks belonging to the current user
            for (Task task : tasks) {
                String taskUserName = task.getUserName();
                if (taskUserName.equals(currentUserUserName)) {
                    createTaskComponent(task, i, panel);
                    i++;
                }
            }
        }
    }
    /**
     * Create a component to display a task.
     * @param task The task to display.
     * @param i The index of the task.
     * @param container The container to add the component to.
     */
    private void createTaskComponent(Task task, int i, JPanel container) {
        String status = task.isCompleted() ? "finished" : "unfinished";
        JPanel outerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        outerPanel.setOpaque(false);
        JPanel horizontalPanel = new JPanel();
        horizontalPanel.setLayout(new BoxLayout(horizontalPanel, BoxLayout.X_AXIS));
        horizontalPanel.setOpaque(false);
        horizontalPanel.setPreferredSize(new Dimension(569, 102));
        horizontalPanel.setBorder(new HorizontalLineBorder(new Color(133, 149, 188), 1));
        JPanel pairContainer = new JPanel(new GridLayout(2, 1));
        pairContainer.setAlignmentY(Component.CENTER_ALIGNMENT);
        pairContainer.setOpaque(false);
        pairContainer.setPreferredSize(new Dimension(500, 100));

        JLabel titleLabel = new JLabel("Task " + (i + 1) + ": " + task.getTitle() + "(" + status + ")");
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        titleLabel.setOpaque(false);
        titleLabel.setForeground(new Color(133, 149, 188));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));

        JLabel descriptionLabel = new JLabel("Description: " + task.getDescription());
        descriptionLabel.setHorizontalAlignment(SwingConstants.LEFT);
        descriptionLabel.setOpaque(false);
        descriptionLabel.setForeground(new Color(112, 172, 249));
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        pairContainer.add(titleLabel);
        pairContainer.add(descriptionLabel);
        horizontalPanel.add(pairContainer, BorderLayout.WEST);
        if (!task.isCompleted()) {
            JButton button = new JButton("Finish");
            horizontalPanel.add(button, BorderLayout.EAST);
            button.addActionListener(e -> {
                String taskID = task.getTaskID();
                updateTaskIsCompletedInCSV(taskID);

                for (Account account : accounts) {
                    if (account.getUsername().equals(currentUser.getUsername()) && account.getStatus().equals("Active")) {
                        AccountManager accountManager = new AccountManager();
                        accountManager.transferIn(account, task.getReward());
                    }
                    break;
                }

                refreshUI();
            });
        }
        outerPanel.add(horizontalPanel);
        container.add(outerPanel);
    }
    /**
     * Update the completion status of a task in the CSV file.
     * @param taskID The description of the task.
     */
    private void updateTaskIsCompletedInCSV(String taskID) {
        String filePath = "src/Data/Tasks.csv";
        Path path = Paths.get(filePath);
        try {
            List<String> lines = Files.readAllLines(path);
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                String[] values = line.split(",");
                if (values.length > 1 && values[0].equals(taskID)) {
                    // Update the 'IsCompleted' value to true
                    values[3] = "true";
                    // Reconstruct the updated task data into a single line
                    StringBuilder updatedLine = new StringBuilder();
                    for (int j = 0; j < values.length; j++) {
                        updatedLine.append(values[j]);
                        if (j < values.length - 1) {
                            updatedLine.append(","); // Add comma separator
                        }
                    }
                    // Update the corresponding row in the list
                    lines.set(i, updatedLine.toString());
                    break;
                }
            }
            // Write back all the updated lines to the file
            Files.write(path, lines);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("failed");
        }
    }
    /**
     * Refreshes the user interface by clearing the current content,
     * updating the middle panel's content, and redrawing the interface.
     */
    public void refreshUI() {
        middlePanel.removeAll();
        setMiddlePanel(middlePanel);
        revalidate();
        repaint();
    }
    /**
     * Handles actions performed by the user interface components.
     * In this case, it opens the create task page and disposes the current window.
     *
     * @param e The ActionEvent that occurred.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        CreateTaskPage createTaskPage = new CreateTaskPage();
        this.dispose();
    }
    public RoundedButton getCntButton() {
        return cnt;
    }
}
