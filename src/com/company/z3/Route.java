package com.company.z3;

import java.util.ArrayList;
import java.util.LinkedList;

public class Route {
    private LinkedList<Section> sections;    // moze zmienic na Linked List?

    public Route(LinkedList<Section> sections) {
        this.sections = sections;
    }

    public void addSection(Point destination, double velocity, double altitude) {
        Section lastSection = sections.get(sections.size()-1);
        //int lastSectionDirection = lastSection.getDirection();
        sections.add(new Section(lastSection.getEnd(), destination, velocity, altitude));
    }

    public void deleteSection() {
        if(sections.size()>0) {
            sections.remove(sections.size()-1);
        }
    }

    public Section getCurrentSection() {
        return sections.get(0);
    }

    public void moveToNextSection() {
        if(sections.size()>0)
            sections.remove();
    }

    public LinkedList<Section> getSections() {
        return sections;
    }

//    public Section getCurrentSection() {
//        // dokonczyc chyba gdy sie doda samoloty?
//    }

}
