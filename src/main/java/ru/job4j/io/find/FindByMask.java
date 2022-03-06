package ru.job4j.io.find;

import java.nio.file.Path;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class FindByMask implements Function<String, Predicate<Path>> {

    @Override
    public Predicate<Path> apply(String regex) {
        Pattern pattern = Pattern.compile(regex
                .replace(".", "\\.")
                .replace("*", ".*")
                .replace("?", "\\w{1}")
        );
        return e -> pattern.matcher(e.getFileName().toString()).matches();
    }
}
