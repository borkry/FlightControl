package com.company.z3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Menu extends JPanel implements ActionListener{
    //<String> myList;
    JButton bAddAirShip;

    public Menu() {
        ArrayList<String> myList = new ArrayList<>(10);
        bAddAirShip = new JButton("Dodaj statek");
        //bAddAirShip.setBounds(100,100,50,20); moze sie przyda
        bAddAirShip.addActionListener(this);
        //bExit = new JButton("Wyłącz radar"); a to nie wiem jak zrobic bo dispose() dziala dla jframe a my mamy jpanel wiec zostawiam, zawsze jest krzyzyk na gorze po prawej

        for(int i =0; i<20; i++) {
            myList.add("Airship " +i);
        }
        JList<String> list = new JList<String>(myList.toArray(new String[myList.size()]));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(list);
        list.setLayoutOrientation(JList.VERTICAL);
        this.add(scrollPane);
        this.add(bAddAirShip);
        this.setPreferredSize(new Dimension(1200, 100));
        this.setBackground(Color.CYAN);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == bAddAirShip) {

            AddAirshipWindow addAirshipWindow = new AddAirshipWindow();
        }
    }

//    public void setMyList(ArrayList<Airship> airships) {
//        myList = new ArrayList<>(airships.size());
//        for(Airship airship : airships) {
//            myList.add(Integer.toString(airship.getId()));
//        }
//    }

}
