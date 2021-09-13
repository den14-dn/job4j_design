package ru.job4j.collection.map;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleMapTest {
    @Test
    public void whenNonDoubles() {
        SimpleMap<String, String> sm = new SimpleMap<>();
        assertTrue(sm.put("test", "first"));
        assertFalse(sm.put("test", "second"));
        assertThat(sm.get("test"), is("first"));
    }

    @Test
    public void whenSuccessDelete() {
        SimpleMap<String, String> sm = new SimpleMap<>();
        assertTrue(sm.put("test1", ""));
        assertTrue(sm.put("test2", "second"));
        assertTrue(sm.remove("test1"));
        assertFalse(sm.remove("test3"));
        assertThat(sm.get("test2"), is("second"));
    }
}
