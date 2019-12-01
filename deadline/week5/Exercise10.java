package com.company;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.*;

public class Exercise10 {

    public Exercise10() {
        var shapes = new Shape[5];
        shapes[0] = new Line2D.Double(0.0, 0.0, 100.0, 100.0);
        shapes[1] = new Rectangle2D.Double(10.0, 100.0, 200.0, 200.0);
        shapes[2] = new Ellipse2D.Double(20.0, 200.0, 100.0, 100.0);

        var path = new GeneralPath(new Line2D.Double(300.0, 100.0, 400.0, 150.0));
        path.append(new Line2D.Double(25.0, 175.0, 300.0, 100.0), true);
        shapes[3] = path;
        shapes[4] = new RoundRectangle2D.Double(350.0, 250, 200.0, 100.0, 50.0, 25.0);

        var panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                for (int i = 0; i < shapes.length; ++i) {
                    if (shapes[i] != null)
                        g2.draw(shapes[i]);
                }
            }
        };

        var frame = new JFrame();
        frame.setSize(500, 500);
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
