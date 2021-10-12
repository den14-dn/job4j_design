package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException, IllegalArgumentException {
        Search objSearch = new Search();
        objSearch.validateArgs(args);
        Path start = Paths.get(args[0]);
        objSearch.search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    private void validateArgs(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER FILE_EXTENSION");
        } else if (args.length == 1) {
            throw new IllegalArgumentException("File extension is null. Usage java -jar dir.jar ROOT_FOLDER FILE_EXTENSION");
        } else if (!Paths.get(args[0]).toFile().isDirectory()) {
            throw new IllegalArgumentException("Path is not directory. Usage java -jar dir.jar ROOT_FOLDER FILE_EXTENSION");
        }
    }

    public List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
