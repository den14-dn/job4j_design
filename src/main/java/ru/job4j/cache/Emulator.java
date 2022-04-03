package ru.job4j.cache;

import java.nio.file.Path;
import java.util.Scanner;

public class Emulator {
    public static void main(String[] args) {
        System.out.print("Укажите кэшируемую директорию: ");
        String dir = new Scanner(System.in).nextLine();
        System.out.print("Укажите текстовый файл для чтения: ");
        String file = new Scanner(System.in).nextLine();
        new Emulator().validateParameters(dir, file);
        AbstractCache cache = new DirFileCache(dir);
        cache.put(file, cache.load(file));
        System.out.println(cache.get(file).toString());
    }

    private void validateParameters(String dir, String file) {
        if (!Path.of(dir, dir).toFile().isDirectory()) {
            throw new IllegalArgumentException("Expected caching directory: ".concat(dir));
        }
        if (!Path.of(dir, file).toFile().isFile()) {
            throw new IllegalArgumentException("Expected file from directory: ".concat(dir));
        }
    }
}
