package spliterators.example1;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.IntConsumer;

public class IntArraySpliterator extends Spliterators.AbstractIntSpliterator {

    private int startInclusive;
    private int endExclusive;
    private int[] data;

    public IntArraySpliterator(int[] data) {
        this(data, 0, data.length);
    }

    private IntArraySpliterator(int[] data, int startInclusive, int endExclusive) {
        super(endExclusive - startInclusive, Spliterator.IMMUTABLE | Spliterator.NONNULL | Spliterator.ORDERED | Spliterator.SIZED);
        this.data = data;
        this.startInclusive = startInclusive;
        this.endExclusive = endExclusive;
    }

    @Override
    public boolean tryAdvance(IntConsumer action) {
        if (startInclusive == endExclusive) {
            return false;
        }
        action.accept(data[startInclusive++]);
        return true;
    }

    @Override
    public long estimateSize() {
        return endExclusive - startInclusive;
    }

    @Override
    public OfInt trySplit() {
        int mid = startInclusive + (int)(estimateSize() / 2);
        return new IntArraySpliterator(data, startInclusive, startInclusive = mid);
    }

    @Override
    public void forEachRemaining(IntConsumer action) {
        while (startInclusive != endExclusive) {
            action.accept(data[startInclusive++]);
        }
    }
}
