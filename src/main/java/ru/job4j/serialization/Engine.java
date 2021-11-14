package ru.job4j.serialization;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "engine")
public class Engine {
    @XmlAttribute
    private String config;
    @XmlAttribute
    private double volume;

    public Engine() { }

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
