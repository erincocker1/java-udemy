package org.example;
// 07/02/25 Section 9 Exercise 42
import java.util.Scanner;

public class MinimumElement {
    public static int readInteger() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static int[] readElements(int howMany) {
        Scanner scanner = new Scanner(System.in);
        int[] elements = new int[howMany];
        for (int i = 0; i < howMany; i++) {
            elements[i] = scanner.nextInt();
        }
        return elements;
    }

    public static int findMin(int[] elements) {
        int min = elements[0];
        for (int element : elements) {
            if (element < min) {
                min = element;
            }
        }
        return min;
    }
}
