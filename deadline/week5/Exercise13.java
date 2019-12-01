package com.company;

import javax.swing.*;
import java.awt.*;

public class Exercise13 {
    Font font = new Font("Arial", Font.PLAIN, 14);
    JTextArea textArea = new JTextArea();

    Exercise13() {
        var menuBar = new JMenuBar();
        menuBar.add(FileMenu());
        menuBar.add(FormatMenu());

        var frame = new JFrame("Exercise 13");
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(TextPane());
        frame.setSize(400, 400);
        frame.setVisible(true);
    }

    JMenu FileMenu() {
        var fileMenu = new JMenu("File");
        var aboutItem = new JMenuItem("About");
        var exitItem = new JMenuItem("Exit");
        fileMenu.add(aboutItem);
        fileMenu.add(exitItem);
        return fileMenu;
    }

    JMenu FormatMenu() {
        var formatMenu = new JMenu("Format");
        formatMenu.add(ColorMenu());
        formatMenu.add(FontMenu());
        return formatMenu;
    }

    JMenu FontMenu() {
        var fontMenu = new JMenu("Font");
        var fontItem1 = new JRadioButtonMenuItem("Arial", true);
        var fontItem2 = new JRadioButtonMenuItem("Verdana");
        var fontItem3 = new JRadioButtonMenuItem("Times New Roman");
        var boldItem = new JCheckBoxMenuItem("Bold");
        var italicItem = new JCheckBoxMenuItem("Italic");

        fontItem1.addActionListener((e) -> {
            if (fontItem1.isSelected()) {
                fontItem2.setSelected(false);
                fontItem3.setSelected(false);
                font = new Font("Arial", font.getStyle(), 14);
                textArea.setFont(font);
            }
        });

        fontItem2.addActionListener((e) -> {
            if (fontItem2.isSelected()) {
                fontItem1.setSelected(false);
                fontItem3.setSelected(false);
                font = new Font("Verdana", font.getStyle(), 14);
                textArea.setFont(font);
            }
        });

        fontItem3.addActionListener((e) -> {
            if (fontItem3.isSelected()) {
                fontItem1.setSelected(false);
                fontItem2.setSelected(false);
                font = new Font("Times New Roman", font.getStyle(), 14);
                textArea.setFont(font);
            }
        });

        boldItem.addActionListener((e) -> {
            if (boldItem.isSelected()) {
                font = new Font(font.getName(), Font.BOLD + (italicItem.isSelected() ? Font.ITALIC : 0), 14);
            } else {
                font = new Font(font.getName(), italicItem.isSelected() ? Font.ITALIC : Font.PLAIN, 14);
            }
            textArea.setFont(font);
        });

        italicItem.addActionListener((e) -> {
            if (italicItem.isSelected()) {
                font = new Font(font.getName(), Font.ITALIC + (boldItem.isSelected() ? Font.BOLD : 0), 14);
            } else {
                font = new Font(font.getName(), boldItem.isSelected() ? Font.BOLD : Font.PLAIN, 14);
            }
            textArea.setFont(font);
        });

        fontMenu.add(fontItem1);
        fontMenu.add(fontItem2);
        fontMenu.add(fontItem3);
        fontMenu.add(boldItem);
        fontMenu.add(italicItem);
        return fontMenu;
    }

    JMenu ColorMenu() {
        var colorMenu = new JMenu("Color");
        var redItem = new JMenuItem("Red");
        var greenItem = new JMenuItem("Green");
        var blueItem = new JMenuItem("Blue");

        redItem.addActionListener((e) -> textArea.setForeground(Color.red));
        greenItem.addActionListener((e) -> textArea.setForeground(Color.green));
        blueItem.addActionListener((e) -> textArea.setForeground(Color.blue));

        colorMenu.add(redItem);
        colorMenu.add(blueItem);
        colorMenu.add(greenItem);
        return colorMenu;
    }

    JScrollPane TextPane() {
        textArea.setLineWrap(true);
        textArea.setFont(font);
        textArea.setForeground(Color.red);

        var scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        return scrollPane;
    }
}