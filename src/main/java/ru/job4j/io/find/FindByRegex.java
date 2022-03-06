package ru.job4j.io.find;

import java.nio.file.Path;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class FindByRegex implements Function<String, Predicate<Path>> {
    @Override
    public Predicate<Path> apply(String regex) {
        Pattern pattern = Pattern.compile(regex);
        return e -> pattern.matcher(e.getFileName().toString()).matches();
    }
}
