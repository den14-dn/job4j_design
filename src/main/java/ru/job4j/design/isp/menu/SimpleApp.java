package ru.job4j.design.isp.menu;

import java.util.Scanner;

public class SimpleApp {
    public static final int ADD_MENU = 1;
    public static final int PRINT_MENU = 2;
    public static final String CONTEXT = """
            1. Добавить пункт меню
            2. Вывести меню 
            3. Завершить
            """;
    public static final String NAME_PARENT = "Введите имя предка или null: ";
    public static final String NAME_CHILD = "Введите имя потомка: ";

    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        MenuPrinter menuPrinter = new SimpleMenuPrinter();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(System.lineSeparator());
            System.out.println(CONTEXT);

            int command = scanner.nextInt();
            scanner.nextLine();

            if (command == ADD_MENU) {
                System.out.println(NAME_PARENT);
                String parent = scanner.nextLine();
                System.out.println(NAME_CHILD);
                String child = scanner.nextLine();

                if (parent.toLowerCase().equals("null")) {
                    parent = Menu.ROOT;
                }
                menu.add(parent, child, SimpleMenu.STUB_ACTION);
            } else if (command == PRINT_MENU) {
                menuPrinter.print(menu);
            } else {
                scanner.close();
                break;
            }
        }
    }
}
