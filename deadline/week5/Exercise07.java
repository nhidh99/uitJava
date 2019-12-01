package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

//package com.company
public class Exercise7 {
    Exercise7() {
        var frame = new JFrame("frame");
        var button = new JButton("Click to choose background color");
        JColorChooser colorChooser = new JColorChooser();
        button.addActionListener(e -> {

            JDialog dialog = new JDialog(frame, true);
            dialog.getContentPane().add(colorChooser);

            var btnOK = new Button("OK");
            btnOK.addActionListener(event-> {
                Color color = colorChooser.getColor();
                frame.getContentPane().setBackground(color);
                dialog.setVisible(false);
            });
            var btnCancel = new Button("Cancel");
            btnCancel.addActionListener(event -> {
                dialog.setVisible(false);
            });
            JPanel pane = new JPanel();
            pane.add(btnOK);
            pane.add(btnCancel);
            dialog.getContentPane().add(BorderLayout.SOUTH, pane);
            dialog.setSize(500,500);
            dialog.setVisible(true);
        });

        /*        frame.getContentPane().add(BorderLayout.CENTER, colorChooser);*/
        frame.getContentPane().add(BorderLayout.SOUTH, button);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}
