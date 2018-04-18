package spliterators.example.example2;

import org.junit.Test;
import spliterators.example1.IntArraySpliterator;
import spliterators.example2.IndexedArraySpliterator;
import spliterators.example2.IndexedPair;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.junit.Assert.assertEquals;

public class IndexedArraySpliteratorTest {

    @Test
    public void getElementsWithOddIndex() {
        String[] data = {"A", "B", "C", "D", "E"};

        Stream<IndexedPair<String>> stream = StreamSupport.stream(new IndexedArraySpliterator<>(data), false);
        List<String> actual = stream.filter(pair -> pair.getIndex() % 2 == 1)
                                    .map(IndexedPair::getValue2)
                                    .collect(Collectors.toList());

        assertEquals(Arrays.asList("B", "D"), actual);
    }
}
