package com.virtualbankv2.boundary;

import com.virtualbankv2.entity.ReturnButton;

import javax.swing.*;
import java.awt.*;
public class UserManual extends JFrame {
    private JButton feature;
    private JButton faq;
    private JButton contact;
    private JPanel middlePanel;
    private JPanel topPanel;
    private JPanel bottomPanel;

    private String getColorHex(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    public UserManual() {
        initializeComponents();
    }
    private void initializeComponents() {
        // Set window size
        setSize(new Dimension(1200, 900));
        setTitle("Virtual Bank");
        // Set background color
        Color z1 = new Color(93,97,195);
        Color z2 = new Color(133,149,188);
        getContentPane().setBackground(new Color(199, 220, 247));
        getContentPane().setLayout(new BorderLayout());

        topPanel = new JPanel(new GridBagLayout());
        topPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        topPanel.setOpaque(false);
        JLabel title = new JLabel("User Manual");
        title.setForeground(new Color(93, 97, 195));
        title.setFont(new Font("Arial", Font.BOLD, 60));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(30, 20, 30,20);
        addComponent(topPanel, gbc,title,2, 0, 0,GridBagConstraints.CENTER);//后面要改
        // Create bottom panel for return button
        bottomPanel = new JPanel();
        bottomPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        JButton returnButton = ReturnButton.createReturnButton(this,"HomePage",new Dimension(120,50));
        bottomPanel.add(returnButton);
        bottomPanel.setOpaque(false);

        middlePanel = new JPanel();
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
        middlePanel.setOpaque(false);

        // Initialize buttons
        feature = new JButton("<html><font size=7 color=" + getColorHex(z1) + ">Feature Introduction</font><br><font size=6 color=" + getColorHex(z2) + ">Click here for explanations about each feature.</font></html>");
        faq = new JButton("<html><font size=7 color=" + getColorHex(z1) + ">FAQ</font><br><font size=6 color=" + getColorHex(z2) + ">Frequently asked questions are displayed here.</font></html>");
        contact = new JButton("<html><font size=7 color=" + getColorHex(z1) + ">Contact Us</font><br><font size=6 color=" + getColorHex(z2) + ">Contact us for personal support through this.</font></html>");

        feature.setFocusPainted(false);
        addButtonToPanel(feature);
        addButtonToPanel(faq);
        addButtonToPanel(contact);
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(middlePanel, BorderLayout.CENTER);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);
/*
//pics needed?
        ImageIcon icon = new ImageIcon("src/Materials/pig.png");
        JLabel pic = new JLabel(icon,JLabel.CENTER);
        JPanel picPanel = new JPanel();
        picPanel.add(pic);
        picPanel.setBackground(bg);
        picPanel.setMaximumSize(new Dimension(300,100));


*/
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void addButtonToPanel(JButton button){
        button.setBackground(new Color(199, 220, 247));
        button.addActionListener(e -> {

            this.dispose();
        });
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBorderPainted(false);
        JPanel outerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        outerPanel.setOpaque(false);

        JPanel p1=new JPanel();
        p1.setPreferredSize(new Dimension(600, 102));
        p1.setLayout(new BoxLayout(p1, BoxLayout.X_AXIS));
        p1.setOpaque(false);
        p1.setBorder(new HorizontalLineBorder(new Color(133, 149, 188), 1));
        p1.add(button);
        outerPanel.add(p1);
        middlePanel.add(outerPanel);
    }
    private void addComponent(JPanel panel,GridBagConstraints gbc,JComponent component,int gridwidth,int gridx,int gridy,int horizontalAlignment){
        gbc.gridwidth = gridwidth;
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.anchor = horizontalAlignment;
        panel.add(component, gbc);
    }
    public static void main(String[] args){
        UserManual userManual=new UserManual();
    }
}
