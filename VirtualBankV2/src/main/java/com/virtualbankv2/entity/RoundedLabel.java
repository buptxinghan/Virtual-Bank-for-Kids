package com.virtualbankv2.entity;

import javax.swing.*;
import java.awt.*;

public class RoundedLabel extends JLabel {
    private int arcH = 20;  // Horizontal arc
    private int arcW = 20;  // Vertical arc

    public RoundedLabel(String text) {
        super(text);
        setOpaque(false);
    }

    public int getArcW() {
        return arcW;
    }

    public int getArcH() {
        return arcH;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcW, arcH);
        super.paintComponent(g);
    }
}

