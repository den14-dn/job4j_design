package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    public void unavailable(String source, String target) {
        List<String> rst = new ArrayList<>();

        String element = null;

        try (BufferedReader log = new BufferedReader(new FileReader(source))) {
            for (String line = log.readLine(); line != null; line = log.readLine()) {
                String[] substrings = line.split(" ");
                if (substrings.length < 2) {
                    continue;
                }

                if ((substrings[0].equals("400") || substrings[0].equals("500")) && element == null) {
                    element = substrings[1];
                } else if (element != null && !substrings[0].equals("400") && !substrings[0].equals("500")) {
                    element = element.concat(";").concat(substrings[1]);
                    rst.add(element);
                    element = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            for (String el : rst) {
                out.println(el);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        /*try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        new Analizy().unavailable("./data/source.csv", "./data/target.csv");
    }
}