package com.virtualbankv2.entity;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the functionality of the RoundedPasswordField class.
 * It includes tests for the constructor, the paintComponent method, the paintBorder method, and the contains method.
 *
 * @version 1.0
 * @since 2024-05-10
 * @autor Ji Zheng
 */
public class RoundedPasswordFieldTest {

    /**
     * Tests the constructor of the RoundedPasswordField class.
     */
    @Test
    public void testConstructor() {
        int size = 10;
        RoundedPasswordField passwordField = new RoundedPasswordField(size);

        assertEquals(size, passwordField.getColumns());
        assertFalse(passwordField.isOpaque());
    }

    /**
     * Tests the paintComponent method of the RoundedPasswordField class.
     */
    @Test
    public void testPaintComponent() {
        RoundedPasswordField passwordField = new RoundedPasswordField(10);
        passwordField.setSize(100, 50);
        passwordField.setBackground(Color.BLUE);

        BufferedImage image = new BufferedImage(100, 50, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();

        passwordField.paintComponent(graphics);

        Color pixelColor = new Color(image.getRGB(10, 10), true);

        assertEquals(new Color(passwordField.getBackground().getRGB()), pixelColor);

        graphics.dispose();
    }

    /**
     * Tests the paintBorder method of the RoundedPasswordField class.
     */
    @Test
    public void testPaintBorder() {
        RoundedPasswordField passwordField = new RoundedPasswordField(10);
        passwordField.setSize(100, 50);

        passwordField.setForeground(Color.RED);

        BufferedImage image = new BufferedImage(100, 50, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();

        passwordField.paintBorder(graphics);

        Color pixelColor = new Color(image.getRGB(10, 0), true);

        assertEquals(new Color(passwordField.getForeground().getRGB()), pixelColor);

        graphics.dispose();
    }

    /**
     * Tests the contains method of the RoundedPasswordField class.
     */
    @Test
    public void testContains() {
        RoundedPasswordField passwordField = new RoundedPasswordField(10);
        passwordField.setSize(100, 50);

        // Points inside the rounded rectangle
        assertTrue(passwordField.contains(10, 10));
        assertTrue(passwordField.contains(90, 10));
        assertTrue(passwordField.contains(10, 40));
        assertTrue(passwordField.contains(90, 40));

        // Points outside the rounded rectangle (including corners)
        assertFalse(passwordField.contains(0, 0));   // Top-left corner
        assertFalse(passwordField.contains(99, 0));  // Top-right corner
        assertFalse(passwordField.contains(0, 49));  // Bottom-left corner
        assertFalse(passwordField.contains(99, 49)); // Bottom-right corner
    }
}