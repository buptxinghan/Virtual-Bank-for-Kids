package com.virtualbankv1;
// Account management interface class

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AccountManager {

    // 成员变量用于存储UI组件的引用
    private static JLabel accountBalanceLabel;
    private static JLabel accountStatusLabel;

    public AccountManager(Account account) {
        accountManagerGUI(account);

    }

    public void accountManagerGUI(Account account) {

        JFrame frame = new JFrame("Account Information");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1600, 900));
        frame.setLayout(new BorderLayout(10, 10)); // Set margins for the layout

        // Set a solid light blue background
        frame.getContentPane().setBackground(new Color(199, 220, 247)); // Light blue background

        // Create the title label with HTML formatting
        JLabel titleLabel = new JLabel("<html><div style='text-align: center; color: #5D61C3;'><font style='font-size: 50px;'>Account Information</font></div></html>", SwingConstants.CENTER);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Top and bottom padding

        // Create the form panel with labels for displaying information
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setOpaque(false); // Transparent background
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50)); // Padding around the form

        // 创建用于显示余额和账户状态的JLabel
        accountBalanceLabel = createDisplayLabel(account.getBalance());
        accountStatusLabel = createDisplayLabel(account.getStatus());

        // Add display labels with fixed information and larger font size
        formPanel.add(createLabel("Account holder:"));
        formPanel.add(createDisplayLabel(account.getUsername()));
        formPanel.add(createLabel("Account number:"));
        formPanel.add(createDisplayLabel(account.getAccountID()));
        formPanel.add(createLabel("Account Type:"));
        formPanel.add(createDisplayLabel(account.getAccountType()));
        formPanel.add(createLabel("Account Status:"));
        formPanel.add(accountStatusLabel);
        formPanel.add(createLabel("Account Balance:"));
        formPanel.add(accountBalanceLabel);

        // Create the buttons panel for the "Withdraw" and "Transfer In" buttons
        JPanel transactionButtonsPanel = new JPanel();
        transactionButtonsPanel.setLayout(new BoxLayout(transactionButtonsPanel, BoxLayout.Y_AXIS));
        transactionButtonsPanel.setOpaque(false); // Transparent background
        transactionButtonsPanel.add(Box.createRigidArea(new Dimension(0, 30))); // Spacer between buttons
        transactionButtonsPanel.add(createTransactionButton("Withdraw", new Color(70, 130, 180), Color.WHITE, new Dimension(200, 50), account, "withdraw"));
        transactionButtonsPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer between buttons
        transactionButtonsPanel.add(createTransactionButton("Transfer In", new Color(70, 130, 180), Color.WHITE, new Dimension(200, 50), account, "transferIn"));
        transactionButtonsPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer between buttons
        transactionButtonsPanel.add(createReturnButton("Return", new Color(70, 130, 180), Color.WHITE, new Dimension(200, 50), frame));

        // Create the bottom buttons panel with custom button styling
        JPanel bottomButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        bottomButtonsPanel.setOpaque(false); // Transparent background
        bottomButtonsPanel.add(createConfirmationButton("Freeze Account", new Color(255, 255, 0), Color.BLACK, new Dimension(250, 50), account, "freeze account")); // Yellow button with black text
        bottomButtonsPanel.add(Box.createRigidArea(new Dimension(50, 10))); // Spacer between buttons
        bottomButtonsPanel.add(createConfirmationButton("Delete Account", new Color(255, 69, 0), Color.WHITE, new Dimension(250, 50), account, "delete account")); // Red button



        // Add components to the window
        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(formPanel, BorderLayout.WEST); // Align the form panel to the left
        frame.add(transactionButtonsPanel, BorderLayout.CENTER); // Place the transaction buttons in the center
        frame.add(bottomButtonsPanel, BorderLayout.SOUTH); // Place the bottom buttons at the bottom

        // Pack the frame and display the window
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }


    // 自定义圆角边框类
    private static class RoundedBorder implements Border {
        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    }

    private static JButton createTransactionButton(String text, Color bgColor, Color textColor, Dimension size, Account account, String actionCommand) {
        RoundedButton button = new RoundedButton("<html><font size ='6'>" + text + "</font></html>");
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(size);
        button.setMinimumSize(size);
        button.setMaximumSize(size);
        addTransactionListenerToButton(button, actionCommand, account); // 添加监听器
        return button;
    }

    private static JButton createConfirmationButton(String text, Color bgColor, Color textColor, Dimension size, Account account, String actionCommand) {
        RoundedButton button = new RoundedButton("<html><font size ='6'>" + text + "</font></html>");
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(size);
        button.setMinimumSize(size);
        button.setMaximumSize(size);
        addConfirmationListenerToButton(button, actionCommand, account); // 添加监听器
        return button;
    }

    private static JButton createReturnButton(String text, Color bgColor, Color textColor, Dimension size, JFrame frame) {
        RoundedButton button = new RoundedButton("<html><font size ='6'>" + text + "</font></html>");
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(size);
        button.setMinimumSize(size);
        button.setMaximumSize(size);
        addReturnListenerToButton(button, frame); // 添加监听器
        return button;
    }

    // Helper method to create labels with HTML formatting and larger font size
    private static JLabel createLabel(String text) {
        return new JLabel("<html><font color='#8595BC' style='font-size: 30px;'>" + text + "</font></html>");
    }

    // Helper method to create display labels with fixed size and larger font size
    private static JLabel createDisplayLabel(String text) {
        JLabel label = new JLabel("<html><font color='Black' style='font-size: 20px;'>" + text + "</font></html>");
        label.setMaximumSize(new Dimension(350, 50)); // Adjusted size for display labels
        label.setMinimumSize(new Dimension(350, 50)); // Adjusted size for display labels
        label.setPreferredSize(new Dimension(350, 50)); // Adjusted size for display labels
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(Color.WHITE);
        return label;
    }
    private static JLabel createDisplayLabel(Double text) {
        JLabel label = new JLabel("<html><font color='Red' style='font-size: 20px;'>" + text + "</font></html>");
        label.setMaximumSize(new Dimension(350, 50)); // Adjusted size for display labels
        label.setMinimumSize(new Dimension(350, 50)); // Adjusted size for display labels
        label.setPreferredSize(new Dimension(350, 50)); // Adjusted size for display labels
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(Color.WHITE);
        return label;
    }

    // 为交易按钮添加动作监听器
    private static void addTransactionListenerToButton(JButton button, String actionCommand, Account account) {
        button.setActionCommand(actionCommand);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(null, "请输入金额：", "交易", JOptionPane.PLAIN_MESSAGE);
                if (input != null && !input.isEmpty()) {
                    try {
                        double amount = Double.parseDouble(input);
                        if ("withdraw".equals(e.getActionCommand())) {
                            // 调用取款方法
                            withdraw(account, amount);
                        } else if ("transferIn".equals(e.getActionCommand())) {
                            // 调用存款方法
                            transferIn(account, amount);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "请输入有效的金额", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    // 为Confirmation按钮添加动作监听器
    private static void addConfirmationListenerToButton(JButton button, String actionCommand, Account account) {
        button.setActionCommand(actionCommand);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null, "您确定要执行此操作吗？", "确认操作", JOptionPane.YES_NO_OPTION);
                if (confirmed == JOptionPane.YES_OPTION) {
                    if ("delete account".equals(e.getActionCommand())) {
                        // 确认删除账户后的逻辑
                        deleteAccount(account); // 将账户状态改为 Deleted
                        // 其他相关操作

                    } else if ("freeze account".equals(e.getActionCommand())) {
                        // 确认冻结账户后的逻辑
                        freezeAccount(account); // 将账户状态改为 Frozen
                        // 其他相关操作
                    }
                }
            }
        });
    }

    // 为返回按钮添加动作监听器
    private static void addReturnListenerToButton(JButton button, JFrame frame) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // 关闭窗口
                new AccountOverviewPage(); //生成上一页面
            }
        });
    }

    // 存款
    public static void transferIn(Account account, double amount) {
        // 存款逻辑
        account.setBalance(account.getBalance() + amount);
        // 更新账户余额
        accountBalanceLabel.setText("<html><font color='Red' style='font-size: 20px;'>" + account.getBalance() + "</font></html>");
        // 记录交易
    }

    // 取款
    public static void withdraw(Account account, double amount) {
        // 取款逻辑
        account.setBalance(account.getBalance() - amount);
        // 更新账户余额
        accountBalanceLabel.setText("<html><font color='Red' style='font-size: 20px;'>" + account.getBalance() + "</font></html>");
        // 记录交易
    }

    // Freeze account
    public static void freezeAccount(Account account) {
        account.setStatus("Frozen");
        accountStatusLabel.setText("<html><font color='Black' style='font-size: 20px;'>" + account.getStatus() + "</font></html>");
    }

    // Delete account
    public static void deleteAccount(Account account) {
        account.setStatus("Deleted");
        accountStatusLabel.setText("<html><font color='Black' style='font-size: 20px;'>" + account.getStatus() + "</font></html>");
    }
}

