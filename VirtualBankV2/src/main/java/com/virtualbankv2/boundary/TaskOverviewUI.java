package com.virtualbankv2.boundary;

import com.virtualbankv2.control.AccountManager;
import com.virtualbankv2.entity.Account;
import com.virtualbankv2.entity.Task;
import com.virtualbankv2.entity.User;

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
        gbc.insets = new Insets(30, 20, 30,20);
        bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(199, 220, 247));
        bottomPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        addComponent(topPanel, gbc,title = new JLabel("Task Management"), new Font("Arial", Font.BOLD, 45), new Color(93, 97, 195),2, 0, 0,GridBagConstraints.CENTER);
        JButton returnButton = ReturnButton.createReturnButton(this,"HomePage",new Dimension(120,50));
        addComponent(topPanel, gbc, cnt, 2,0,1,GridBagConstraints.CENTER);
        bottomPanel.add(returnButton);
        middlePanel = new JPanel();
        middlePanel.setBackground(new Color(199, 220, 247));
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
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
        //User currentUser=new User("Zhong Zhenghan","111111");
        Reader reader=new Reader();
        int i=0;
        String currentUserUserName = currentUser.getUsername();
        for(Task task : tasks){
            String taskUserName = task.getUserName();
            if (taskUserName.equals(currentUserUserName)) {
                // 用户名相符的情况下的处理逻辑
                createTaskComponent(task,i,panel);
                i++;
            }
        }
    }
    private void createTaskComponent(Task task, int i, JPanel container) {
        String status;
        if(task.isCompleted()){
            status=new String("finished");
        }
        else{
            status=new String("unfinished");
        }
        JPanel horizontalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // 使用 FlowLayout，左对齐
        horizontalPanel.setOpaque(false);
        // 创建一个容器用于垂直排列两个小组件
        JPanel pairContainer = new JPanel(new GridLayout(2, 1)); // 使用网格布局，两行一列
        pairContainer.setAlignmentX(Component.CENTER_ALIGNMENT); // 设置容器水平居中对齐
        //pairContainer.setPreferredSize(new Dimension(300, 200));
        pairContainer.setOpaque(false);

        // 创建第一个小组件（标题）
        JLabel titleLabel = new JLabel("Task " + (i + 1) + ": " + task.getTitle()+"("+status+")");
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT); // 设置文本左对齐
        titleLabel.setOpaque(false); // 设置标签背景透明
        titleLabel.setForeground(new Color(133, 149, 188));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        // 创建第二个小组件（描述）
        JLabel descriptionLabel = new JLabel("Description: " + task.getDescription());
        descriptionLabel.setHorizontalAlignment(SwingConstants.LEFT); // 设置文本左对齐
        descriptionLabel.setOpaque(false);
        descriptionLabel.setForeground(new Color(112, 172, 249));
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        pairContainer.add(titleLabel);
        pairContainer.add(descriptionLabel);
        horizontalPanel.add(pairContainer);
        if(!task.isCompleted()) {
            JButton button = new JButton("Finish");
            horizontalPanel.add(button);
            button.addActionListener(e -> {
                String description = task.getDescription();
                updateTaskIsCompletedInCSV(description);
                //还有transferin
                for (Account account : accounts) {
                    if (account.getUsername().equals(currentUser.getUsername())&&account.getStatus().equals("Active")) {
                        AccountManager accountManager=new AccountManager();
                        accountManager.transferIn(account,task.getReward());
                    }
                    break;
                }


                //
                refreshUI();
            });
        }
        // 将两个小组件添加到容器中


        // 将一对小组件的容器添加到整体容器中
        container.add(horizontalPanel);
    }
    private void updateTaskIsCompletedInCSV(String description) {
        String filePath = "src/Data/Tasks.csv"; // CSV 文件路径
        Path path = Paths.get(filePath);

        try {
            List<String> lines = Files.readAllLines(path);
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                String[] values = line.split(",");
                if (values.length > 1 && values[1].equals(description)) { // 根据描述来定位需要修改的行
                    // 更新 IsCompleted 值为 true
                    values[3] = "true";

                    // 将更新后的任务数据重新组合成一行
                    StringBuilder updatedLine = new StringBuilder();
                    for (int j = 0; j < values.length; j++) {
                        updatedLine.append(values[j]);
                        if (j < values.length - 1) {
                            updatedLine.append(","); // 添加逗号分隔符
                        }
                    }

                    // 更新列表中的对应行
                    lines.set(i, updatedLine.toString());
                    break;
                }
            }

            // 将更新后的所有行写回到文件中
            Files.write(path, lines);
            System.out.println("CSV 文件更新成功。");
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("CSV 文件更新失败。");
        }
    }
    public void refreshUI() {
        // 清空当前界面的内容
        middlePanel.removeAll();

        // 重新设置中间面板的内容
        setMiddlePanel(middlePanel);

        // 重新绘制界面
        revalidate();
        repaint();
    }
    //public static void main(String[] args) {
        //TaskOverviewUI taskOverviewUI= new TaskOverviewUI();
    //}
    @Override
    public void actionPerformed(ActionEvent e) {
        CreateTaskPage createTaskPage=new CreateTaskPage();
        this.dispose();
    }
}
