package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Person {
    private final String sex;
    private final int age;
    private final boolean married;
    private final Contact contact;
    private final String[] statuses;

    public Person(String sex, int age, boolean married, Contact contact, String... statuses) {
        this.sex = sex;
        this.age = age;
        this.married = married;
        this.contact = contact;
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "Person{"
                + "sex=" + sex
                + ", age=" + age
                + ", contact=" + contact
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }

    public static void main(String[] args) {
        final Person person = new Person("male", 30, false, new Contact("999-11-22"), "worker", "married");
    }
}
