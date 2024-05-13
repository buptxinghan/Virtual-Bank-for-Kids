package com.virtualbankv2.boundary;

import com.virtualbankv2.entity.Account;
import com.virtualbankv2.entity.ReturnButton;
import com.virtualbankv2.entity.RoundedButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static com.virtualbankv2.boundary.Reader.accounts;
import static com.virtualbankv2.control.VirtualBankApplication.currentUser;


public class OpenAccountPage extends JFrame implements ActionListener {
    private JLabel title, att, atd, pp, ppd, cp;
    private String options[] = {"Saving", "Current"};
    private JComboBox<String> ats = new JComboBox<>(options);
    private JTextField pw = new JTextField(6);
    private JTextField pw2 = new JTextField(6);
    public static JButton createRoundButton() {
        RoundedButton button = new RoundedButton("<html><font size ='6'>Submit</font></html>");
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(200, 50));
        button.setMinimumSize(new Dimension(200, 50));
        button.setMaximumSize(new Dimension(200, 50));
        return button;
    }
    private RoundedButton submit = (RoundedButton) createRoundButton();

    private JButton returnButton= ReturnButton.createReturnButton(this,"accountOverviewPage");

    public OpenAccountPage() {
        setPreferredSize(new Dimension(1200,900));
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(15, 15, 15, 15);
        getContentPane().setBackground(new Color(199, 220, 247));
        addComponent(panel, gbc, title = new JLabel("Open Account"), new Font("Arial", Font.BOLD, 45), new Color(93, 97, 195), 2, 0, 0,GridBagConstraints.CENTER);
        // 设置小标题的颜色和字体大小
        addComponent(panel, gbc, att = new JLabel("Account Type (single-select):"), new Font("Arial", Font.BOLD, 32), new Color(133, 149, 188), 1, 0, 1,GridBagConstraints.WEST);
        addComponent(panel, gbc, atd = new JLabel("Basically, the only difference between these two types of account is whether users can get a yield by their savings."), new Font("Arial", Font.PLAIN, 20), new Color(112, 172, 249), 2, 0, 2,GridBagConstraints.WEST);
        // 设置组件的颜色
        addComponent(panel, gbc, ats, new Color(112, 172, 249), 0,3);
        addComponent(panel, gbc, pp = new JLabel("Payment Password:"), new Font("Arial", Font.BOLD, 32), new Color(133, 149, 188), 0, 4,GridBagConstraints.WEST);
        addComponent(panel, gbc, ppd = new JLabel("A 6-digit password is allowed."), new Font("Arial", Font.PLAIN, 20), new Color(112, 172, 249), 12, 0, 5,GridBagConstraints.WEST);
        addComponent(panel, gbc, pw, new Color(112, 172, 249),0, 6);
        addComponent(panel, gbc, cp = new JLabel("Confirm Password:"), new Font("Arial", Font.BOLD, 32), new Color(133, 149, 188), 0, 7,GridBagConstraints.WEST);
        addComponent(panel, gbc, pw2, new Color(112, 172, 249), 0,8);
        addComponent(panel, gbc, submit, 0,9);
        addComponent(panel, gbc, returnButton,0,10);
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        submit.addActionListener(this);
        this.setVisible(true);
    }
    private void addComponent(JPanel panel, GridBagConstraints gbc, JLabel label, Font font, Color color, int gridwidth, int gridx, int gridy, int horizontalAlignment) {
        label.setFont(font);
        label.setForeground(color);
        gbc.gridwidth = gridwidth;
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.anchor = horizontalAlignment;
        panel.setBackground(new Color(199, 220, 247));
        panel.add(label, gbc);
    }
    private void addComponent(JPanel panel, GridBagConstraints gbc, JLabel label, Font font, Color color, int gridx, int gridy,int horizontalAlignment) {
        label.setFont(font);
        label.setForeground(color);
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.anchor = horizontalAlignment;
        panel.setBackground(new Color(199, 220, 247));
        panel.add(label, gbc);
    }

    private void addComponent(JPanel panel, GridBagConstraints gbc, JComboBox<String> comboBox, Color color, int gridx,int gridy) {
        comboBox.setForeground(color);
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        panel.setBackground(new Color(199, 220, 247));
        panel.add(comboBox, gbc);
    }

    private void addComponent(JPanel panel, GridBagConstraints gbc, JTextField textField, Color color, int gridx,int gridy) {
        textField.setForeground(color);
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        panel.setBackground(new Color(199, 220, 247));
        panel.add(textField, gbc);
    }

    private void addComponent(JPanel panel, GridBagConstraints gbc, JComponent component,int gridx, int gridy) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        panel.setBackground(new Color(199, 220, 247));
        panel.add(component, gbc);
    }
    /*public static void main(String[] args) {
        OpenAccountPage openAccountPage = new OpenAccountPage();
        openAccountPage.setSize(1200, 900);
        openAccountPage.setVisible(true);
    }*/

    public void actionPerformed(ActionEvent event) {
        String str1 = pw.getText();
        String str2 = pw2.getText();
        String str3 = (String) ats.getSelectedItem();
        if (str1.equals("") || str2.equals("") || str1.length() != 6 || str2.length() != 6) {
            //invalid input
            JOptionPane.showOptionDialog(
                    this,
                    "Invalid input! Payment password must be a 6-digit number.",
                    "Error",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE,
                    null,
                    new String[] {"OK"},
                    "OK"
            );
        } else if (!str1.equals(str2)) {
            //don match
            JOptionPane.showOptionDialog(
                    this,
                    "Passwords do not match!",
                    "Error",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE,
                    null, // 没有自定义图标
                    new String[] {"OK"},
                    "OK"
            );
        } else {
            Random random = new Random();
            int randomNumber = random.nextInt(900000) + 100000;
            String accountid = String.valueOf(randomNumber);
            Account account = new Account(accountid, str3, currentUser.getUsername(),str1,  0.00, "Active");
            //success
            //store
            Reader reader=new Reader();
            accounts.add(account);
            Writer writer=new Writer();
            writer.writeAccounts(accounts);
            JOptionPane.showOptionDialog(
                    this,
                    "Account created successfully!",
                    "Success",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    new String[] {"OK"},
                    "OK"
            );
            pw.setText("");
            pw2.setText("");
        }
    }

    public JTextField getPw() {
        return pw;
    }

    public JTextField getPw2() {
        return pw2;
    }

    public JButton getSubmit() {
        return submit;
    }


}
