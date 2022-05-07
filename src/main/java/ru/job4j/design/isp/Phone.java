package ru.job4j.design.isp;

/**
 * 1. Интерфейс содержит избыточное количество методов,
 *  которые не могут быть реализованы в полном объеме такими классами, как смартфон, таксофон.
 */
public interface Phone {
    void call();
    void takePhoto();
    void sendMessage();
    void browseInternet();
}
