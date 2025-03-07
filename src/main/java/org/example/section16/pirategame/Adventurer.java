package org.example.section16.pirategame;

import org.example.section16.game.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Adventurer implements Player {
    private String name;
    private int currentMenu;
    private Map<String, Integer> gameData; //mapStage, level
    private Weapon currentWeapon;

    public Adventurer(String name) {
        this.name = name;
        this.currentMenu = 0;
        gameData = new HashMap<>(Map.of(
                "mapStage", 0,
                "level", 1
        ));
        currentWeapon = Weapon.FIST;
    }

    boolean goToWeaponMenu() {
        currentMenu = 1;
        return false;
    }

    public List<Weapon> getWeaponsByLevel() {
        return Weapon.getWeaponsByLevel(gameData.get("level"));
    }

    boolean setCurrentWeapon(char letter) {
        this.currentWeapon = Weapon.getWeaponByChar(letter);
        currentMenu = 0;
        return false;
    }

    boolean goToLocationMenu() {
        currentMenu = 2;
        return false;
    }

    public List<Location> getPossibleLocations(List<Stage> gameMap) {
        List<Location> locations = new ArrayList<>();
        for (Location location : gameMap.get(gameData.get("mapStage")).locations()) {
            if (!location.isBeaten()) locations.add(location);
        }
        return locations;
    }

    boolean chooseLocationToFight(char letter, List<Stage> gameMap) {
        Location locationToFight = null;
        for (Location location : gameMap.get(gameData.get("mapStage")).locations()) {
            if (location.getName().charAt(0) == letter) {
                locationToFight = location;
                break;
            }
        }

        if (locationToFight == null) {
            System.out.println("Invalid location");
        } else {
            if (currentWeapon.getHitPoints() > locationToFight.getStrength()) {
                System.out.println("Location beaten!");
                locationToFight.setBeaten(true);
            } else {
                System.out.println("You're not strong enough... Come back when you're rested and stronger");
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

    @Override
    public String toString() {
        return "Adventurer %s is at level %d and wields a %s".formatted(name, gameData.get("level"), currentWeapon);
    }

}
