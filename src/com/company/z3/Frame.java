package com.company.z3;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Frame extends JFrame {
    Radar radar;
    Menu menu;
    public Frame() throws IOException {
        this.setPreferredSize(new Dimension(1200, 700));
        this.setLayout(new BorderLayout(0,0));

        radar = new Radar();
        menu = new Menu();
        Button button1 = new Button("Button 1");
        radar.add(button1);
        radar.setBackground(Color.red);
        radar.loadMap("D:\\FlightControl\\src\\com\\company\\z3\\map.txt"); //UWAGA tutaj przekopiowac sciezke do mapy na swoim kompie









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
