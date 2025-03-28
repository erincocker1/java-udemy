package org.example.section20.indexing;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.StringJoiner;
import java.util.stream.Stream;

public class IndexingAFileSystem {
    public static void main(String[] args) {
        String initialPathName = "src/main/java/org/example/section20/indexing";
        makeStartingDirectories(initialPathName);
        updateIndexFiles(Path.of(initialPathName + "/public"));
    }


    private static void makeStartingDirectories(String initialPath) {
        try {
            Files.createDirectories(Path.of(initialPath + "/public"));
            Files.createDirectories(Path.of(initialPath + "/public/assets"));
            Files.createDirectories(Path.of(initialPath + "/public/assets/icons"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void updateIndexFiles(Path initialPath) {
        if (!Files.isDirectory(initialPath)) {
            System.out.println("Parameter must be a directory - quitting method");
            return;
        }

        //recursion - make inner index files first
        try (Stream<Path> children = Files.list(initialPath)) {
            for (Path path : children.toList()) {
                if (Files.isDirectory(path)) updateIndexFiles(path);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File file = new File(initialPath + "/index.txt");
        createIndexFile(file);

        writeToIndexFile(initialPath, file);
    }

    private static void writeToIndexFile(Path initialPath, File file) {
        StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());
        try (Stream<Path> children = Files.list(initialPath)) {
            for (Path path : children.toList()) {
                stringJoiner.add(Files.getLastModifiedTime(path) + "    " + path);
                if (Files.isDirectory(path)) {
                    stringJoiner.add(Files.readString(Path.of(path + "/index.txt")));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Files.writeString(file.toPath(), stringJoiner.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createIndexFile(File file) {
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    System.out.println("Error with " + file.toPath());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
