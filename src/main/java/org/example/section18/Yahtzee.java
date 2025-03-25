package org.example.section18;

import java.time.format.DateTimeFormatter;
import java.util.*;

//Section 18 Lecture 271
public class Yahtzee {
    private List<Integer> dice = new ArrayList<>();
    private final Random random = new Random();
    private final Scanner scanner = new Scanner(System.in);

    public void play() {
        dice.addAll(rollDice(6));
        while (true) {
            System.out.println("Your dice are " + dice);
            System.out.println("Press Enter to Score");
            System.out.println("Type \"ALL\" to re-roll all the dice");
            System.out.println("List numbers (separated by spaces) to re-roll the selected dice");
            System.out.print("--> ");
            String userInput = scanner.nextLine();

            if (userInput.equals("")) {
                break;
            } else if (userInput.equals("ALL")) {
                dice = rollDice(6);
            } else {
                rollSomeDice(userInput.trim().split(" "));
            }
        }
        Collections.sort(dice);
        System.out.println("Thanks for playing, your final dice are " + dice);
    }

    private List<Integer> rollDice(int amount) {
        return random.ints(amount, 1, 7).sorted().boxed().toList();
    }

    private void rollSomeDice(String[] userInput) {
        Set<Integer> diceIndicesToReRoll = new HashSet<>();
        for (String num : userInput) {
            if (num.matches("^[1-6]$")) {
                diceIndicesToReRoll.add(Integer.parseInt(num));
            }
        }
        List<Integer> diceIndicesToReRollList = new ArrayList<>(diceIndicesToReRoll);
        diceIndicesToReRollList.sort(Collections.reverseOrder());
        diceIndicesToReRollList.forEach(i -> dice.remove((i - 1)));

        System.out.println("Keeping " + dice);
        dice.addAll(rollDice(6 - dice.size()));
    }
}
