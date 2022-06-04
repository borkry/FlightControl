package com.company.z3;

import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.nio.file.Path;
import java.io.IOException;

public class Radar extends JPanel implements Runnable{
    private ArrayList<Airship> airships;
    private ArrayList<GroundObject> groundObjects;

    Image image;
    Graphics graphics;
    ArrayList<Section> s1 = new ArrayList<Section>();
    Route r1 = new Route(s1);
    MyRectangle myRectangle1 = new MyRectangle(new Point(5,6), 50,50); // stworzone do testow
    Plane plane1 = new Plane(r1, myRectangle1);

    Thread gameThread;

    public Radar(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public ArrayList<Airship> getAirships() {
        return airships;
    }

    public void loadMap(String fileName) throws IOException{ /// w pliku każa linijka wygląda tak: GroundObject x y width height
        Path path = Paths.get(fileName);
        List<String> lines = Files.readAllLines(path);

        for(int i=0;i<lines.size();i++){
            if(lines.get(i).equals("GroundObject")){
                int groundObjectX = Integer.parseInt(lines.get(i+1));
                int groundObjectY = Integer.parseInt(lines.get(i+2));
                int groundObjectWidth = Integer.parseInt(lines.get(i+3));
                int groundObjectHeight = Integer.parseInt(lines.get(i+4));
                Point newPoint = new Point(groundObjectX, groundObjectY);
                MyRectangle newMyRectangle = new MyRectangle(newPoint, groundObjectWidth, groundObjectHeight);
                groundObjects.add(new GroundObject(newMyRectangle));
            }
        }

    }

    public void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1) {
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }

    public void addAirship(Plane plane){
        airships.add(plane);
    }
    public void addAirship(Helicopter helicopter){
        airships.add(helicopter);
    }
    public void addAirship(Glider glider){
        airships.add(glider);
    }
    public void addAirship(Balloon balloon){
        airships.add(balloon);
    }

    public void deleteAirship(int airshipId){
        if(airships.size()>0) {
            airships.remove(airships.size()-1);
        }
    }

    public void drawAirship(int amount){

    }

    public void modifyRoute(int airshipId, Route newRoute){ //chyba trzeba przeszukać listę samolotów i ten, którego id=airshipId zmieniamy trase

    }

    public void checkCollision(){

    }

    public void move(){
        plane1.move();
    }

    public void paint(Graphics g) {
        image = createImage(1600, 900);
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this); // To ogólny obraz radaru jakby
    }

    public void draw(Graphics g){
        plane1.draw(g);
    }
}
