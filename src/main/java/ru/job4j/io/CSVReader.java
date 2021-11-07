package ru.job4j.io;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        var csvReader = new CSVReader();
        csvReader.validate(argsName);
        List<String> filters = List.of(argsName.get("filter").split(","));
        Set<Integer> columnsFilters = new HashSet<>(filters.size());
        String delimiter = argsName.get("delimiter");
        boolean firstLine = true;
        String rst = "";

        Path path = Paths.get(argsName.get("path"));
        var scanner = new Scanner(path);
        scanner.useDelimiter(System.lineSeparator());
        while (scanner.hasNext()) {
            String line = scanner.next();
            var scannerLine = new Scanner(line);
            scannerLine.useDelimiter(delimiter);
            int index = 0;
            String str = "";
            while (scannerLine.hasNext()) {
                String subLine = scannerLine.next();
                if (firstLine && filters.contains(subLine)) {
                    columnsFilters.add(index);
                    str = str.isEmpty() ? str.concat(subLine) : str.concat(delimiter).concat(subLine);
                } else if (!firstLine) {
                    if (columnsFilters.contains(index)) {
                        str = str.isEmpty() ? str.concat(subLine) : str.concat(delimiter).concat(subLine);
                    }
                }
                index++;
            }
            firstLine = false;
            rst = rst.concat(str).concat(System.lineSeparator());
        }
        Files.writeString(Paths.get(argsName.get("out")), rst);
    }

    private void validate(ArgsName argsName) {
        if (argsName.get("path") == null) {
            throw new IllegalArgumentException("File source is not specified.");
        } else if (argsName.get("delimiter") == null) {
            throw new IllegalArgumentException("Delimiter is not specified.");
        } else if (argsName.get("out") == null) {
            throw new IllegalArgumentException("File for recording the result is not specified.");
        } else if (argsName.get("filter") == null) {
            throw new IllegalArgumentException("No filters specified.");
        }
    }
}
