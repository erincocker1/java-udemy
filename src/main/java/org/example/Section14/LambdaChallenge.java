package org.example.Section14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LambdaChallenge {
    public static void main(String[] args) {
        String[] firstNames = {"Alice", "Bob", "Charlie", "Derek", "Eve"};
        List<String> firstNamesList = Arrays.asList(firstNames);
        firstNamesList.replaceAll(s -> s.toUpperCase());
        firstNamesList.replaceAll(s -> "%s %s. %s".formatted(s,
                getMiddleChar(65,91),
                getReverseSurname(s)));

        firstNamesList.forEach(s -> System.out.println(s));
        System.out.println();

        List<String> anotherList = new ArrayList<>(List.of(firstNames));
        anotherList.removeIf(s -> s.split(" ")[0].equals(s.split(" ")[2]));
        anotherList.forEach(s -> System.out.println(s));
    }

    private static char getMiddleChar(int startChar, int endChar) {
        return (char) new Random().nextInt(startChar, endChar);
    }

    private static String getReverseSurname(String firstName) {
        return new StringBuilder(firstName).reverse().toString();

    }
}
