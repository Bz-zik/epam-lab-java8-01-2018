package spliterators.example.example1;

import org.junit.Test;
import spliterators.example1.IntArraySpliterator;

import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

import static org.junit.Assert.assertEquals;

public class IntArraySpliteratorTest {

    @Test
    public void testSumUsingSequentialSpliterator() {
        int[] data = IntStream.generate(() -> 1)
                              .limit(10_000)
                              .toArray();

        IntStream stream = StreamSupport.intStream(new IntArraySpliterator(data), false);

        assertEquals(10_000, stream.sum());
    }

    @Test
    public void testSumUsingParallelSpliterator() {
        int[] data = IntStream.generate(() -> 1)
                              .limit(10_000)
                              .toArray();

        IntStream stream = StreamSupport.intStream(new IntArraySpliterator(data), true);

        assertEquals(10_000, stream.sum());
    }

    @Test
    public void testAverage() {
        int[] data = IntStream.generate(() -> 1)
                              .limit(10_000)
                              .toArray();

        IntStream stream = StreamSupport.intStream(new IntArraySpliterator(data), false);

        assertEquals(1.0, stream.average().orElseThrow(IllegalStateException::new), 0.001);
    }

    @Test
    public void testReduce() {
        int[] data = IntStream.generate(() -> 1)
                              .limit(10_000)
                              .toArray();

        IntStream stream = StreamSupport.intStream(new IntArraySpliterator(data), false);

        assertEquals(20_000, stream.reduce(0, (result, curr) -> result += 2 * curr));
    }
}
