package org.example.section17.streamingstudentss;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        Course pymc = new Course("PYMC", "Python Masterclass");
        Course jmc = new Course("JMC", "Java Masterclass");
        Student[] students = new Student[1000];
        Arrays.setAll(students, (i) -> Student.getRandomStudent(jmc, pymc));

        printNumOfStudentsByGender(students, "M");
        printNumOfStudentsByGender(students, "F");
        printNumOfStudentsByGender(students, "U");
        System.out.println("------------------------------------------");
        printNumOfStudentsByAgeRange(students, 0, 30);
        printNumOfStudentsByAgeRange(students, 30, 61);
        printNumOfStudentsByAgeRange(students, 61, 150);
        System.out.println("------------------------------------------");
        printAgeStats(students);
        System.out.println("------------------------------------------");
        printDistinctCountries(students);
        System.out.println("------------------------------------------");
        printOldAndActiveStudents(students);
    }

    private static void printNumOfStudentsByGender(Student[] students, String gender) {
        int count = (int) Stream.of(students)
                .filter(student -> student.getGender().equals(gender))
                .count();
        System.out.println("Number of students of gender " + gender + ": " + count);
    }

    private static void printNumOfStudentsByAgeRange(Student[] students, int startRange, int endRange) {
        int count = (int) Stream.of(students)
                .filter(student -> student.getAge() >= startRange && student.getAge() < endRange)
                .count();
        System.out.printf("Number of students in range %d <= age < %d : %d\n", startRange, endRange, count);
    }

    private static void printAgeStats(Student[] students) {
        IntSummaryStatistics stats = Stream.of(students)
                .mapToInt(Student::getAge)
                .summaryStatistics();
        System.out.printf("Ages range from %d to %d and the average is %.2f\n",
                stats.getMin(), stats.getMax(), stats.getAverage());
    }

    private static void printDistinctCountries(Student[] students) {
        Stream.of(students)
                .map(Student::getCountryCode)
                .distinct()
                .forEach(System.out::println);
    }

    private static void printOldAndActiveStudents(Student[] students) {
        if (Stream.of(students)
                .anyMatch(student -> student.getYearsSinceEnrolled() > 7 && student.getMonthsSinceActive() < 2)) {
            System.out.println("There are students who have been enrolled over 7 years and are still active");
            Stream.of(students)
                    .filter(student -> student.getYearsSinceEnrolled() > 7 && student.getMonthsSinceActive() < 2)
                    .limit(5)
                    .forEach(System.out::println);

        } else {
            System.out.println("There are NOT students who have been enrolled over 7 years and are still active");
        }
    }

}
