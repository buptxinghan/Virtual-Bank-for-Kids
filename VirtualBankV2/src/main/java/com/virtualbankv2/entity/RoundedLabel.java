package com.virtualbankv2.entity;

import javax.swing.*;
import java.awt.*;
/**
 * The RoundedLabel class extends JLabel.
 * It is used to create a label with rounded corners.
 *
 * @author Botong Wu
 * @version 1.0
 * @since 2024-04-15
 */
public class RoundedLabel extends JLabel {
    private int arcH = 20;  // Horizontal arc
    private int arcW = 20;  // Vertical arc

    /**
     * Constructor for the RoundedLabel.
     * Initializes the label with the given text and sets it to be non-opaque.
     *
     * @param text The text of the label.
     */
    public RoundedLabel(String text) {
        super(text);
        setOpaque(false);
    }

    /**
     * Returns the vertical arc of the label.
     *
     * @return Returns the vertical arc.
     */
    public int getArcW() {
        return arcW;
    }

    /**
     * Returns the horizontal arc of the label.
     *
     * @return Returns the horizontal arc.
     */
    public int getArcH() {
        return arcH;
    }

    /**
     * Paints the component with a background color and rounded corners.
     *
     * @param g The Graphics object to protect.
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcW, arcH);
        super.paintComponent(g);
    }
}