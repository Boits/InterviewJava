package java8;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MyStreamsTest {

    @ParameterizedTest
    @ValueSource(strings = {"madam", "racecar", "level"})
    void testPalindrome(String candidate) {
        System.out.println(candidate);
//        assertTrue(isPalindrome(candidate));
    }

    @ParameterizedTest
    @CsvSource({
            "2,3,5",
            "10,5,15"
    })
    void testAdd(int a, int b, int expected) {
        System.out.println(a);
        System.out.println(b);
        System.out.println(expected);
//        assertEquals(expected, calculator.add(a, b));
    }

    MyStreams myStreams = new MyStreams();

    @Test
    void filter_test() {
        var src = Arrays.asList("qwe", "asd", "");
        var strings = myStreams.filterStartingWithAaa(src);

        assertEquals(0, strings.size());
        assertTrue(strings.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("datasets")
    void testFilter(List<String> input, List<String> expected) {
        assertEquals(expected, myStreams.filterStartingWithAaa(input));
    }

    static Stream<Arguments> datasets() {
        return Stream.of(
                Arguments.of(List.of("aaa2", "bbb", "aaa1"), List.of("aaa2", "aaa1")),
                Arguments.of(List.of("bbb", "ccc"), List.of()),
                Arguments.of(List.of("aaa", "aaa123"), List.of("aaa", "aaa123")),
                Arguments.of(List.of(), List.of())
        );
    }
}