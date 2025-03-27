package org.example.section20.filewriting;
import org.example.section17.streamingstudentss.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileWriting {
    public static void main(String[] args) {
        Course pymc = new Course("PYMC", "Python Masterclass", 50);
        Course jmc = new Course("JMC", "Java Masterclass", 100);
        Course cgj = new Course("CGJ", "Creating Games in Java", 70);
        Course cmc = new Course("CMC", "C Masterclass", 80);

        String students = Stream
                .generate(() -> Student.getRandomStudent(pymc, jmc, cgj, cmc))
                .limit(2)
                .map(Student::toJSON)
                .collect(Collectors.joining(", " + System.lineSeparator(), "[", "]"));

        Path path = Path.of("src/main/java/org/example/section20/filewriting/students.json");
        try {
            Files.writeString(path, students);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
