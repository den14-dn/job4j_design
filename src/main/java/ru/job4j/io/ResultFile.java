package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        String fileName = "C:\\Users\\shubko\\Downloads\\result.txt";
        try (FileOutputStream out = new FileOutputStream(fileName)) {
            int size = 5;
            for (int i = 0; i < size;) {
                i++;
                for (int j = 0; j < size;) {
                    j++;
                    out.write(String.valueOf(i * j).concat(" ").getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
