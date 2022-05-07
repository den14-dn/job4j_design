package ru.job4j.design.isp;

/**
 * 2. Не все автомобили перевозят пассажиров, например, грузовики, гоночные болиды.
 *  Не все автомобили заправляют топливом, есть электрокары
 */
public interface Auto {
    void beep();
    void drive();
    void carryPassengers();
    void refuel();
}
