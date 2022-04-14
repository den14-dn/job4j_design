package ru.job4j.design.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.function.Predicate;

public class ReportEngineJSON implements Report {
    private Store store;

    public ReportEngineJSON(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Employees employees = new Employees(store.findBy(filter));
        return gson.toJson(employees);
    }
}
