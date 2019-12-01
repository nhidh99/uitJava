package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exercise5 {
    Exercise5(){
        JButton btn1 = new JButton("Left");
        JButton btn2 = new JButton("Top");
        JButton btn3 = new JButton("Right");
        JButton btn4 = new JButton("Bottom");
        JButton btn5 = new JButton("Center");

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                btn2.setEnabled(true);
                btn3.setEnabled(true);
                btn4.setEnabled(true);
                btn5.setEnabled(true);
                btn1.setEnabled(false);
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                btn1.setEnabled(true);
                btn3.setEnabled(true);
                btn4.setEnabled(true);
                btn5.setEnabled(true);
                btn2.setEnabled(false);
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                btn2.setEnabled(true);
                btn1.setEnabled(true);
                btn4.setEnabled(true);
                btn5.setEnabled(true);
                btn3.setEnabled(false);
            }
        });
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                btn2.setEnabled(true);
                btn3.setEnabled(true);
                btn1.setEnabled(true);
                btn5.setEnabled(true);
                btn4.setEnabled(false);
            }
        });
        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                btn2.setEnabled(true);
                btn3.setEnabled(true);
                btn4.setEnabled(true);
                btn1.setEnabled(true);
                btn5.setEnabled(false);
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(btn1, BorderLayout.LINE_START);
        panel.add(btn2, BorderLayout.PAGE_START);
        panel.add(btn3, BorderLayout.LINE_END);
        panel.add(btn4, BorderLayout.PAGE_END);
        panel.add(btn5, BorderLayout.CENTER);

        JFrame frame = new JFrame("Main Screen");
        frame.add(panel);
        frame.setVisible(true);
        frame.setSize(300,300);
    }
}
