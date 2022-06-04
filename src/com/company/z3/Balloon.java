package com.company.z3;

public class Balloon extends Airship{
    public Balloon(Route route, MyRectangle myRectangle){
        this.route = route;
        this.collisionZone = myRectangle;
    }

    /*public void draw(Graphics g){

    }*/
}
