package com.virtualbankv2.entity;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the functionality of the RoundedButton class.
 * It includes tests for the constructor, setting and getting size, and setting and getting background color.
 *
 * @version 1.0
 * @since 2024-05-10
 * @author Ji Zheng
 */
class RoundedButtonTest {

    /**
     * Tests the constructor of the RoundedButton class.
     */
    @Test
    void testRoundedButtonConstructor() {
        String label = "Test Button";
        RoundedButton button = new RoundedButton(label);
        assertNotNull(button);
        assertEquals(label, button.getText());
    }

    /**
     * Tests setting and getting the size of the RoundedButton.
     */
    @Test
    void testRoundedButtonSize() {
        String label = "Test Button";
        RoundedButton button = new RoundedButton(label);
        Dimension expectedSize = new Dimension(100, 50);
        button.setPreferredSize(expectedSize);
        Dimension actualSize = button.getPreferredSize();
        assertEquals(expectedSize, actualSize);
    }

    /**
     * Tests setting and getting the background color of the RoundedButton.
     */
    @Test
    void testRoundedButtonBackground() {
        String label = "Test Button";
        RoundedButton button = new RoundedButton(label);
        Color expectedBackground = Color.RED;
        button.setBackground(expectedBackground);
        Color actualBackground = button.getBackground();
        assertEquals(expectedBackground, actualBackground);
    }
}
