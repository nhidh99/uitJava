package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.util.Random;

public class Exercise11 {
    Exercise11() {
        var frame = new JFrame("frame");
        JPanel p = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.WHITE);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                Random rand = new Random();
                for (int i = 0; i < 20; i++) {
                    float red = rand.nextFloat();
                    float green = rand.nextFloat();
                    float blue = rand.nextFloat();
                    g2.setColor(new Color(red, green, blue));
                    g2.draw(createDefaultStar(4, 80 * Math.cos(Math.toRadians(18 * i)) + 125, 80 * Math.sin(Math.toRadians(18 * i)) + 125));
                }
            }
        };

        frame.getContentPane().add(p);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    private static Shape createDefaultStar(double radius, double centerX,
                                           double centerY) {
        return createStar(centerX, centerY, radius, radius * 2.63, 5,
                Math.toRadians(-18));
    }

    private static Shape createStar(double centerX, double centerY,
                                    double innerRadius, double outerRadius, int numRays,
                                    double startAngleRad) {
        Path2D path = new Path2D.Double();
        double deltaAngleRad = Math.PI / numRays;
        for (int i = 0; i < numRays * 2; i++) {
            double angleRad = startAngleRad + i * deltaAngleRad;
            double ca = Math.cos(angleRad);
            double sa = Math.sin(angleRad);
            double relX = ca;
            double relY = sa;
            if ((i & 1) == 0) {
                relX *= outerRadius;
                relY *= outerRadius;
            } else {
                relX *= innerRadius;
                relY *= innerRadius;
            }
            if (i == 0) {
                path.moveTo(centerX + relX, centerY + relY);
            } else {
                path.lineTo(centerX + relX, centerY + relY);
            }
        }
        path.closePath();
        return path;
    }
}
