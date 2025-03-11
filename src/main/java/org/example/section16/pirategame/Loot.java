package org.example.section16.pirategame;

public enum Loot {
    DOUBLOON("Spanish Doubloon", 2),
    DAGGER("Tibetan Ritual Dagger", 4),
    WHEEL("Cipher Wheel", 2),
    CROSS("St. Dismas' Cross", 1)
    ;

    private final String name;
    private final int value;

    Loot(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
