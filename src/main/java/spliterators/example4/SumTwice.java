package spliterators.example4;

import java.util.stream.IntStream;

@SuppressWarnings("SameParameterValue")
public class SumTwice {

    public static void main(String[] args) {
        System.out.println(sumTwice(3));
        // 1: 1 + 1
        // 2: 2 + 2
        // 3: 3 + 3
        // expected: 12
    }

    private static long sumTwice(int upperBound) {
        long sum = 0;
        for (int i = 1; i <= upperBound; ++i) {
            sum += i * 2;
        }
        return sum;
    }

    private static long sumTwiceStream(int upperBound) {
        return IntStream.rangeClosed(1, upperBound)
                        .mapToLong(value -> 2 * value)
                        .sum();
    }
}
