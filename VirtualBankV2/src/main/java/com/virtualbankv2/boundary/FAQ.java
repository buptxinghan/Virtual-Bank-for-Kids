package com.virtualbankv2.boundary;

import com.virtualbankv2.entity.ReturnButton;
import com.virtualbankv2.entity.RoundedPanel;

import javax.swing.*;
import java.awt.*;

public class FAQ extends JFrame {
    public FAQ() {
        super("Account Management System");


        JPanel mainPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int w = getWidth();
                int h = getHeight();
                GradientPaint gp = new GradientPaint(
                        0, 0, Color.decode("#46a6e2"),
                        0, getHeight(), Color.decode("#638bbf")
                );
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(10, 0, 10, 0); // 添加顶部和底部的空白

        String[] titles = {
                "Q1: How do I create a new account in the Account Management System?",
                "Q2: Is it possible to freeze my account temporarily? If so, how?",
                "Q3: What do I need to do if I want to cancel my account?",
                "Q4: Can I have both a Current and a Savings account simultaneously",
                "Q5: What are the security features available to protect my account?",
        };

        String[] contents = {
                "A1: You can start creating a new account by selecting the type of account you need: either a 'Current' or 'Savings' account. Our system will guide you through the necessary steps, where you'll provide personal information, choose account preferences, and set up security features. Once all steps are completed, your account will be ready to use.",
                "A2: Yes, you can temporarily freeze your account for added security or other reasons. To freeze your account, navigate to the account management settings and select the 'Freeze Account' option. You may be required to verify your identity through a chosen verification method before the action is completed. Your account will remain frozen until you choose to unfreeze it.",
                "A3: If you decide to cancel your account, you can do so by accessing the account settings and selecting 'Cancel Account'. You'll need to verify your identity for security purposes, which could involve providing your name, date of birth, or other verification methods. After verification, follow the on-screen instructions to complete the cancellation process.",
                "A4: Absolutely! Our Account Management System allows users to manage multiple types of accounts under the same user profile. You can create and customize each account according to your financial needs, ensuring that you get the most out of your banking experience with us.",
                "A5: We prioritize your account's security by offering several features: password protection, two-factor authentication (2FA), account freezing options, and identity verification processes for sensitive actions. We continuously update our security measures to ensure your account's safety and integrity."
        };

        for (int i = 0; i < titles.length; i++) {
            JPanel panel = createPanel(titles[i], contents[i]);
            mainPanel.add(panel, gbc);
            if (i < titles.length - 1) {
                mainPanel.add(new JSeparator(), gbc);
            }
        }

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(199, 220, 247));
        topPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        JLabel label = createLabel("<html><font color='#5D61C3' style='font-size: 25px;'>FAQ</font></html>",Font.BOLD, 25,Component.CENTER_ALIGNMENT);
        topPanel.add(label);

        JPanel bottlePanel = new JPanel();
        bottlePanel.setBackground(new Color(199, 220, 247));
        bottlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        JButton returnButton = ReturnButton.createReturnButton(this,"UserManual",new Dimension(120,50));
        bottlePanel.add(returnButton);

        JScrollPane scrollPane = new JScrollPane(mainPanel);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(topPanel);
        add(scrollPane);
        add(bottlePanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 900);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createPanel(String title, String content) {
        JPanel panel = new RoundedPanel(15);
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(255, 255, 255, 100)); // 设置面板背景为半透明
        panel.setPreferredSize(new Dimension(700, 250));
        panel.setMinimumSize(new Dimension(700, 250));
        panel.setBorder(null); // 移除边框

        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");

        String titleFontName = "Arial";
        int titleFontSize = 20;
        String titleColor = "#861C8C"; // 黑色
        String titleFontWeight = "bold"; // 粗体
        String titleFontStyle = "italic"; // 斜体

        String contentFontName = "Arial";
        int contentFontSize = 14;
        String contentColor = "#000000"; // 黑色
        String contentFontWeight = "normal"; // 正常

        String htmlText = "<html><body style='text-align: justify;'>" +
                "<h1 style='font-family:" + titleFontName + "; font-size:" + titleFontSize + "px; color:" + titleColor + "; font-weight:" + titleFontWeight + "; font-style:" + titleFontStyle + "; text-align: center;'>" + title + "</h1>" +
                "<p style='font-family:" + contentFontName + "; font-size:" + contentFontSize + "px; color:" + contentColor + "; font-weight:" + contentFontWeight + ";'>" + content.replace("\n", "<br>") + "</p>" +
                "</body></html>";

        textPane.setText(htmlText);
        textPane.setEditable(false);
        textPane.setOpaque(false);

        panel.add(textPane, BorderLayout.CENTER);

        return panel;
    }

    private JLabel createLabel(String text, int fontStyle, int fontSize,float alignment) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", fontStyle, fontSize));
        label.setAlignmentX(alignment);
        return label;
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            new FeatureIntroPage().setVisible(true);
//        });
//    }
}



