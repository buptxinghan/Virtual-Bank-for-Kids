package com.virtualbankv2.entity;

import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import static org.junit.jupiter.api.Assertions.*;

public class RoundedLabelTest {

    @Test
    public void testConstructor() {
        String labelText = "Test Label";
        RoundedLabel roundedLabel = new RoundedLabel(labelText);

        assertEquals(labelText, roundedLabel.getText());
        assertFalse(roundedLabel.isOpaque());
    }

    @Test
    public void testPaintComponent() {
        RoundedLabel roundedLabel = new RoundedLabel("Test");
        roundedLabel.setSize(100, 50);

        roundedLabel.setBackground(Color.RED);

        BufferedImage image = new BufferedImage(100, 50, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();

        roundedLabel.paintComponent(graphics);

        Color pixelColor = new Color(image.getRGB(10, 10), true);

        assertEquals(new Color(roundedLabel.getBackground().getRGB()), pixelColor);

        graphics.dispose();
    }
}
