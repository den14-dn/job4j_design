package ru.job4j.storage.food;

import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TrashTest {
    @Test
    public void whenFoodHasIntoTrash() {
        Food food = new Food("Простоквашино", LocalDate.now(), LocalDate.now().minusDays(14), 119.90f, 10);
        Store store = new Trash();
        store.add(food);
        assertThat(store.getAll().size(), is(1));
    }

    @Test
    public void whenIntoTrashIsEmpty() {
        Food food = new Food("Простоквашино", LocalDate.now().plusDays(14), LocalDate.now(), 119.90f, 10);
        Store store = new Trash();
        store.add(food);
        assertThat(store.getAll().size(), is(0));
    }

    @Test
    public void whenAcceptIsTrue() {
        Food food = new Food("Простоквашино", LocalDate.now(), LocalDate.now().minusDays(14), 119.90f, 10);
        Store store = new Trash();
        assertTrue(store.accept(food));
    }

    @Test
    public void whenAcceptIsFalse() {
        Food food = new Food("Простоквашино", LocalDate.now().plusDays(14), LocalDate.now(), 119.90f, 10);
        Store store = new Trash();
        assertFalse(store.accept(food));
    }
}
