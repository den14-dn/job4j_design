package ru.job4j.io.find;

import ru.job4j.io.ArgsName;
import ru.job4j.io.SearchFiles;

import java.io.*;
import java.nio.file.*;
import java.util.function.Predicate;

public class Find {
    private void validateArgs(ArgsName args) {
        String command = "java -jar find.jar -d=ROOT_FOLDER -n=NAME_FILE -t=TYPE_SEARCH -o=FILE_RESULT";
        if (args.get("d") == null || !Paths.get(args.get("d")).toFile().isDirectory()) {
            throw new IllegalArgumentException("Root folder is null. Usage ".concat(command));
        } else if (args.get("n") == null || args.get("n").isEmpty()) {
            throw new IllegalArgumentException("Search criteria is null. Usage ".concat(command));
        } else if (args.get("t") == null || args.get("t").isEmpty()) {
            throw new IllegalArgumentException("Search type is null. Usage ".concat(command));
        } else if (args.get("o") == null || args.get("o").isEmpty()) {
            throw new IllegalArgumentException("Search results is null. Usage ".concat(command));
        }
    }

    private void search(Path root, Predicate<Path> condition, String logFile) {
        SearchFiles searcher = new SearchFiles(condition);
        try (PrintWriter out = new PrintWriter(new FileOutputStream(logFile))) {
            Files.walkFileTree(root, searcher);
            searcher.getPaths().forEach(out :: println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        Find objFind = new Find();
        objFind.validateArgs(argsName);
        Predicate<Path> condition = e -> e.getFileName().toString().equalsIgnoreCase(argsName.get("n"));
        if (argsName.get("t").equalsIgnoreCase("mask")) {
            String regex = argsName.get("n").startsWith("*") ? ".".concat(argsName.get("n")) : ".*".concat(argsName.get("n"));
            condition = e -> e.getFileName().toString().matches(regex);
        } else if (argsName.get("t").equalsIgnoreCase("regex")) {
            condition = e -> e.getFileName().toString().matches(argsName.get("t"));
        }
        objFind.search(Paths.get(argsName.get("d")), condition, argsName.get("o"));
    }
}
