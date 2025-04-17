package org.example.section24;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.ValueSources;

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

    @ParameterizedTest
    @CsvSource({"ABCDEFF,ABCDEF", "AB88EFFG,AB8EFG", "112233445566,123456", "ZYZQQB,ZYZQB", "A,A"})
    void removePairsMultipleTests(String input, String expected) {
        assertEquals(expected, util.removePairs(input));
    }

    @Test
    void converter() {
        assertEquals(300, util.converter(10,5));
    }

    @Test
    void converterDivideByZero() {
        assertEquals(300, util.converter(10,5));
        assertThrows(ArithmeticException.class, () -> util.converter(10,0));
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