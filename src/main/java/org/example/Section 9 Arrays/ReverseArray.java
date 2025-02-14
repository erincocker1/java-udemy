package org.example;

import java.util.Arrays;

// 07/02/25 Section 9 Exercise 43
public class ReverseArray {
    private static void reverse(int[] array) {
        int len = array.length;
        for (int i = 0; i < len / 2; i++) {
            int temp = array[i];
            array[i] = array[len - 1 - i];
            array[len - 1 - i] = temp;
        }
        System.out.println("Reversed array = " + Arrays.toString(array));
    }
}
