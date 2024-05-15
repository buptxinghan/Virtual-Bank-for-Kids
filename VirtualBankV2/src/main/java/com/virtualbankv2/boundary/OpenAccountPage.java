package com.virtualbankv2.boundary;

import com.virtualbankv2.entity.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static com.virtualbankv2.boundary.Reader.accounts;
import static com.virtualbankv2.control.VirtualBankApplication.currentUser;
/**
 * OpenAccountPage is a GUI class that allows users to open a new account.
 * It extends JFrame and implements ActionListener to handle button actions.
 */
public class OpenAccountPage extends JFrame implements ActionListener {
    private JLabel title, att, atd, pp, ppd, cp;
    private String options[] = {"Saving", "Current"};
    private JComboBox<String> ats = new JComboBox<>(options);
    private JTextField pw = new JTextField(6);
    private JTextField pw2 = new JTextField(6);
    /**
     * Creates a rounded submit button with predefined styles.
     * @return A JButton with rounded corners and specific styles.
     */
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
    private JButton returnButton=ReturnButton.createReturnButton(this,"accountOverviewPage");
    /**
     * Constructor for OpenAccountPage. Initializes the GUI components and layout.
     */
    public OpenAccountPage() {
        setPreferredSize(new Dimension(1200,900));
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(15, 15, 15, 15);
        getContentPane().setBackground(new Color(199, 220, 247));
        addComponent(panel, gbc, title = new JLabel("Open Account"), new Font("Arial", Font.BOLD, 45), new Color(93, 97, 195), 2, 0, 0,GridBagConstraints.CENTER);
        addComponent(panel, gbc, att = new JLabel("Account Type (single-select):"), new Font("Arial", Font.BOLD, 32), new Color(133, 149, 188), 1, 0, 1,GridBagConstraints.WEST);
        addComponent(panel, gbc, atd = new JLabel("Basically, the only difference between these two types of account is whether users can get a yield by their savings."), new Font("Arial", Font.PLAIN, 20), new Color(112, 172, 249), 2, 0, 2,GridBagConstraints.WEST);
        ats.setForeground(new Color(112, 172, 249));
        ats.setFont(new Font("Arial", Font.BOLD, 16));
        addComponent(panel, gbc, ats, 0,3);
        addComponent(panel, gbc, pp = new JLabel("Payment Password:"), new Font("Arial", Font.BOLD, 32), new Color(133, 149, 188), 0, 4,GridBagConstraints.WEST);
        addComponent(panel, gbc, ppd = new JLabel("A 6-digit password is allowed."), new Font("Arial", Font.PLAIN, 20), new Color(112, 172, 249), 12, 0, 5,GridBagConstraints.WEST);
        pw.setForeground(new Color(112, 172, 249));
        pw.setFont(new Font("Arial", Font.BOLD, 16));
        addComponent(panel, gbc, pw,0, 6);
        addComponent(panel, gbc, cp = new JLabel("Confirm Password:"), new Font("Arial", Font.BOLD, 32), new Color(133, 149, 188), 0, 7,GridBagConstraints.WEST);
        pw2.setForeground(new Color(112, 172, 249));
        pw2.setFont(new Font("Arial", Font.BOLD, 16));
        addComponent(panel, gbc, pw2, 0,8);
        addComponent(panel, gbc, submit, 0,9);
        addComponent(panel, gbc, returnButton,0,10);
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        submit.addActionListener(this);
        this.setVisible(true);
    }
    /**
     * Adds a JLabel component to the panel with specified GridBagConstraints.
     * @param panel The JPanel to add the component to.
     * @param gbc The GridBagConstraints for layout.
     * @param label The JLabel component to add.
     * @param font The font to set for the label.
     * @param color The color to set for the label text.
     * @param gridwidth The number of cells the component should span.
     * @param gridx The column index of the component.
     * @param gridy The row index of the component.
     * @param horizontalAlignment The horizontal alignment for the component.
     */
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
    /**
     * Adds a JLabel component to the panel with specified GridBagConstraints.
     * @param panel The JPanel to add the component to.
     * @param gbc The GridBagConstraints for layout.
     * @param label The JLabel component to add.
     * @param font The font to set for the label.
     * @param color The color to set for the label text.
     * @param gridx The column index of the component.
     * @param gridy The row index of the component.
     * @param horizontalAlignment The horizontal alignment for the component.
     */
    private void addComponent(JPanel panel, GridBagConstraints gbc, JLabel label, Font font, Color color, int gridx, int gridy,int horizontalAlignment) {
        label.setFont(font);
        label.setForeground(color);
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.anchor = horizontalAlignment;
        panel.setBackground(new Color(199, 220, 247));
        panel.add(label, gbc);
    }
    /**
     * Adds a JComponent component to the panel with specified GridBagConstraints.
     * @param panel The JPanel to add the component to.
     * @param gbc The GridBagConstraints for layout.
     * @param component The JComponent to add.
     * @param gridx The column index of the component.
     * @param gridy The row index of the component.
     */
    private void addComponent(JPanel panel, GridBagConstraints gbc, JComponent component,int gridx, int gridy) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        panel.setBackground(new Color(199, 220, 247));
        panel.add(component, gbc);
    }
    /**
     * Handles the action performed when the submit button is clicked.
     * @param event The ActionEvent triggered by the submit button.
     */
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
            // Passwords do not match
            JOptionPane.showOptionDialog(
                    this,
                    "Passwords do not match!",
                    "Error",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE,
                    null,
                    new String[] {"OK"},
                    "OK"
            );
        } else {
            // Create a new account
            Random random = new Random();
            int randomNumber = random.nextInt(900000) + 100000;
            String accountid = String.valueOf(randomNumber);
            Account account = new Account(accountid, str3, currentUser.getUsername(),str1,  0.00, "Active");
            //success
            //store
            // Add the account to the accounts list and write to file
            Reader reader=new Reader();
            accounts.add(account);
            Writer writer=new Writer();
            writer.writeAccounts(accounts);
            // Show success message
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
    /**
     * Gets the payment password text field.
     * @return The payment password text field.
     */
    public JTextField getPw() {
        return pw;
    }
    /**
     * Gets the confirmed password text field.
     * @return The confirmed password text field.
     */
    public JTextField getPw2() {
        return pw2;
    }
    /**
     * Gets the submit button.
     * @return The submit button.
     */
    public JButton getSubmit() {
        return submit;
    }


}
