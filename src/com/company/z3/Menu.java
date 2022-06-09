package com.company.z3;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel implements ActionListener{
    //<String> myList;
    private ArrayList<String> myList = new ArrayList<>();
    JFrame frame = new JFrame();
    JButton bAddAirShip = new JButton("Dodaj statek");

    public Menu() {
        bAddAirShip.setFocusable(false);
        bAddAirShip.addActionListener(this);
        this.add(bAddAirShip);

        //bAddAirShip.setBounds(100,100,50,20); moze sie przyda
        //bExit = new JButton("Wyłącz radar"); a to nie wiem jak zrobic bo dispose() dziala dla jframe a my mamy jpanel wiec zostawiam, zawsze jest krzyzyk na gorze po prawej

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1200, 100));
        this.setBackground(Color.CYAN);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
    }

    public void showAirshipList(ArrayList<Airship> airships) {
        int size = airships.size();
        myList.clear();
        for(Airship airship : airships) {
            if(airship instanceof Plane)
                myList.add(airship.getId() + " Plane");
            if(airship instanceof Balloon)
                myList.add(airship.getId() + " Balloon");
        }

        JList<String> list = new JList<String>(myList.toArray(new String[myList.size()]));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(list);
        list.setLayoutOrientation(JList.VERTICAL);
        this.add(scrollPane);

        //this.setPreferredSize(new Dimension(1200, 100));
        //this.setBackground(Color.CYAN);
        //this.setLayout(new FlowLayout(FlowLayout.LEFT));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bAddAirShip) {

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

/*ArrayList<String> myList = new ArrayList<>(10);

        for(int i =0; i<20; i++) {
            myList.add("Airship " +i);
        }
        JList<String> list = new JList<String>(myList.toArray(new String[myList.size()]));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(list);
        list.setLayoutOrientation(JList.VERTICAL);
        this.add(scrollPane); */