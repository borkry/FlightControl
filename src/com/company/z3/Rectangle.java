package com.company.z3;

public class Rectangle {
    private Point location;
    private double width;
    private double height;

    public Rectangle(Point location, double width, double height) {
        this.location = new Point(location);
        this.width = width;
        this.height = height;
    }

    public Point getLocation() {
        return location;
    }

    public double getHeight() {
        return height;
    }

    public void moveRectangle(double x, double y) {
        location.przesunPunkt(x, y);
    }
}
