package ru.job4j.io.scanner;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class ScannerExample2 {
    public static void main(String[] args) {
        var data = "expl1@mail.ru, expl2@mail.ru, expl3@mail.ru";
        var scanner = new Scanner(new ByteArrayInputStream(data.getBytes())).useDelimiter(", ");
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }
    }
}
