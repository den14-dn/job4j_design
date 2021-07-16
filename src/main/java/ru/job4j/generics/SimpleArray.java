package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterator<T> {
    private Object[] elements;
    private int size;
    private int cursor;

    public SimpleArray(int capacity) {
        if (capacity >= 0) {
            this.elements = new Object[capacity];
        } else {
            throw new IllegalArgumentException("Указан не правильный размер: " + capacity);
        }
    }

    boolean add(T element) {
        if (size < elements.length) {
            elements[size++] = element;
            return true;
        }
        return false;
    }

    void set(int index, T element) {
        Objects.checkIndex(index, size);
        elements[index] = element;
    }

    void remove(int index) {
        Objects.checkIndex(index, size);
        System.arraycopy(elements, index + 1, elements, index, size - index);
        elements[index] = null;
        size--;
    }

    T get(int index) {
        Objects.checkIndex(index, size);
        return (T) elements[index];
    }

    @Override
    public boolean hasNext() {
        return cursor < size;
    }

    @Override
    public T next() {
        if (cursor >= size) {
            throw new NoSuchElementException();
        }
        return (T) elements[cursor++];
    }
}
