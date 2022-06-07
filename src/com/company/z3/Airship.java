package com.company.z3;

import java.awt.*;

public abstract class Airship extends Rectangle {
    protected Route route;
    protected MyRectangle collisionZone;
    protected static int counter = 1000; // wartości id beda zaczynaly sie od 1000
    protected int id;
    private boolean reachedDestination = false;

    public int getId() {
        return id;
    }

    public boolean getReachedDestination() {
        return reachedDestination;
    }

    public void modifyRoute(Route newRoute){
        this.route = newRoute;
    }

    public boolean ifCollision(Airship airship){
        return false;
    }
    public boolean ifCollision(GroundObject groundObject){
        return false;
    }

    public void move(Section section){
        double speed = section.getVelocity()/100;
        double deltaX = section.getEnd().getX() - collisionZone.getLocation().getX();
        double deltaY = section.getEnd().getY() - collisionZone.getLocation().getY();
        double angle = Math.atan2(deltaY, deltaX);

        //System.out.println(deltaX);
        //System.out.println(deltaY);
        //double currentX = collisionZone.getLocation().getX() + 0 * Math.cos(angle);
        //double currentY = collisionZone.getLocation().getY() + 0 * Math.sin(angle);
        double moveX = speed * Math.cos(angle);
        double moveY = speed * Math.sin(angle);

        if(Math.sqrt(Math.pow((route.getCurrentSection().end.getX() - collisionZone.getLocation().getX()), 2) + Math.pow((route.getCurrentSection().end.getY() - collisionZone.getLocation().getY()), 2)) < Math.sqrt(Math.pow((collisionZone.getLocation().getX()+moveX - collisionZone.getLocation().getX()), 2) + Math.pow((collisionZone.getLocation().getY()+moveY- collisionZone.getLocation().getY()), 2) )) {
            collisionZone.getLocation().setX(route.getCurrentSection().end.getX());
            collisionZone.getLocation().setY(route.getCurrentSection().end.getY());
            if(route.getSections().size()>1)
                route.moveToNextSection();
            else
                reachedDestination = true;
        }
        else
            collisionZone.moveRectangle(speed*Math.cos(angle), speed*Math.sin(angle)); //"droga po ktorej sie porusza" - tutaj chyba trzeba dac parametry zeby dla kazdego obiektu mozna bylo dac inna droge, cos jakby funkcje liniowa chyba

     /*   if(Math.abs(collisionZone.getLocation().getX() - section.getEnd().getX()) < 2 && Math.abs(collisionZone.getLocation().getY() - section.getEnd().getY()) < 2) { // tutaj mozna pomyslec czy mozna to zrobic lepiej, ale probowalem i nie dalo rady, bo tak naprawde X samolotu nigdy nie bedzie rowny X celu
            //System.out.println(route.getCurrentSection().end.getX());
            if(route.getSections().size()>1)
                route.moveToNextSection();                                                   // przejscie do kolejnego odcinka
            //else if(route.getSections().size()<2)
             //   reachedDestination = true;

            //collisionZone.getLocation().setX(route.getCurrentSection().beginning.getX());
            //collisionZone.getLocation().setY(route.getCurrentSection().beginning.getY());
            //System.out.println(route.getCurrentSection().beginning.getX());
        }
        else if(route.getSections().size() < 2 && Math.abs(collisionZone.getLocation().getX() - section.getEnd().getX()) < 2 && Math.abs(collisionZone.getLocation().getY() - section.getEnd().getY()) < 2)
            reachedDestination = true;*/
    }

    public void drawRoute(){

    }

    /*public void draw(Graphics g){

    }*/

    /*
    public int getCounter(){
        return counter;
    }
    public void incrementCounter(){
        ++counter;
    }*/

    public Route getRoute(){
        return route;
    }

}
