package com.virtualbankv2.boundary;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

public class HorizontalLineBorderTest {

    @Test
    public void testPaintBorder() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.add(panel);
        frame.setVisible(true);

        Color color = Color.BLACK;
        int thickness = 2;
        HorizontalLineBorder border = new HorizontalLineBorder(color, thickness);

        panel.setBorder(border);
        panel.setSize(200, 200);
        BufferedImage image = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        panel.paint(g2d);
        g2d.dispose();

        int bottomLineY = panel.getHeight() - thickness;
        int middleX = panel.getWidth() / 2;
        int colorRGB = color.getRGB();

        assertEquals(colorRGB, image.getRGB(0, bottomLineY));
        assertEquals(colorRGB, image.getRGB(panel.getWidth() - 1, bottomLineY));
        assertEquals(colorRGB, image.getRGB(middleX, bottomLineY));

        frame.dispose();
    }

    @Test
    public void testGetBorderInsets() {
        Color color = Color.BLACK;
        int thickness = 2;
        HorizontalLineBorder border = new HorizontalLineBorder(color, thickness);

        Component component = new JLabel();

        Insets insets = border.getBorderInsets(component);
        assertEquals(thickness, insets.top);
        assertEquals(thickness, insets.bottom);
        assertEquals(0, insets.left);
        assertEquals(0, insets.right);
    }

    @Test
    public void testIsBorderOpaque() {
        Color color = Color.BLACK;
        int thickness = 2;
        HorizontalLineBorder border = new HorizontalLineBorder(color, thickness);

        assertTrue(border.isBorderOpaque());
    }
}