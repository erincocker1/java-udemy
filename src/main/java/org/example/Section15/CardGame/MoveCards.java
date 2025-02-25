package org.example.Section15.CardGame;

import java.util.*;
import java.util.regex.Pattern;

public class MoveCards {

    public static void moveCards(Solitaire solitaire, String userInputLowerCase) {
        Pattern pattern = Pattern.compile("^([1-7]c[1-7]|waste|hearts|spades|clubs|diamonds) (c[1-7]|hearts|spades|clubs|diamonds)$");
        if (!pattern.matcher(userInputLowerCase).matches()) {
            System.out.println("Invalid command. Enter 'help' to see valid commands");
            return;
        }

        String source = userInputLowerCase.split(" ")[0];
        String dest = userInputLowerCase.split(" ")[1];

        Deque<Card> cardsToMove = getCardsToBeMoved(solitaire, source);
        if (cardsToMove == null || cardsToMove.size() == 0) {
            System.out.println("Move not allowed!");
            return;
        }

        if (!tryToPlaceMovedCards(solitaire, cardsToMove, dest)) {
            putThoseCardsBackWhereTheyCameFromOrSoHelpMe(solitaire, cardsToMove, source);
            System.out.println("Move not allowed!");
            return;
        }

        if (source.charAt(1) == 'c') {
            List<Card> column = solitaire.tableau.get(Integer.parseInt(source.substring(2,3)) - 1);
            if (column.size() > 0) {
                column.get(column.size() - 1).setFaceUp(true);
            }
        }
    }

    private static Deque<Card> getCardsToBeMoved(Solitaire solitaire, String source) {
        Deque<Card> cards = new ArrayDeque<>();
        switch (source) {
            case "waste" -> cards.push(solitaire.wastePile.pop());
            case "hearts" -> cards.push(solitaire.hearts.pop());
            case "spades" -> cards.push(solitaire.spades.pop());
            case "clubs" -> cards.push(solitaire.clubs.pop());
            case "diamonds" -> cards.push(solitaire.diamonds.pop());
            default -> {

                int numOfCards = Integer.parseInt(source.substring(0, 1));
                List<Card> column = solitaire.tableau.get(Integer.parseInt(source.substring(2, 3)) - 1);

                if (column.size() < numOfCards) {
                    return null;
                }

                for (int i = 0; i < numOfCards; i++) {
                    cards.push(column.remove(column.size() - 1));
                }

                if (!cardsToBeMovedAreValid(cards)) { //put em back if it's invalid
                    putThoseCardsBackWhereTheyCameFromOrSoHelpMe(solitaire, cards, source);
                }

            }
        }
        return cards;
    }

    private static void putThoseCardsBackWhereTheyCameFromOrSoHelpMe(
            Solitaire solitaire, Deque<Card> cards, String source) {

        switch (source) {
            case "waste" -> solitaire.wastePile.push(cards.pop());
            case "hearts" -> solitaire.hearts.push(cards.pop());
            case "spades" -> solitaire.spades.push(cards.pop());
            case "clubs" -> solitaire.clubs.push(cards.pop());
            case "diamonds" -> solitaire.diamonds.push(cards.pop());
            default -> {
                int columnIndex = Integer.parseInt(source.substring(2, 3)) - 1;
                while (cards.size() != 0) {
                    solitaire.tableau.get(columnIndex).add(cards.pop());
                }
            }
        }
    }

    private static boolean cardsToBeMovedAreValid(Deque<Card> cards) {
        for (Card card : cards) {
            if (!card.isFaceUp()) {
                return false;
            }
        }
        return true;
    }

    private static boolean tryToPlaceMovedCards(Solitaire solitaire, Deque<Card> cardsToMove, String dest) {
        Card topCard = cardsToMove.peek();
        switch (dest) {
            case "hearts" -> {
                return canBePlacedOnSuitPile(cardsToMove, topCard,
                        solitaire.hearts, Card.Suit.HEART);
            }
            case "spades" -> {
                return canBePlacedOnSuitPile(cardsToMove, topCard,
                        solitaire.spades, Card.Suit.SPADE);
            }
            case "clubs" -> {
                return canBePlacedOnSuitPile(cardsToMove, topCard,
                        solitaire.clubs, Card.Suit.CLUB);
            }
            case "diamonds" -> {
                return canBePlacedOnSuitPile(cardsToMove, topCard,
                        solitaire.diamonds, Card.Suit.DIAMOND);
            }
            default -> {
                List<Card> column = solitaire.tableau.get(Integer.parseInt(dest.substring(1,2)) - 1);

                if (column.size() == 0 && topCard.getRank() != 13) {
                    return false;
                }
                if (column.get(column.size()-1).getRank() - topCard.getRank() != 1) {
                    return false;
                }

                if ((column.get(column.size()-1).getSuit() == Card.Suit.HEART ||
                        column.get(column.size()-1).getSuit() == Card.Suit.DIAMOND) &&
                        (topCard.getSuit() == Card.Suit.HEART || topCard.getSuit() == Card.Suit.DIAMOND)) {
                    return false;
                }

                if ((column.get(column.size()-1).getSuit() == Card.Suit.SPADE ||
                        column.get(column.size()-1).getSuit() == Card.Suit.CLUB) &&
                        (topCard.getSuit() == Card.Suit.SPADE || topCard.getSuit() == Card.Suit.CLUB)) {
                    return false;
                }

                while (cardsToMove.size() != 0) {
                    column.add(cardsToMove.pop());
                }
            }
        }
        return true;
    }

    private static boolean canBePlacedOnSuitPile(
            Deque<Card> cardsToMove, Card topCard, Deque<Card> suitPile, Card.Suit suit) {

        if (cardsToMove.size() != 1 || topCard.getSuit() != suit) {
            return false;
        }
        if (suitPile.size() == 0 && topCard.getRank() != 1) {
            return false;
        }
        if (suitPile.size() > 0) {
            if (topCard.getRank() - suitPile.peek().getRank() != 1) {
                return false;
            }
        }
        suitPile.push(cardsToMove.pop());
        return true;
    }
}
