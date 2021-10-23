package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private List<String> responses;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() throws IOException {
        readPhrases();
        List<String> log = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String userPhrase = "";
        boolean repeatUserPoll = false;
        while (!OUT.equalsIgnoreCase(userPhrase)) {
            System.out.print("Введите слово-фразу: ");
            userPhrase = scanner.nextLine();
            log.add("user phrase : " + userPhrase);
            if (STOP.equalsIgnoreCase(userPhrase)) {
                repeatUserPoll = true;
            } else if (CONTINUE.equalsIgnoreCase(userPhrase)) {
                repeatUserPoll = false;
            }
            if (repeatUserPoll || OUT.equalsIgnoreCase(userPhrase)) {
                continue;
            }
            int randomIndex = (int) (Math.random() * responses.size());
            String botAnswer = responses.get(randomIndex);
            log.add("bot answer : " + botAnswer);
            System.out.println("Ответ бота : " + botAnswer);
        }
        saveLog(log);
    }

    private void readPhrases() throws IOException {
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers, UTF_8))) {
            responses = in.lines().collect(Collectors.toList());
        }
    }

    private void saveLog(List<String> log) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(path, UTF_8))) {
            for (String el : log) {
                out.write(el + '\n');
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ConsoleChat cc = new ConsoleChat("./data/chatLogs.txt", "./data/botAnswers.txt");
        cc.run();
    }
}
