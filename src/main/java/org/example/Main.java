package org.example;

/**
 * Hello world!
 */
public class Main {
    public static void main(String[] args) {
        int[] array = SortedArray.getIntegers(5);
        SortedArray.printArray(array);
        int[] sortedArray = SortedArray.sortIntegers(array);
        SortedArray.printArray(sortedArray);
    }
}
