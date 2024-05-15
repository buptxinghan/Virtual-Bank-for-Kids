package com.virtualbankv2.boundary;

import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class RoundedPanelTest {

    @Test
    void testRoundedPanelConstructor() {
        int radius = 20;
        RoundedPanel panel = new RoundedPanel(radius);
        assertNotNull(panel);
        assertEquals(radius, panel.getCornerRadius());
    }

    @Test
    void testRoundedPanelBackground() {
        RoundedPanel panel = new RoundedPanel(15);
        Color expectedBackground = Color.BLUE;
        panel.setBackground(expectedBackground);
        Color actualBackground = panel.getBackground();
        assertEquals(expectedBackground, actualBackground);
    }

    @Test
    void testRoundedPanelPreferredSize() {
        RoundedPanel panel = new RoundedPanel(10);
        Dimension expectedSize = new Dimension(200, 100);
        panel.setPreferredSize(expectedSize);
        Dimension actualSize = panel.getPreferredSize();
        assertEquals(expectedSize, actualSize);
    }
}
