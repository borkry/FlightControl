package com.company.z3;

public class Point {
    private double x;
    private double y;
    
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point p) { //dodane względem diagramu, ponieważ nie dało się stworzyć linii
        x = p.x;
        y = p.y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void movePoint(double x, double y) {
        this.x += x;
        this.y += y;
    }
}
