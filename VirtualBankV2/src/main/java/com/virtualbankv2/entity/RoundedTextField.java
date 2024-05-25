package com.virtualbankv2.entity;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
/**
 * The RoundedTextField class extends JTextField and is part of the entity package of the VirtualBankV2 application.
 * It is responsible for creating a text field with rounded corners.
 *
 * @author Botong Wu
 * @version 1.0
 * @since 2024-04-12
 */
public class RoundedTextField extends JTextField {
    private Shape shape;
    /**
     * Constructor for the RoundedTextField.
     * Initializes the text field with the given size and sets it to be non-opaque.
     *
     * @param size The size of the text field.
     */
    public RoundedTextField(int size) {
        super(size);
        setOpaque(false); // As suggested by @AVD in comment.
    }
    /**
     * Paints the component with a background color and rounded corners.
     *
     * @param g The Graphics object to protect.
     */
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        super.paintComponent(g);
    }
    /**
     * Paints the border of the component with a foreground color and rounded corners.
     *
     * @param g The Graphics object to protect.
     */
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
    }
    /**
     * Checks if the component contains the given x and y coordinates.
     * If the shape is null or its bounds do not equal the component's bounds, a new RoundRectangle2D is created.
     *
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @return Returns true if the shape contains the coordinates, false otherwise.
     */
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        }
        return shape.contains(x, y);
    }
}