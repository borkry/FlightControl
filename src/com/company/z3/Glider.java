package com.company.z3;

public class Glider extends Airship{
    public Glider(Route route, MyRectangle myRectangle){
        this.route = route;
        this.collisionZone = myRectangle;
    }

    /*public void draw(Graphics g){

    }*/
}
