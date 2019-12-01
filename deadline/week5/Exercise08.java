package com.company;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class Exercise8 {
    Exercise8() {
        var frame = new JFrame("frame");
        var button = new Button("Get image");
        var label = new JLabel("Icon");
        button.addActionListener(event -> {
            JFileChooser fc = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, GIF & PNG", "jpg", "gif", "png");
            fc.setFileFilter(filter);
            int i = fc.showOpenDialog(null);
            if(i == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                Icon icon = new ImageIcon(file.getAbsolutePath());
                label.setIcon(icon);
            }
        });
        frame.getContentPane().add(BorderLayout.CENTER, label);
        frame.getContentPane().add(BorderLayout.SOUTH, button);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}
