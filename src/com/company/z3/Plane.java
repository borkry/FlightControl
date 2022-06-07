package com.company.z3;

import java.awt.*;

public class Plane extends Airship{
    public Plane(Route route, MyRectangle myRectangle){
        this.route = route;
        this.collisionZone = myRectangle;
        this.id = counter;
        ++counter; // następne przydzielone id bedzie o 1 wieksze
    }

    public void draw(Graphics g){
        g.setColor(Color.black);
        g.drawString(Integer.toString(id), (int) collisionZone.getLocation().getX()-1, (int) collisionZone.getLocation().getY()-1); // wyswietlanie id nad statkiem na radarze
        g.drawRect((int)collisionZone.getLocation().getX(),(int)collisionZone.getLocation().getY(), (int)collisionZone.getHeight(), (int)collisionZone.getHeight());
    }


}
