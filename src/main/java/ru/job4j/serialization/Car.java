package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Arrays;

public class Car {
    private final boolean fourMatic;
    private final int weight;
    private final String name;
    private final Engine engine;
    private final String[] specification;

    public Car(boolean fourMatic, int weight, String name, Engine engine, String... specification) {
        this.fourMatic = fourMatic;
        this.weight = weight;
        this.name = name;
        this.engine = engine;
        this.specification = specification;
    }

    @Override
    public String toString() {
        return "Car{"
                + "fourMatic=" + fourMatic
                + ", weight=" + weight
                + ", name=" + name
                + ", engine=" + engine
                + ", specification=" + Arrays.toString(specification)
                + '}';
    }

    public static void main(String[] args) {
        final Car car = new Car(true, 2300, "Mercedes G500", new Engine("V6", 6.3), "black color", "four-wheel drive");
    }
}
