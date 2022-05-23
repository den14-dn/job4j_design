package ru.job4j.parking;

public class Car implements Auto {
    private int size;

    public Car() {
        this.size = 1;
    }

    @Override
    public int getSize() {
        return size;
    }
}
