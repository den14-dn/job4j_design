package ru.job4j.generics;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] elements;
    private int size;
    private int modCount;

    public SimpleArray(int capacity) {
        if (capacity >= 0) {
            this.elements = new Object[capacity];
        } else {
            throw new IllegalArgumentException("Указан не правильный размер: " + capacity);
        }
    }

    public SimpleArray() {
        this(10);
    }

    public boolean add(T element) {
        if (size < elements.length) {
            elements[size++] = element;
            modCount++;
            return true;
        }
        return false;
    }

    public void set(int index, T element) {
        Objects.checkIndex(index, size);
        elements[index] = element;
    }

    public void remove(int index) {
        Objects.checkIndex(index, size);
        System.arraycopy(elements, index + 1, elements, index, size - index);
        elements[index] = null;
        size--;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) elements[index];
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int cursor;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor < size;
            }

            @Override
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (cursor >= size) {
                    throw new NoSuchElementException();
                }
                T value = (T) elements[cursor++];
                return value;
            }
        };
    }
}
