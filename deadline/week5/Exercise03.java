package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class Exercise3 {

    public Exercise3() {
        var textAreaSrc = new JTextArea(15, 15);
        var textAreaDes = new JTextArea(15, 15);
        var button = new JButton("Copy text");
        textAreaDes.setLineWrap(true);
        textAreaSrc.setLineWrap(true);

        button.addActionListener((e) -> {
            textAreaDes.setText(textAreaSrc.getSelectedText());
        });

        var frame = new JFrame();
        frame.getContentPane().add(BorderLayout.CENTER, button);
        frame.getContentPane().add(BorderLayout.WEST, textAreaSrc);
        frame.getContentPane().add(BorderLayout.EAST, textAreaDes);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
