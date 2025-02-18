package org.example.Section15.CardGame;

import java.util.ArrayList;
import java.util.List;

public class Card {


    public enum Suit {
        CLUB, DIAMOND, HEART, SPADE;

        public char getImage() {
            return (new char[]{9827, 9830, 9829, 9824})[this.ordinal()];
        }
    }

    private final Suit suit;
    private final String face;
    private final int rank;
    private boolean faceUp;

    private Card(Suit suit, String face, int rank, boolean faceUp) {
        this.suit = suit;
        this.face = face;
        this.rank = rank;
        this.faceUp = faceUp;
    }

    @Override
    public String toString() {
        if (!faceUp) {
            return "[-]";
        }
        int index = face.equals("10") ? 2 : 1;
        String faceString = face.substring(0, index);
        return "%s%c".formatted(faceString, suit.getImage());
    }

    public static Card getNumericCard(Suit suit, int cardNumber, boolean isFaceUp) {

        if (cardNumber > 1 && cardNumber < 11) {
            return new Card(suit, String.valueOf(cardNumber), cardNumber, isFaceUp);
        }
        System.out.println("Invalid Numeric card selected");
        return null;
    }

    public static Card getFaceCard(Suit suit, char abbrev, boolean isFaceUp) {
        if (abbrev == 'A') {
            return new Card(suit, "A", 1, isFaceUp);
        }

        int charIndex = "JQK".indexOf(abbrev);
        if (charIndex > -1) {
            return new Card(suit, "" + abbrev, charIndex + 11, isFaceUp);
        }
        System.out.println("Invalid Face card selected");
        return null;
    }

    public static List<Card> getStandardDeck(Boolean isFaceUp) {

        List<Card> deck = new ArrayList<>(52);
        for (Suit suit : Suit.values()) {
            for (int i = 2; i <= 10; i++) {
                deck.add(getNumericCard(suit, i, isFaceUp));
            }
            for (char c : new char[]{'J', 'Q', 'K', 'A'}) {
                deck.add(getFaceCard(suit, c, isFaceUp));
            }
        }
        return deck;
    }

    public static void printDeck(List<Card> deck) {
        printDeck(deck, "Current Deck", 4);
    }

    public static void printDeck(List<Card> deck, String description, int rows) {

        System.out.println("---------------------------");
        if (description != null) {
            System.out.println(description);
        }
        int cardsInRow = deck.size() / rows;
        for (int i = 0; i < rows; i++) {
            int startIndex = i * cardsInRow;
            int endIndex = startIndex + cardsInRow;
            deck.subList(startIndex, endIndex).forEach(c -> System.out.print(c + " "));

            System.out.println();
        }
    }

    public Card flip() {
        this.faceUp = ! this.faceUp;
        return this;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
    }
}

