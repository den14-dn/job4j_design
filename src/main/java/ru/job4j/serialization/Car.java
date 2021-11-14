package ru.job4j.serialization;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {
    @XmlAttribute
    private boolean fourMatic;
    @XmlAttribute
    private int weight;
    @XmlAttribute
    private String name;
    private Engine engine;
    @XmlElementWrapper(name = "specifications")
    @XmlElement(name = "specification")
    private String[] specification;

    public Car() {

    }

    public Car(boolean fourMatic, int weight, String name, Engine engine, String... specification) {
        this.fourMatic = fourMatic;
        this.weight = weight;
        this.name = name;
        this.engine = engine;
        this.specification = specification;
    }

    public boolean isFourMatic() {
        return fourMatic;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public Engine getEngine() {
        return engine;
    }

    public String[] getSpecification() {
        return specification;
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

    public static void main(String[] args) throws Exception {

        JSONObject jsonEngine = new JSONObject("{\"config\":\"V8\", \"volume\":4.5}");

        List<String> list = new ArrayList<>();
        list.add("red color");
        list.add("sedan");
        JSONArray jsonSpecifications = new JSONArray(list);

        final Car car = new Car(true, 2300, "Mercedes G500", new Engine("V6", 6.3), "black color", "four-wheel drive");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fourMatic", car.isFourMatic());
        jsonObject.put("weight", car.getWeight());
        jsonObject.put("name", car.getName());
        jsonObject.put("engine", jsonEngine);
        jsonObject.put("specification", jsonSpecifications);

        System.out.println(jsonObject.toString());

        System.out.println(new JSONObject(car).toString());
    }
}
