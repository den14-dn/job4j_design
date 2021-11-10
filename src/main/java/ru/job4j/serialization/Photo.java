package ru.job4j.serialization;

import java.io.Serializable;

public class Photo implements Serializable {
    private String fileName;
    private int size;

    Photo(String fileName, int size) {
        this.fileName = fileName;
        this.size = size;
    }

    String getFileName() {
        return fileName;
    }
}
