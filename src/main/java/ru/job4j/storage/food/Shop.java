package ru.job4j.storage.food;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {
    private final List<Food> foods = new ArrayList<>();
    private final ControlQuality controlQuality;

    public Shop(ControlQuality controlQuality) {
        this.controlQuality = controlQuality;
    }

    @Override
    public boolean test(Food food) {
        int percent = controlQuality.getCondition(food);
        return percent >= 25 && percent < 100;
    }

    @Override
    public void add(Food food) {
        int percent = controlQuality.getCondition(food);
        if (percent >= 25 && percent < 75) {
            food.setDiscount(10);
        }
        foods.add(food);
    }

    @Override
    public void remove(Food food) {
        foods.removeIf(o -> o.equals(food));
    }
}
