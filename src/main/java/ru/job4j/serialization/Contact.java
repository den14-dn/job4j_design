package ru.job4j.serialization;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.*;
import java.nio.file.Files;

@XmlRootElement(name = "contact")
public class Contact implements Serializable {
    private String phone;

    public Contact() { }

    public Contact(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "phone='" + phone + '\''
                + '}';
    }
}
