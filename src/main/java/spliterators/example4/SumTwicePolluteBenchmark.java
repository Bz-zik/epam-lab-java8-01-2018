package spliterators.example4;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@State(Scope.Benchmark)
@SuppressWarnings("unused")
public class SumTwicePolluteBenchmark {

    @Param({"100000", "1000000", "10000000"})
    private int upperBound;

    @Param({"0", "1", "2", "3"})
    private int pollute;

    @Setup
    public void setup(Blackhole blackhole) {
        switch (pollute) {
            case 3:
                for (int i = 0; i < 1000; ++i) {
                    blackhole.consume(IntStream.range(0, 100).mapToLong(x -> x * 3).sum());
                }

            case 2:
                for (int i = 0; i < 1000; ++i) {
                    blackhole.consume(IntStream.range(0, 100).mapToLong(x -> x * 4).sum());
                }

            case 1:
                for (int i = 0; i < 1000; ++i) {
                    blackhole.consume(IntStream.range(0, 100).mapToLong(x ->   5).sum());
                }
        }
    }

    @Benchmark
    public long stream() {
        return SumTwiceNaiveBenchmark.sumTwiceStream(upperBound);
    }
}
