package com.company.z3;

public class MyRectangle {
    private Point location;
    private double width;
    private double height;

    public MyRectangle(Point location, double width, double height) {
        location.movePoint(-(width/2),-(height/2));
        this.location = new Point(location);
        this.width = width;
        this.height = height;
    }

    public Point getLocation() {
        return location;
    }
    public void setLocation(Point location) {this.location = location;}

    public double getHeight() {
        return height;
    }
    public double getWidth() {return width;}

    public void moveRectangle(double x, double y) {
        location.movePoint(x, y);
    }
}
