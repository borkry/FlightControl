package com.company.z3;

public class Plane extends Airship{
    public Plane(Route route, Rectangle rectangle){
        this.route = route;
        this.collisionZone = rectangle;
    }

    /*public void draw(Graphics g){

    }*/
}
