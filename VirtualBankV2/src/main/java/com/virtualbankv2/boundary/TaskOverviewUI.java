package com.virtualbankv2.boundary;

import com.virtualbankv2.entity.Task;
import com.virtualbankv2.entity.User;
import static com.virtualbankv2.control.VirtualBankApplication.currentUser;
import static com.virtualbankv2.boundary.Reader.tasks;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskOverviewUI extends JFrame implements ActionListener {
    private JPanel middlePanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JLabel title;
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

    // Constructor
    public TaskOverviewUI() {
        setPreferredSize(new Dimension(1200,900));
        getContentPane().setBackground(new Color(199, 220, 247));
        JPanel panel = new JPanel(new GridBagLayout());
        topPanel = new JPanel(new GridBagLayout());
        topPanel.setBackground(new Color(199, 220, 247));
        topPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(15, 15, 15, 15);
        bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(199, 220, 247));
        bottomPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        addComponent(topPanel, gbc,title = new JLabel("Task Management"), new Font("Arial", Font.BOLD, 45), new Color(93, 97, 195),2, 0, 0,GridBagConstraints.CENTER);
        JButton returnButton = ReturnButton.createReturnButton(this,"HomePage",new Dimension(120,50));
        addComponent(topPanel, gbc, cnt, 2,0,1,GridBagConstraints.CENTER);
        bottomPanel.add(returnButton);
        middlePanel = new JPanel(new GridBagLayout());
        middlePanel.setBackground(new Color(199, 220, 247));
        setMiddlePanel(middlePanel);
        addComponent(panel,gbc,topPanel,2,0,0,GridBagConstraints.CENTER);
        addComponent(panel,gbc,middlePanel,2,0,1,GridBagConstraints.CENTER);
        addComponent(panel,gbc,bottomPanel,2,0,2,GridBagConstraints.CENTER);
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cnt.addActionListener(this);
        this.setVisible(true);

    }
    private void addComponent(JPanel panel, GridBagConstraints gbc, JLabel label, Font font, Color color, int gridwidth, int gridx, int gridy, int horizontalAlignment) {
        label.setFont(font);
        label.setForeground(color);
        gbc.gridwidth = gridwidth;
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.anchor = horizontalAlignment;
        panel.setBackground(new Color(199, 220, 247));
        panel.add(label, gbc);
    }
    private void addComponent(JPanel panel, GridBagConstraints gbc, RoundedButton label,  int gridwidth, int gridx, int gridy, int horizontalAlignment) {
        gbc.gridwidth = gridwidth;
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.anchor = horizontalAlignment;
        panel.setBackground(new Color(199, 220, 247));
        panel.add(label, gbc);
    }
    private void addComponent(JPanel panel, GridBagConstraints gbc, JPanel smallPanel, int gridwidth,int gridx, int gridy,int horizontalAlignment) {
        gbc.gridwidth = gridwidth;
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.anchor = horizontalAlignment;
        panel.setBackground(new Color(199, 220, 247));
        panel.add(smallPanel, gbc);
    }
    private void setMiddlePanel(JPanel panel){
        Reader reader=new Reader();
        String currentUserUserName = currentUser.getUsername();
        for(Task task : tasks){
            String taskUserName = task.getUserName();
        }

    }

    public static void main(String[] args) {
        TaskOverviewUI taskOverviewUI= new TaskOverviewUI();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
//跳转
    }
}
