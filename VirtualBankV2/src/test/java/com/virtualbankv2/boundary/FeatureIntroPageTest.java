package com.virtualbankv2.boundary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the functionality of the FeatureIntroPage class.
 * It includes tests for initializing the main panel, top panel, bottle panel, and gradient background.
 *
 * @version 1.0
 * @since 2024-04-25
 * @author Ji Zheng
 */

public class FeatureIntroPageTest {

    private FeatureIntroPage featureIntroPage;

    @BeforeEach
    public void setUp() {
        featureIntroPage = new FeatureIntroPage();
    }

    @Test
    public void testMainPanelInitialization() {
        JPanel mainPanel = (JPanel) ((JScrollPane) featureIntroPage.getContentPane().getComponent(1)).getViewport().getView();
        assertNotNull(mainPanel);
        assertEquals(GridBagLayout.class, mainPanel.getLayout().getClass());

        Component[] components = mainPanel.getComponents();
        assertEquals(15, components.length); // 8 Q&A panels + 7 separators

        for (int i = 0; i < components.length; i++) {
            if (i % 2 == 0) {
                assertTrue(components[i] instanceof JPanel);
            } else {
                assertTrue(components[i] instanceof JSeparator);
            }
        }
    }

    @Test
    public void testTopPanelInitialization() {
        JPanel topPanel = (JPanel) featureIntroPage.getContentPane().getComponent(0);
        assertNotNull(topPanel);
        assertEquals(new Color(199, 220, 247), topPanel.getBackground());
        assertTrue(topPanel.getComponent(0) instanceof JLabel);
    }

    @Test
    public void testBottlePanelInitialization() {
        JPanel bottlePanel = (JPanel) featureIntroPage.getContentPane().getComponent(2);
        assertNotNull(bottlePanel);
        assertEquals(new Color(199, 220, 247), bottlePanel.getBackground());
        assertTrue(bottlePanel.getComponent(0) instanceof JButton);
    }

    @Test
    public void testGradientBackground() {
        JPanel mainPanel = (JPanel) ((JScrollPane) featureIntroPage.getContentPane().getComponent(1)).getViewport().getView();
        BufferedImage image = new BufferedImage(mainPanel.getWidth(), mainPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        mainPanel.paint(g2d);

        Color topColor = new Color(image.getRGB(0, 0));
        Color bottomColor = new Color(image.getRGB(0, mainPanel.getHeight() - 1));

        assertAll("Gradient Background Colors",
                () -> assertRGBEquals(new Color(0x46a6e2), topColor),
                () -> assertRGBEquals(new Color(0x638bbf), bottomColor)
        );

        g2d.dispose();
    }

    private void assertRGBEquals(Color expected, Color actual) {
        int tolerance = 1;
        assertEquals(expected.getRed(), actual.getRed(), tolerance, "Red component mismatch");
        assertEquals(expected.getGreen(), actual.getGreen(), tolerance, "Green component mismatch");
        assertEquals(expected.getBlue(), actual.getBlue(), tolerance, "Blue component mismatch");
    }
}
