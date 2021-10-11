package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Array params is empty. Need to pass array of params.");
        }
        for (String el : args) {
            String[] params = el.split("=");
            if (params.length == 0) {
                throw new IllegalArgumentException("Array's element doesn't contains params.");
            } else if (params[0].isEmpty()) {
                throw new IllegalArgumentException("Parameter doesn't contains key.");
            } else if (params.length == 1) {
                throw new IllegalArgumentException("Parameter doesn't contains value.");
            }
            values.put(params[0].replace("-", ""), params[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
