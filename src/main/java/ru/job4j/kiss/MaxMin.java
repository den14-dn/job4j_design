package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return getValueFromList(value, (first, second) -> comparator.compare(first, second) > 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return getValueFromList(value, (first, second) -> comparator.compare(first, second) < 0);
    }

    private <T> T getValueFromList(List<T> value, BiPredicate<T, T> predicate) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        T rst = value.get(0);
        for (T el : value) {
            rst = predicate.test(rst, el) ? rst : el;
        }
        return rst;
    }
}
