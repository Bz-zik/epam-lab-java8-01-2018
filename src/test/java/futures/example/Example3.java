package futures.example;

import org.junit.Test;

import java.util.concurrent.*;

public class Example3 {

    @Test
    public void name1() throws ExecutionException, InterruptedException {
        ExecutorService service = new ForkJoinPool(1);

        Future<Boolean> task1 = service.submit(() -> {
            try {
                Future<Object> task2 = service.submit(() -> {
                    throw new RuntimeException();
                });
                task2.get();
            } catch (ExecutionException | InterruptedException ex) {
                return false;
            }
            return true;
        });
        boolean result = task1.get();
        System.out.println(result);
    }
}
