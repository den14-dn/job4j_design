package ru.job4j.parking;

public class Car implements Auto {
    public static final int SIZE_CAR = 1;

    @Override
    public int getSize() {
        return SIZE_CAR;
    }
}
