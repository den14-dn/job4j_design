package ru.job4j.storage.food;

import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ShopTest {
    @Test
    public void whenFoodHasIntoShop() {
        Food food = new Food("Простоквашино", LocalDate.now().plusDays(7), LocalDate.now().minusDays(7), 119.90f, 10);
        Store store = new Shop();
        store.add(food);
        assertThat(store.getAll().size(), is(1));
    }

    @Test
    public void whenIntoShopIsEmpty() {
        Food food = new Food("Простоквашино", LocalDate.now().minusDays(7), LocalDate.now().minusDays(14), 119.90f, 10);
        Store store = new Shop();
        store.add(food);
        assertThat(store.getAll().size(), is(0));
    }

    @Test
    public void whenAcceptIsTrue() {
        Food food = new Food("Простоквашино", LocalDate.now().plusDays(7), LocalDate.now().minusDays(7), 119.90f, 10);
        Store store = new Shop();
        assertTrue(store.accept(food));
    }

    @Test
    public void whenAcceptIsFalse() {
        Food food = new Food("Простоквашино", LocalDate.now().minusDays(7), LocalDate.now().minusDays(14), 119.90f, 10);
        Store store = new Shop();
        assertFalse(store.accept(food));
    }

    @Test
    public void whenItemIntoShopAndIsDiscount() {
        float startPrice = 119.90f;
        Food food = new Food("Простоквашино", LocalDate.now().plusDays(2), LocalDate.now().minusDays(7), startPrice, 10);
        Store store = new Shop();
        store.add(food);
        float finishPrice = store.getAll().stream().findFirst().get().getPrice();
        assertTrue(finishPrice < startPrice);
    }
}
