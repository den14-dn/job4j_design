package ru.job4j.ood;

import java.nio.file.Path;

/**
 * В методе выполняются проверки, данная валидация может через время измениться,
 *  лучше условие передавать через предикат
 */
public class OrderGenerator {
    public void process(Order order) {
        if (order.getCountItems() <= 0 || order.getCost() <= 0) {
            System.out.println("Fraud order");
            return;
        }

        order.addItems(1);
    }
}
