package com.company;

import javax.swing.*;
import java.awt.*;

public class Exercise1 {
    Exercise1(){
        var frame = new JFrame("frame");
        frame.setTitle("Main Screen");
        frame.setSize(250,250);

        var textField = new JTextField("");
        textField.setText("Hello " + popupShow());

        frame.add(textField);
        frame.setVisible(true);
    }

    public String popupShow(){
        var field = new JTextField("");
        var panel = new JPanel(new GridLayout(0,1));
        panel.add(new JLabel("Nhap ho ten : "));
        panel.add(field);

        int result = JOptionPane.showConfirmDialog(null, panel, "PopUp",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            return field.getText();
        } else {
            return "Cancelled";
        }
    }
}
