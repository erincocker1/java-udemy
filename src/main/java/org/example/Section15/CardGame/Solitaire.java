package org.example.Section15.CardGame;

import java.util.*;

public class Solitaire {
    List<List<Card>> tableau = new ArrayList<>(7);
    private Deque<Card> stock = new ArrayDeque<>();
    //using arraydeque because java says this is preferred to the legacy class Stack
    Deque<Card> wastePile = new ArrayDeque<>();
    Deque<Card> hearts = new ArrayDeque<>();
    Deque<Card> diamonds = new ArrayDeque<>();
    Deque<Card> spades = new ArrayDeque<>();
    Deque<Card> clubs = new ArrayDeque<>();

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
            System.out.print("Enter your move: ");
            String userInput = scanner.nextLine().toLowerCase();
            System.out.println();
            if (userInput.equals("stock")) {
                stockClicked(); //either move top card from stock to waste, or reset stock if empty
            } else if (userInput.equals("quit")) {
                break;
            } else {
                MoveCards.moveCards(this, userInput.toLowerCase());
                // '3c1 c2' means move the top 3 cards on column 1 to column 2
                // 'waste c6' means move the card on the wastepile to column 6
                // '1c7 hearts' means move the top card on column 7 to the hearts pile. (must be 1)
            }

            printBoard();
        }
    }


    private void stockClicked() {
        if (stock.size() == 0) {
            while (wastePile.size() != 0) {
                stock.push(wastePile.pop().flip());
            }
        } else {
            wastePile.push(stock.pop().flip());
        }
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
        System.out.printf("%9s%9s      %9s%9s%9s%9s\n",
                "Stock", "Waste", "Hearts", "Spades", "Clubs", "Diamonds");
        System.out.printf("%9s%9s      %9s%9s%9s%9s\n",
                getTopCard(stock), getTopCard(wastePile), getTopCard(hearts),
                getTopCard(spades), getTopCard(clubs), getTopCard(diamonds));
        System.out.println();

        int maxHeight = 0;
        for (List<Card> column : tableau) {
            maxHeight = Math.max(column.size(), maxHeight);
        }

        System.out.println("              1     2     3     4     5     6     7");
        for (int height = 0; height < maxHeight; height++) {
            System.out.print("          ");
            for (int column = 0; column < 7; column++) {
                System.out.printf("%6s", height < tableau.get(column).size() ? tableau.get(column).get(height) : "");
            }
            System.out.println();
        }
    }

    private String getTopCard(Deque<Card> stack) {
        return stack.isEmpty() ? "[ ]" : stack.peek().toString();
    }

}
