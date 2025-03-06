package org.example.section7;

import java.util.Scanner;

public class SumAndAverage {
    public static void inputThenPrintSumAndAverage() {
        Scanner scanner = new Scanner(System.in);
        int sum = 0;
        double count = 0;

        while (true) {
            System.out.println("Enter an integer, or anything else to quit");
            try {
                sum += scanner.nextInt();
                count += 1;
            } catch (Exception e) {
                break;
            }
        }

        if (count == 0) {
            System.out.println("SUM = 0 AVG = 0");
        } else {
            System.out.println("SUM = " + sum + " AVG = " + Math.round(sum/count));
        }
    }
}
