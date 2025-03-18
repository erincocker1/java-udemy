package org.example.section17.streamingstudentss;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//14/03/25 Section 17 Lecture 262, 267
public class MainChallenge {
    public static void main(String[] args) {
        secondChallenge();
    }

    private static void secondChallenge() { //from lecture 267
        Course pymc = new Course("PYMC", "Python Masterclass", 50);
        Course jmc = new Course("JMC", "Java Masterclass", 100);
        Course cgj = new Course("CGJ", "Creating Games in Java", 70);
        Course cmc = new Course("CMC", "C Masterclass", 80);

        List<Student> students = Stream
                .generate(() -> Student.getRandomStudent(jmc, pymc, cgj, cmc))
                .filter(student -> student.getYearsSinceEnrolled() <= 4)
                .limit(10000)
                .toList();


        Map<String, Long> courseCounts = students.stream()
                .flatMap(student -> student.getEngagementMap().keySet().stream())
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        System.out.println(courseCounts);
        System.out.println("----------------------------------------------");


        Map<Integer, Long> courseAmountCounts = students.stream()
                .map(student -> student.getEngagementMap().keySet().size())
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        System.out.println(courseAmountCounts);
        System.out.println("----------------------------------------------");


        Map<String, Double> avgPercentageByCourse = students.stream()
                .flatMap(student -> student.getEngagementMap().values().stream())
                .collect(Collectors.groupingBy(CourseEngagement::getCourseCode,
                        Collectors.averagingDouble(CourseEngagement::getPercentComplete)));
        avgPercentageByCourse.forEach((k, v) -> System.out.printf("%s: avg. %.2f%% complete, ", k, v));
        System.out.println();
        System.out.println("----------------------------------------------");


        Map<String, Map<Integer, Long>> coursesByActivityYear = students.stream()
                .flatMap(student -> student.getEngagementMap().values().stream())
                .collect(Collectors.groupingBy(CourseEngagement::getCourseCode,
                        Collectors.groupingBy(CourseEngagement::getLastActivityYear, Collectors.counting())));
        coursesByActivityYear.forEach((course, values) -> {
            System.out.println("Count for course " + course + ": ");
            values.forEach((year, count) -> System.out.println(year + ": " + count));
        });

    }

    private static void firstChallenge() { //from lecture 262
        Course pymc = new Course("PYMC", "Python Masterclass", 50);
        Course jmc = new Course("JMC", "Java Masterclass", 100);
        Course cgj = new Course("CGJ", "Creating Games in Java");

        Student[] students = new Student[5000];
        Arrays.setAll(students, (i) -> Student.getRandomStudent(jmc, pymc));

        int jmcStudentCount = (int) Stream.of(students)
                .filter(student -> student.getEngagementMap().get("JMC") != null)
                .count();

        double sumOfPercentages = Stream.of(students)
                .filter(student -> student.getEngagementMap().get("JMC") != null)
                .mapToDouble(student -> student.getPercentComplete("JMC"))
                .reduce(0, Double::sum);

        System.out.printf("Average percentage complete for the JMC is %.2f%%\n", sumOfPercentages / jmcStudentCount);

        double seventyFifthPercentile = Stream.of(students)
                .filter(student -> student.getEngagementMap().get("JMC") != null)
                .mapToDouble(student -> student.getPercentComplete("JMC"))
                .sorted()
                .skip(Math.round(jmcStudentCount * 0.75))
                .limit(1)
                .sum();
        System.out.printf("75th percentile of percentage complete for the JMC is %.2f%%\n", seventyFifthPercentile);

        List<Student> topStudents = Stream.of(students)
                .filter(student -> student.getEngagementMap().get("JMC") != null)
                .filter(student -> student.getPercentComplete("JMC") >= seventyFifthPercentile)
                .filter(student -> student.getMonthsSinceActive() < 12)
                .sorted(Comparator.comparing(Student::getYearEnrolled))
                .limit(10)
                .toList();

        System.out.println("-----------------------------------------");
        topStudents.forEach(System.out::println);
        topStudents.forEach(student -> student.addCourse(cgj));
        System.out.println("-----------------------------------------");
        topStudents.forEach(System.out::println);
    }
}
