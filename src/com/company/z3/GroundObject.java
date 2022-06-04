package com.company.z3;

public class GroundObject {
    protected MyRectangle collisionZone;

    public GroundObject(MyRectangle myRectangle){
        this.collisionZone = myRectangle;
    }

    public MyRectangle getCollisionZone(){
        return collisionZone;
    }

    /*public void draw(Graphics g){

    }*/

}
