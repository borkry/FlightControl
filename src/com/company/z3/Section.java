package com.company.z3;

public class Section extends Line{
    private double velocity;
    private double altitude;
    private double direction;

    public Section(Point beginning, Point end, double velocity, double altitude, double direction) {
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

    public double getDirection() {
        return direction;
    }
}
