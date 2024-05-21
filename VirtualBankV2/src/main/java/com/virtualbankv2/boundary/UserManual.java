package com.virtualbankv2.boundary;

import com.virtualbankv2.entity.ReturnButton;

import javax.swing.*;
import java.awt.*;
/**
 * The UserManual class represents a GUI window that displays the user manual of the Virtual Bank application.
 * It contains a title, an image, and buttons for different sections of the manual.
 */
public class UserManual extends JFrame {
    private JButton feature;
    private JButton faq;
    private JButton contact;
    private JPanel middlePanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    /**
     * Converts a Color object to its hexadecimal string representation.
     *
     * @param color the Color object to convert
     * @return the hexadecimal string representation of the color
     */
    private String getColorHex(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }
    /**
     * Constructs a new UserManual window and initializes its components.
     */
    public UserManual() {
        initializeComponents();
    }
    /**
     * Initializes the components of the UserManual window.
     */
    private void initializeComponents() {
        // Set window size
        setSize(new Dimension(1200, 900));
        setTitle("Virtual Bank");
        // Set background color
        Color z1 = new Color(93,97,195);
        Color z2 = new Color(133,149,188);
        getContentPane().setBackground(new Color(199, 220, 247));
        getContentPane().setLayout(new BorderLayout());
        // Initialize top panel with a GridBagLayout
        topPanel = new JPanel(new GridBagLayout());
        topPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 400));
        topPanel.setOpaque(false);
        // Create title label
        JLabel title = new JLabel("User Manual");
        title.setForeground(new Color(93, 97, 195));
        title.setFont(new Font("Arial", Font.BOLD, 60));
        title.setPreferredSize(new Dimension(1200,100));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 20, 10,20);
        // Create and scale the image icon
        ImageIcon pic = new ImageIcon("src/Materials/userManual.jpg");
        Image image = pic.getImage().getScaledInstance(1200, 125, Image.SCALE_SMOOTH);
        pic = new ImageIcon(image);
        JLabel picLabel = new JLabel(pic,SwingConstants.CENTER);
        // Add components to the top panel
        addComponent(topPanel,gbc,picLabel,2,0,0,GridBagConstraints.CENTER);
        addComponent(topPanel, gbc,title,2, 0, 1,GridBagConstraints.CENTER);

        // Create bottom panel for return button
        bottomPanel = new JPanel();
        bottomPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        JButton returnButton = ReturnButton.createReturnButton(this,"HomePage",new Dimension(120,50));
        bottomPanel.add(returnButton);
        bottomPanel.setOpaque(false);
        // Initialize middle panel with BoxLayout
        middlePanel = new JPanel();
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
        middlePanel.setOpaque(false);

        // Initialize buttons
        feature = new JButton("<html><font size=7 color=" + getColorHex(z1) + ">Feature Introduction</font><br><font size=6 color=" + getColorHex(z2) + ">Click here for explanations about each feature.</font></html>");
        faq = new JButton("<html><font size=7 color=" + getColorHex(z1) + ">FAQ</font><br><font size=6 color=" + getColorHex(z2) + ">Frequently asked questions are displayed here.</font></html>");
        contact = new JButton("<html><font size=7 color=" + getColorHex(z1) + ">Contact Us</font><br><font size=6 color=" + getColorHex(z2) + ">Contact us for personal support through this.</font></html>");

        // Add buttons to the middle panel
        addButtonToPanel(feature);
        addButtonToPanel(faq);
        addButtonToPanel(contact);
        // Add panels to the content pane
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(middlePanel, BorderLayout.CENTER);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        // Add action listeners to buttons
        feature.addActionListener(e -> {
            new FeatureIntroPage();
            this.dispose();
        });
        faq.addActionListener(e -> {
            new FAQ();
            this.dispose();
        });
        contact.addActionListener(e -> {
            new ContactUsPage();
            this.dispose();
        });
        // Set default close operation and visibility
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    /**
     * Adds a button to the middle panel with a specific style and layout.
     *
     * @param button the JButton to add
     */
    private void addButtonToPanel(JButton button){
        button.setBackground(new Color(199, 220, 247));
        button.setFocusPainted(false);
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
    /**
     * Adds a component to a panel using GridBagLayout with specified constraints.
     *
     * @param panel the JPanel to add the component to
     * @param gbc the GridBagConstraints to use
     * @param component the JComponent to add
     * @param gridwidth the number of columns the component should span
     * @param gridx the column in which to place the component
     * @param gridy the row in which to place the component
     * @param horizontalAlignment the alignment of the component within its cell
     */
    private void addComponent(JPanel panel,GridBagConstraints gbc,JComponent component,int gridwidth,int gridx,int gridy,int horizontalAlignment){
        gbc.gridwidth = gridwidth;
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.anchor = horizontalAlignment;
        panel.add(component, gbc);
    }
    /*public static void main(String[] args){
        UserManual userManual=new UserManual();
    }*/
}
