package ru.job4j.design.ocp;

import java.util.ArrayList;

// 1. класс должен зависеть от абстракции
public class ToyotaCar {
    // 2. поля должны представлять тип абстракции
    private GasolineEngine engine;

    // 3. входные параметры и возвращаемый тип должны быть реализованы через асбтракции
    public ArrayList<Integer> getGearsToSwitch(Integer currentTransmission) {
        return null;
    }
}
