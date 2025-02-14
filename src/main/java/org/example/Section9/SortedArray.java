package org.example.Section9;

import java.util.Arrays;
import java.util.Scanner;

// 06/02/25 Section 9 Exercise 41
public class SortedArray {

    public static int[] getIntegers(int size) {
        int[] newArray = new int[size];
        Scanner scanner = new Scanner(System.in);

        for (int i=0; i<size; i++) {
            newArray[i] = scanner.nextInt();
        }

        return newArray;
    }

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println("Element " + i + " contents " + array[i]);
        }
    }

    public static int[] sortIntegers(int[] array) {
        int length = array.length;
        int[] copiedArray = Arrays.copyOf(array, length);
        Arrays.sort(copiedArray);

        for (int i = 0; i < length / 2; i++) {
            int temp = copiedArray[i];
            copiedArray[i] = copiedArray[length-1-i];
            copiedArray[length-1-i] = temp;
        }
        return copiedArray;
    }
}
