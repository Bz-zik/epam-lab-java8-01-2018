package spliterators.example4;

import java.util.concurrent.TimeUnit;

public class SumTwicePerfect {

    static final int UPPER_BOUND = 10_000_000;

    public static void main(String[] args) {
        long startSimple = System.nanoTime();
        long resultSimple = sumTwicePerfectly(UPPER_BOUND);
        System.out.println("Simple result = " + resultSimple + ", time = " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startSimple));
    }

    static long sumTwicePerfectly(int upperBound) {
        return upperBound * (upperBound + 1L);
    }
}
