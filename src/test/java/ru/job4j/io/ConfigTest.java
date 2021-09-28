package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Denis"));
        assertThat(config.value("surname"), is("Shubko"));
        assertThat(config.value("learnStage"), is(Matchers.nullValue()));
    }

    @Test
    public void whenPairWithCommentAndSpace() {
        String path = "./data/pair_with_comment_and_space.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Denis"));
        assertThat(config.value("surname"), is("Shubko"));
        assertThat(config.value("learnStage"), is(Matchers.nullValue()));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenPairWithoutKey() {
        String path = "./data/pair_without_key.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test
    public void whenPairWithoutValue() {
        String path = "./data/pair_without_value.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("surname"), is(""));
    }
}
