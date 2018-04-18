package spliterators.example.example1;

import java.util.Arrays;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Example1 {


    // A B C D E R T Y U
    public static void main(String[] args) {
//        Character[] data = {'A', 'E', 'C', 'B', 'D'};
//        Stream<Character> stream = Arrays.stream(data);
//
//        int sum = stream.parallel()
//                        .filter(character -> character > 'B')
//                        .sorted()
//                        .mapToInt(character -> (int) character)
//                        .sum();

        int[] ints = {1, 2, 3, 4, 6, 10, 2, -1};

        Spliterator.OfInt spliterator = Spliterators.spliterator(ints, 0);

        IntStream intStream = StreamSupport.intStream(spliterator, true);

        intStream.forEachOrdered(value -> {
            System.out.println(Thread.currentThread().getId());
        });

    }
}
