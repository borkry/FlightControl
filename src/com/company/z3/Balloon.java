package com.company.z3;

import java.awt.*;

public class Balloon extends Airship{
    public Balloon(Route route, MyRectangle myRectangle){
        this.route = route;
        this.collisionZone = myRectangle;
        this.id = counter;
        ++counter; // następne przydzielone id bedzie o 1 wieksze
    }

//    public void draw(Graphics g){
//        g.setColor(Color.blue);
//        g.drawRect((int)collisionZone.getLocation().getX(),(int)collisionZone.getLocation().getY(), (int)collisionZone.getHeight(), (int)collisionZone.getHeight());
//    }
}
