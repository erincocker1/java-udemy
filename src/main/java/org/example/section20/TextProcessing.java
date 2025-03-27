package org.example.section20;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextProcessing {
    public static void main(String[] args) {
        printCommonWords("src/main/resources/cars.txt", 6, 15);
        System.out.println("----------------------------------------");
        //printCommonWords("src/main/resources/beemovie.txt", 5, 10);

    }

    private static void printCommonWords(String filePath, int minWordLength, int wordsShown) {
        Path path = Path.of(filePath);
        Map<String, Long> counts;
        Pattern pattern = Pattern.compile("\\p{javaWhitespace}+");

        try (Stream<String> stringStream = Files.lines(path)) {
            counts = stringStream
                    .flatMap(pattern::splitAsStream)
                    .filter(s -> !(s.startsWith("[") || s.endsWith("]")))
                    .map(s -> s.toLowerCase().replaceAll("[^a-z-]", ""))
                    .filter(s -> s.length() >= minWordLength)
                    .collect(Collectors.groupingBy(s -> s, Collectors.counting()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        counts.entrySet().stream()
                .sorted((Map.Entry.comparingByValue(Comparator.reverseOrder())))
                .limit(wordsShown)
                .forEach(System.out::println);
    }
}
