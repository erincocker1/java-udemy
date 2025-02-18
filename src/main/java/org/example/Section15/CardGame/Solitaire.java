package org.example.Section15.CardGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Solitaire {
    private List<List<Card>> tableau;
    private Stack<Card> stock;
    private Stack<Card> wastePile;
    private Stack<Card> hearts;
    private Stack<Card> diamonds;
    private Stack<Card> spades;
    private Stack<Card> clubs;

    public Solitaire() {
        tableau = new ArrayList<>(7);
        stock = new Stack<>();
        wastePile = new Stack<>();
        hearts = new Stack<>();
        diamonds = new Stack<>();
        spades = new Stack<>();
        clubs = new Stack<>();

        SetUpBoard();
    }

    private void SetUpBoard() {
        for (int i = 0; i < 7; i++) {
            tableau.add(new ArrayList<>());
        }

        List<Card> deck = Card.getStandardDeck(false);
        Collections.shuffle(deck);
        for (int height = 0; height < 7; height++) {
            for (int pile = height+1; pile < 7; pile++) {
                tableau.get(pile).add(deck.remove(0));
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
        for (List<Card> pile : tableau) {
            maxHeight = Math.max(pile.size(), maxHeight);
        }

        for (int height = 0; height < maxHeight; height++) {
            for (int pile = 0; pile < 7; pile++) {
                System.out.printf("%5s", height < tableau.get(pile).size() ? tableau.get(pile).get(height) : "");
            }
            System.out.println();
        }
    }

    private String getTopCard(Stack<Card> stack) {
        return stack.isEmpty() ? "[ ]" : stack.peek().toString();
    }

}
