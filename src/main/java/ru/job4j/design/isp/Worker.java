package ru.job4j.design.isp;

/**
 * 3. Данный набор методов характерен для рабочего-человека.
 *  Для робота-рабочего метод спать не применить.
 */
public interface Worker {
    void work();
    void sleep();
}
