package com.virtualbankv2.entity;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class RoundedButtonTest {

    @Test
    void testRoundedButtonConstructor() {
        String label = "Test Button";
        RoundedButton button = new RoundedButton(label);
        assertNotNull(button);
        assertEquals(label, button.getText());
    }

    @Test
    void testRoundedButtonSize() {
        String label = "Test Button";
        RoundedButton button = new RoundedButton(label);
        Dimension expectedSize = new Dimension(100, 50);
        button.setPreferredSize(expectedSize);
        Dimension actualSize = button.getPreferredSize();
        assertEquals(expectedSize, actualSize);
    }

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
