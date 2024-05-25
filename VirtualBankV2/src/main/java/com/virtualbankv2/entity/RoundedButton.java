package com.virtualbankv2.entity;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * A custom JButton with rounded corners.
 *
 * @version 1.0
 * @since 2024-04-15
 * @author Zhenghan Zhong
 */
public class RoundedButton extends JButton {
    private Shape shape;
    private int cornerRadius;

    /**
     * Constructs a rounded button with the specified label.
     *
     * @param label The text label of the button.
     */
    public RoundedButton(String label) {
        super(label);
        this.cornerRadius = 60;
        setOpaque(false); // Make the button transparent
        setContentAreaFilled(false); // Prevent the default square background fill
        setFocusPainted(false); // Remove focus painting
        setBorderPainted(false); // Remove border painting
    }

    /**
     * Overrides the paintComponent method to draw a rounded background.
     *
     * @param g The Graphics context in which to paint.
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (getModel().isArmed()) {
            g2.setColor(getBackground().darker());
        } else {
            g2.setColor(getBackground());
        }
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius));
        super.paintComponent(g2);
        g2.dispose();
    }

    /**
     * Overrides the contains method to ensure that click events are only triggered within the rounded area.
     *
     * @param x The x-coordinate of the point to be tested.
     * @param y The y-coordinate of the point to be tested.
     * @return boolean True if the point is within the rounded area, otherwise false.
     */
    @Override
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        }
        return shape.contains(x, y);
    }
}