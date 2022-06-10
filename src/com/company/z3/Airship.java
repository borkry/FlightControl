package com.company.z3;

import java.awt.*;

public abstract class Airship extends Rectangle {
    protected Route route;
    protected MyRectangle collisionZone;
    protected static int counter = 1000; // wartości id beda zaczynaly sie od 1000
    protected int id;
    private boolean reachedDestination = false;
    private boolean isCollision = false;
    private boolean isCloseCollision = false;
    private boolean isCollisionWithGroundObject = false;


    public int getId() {
        return id;
    }

    public boolean getReachedDestination() {
        return reachedDestination;
    }

    public void setIsCollision(boolean isCollision) {
        this.isCollision = isCollision;
    }

    public void setIsCloseCollision(boolean isCloseCollision) {
        this.isCloseCollision = isCloseCollision;
    }

    public void modifyRoute(Route newRoute){
        this.route = newRoute;
        Point beginning = newRoute.getCurrentSection().beginning;
        collisionZone.setLocation(beginning);
    }

    // KOLIZJE

    public boolean ifCollision(Airship airship){
        double airshipX = airship.collisionZone.getLocation().getX();
        double airshipWidth = airship.collisionZone.getWidth();
        double airshipY = airship.collisionZone.getLocation().getY();
        double airshipHeight = airship.collisionZone.getHeight();

        double thisX = collisionZone.getLocation().getX();
        double thisY = collisionZone.getLocation().getY();
        double thisWidth = collisionZone.getWidth();
        double thisHeight = collisionZone.getHeight();

        double difference = Math.abs(route.getCurrentSection().getAltitude() - airship.route.getCurrentSection().getAltitude());    // sprawdzamy roznice wysokosci
        if(difference > 300) return false;

        if ((thisX + thisWidth >= airshipX && thisX + thisWidth <= airshipX + airshipWidth && thisY >= airshipY && thisY <= airshipY + airshipWidth)
            || (thisX <= airshipX + airshipWidth && thisX >= airshipX && thisY >= airshipY && thisY <= airshipY + airshipHeight)
                || (thisX + thisWidth >= airshipX && thisX + thisWidth <= airshipX + airshipWidth && thisY + thisHeight >= airshipY && thisY + thisHeight <= airshipY + airshipHeight)
                || (thisX <= airshipX + airshipWidth && thisX >= airshipX && thisY + thisHeight >= airshipY && thisY + thisHeight <= airshipY + airshipHeight)
        ) {
            return true;
        }
        return false;
    }


    public boolean ifCloseCollision(Airship airship) {
        double airshipX = airship.collisionZone.getLocation().getX();
        double airshipWidth = airship.collisionZone.getWidth()+70;
        double airshipY = airship.collisionZone.getLocation().getY()+70;
        double airshipHeight = airship.collisionZone.getHeight();

        double thisX = collisionZone.getLocation().getX();
        double thisY = collisionZone.getLocation().getY();
        double thisWidth = collisionZone.getWidth()+70;
        double thisHeight = collisionZone.getHeight()+70;

        double difference = Math.abs(route.getCurrentSection().getAltitude() - airship.route.getCurrentSection().getAltitude());    // sprawdzamy roznice wysokosci
        if(difference > 100) return false;

        if ((thisX + thisWidth >= airshipX && thisX + thisWidth <= airshipX + airshipWidth && thisY >= airshipY && thisY <= airshipY + airshipWidth)
                || (thisX <= airshipX + airshipWidth && thisX >= airshipX && thisY >= airshipY && thisY <= airshipY + airshipHeight)
                || (thisX + thisWidth >= airshipX && thisX + thisWidth <= airshipX + airshipWidth && thisY + thisHeight >= airshipY && thisY + thisHeight <= airshipY + airshipHeight)
                || (thisX <= airshipX + airshipWidth && thisX >= airshipX && thisY + thisHeight >= airshipY && thisY + thisHeight <= airshipY + airshipHeight)
        ) {
            return true;
        }
        return false;
    }


    public boolean ifCollision(GroundObject groundObject){
        double airshipX = groundObject.collisionZone.getLocation().getX();
        double airshipWidth = groundObject.collisionZone.getWidth();
        double airshipY = groundObject.collisionZone.getLocation().getY();
        double airshipHeight = groundObject.collisionZone.getHeight();

        double thisX = collisionZone.getLocation().getX();
        double thisY = collisionZone.getLocation().getY();
        double thisWidth = collisionZone.getWidth();
        double thisHeight = collisionZone.getHeight();

        double difference = Math.abs(route.getCurrentSection().getAltitude() - groundObject.getHeightOfGroundObject());     //sprawdzamy roznice wysokosci
        if(difference > 300) return false;
        if ((thisX + thisWidth >= airshipX && thisX + thisWidth <= airshipX + airshipWidth && thisY >= airshipY && thisY <= airshipY + airshipWidth)
                || (thisX <= airshipX + airshipWidth && thisX >= airshipX && thisY >= airshipY && thisY <= airshipY + airshipHeight)
                || (thisX + thisWidth >= airshipX && thisX + thisWidth <= airshipX + airshipWidth && thisY + thisHeight >= airshipY && thisY + thisHeight <= airshipY + airshipHeight)
                || (thisX <= airshipX + airshipWidth && thisX >= airshipX && thisY + thisHeight >= airshipY && thisY + thisHeight <= airshipY + airshipHeight)
        ) {
            return true;
        }

        return false;
    }

