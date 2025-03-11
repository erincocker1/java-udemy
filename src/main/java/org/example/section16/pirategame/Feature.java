package org.example.section16.pirategame;

//'you came across a...'
public enum Feature {
    TRAP("Pirate Trap", -10),
    MEDKIT("Medkit", +10),
    SPIDERS("Horde of deadly spiders", -20)
    ;

    private final String name;
    private final int value;

    Feature(String name, int value) {
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
