package com.company.z3;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static java.awt.SystemColor.window;

public class Frame extends JFrame {
    Radar radar;
    Menu menu;

    public Frame() throws IOException {
        this.setPreferredSize(new Dimension(1200, 700));
        this.setLayout(new BorderLayout(0,0));
        Color grey = new Color(98, 105, 93);
        this.setBackground(grey);

        radar = new Radar();
        menu = new Menu(radar);
        radar.addMenu(menu);

        radar.loadMap("C:\\Users\\Lenovo\\Desktop\\FlightControl2\\src\\com\\company\\z3"); // UWAGA tutaj przekopiowac sciezke do mapy na swoim komputerze
        menu.showAirshipList();

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
