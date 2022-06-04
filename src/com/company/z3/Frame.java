package com.company.z3;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    Radar radar;
    Menu menu;
    public Frame(){
        this.setPreferredSize(new Dimension(1600, 900));
        this.setLayout(new BorderLayout(0,0));

        radar = new Radar();
        menu = new Menu();
        Button button1 = new Button("Button 1");
        radar.add(button1);
        radar.setBackground(Color.red);








        this.add(radar, BorderLayout.CENTER);
        this.add(menu, BorderLayout.SOUTH);
        this.setTitle("Flight Control");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
}
