package com.company;

import javax.swing.*;
import java.awt.*;

public class Exercise12 {
    Exercise12() {
        var frame = new JFrame("frame");
        var slider = new JSlider(0, 10, 1);
        slider.setPaintTrack(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(1);
        slider.setMinorTickSpacing(1);

        JPanel p = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawCenteredCircle(g, 135, 110, 15 * slider.getValue());
            }
        };

        slider.addChangeListener((e) -> {
            p.repaint();
        });

        frame.getContentPane().add(BorderLayout.SOUTH, slider);
        frame.getContentPane().add(BorderLayout.CENTER, p);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    void drawCenteredCircle(Graphics g, int x, int y, int r) {
        Graphics2D g2 = (Graphics2D) g;
        x = x - (r >>> 1);
        y = y - (r >>> 1);
        g2.drawOval(x, y, r, r);
    }
}
