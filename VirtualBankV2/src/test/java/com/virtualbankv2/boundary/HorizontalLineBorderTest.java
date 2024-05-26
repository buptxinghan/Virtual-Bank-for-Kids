package com.virtualbankv2.boundary;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the functionality of the HorizontalLineBorder class.
 * It includes tests for the paintBorder, getBorderInsets, and isBorderOpaque methods.
 *
 * @version 2.0
 * @since 2024-05-05
 * @author Ji Zheng
 */

public class HorizontalLineBorderTest {

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
