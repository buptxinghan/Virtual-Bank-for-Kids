package com.virtualbankv2.entity;

import org.junit.jupiter.api.Test;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the functionality of the RoundedPanel class.
 * It includes tests for the constructor, setting and getting the background color, and setting and getting the preferred size.
 *
 * @version 1.0
 * @since 2024-05-10
 * @autor Ji Zheng
 */
class RoundedPanelTest {

    /**
     * Tests the constructor of the RoundedPanel class.
     */
    @Test
    void testRoundedPanelConstructor() {
        int radius = 20;
        RoundedPanel panel = new RoundedPanel(radius);
        assertNotNull(panel);
        assertEquals(radius, panel.getCornerRadius());
    }

    /**
     * Tests setting and getting the background color of the RoundedPanel.
     */
    @Test
    void testRoundedPanelBackground() {
        RoundedPanel panel = new RoundedPanel(15);
        Color expectedBackground = Color.BLUE;
        panel.setBackground(expectedBackground);
        Color actualBackground = panel.getBackground();
        assertEquals(expectedBackground, actualBackground);
    }

    /**
     * Tests setting and getting the preferred size of the RoundedPanel.
     */
    @Test
    void testRoundedPanelPreferredSize() {
        RoundedPanel panel = new RoundedPanel(10);
        Dimension expectedSize = new Dimension(200, 100);
        panel.setPreferredSize(expectedSize);
        Dimension actualSize = panel.getPreferredSize();
        assertEquals(expectedSize, actualSize);
    }
}
