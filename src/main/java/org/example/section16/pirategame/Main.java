package org.example.section16.pirategame;

import org.example.section16.game.GameConsole;

public class Main {
    public static void main(String[] args) {
        GameConsole<AdventureGame> gameConsole =
                new GameConsole<>(new AdventureGame("Adventure Game"));
        gameConsole.addPlayer();
        gameConsole.playGame(0);
    }
}
