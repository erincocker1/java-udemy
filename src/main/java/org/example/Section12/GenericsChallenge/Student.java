package org.example.Section12.GenericsChallenge;

import java.util.Random;

public class Student implements QueryItem, Comparable<Student> {

    private String name;
    private String course;
    private int yearStarted;

    private int id;
    private static int idCounter = 1;

    protected static Random random = new Random();

    private static String[] firstNames = {"Ann", "Bill", "Cathy", "John", "Tim"};
    private static String[] courses = {"C++", "Java", "Python"};

    public Student() {
        int lastNameIndex = random.nextInt(65, 91);
        name = firstNames[random.nextInt(5)] + " " + (char) lastNameIndex;
        course = courses[random.nextInt(3)];
        yearStarted = random.nextInt(2018, 2023);
        id = idCounter;
        idCounter++;
    }

    @Override
    public String toString() {
        return "%-5d %-15s %-15s %d".formatted(id, name, course, yearStarted);
    }

    public int getYearStarted() {
        return yearStarted;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public boolean matchFieldValue(String fieldName, String value) {

        String fName = fieldName.toUpperCase();
        return switch (fName) {
            case "NAME" -> name.equalsIgnoreCase(value);
            case "COURSE" -> course.equalsIgnoreCase(value);
            case "YEARSTARTED" -> yearStarted == (Integer.parseInt(value));
            case "ID" -> id == (Integer.parseInt(value));
            default -> false;
        };
    }

    @Override
    public int compareTo(Student o) {
        return Integer.compare(this.id, o.id);
    }

    //Comparison by year can be easily implemented with a method reference to getYearStarted. e.g.
    // matchesQueryList.sort(Comparator.comparingInt(Student::getYearStarted));
    //Something more complex that doesn't just use comparingInt could also be easily implemented
    // with an anonymous inner class.
}
