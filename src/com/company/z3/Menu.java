package com.company.z3;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Menu extends JPanel {
    ArrayList<String> myList;
    public Menu() {
        //ArrayList<String> myList = new ArrayList<>(10);

        for(int i =0; i<20; i++) {
            myList.add("Airship " +i);
        }
        JList<String> list = new JList<String>(myList.toArray(new String[myList.size()]));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(list);
        list.setLayoutOrientation(JList.VERTICAL);
        this.add(scrollPane);
        this.setPreferredSize(new Dimension(1600, 100));
        this.setBackground(Color.CYAN);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
    }

    public void setMyList(ArrayList<Airship> airships) {
        myList = new ArrayList<>(airships.size());
        for(Airship airship : airships) {
            myList.add(Integer.toString(airship.getId()));
        }
    }

}
