package concurrency.thread_pool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureExample {

    public static void res() {
//        thenCombine();
//        thenCompose();
//        allOf();
//        anyOf();
//        exceptionally();
//        handle();
//        cancel();
        completableFutureWithExecutor();
    }

    private static void completableFutureWithExecutor() {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("Задача выполняется в пользовательском Executor");
        }, executor);

//        // Ожидание завершения задачи
        future.join();

        // Завершение работы ExecutorService
        executor.shutdown();
    }

    private static void cancel() {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(5000); // Имитация долгой задачи
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        future.cancel(true); // Отмена задачи
    }

    private static void handle() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            if (true) throw new RuntimeException("Ошибка!");
            return 42;
        }).handle((result, ex) -> {
            if (ex != null) {
                System.out.println("Произошла ошибка: " + ex.getMessage());
                return 0;
            }
            return result;
        });
        future.thenAccept(result -> System.out.println("Результат: " + result));
    }

    private static void exceptionally() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            if (true) throw new RuntimeException("Ошибка!");
            return 42;
        }).exceptionally(ex -> {
            System.out.println("Произошла ошибка: " + ex.getMessage());
            return 0;
        });
        future.thenAccept(result -> System.out.println("Результат: " + result));
    }

    private static void anyOf() {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 5);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 10);

        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(future1, future2);
        anyOf.thenAccept(result -> System.out.println("Первая завершённая задача: " + result));
    }

    private static void allOf() {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 5);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 10);

        CompletableFuture<Void> allOf = CompletableFuture.allOf(future1, future2);
        allOf.thenRun(() -> System.out.println("Все задачи завершены"));
    }

    private static void thenCompose() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 5)
                .thenCompose(result -> CompletableFuture.supplyAsync(() -> result * 2));
        future.thenAccept(result -> System.out.println("Результат: " + result));
    }

    private static void thenCombine() {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 5);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 10);

        CompletableFuture<Integer> combinedFuture = future1.thenCombine(future2, (x, y) -> x + y);
        combinedFuture.thenAccept(result -> System.out.println("Сумма: " + result));
    }

}
