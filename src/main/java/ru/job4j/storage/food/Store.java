package ru.job4j.storage.food;

public interface Store {
    boolean test(Food food);
    void add(Food food);
    void remove(Food food);
}
