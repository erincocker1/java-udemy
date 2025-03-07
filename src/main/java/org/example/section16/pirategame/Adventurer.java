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
    private List<Location> visitedTowns = new ArrayList<>();

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

    boolean setCurrentWeapon(char letter) {
        this.currentWeapon = Weapon.getWeaponByChar(letter);
        currentMenu = 0;
        return false;
    }

    public List<Weapon> getWeaponsByLevel() {
        return Weapon.getWeaponsByLevel(gameData.get("level"));
    }

    boolean goToLocation() {
        System.out.println("tbd: print the towns available and you choose which one to fight.");
        currentMenu = 2;
        return false;
    }

    boolean chooseLocationToFight() {
        System.out.println("tbd: find out if you've won or lost. take this town off the menu");
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
        return "Pirate %s is at level %d and wields a %s".formatted(name, gameData.get("level"), currentWeapon);
    }
}
