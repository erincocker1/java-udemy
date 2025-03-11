package org.example.section16.pirategame;

import java.util.ArrayList;
import java.util.List;

public enum Weapon {
    FIST(1, 1),
    PISTOL(2, 1),
    SHOTGUN(3, 3),
    GRENADE(5, 4),
    RPG(6, 5);

    private final int hitPoints;
    private final int minLevel;

    Weapon(int hitPoints, int minLevel) {
        this.hitPoints = hitPoints;
        this.minLevel = minLevel;
    }

    public static Weapon getWeaponByChar(char firstInitial) {
        for (Weapon weapon : values()) {
            if (weapon.name().charAt(0) == firstInitial) return weapon;
        }
        return values()[0];
    }

    public static List<Weapon> getWeaponsByLevel(int userLevel) {
        List<Weapon> validWeapons = new ArrayList<>();
        for (Weapon weapon : values()) {
            if (userLevel >= weapon.minLevel) validWeapons.add(weapon);
        }
        return validWeapons;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getMinLevel() {
        return minLevel;
    }
}
