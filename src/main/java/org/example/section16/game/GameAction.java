package org.example.section16.game;

import java.util.function.BiPredicate;

public record GameAction(char key, String prompt, BiPredicate<Integer, Character> action) {
}
