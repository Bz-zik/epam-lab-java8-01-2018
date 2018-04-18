package spliterators.example4;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@SuppressWarnings("SameParameterValue")
public class SumTwiceNaiveBenchmark {

    static final int UPPER_BOUND = 10_000_000;

    public static void main(String[] args) {
        long startSimple = System.nanoTime();
        long resultSimple = sumTwice(UPPER_BOUND);
        System.out.println("Simple result = " + resultSimple + ", time = " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startSimple));

        long startStream = System.nanoTime();
        long resultStream = sumTwiceStream(UPPER_BOUND);
        System.out.println("Stream result = " + resultStream + ", time = " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startStream));
    }

    static long sumTwice(int upperBound) {
        long sum = 0;
        for (int i = 1; i <= upperBound; ++i) {
            sum += i * 2;
        }
        return sum;
    }

    static long sumTwiceStream(int upperBound) {
        return IntStream.rangeClosed(1, upperBound)
                        .mapToLong(x -> x * 2)
                        .sum();
    }
}
