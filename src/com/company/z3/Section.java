package com.company.z3;

public class Section extends Line{
    private double velocity;
    private double altitude;

    public Section(Point beginning, Point end, double velocity, double altitude) {
        super(beginning, end);
        this.velocity = velocity;
        this.altitude = altitude;
    }

    public double getVelocity() {
        return velocity;
    }

    public double getAltitude() {
        return altitude;
    }
}
