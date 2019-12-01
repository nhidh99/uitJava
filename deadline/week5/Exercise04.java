package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exercise4 {
    Exercise4(){
        JButton btn1 = new JButton("Left");
        btn1.setBounds(100,100,100,50);

        JButton btn2 = new JButton("Center");
        btn2.setBounds(250,100,100,50);

        JButton btn3 = new JButton("Right");
        btn3.setBounds(400,100,100,50);

        JButton btnBack = new JButton("Back-Before-Tap-Again");
        btnBack.setBounds(0,0,250,50);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                btn1.setBounds(100,100,100,50);
                btn2.setBounds(250,100,100,50);
                btn3.setBounds(400,100,100,50);
            }
        });

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                btn2.setBounds(100,200,100,50);
                btn3.setBounds(100,300,100,50);
            }
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                btn1.setBounds(250,200,100,50);
                btn3.setBounds(250,300,100,50);
            }
        });

        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                btn1.setBounds(400,200,100,50);
                btn2.setBounds(400,300,100,50);
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(btn1);
        panel.add(btn2);
        panel.add(btn3);
        panel.add(btnBack);

        JFrame frame = new JFrame("Main Screen");
        frame.add(panel);
        frame.setSize(600,300);
        frame.setVisible(true);
    }
}
