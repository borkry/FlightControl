package com.company.z3;

public class Section extends Line{
    private double velocity;
    private double altitude;
    private int direction;

    public Section(Point beginning, Point end, double velocity, double altitude, int direction) {
        super(beginning, end);
        this.velocity = velocity;
        this.altitude = altitude;
        this.direction = direction;
    }

    public double getVelocity() {
        return velocity;
    }

    public double getAltitude() {
        return altitude;
    }

    public int getDirection() {
        return direction;
    }
}
