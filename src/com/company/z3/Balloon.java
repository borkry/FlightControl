package com.company.z3;

import java.awt.*;

public class Balloon extends Airship{
    public Balloon(Route route, MyRectangle myRectangle){
        this.route = route;
        this.collisionZone = myRectangle;
        this.id = counter;
        ++counter; // następne przydzielone id bedzie o 1 wieksze
    }
}
