package ru.job4j.template;

import org.junit.Ignore;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@Ignore
public class GeneratorTemplatesTest {
    @Test
    public void whenTemplateMatchesParameters() {
        Generator gen = new GeneratorTemplates();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Denis");
        map.put("subject", "you");
        String expect = "I am a SpiderMan, Who are you?";
        String rst = gen.produce(template, map);
        assertThat(rst, is(expect));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNotAllParameters() {
        Generator gen = new GeneratorTemplates();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = new HashMap<>();
        map.put("subject", "you");
        String rst = gen.produce(template, map);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenParametersMoreKeys() {
        Generator gen = new GeneratorTemplates();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Denis");
        map.put("subject", "you");
        map.put("weather", "raining");
        String rst = gen.produce(template, map);
    }
}