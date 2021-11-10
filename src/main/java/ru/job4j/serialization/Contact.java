package ru.job4j.serialization;

import java.io.*;
import java.nio.file.Files;

public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int zipCode;
    private final String phone;
    private final Photo photo;

    public Contact(int zipCode, String phone, Photo photo) {
        this.zipCode = zipCode;
        this.phone = phone;
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "zipCode=" + zipCode
                + ", phone='" + phone + '\''
                + ", photo='" + photo.getFileName() + '\''
                + '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Photo photo = new Photo("./photo1.jpg", 4542312);
        final Contact contact = new Contact(123456, "+7 (999) 123-12-12", photo);

        File tempFile = Files.createTempFile(null, null).toFile();
        try (FileOutputStream fos = new FileOutputStream(tempFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(contact);
        }

        try (FileInputStream fis = new FileInputStream(tempFile);
            ObjectInputStream ois = new ObjectInputStream(fis)) {
            final Contact contactFromFile = (Contact) ois.readObject();
            System.out.println(contactFromFile);
        }
    }
}
