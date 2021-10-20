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

    public static void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean valid(ArgsName argsName) {
        return (argsName.get("d") != null
                && argsName.get("e") != null
                && argsName.get("o") != null
                && Paths.get(argsName.get("d")).toFile().isDirectory()
        );
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
        if (!valid(argsName)) {
            throw new IllegalArgumentException();
        }
        try {
            List<File> files = getListFiles(argsName.get("d"), argsName.get("e").substring(1));
            packFiles(files, Paths.get(argsName.get("o")).toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 }
