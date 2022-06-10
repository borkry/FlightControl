package com.company.z3;

public class Point {
    private double x;
    private double y;
    
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point p) {
        x = p.x;
        y = p.y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double xx){this.x = xx; }
    public void setY(double yy){this.y = yy; }

    public void movePoint(double x, double y) {
        this.x += x;
        this.y += y;
    }
}
