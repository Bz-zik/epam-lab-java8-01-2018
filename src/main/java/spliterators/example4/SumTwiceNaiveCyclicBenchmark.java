package spliterators.example4;

import java.util.stream.IntStream;

public class SumTwiceNaiveCyclicBenchmark {

    private static final int UPPER_BOUND = 10_000_000;

    public static void main(String[] args) {
        // -XX:Tier4BackEdgeThreshold=40000
        for (int i = 0; i < 6000; ++i) {
            long start = System.nanoTime();
            long result = sumTwiceStream(UPPER_BOUND);
            System.out.println("Iteration = " + i + ", result = " + result + ", time = " + (System.nanoTime() - start) / 1_000_000d);
        }
    }

    //-XX:Tier4InvocationThreshold=5000
    static long sumTwiceStream(int upperBound) {
        return IntStream.rangeClosed(1, upperBound)
                        .mapToLong(x -> x * 2)
                        .sum();
    }
}
