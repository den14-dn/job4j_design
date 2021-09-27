package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class ResultFile {
    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream("result.txt")
                )
        )) {
            //out.write("Hello, world!");
            out.printf("%s%n", "some string");
            out.printf("%d%n", 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}