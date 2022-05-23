package ru.job4j.storage.food;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {
    private final List<Food> foods = new ArrayList<>();

    @Override
    public boolean accept(Food food) {
        return expirationDateExpired(food) < 25;
    }

    @Override
    public boolean add(Food food) {
        return accept(food) && foods.add(food);
    }

    @Override
    public List<Food> getAll() {
        return List.copyOf(foods);
    }

    @Override
    public void clear() {
        foods.clear();
    }
}
