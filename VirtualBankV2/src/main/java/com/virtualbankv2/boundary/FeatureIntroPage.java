package com.virtualbankv2.boundary;

import com.virtualbankv2.entity.ReturnButton;
import com.virtualbankv2.entity.RoundedPanel;

import javax.swing.*;
import java.awt.*;

public class FeatureIntroPage extends JFrame {
    public FeatureIntroPage() {
        super("Account Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 900);
        setLocationRelativeTo(null);

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
        JScrollPane scrollPane = new JScrollPane(mainPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(10, 0, 10, 0); // 添加顶部和底部的空白

        String[] titles = {
                "Welcome!",
                "Account Creation",
                "Account Freezing",
                "Account Cancellation",
                "Security Features",
                "User Support",
                "Customization Services",
                ""
        };

        String[] contents = {
                "Welcome to our Account Management System, a platform designed to provide you with a seamless, secure, and efficient account management experience. Whether you're conducting daily banking activities or addressing specific needs, our system is equipped to serve a wide array of requirements.",
                "Current Account Creation: Offers essential banking functions, including but not limited to deposits, withdrawals, and transfers. This account type is tailored for users seeking straightforward banking solutions.\n\nSavings Account Creation: Designed for users aiming to earn interest on their deposits. Our savings accounts come with competitive annual interest rates, helping your savings grow.",
                "Users have the capability to freeze their account at any time to mitigate potential security risks. Once frozen, all transaction activities are temporarily suspended until the user initiates an unfreeze action.",
                "Users wishing to discontinue their account can opt for account cancellation. During this process, we require users to undergo a series of verification steps to ensure the security of the operation.",
                "Password Protection: All user accounts are secured with strong passwords, ensuring the safety of account information.\n\nTwo-Factor Authentication: To add an extra layer of security, we offer users with two-factor authentication options, which verify user identity via SMS or email.\n\nIdentity Verification Process: For sensitive operations, such as account cancellation, we implement strict identity verification processes to ensure actions are authorized by the account holder.",
                "Our system provides round-the-clock user support services. Should you encounter any issues or require assistance, our customer service team is readily available to help.",
                "Users can customize their account settings based on personal preferences, including but not limited to transaction notifications and regular billing statements, making your banking experience more personalized and convenient.",
                "We are committed to continuous technological innovation and optimization to offer secure, reliable, and convenient banking services. Thank you for choosing our system. We look forward to providing you with further assistance and support!"
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
        JLabel label = createLabel("<html><font color='#5D61C3' style='font-size: 25px;'>Feature Introduction</font></html>",Font.BOLD, 25,Component.CENTER_ALIGNMENT);
        topPanel.add(label);

        JPanel bottlePanel = new JPanel();
        bottlePanel.setBackground(new Color(199, 220, 247));
        bottlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        JButton returnButton = ReturnButton.createReturnButton(this,"UserManual",new Dimension(120,50));
        bottlePanel.add(returnButton);


        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(topPanel);
        add(scrollPane);
        add(bottlePanel);
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