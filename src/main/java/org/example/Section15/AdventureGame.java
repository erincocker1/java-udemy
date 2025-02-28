package org.example.Section15;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AdventureGame {
    private final String MAPDATA = """
            road,at the end of the road,W: hill,E:well house,S:valley,N:forest
            hill,on top of hill with a view in all directions,N:forest,E:road
            well house,inside a well house for a small spring,W:road,N:lake,S:stream
            valley,in a forest valley beside a tumbling stream,N:road,W:hill,E:stream
            forest,at the edge of a thick dark forest,S:road,E:lake
            lake,by an alpine lake surrounded by wildflowers,W:forest,S:well house
            stream,near a stream with a rocky bed,W:valley,N:well house""";

    Map<String, Location> map;
    String location;

    public AdventureGame() {
        this.map = getMapFromData(MAPDATA);
        this.location = "road";
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        System.out.println("Welcome to the adventure game! " +
                "Enter the compass direction you want to follow or enter Q to quit");
        System.out.println();

        while (true) {
            System.out.println(map.get(location));
            System.out.println();
            System.out.print("> ");
            userInput = scanner.nextLine().toUpperCase();

            if (userInput.equals("Q")) break;

            if (!map.get(location).nextPlaces.containsKey(Direction.valueOf(userInput))) {
                System.out.println("You can't go that way");
                System.out.println();
                continue;
            }

            this.location = map.get(location).nextPlaces.get(Direction.valueOf(userInput));
            System.out.println();
        }
    }

    private Map<String, Location> getMapFromData(String data) {
        Map<String, Location> map = new HashMap<>();
        Scanner scanner = new Scanner(data);

        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(",");

            Map<Direction, String> nextPlaces = new HashMap<>();
            for (int i = 2; i < line.length; i++) {
                String[] place = line[i].split(":");
                nextPlaces.put(Direction.valueOf(place[0]), place[1]);
            }

            map.put(line[0], new Location(line[1], nextPlaces));
        }
        return map;
    }
}

enum Direction {
    N {
        @Override
        public String toString() {
            return "North";
        }
    },
    S {
        @Override
        public String toString() {
            return "South";
        }
    },
    E {
        @Override
        public String toString() {
            return "East";
        }
    },
    W {
        @Override
        public String toString() {
            return "West";
        }
    }
};

class Location {
    String description;
    Map<Direction, String> nextPlaces;

    public Location(String description, Map<Direction, String> nextPlaces) {
        this.description = description;
        this.nextPlaces = nextPlaces;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("You are standing " + description + ".\n");
        for (Map.Entry<Direction,String> place: nextPlaces.entrySet()) {
            string.append("To the %s is the %s\n".formatted(place.getKey(), place.getValue()));
        }

        return string.toString();
    }
}


