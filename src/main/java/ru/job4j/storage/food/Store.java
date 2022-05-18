package ru.job4j.storage.food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public interface Store {
    boolean add(Food food);
    void remove(Food food);
    List<Food> getAll();

    default int expirationDateExpired(Food food) {
        long base = ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
        long rest = ChronoUnit.DAYS.between(food.getCreateDate(), LocalDate.now());
        return (int) (rest * 100 / base);
    }
}
