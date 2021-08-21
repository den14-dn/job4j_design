package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {
    private T[] container;
    private int modCount;
    private int size;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    public SimpleArrayList() {
        this(10);
    }

    @Override
    public void add(T model) {
        container[size++] = model;
        if (container.length == size) {
            container = Arrays.copyOf(container, container.length * 2);
        }
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T value = container[index];
        container[index] = newValue;
        return value;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T value = container[index];
        System.arraycopy(container, index + 1, container, index, size - index - 1);
        container[--size] = null;
        modCount++;
        return value;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int expectedModCount = modCount;
            int cursor;

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
                return container[cursor++];
            }
        };
    }
}
