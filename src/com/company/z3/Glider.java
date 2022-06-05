package com.company.z3;

public class Glider extends Airship{
    public Glider(Route route, MyRectangle myRectangle){
        this.route = route;
        this.collisionZone = myRectangle;
        this.id = counter;
        ++counter; // następne przydzielone id bedzie o 1 wieksze
    }

    /*public void draw(Graphics g){

    }*/
}
