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
    private JFrame frame;
    private JTextField holderTextField, idTextField, typeTextField, balanceTextField, dateTextField;
    private JButton returnButton, withdrawButton, TransferInButton, freezeButton, deleteButton;

    public AccountManager() {


        // Create a window
        frame = new JFrame("Account Information");
        frame.setSize(1200, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Title
        JLabel titleLabel = new JLabel("Account Information");
        titleLabel.setBounds(10, 10, 200, 25);
        frame.add(titleLabel);

        // Account holder information
        JLabel holderLabel = new JLabel("Account holder:");
        holderLabel.setBounds(10, 40, 100, 25);
        frame.add(holderLabel);

        holderTextField = new JTextField(20);
        holderTextField.setBounds(120, 40, 165, 25);
        frame.add(holderTextField);

        // Account ID information
        JLabel idLabel = new JLabel("AccountID:");
        idLabel.setBounds(10, 70, 100, 25);
        frame.add(idLabel);

        idTextField = new JTextField(20);
        idTextField.setBounds(120, 70, 165, 25);
        frame.add(idTextField);

        // Account type information
        JLabel typeLabel = new JLabel("AccountType:");
        typeLabel.setBounds(10, 100, 100, 25);
        frame.add(typeLabel);

        typeTextField = new JTextField(20);
        typeTextField.setBounds(120, 100, 165, 25);
        frame.add(typeTextField);

        // Account balance information
        JLabel balanceLabel = new JLabel("Account Balance:");
        balanceLabel.setBounds(10, 130, 100, 25);
        frame.add(balanceLabel);

        balanceTextField = new JTextField(20);
        balanceTextField.setBounds(120, 130, 165, 25);
        frame.add(balanceTextField);

        // Account opening time information
        JLabel dateLabel = new JLabel("Open Date:");
        dateLabel.setBounds(10, 160, 100, 25);
        frame.add(dateLabel);

        dateTextField = new JTextField(20);
        dateTextField.setBounds(120, 160, 165, 25);
        frame.add(dateTextField);

        // Buttons
        returnButton = new JButton("Return");
        returnButton.setBounds(10, 190, 80, 25);
        frame.add(returnButton);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(95, 190, 100, 25);
        frame.add(withdrawButton);

        TransferInButton = new JButton("Transfer In");
        TransferInButton.setBounds(200, 190, 130, 25);
        frame.add(TransferInButton);

        freezeButton = new JButton("Freeze Account");
        freezeButton.setBounds(335, 190, 130, 25);
        frame.add(freezeButton);

        deleteButton = new JButton("Delete Account");
        deleteButton.setBounds(470, 190, 130, 25);
        frame.add(deleteButton);

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

    public static void main(String[] args) {

        new AccountManager();

    }
}
