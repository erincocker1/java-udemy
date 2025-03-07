package org.example.section16.pirategame;

import org.example.section16.game.Game;
import org.example.section16.game.GameAction;

import java.util.*;

public class AdventureGame extends Game<Adventurer> {
    List<Stage> gameMap;

    String gameMapData = """
            Stage: Pacific Ocean
            Sailboat, 1
            Stage: Amazon Rainforest
            Abandoned U-boat, 1
            Ruins, 2
            Stage: Tropical Island
            Ruined Fortress, 3
            Flooded City, 4
            Monastery, 5""";

    public AdventureGame(String gameName) {
        super(gameName);
        gameMap = loadData(gameMapData);
    }

    private List<Stage> loadData(String gameMapData) {
        List<Stage> gameMap = new ArrayList<>();
        Scanner scanner = new Scanner(gameMapData);
        int stageNumber = -1;
        String stageName = "";


        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.startsWith("Stage: ")) {
                stageName = line.substring(7);
                stageNumber++;
                gameMap.add(new Stage(stageName, new ArrayList<>()));
            } else {
                gameMap.get(stageNumber).addLocation(line.split(",")[0].trim(),
                        Integer.parseInt(line.split(",")[1].trim()));
            }
        }

        return gameMap;
    }

    @Override
    public Adventurer createNewPlayer(String name) {
        return new Adventurer(name);
    }

    @Override
    public Map<Character, GameAction> getGameActions(int playerIndex) {
        //either return main menu: see weapons, choose weapon, or go to town
        //or submenu: choose weapon (axe knife machete), or fight in town (from list of towns in level)
        int currentMenu = getPlayer(playerIndex).getCurrentMenu();
        Map<Character, GameAction> map;

        switch (currentMenu) {

            case 1 -> {
                map = new LinkedHashMap<>();
                for (Weapon weapon : getPlayer(playerIndex).getWeaponsByLevel()) {
                    map.put(weapon.name().charAt(0), new GameAction(weapon.name().charAt(0), "Choose " + weapon, this::setCurrentWeapon));
                }
            }
            case 2 -> {
                map = new LinkedHashMap<>();
                for (Location location : getPlayer(playerIndex).getPossibleLocations(gameMap)) {
                    map.put(location.getName().charAt(0), new GameAction(location.getName().charAt(0), "Visit the " + location, this::chooseLocation));
                }
            }
            default -> {
                map = new LinkedHashMap<>(Map.of(
                        'W', new GameAction('W', "Choose your weapon", this::goToWeaponMenu),
                        'T', new GameAction('T', "Go to location", this::goToLocationMenu)
                ));
                map.putAll(getStandardActions());
            }
        }


        return map;
    }

    private boolean setCurrentWeapon(int playerIndex, char key) { return getPlayer(playerIndex).setCurrentWeapon(key);}

    private boolean chooseLocation(int playerIndex, char key) {
        return getPlayer(playerIndex).chooseLocationToFight(key, gameMap);
    }

    private boolean goToWeaponMenu(int playerIndex, char key) {
        return getPlayer(playerIndex).goToWeaponMenu();
    }

    private boolean goToLocationMenu(int playerIndex, char key) {
        return getPlayer(playerIndex).goToLocationMenu();
    }
}
