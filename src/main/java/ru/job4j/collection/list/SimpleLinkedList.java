package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
    private int size;
    private Node<E> first;
    private Node<E> last;
    private int modCount;

    public SimpleLinkedList() {
    }

    @Override
    public void add(E value) {
        Node<E> node = new Node<>(last, value, null);
        if (last == null) {
            first = node;
        } else {
            last.next = node;
        }
        last = node;
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> rst = first;
        for (int i = 0; i < index; i++) {
            rst = rst.next;
        }
        return rst.value;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int cursor;
            int expectedModCount = modCount;
            Node<E> node = first;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor < size;
            }

            @Override
            public E next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (cursor >= size) {
                    throw new NoSuchElementException();
                }
                cursor++;
                E rst = node.value;
                node = node.next;
                return rst;
            }
        };
    }

    private static class Node<E> {
        E value;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E value, Node<E> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }
}
