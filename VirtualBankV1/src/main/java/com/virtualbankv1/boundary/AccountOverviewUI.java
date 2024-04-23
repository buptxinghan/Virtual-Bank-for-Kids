package com.virtualbankv1.boundary;

import com.virtualbankv1.control.AccountOverviewPage;
import com.virtualbankv1.entity.Account;

import javax.swing.*;
import java.awt.*;


public class AccountOverviewUI extends JFrame {

    private JPanel mainPanel;
    public PageOpen pageOpen = new PageOpen();
    // Constructor
    public AccountOverviewUI(AccountOverviewPage controller) {
        this.mainPanel = new JPanel(new GridLayout(2,2));
        setContentPane(this.mainPanel);
        displayCreateAccountPanel();
    }

    public void setPage() {
        //pack();
        setLocationRelativeTo(null); // Center the window
        setSize(new Dimension(1200, 900));
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    // Display the panel for creating a new account
    private void displayCreateAccountPanel() {

        JPanel outerPanel = new JPanel(new GridBagLayout());
        outerPanel.setSize(600,450);

        RoundedButton addButton = new RoundedButton("<html><font style='font-size: 15px;'>+</font></html>" );
        addButton.setBackground(new Color(70, 130, 180));
        addButton.setForeground(Color.WHITE);
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addButton.setSize(new Dimension(100,100));

        addButton.addActionListener(e -> {
            dispose();
            pageOpen.openAccount();
        });

        RoundedPanel innerPanel = createRoundedPanel();
        innerPanel.setSize(600,450);
        innerPanel.add(createLabel("Create new account ", Font.BOLD, 14));
        innerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        innerPanel.add(addButton);
        innerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        outerPanel.add(innerPanel);

        add(outerPanel);

        setPage();
    }

    // Update the page with the given account information
    public void updatePage(Account account) {
//        if (account == null || color == null) {
//            throw new IllegalArgumentException("Account or color cannot be null");
//        }
        JPanel outerPanel = new JPanel(new GridBagLayout());
//        outerPanel.setSize(600,450);
        RoundedButton selectButton = new RoundedButton( "<html><font style='font-size: 15px;'>select</font></html>");
        selectButton.setBackground(new Color(70, 130, 180));
        selectButton.setForeground(Color.WHITE);
        selectButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectButton.setPreferredSize(new Dimension(10,100));
        selectButton.addActionListener(e -> {
            dispose();
            pageOpen.showAccountInfo(account);
        });

        RoundedPanel panel = createRoundedPanel();
//        panel.setSize(600,450);
        panel.setBackground(Color.CYAN);
        panel.add(createLabel(account.getAccountID(), Font.BOLD, 18));
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(createLabel("Account status: " + account.getStatus()));
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(createLabel("Account type: " + account.getAccountType()));
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(selectButton);
        outerPanel.add(panel);

        SwingUtilities.invokeLater(() -> {
            add(outerPanel);
            setPage();
            revalidate();
        });
    }

    private JLabel createLabel(String text, int fontStyle, int fontSize) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", fontStyle, fontSize));
        label.setAlignmentX(Component.CENTER_ALIGNMENT); // Center alignment
        return label;
    }

    private JLabel createLabel(String text) {
        return createLabel(text, Font.PLAIN, 12);
    }


    public RoundedPanel createRoundedPanel() {
        RoundedPanel rpanel = new RoundedPanel(15);
        rpanel.setLayout(new BoxLayout(rpanel, BoxLayout.Y_AXIS));
        rpanel.setBorder(BorderFactory.createEmptyBorder(40, 60, 30, 60));
        return rpanel;
    }

}