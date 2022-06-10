package com.company.z3;

import javax.management.InvalidAttributeValueException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import static java.lang.Double.parseDouble;

public class AddAirshipWindow extends JPanel implements ActionListener {

    JFrame addAirshipFrame = new JFrame();
    JRadioButton rbPlane, rbHeli, rbGlider, rbBallon;
    JButton addDestination, addAirship, setStart;
    JTextField enterX1, enterY1, enterX2, enterY2, enterAltitude, enterVelocity;
    JLabel hint;
    Route newRoute;
    Point startingPoint;
    Point actualPoint;
    Radar radar;
    Menu menu;
    Integer id;


    public AddAirshipWindow(Radar radar, Menu menu, Integer id) throws InvalidAttributeValueException {
        this.radar = radar;
        this.menu = menu;
        this.id = id;

        newRoute = new Route(new LinkedList<Section>());


        JLabel lChoose = new JLabel("Wybierz typ statku:");
        ButtonGroup bgChoose = new ButtonGroup();
        rbPlane = new JRadioButton("Samolot", true);
        rbHeli = new JRadioButton("Helikopter");
        rbGlider = new JRadioButton("Szybowiec");
        rbBallon = new JRadioButton("Balon");
        bgChoose.add(rbPlane);
        bgChoose.add(rbHeli);
        bgChoose.add(rbGlider);
        bgChoose.add(rbBallon);

        JLabel sizeHintX = new JLabel("Zakres X: <0;1200>");
        JLabel sizeHintY = new JLabel("Zakres Y: <0;600>");

        JLabel lStartPosition = new JLabel("Podaj położenie początkowe statku:");
        JLabel lStartX = new JLabel("X:");
        enterX1 = new JTextField(4);
        JLabel lStartY = new JLabel("Y:");
        enterY1 = new JTextField(4);
        setStart = new JButton("Zatwierdź");

        JLabel lDestination = new JLabel("Podaj cel statku, wysokość oraz prędkość na trasie:");
        JLabel lDestinationX= new JLabel("X:");
        enterX2 = new JTextField(4);
        JLabel lDestinationY = new JLabel("Y:");
        enterY2 = new JTextField(4);
        JLabel lDestinationAltitude= new JLabel("Prędkość:");
        enterAltitude = new JTextField(4);
        JLabel lDestinationVelocity = new JLabel("Wysokość:");
        enterVelocity = new JTextField(4);
        addDestination = new JButton("Dodaj cel");
        hint = new JLabel("Możesz wskazać kilka kolejnych celów przed zatwierdzeniem trasy.");
        addAirship = new JButton("Zatwierdź trasę");

        lChoose.setBounds(5,0,300,30);
        rbPlane.setBounds(5,30,90,30);
        rbHeli.setBounds(95,30,90,30);
        rbGlider.setBounds(185,30,90,30);
        rbBallon.setBounds(275,30,90,30);
        sizeHintX.setBounds(380,0,110,20);
        sizeHintY.setBounds(380,20,110,20);

        lStartPosition.setBounds(5,60,220,30);
        lStartX.setBounds(5,90,20,30);
        enterX1.setBounds(25,90,30,30);
        lStartY.setBounds(65,90,20,30);
        enterY1.setBounds(85,90,30,30);
        setStart.setBounds(350,90,110,30);

        lDestination.setBounds(5,120,420,30);
        lDestinationX.setBounds(5,150,20,30);
        enterX2.setBounds(25,150,30,30);
        lDestinationY.setBounds(65,150,20,30);
        enterY2.setBounds(85,150,30,30);
        lDestinationAltitude.setBounds(125, 150, 65,30);
        enterAltitude.setBounds(190, 150,30,30);
        lDestinationVelocity.setBounds(230,150,65,30);
        enterVelocity.setBounds(300,150,30,30);
        addDestination.setBounds(350,150,110,30);
        hint.setBounds(5,180,500,30);


        addAirship.setBounds(180,220, 140,30);


        addAirshipFrame.add(lChoose);
        addAirshipFrame.add(rbPlane);
        addAirshipFrame.add(rbHeli);
        addAirshipFrame.add(rbGlider);
        addAirshipFrame.add(rbBallon);
        addAirshipFrame.add(sizeHintX);
        addAirshipFrame.add(sizeHintY);

        addAirshipFrame.add(lStartPosition);
        addAirshipFrame.add(lStartX);
        addAirshipFrame.add(enterX1);
        addAirshipFrame.add(lStartY);
        addAirshipFrame.add(enterY1);
        addAirshipFrame.add(setStart);
        addAirshipFrame.add(lDestination);
        addAirshipFrame.add(lDestinationX);
        addAirshipFrame.add(enterX2);
        addAirshipFrame.add(lDestinationY);
        addAirshipFrame.add(enterY2);
        addAirshipFrame.add(lDestinationAltitude);
        addAirshipFrame.add(lDestinationVelocity);
        addAirshipFrame.add(enterAltitude);
        addAirshipFrame.add(enterVelocity);
        addAirshipFrame.add(addDestination);
        addAirshipFrame.add(hint);
        addAirshipFrame.add(addAirship);


        if(id == null) {
            addAirshipFrame.setTitle("Dodawanie samolotu");
        } else {
            addAirshipFrame.setTitle("Modyfikowanie trasy");
        }
        addAirshipFrame.setSize(500,300);
        addAirshipFrame.setLayout(null);
        addAirshipFrame.setLocationRelativeTo(null);
        addAirshipFrame.setVisible(true);
        addAirshipFrame.setResizable(false);
        addAirshipFrame.setFont(new Font(null,Font.PLAIN,25));
        Color darkGrey = new Color(98, 105, 93);
        this.setBackground(darkGrey);

        rbPlane.addActionListener(this);
        rbHeli.addActionListener(this);
        rbGlider.addActionListener(this);
        rbBallon.addActionListener(this);
        addAirship.addActionListener(this);
        setStart.addActionListener(this);
        addDestination.addActionListener((this));
        enterVelocity.addActionListener(this);
        enterAltitude.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        MyRectangle newRectangle;
        Point startPoint = new Point(0,0);
        Point destinationPoint = new Point(0,0);

        if(e.getSource()==setStart) {
            double startX=0, startY=0;
            startX = parseDouble(enterX1.getText());
            startY = parseDouble(enterY1.getText());
            enterX1.setText("");
            enterY1.setText("");
            startingPoint = new Point(startX, startY);
            actualPoint = startingPoint;
        }

        if(e.getSource()==addDestination) {
            if(actualPoint != null) {
                double x = parseDouble(enterX2.getText());
                double y = parseDouble(enterY2.getText());
                Point endPoint = new Point(x,y);
                double altitude = parseDouble(enterY2.getText());
                double velocity = parseDouble(enterY2.getText());
                Section newSection = new Section(actualPoint, endPoint, velocity, altitude);
                enterX2.setText("");
                enterY2.setText("");
                enterAltitude.setText("");
                enterVelocity.setText("");
                newRoute.addSection(newSection);
                actualPoint = endPoint;
            } else {
                hint.setText("Podaj punkt początkowy");
            }
        }

        if(e.getSource()==addAirship) {
            Airship newAirship = null;
            if(id == null) {
                if (rbBallon.isSelected()) {
                    newRectangle = new MyRectangle(startingPoint, 20, 20);
                    newAirship = new Balloon(newRoute, newRectangle);
                }
                if (rbHeli.isSelected()) {
                    newRectangle = new MyRectangle(startingPoint, 30, 45);
                    newAirship = new Helicopter(newRoute, newRectangle);
                }
                if (rbGlider.isSelected()) {
                    newRectangle = new MyRectangle(startingPoint, 40, 20);
                    newAirship = new Glider(newRoute, newRectangle);
                }
                if (rbPlane.isSelected()) {
                    newRectangle = new MyRectangle(startingPoint, 50, 50);
                    newAirship = new Plane(newRoute, newRectangle);
                }
                this.radar.addAirship(newAirship);
                this.menu.addAirshipToList(newAirship);
            } else {
                ArrayList<Airship> airships = this.radar.getAirships();
                for(Airship airship : airships){
                    if(airship.getId() == id){
                        airship.modifyRoute(newRoute);
                    }
                }
            }
            this.radar.repaint();
            addAirshipFrame.dispose();
        }
    }
}
