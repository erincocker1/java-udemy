package org.example.section17;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

//12/03/25
//Challenge to generate a stream of bingo balls, generating each letter section in a different way
public class StreamSourceChallenge {
    public static void main(String[] args) {
        printBingoBalls();
    }

    public static void printBingoBalls() {
        Stream.concat(getBStream(),
                        Stream.concat(getIStream(),
                                Stream.concat(getNStream(),
                                        Stream.concat(getGStream(), getOStream()))))
                .forEach(System.out::println);
    }

    public static Stream<String> getBStream() {
        return Stream.iterate(1, i -> i <= 15, i -> i + 1)
                .map(i -> "B" + i);
    }

    public static Stream<String> getIStream() {
        return IntStream.range(16, 31)
                .mapToObj(i -> "I" + String.valueOf(i));
    }

    public static Stream<String> getNStream() {
        String[] bingoBalls = new String[15];
        for (int i = 0; i < 15; i++) {
            bingoBalls[i] = "N" + (i + 31);
        }
        return Arrays.stream(bingoBalls);
    }

    public static Stream<String> getGStream() {
        return Stream.of("G46", "G47", "G48", "G49", "G50", "G51", "G52",
                "G53", "G54", "G55", "G56", "G57", "G58", "G59", "G60");
    }

    public static Stream<String> getOStream() {
        return Stream.iterate(61, i -> i + 1)
                .limit(15)
                .map(i -> "O" + i);
    }
}
