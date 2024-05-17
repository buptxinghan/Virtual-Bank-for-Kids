package com.virtualbankv2.boundary;

import com.virtualbankv2.control.ChildLockManager;
import com.virtualbankv2.entity.Account;
import com.virtualbankv2.entity.ReturnButton;
import com.virtualbankv2.entity.RoundedButton;
import com.virtualbankv2.entity.RoundedPanel;

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

        RoundedButton addButton = new RoundedButton("+");
        addButton.setFont(new Font("Arial", Font.BOLD, 25));
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        extracted(addButton,50,50);
        new ChildLockManager().addButtonWithChildLock(this,addButton, pageOpen);

        RoundedPanel innerPanel = new RoundedPanel(15);
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        innerPanel.setBackground(new Color(255,250,240));
        innerPanel.setBorder(BorderFactory.createEmptyBorder(30, 80, 40, 80));

        innerPanel.setPreferredSize(new Dimension(400,250));
        innerPanel.setMinimumSize(new Dimension(400,250));
        innerPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        innerPanel.add(createLabel("Create  new  account ", Font.BOLD, 22,Component.CENTER_ALIGNMENT));
        innerPanel.add(Box.createRigidArea(new Dimension(0, 55)));
        innerPanel.add(addButton);

        JPanel outerPanel = new JPanel();
        createOuterPanel(outerPanel, innerPanel);

        SwingUtilities.invokeLater(() -> {
            mainPanel.add(outerPanel);
            setPage();
        });
    }

    private static void extracted(RoundedButton Button,int width,int height) {
        Button.setBackground(new Color(79,143,230));
        Button.setForeground(Color.WHITE);
        Button.setPreferredSize(new Dimension(width,height));
        Button.setMaximumSize(new Dimension(width,height));
    }

    // Update the page with the given account information
    public void updatePage(Account account) {

        RoundedButton selectButton = new RoundedButton( "select");
        selectButton.setFont(new Font("Arial", Font.BOLD, 17));
        extracted(selectButton,90,35);
        selectButton.addActionListener(e -> {
            dispose();
            pageOpen.showAccountInfo(account);
        });

        RoundedPanel panel = new RoundedPanel(15);
        panel.setBackground(new Color(255,239,213));
        panel.setPreferredSize(new Dimension(400,250));
        panel.setMinimumSize(new Dimension(400,250));

        JLabel label1 = new JLabel("Account ID:  " + account.getAccountID());
        label1.setFont(new Font("Arial", Font.BOLD, 18));

        Color statusColor = account.getStatus().equals("Active") ? new Color(91,165,133) : Color.RED;
        JLabel label2 = new JLabel("Account Status: ");
        JLabel statusLabel = new JLabel(account.getStatus());
        statusLabel.setForeground(statusColor);
        label2.setFont(new Font("Arial", Font.BOLD, 18));
        statusLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel label3 = new JLabel("Account Type: " + account.getAccountType());
        label3.setFont(new Font("Arial", Font.BOLD, 18));

        GridBagLayout gridBagLayout=new GridBagLayout();
        GridBagConstraints gridBagConstraints=new GridBagConstraints();
        gridBagConstraints.fill=GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(10, 0, 10, 0);  // 上、左、下、右的间距

        gridBagConstraints.anchor = GridBagConstraints.WEST;

        gridBagConstraints.gridx=1;
        gridBagConstraints.gridy=2;
        gridBagConstraints.gridwidth=4;
        gridBagConstraints.gridheight=2;
        gridBagLayout.setConstraints(label1, gridBagConstraints);

        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.gridx=3;
        gridBagConstraints.gridy=4;
        gridBagConstraints.gridwidth=2;
        gridBagConstraints.gridheight=1;
        gridBagLayout.setConstraints(statusLabel, gridBagConstraints);

        gridBagConstraints.gridx=1;
        gridBagConstraints.gridy=4;
        gridBagConstraints.gridheight=1;
        gridBagLayout.setConstraints(label2, gridBagConstraints);

        gridBagConstraints.gridx=1;
        gridBagConstraints.gridy=5;
        gridBagConstraints.gridwidth=4;
        gridBagConstraints.gridheight=1;
        gridBagLayout.setConstraints(label3, gridBagConstraints);

        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.gridx=2;
        gridBagConstraints.gridy=7;
//        gridBagConstraints.gridwidth=1;
//        gridBagConstraints.gridheight=4;
        gridBagLayout.setConstraints(selectButton, gridBagConstraints);

        panel.setLayout(gridBagLayout);
        panel.add(label1);
        panel.add(label2);
        panel.add(statusLabel);
        panel.add(label3);
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

}