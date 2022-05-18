package ru.job4j.storage.food;

import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class WarehouseTest {
    @Test
    public void whenFoodHasIntoWarehouse() {
        Food food = new Food("Простоквашино", LocalDate.now().plusDays(14), LocalDate.now(), 119.90f, 10);
        Store store = new Warehouse();
        store.add(food);
        assertThat(store.getAll().size(), is(1));
    }

    @Test
    public void whenIntoWarehouseIsEmpty() {
        Food food = new Food("Простоквашино", LocalDate.now(), LocalDate.now().minusDays(14), 119.90f, 10);
        Store store = new Warehouse();
        store.add(food);
        assertThat(store.getAll().size(), is(0));
    }

    @Test
    public void whenAcceptIsTrue() {
        Food food = new Food("Простоквашино", LocalDate.now().plusDays(14), LocalDate.now(), 119.90f, 10);
        Store store = new Warehouse();
        assertTrue(store.accept(food));
    }

    @Test
    public void whenAcceptIsFalse() {
        Food food = new Food("Простоквашино", LocalDate.now(), LocalDate.now().minusDays(14), 119.90f, 10);
        Store store = new Warehouse();
        assertFalse(store.accept(food));
    }
}
