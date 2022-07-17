package ru.job4j.design.isp.menu;

public class SimpleApp {
    public static void main(String[] args) {
        ActionDelegate action = System.out::println;

        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", action);
        menu.add(Menu.ROOT, "Покормить собаку", action);
        menu.add("Сходить в магазин", "Купить продукты", action);
        menu.add("Купить продукты", "Купить хлеб", action);
        menu.add("Купить продукты", "Купить молоко", action);

        new SimpleMenuPrinter().print(menu);
    }
}
