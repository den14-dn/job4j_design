package ru.job4j.io.find;

import java.nio.file.Path;
import java.util.function.Function;
import java.util.function.Predicate;

public class FindByFileName implements Function<String, Predicate<Path>> {
    @Override
    public Predicate<Path> apply(String s) {
        return e -> e.getFileName().toString().equalsIgnoreCase(s);
    }
}
