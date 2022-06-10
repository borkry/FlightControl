package com.company.z3;

import java.awt.*;

public class GroundObject extends Rectangle{
    protected MyRectangle collisionZone;
    private double heightOfGroundObject;

    public GroundObject(MyRectangle myRectangle, double heightOfGroundObject){
        this.collisionZone = myRectangle;
        this.heightOfGroundObject = heightOfGroundObject;
    }

    public double getHeightOfGroundObject() {
        return heightOfGroundObject;
    }

    public MyRectangle getCollisionZone(){
        return collisionZone;
    }

    public void draw(Graphics g){
        g.setColor(Color.black);
        g.drawRect((int)collisionZone.getLocation().getX(),(int)collisionZone.getLocation().getY(), (int)collisionZone.getWidth(), (int)collisionZone.getHeight());
    }

}
