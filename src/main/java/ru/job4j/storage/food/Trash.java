package ru.job4j.storage.food;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {
    private final List<Food> foods = new ArrayList<>();

    @Override
    public boolean accept(Food food) {
        int percent = expirationDateExpired(food);
        return percent >= 100;
    }

    @Override
    public boolean add(Food food) {
        return accept(food) && foods.add(food);
    }

    @Override
    public List<Food> getAll() {
        return List.copyOf(foods);
    }
}
