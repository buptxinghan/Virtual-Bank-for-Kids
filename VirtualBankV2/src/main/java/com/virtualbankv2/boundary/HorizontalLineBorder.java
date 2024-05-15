package com.virtualbankv2.boundary;
import javax.swing.border.Border;
import java.awt.*;
/**
 * A custom Swing border implementation to draw a horizontal line at the bottom of a component.
 */
class HorizontalLineBorder implements Border {
    private Color color;
    private int thickness;
    /**
     * Constructs a HorizontalLineBorder with the specified color and thickness.
     *
     * @param color     The color of the border line.
     * @param thickness The thickness of the border line.
     */
    public HorizontalLineBorder(Color color, int thickness) {
        this.color = color;
        this.thickness = thickness;
    }
    /**
     * Paints the border for the specified component with the specified position, size, and insets.
     *
     * @param c      The component for which this border is being painted.
     * @param g      The Graphics context in which to paint.
     * @param x      The x position of the painted border.
     * @param y      The y position of the painted border.
     * @param width  The width of the painted border.
     * @param height The height of the painted border.
     */
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(color);
        g2.setStroke(new BasicStroke(thickness));
        g2.drawLine(x, y + height - thickness, x + width, y + height - thickness); // 底部线条
        g2.dispose();
    }
    /**
     * Returns the insets of the border.
     *
     * @param c The component for which this border insets value applies.
     * @return The insets of the border.
     */
    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(thickness, 0, thickness, 0);
    }
    /**
     * Returns whether or not the border is opaque.
     *
     * @return {@code true} if the border is fully opaque; {@code false} otherwise.
     */

    @Override
    public boolean isBorderOpaque() {
        return true;
    }
}
