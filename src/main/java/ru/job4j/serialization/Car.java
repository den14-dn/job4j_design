package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Arrays;

public class Car {
    private final boolean fourMatic;
    private final int weight;
    private final String name;
    private final Engine engine;
    private final String[] specifications;

    public Car(boolean fourMatic, int weight, String name, Engine engine, String... specifications) {
        this.fourMatic = fourMatic;
        this.weight = weight;
        this.name = name;
        this.engine = engine;
        this.specifications = specifications;
    }

    @Override
    public String toString() {
        return "Car{"
                + "fourMatic=" + fourMatic
                + ", weight=" + weight
                + ", name=" + name
                + ", engine=" + engine
                + ", specifications=" + Arrays.toString(specifications)
                + '}';
    }

    public static void main(String[] args) {
        final Car car = new Car(true, 2300, "Mercedes G500", new Engine("V6", 6.3), "black color", "four-wheel drive");

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car));

        final String carJson =
                "{"
                        + "\"fourMatic\":false,"
                        + "\"weight\":1800,"
                        + "\"name\":\"BMW 520M\","
                        + "\"engine\":"
                        + "{"
                        + "\"config\":\"R4\","
                        + "\"volume\":2.0"
                        + "},"
                        + "\"specifications\":"
                        + "[\"sedan\",\"grey\"]"
                        + "}";
        final Car carMod = gson.fromJson(carJson, Car.class);
        System.out.println(carMod);
    }
}
