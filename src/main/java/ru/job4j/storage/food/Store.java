package ru.job4j.storage.food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public interface Store {
    boolean accept(Food food);
    boolean add(Food food);
    List<Food> getAll();
    void clear();

    default int expirationDateExpired(Food food) {
        long base = ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
        long rest = ChronoUnit.DAYS.between(food.getCreateDate(), LocalDate.now());
        return (int) (rest * 100 / base);
    }
}
