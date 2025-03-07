package org.example.section16.pirategame;

import org.example.section16.game.Game;
import org.example.section16.game.GameAction;

import java.util.LinkedHashMap;
import java.util.Map;

public class PirateGame extends Game<Pirate> {
    public PirateGame(String gameName) {
        super(gameName);
    }

    @Override
    public Pirate createNewPlayer(String name) {
        return new Pirate(name);
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
                map = new LinkedHashMap<>(Map.of(
                        'A', new GameAction('A', "Choose town", this::chooseTown)
                ));
            }
            default -> {
                map = new LinkedHashMap<>(Map.of(
                        'W', new GameAction('W', "Choose your weapon", this::goToWeaponMenu),
                        'T', new GameAction('T', "Go to town", this::goToTown)
                ));
                map.putAll(getStandardActions());
            }
        }


        return map;
    }

    private boolean setCurrentWeapon(int playerIndex, char key) { return getPlayer(playerIndex).setCurrentWeapon(key);}


    //tmp onwards
    private boolean chooseTown(int playerIndex, char key) {
        return getPlayer(playerIndex).chooseTownToFight();
    }

    private boolean goToWeaponMenu(int playerIndex, char key) {
        return getPlayer(playerIndex).goToWeaponMenu();
    }

    private boolean goToTown(int playerIndex, char key) {
        return getPlayer(playerIndex).goToTown();
    }
}
