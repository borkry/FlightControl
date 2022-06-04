package com.company.z3;

public abstract class Airship {
    protected Route route;
    protected Rectangle collisionZone;
    private static int id;

    public int getId() {
        return id;
    }

    public void modifyRoute(Route newRoute){
        this.route = newRoute;
    }

    public boolean ifCollision(Airship airship){
        return false;
    }
    public boolean ifCollision(GroundObject groundObject){
        return false;
    }

    public void move(){

    }

    public void drawRoute(){

    }

    /*public void draw(Graphics g){

    }*/


}
