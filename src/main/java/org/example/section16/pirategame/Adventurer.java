package org.example.section16.pirategame;

import org.example.section16.game.Player;

import java.util.ArrayList;
import java.util.List;

public class Adventurer implements Player {
    private String name;
    private int health;
    private int score;
    private int currentMenu;
    private int mapStage;
    private int level;
    private Weapon currentWeapon;

    public Adventurer(String name) {
        this.name = name;
        health = 100;
        score = 0;
        currentMenu = 0;
        mapStage = 0;
        level = 1;
        currentWeapon = Weapon.FIST;
    }

    boolean goToWeaponMenu() {
        currentMenu = 1;
        return false;
    }

    public List<Weapon> getWeaponsByLevel() {
        return Weapon.getWeaponsByLevel(level);
    }

    boolean setCurrentWeapon(char letter) {
        this.currentWeapon = Weapon.getWeaponByChar(letter);
        currentMenu = 0;
        return false;
    }

    boolean goToLocationMenu(List<Stage> gameMap) {
        currentMenu = 2;
        System.out.println("Going to the " + gameMap.get(mapStage).name());
        return false;
    }

    public List<Location> getPossibleLocations(List<Stage> gameMap) {
        List<Location> locations = new ArrayList<>();
        for (Location location : gameMap.get(mapStage).locations()) {
            if (!location.isBeaten()) locations.add(location);
        }
        return locations;
    }

    boolean chooseLocationToFight(char letter, List<Stage> gameMap) {
        Location locationToFight = null;
        for (Location location : gameMap.get(mapStage).locations()) {
            if (location.getName().charAt(0) == letter) {
                locationToFight = location;
                break;
            }
        }

        if (locationToFight == null) {
            System.out.println("Invalid location");
        } else {
            for (Loot loot: locationToFight.getLoot()) {
                System.out.println("You found a " + loot.getName() + "! (+" + loot.getValue() + " to score)");
                score += loot.getValue();
            }

            for (Feature feature: locationToFight.getFeatures()) {
                System.out.println("You came across a " + feature.getName() + "! (" +
                        (feature.getValue() > 0 ? "+" : "") + feature.getValue() + " to health)");
                health += feature.getValue();
            }

            if (currentWeapon.getHitPoints() > locationToFight.getStrength()) {
                System.out.println("Location beaten!");
                locationToFight.setBeaten(true);
                score += 1;
                level++;

                if (getPossibleLocations(gameMap).size() == 0) mapStage++;

            } else {
                System.out.println("You're not strong enough... Come back when you're rested and stronger");
                health -= 20;
            }
        }

        currentMenu = 0;
        return false;
    }


    @Override
    public String name() {
        return name;
    }

    public int getCurrentMenu() {
        return currentMenu;
    }

    public int getMapStage() {
        return mapStage;
    }

    @Override
    public String toString() {
        return """
                Adventurer %s:
                Level %d
                HP %d/100
                Score %d
                Wielding a %s""".formatted(name, level, health, score, currentWeapon);
    }

}
