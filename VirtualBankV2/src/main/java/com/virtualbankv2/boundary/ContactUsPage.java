package com.virtualbankv2.boundary;

import javax.swing.*;
import java.awt.*;

public class ContactUsPage extends JFrame {
    public ContactUsPage(){
        this.initComponents();

    }

    private void initComponents(){
        setSize(new Dimension(1200, 900));
        setTitle("Virtual Bank");

        Color bg = new Color(199, 220, 247);
        Color z1 = new Color(93, 97, 195);
        Color z2 = new Color(70, 130, 180);
        getContentPane().setBackground(bg);

        //return button
        JButton returnButton = ReturnButton.createReturnButton(this, "homepage");

        JLabel head = new JLabel("<html><font size=7 color=" + getColorHex(z1) + ">Contact Us</font></html>",JLabel.CENTER);
        JLabel l1 = new JLabel("<html><font size=6 color=" + getColorHex(z1) + ">Customer Services</font></html>",JLabel.LEFT);
        JLabel l2 = new JLabel("<html><font size=5 color=" + getColorHex(z1) + ">Wechat Number: XXXXX</font></html>",JLabel.LEFT);
        JLabel l3 = new JLabel("<html><font size=5 color=" + getColorHex(z1) + ">Tel:           XXXXX</font></html>",JLabel.LEFT);
        JLabel l4 = new JLabel("<html><font size=5 color=" + getColorHex(z1) + ">Email:         XXXX@xx.com</font></html>",JLabel.LEFT);
        JLabel l5 = new JLabel("<html><font size=5 color=" + getColorHex(z1) + ">Service Time:  09:00-20:00</font></html>",JLabel.LEFT);

        //content
        JTextArea contentArea = new JTextArea("Please Contact us if you need help!");
        contentArea.setEditable(false);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(contentArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(250, 150));

        ImageIcon pic = new ImageIcon("src/Materials/contactus.jpg");
        JLabel top = new JLabel(pic,SwingConstants.CENTER);

        JPanel Center = new JPanel(new GridLayout(2, 1));
        JPanel C1 = new JPanel(new GridLayout(6,1));
        JPanel C2 = new JPanel(new GridLayout(2,1));

        JPanel S = new JPanel();
        JPanel W = new JPanel();
        JPanel E = new JPanel();
        JPanel b = new JPanel();
        JPanel N = new JPanel();

        W.setPreferredSize(new Dimension(200, 200));
        E.setPreferredSize(new Dimension(200, 200));
        S.setPreferredSize(new Dimension(100, 100));
        N.setPreferredSize(new Dimension(1200, 200));

        Center.setBackground(bg);
        C1.setBackground(bg);
        C2.setBackground(bg);
        S.setBackground(bg);
        W.setBackground(bg);
        E.setBackground(bg);
        N.setBackground(bg);
        b.setBackground(bg);

        N.add(top);

        C1.add(head);
        C1.add(l1);
        C1.add(l2);
        C1.add(l3);
        C1.add(l4);
        C1.add(l5);

        b.setLayout(null);
        returnButton.setBounds(660,20,100,50);
        b.add(returnButton);
        C2.add(contentArea);
        C2.add(b);

        Center.add(C1);
        Center.add(C2);

        //add panels into the frame
        add(E,BorderLayout.EAST);
        add(N,BorderLayout.NORTH);
        add(S,BorderLayout.SOUTH);
        add(W,BorderLayout.WEST);
        add(Center);

        //display
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

    public static void main(String[] args){
      ContactUsPage ct = new ContactUsPage();
    };

}
