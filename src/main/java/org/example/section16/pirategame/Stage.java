package org.example.section16.pirategame;

import java.util.List;

public record Stage(String name, List<Location> locations) {
    @Override
    public String toString() {
        return name;
    }

    void addLocation(String name, int strength) {
        locations.add(new Location(name, strength));
    }
}
