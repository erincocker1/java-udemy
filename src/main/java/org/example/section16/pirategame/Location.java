package org.example.section16.pirategame;

class Location {
    private final String name;
    private final int strength;
    private boolean beaten;

    public Location(String name, int strength) {
        this.name = name;
        this.strength = strength;
        this.beaten = false;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public int getStrength() {
        return strength;
    }

    public boolean isBeaten() {
        return beaten;
    }

    public void setBeaten(boolean beaten) {
        this.beaten = beaten;
    }
}
