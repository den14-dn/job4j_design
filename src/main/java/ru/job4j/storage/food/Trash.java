package ru.job4j.storage.food;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {
    private List<Food> foods = new ArrayList<>();
    private final ControlQuality controlQuality;

    public Trash(ControlQuality controlQuality) {
        this.controlQuality = controlQuality;
    }

    @Override
    public boolean test(Food food) {
        int percent = controlQuality.getCondition(food);
        return percent >= 100;
    }

    @Override
    public void add(Food food) {
        foods.add(food);
    }

    @Override
    public void remove(Food food) {
        foods.removeIf(o -> o.equals(food));
    }
}
