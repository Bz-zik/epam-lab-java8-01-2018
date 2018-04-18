package futures.example;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import static org.junit.Assert.assertEquals;

public class Example2 {

    @Test
    public void thenAccept() {
        CompletableFuture<String> createString = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread());
            return "string";
        });

        CompletableFuture<Void> assertEquals = createString.thenAccept(string -> assertEquals("string", string));

        assertEquals.join();
    }

    @Test
    public void thenAcceptChain() {
        CompletableFuture.supplyAsync(() -> {
                            System.out.println(Thread.currentThread());
                            return "string";
                          })
                         .thenAccept(string -> assertEquals("string", string))
                         .join();
    }

    @Test
    public void exceptionally() {
        CompletableFuture.supplyAsync(() -> {
                                System.out.println(Thread.currentThread());
                                if (true) {
                                    throw new CompletionException(new IllegalArgumentException("Invalid argument!"));
                                } else {
                                    throw new CompletionException(new IllegalStateException("Invalid state!"));
                                }
                            })
                         .thenApply(o -> {
                             System.out.println("thenApply");
                             return "0";
                         })
                         .exceptionally(throwable -> {
                             Throwable cause = throwable.getCause();
                             if (cause instanceof IllegalStateException) {
                                 // ...
                             } // else if (...


                             try {
                                 throw cause;
                             } catch (IllegalArgumentException ex) {
                                 // ...
                             } catch (IllegalStateException ex) {
                                 // ...
                             } catch (Throwable throwable1) {
                                 // ... unknown exception!
                             }
                             System.out.println(throwable);
                             return "123";
                         })
                         .thenAccept(string -> assertEquals("123", string));
    }
}
