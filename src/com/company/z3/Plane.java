package com.company.z3;

import java.awt.*;

public class Plane extends Airship{
    public Plane(Route route, MyRectangle myRectangle){
        this.route = route;
        this.collisionZone = myRectangle;
    }

    public void draw(Graphics g){
        g.setColor(Color.black);
        g.drawRect((int)collisionZone.getLocation().getX(),(int)collisionZone.getLocation().getY(), (int)collisionZone.getHeight(), (int)collisionZone.getHeight());
    }
}
