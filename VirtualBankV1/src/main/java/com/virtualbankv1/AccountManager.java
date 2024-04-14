package com.virtualbankv1;
// Account management interface class

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AccountManager {

    // Create an account

    public AccountManager(Account account) {
        accountManagerGUI(account);

    }

    public void accountManagerGUI(Account account) {

        JFrame frame;
        JLabel holderLabel, idLabel, typeLabel, balanceLabel, dateLabel;
        JButton returnButton, withdrawButton, TransferInButton, freezeButton, deleteButton;

        // Create a window and set its size
        frame = new JFrame("Account Information");
        frame.setSize(1400, 1000); // Set the initial size of the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Title with larger font and centered in the North area
        JLabel titleLabel = new JLabel("Account Information", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 60));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Panel for information fields with margins on the sides
        JPanel infoPanelWithMargins = new JPanel(new BorderLayout());
        infoPanelWithMargins.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50)); // Top, left, bottom, right margins

        // Panel for information fields in the center area
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(5, 2, 10, 10)); // 5 rows, 2 columns, with padding

        // Custom font for information fields and buttons
        Font infoFont = new Font("Arial", Font.PLAIN, 32);

        // Account holder information
        holderLabel = new JLabel("Account Holder: " + account.getUsername());
        holderLabel.setFont(infoFont);
        infoPanel.add(holderLabel);

        // Account ID information
        idLabel = new JLabel("Account ID: " + account.getAccountID());
        idLabel.setFont(infoFont);
        infoPanel.add(idLabel);

        // Account type information
        typeLabel = new JLabel("Account Type: " + account.getAccountType());
        typeLabel.setFont(infoFont);
        infoPanel.add(typeLabel);

        // Account balance information
        balanceLabel = new JLabel("Account Balance: $" + account.getBalance());
        balanceLabel.setFont(infoFont);
        infoPanel.add(balanceLabel);

        // Account opening date information
        dateLabel = new JLabel("Account Status: " + account.getStatus());
        dateLabel.setFont(infoFont);
        infoPanel.add(dateLabel);

        // Add the information panel to the panel with margins
        infoPanelWithMargins.add(infoPanel, BorderLayout.CENTER);

        // Add the information panel with margins to the center area of the frame
        frame.add(infoPanelWithMargins, BorderLayout.CENTER);

        // South panel that will contain both the buttons and the blank space
        JPanel southPanel = new JPanel(new BorderLayout());

        // Panel for buttons with margins on the sides
        JPanel buttonPanelWithMargins = new JPanel(new BorderLayout());
        buttonPanelWithMargins.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50)); // Top, left, bottom, right margins

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 5, 10, 0)); // 1 row, 5 columns, with padding

        // Custom dimension for buttons
        Dimension buttonSize = new Dimension(280, 40);

        // Buttons with larger font and custom width
        returnButton = new JButton("Return");
        returnButton.setFont(infoFont);
        returnButton.setPreferredSize(buttonSize);
        buttonPanel.add(returnButton);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.setFont(infoFont);
        withdrawButton.setPreferredSize(buttonSize);
        buttonPanel.add(withdrawButton);

        TransferInButton = new JButton("Transfer In");
        TransferInButton.setFont(infoFont);
        TransferInButton.setPreferredSize(buttonSize);
        buttonPanel.add(TransferInButton);

        freezeButton = new JButton("Freeze Account");
        freezeButton.setFont(infoFont);
        freezeButton.setPreferredSize(buttonSize);
        buttonPanel.add(freezeButton);

        deleteButton = new JButton("Delete Account");
        deleteButton.setFont(infoFont);
        deleteButton.setPreferredSize(buttonSize);
        buttonPanel.add(deleteButton);

        // Add the button panel to the button panel with margins
        buttonPanelWithMargins.add(buttonPanel, BorderLayout.CENTER);

        // Add the button panel with margins to the top of the south panel
        southPanel.add(buttonPanelWithMargins, BorderLayout.NORTH);

        // Create a blank panel to add space below the buttons
        JPanel blankPanel = new JPanel();
        blankPanel.setPreferredSize(new Dimension(0, 50)); // Set the preferred height for the blank space
        southPanel.add(blankPanel, BorderLayout.CENTER); // Add the blank panel below the buttons

        // Add the south panel to the South area of the frame
        frame.add(southPanel, BorderLayout.SOUTH);

        // Display the window
        frame.setVisible(true);
    }

    // 存款
    public boolean deposit(User user, double amount) {
        // 存款逻辑
        // 更新账户余额
        // 记录交易
        return true;
    }

    // 取款
    public boolean withdraw(User user, double amount) {
        // 取款逻辑
        // 更新账户余额
        // 记录交易
        return true;
    }
}
