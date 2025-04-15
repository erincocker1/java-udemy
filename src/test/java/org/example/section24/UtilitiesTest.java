package org.example.section24;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilitiesTest {

    @Test
    void everyNthChar() {
        fail("Test not yet implemented");
    }

    @Test
    void removePairs() {
        Utilities utilities = new Utilities();
        assertEquals("ABCDEF", utilities.removePairs("AABCDDEFF"));
    }

    @Test
    void removePairsWithNonConsecutiveRepeatingLetters() {
        Utilities utilities = new Utilities();
        assertEquals("ABCABDEF", utilities.removePairs("ABCCABDEEF"));
    }

    @Test
    void converter() {
        fail("Test not yet implemented");
    }

    @Test
    void nullIfOddLength() {
        fail("Test not yet implemented");
    }
}