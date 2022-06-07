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
    Section section1 = new Section(new Point(50, 60), new Point(100,70), 300, 200, 1);
    Section section2 = new Section(new Point(100, 70), new Point(120,50), 300, 200, 1);
    Section section3 = new Section(new Point(120, 50), new Point(200,200), 300, 200, 1);


    Section section4 = new Section(new Point(200, 200), new Point(200,200), 0,0,1);
    //teraz ten odcinek jest ostatni na liscie i nie jest usuwany, w funkcji trzeba bedzie dodawac go automatycznie bez wiedzy usera dajac mu wspolrzedne ostatniego polozenia samolotu, dzieki temu mozna usunac fakttyczny ostatni odcinek bez crasha programu. Mysle ze mozemy nie usuwac samolotu, on sobie wyladowal i stoi na ziemi tamu ma zerowa predkosc i wysokosc:P

    LinkedList<Section> s1 = new LinkedList<>(Arrays.asList(section1, section2, section3, section4));
    Route r1 = new Route(s1);

    Section section5 = new Section(new Point(100, 100), new Point(300,120), 300, 1000, 1);
    Section section6 = new Section(new Point(300, 120), new Point(600,120), 300, 1000, 1);
    Section section7 = new Section(new Point(600, 120), new Point(700,220), 300, 1000, 1);
    Section section8 = new Section(new Point(700,220), new Point(700,220), 0,0,1);
    // to samo co section 4

    LinkedList<Section> s2 = new LinkedList<>(Arrays.asList(section5, section6, section7, section8));
    Route r2 = new Route(s2);

    MyRectangle myRectangle1 = new MyRectangle(new Point(50,60), 20,20); // stworzone do testow
    MyRectangle myRectangle2 = new MyRectangle(new Point(100,100), 20,20); // stworzone do testow
    Plane plane1 = new Plane(r1, myRectangle1);
    Plane plane2 = new Plane(r2, myRectangle2);

    ArrayList<Airship> arrships = new ArrayList<>(Arrays.asList(plane1, plane2)); //lista do testow


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
                int groundObjectHeight = Integer.parseInt(lines.get(i+4));  // wspolrzedna prostokata do narysowania na mapie
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
                //checkCollision();
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
            LinkedList<Section> sections = new LinkedList<Section>();
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

  /*  public void checkAirshipsCollisionsWithGroundObjects(){ //sprawdzenie kolizji statków z obiektami naziemnymi
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

            }
        }
    }
    /*public void checkAirshipsCollisionsWithAirships(){ //sprawdzenie kolizji między statkami
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

            }
        }
    } */
    public void checkCollision(){
        //checkAirshipsCollisionsWithGroundObjects();
        //checkAirshipsCollisionsWithAirships();

    }

    public void move(){
        //plane1.move(plane1.getRoute().getCurrentSection());

        //plane1.move(s1.get(1));
        //plane1.move(section1);
        for(Airship airship : arrships) { //do testow
            //if(airship.getReachedDestination())
              //  arrships.remove(airship);
            airship.move(airship.getRoute().getCurrentSection());
        }

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
        //plane1.draw(g);

        for(Airship airship : arrships) {
            if(airship instanceof Plane) {  //sprawdzam typ zeby rysowalo w danych kolorach np. plane - czarny
                ((Plane) airship).draw(g);
            }
            for(Section section : airship.getRoute().getSections()) {
                section.draw(g);
            }
        }



    }
}