    public boolean ifCloseCollision(GroundObject groundObject){
        double airshipX = groundObject.collisionZone.getLocation().getX();
        double airshipWidth = groundObject.collisionZone.getWidth()+70;
        double airshipY = groundObject.collisionZone.getLocation().getY()+70;
        double airshipHeight = groundObject.collisionZone.getHeight();

        double thisX = collisionZone.getLocation().getX();
        double thisY = collisionZone.getLocation().getY();
        double thisWidth = collisionZone.getWidth()+70;
        double thisHeight = collisionZone.getHeight()+70;

        double difference = Math.abs(route.getCurrentSection().getAltitude() - groundObject.getHeightOfGroundObject());     //sprawdzamy roznice wysokosci
        if(difference > 100) return false;
        if ((thisX + thisWidth >= airshipX && thisX + thisWidth <= airshipX + airshipWidth && thisY >= airshipY && thisY <= airshipY + airshipWidth)
                || (thisX <= airshipX + airshipWidth && thisX >= airshipX && thisY >= airshipY && thisY <= airshipY + airshipHeight)
                || (thisX + thisWidth >= airshipX && thisX + thisWidth <= airshipX + airshipWidth && thisY + thisHeight >= airshipY && thisY + thisHeight <= airshipY + airshipHeight)
                || (thisX <= airshipX + airshipWidth && thisX >= airshipX && thisY + thisHeight >= airshipY && thisY + thisHeight <= airshipY + airshipHeight)
        ) {
            return true;
        }

        return false;
    }

    public void move(Section section){
        double speed = section.getVelocity()/300;       // ogólny mnożnik prędkości
        double deltaX = section.getEnd().getX() - collisionZone.getLocation().getX();
        double deltaY = section.getEnd().getY() - collisionZone.getLocation().getY();
        double angle = Math.atan2(deltaY, deltaX);

        double moveX = speed * Math.cos(angle);
        double moveY = speed * Math.sin(angle);

        if(Math.sqrt(Math.pow((route.getCurrentSection().end.getX() - collisionZone.getLocation().getX()), 2) + Math.pow((route.getCurrentSection().end.getY() - collisionZone.getLocation().getY()), 2)) < Math.sqrt(Math.pow((collisionZone.getLocation().getX()+moveX - collisionZone.getLocation().getX()), 2) + Math.pow((collisionZone.getLocation().getY()+moveY- collisionZone.getLocation().getY()), 2) )) {
            collisionZone.getLocation().setX(route.getCurrentSection().end.getX());
            collisionZone.getLocation().setY(route.getCurrentSection().end.getY());         // jezeli przesuniecie ma byc wieksze niz odleglosc do punktu docelowego
            if(route.getSections().size()>1)                                                // to ustawiamy wspolrzedne statku powietrznego na punkt docelowy
                route.moveToNextSection();
            else
                reachedDestination = true;
        }
        else
            collisionZone.moveRectangle(speed*Math.cos(angle), speed*Math.sin(angle));      // poruszarnie sie statku powietrznego

    }

    public void drawRoute(){

    }

    public void draw(Graphics g){
        if(isCollision) {
            g.setColor(Color.red);
            g.fillRect((int)collisionZone.getLocation().getX(),(int)collisionZone.getLocation().getY(), (int)collisionZone.getHeight(), (int)collisionZone.getHeight());    //wypelnienie kolorem czerwonym
            g.setColor(Color.black);
            g.drawString(Integer.toString(id), (int) collisionZone.getLocation().getX()-1, (int) collisionZone.getLocation().getY()-1);
            isCollision = false;
            return;
        }                                                                                                                                                // rozny kolor w zaleznosci od rodzaju kolizji
        else if (isCloseCollision) {
            g.setColor(Color.orange);
            g.fillRect((int)collisionZone.getLocation().getX(),(int)collisionZone.getLocation().getY(), (int)collisionZone.getHeight(), (int)collisionZone.getHeight());
            g.setColor(Color.black);
            g.drawString(Integer.toString(id), (int) collisionZone.getLocation().getX()-1, (int) collisionZone.getLocation().getY()-1);
            isCloseCollision = false;
            return;
        }
        else if (isCollisionWithGroundObject) {
            g.setColor(Color.red);
            g.fillRect((int)collisionZone.getLocation().getX(),(int)collisionZone.getLocation().getY(), (int)collisionZone.getHeight(), (int)collisionZone.getHeight());
            g.setColor(Color.black);
            g.drawString(Integer.toString(id), (int) collisionZone.getLocation().getX()-1, (int) collisionZone.getLocation().getY()-1);
            isCollisionWithGroundObject = false;
            return;
        }


        if(this instanceof Plane)                   // rozny kolor reprezentacji na radarze dla typow statkow powietrznych
            g.setColor(Color.black);
        else if(this instanceof Balloon)
            g.setColor(Color.blue);
        else if(this instanceof Helicopter)
            g.setColor(Color.green);
        else if(this instanceof Glider)
            g.setColor(Color.magenta);

        g.drawRect((int)collisionZone.getLocation().getX(),(int)collisionZone.getLocation().getY(), (int)collisionZone.getHeight(), (int)collisionZone.getHeight());    // rysowanie prostokata
        g.setColor(Color.black);
        g.drawString(Integer.toString(id), (int) collisionZone.getLocation().getX()-1, (int) collisionZone.getLocation().getY()-1); // wyswietlanie id nad statkiem na radarze
        //g.drawString(String.valueOf((int)(route.getCurrentSection().getAltitude())),(int) collisionZone.getLocation().getX()+25, (int) collisionZone.getLocation().getY()+10 ); // tutaj mozna wlaczyc pokazywanie wysokosci
    }


    public Route getRoute(){
        return route;
    }

}
