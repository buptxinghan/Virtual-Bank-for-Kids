package com.virtualbankv2.entity;

import javax.swing.*;
import java.awt.*;
/**
 * The RoundedPanel class extends JPanel and is part of the entity package of the VirtualBankV2 application.
 * It is responsible for creating a panel with rounded corners.
 *
 * @author Botong Wu
 * @version 1.0
 * @since 2024-04-15
 */
public class RoundedPanel extends JPanel {
    private Color backgroundColor;
    private int cornerRadius = 15;
    /**
     * Constructor for the RoundedPanel.
     * Initializes the panel with the given corner radius and sets it to be non-opaque.
     *
     * @param radius The radius of the corners.
     */
    public RoundedPanel(int radius) {
        cornerRadius = radius;
        setOpaque(false);
    }
    /**
     * Paints the component with a background color and rounded corners.
     *
     * @param g The Graphics object to protect.
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(cornerRadius, cornerRadius);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Draws the rounded panel with borders.
        if (backgroundColor != null) {
            graphics.setColor(backgroundColor);
        } else {
            graphics.setColor(getBackground());
        }
        graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint background
        graphics.setColor(getForeground());
//        graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint border
    }
    /**
     * Returns the corner radius of the panel.
     *
     * @return Returns the corner radius.
     */
    public int getCornerRadius() {
        return cornerRadius;
    }
    /**
     * Sets the corner radius of the panel and repaints it.
     *
     * @param cornerRadius The new corner radius.
     */
    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        repaint();
    }
}
