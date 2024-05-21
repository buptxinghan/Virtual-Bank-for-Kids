package com.virtualbankv2.entity;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

public class RoundedTextFieldTest {

    @Test
    public void testConstructor() {
        int size = 10;
        RoundedTextField textField = new RoundedTextField(size);

        assertEquals(size, textField.getColumns());
        assertFalse(textField.isOpaque());
    }

    @Test
    public void testPaintComponent() {
        RoundedTextField textField = new RoundedTextField(10);
        textField.setSize(100, 50);

        textField.setBackground(Color.GREEN);

        BufferedImage image = new BufferedImage(100, 50, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();

        textField.paintComponent(graphics);

        Color pixelColor = new Color(image.getRGB(10, 10), true);

        assertEquals(new Color(textField.getBackground().getRGB()), pixelColor);

        graphics.dispose();
    }

    @Test
    public void testPaintBorder() {
        RoundedTextField textField = new RoundedTextField(10);
        textField.setSize(100, 50);

        textField.setForeground(Color.ORANGE);

        BufferedImage image = new BufferedImage(100, 50, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();

        textField.paintBorder(graphics);

        Color pixelColor = new Color(image.getRGB(10, 0), true);

        assertEquals(new Color(textField.getForeground().getRGB()), pixelColor);

        graphics.dispose();
    }

    @Test
    public void testContains() {
        RoundedTextField textField = new RoundedTextField(10);
        textField.setSize(100, 50);

        // Points inside the rounded rectangle
        assertTrue(textField.contains(10, 10));
        assertTrue(textField.contains(90, 10));
        assertTrue(textField.contains(10, 40));
        assertTrue(textField.contains(90, 40));

        // Points outside the rounded rectangle (including corners)
        assertFalse(textField.contains(0, 0));   // Top-left corner
        assertFalse(textField.contains(99, 0));  // Top-right corner
        assertFalse(textField.contains(0, 49));  // Bottom-left corner
        assertFalse(textField.contains(99, 49)); // Bottom-right corner
    }
}