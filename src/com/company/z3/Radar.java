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
import java.util.Random;

public class Radar extends JPanel implements Runnable{
    private ArrayList<Airship> airships;
    private ArrayList<GroundObject> groundObjects;
    Image image;
    Graphics graphics;
    ArrayList<Section> s1 = new ArrayList<Section>();
    Route r1 = new Route(s1);
    MyRectangle myRectangle1 = new MyRectangle(new Point(5,6), 20,20); // stworzone do testow
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
        groundObjects = new ArrayList<GroundObject>(lines.size()/6);
        for(int i=0;i<lines.size();i++){
            if(lines.get(i).equals("GroundObject")){
                int groundObjectX = Integer.parseInt(lines.get(i+1));
                int groundObjectY = Integer.parseInt(lines.get(i+2));
                int groundObjectWidth = Integer.parseInt(lines.get(i+3));
                int groundObjectHeight = Integer.parseInt(lines.get(i+4));  // wysokosc prostokata do narysowania na mapie
                int heightOfGroundObject = Integer.parseInt(lines.get(i+5)); // fizyczna wysokosc obiektu nieruchomego
                Point newPoint = new Point(groundObjectX, groundObjectY);
                MyRectangle newMyRectangle = new MyRectangle(newPoint, groundObjectWidth, groundObjectHeight);
                groundObjects.add(new GroundObject(newMyRectangle, heightOfGroundObject));
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

    public void drawAirship(int amount){ //losowanie nowych samolotów
        Random random = new Random();
        for(int i=0;i<amount;i++){//pętla do losowania i tworzenia samolotu
            int newAirshipType = random.nextInt(4);
            int newAirshipX = random.nextInt(100);
            int newAirshipY = random.nextInt(100);
            int newAirshipWidth = random.nextInt(50);
            int newAirshipHeight = newAirshipWidth;//kwadrat
            Point newPoint = new Point(newAirshipX, newAirshipY);
            MyRectangle newMyRectangle = new MyRectangle(newPoint, newAirshipWidth, newAirshipHeight);

            int numberOfSections = random.nextInt(5);
            ArrayList<Section> sections = new ArrayList<Section>(numberOfSections);
            for(int j=0;j<numberOfSections;j++){//pętla do losowania i tworzenia trasy
                int newBeginningX = random.nextInt(100);
                int newBeginningY = random.nextInt(100);
                int newEndingX = random.nextInt(100);
                int newEndingY = random.nextInt(100);
                double newVelocity = random.nextDouble(10);
                double newAltitude = random.nextDouble(10);
                int newDirection = random.nextInt(2);
                Section newSection = new Section(new Point(newBeginningX, newBeginningY), new Point(newEndingX, newEndingY), newVelocity, newAltitude, newDirection);
                sections.add(newSection);
            }
            Route newRoute = new Route(sections);
            switch(newAirshipType){
                case 0:
                    Balloon balloon = new Balloon(newRoute, newMyRectangle);
                    break;
                case 1:
                    Glider glider = new Glider(newRoute, newMyRectangle);
                    break;
                case 2:
                    Helicopter helicopter = new Helicopter(newRoute, newMyRectangle);
                    break;
                case 3:
                    Plane plane = new Plane(newRoute, newMyRectangle);
                    break;
            }
        }
    }

    public void modifyRoute(int airshipId, Route newRoute){ //chyba trzeba przeszukać listę samolotów i ten, którego id=airshipId zmieniamy trase
        for(Airship airship : airships){
            if(airship.getId()==airshipId){
                airship.route = newRoute;
            }
        }
    }

    public void checkAirshipsCollisionsWithGroundObjects(){ //sprawdzenie kolizji statków z obiektami naziemnymi
        for(Airship airship : airships){
            for(GroundObject groundObject : groundObjects){
                if(airship.collisionZone == groundObject.collisionZone){ //jest kolizja samolotu z obiektem naziemnym
                    airship.ifCollision(airship);
                }
                /*else if(odległość między nimi < pewnej wartośći){
                    jest zagrożenie kolizją, zmienia się kolor samolotu
                }
                else{
                    nie ma zagrożenia kolizją, w sumie nic się nie dzieje wtedy
                }
                 */
            }
        }
    }
    public void checkAirshipsCollisionsWithAirships(){ //sprawdzenie kolizji między statkami
        int j;
        for(int i=0; i<airships.size();++i){//samoloty w liście przed samolotem i
            for(j=0;j<i;++j){
                if(airships.get(i).collisionZone == airships.get(j).collisionZone){ //jest kolizja między samolotami
                    airships.get(i).ifCollision(airships.get(i));
                    airships.get(j).ifCollision(airships.get(j));
                }
                /*else if(odległość między nimi < pewnej wartośći){
                    jest zagrożenie kolizją, zmieniają się kolory samolotów
                }
                else{
                    nie ma zagrożenia kolizją, w sumie nic się nie dzieje wtedy
                }
                 */
            }
            for(++j;j<airships.size();++j){ //samoloty w liście po samolocie i
                if(airships.get(i).collisionZone == airships.get(j).collisionZone){ //jest kolizja między samolotami
                    airships.get(i).ifCollision(airships.get(i));
                    airships.get(j).ifCollision(airships.get(j));
                }
                /*else if(odległość między nimi < pewnej wartośći){
                    jest zagrożenie kolizją, zmieniają się kolory samolotów
                }
                else{
                    nie ma zagrożenia kolizją, w sumie nic się nie dzieje wtedy
                }
                 */
            }
        }
    }
    public void checkCollision(){
        checkAirshipsCollisionsWithGroundObjects();
        checkAirshipsCollisionsWithAirships();

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
        for(GroundObject go : groundObjects)
            go.draw(g);
//        groundObjects.get(0).draw(g);
//        groundObjects.get(1).draw(g); //to mozna usunac
        plane1.draw(g);
    }
}
