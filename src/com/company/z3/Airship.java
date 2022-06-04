package com.company.z3;

import java.awt.*;

public abstract class Airship extends Rectangle {
    protected Route route;
    protected MyRectangle collisionZone;
    private static int id;

    public int getId() {
        return id;
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

    public void move(){
        collisionZone.moveRectangle(1,1); //"droga po ktorej sie porusza" - tutaj chyba trzeba dac parametry zeby dla kazdego obiektu mozna bylo dac inna droge, cos jakby funkcje linipwa chyba
    }

    public void drawRoute(){

    }

    /*public void draw(Graphics g){

    }*/


}
