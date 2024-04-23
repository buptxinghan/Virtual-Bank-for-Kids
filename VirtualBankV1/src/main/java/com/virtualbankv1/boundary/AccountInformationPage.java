package com.virtualbankv1.boundary;

import com.virtualbankv1.control.AccountManager;
import com.virtualbankv1.entity.Account;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class AccountInformationPage extends JFrame {

    private final AccountManager accountManager = new AccountManager();

    // 成员变量用于存储UI组件的引用
    private JLabel accountBalanceLabel;
    private JLabel accountStatusLabel;
    private JLabel accountIDLabel;
    private JLabel accountTypeLabel;
    private JLabel accountUsernameLabel;

    public AccountInformationPage(Account account) {

        setTitle("Account Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1200, 900));
        setLayout(new BorderLayout(10, 10)); // Set margins for the layout

        // Set a solid light blue background
        getContentPane().setBackground(new Color(199, 220, 247)); // Light blue background

        // Create the title label with HTML formatting
        JLabel titleLabel = new JLabel("<html><div style='text-align: center; color: #5D61C3;'><font style='font-size: 45px;'>Account Information</font></div></html>", SwingConstants.CENTER);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Top and bottom padding

        // Create the form panel with labels for displaying information
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setOpaque(false); // Transparent background
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 180, 10, 50)); // Padding around the form

        // 创建用于显示余额和账户状态的JLabel
        accountBalanceLabel = createDisplayLabel(account.getBalance());
        accountStatusLabel = createDisplayLabel(account.getStatus());
        accountTypeLabel = createDisplayLabel(account.getAccountType());
        accountIDLabel = createDisplayLabel(account.getAccountID());
        accountUsernameLabel = createDisplayLabel(account.getUsername());

        // Add display labels with fixed information and larger font size
        formPanel.add(createLabel("Account holder:"));
        formPanel.add(accountUsernameLabel);
        formPanel.add(createLabel("Account number:"));
        formPanel.add(accountIDLabel);
        formPanel.add(createLabel("Account Type:"));
        formPanel.add(accountTypeLabel);
        formPanel.add(createLabel("Account Status:"));
        formPanel.add(accountStatusLabel);
        formPanel.add(createLabel("Account Balance:"));
        formPanel.add(accountBalanceLabel);

        // Create the buttons panel for the "Withdraw" and "Transfer In" buttons
        JPanel transactionButtonsPanel = new JPanel();
        transactionButtonsPanel.setLayout(new BoxLayout(transactionButtonsPanel, BoxLayout.Y_AXIS));
        transactionButtonsPanel.setOpaque(false); // Transparent background
        transactionButtonsPanel.add(Box.createRigidArea(new Dimension(0, 30))); // Spacer between buttons
        transactionButtonsPanel.add(createButtons("Withdraw", new Color(70, 130, 180), Color.WHITE, account, "withdraw"));
        transactionButtonsPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer between buttons
        transactionButtonsPanel.add(createButtons("Transfer In", new Color(70, 130, 180), Color.WHITE, account, "transferIn"));
        transactionButtonsPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer between buttons
        transactionButtonsPanel.add(createButtons("Transfer accounts", new Color(70, 130, 180), Color.WHITE, account, "transferAccounts"));
        transactionButtonsPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer between buttons
        transactionButtonsPanel.add(createButtons("History", new Color(70, 130, 180), Color.WHITE, account, "history"));
        transactionButtonsPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer between buttons

        // Create return button
        JButton returnButton = ReturnButton.createReturnButton(this, "accountOverviewPage", new Dimension(250, 50));
        transactionButtonsPanel.add(returnButton);
        // Add picture
        Icon transferIcon = new ImageIcon("src/Materials/Transfer.png");
        JLabel transferLabel = new JLabel(transferIcon);
        transferLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        transactionButtonsPanel.add(Box.createRigidArea(new Dimension(0, 50))); // 添加间隔
        transactionButtonsPanel.add(transferLabel);

        // Create the bottom buttons panel with custom button styling
        JPanel bottomButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 50));
        bottomButtonsPanel.setOpaque(false); // Transparent background
        bottomButtonsPanel.add(createButtons("Freeze Account", new Color(255, 255, 0), Color.BLACK, account, "freeze account")); // Yellow button with black text
        bottomButtonsPanel.add(Box.createRigidArea(new Dimension(50, 10))); // Spacer between buttons
        bottomButtonsPanel.add(createButtons("Unfreeze Account", new Color(255, 255, 0), Color.BLACK, account, "unfreeze account")); // Yellow button with black text
        bottomButtonsPanel.add(Box.createRigidArea(new Dimension(50, 10))); // Spacer between buttons
        bottomButtonsPanel.add(createButtons("Delete Account", new Color(255, 69, 0), Color.WHITE, account, "delete account")); // Red button

        // Add components to the window
        add(titleLabel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.WEST); // Align the form panel to the left
        add(transactionButtonsPanel, BorderLayout.CENTER); // Place the transaction buttons in the center
        add(bottomButtonsPanel, BorderLayout.SOUTH); // Place the bottom buttons at the bottom

        // Pack the frame
        pack();
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    private JButton createButtons(String text, Color bgColor, Color textColor, Account account, String actionCommand) {
        RoundedButton button = new RoundedButton("<html><font style='font-size: 18px;'>" + text + "</font></html>");
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(250, 50));
        button.setMinimumSize(new Dimension(250, 50));
        button.setMaximumSize(new Dimension(250, 50));
        if (actionCommand.equals("withdraw") || actionCommand.equals("transferIn")) {
            accountManager.addTransactionListenerToButton(button, actionCommand, account, accountBalanceLabel); // 添加监听器
        } else if (actionCommand.equals("freeze account") || actionCommand.equals("unfreeze account") || actionCommand.equals("delete account")) {
            accountManager.addConfirmationListenerToButton(button, actionCommand, account, accountStatusLabel, accountBalanceLabel, accountTypeLabel, accountIDLabel, accountUsernameLabel);
        } else if (actionCommand.equals("transferAccounts")) {
            accountManager.addTransferListenerToButton(button, actionCommand, account, this);
        } else if (actionCommand.equals("history")) {
            accountManager.addHistoryListenerToBotton(button, actionCommand, account, this);
        }

        return button;
    }

    // Helper method to create labels with HTML formatting and larger font size
    private static JLabel createLabel(String text) {
        return new JLabel("<html><font color='#8595BC' style='font-size: 25px;'>" + text + "</font></html>");
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

    private static JLabel createDisplayLabel(Double balance) {
        //格式化balance
        DecimalFormat df = new DecimalFormat("#,##0.00");
        String formattedBalance = df.format(balance);

        JLabel label = new JLabel("<html><font color='Red' style='font-size: 20px;'>" + formattedBalance + "</font></html>");
        label.setMaximumSize(new Dimension(350, 50)); // Adjusted size for display labels
        label.setMinimumSize(new Dimension(350, 50)); // Adjusted size for display labels
        label.setPreferredSize(new Dimension(350, 50)); // Adjusted size for display labels
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(Color.WHITE);
        return label;
    }


}
