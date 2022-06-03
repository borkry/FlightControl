package com.company.z3;

import java.util.ArrayList;
import java.util.LinkedList;

public class Radar {
    private ArrayList<Airship> airships;
    private ArrayList<GroundObject> groundObjects;

    public Radar(){

    }

    public void loadMap(String fileName){

    }

    public void run(int frequency){

    }

    public void addAirship(Plane plane){
        airships.add(plane);
    }
    public void addAirship(Helicopter helicopter){
        airships.add(helicopter);
    }
    public void addAirship(Glider glider){
        airships.add(glider);
    }
    public void addAirship(Balloon balloon){
        airships.add(balloon);
    }

    public void deleteAirship(int airshipId){
        if(airships.size()>0) {
            airships.remove(airships.size()-1);
        }
    }

    public void drawAirship(int amount){

    }

    public void modifyRoute(int airshipId, Route newRoute){ //chyba trzeba przeszukać listę samolotów i ten, którego id=airshipId zmieniamy trase

    }

    public void checkCollision(){

    }

    public void move(){

    }

    /*public void draw(Graphics g){

    }*/
}
