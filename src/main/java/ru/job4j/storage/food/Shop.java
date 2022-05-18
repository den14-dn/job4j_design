package ru.job4j.storage.food;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {
    private final List<Food> foods = new ArrayList<>();

    private boolean accept(Food food) {
        int percent = expirationDateExpired(food);
        return percent >= 25 && percent < 100;
    }

    @Override
    public boolean add(Food food) {
        boolean rst = false;
        if (accept(food)) {
            int percent = expirationDateExpired(food);
            if (percent >= 75 && percent < 100) {
                food.setPrice(food.getPrice() - (food.getPrice() / 100 * food.getDiscount()));
            }
            rst = foods.add(food);
        }
        return rst;
    }

    @Override
    public void remove(Food food) {
        foods.remove(food);
    }

    @Override
    public List<Food> getAll() {
        return foods;
    }
}
