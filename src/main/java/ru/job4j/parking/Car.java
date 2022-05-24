package ru.job4j.parking;

public class Car implements Auto {
    private int size;
    public static final int SIZE_CAR = 1;

    public Car() {
        this.size = Car.SIZE_CAR;
    }

    @Override
    public int getSize() {
        return size;
    }
}
