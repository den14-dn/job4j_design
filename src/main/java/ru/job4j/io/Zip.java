package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File file : sources) {
                zip.putNextEntry(new ZipEntry(file.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void valid(ArgsName argsName) {
        if (argsName.get("d") == null) {
            throw new IllegalArgumentException("Isn't argument directory");
        } else if (argsName.get("e") == null) {
            throw new IllegalArgumentException("Isn't argument exclude");
        } else if (argsName.get("o") == null) {
            throw new IllegalArgumentException("Isn't argument output");
        } else if (!Paths.get(argsName.get("d")).toFile().isDirectory()) {
            throw new IllegalArgumentException("Directory '" + argsName.get("d") + " doesn't exist");
        }
    }

    private static List<File> getListFiles(String path, String extension) throws IOException {
        return new Search().search(
                Paths.get(path),
                p -> !p.toFile().getName().endsWith(extension)
        ).stream()
                .map(Path::toFile)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        valid(argsName);
        try {
            List<File> files = getListFiles(argsName.get("d"), argsName.get("e").substring(1));
            packFiles(files, Paths.get(argsName.get("o")).toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 }
