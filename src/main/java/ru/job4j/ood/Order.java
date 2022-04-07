package ru.job4j.ood;

/**
 * Класс перегружен по функциональности, он является моделью,
 *  и в нем реализована логика процесса и выполняет сохранение в память.
 */
public class Order {
    private int countItems;
    private String customer;
    private float cost;

    public int getCountItems() {
        return countItems;
    }

    public String getCustomer() {
        return customer;
    }

    public float getCost() {
        return cost;
    }

    public Order(int countItems, String customer, float cost) {
        this.countItems = countItems;
        this.customer = customer;
        this.cost = cost;
    }

    public void addItems(int cout) {
        countItems += cout;
    }
}
