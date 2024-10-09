package co.edu.udea.salasinfo.utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class StreamUtilsTest {

    @Test
    void testZip() {
        List<String> keys = Arrays.asList("key1", "key2", "key3");
        List<Integer> values = Arrays.asList(1, 2, 3);

        Map<String, Integer> result = StreamUtils.zip(keys, values);

        Map<String, Integer> expected = new HashMap<>();
        expected.put("key1", 1);
        expected.put("key2", 2);
        expected.put("key3", 3);

        assertEquals(expected, result);
    }

    @Test
    void testMap() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        Function<Integer, String> mapper = Object::toString;

        List<String> result = StreamUtils.map(numbers, mapper);

        List<String> expected = Arrays.asList("1", "2", "3", "4");
        assertEquals(expected, result);
    }

    @Test
    void testFindAny_Found() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        Predicate<Integer> predicate = n -> n > 2;

        Integer result = StreamUtils.findAny(numbers, predicate, new RuntimeException("No element found"));

        assertNotNull(result);
        assertTrue(result > 2);
    }

    @Test
    void testMapAndReduce() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        Function<Integer, Integer> mapper = n -> n * 2;
        BinaryOperator<Integer> reducer = Integer::sum;

        Integer result = StreamUtils.mapAndReduce(numbers, 0, mapper, reducer);

        assertEquals(20, result); // 0 + (1*2) + (2*2) + (3*2) + (4*2) = 20
    }

    @Test
    void testMapAndReduce_EmptyList() {
        List<Integer> numbers = List.of();
        Function<Integer, Integer> mapper = n -> n * 2;
        BinaryOperator<Integer> reducer = Integer::sum;

        Integer result = StreamUtils.mapAndReduce(numbers, 0, mapper, reducer);

        assertEquals(0, result); // Initial value should be returned
    }
}
