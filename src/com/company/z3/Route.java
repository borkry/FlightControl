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
        sections.add(new Section(lastSection.getEnd(), destination, velocity, altitude));
    }

    public void addSection(Section newSection) {
        sections.add(newSection);
    }

    public void deleteSection() {
        if(sections.size()>0) {
            sections.remove(sections.size()-1);
        }
    }

    public Section getCurrentSection() {
        return sections.get(0);
    }                   // zwraca aktualny odcinek

    public void moveToNextSection() {
        if(sections.size()>0)
            sections.remove();                  // kiedy samolot dotrze do celu ktoregos odcinka to wywoluje te metode aby przejsc do nastepnego (usuwamy z kolejki)
    }

    public LinkedList<Section> getSections() {
        return sections;
    }


}
