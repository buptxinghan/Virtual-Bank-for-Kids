package com.virtualbankv1.boundary;

import com.virtualbankv1.control.AccountManager;
import com.virtualbankv1.entity.Account;
import com.virtualbankv1.entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.virtualbankv1.boundary.Reader.accounts;
import static com.virtualbankv1.boundary.Reader.users;

public class AccountOverviewPage extends JFrame {
    private JButton createNewAccountButton = new JButton("+");
    private JPanel mainPanel;
    // Constructor
    public AccountOverviewPage(String username) {
        this.mainPanel = new JPanel(new GridBagLayout());
        setContentPane(this.mainPanel);
        boolean userExists = checkUserExists(username);
        if (userExists) {
            displayAccountInfo(username);
        }

        displayCreateAccountPanel();
    }

    // Check if the user exists
    private boolean checkUserExists(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    // Display the account information for the given username
    private void displayAccountInfo(String username) {
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                updatePage(account, Color.WHITE);
            }
        }
    }

    // Display the panel for creating a new account
    private void displayCreateAccountPanel() {
        JPanel panel = createPanel();

        JLabel titleLabel = new JLabel("Create new account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(titleLabel);

        JButton addButton = new JButton("+");
        addButton.addActionListener(e -> openAccountPage());
        panel.add(addButton);

        add(panel);
        pack();
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Modify the close behavior
    }

    // Open the account page
    private void openAccountPage() {
        OpenAccountPage openAccount = new OpenAccountPage();
        openAccount.setSize(1200, 900);
        openAccount.setVisible(true);
        openAccount.setLocationRelativeTo(null); // Center the window
        openAccount.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Modify the close behavior
    }

    // Show the page
    public void setPage(AccountOverviewPage aop) {
        aop.setSize(1200, 900);
        aop.setLocationRelativeTo(null); // Center the window
        aop.setVisible(true);
        aop.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    // Update the page with the given account information
    public void updatePage(Account account, Color color) {
        JPanel panel = createPanel();
        panel.setBackground(color);

        JLabel titleLabel = new JLabel(account.getAccountID());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(titleLabel);

        JLabel numberLabel = new JLabel("Account status: " + account.getStatus());
        panel.add(numberLabel);

        JLabel typeLabel = new JLabel("Account type: " + account.getAccountType());
        panel.add(typeLabel);

        JButton selectButton = new JButton("Select");
        selectButton.addActionListener(e -> showAccountInfo(account));
        panel.add(selectButton);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.weighty = 1;
        mainPanel.add(panel, gbc);

        pack();
        revalidate();
    }

    // Show the account information
    private void showAccountInfo(Account account) {
        dispose();
        AccountInformationPage am = new AccountInformationPage(account);
    }

    // Create a new JPanel with some default settings
    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        return panel;
    }
}
