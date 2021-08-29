package ru.job4j.collection.set;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleSetTest {
    @Test
    public void whenAddNonNull() {
        Integer n = 1;
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(n));
        assertTrue(set.contains(n));
        assertFalse(set.add(n));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenAddDuplicate() {
        Set<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        set.add(1);
        assertThat(set.size(), is(2));
    }
}
