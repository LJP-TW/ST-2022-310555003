import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PriorityQueueTest {
    static Stream<Arguments> streamProvider() {
        return Stream.of(
                Arguments.of(new int[]{2, 1, 3}, new int[]{1, 2, 3}),
                Arguments.of(new int[]{5, 4, 2, 3}, new int[]{2, 3, 4, 5}),
                Arguments.of(new int[]{0, 1, -1}, new int[]{-1, 0, 1}),
                Arguments.of(new int[]{0, 2147483647, -1}, new int[]{-1, 0, 2147483647}),
                Arguments.of(new int[]{0, 1, -2147483648}, new int[]{-2147483648, 0, 1})
        );
    }

    @ParameterizedTest(name = "#{index} - Test with Argument={0},{1}")
    @MethodSource("streamProvider")
    public void PriorityQueue_RunTest(int[] random_array, int[] correct_array) {
        PriorityQueue<Integer> test = new PriorityQueue<Integer>();
        int index = 0;
        Integer s;
        int[] result = new int[random_array.length];

        // Add random_array to PriorityQueue
        for (int i = 0; i < random_array.length; ++i) {
            test.add(random_array[i]);
        }

        // Get PriorityQueue result
        for (int i = 0; i < random_array.length; ++i) {
            result[i] = test.poll();
        }

        assertArrayEquals(correct_array, result);
    }

    @Test
    public void whenExceptionThrown_thenInitialCapacityNotGreaterThanOne() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            PriorityQueue<Integer> test = new PriorityQueue<Integer>(0);
        });
    }

    @Test
    public void whenExceptionThrown_thenOfferEisNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            Collection<Integer> collection = null;
            PriorityQueue<Integer> test = new PriorityQueue<Integer>(collection);
        });
    }

    @Test
    public void whenExceptionThrown_thenNoElementCanRemove() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            PriorityQueue<Integer> test = new PriorityQueue<Integer>();
            test.add(1);
            test.remove();
            test.remove();
        });
    }
}
