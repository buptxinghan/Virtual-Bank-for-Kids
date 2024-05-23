package com.virtualbankv2.boundary;

import com.virtualbankv2.entity.ReturnButton;

import javax.swing.*;
import java.awt.*;

/**
 * The ContactUsPage class represents the customer service page of the Virtual Bank application.
 * It displays contact information and provides a return button to navigate back to the user manual.
 *
 * @author Feng Shiyu
 * @since 3.0
 */
public class ContactUsPage extends JFrame {

    /**
     * Constructs a ContactUsPage instance and initializes its components.
     */
    public ContactUsPage() {
        this.initComponents();
    }

    /**
     * Initializes the components of the contact us page.
     * Sets up the layout, adds labels, text area, and return button.
     */
    private void initComponents() {
        setSize(new Dimension(1200, 900));
        setTitle("Virtual Bank");

        Color bg = new Color(199, 220, 247);
        Color z1 = new Color(93, 97, 195);
        getContentPane().setBackground(bg);

        // return button
        JButton returnButton = ReturnButton.createReturnButton(this, "UserManual");
        //labels
        JLabel head = new JLabel("<html><font color=" + getColorHex(z1) + ">Customer Services</font></html>", JLabel.CENTER);
        head.setFont(new Font("Serif", Font.BOLD, 50)); // Set font style and size
        JLabel l2 = new JLabel("<html><font size=6 color=" + getColorHex(z1) + ">Wechat Number:  buptxinghan</font></html>", JLabel.CENTER);
        JLabel l3 = new JLabel("<html><font size=6 color=" + getColorHex(z1) + ">Tel:      +86 15545195623</font></html>", JLabel.CENTER);
        JLabel l4 = new JLabel("<html><font size=6 color=" + getColorHex(z1) + ">Email:        jp2021213165@qmul.ac.uk</font></html>", JLabel.CENTER);
        JLabel l5 = new JLabel("<html><font size=6 color=" + getColorHex(z1) + ">Service Time:  09:00-20:00</font></html>", JLabel.CENTER);

        ImageIcon pic = new ImageIcon("src/Materials/contactus.jpg");
        JLabel top = new JLabel(pic, SwingConstants.CENTER);

        JPanel center = new JPanel(new GridLayout(2, 1));
        JPanel c1 = new JPanel(new GridLayout(5, 1));
        JPanel c2 = new JPanel(new GridLayout(1, 1));

        JPanel s = new JPanel();
        JPanel w = new JPanel();
        JPanel e = new JPanel();
        JPanel b = new JPanel();
        JPanel n = new JPanel();

        w.setPreferredSize(new Dimension(300, 200));
        e.setPreferredSize(new Dimension(300, 200));
        s.setPreferredSize(new Dimension(10, 10));
        n.setPreferredSize(new Dimension(1200, 200));

        center.setBackground(bg);
        c1.setBackground(bg);
        c2.setBackground(bg);
        s.setBackground(bg);
        w.setBackground(bg);
        e.setBackground(bg);
        n.setBackground(bg);
        b.setBackground(bg);

        n.add(top);
        c1.add(head);
        c1.add(l2);
        c1.add(l3);
        c1.add(l4);
        c1.add(l5);

        b.setLayout(null);
        returnButton.setBounds(250, 20, 120, 50);
        b.add(returnButton);
        c2.add(b);

        center.add(c1);
        center.add(c2);

        // add panels into the frame
        add(e, BorderLayout.EAST);
        add(n, BorderLayout.NORTH);
        add(s, BorderLayout.SOUTH);
        add(w, BorderLayout.WEST);
        add(center);

        // display
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Retrieves the color hex code of the specified color.
     *
     * @param color The Color object.
     * @return A String representing the color hex code.
     */
    private String getColorHex(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

}
