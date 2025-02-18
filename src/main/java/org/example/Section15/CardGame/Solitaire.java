package org.example.Section15.CardGame;

import java.util.*;

public class Solitaire {
    private List<List<Card>> tableau = new ArrayList<>(7);
    private Deque<Card> stock = new ArrayDeque<>();
    //using arraydeque because java says this is preferred to the legacy class Stack
    private Deque<Card> wastePile = new ArrayDeque<>();
    private Deque<Card> hearts = new ArrayDeque<>();
    private Deque<Card> diamonds = new ArrayDeque<>();
    private Deque<Card> spades = new ArrayDeque<>();
    private Deque<Card> clubs = new ArrayDeque<>();

    public Solitaire() {
        setUpBoard();
        printBoard();
        System.out.println("Commands:");
        System.out.println("Enter 'stock' to show a new card from the stock or to refresh the pile");
        System.out.println("Enter 'quit' to quit");
        System.out.println("Enter e.g. '1c1 c4' to move 1 card from column 1 to column 4");
        System.out.println("Or enter e.g. 'waste hearts' to move the card from the wastepile to the hearts pile");
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("Enter your move: ");
            String userInput = scanner.nextLine().toLowerCase();
            if (userInput.equals("stock")) {
                stockClicked(); //either move top card from stock to waste, or reset stock if empty
            } else if (userInput.equals("quit")) {
                break;
            } else {
                System.out.println(userInput);
                System.out.println(Arrays.toString(userInput.split(" ")));
                moveCards(userInput.split(" ")[0], userInput.split(" ")[1]);
                // '3c1 c2' means move the top 3 cards on column 1 to column 2
                // 'waste c6' means move the card on the wastepile to column 6
                // '1c7 hearts' means move the top card on column 7 to the hearts pile. (must be 1)
            }
        }
    }

    private void moveCards(String source, String dest) {
        System.out.println("Moving cards");
    }


    private void stockClicked() {
        System.out.println("Updating stock pile");
    }

    private void setUpBoard() {
        for (int i = 0; i < 7; i++) {
            tableau.add(new ArrayList<>());
        }

        List<Card> deck = Card.getStandardDeck(false);
        Collections.shuffle(deck);
        for (int height = 0; height < 7; height++) {
            for (int column = height + 1; column < 7; column++) {
                tableau.get(column).add(deck.remove(0));
            }
            tableau.get(height).add(deck.remove(0).flip());
        }

        stock.addAll(deck);
    }

    public void printBoard() {
        System.out.printf("%5s%5s        %5s%5s%5s%5s\n",
                getTopCard(stock), getTopCard(wastePile), getTopCard(hearts),
                getTopCard(spades), getTopCard(clubs), getTopCard(diamonds));
        System.out.println();

        int maxHeight = 0;
        for (List<Card> column : tableau) {
            maxHeight = Math.max(column.size(), maxHeight);
        }

        for (int height = 0; height < maxHeight; height++) {
            for (int column = 0; column < 7; column++) {
                System.out.printf("%5s", height < tableau.get(column).size() ? tableau.get(column).get(height) : "");
            }
            System.out.println();
        }
    }

    private String getTopCard(Deque<Card> stack) {
        return stack.isEmpty() ? "[ ]" : stack.peek().toString();
    }

}
