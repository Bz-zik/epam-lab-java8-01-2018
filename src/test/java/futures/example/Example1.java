package futures.example;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Example1 {

    @Test
    public void complete() {
        CompletableFuture<String> future = new CompletableFuture<>();

        assertFalse(future.isDone());

        future.complete("string");
        assertTrue(future.isDone());

        try {
            assertEquals("string", future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void completedFuture() {
        CompletableFuture<String> future = CompletableFuture.completedFuture("string");
        assertTrue(future.isDone());

        try {
            assertEquals("string", future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void supplyAsync() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread());
            return "string";
        });

        try {
            assertEquals("string", future.get());
            assertEquals("string", future.get());
            assertTrue(future.isDone());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void completeExceptionally() {
        CompletableFuture<String> future = new CompletableFuture<>();
        future.completeExceptionally(new IllegalArgumentException());

        assertTrue(future.isDone());
        assertTrue(future.isCompletedExceptionally());
    }



}
