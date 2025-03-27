package org.example.section20;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class FileNavigation {

    public static void main(String[] args) {

        Path startingPath = Path.of(".");
        FileVisitor<Path> statsVisitor = new StatsVisitor(2);
        try {
            Files.walkFileTree(startingPath, statsVisitor);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class StatsVisitor implements FileVisitor<Path> {

        private Path initialPath = null;
        private final Map<Path, Map<String, Long>> folderInfo = new LinkedHashMap<>();
        //"size" and "files" will be the keys
        private int initialCount;
        private final int printLevel;

        public StatsVisitor(int printLevel) {
            this.printLevel = printLevel;
        }

        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                throws IOException {

            Objects.requireNonNull(file);
            Objects.requireNonNull(attrs);

            Map<String, Long> parentMap = folderInfo.get(file.getParent());
            if (parentMap != null) {
                folderInfo.get(file.getParent()).merge("size", 0L, (o, n) -> o + attrs.size());
                folderInfo.get(file.getParent()).merge("files", 0L, (o, n) -> o + 1);
            }
            return FileVisitResult.CONTINUE;
        }

        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            Objects.requireNonNull(file);
            if (exc != null) {
                System.out.println(exc.getClass().getSimpleName() + " " + file);
            }
            return FileVisitResult.CONTINUE;
        }

        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                throws IOException {

            Objects.requireNonNull(dir);
            Objects.requireNonNull(attrs);

            if (initialPath == null) {
                initialPath = dir;
                initialCount = dir.getNameCount();
            } else {
                int relativeLevel = dir.getNameCount() - initialCount;
                if (relativeLevel == 1) {
                    folderInfo.clear();
                }
                folderInfo.put(dir, new HashMap<>(Map.of("size", 0L, "files", 0L)));
            }
            return FileVisitResult.CONTINUE;
        }

        public FileVisitResult postVisitDirectory(Path dir, IOException exc)
                throws IOException {

            Objects.requireNonNull(dir);
//            if (exc != null)
//                throw exc;

            if (dir.equals(initialPath)) {
                return FileVisitResult.TERMINATE;
            }

            int relativeLevel = dir.getNameCount() - initialCount;
            if (relativeLevel == 1) {
                folderInfo.forEach((key, value) -> {
                    int level = key.getNameCount() - initialCount - 1;
                    if (level < printLevel) {
                        System.out.printf("%s[%s] - %,d bytes - %,d files %n",
                                "\t".repeat(level), key.getFileName(), value.get("size"), value.get("files"));
                    }
                });

            } else {
                long folderSize = folderInfo.get(dir).get("size");
                folderInfo.get(dir.getParent()).merge("size", 0L, (o, n) -> o + folderSize);
                long numOfFiles = folderInfo.get(dir).get("files");
                folderInfo.get(dir.getParent()).merge("files", 0L, (o, n) -> o + numOfFiles);
            }
            return FileVisitResult.CONTINUE;
        }
    }
}
