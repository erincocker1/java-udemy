package org.example.section16.game;

import java.util.Scanner;

public class GameConsole<T extends Game<? extends Player>> {

    private final T game;
    private static final Scanner scanner = new Scanner(System.in);

    public GameConsole(T game) {
        this.game = game;
    }

    public int addPlayer() {

        System.out.print("Enter your playing name: ");
        String name = scanner.nextLine();

        System.out.printf("Welcome to %s, %s!%n".formatted(game.getGameName(), name));
        return game.addPlayer(name);
    }

    public void playGame(int playerIndex) {

        boolean done = false;
        while (!done) {
            var gameActions = game.getGameActions(playerIndex);
            System.out.println("Select from one of the following Actions: ");
            for (Character c : gameActions.keySet()) {
                String prompt = gameActions.get(c).prompt();
                System.out.println("\t" + prompt + " (" + c + ")");
            }
            System.out.print("Enter Next Action: ");
            String userInput = scanner.nextLine();

            System.out.println("-------------------------------------------");

            if (userInput.length() == 0) {
                System.out.println("-------------------------------------------");
                continue;
            }

            char nextMove = userInput.toUpperCase().charAt(0);

            GameAction gameAction = gameActions.get(nextMove);

            if (gameAction != null) {
                done = game.executeGameAction(playerIndex, gameAction);
                if (!done) {
                    System.out.println("-------------------------------------------");
                }
            }
        }
    }
}
