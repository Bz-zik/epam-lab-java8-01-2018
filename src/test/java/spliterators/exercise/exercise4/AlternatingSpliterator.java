package spliterators.exercise.exercise4;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class AlternatingSpliterator<T> extends Spliterators.AbstractSpliterator<T> {

    private AlternatingSpliterator(long estimatedSize, int characteristics, Spliterator<T> first, Spliterator<T> second) {
        super(0, 0);
        throw new UnsupportedOperationException();
    }

    public static <T> AlternatingSpliterator<T> combine(Stream<T> firstStream, Stream<T> secondStream) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean tryAdvance(Consumer<? super T> action) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void forEachRemaining(Consumer<? super T> action) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getExactSizeIfKnown() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasCharacteristics(int characteristics) {
        throw new UnsupportedOperationException();
    }
}
