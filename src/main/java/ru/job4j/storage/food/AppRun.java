package ru.job4j.storage.food;

import java.time.LocalDate;
import java.util.List;

public class AppRun {
    public static void main(String[] args) {
        Food milk = new Food("Простоквашино", LocalDate.of(2022, 5, 29), LocalDate.of(2022, 5, 16), 119.90f);
        Food conserve = new Food("Сайра", LocalDate.of(2023, 2, 10), LocalDate.of(2022, 2, 9), 321.60f);
        Food pasta = new Food("Щебекино", LocalDate.of(2022, 12, 12), LocalDate.of(2022, 4, 11), 156.30f);
        Food oil = new Food("Солнышко", LocalDate.of(2022, 10, 11), LocalDate.of(2022, 4, 10), 239.70f);

        List<Food> foods = List.of(milk, conserve, pasta, oil);

        ControlQuality controlQuality = new ControlQuality();

        List<Store> stores = List.of(new Warehouse(controlQuality), new Shop(controlQuality), new Trash(controlQuality));

        for (Food food : foods) {
            for (Store store : stores) {
                if (store.test(food)) {
                    store.add(food);
                } else {
                    store.remove(food);
                }
            }
        }
    }
}
