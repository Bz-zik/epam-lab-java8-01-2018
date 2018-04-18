package spliterators.example.example1;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParallelStream {

    @Test
    public void parallelMap() {
        int[] ints = {1, 2, 3, 4, 5};
        Arrays.stream(ints)
              .parallel()
              .mapToObj(String::valueOf)
              .forEach(System.out::println);
    }

    @Test
    public void parallelSumOnIntStream() {
        int[] ints = {1, 2, 3, 4, 5};
        int result = Arrays.stream(ints)
                           .parallel()
                           .sum();
    }

    @Test
    public void parallelToList() {
        int[] ints = {1, 2, 3, 4, 5};
        List<Integer> result = Arrays.stream(ints)
                                     .parallel()
                                     .boxed()
                                     .collect(Collectors.toList());
    }
}
