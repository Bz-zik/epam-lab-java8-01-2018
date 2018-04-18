package spliterators.example4;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
public class SumTwiceFixedBenchmark {

    @Benchmark
    public long stream() {
        return SumTwiceNaiveBenchmark.sumTwiceStream(SumTwiceNaiveBenchmark.UPPER_BOUND);
    }

    @Benchmark
    public long simple() {
        return SumTwiceNaiveBenchmark.sumTwice(SumTwiceNaiveBenchmark.UPPER_BOUND);
    }
}
