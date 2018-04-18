package spliterators.example4;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@State(Scope.Benchmark)
public class SumTwiceParametrizedBenchmark {

    @Param({"100000", "1000000", "10000000"})
    private int upperBound;

    @Benchmark
    public long stream() {
        return SumTwiceNaiveBenchmark.sumTwiceStream(upperBound);
    }

    @Benchmark
    public long simple() {
        return SumTwiceNaiveBenchmark.sumTwice(upperBound);
    }
}
