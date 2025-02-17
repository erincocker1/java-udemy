package org.example.Section15.CardGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solitaire {
    private List<List<Card>> tableau;
    private List<Card> stock;

    public Solitaire() {
        SetUpBoard();
        System.out.println(tableau);
        System.out.println("---------");
        System.out.println(stock);
    }

    private void SetUpBoard() {
        tableau = new ArrayList<>(7);
        for (int i = 0; i < 7; i++) {
            tableau.add(new ArrayList<>());
        }

        List<Card> deck = Card.getStandardDeck(false);
        Collections.shuffle(deck);
        for (int height = 0; height < 7; height++) {
            for (int pile = 0; pile < 6-height; pile++) {
                tableau.get(pile).add(deck.remove(0));
            }
            tableau.get(6-height).add(deck.remove(0).flip());
        }

        stock = new ArrayList<>(deck);
    }
}
