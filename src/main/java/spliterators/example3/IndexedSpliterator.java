package spliterators.example3;

import spliterators.example2.IndexedPair;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;

public class IndexedSpliterator<T> extends Spliterators.AbstractSpliterator<IndexedPair<T>> {

    private final Spliterator<T> source;
    private long index;
    private long bound;

    public IndexedSpliterator(Spliterator<T> source) {
        this(source, 0, source.estimateSize());
    }

    private IndexedSpliterator(Spliterator<T> source, long from, long bound) {
        super(bound - from, check(source));
        this.source = source;
        this.bound = bound;
        this.index = from;
    }

    private static <T> int check(Spliterator<T> source) {
        if (!source.hasCharacteristics(SUBSIZED)) {
            throw new IllegalArgumentException("Non-subsized spliterator!");
        }
        return source.characteristics();
    }

    @Override
    public boolean tryAdvance(Consumer<? super IndexedPair<T>> action) {
        return source.tryAdvance(value -> action.accept(new IndexedPair<>(index++, value)));
    }

    @Override
    public void forEachRemaining(Consumer<? super IndexedPair<T>> action) {
        source.forEachRemaining(value -> action.accept(new IndexedPair<>(index++, value)));
    }

    @Override
    public Spliterator<IndexedPair<T>> trySplit() {
        if (estimateSize() == 0) {
            return null;
        }
        Spliterator<T> chunk = source.trySplit();
        return chunk != null ? new IndexedSpliterator<>(chunk, index, index += chunk.getExactSizeIfKnown())
                             : null;
    }

    @Override
    public long estimateSize() {
        return bound - index;
    }
}