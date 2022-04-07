package ru.job4j.ood;

/**
 * Класс перегружен, в нем два действия, к тому же эти действия нужно обозначить абстракцией
 */
public class OrderProcessor {
    public void payOrder(Order order) {
        sendToBank(order.getCost());
    }

    public void informCustomer(Order order, String message) {
        sendToCustomer(order.getCustomer(), message);
    }
}
