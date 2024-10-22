package com.virtualbankv2.boundary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the functionality of the FAQ class.
 * It includes tests for initializing the main panel, top panel, bottle panel, and gradient background.
 *
 * @version 1.0
 * @since 2024-04-25
 * @author Ji Zheng
 */

public class FAQTest {

    private FAQ faq;

    @BeforeEach
    public void setUp() {
        faq = new FAQ();
    }

    @Test
    public void testMainPanelInitialization() {
        JPanel mainPanel = (JPanel) ((JScrollPane) faq.getContentPane().getComponent(1)).getViewport().getView();
        assertNotNull(mainPanel);
        assertEquals(GridBagLayout.class, mainPanel.getLayout().getClass());

        Component[] components = mainPanel.getComponents();
        assertEquals(9, components.length); // 5 Q&A panels + 4 separators

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
        JPanel topPanel = (JPanel) faq.getContentPane().getComponent(0);
        assertNotNull(topPanel);
        assertEquals(new Color(199, 220, 247), topPanel.getBackground());
        assertTrue(topPanel.getComponent(0) instanceof JLabel);
    }

    @Test
    public void testBottlePanelInitialization() {
        JPanel bottlePanel = (JPanel) faq.getContentPane().getComponent(2);
        assertNotNull(bottlePanel);
        assertEquals(new Color(199, 220, 247), bottlePanel.getBackground());
        assertTrue(bottlePanel.getComponent(0) instanceof JButton);
    }

    @Test
    public void testGradientBackground() {
        JPanel mainPanel = (JPanel) ((JScrollPane) faq.getContentPane().getComponent(1)).getViewport().getView();
        BufferedImage image = new BufferedImage(mainPanel.getWidth(), mainPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        mainPanel.paint(g2d);

        Color topColor = new Color(image.getRGB(0, 0));
        Color bottomColor = new Color(image.getRGB(0, mainPanel.getHeight() - 1));

        assertEquals(new Color(0x46a6e2), topColor);
        assertEquals(new Color(0x638bbf), bottomColor);
        g2d.dispose();
    }
}
