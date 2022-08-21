package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    void whatsThisSphere() {
        Box box = new Box(4, 5);
        String result = box.whatsThis();
        assertThat(result).isNotNull()
                .isNotEmpty()
                .isEqualTo("Tetrahedron");
    }

    @Test
    void whatsThisUnknown() {
        Box box = new Box(1, 5);
        String result = box.whatsThis();
        assertThat(result).isNotNull()
                .isNotEmpty()
                .containsIgnoringCase("unknown")
                .isEqualTo("Unknown object");
    }

    @Test
    void getNumberOfVertices0() {
        Box box = new Box(0, 1);
        int result = box.getNumberOfVertices();
        assertThat(result).isLessThan(4)
                .isEqualTo(0);
    }

    @Test
    void getNumberOfVerticesLessThan0() {
        Box box = new Box(0, 0);
        int result = box.getNumberOfVertices();
        assertThat(result).isLessThan(0)
                .isEqualTo(-1);
    }

    @Test
    void isExistTrue() {
        Box box = new Box(8, 16);
        boolean result = box.isExist();
        assertThat(result).isTrue();
    }

    @Test
    void isExistFalse() {
        Box box = new Box(7, 16);
        boolean result = box.isExist();
        assertThat(result).isFalse();
    }

    @Test
    void getAreaOfCube() {
        Box box = new Box(8, 2);
        double result = box.getArea();
        assertThat(result).isEqualTo(24.0d, withPrecision(0.1d))
                .isGreaterThan(23.99d)
                .isLessThan(24.01d);
    }

    @Test
    void getAreaIsNot() {
        Box box = new Box(7, 2);
        double result = box.getArea();
        assertThat(result).isEqualTo(0.0d, withPrecision(0.01d));
    }
}