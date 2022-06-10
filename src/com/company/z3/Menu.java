package com.company.z3;

import javax.management.InvalidAttributeValueException;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Random;

public class Menu extends JPanel implements ActionListener{

    Radar radar;
    ArrayList<Airship> airShips;
    JList<String> list = new JList<>();
    JFrame frame = new JFrame();
    JButton bRemoveAirship = new JButton("Usun statek");
    JButton bAddAirShip = new JButton("Dodaj statek");
    JButton bModifyAirShip = new JButton("Modyfikuj statek");
    JButton bAddRandom = new JButton("Dodaj losowy statek");
    JButton bStart = new JButton("Rozpocznij symulację");
    DefaultListModel myList = new DefaultListModel();
    JScrollPane scrollPane = new JScrollPane();

    public Menu(Radar radar) {
        this.radar = radar;

        bAddAirShip.setBounds(30,25,180,50);
        bAddRandom.setBounds(230,25,180,50);
        bStart.setBounds(430,25,180,50);
        bModifyAirShip.setBounds(630,25,180,50);
        bRemoveAirship.setBounds(830,25,180,50);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.setBounds(1030, 10, 100, 80);


        bAddAirShip.addActionListener(this);
        bAddRandom.addActionListener(this);
        bStart.addActionListener(this);
        bModifyAirShip.addActionListener(this);
        bRemoveAirship.addActionListener(this);

        this.add(bAddAirShip);
        this.add(bAddRandom);
        this.add(bModifyAirShip);
        this.add(bRemoveAirship);
        this.add(bStart);

        this.list.setModel(myList);
        scrollPane.setViewportView(list);
        Color scrollPaneGrey = new Color(98, 105, 93);
        list.setBackground(scrollPaneGrey);
        list.setLayoutOrientation(JList.VERTICAL);
        this.add(scrollPane);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1200, 100));
        Color darkGrey = new Color(53, 59, 49);
        this.setBackground(darkGrey);
        this.setFont(new Font(null,Font.PLAIN,25));
        this.setLayout(null);
    }

    public void addAirshipToList(Airship airship) {
        myList.addElement(String.valueOf(airship.getId()));
    }


    public void showAirshipList() {
        myList.clear();
        airShips = radar.getAirships();
        for(Airship airship : airShips) {
            myList.addElement(String.valueOf(airship.getId()));
        }
    }

    public Point getRandomPoint() {
        Random random = new Random();
        double x = 1100 * random.nextDouble() + 50;
        double y = 500 * random.nextDouble()  + 50;
        return new Point(x,y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bAddAirShip) {
            try {
                AddAirshipWindow addAirshipWindow = new AddAirshipWindow(this.radar, this, null);
            }
            catch (InvalidAttributeValueException Ie) {
                JOptionPane.showMessageDialog(frame, "Wprowadzone dane są niepoprawne.");
            }
        }
        if(e.getSource()==bModifyAirShip) {
            int id = Integer.parseInt(String.valueOf(list.getSelectedValue()));
            try {
                AddAirshipWindow addAirshipWindow = new AddAirshipWindow(this.radar, this, id);
            }
            catch (InvalidAttributeValueException Ie) {
                JOptionPane.showMessageDialog(frame, "Wprowadzone dane są niepoprawne.");
            }
        }
        if(e.getSource() == bRemoveAirship) {
            //dodac wyjatki
            int id = Integer.parseInt(String.valueOf(list.getSelectedValue()));
            radar.removeAirship(id);
            myList.removeElementAt(list.getSelectedIndex());
            this.radar.repaint();
        }
        if(e.getSource() == bAddRandom) {

            Random random = new Random();
            Route route = new Route(new LinkedList<>());
            Point startingPoint = getRandomPoint();
            Point actualPoint = startingPoint;
            int points = random.nextInt(5) + 1;
            for(int i=0; i < points; ++i) {
                Point end = getRandomPoint();
                double velocity = random.nextDouble() * 1000 + 100;
                double altitude = random.nextDouble() * 10000 + 1000;
                Section newSection = new Section(actualPoint, end, velocity, altitude);
                route.addSection(newSection);
                actualPoint = end;
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
            myList.addElement(ship.getId());
            this.radar.repaint();
        }
        if(e.getSource() == bStart){
            this.radar.start();
        }
    }
}
