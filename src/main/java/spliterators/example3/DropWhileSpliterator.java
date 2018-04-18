package spliterators.example3;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class DropWhileSpliterator<T> extends Spliterators.AbstractSpliterator<T> {

    private final Spliterator<T> source;
    private final Predicate<? super T> predicate;
    private boolean dropped;

    public DropWhileSpliterator(Spliterator<T> source, Predicate<? super T> predicate) {
        super(source.estimateSize(), source.characteristics() & ~SIZED);
        this.predicate = predicate;
        this.source = source;
    }

    @Override
    public boolean tryAdvance(Consumer<? super T> action) {
        if (dropped) {
            return source.tryAdvance(action);
        }
        do {} while (!dropped && source.tryAdvance(element -> {
            if (!predicate.test(element)) {
                dropped = true;
                action.accept(element);
            }
        }));
        return dropped;
    }

    @Override
    public Spliterator<T> trySplit() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void forEachRemaining(Consumer<? super T> action) {
        while (!dropped) {
            if (!tryAdvance(action)) {
                return;
            }
        }
        source.forEachRemaining(action);
    }
}
