package com.company.z3;

public class GroundObject {
    protected Rectangle collisionZone;

    public GroundObject(Rectangle rectangle){
        this.collisionZone = rectangle;
    }

    public Rectangle getCollisionZone(){
        return collisionZone;
    }

    /*public void draw(Graphics g){

    }*/

}
