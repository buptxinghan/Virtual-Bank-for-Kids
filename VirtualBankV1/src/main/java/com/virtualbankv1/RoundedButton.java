package com.virtualbankv1;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedButton extends JButton {
    private Shape shape;
    private int cornerRadius;

    public RoundedButton(String label) {
        super(label);
        this.cornerRadius = 60;
        setOpaque(false); // Make the button transparent
        setContentAreaFilled(false); // Prevent the default square background fill
        setFocusPainted(false); // Remove focus painting
        setBorderPainted(false); // Remove border painting
    }

    // 重写 paintComponent 方法来绘制圆角背景
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (getModel().isArmed()) {
            g2.setColor(getBackground().darker()); // 按钮被按下时的颜色
        } else {
            g2.setColor(getBackground()); // 默认颜色
        }
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius));
        super.paintComponent(g2);
        g2.dispose();
    }

    // 重写 contains 方法来确保点击事件只在圆角区域内触发
    @Override
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        }
        return shape.contains(x, y);
    }
}