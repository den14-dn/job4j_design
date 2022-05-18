package ru.job4j.storage.food;

import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ShopTest {
    @Test
    public void whenFoodHasIntoShop() {
        List<Food> foods = List.of(
                new Food("Простоквашино",
                        LocalDate.now().plusDays(7),
                        LocalDate.now().minusDays(7),
                        119.90f,
                        10));

        Store store = new Shop();
        List<Store> stores = List.of(store);

        ControlQuality controlQuality = new ControlQuality(stores);

        for (Food food : foods) {
            controlQuality.distribution(food);
        }

        assertThat(store.getAll().size(), is(1));
    }

    @Test
    public void whenIntoShopIsEmpty() {
        List<Food> foods = List.of(
                new Food("Простоквашино",
                        LocalDate.now().minusDays(7),
                        LocalDate.now().minusDays(14),
                        119.90f,
                        10));

        Store store = new Shop();
        List<Store> stores = List.of(store);

        ControlQuality controlQuality = new ControlQuality(stores);

        for (Food food : foods) {
            controlQuality.distribution(food);
        }

        assertThat(store.getAll().size(), is(0));
    }

    @Test
    public void whenItemIntoShopAndIsDiscount() {
        float startPrice = 119.90f;
        List<Food> foods = List.of(
                new Food("Простоквашино",
                        LocalDate.now().plusDays(2),
                        LocalDate.now().minusDays(7),
                        startPrice,
                        10));

        Store store = new Shop();
        List<Store> stores = List.of(store);

        ControlQuality controlQuality = new ControlQuality(stores);

        for (Food food : foods) {
            controlQuality.distribution(food);
        }

        float finishPrice = store.getAll().stream().findFirst().get().getPrice();
        assertTrue(finishPrice < startPrice);
    }
}
