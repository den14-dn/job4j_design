package ru.job4j.design.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.function.Predicate;

public class ReportEngineXML implements Report {
    private Store store;

    public ReportEngineXML(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Employees.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        Employees employees = new Employees(store.findBy(filter));
        String text = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(employees, writer);
            text = writer.getBuffer().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
}
