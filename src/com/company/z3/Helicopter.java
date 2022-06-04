package com.company.z3;

public class Helicopter extends Airship{
    public Helicopter(Route route, MyRectangle myRectangle){
        this.route = route;
        this.collisionZone = myRectangle;
    }

    /*public void draw(Graphics g){

    }*/
}
