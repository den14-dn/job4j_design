package ru.job4j.storage.food;

import java.time.LocalDate;
import java.time.Period;

public class ControlQuality {
    public int getCondition(Food food) {
        int base = Period.between(food.getExpiryDate(), food.getCreateDate()).getDays();
        int rest = Period.between(food.getExpiryDate(), LocalDate.now()).getDays();
        return rest / base * 100;
    }
}
