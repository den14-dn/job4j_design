package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    public void unavailable(String source, String target) {
        String element = null;

        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(target)))) {
                for (String line = in.readLine(); line != null; line = in.readLine()) {
                    String[] substrings = line.split(" ");
                    if (substrings.length < 2) {
                        continue;
                    }

                    if ((substrings[0].equals("400") || substrings[0].equals("500")) && element == null) {
                        element = substrings[1];
                    } else if (element != null && !substrings[0].equals("400") && !substrings[0].equals("500")) {
                        element = element.concat(";").concat(substrings[1]);
                        out.println(element);
                        element = null;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}