package com.company.z3;

import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.nio.file.Path;
import java.io.IOException;
import java.util.List;

public class Radar extends JPanel implements Runnable{
    private ArrayList<Airship> airships;
    private ArrayList<GroundObject> groundObjects;
    Image image;
    Graphics graphics;
    Menu menu;
    boolean is_running = false;
    Section section1 = new Section(new Point(50, 60), new Point(100,70), 300, 5);
    Section section2 = new Section(new Point(100, 70), new Point(120,50), 300, 5);
    Section section3 = new Section(new Point(120, 50), new Point(200,200), 300, 5);


    LinkedList<Section> s1 = new LinkedList<>(Arrays.asList(section1, section2, section3));
    Route r1 = new Route(s1);

    Section section5 = new Section(new Point(150, 90), new Point(200,10), 300, 5);
    Section section6 = new Section(new Point(200, 10), new Point(320,400), 300, 5);
    Section section7 = new Section(new Point(320, 400), new Point(600,200), 300, 5);
    // to samo co section 4

    LinkedList<Section> s2 = new LinkedList<>(Arrays.asList(section5, section6, section7));
    Route r2 = new Route(s2);

    MyRectangle myRectangle1 = new MyRectangle(new Point(50,60), 20,20); // stworzone do testow
    MyRectangle myRectangle2 = new MyRectangle(new Point(150,90), 20,20); // stworzone do testow
    Plane plane1 = new Plane(r1, myRectangle1);
    Balloon balloon = new Balloon(r2, myRectangle2);

    ArrayList<Airship> arrships = new ArrayList<>(Arrays.asList(plane1, balloon)); //lista do testow


    Thread gameThread;

    public Radar(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void start() {
        is_running = true;

    }

    public void addMenu(Menu menu) {
        this.menu = menu;
    }

    public ArrayList<Airship> getAirships() {
        return arrships;
    }

    public void loadMap(String fileName) throws IOException{                    // w pliku każa linijka wygląda tak: GroundObject x y width height goHeight
        Path path = Paths.get(fileName);
        List<String> lines = Files.readAllLines(path);
        groundObjects = new ArrayList<GroundObject>(lines.size()/6);
        for(int i=0;i<lines.size();i++){
            if(lines.get(i).equals("GroundObject")){
                int groundObjectX = Integer.parseInt(lines.get(i+1));
                int groundObjectY = Integer.parseInt(lines.get(i+2));
                int groundObjectWidth = Integer.parseInt(lines.get(i+3));
                int groundObjectHeight = Integer.parseInt(lines.get(i+4));                              // wspolrzedna prostokata do narysowania na mapie
                int heightOfGroundObject = Integer.parseInt(lines.get(i+5));                            // fizyczna wysokosc obiektu nieruchomego
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
                if(is_running) {
                    move();
                    checkCollision();
                    repaint();
                }
                delta--;
            }
        }
    }

    public void addAirship(Airship plane){
        arrships.add(plane);
    }

    public void removeAirship(int airshipId) {
        for(int i=0; i<arrships.size();i++) {
            if(arrships.get(i).getId() == airshipId) {
                arrships.remove(i);
                break;
            }
        }

    }

    public void drawAirship(int amount){                        //losowanie nowych samolotów
        Random random = new Random();
        for(int i=0;i<amount;i++){                              //pętla do losowania i tworzenia samolotu
            int newAirshipType = random.nextInt(4);
            int newAirshipX = random.nextInt(100);
            int newAirshipY = random.nextInt(100);
            int newAirshipWidth = random.nextInt(50);
            int newAirshipHeight = newAirshipWidth;                     //kwadrat
            Point newPoint = new Point(newAirshipX, newAirshipY);
            MyRectangle newMyRectangle = new MyRectangle(newPoint, newAirshipWidth, newAirshipHeight);

            int numberOfSections = random.nextInt(5) + 1;
            LinkedList<Section> sections = new LinkedList<Section>();
            for(int j=0;j<numberOfSections;j++){                    //pętla do losowania i tworzenia trasy
                int newBeginningX = random.nextInt(1000);
                int newBeginningY = random.nextInt(100);
                int newEndingX = random.nextInt(1000);
                int newEndingY = random.nextInt(600);
                double newVelocity = random.nextDouble() * 1000 + 100;
                double newAltitude = random.nextDouble() * 10000 + 1000;
                int newDirection = random.nextInt(2);
                Section newSection = new Section(new Point(newBeginningX, newBeginningY), new Point(newEndingX, newEndingY), newVelocity, newAltitude);
                sections.add(newSection);
            }
            Route newRoute = new Route(sections);
            Airship ship = null;
            switch(newAirshipType){

                case 0:
                    ship = new Balloon(newRoute, newMyRectangle);
                    break;
                case 1:
                    ship = new Glider(newRoute, newMyRectangle);
                    break;
                case 2:
                    ship= new Helicopter(newRoute, newMyRectangle);
                    break;
                case 3:
                    ship = new Plane(newRoute, newMyRectangle);
                    break;
            }
            addAirship(ship);
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
        for(Airship airship : arrships){
            for(GroundObject groundObject : groundObjects){
                if(airship.ifCollision(groundObject)) {
                    airship.setIsCollision(true);
                }
                if(airship.ifCloseCollision(groundObject)) {
                    airship.setIsCloseCollision(true);
                }

            }
        }
    }

    public void checkAirshipsCollisionsWithAirships() { //sprawdzenie kolizji między statkami
        int j;
        for (int i = 0; i < arrships.size(); ++i) {//samoloty w liście przed samolotem i
            for (j = 0; j < i; ++j) {

                if (arrships.get(i).ifCloseCollision(arrships.get(j))) {
                    arrships.get(i).setIsCloseCollision(true);
                    arrships.get(j).setIsCloseCollision(true);
                }

                if (arrships.get(i).ifCollision(arrships.get(j))) {
                    arrships.get(i).setIsCollision(true);
                    arrships.get(j).setIsCollision(true);
                }

            }
        }
    }

    public void checkCollision(){
        checkAirshipsCollisionsWithGroundObjects();
        checkAirshipsCollisionsWithAirships();

    }

    public void move(){
        //plane1.move(plane1.getRoute().getCurrentSection());

        //plane1.move(s1.get(1));
        //plane1.move(section1);
        if (arrships.size()>0) {
            for (Airship airship : arrships) { //do testow
                airship.move(airship.getRoute().getCurrentSection());
            }
            if(arrships.removeIf(Airship::getReachedDestination))
                this.menu.showAirshipList();
            if(arrships.size() == 0){
                is_running = false;
            }
        }
    }

    public void paint(Graphics g) {
        image = createImage(1200, 700);
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this); // To ogólny obraz radaru jakby
    }

    public void draw(Graphics g){
        for(GroundObject go : groundObjects)        // wyswietlanie obiektow nieruchomych na mapie
            go.draw(g);

        for(Airship airship : arrships) {
            airship.draw(g);                                                            // wyswietlanie statkow powietrznych oraz ich odcinkow na radarze
            for(Section section : airship.getRoute().getSections()) {
                section.draw(g);
            }
        }

    }

}
