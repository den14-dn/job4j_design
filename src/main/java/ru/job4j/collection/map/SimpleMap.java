package ru.job4j.collection.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        int index = indexFor(hash(key.hashCode()));
        MapEntry e = table[index];
        if (e == null) {
            ++modCount;
            if (++count > (capacity * LOAD_FACTOR)) {
                expand();
                index = indexFor(hash(key.hashCode()));
            }
            table[index] = new MapEntry<>(key, value);
            return true;
        }
        return false;
    }

    @Override
    public V get(K key) {
        int index = indexFor(hash(key.hashCode()));
        MapEntry<K, V> e = table[index];
        if (e == null || !e.key.equals(key)) {
            return null;
        }
        return e.value;
    }

    @Override
    public boolean remove(K key) {
        int index = indexFor(hash(key.hashCode()));
        MapEntry e = table[index];
        if (e == null || !e.key.equals(key)) {
            return false;
        }
        table[index] = null;
        count--;
        modCount++;
        return true;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int expectedModCount = modCount;
            int cursor;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (cursor < table.length) {
                    if (table[cursor] != null) {
                        return true;
                    }
                    ++cursor;
                }
                return false;
            }

            @Override
            public K next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (cursor >= table.length) {
                    throw new NoSuchElementException();
                }
                return table[cursor].key;
            }
        };
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return (table.length - 1) & hash;
    }

    private void expand() {
        MapEntry<K, V>[] oldTable = table;
        int oldCapacity = ((oldTable == null) ? 0 : table.length);
        capacity = oldCapacity * 2;
        table = new MapEntry[capacity];
        if (oldTable != null) {
            for (int i = 0; i < oldCapacity; i++) {
                MapEntry<K, V> e = oldTable[i];
                if (e != null) {
                    int index = indexFor(hash(e.key.hashCode()));
                    table[index] = e;
                }
            }
        }
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
