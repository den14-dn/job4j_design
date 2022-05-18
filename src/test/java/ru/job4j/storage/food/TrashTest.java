package ru.job4j.storage.food;

import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrashTest {
    @Test
    public void whenFoodHasIntoTrash() {
        List<Food> foods = List.of(
                new Food("Простоквашино",
                        LocalDate.of(2021, 5, 29),
                        LocalDate.of(2021, 5, 16),
                        119.90f,
                        10)
        );

        Store store = new Trash();
        List<Store> stores = List.of(store);

        ControlQuality controlQuality = new ControlQuality(stores);

        for (Food food : foods) {
            controlQuality.distribution(food);
        }

        assertThat(store.getAll().size(), is(1));
    }

    @Test
    public void whenIntoTrashIsEmpty() {
        List<Food> foods = List.of(
                new Food("Простоквашино",
                        LocalDate.of(2042, 5, 29),
                        LocalDate.of(2022, 5, 16),
                        119.90f,
                        10)
        );

        Store store = new Trash();
        List<Store> stores = List.of(store);

        ControlQuality controlQuality = new ControlQuality(stores);

        for (Food food : foods) {
            controlQuality.distribution(food);
        }

        assertThat(store.getAll().size(), is(0));
    }
}
