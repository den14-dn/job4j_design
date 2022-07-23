package ru.job4j.design.isp.menu;

import java.util.List;
import java.util.Scanner;

public class SimpleApp {
    public static void main(String[] args) {
        ActionDelegate action = System.out::println;

        List<String> listActions = List.of(
                "1. Ввести родителя",
                "2. Ввести потомка",
                "3. Вывести меню"
        );

        Menu menu = new SimpleMenu();
        MenuPrinter menuPrinter = new SimpleMenuPrinter();
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("Введите имя предка или null: ");
            String parent = scanner.nextLine();
            System.out.println("Введите потомка: ");
            String child = scanner.nextLine();

            if (parent.toLowerCase().equals("null")) {
                parent = Menu.ROOT;
            }
            menu.add(parent, child, SimpleMenu.STUB_ACTION);

            System.out.println("Вывести меню: true/false");
            boolean rst = scanner.nextBoolean();
            scanner.nextLine();
            if (rst) {
                menuPrinter.print(menu);
                scanner.close();
                break;
            }
        }
    }
}
