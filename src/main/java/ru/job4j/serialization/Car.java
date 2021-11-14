package ru.job4j.serialization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

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

    public Car() { }

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

    public static void main(String[] args) throws Exception {
        final Car car = new Car(true, 2300, "Mercedes G500", new Engine("V6", 6.3), "black color", "four-wheel drive");

        JAXBContext context = JAXBContext.newInstance(Car.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(car, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();

        try (StringReader reader = new StringReader(xml)) {
            Car result = (Car) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
