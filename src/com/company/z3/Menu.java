package com.company.z3;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Random;

public class Menu extends JPanel implements ActionListener{
    //<String> myList;
    Radar radar;
    ArrayList<Airship> airships;
    JList<String> list = new JList<>();
    JFrame frame = new JFrame();
    JButton bRemoveAirship = new JButton("Usun statek");
    JButton bAddAirShip = new JButton("Dodaj statek");
    JButton bModifyAirShip = new JButton("Modyfikuj statek");
    JButton bAddRandom = new JButton("Dodaj losowy statek");
    JButton bStart = new JButton("Start symulacji");
    DefaultListModel mylist = new DefaultListModel();

    public Menu(Radar radar) {
        this.radar = radar;
        bAddAirShip.setFocusable(false);
        bAddAirShip.addActionListener(this);
        bModifyAirShip.addActionListener(this);
        bAddRandom.addActionListener(this);
        bRemoveAirship.addActionListener(this);
        bStart.addActionListener(this);
        this.add(bAddAirShip);
        this.add(bModifyAirShip);
        this.add(bRemoveAirship);
        this.add(bAddRandom);
        this.add(bStart);


        //bAddAirShip.setBounds(100,100,50,20); moze sie przyda
        //bExit = new JButton("Wyłącz radar"); a to nie wiem jak zrobic bo dispose() dziala dla jframe a my mamy jpanel wiec zostawiam, zawsze jest krzyzyk na gorze po prawej

        this.list.setModel(mylist);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(list);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setFixedCellWidth(50);
        this.add(scrollPane);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1200, 100));
        this.setBackground(Color.CYAN);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
    }

    /*public void showAirshipList(ArrayList<Airship> airships) {
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
*/

    public void addAirshipToList(Airship airship) {
        mylist.addElement(String.valueOf(airship.getId()));
    }


    public void showAirshipList() {
        mylist.clear();
        airships = radar.getAirships();
        for(Airship airship : airships) {
            mylist.addElement(String.valueOf(airship.getId()));
        }
    }

    public Point getRandomPoint() {
        Random random = new Random();
        double x = 1100 * random.nextDouble() + 50;
        double y = random.nextDouble() * 500 + 50;
        return new Point(x,y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bAddAirShip) {

            AddAirshipWindow addAirshipWindow = new AddAirshipWindow(this.radar, this, null);
        }
        if(e.getSource()==bModifyAirShip) {
            int id = Integer.parseInt(String.valueOf(list.getSelectedValue()));
            AddAirshipWindow addAirshipWindow = new AddAirshipWindow(this.radar, this, id);
        }
        if(e.getSource() == bRemoveAirship) {
            //dodac wyjatki
            int id = Integer.parseInt(String.valueOf(list.getSelectedValue()));
            radar.removeAirship(id);
            mylist.removeElementAt(list.getSelectedIndex());
            this.radar.repaint();
        }
        if(e.getSource() == bAddRandom) {

            Random random = new Random();
            Route route = new Route(new LinkedList<>());
            Point startingPoint = getRandomPoint();
            Point actual = startingPoint;
            int points = random.nextInt(5) + 1;
            for(int i=0; i < points; ++i) {
                Point end = getRandomPoint();
                double v = random.nextDouble() * 1000 + 100;
                double a = random.nextDouble() * 10000 + 1000;
                Section newSection = new Section(actual, end, v, a);
                route.addSection(newSection);
                actual = end;
            }
            int type = random.nextInt(4);

            Airship ship = null;
            MyRectangle newMyRectangle = new MyRectangle(startingPoint, 20, 20);
            switch(type){

                case 0:
                    ship = new Balloon(route, newMyRectangle);
                    break;
                case 1:
                    ship = new Glider(route, newMyRectangle);
                    break;
                case 2:
                    ship= new Helicopter(route, newMyRectangle);
                    break;
                case 3:
                    ship = new Plane(route, newMyRectangle);
                    break;
            }
            this.radar.addAirship(ship);
            mylist.addElement(ship.getId());
            this.radar.repaint();
        }
        if(e.getSource() == bStart){
            this.radar.start();
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