package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Exercise2 {
    Exercise2(){
        JLabel label = new JLabel("You pressed : ");

        JTextField textField = new JTextField("");
        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                System.out.println(keyEvent.getKeyChar());
                label.setText("You pressed : " + keyEvent.getKeyChar());
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });



        JPanel panel = new JPanel(new GridLayout(0,1));
        panel.add(textField);
        panel.add(label);

        JFrame frame = new JFrame("Main Screen");
        frame.setSize(300,300);
        frame.add(panel);
        frame.setVisible(true);
    }
}

