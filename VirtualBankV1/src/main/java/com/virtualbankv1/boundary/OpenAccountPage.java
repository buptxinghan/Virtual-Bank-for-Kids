package com.virtualbankv1.boundary;

import com.virtualbankv1.entity.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenAccountPage extends JFrame implements ActionListener {
    private JLabel title, att, atd, pp, ppd, cp;
    private String options[] = {"Saving", "Current"};
    private JComboBox<String> ats = new JComboBox<>(options);
    private JTextField pw = new JTextField(6);
    private JTextField pw2 = new JTextField(6);
    private JButton submit = new JButton("submit");

    public OpenAccountPage() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        addComponent(panel, gbc, title = new JLabel("Open Account"), Font.BOLD, 24, 2, 0, 0);
        addComponent(panel, gbc, att = new JLabel("Account Type (single-select):"), Font.BOLD, 14, 1, 0, 1);
        addComponent(panel, gbc, atd = new JLabel("Basically, the only difference between these two types of account is whether users can get a yield by their savings."), Font.PLAIN, 12, 2, 0, 2);
        addComponent(panel, gbc, ats, 0, 3);
        addComponent(panel, gbc, pp = new JLabel("Payment Password:"), Font.BOLD, 14, 0, 4);
        addComponent(panel, gbc, ppd = new JLabel("A 6-digit password is allowed."), Font.PLAIN, 12, 0, 5);
        addComponent(panel, gbc, pw, 0, 6);
        addComponent(panel, gbc, cp = new JLabel("Confirm Password:"), Font.BOLD, 14, 0, 7);
        addComponent(panel, gbc, pw2, 0, 8);
        addComponent(panel, gbc, submit, 0, 9);

        add(panel);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        submit.addActionListener(this);
    }

    private void addComponent(JPanel panel, GridBagConstraints gbc, JComponent component, int fontStyle, int fontSize, int gridwidth, int gridx, int gridy) {
        component.setFont(component.getFont().deriveFont(fontStyle, fontSize));
        gbc.gridwidth = gridwidth;
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        panel.add(component, gbc);
    }

    private void addComponent(JPanel panel, GridBagConstraints gbc, JComponent component, int fontStyle, int fontSize, int gridx, int gridy) {
        component.setFont(component.getFont().deriveFont(fontStyle, fontSize));
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        panel.add(component, gbc);
    }

    private void addComponent(JPanel panel, GridBagConstraints gbc, JComponent component, int gridx, int gridy) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        panel.add(component, gbc);
    }

//    public static void main(String[] args) {
//        OpenAccountPage openAccountPage = new OpenAccountPage();
//        openAccountPage.setSize(1000, 500);
//        openAccountPage.setVisible(true);
//    }

    public void actionPerformed(ActionEvent event) {
        String str1 = pw.getText();
        String str2 = pw2.getText();
        String str3 = (String) ats.getSelectedItem();
        if (str1.equals("") || str2.equals("") || str1.length() != 6 || str2.length() != 6) {
            //弹窗？invalid input
        } else if (!str1.equals(str2)) {
            //弹窗密码不一致
        } else {
            Account account = new Account(str1, str3, "a", "b", 0.1, "s");
            //弹窗成功创建
            //存储进csv
        }
    }
}
