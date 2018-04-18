package spliterators.example3;

import spliterators.example2.IndexedPair;
import spliterators.example2.Pair;

import java.util.function.Predicate;
import java.util.stream.Stream;

// Stream<String> a = {A B C D E...}
// Stream<String> b = {QW ER TY UI II...}
public interface AdvancedStream<T> extends Stream<T> {

    // zipped = a.zipWithIndex();
    // zipped = {(1, A) (2, B) (3, C)...}
    AdvancedStream<IndexedPair<T>> zipWithIndex();

    // zipped = a.zip(b)
    // zipped = {(A, QW) (B, ER) (C, TY)...}
    <U> AdvancedStream<Pair<T, U>> zip(Stream<U> other);

    // lessD = a.takeWhile(elem -> elem < 'D')
    // lessD = {A, B, C}
    AdvancedStream<T> takeWhile(Predicate<? super T> predicate);

    // greaterC = a.dropWhile(elem -> elem < 'C')
    // greaterC = {D, E...}
    AdvancedStream<T> dropWhile(Predicate<? super T> predicate);
}
