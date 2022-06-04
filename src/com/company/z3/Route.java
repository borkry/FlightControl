package com.company.z3;

import java.util.ArrayList;
import java.util.LinkedList;

public class Route {
    private ArrayList<Section> sections;    // moze zmienic na Linked List?

    public Route(ArrayList<Section> sections) {
        this.sections = sections;
    }

    public void addSection(Point destination, double velocity, double altitude) {
        Section lastSection = sections.get(sections.size()-1);
        int lastSectionDirection = lastSection.getDirection();
        sections.add(new Section(lastSection.getEnd(), destination, velocity, altitude, lastSectionDirection));
    }

    public void deleteSection() {
        if(sections.size()>0) {
            sections.remove(sections.size()-1);
        }
    }

//    public Section getCurrentSection() {
//        // dokonczyc chyba gdy sie doda samoloty?
//    }

}
