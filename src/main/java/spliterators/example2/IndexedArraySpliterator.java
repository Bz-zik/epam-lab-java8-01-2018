package spliterators.example2;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;

// first : A1 B1 C1 D1 E1
// second: A2 B2 C2 D2 E2

// zip(first, second)  -> {A1, A2} {B1, B2} {C1, C2} ...
// zipWithIndex(first) -> {0, A1} {1, B1} {2, C1} ...

// A B C D E R T Y
// chunk1: A B C D
// chunk2: E R T Y
public class IndexedArraySpliterator<T> extends Spliterators.AbstractSpliterator<IndexedPair<T>> {

    private final T[] data;
    private long boundExclusive;
    private long index;

    public IndexedArraySpliterator(T[] data) {
        this(data, 0, data.length);
    }

    private IndexedArraySpliterator(T[] data, long startInclusive, long endExclusive) {
        super(endExclusive - startInclusive, IMMUTABLE | ORDERED | SIZED | NONNULL);
        this.boundExclusive = endExclusive;
        this.index = startInclusive;
        this.data = data;
    }

    @Override
    public boolean tryAdvance(Consumer<? super IndexedPair<T>> action) {
        if (index == boundExclusive) {
            return false;
        }
        action.accept(new IndexedPair<>(index, data[(int)index++]));
        return true;
    }

    @Override
    public void forEachRemaining(Consumer<? super IndexedPair<T>> action) {
        while (index != boundExclusive) {
            action.accept(new IndexedPair<>(index, data[(int)index++]));
        }
    }

    @Override
    public Spliterator<IndexedPair<T>> trySplit() {
        long mid = index + (int)(estimateSize() / 2);
        return new IndexedArraySpliterator<>(data, index, boundExclusive = mid);
    }

    @Override
    public long estimateSize() {
        return boundExclusive - index;
    }
}
