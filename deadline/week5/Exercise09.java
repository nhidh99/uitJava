package com.company;

import javax.swing.*;
import java.awt.*;

public class Exercise9 {
    Exercise9() {
        var frame = new JFrame("frame");
        var textfield = new JTextField("Your input here");
        var font = new Font("Serif", Font.PLAIN,13);
        textfield.setFont(font);
        var regular = new JRadioButton("Regular");
        var bold = new JRadioButton("Bold");
        var italic = new JRadioButton("Italic");
        var italicBold = new JRadioButton("Italic & Bold");
        regular.setSelected(true);
        regular.addActionListener(event -> {
            if(regular.isSelected()) {
                bold.setSelected(false);
                italic.setSelected(false);
                italicBold.setSelected(false);
                textfield.setFont(new Font("Serif", Font.PLAIN, 13));
            }
        });

        bold.addActionListener(e-> {
            if(bold.isSelected()) {
                regular.setSelected(false);
                italic.setSelected(false);
                italicBold.setSelected(false);
                textfield.setFont(new Font("Serif", Font.BOLD, 13));
            }
        });

        italic.addActionListener(e -> {
            if(italic.isSelected()) {
                bold.setSelected(false);
                regular.setSelected(false);
                italicBold.setSelected(false);
                textfield.setFont(new Font("Serif", Font.ITALIC, 13));
            }
        });

        italicBold.addActionListener(e-> {
            if(italicBold.isSelected()) {
                bold.setSelected(false);
                italic.setSelected(false);
                regular.setSelected(false);
                textfield.setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 13));
            }
        });
        var p = new JPanel(new FlowLayout());
        p.add(regular);
        p.add(bold);
        p.add(italic);
        p.add(italicBold);

        frame.getContentPane().add(BorderLayout.CENTER, textfield);
        frame.getContentPane().add(BorderLayout.SOUTH, p);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}
