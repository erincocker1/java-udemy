package org.example.section16.pirategame;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Location {
    private final String name;
    private final int strength;
    private boolean beaten;
    private final List<Loot> loot;
    private final List<Feature> features;

    public Location(String name, int strength) {
        this.name = name;
        this.strength = strength;
        this.beaten = false;
        this.loot = generateLoot();
        this.features = generateFeatures();

    }

    private List<Loot> generateLoot() {
        List<Loot> loot = new ArrayList<>();
        Random random = new Random();
        int amountOfLoot = random.nextInt(2);
        if (amountOfLoot == 1) {
            loot.add(Loot.values()[random.nextInt(Loot.values().length)]);
        }
        return loot;
    }

    private List<Feature> generateFeatures() {
        List<Feature> features = new ArrayList<>();
        Random random = new Random();
        int numOfFeatures = random.nextInt(2);
        if (numOfFeatures == 1) {
            features.add(Feature.values()[random.nextInt(Feature.values().length)]);
        }
        return features;
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

    public List<Loot> getLoot() {
        return List.copyOf(loot);
    }

    public List<Feature> getFeatures() {
        return List.copyOf(features);
    }
}
