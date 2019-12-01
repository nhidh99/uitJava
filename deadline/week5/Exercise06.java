package com.company;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class Exercise6 {
    Exercise6() {
        JFrame frame = new JFrame("BT6");
        JList<String> listCategory = new JList<String>();
        String[] bookCategories = {"Văn học", "Khoa học", "Lịch sử", "Công nghệ", "Thể thao", "Văn hóa"};
        JLabel label = new JLabel("Hello, please choose a kind of book you want");
        listCategory.setListData(bookCategories);
        listCategory.setVisibleRowCount(5);
        listCategory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listCategory.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                label.setText("Your selection is: " + listCategory.getSelectedValue());
            }
        });
        JScrollPane scrollPane = new JScrollPane(listCategory);
        scrollPane.setSize(200, 200);

        frame.getContentPane().add(BorderLayout.CENTER, scrollPane);
        frame.getContentPane().add(BorderLayout.SOUTH,label);


        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}
