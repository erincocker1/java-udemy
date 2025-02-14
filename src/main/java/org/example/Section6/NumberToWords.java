package org.example.Section6;

public class NumberToWords {
    public static void numberToWords(int num) {
        int reversedNum = reverse(num);

        int leadingZeros = getDigitCount(num) - getDigitCount(reversedNum);
        for (int i=0; i < leadingZeros; i++) {
            System.out.print("Zero ");
        }

        while (reversedNum != 0) {
            int digit = reversedNum % 10;
            System.out.print(getWordFromDigit(digit) + " ");
            reversedNum = reversedNum / 10;
        }
    }

    public static int reverse(int num) {
        int reversedNum = 0;
        while (num != 0) {
            reversedNum = 10*reversedNum + num % 10;
            num = num / 10;
        }
        return reversedNum;
    }

    public static int getDigitCount(int num) {
        int count = 1;
        while (num >= 10) {
            num = num / 10;
            count++;
        }
        return count;
    }

    public static String getWordFromDigit(int digit) {
        return switch (digit) {
            case 0 -> "Zero";
            case 1 -> "One";
            case 2 -> "Two";
            case 3 -> "Three";
            case 4 -> "Four";
            case 5 -> "Five";
            case 6 -> "Six";
            case 7 -> "Seven";
            case 8 -> "Eight";
            case 9 -> "Nine";
            default -> "uh oh";
        };
    }
}
