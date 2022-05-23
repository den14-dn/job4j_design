package ru.job4j.storage.food;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private final List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void distribution(Food food) {
        for (Store store : stores) {
            if (store.accept(food)) {
                store.add(food);
            }
        }
    }

    public void resort() {
        List<Food> foods = new ArrayList<>();
        for (Store store : stores) {
            foods.addAll(store.getAll());
            store.clear();
        }
        for (Food food : foods) {
            distribution(food);
        }
    }
}
