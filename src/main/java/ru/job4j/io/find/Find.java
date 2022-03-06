package ru.job4j.io.find;

import ru.job4j.io.ArgsName;
import ru.job4j.io.SearchFiles;

import java.io.*;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class Find {
    Map<String, Function<String, Predicate<Path>>> conditions = new HashMap<>();

    public Find() {
        conditions.put("mask", new FindByMask());
        conditions.put("regex", new FindByRegex());
        conditions.put("fileName", new FindByFileName());
    }

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
        Predicate<Path> condition = objFind.conditions
                .get(argsName.get("t").toLowerCase())
                .apply(argsName.get("n"));
        objFind.search(Paths.get(argsName.get("d")), condition, argsName.get("o"));
    }
}
