package ru.job4j.kiss;

import org.hamcrest.core.Is;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertThat;

public class MaxMinTest {
    @Test
    public void whenMaxValueIs9() {
        List<Integer> list = List.of(4, 2, 9, 0, 6, 1, 8);
        Integer rst = new MaxMin().max(list, Integer::compareTo);
        assertThat(rst, Is.is(9));
    }

    @Test
    public void whenMinValueIs0() {
        List<Integer> list = List.of(4, 2, 9, 0, 6, 1, 8);
        Integer rst = new MaxMin().min(list, Integer::compareTo);
        assertThat(rst, Is.is(0));
    }
}
