package ru.job4j.storage.food;

import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ControlQualityTest {
    @Test
    public void whenFoodHasIntoTrash() {
        Food food = new Food("Простоквашино", LocalDate.now(), LocalDate.now().minusDays(14), 119.90f, 10);
        Store store = new Trash();
        ControlQuality controlQuality = new ControlQuality(List.of(store));
        controlQuality.distribution(food);
        assertThat(store.getAll().size(), is(1));
    }

    @Test
    public void whenIntoTrashIsEmpty() {
        Food food = new Food("Простоквашино", LocalDate.now().plusDays(14), LocalDate.now(), 119.90f, 10);
        Store store = new Trash();
        ControlQuality controlQuality = new ControlQuality(List.of(store));
        controlQuality.distribution(food);
        assertThat(store.getAll().size(), is(0));
    }
}
