package ru.job4j.collection.set;

import ru.job4j.generics.SimpleArray;

import java.util.Iterator;

public class SimpleSet<T> implements Set<T> {

    private SimpleArray<T> set = new SimpleArray<>();

    @Override
    public boolean add(T value) {
        if (contains(value)) {
            return false;
        }
        set.add(value);
        return true;
    }

    @Override
    public boolean contains(T value) {
        Iterator<T> i = set.iterator();
        while (i.hasNext()) {
            T iteratorValue = i.next();
            if (iteratorValue == value || iteratorValue.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return set.size();
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
