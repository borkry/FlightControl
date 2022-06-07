package com.company.z3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAirshipWindow extends JPanel implements ActionListener {

    JFrame addAirshipFrame = new JFrame();
    JRadioButton rbPlane, rbHeli, rbGlider, rbBallon;
    JButton addLocation, addAirship;

    public void AddAirshipWindow(){
        //label.setFont(new Font(null,Font.PLAIN,25));      na potem

        JLabel lChoose = new JLabel("Wybierz typ statku:");
        ButtonGroup bgChoose = new ButtonGroup();
        rbPlane = new JRadioButton("Samolot");
        rbHeli = new JRadioButton("Helikopter");
        rbGlider = new JRadioButton("Szybowiec");
        rbBallon = new JRadioButton("Balon");
        bgChoose.add(rbPlane);
        bgChoose.add(rbHeli);
        bgChoose.add(rbGlider);
        bgChoose.add(rbBallon);

        JLabel lStartPosition = new JLabel("Podaj położenie początkowe statku:");
        JLabel lStartX = new JLabel("X:");
        JTextField enterX1 = new JTextField(4);
        JLabel lStartY = new JLabel("Y:");
        JTextField enterY1 = new JTextField(4);

        JLabel lDestination = new JLabel("Podaj cel statku:");
        JLabel lDestinationX= new JLabel("X:");
        JTextField enterX2 = new JTextField(4);
        JLabel lDestinationY = new JLabel("Y:");
        JTextField enterY2 = new JTextField(4);

        addLocation = new JButton("Dodaj");
        JLabel hint = new JLabel("Możesz wskazać kilka kolejnych celów przed zatwierdzeniem trasy.");
        addAirship = new JButton("Zatwierdź");

        lChoose.setBounds(5,0,300,30);
        rbPlane.setBounds(5,30,90,30);
        rbHeli.setBounds(95,30,90,30);
        rbGlider.setBounds(185,30,90,30);
        rbBallon.setBounds(275,30,90,30);

        lStartPosition.setBounds(5,60,220,30);
        lStartX.setBounds(5,90,20,30);
        enterX1.setBounds(25,90,30,30);
        lStartY.setBounds(65,90,20,30);
        enterY1.setBounds(85,90,30,30);

        lDestination.setBounds(5,120,220,30);
        lDestinationX.setBounds(5,150,20,30);
        enterX2.setBounds(25,150,30,30);
        lDestinationY.setBounds(65,150,20,30);
        enterY2.setBounds(85,150,30,30);
        addLocation.setBounds(120,150,100,30);
        hint.setBounds(5,150,500,30);
        addAirship.setBounds(5,180, 100,30);


        addAirshipFrame.add(lChoose);
        addAirshipFrame.add(rbPlane);
        addAirshipFrame.add(rbHeli);
        addAirshipFrame.add(rbGlider);
        addAirshipFrame.add(rbBallon);
        addAirshipFrame.add(lStartPosition);
        addAirshipFrame.add(lStartX);
        addAirshipFrame.add(enterX1);
        addAirshipFrame.add(lStartY);
        addAirshipFrame.add(enterY1);
        addAirshipFrame.add(lDestination);
        addAirshipFrame.add(lDestinationX);
        addAirshipFrame.add(enterX2);
        addAirshipFrame.add(lDestinationY);
        addAirshipFrame.add(enterY2);
        addAirshipFrame.add(addLocation);
        addAirshipFrame.add(addAirship);

        addAirshipFrame.setTitle("Dodawanie samolotu");
        addAirshipFrame.setSize(500,300);
        addAirshipFrame.setLayout(null);
        addAirshipFrame.setLocationRelativeTo(null);
        addAirshipFrame.setVisible(true);

        rbPlane.addActionListener(this);
        rbHeli.addActionListener(this);
        rbGlider.addActionListener(this);
        rbBallon.addActionListener(this);
        addAirship.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
