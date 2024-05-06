package com.virtualbankv1.boundary;

import com.virtualbankv1.control.ChildLockManager;
import com.virtualbankv1.entity.Account;
import javax.swing.*;
import java.awt.*;



public class AccountOverviewUI extends JFrame {

    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel bottlePanel;
    public PageOpen pageOpen = new PageOpen();
    // Constructor
    public AccountOverviewUI() {
        mainPanel = new JPanel(new GridLayout(3,2));
        mainPanel.setBackground(new Color(199, 220, 247));
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        topPanel = new JPanel();
        topPanel.setBackground(new Color(199, 220, 247));
        topPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        bottlePanel = new JPanel();
        bottlePanel.setBackground(new Color(199, 220, 247));
        bottlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        JLabel label = createLabel("<html><font color='#5D61C3' style='font-size: 25px;'>My Accounts</font></html>",Font.BOLD, 25,Component.CENTER_ALIGNMENT);
        JButton returnButton = ReturnButton.createReturnButton(this,"HomePage",new Dimension(120,50));
        topPanel.add(label);
        bottlePanel.add(returnButton);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(this.topPanel);
//        add(this.mainPanel);
        add(scrollPane);
        add(this.bottlePanel);
        displayCreateAccountPanel();
    }


    public void setPage() {
//        pack();
        setPreferredSize(new Dimension(1200,900));
        setMinimumSize(new Dimension(1200,900));
        setLocationRelativeTo(null); // Center the window

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    // Display the panel for creating a new account
    private void displayCreateAccountPanel() {

        RoundedButton addButton = new RoundedButton("<html><font style='font-size: 15px;'>+</font></html>");
        extracted(addButton);
        new ChildLockManager().addButtonWithChildLock(this,addButton, pageOpen);

        RoundedPanel innerPanel = createRoundedPanel(new Color(255,250,240));
        innerPanel.setPreferredSize(new Dimension(400,250));
        innerPanel.setMinimumSize(new Dimension(400,250));
        innerPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        innerPanel.add(createLabel("Create  new  account ", Font.BOLD, 18,Component.CENTER_ALIGNMENT));
        innerPanel.add(Box.createRigidArea(new Dimension(0, 65)));
        innerPanel.add(addButton);

        JPanel outerPanel = new JPanel();
        createOuterPanel(outerPanel, innerPanel);

        SwingUtilities.invokeLater(() -> {
            mainPanel.add(outerPanel);
            setPage();
        });

    }

    private static void extracted(RoundedButton Button) {
        Button.setBackground(new Color(70, 130, 180));
        Button.setForeground(Color.WHITE);
        Button.setAlignmentX(Component.CENTER_ALIGNMENT);
        Button.setPreferredSize(new Dimension(0,10));
    }

    // Update the page with the given account information
    public void updatePage(Account account) {
//      if (account == null || color == null) {
//          throw new IllegalArgumentException("Account or color cannot be null");
//      }

        RoundedButton selectButton = new RoundedButton( "<html><font style='font-size: 15px;'>select</font></html>");
        extracted(selectButton);
        selectButton.addActionListener(e -> {
            dispose();
            pageOpen.showAccountInfo(account);
        });

        RoundedPanel panel = createRoundedPanel(new Color(255,239,213));
        panel.add(createLabel(account.getAccountID(), Font.BOLD, 18,Component.CENTER_ALIGNMENT));
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(createLabel("Account status: " + account.getStatus(),Font.BOLD, 18,Component.LEFT_ALIGNMENT));
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(createLabel("Account type: " + account.getAccountType(),Font.BOLD, 18,Component.LEFT_ALIGNMENT));
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(selectButton);

        JPanel outerPanel = new JPanel();
        createOuterPanel(outerPanel, panel);

        SwingUtilities.invokeLater(() -> {
            mainPanel.add(outerPanel);
            setPage();
            revalidate();
        });
    }

    private static void createOuterPanel(JPanel outerPanel, RoundedPanel panel) {
        outerPanel.setLayout(new BorderLayout());
        outerPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        outerPanel.setBackground(new Color(199, 220, 247));
        outerPanel.add(panel);
    }

    private JLabel createLabel(String text, int fontStyle, int fontSize,float alignment) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", fontStyle, fontSize));
        label.setAlignmentX(alignment);
        return label;
    }

    public RoundedPanel createRoundedPanel(Color color) {
        RoundedPanel rpanel = new RoundedPanel(15);
        rpanel.setLayout(new BoxLayout(rpanel, BoxLayout.Y_AXIS));
        rpanel.setBorder(BorderFactory.createEmptyBorder(42, 40, 40, 40));
        rpanel.setBackground(color);
        return rpanel;
    }

}