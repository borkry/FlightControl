package com.company.z3;

public class Line {
    public Point beginning;
    public Point end;

    public Line(Point beginning, Point end) {
        this.beginning = new Point(beginning);
        this.end = new Point(end);
    }

    public Point getBeginning() {
        return beginning;
    }

    public Point getEnd() {
        return end;
    }
}
