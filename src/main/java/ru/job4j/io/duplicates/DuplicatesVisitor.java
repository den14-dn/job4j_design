package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Set<FileProperty> files = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String nameFile = file.toFile().getName();
        FileProperty fileProperty = new FileProperty(file.toFile().length(), nameFile);
        if (files.contains(fileProperty)) {
            System.out.println(file.toAbsolutePath());
        } else {
            files.add(fileProperty);
        }
        return super.visitFile(file, attrs);
    }
}
