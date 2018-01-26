package lambda.part3.exercise;

import lambda.data.Employee;
import lambda.data.Person;
import lambda.part3.example.Example1;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

@SuppressWarnings({"unused", "ConstantConditions"})
public class Exercise3 {

    private static class LazyMapHelper<T, R> {

        private List<T> source;
        private Function<T, R> mapping;

        private LazyMapHelper(List<T> source, Function<T, R> mapping) {
            this.source = source;
            this.mapping = mapping;
        }

        public static <T> LazyMapHelper<T, T> from(List<T> list) {
            return new LazyMapHelper<T, T>(list, v -> v);
        }

        public List<R> force() {
            List<R> result = new ArrayList<>();
            source.forEach(v -> result.add(mapping.apply(v)));
            return result;
        }

        public <R2> LazyMapHelper<T, R2> map(Function<R, R2> mapping) {
            return new LazyMapHelper<T, R2>(source, this.mapping.andThen(mapping));
        }
    }

    @Test
    public void mapEmployeesToLengthOfTheirFullNamesUsingLazyMapHelper() {
        List<Employee> employees = Example1.getEmployees();

        List<Integer> lengths = LazyMapHelper.from(employees)
                .map(Employee::getPerson)
                .map(Person::getFullName)
                .map(String::length)
                .force();
        assertEquals(Arrays.asList(14, 19, 14, 15, 14, 16), lengths);
    }
}
