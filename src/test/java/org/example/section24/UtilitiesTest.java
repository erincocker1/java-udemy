package org.example.section24;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilitiesTest {
    Utilities util;
    @BeforeEach
    void setUp() {
        util = new Utilities();
    }

    @Test
    void everyNthChar() {
        assertArrayEquals(new char[] {'e','l'}, util.everyNthChar("hello".toCharArray(), 2));
    }

    @Test
    void everyNthCharArrayNotLongEnough() {
        assertArrayEquals(new char[] {'h','e','l','l','o'},
                util.everyNthChar("hello".toCharArray(), 6));
    }

    @Test
    void removePairs() {
        assertEquals("ABCDEF", util.removePairs("AABCDDEFF"));
    }

    @Test
    void removePairsWithNonConsecutiveRepeatingLetters() {
        assertEquals("ABCABDEF", util.removePairs("ABCCABDEEF"));
    }

    @Test
    void converter() {
        assertEquals(300, util.converter(10,5));
    }

    @Test
    void nullIfOddLengthOddString() {
        assertNull(util.nullIfOddLength("odd"));
    }

    @Test
    void nullIfOddLengthEvenString() {
        assertEquals("even", util.nullIfOddLength("even"));
    }
}