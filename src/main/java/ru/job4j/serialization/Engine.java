package ru.job4j.serialization;

public class Engine {
    private final String config;
    private final double volume;

    public Engine(String config, double volume) {
        this.config = config;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "config=" + config
                + "volume=" + volume
                + '}';
    }
}
