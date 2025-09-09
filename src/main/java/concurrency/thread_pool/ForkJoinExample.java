package concurrency.thread_pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinExample {

    public static void res() {
        createTreadWithForkJoinPool();
    }

    private static void createTreadWithForkJoinPool() {
        try {
            ForkJoinPool pool = new ForkJoinPool();
            FibonacciTask task = new FibonacciTask(10);
            int result = pool.invoke(task); //запуск задачи и получение результата
            System.out.println("Результат: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    RecursiveAction: Используется, когда задача не возвращает результат
    private static class FibonacciTask extends RecursiveTask<Integer> {
        private final int n;

        FibonacciTask(int n) {
            this.n = n;
        }

        @Override
        protected Integer compute() {
            if (n <= 1) {
                return n;
            }
            FibonacciTask f1 = new FibonacciTask(n - 1);
            FibonacciTask f2 = new FibonacciTask(n - 2);
            f1.fork();
            return f2.compute() + f1.join();
        }
    }
}
