package ru.job4j.collection.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        Node<E> node = this.root;
        Optional<Node<E>> optional = findBy(parent);
        if (optional.isPresent()) {
            node = optional.get();
        }
        if (findBy(child).isPresent()) {
            return false;
        }
        node.children.add(new Node<>(child));
        return true;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rst = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rst = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rst;
    }
}
