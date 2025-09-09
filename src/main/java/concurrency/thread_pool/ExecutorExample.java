package concurrency.thread_pool;

import java.util.concurrent.*;

public class ExecutorExample {

    public static void res() {
        createExecutor();
//        createExecutorWithFuture();
//        cancelTaskWithFuture();
//        interruptExecutor();
    }

    private static void createExecutor() {
        ExecutorService executor = Executors.newFixedThreadPool(2); // Создание пула из двух потоков

        Runnable task = () -> {
            System.out.println("Поток выполняется в " + Thread.currentThread().getName());
        };

        executor.submit(task); // Запуск задачи
        executor.submit(task);

        executor.shutdown(); // Завершение работы пула
    }


    private static void createExecutorWithFuture() {
        Callable<Integer> task = () -> {
            Thread.sleep(2000);
            return 42;  // Возвращаем результат
        };

        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Integer> future = executor.submit(task);  // Отправляем задачу на выполнение

        try {
            if (future.isDone()) {
                System.out.println("Задача завершена");
            } else {
                System.out.println("Задача ещё выполняется");
            }

            System.out.println("Результат выполнения: " + future.get());  // Получаем результат
            //get() - блокирует поток, пока результат не будет готов.

            if (future.isDone()) {
                System.out.println("Задача завершена");
            } else {
                System.out.println("Задача ещё выполняется");
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }

    private static void cancelTaskWithFuture() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<Integer> task = () -> {
            Thread.sleep(5000);  // Долгая задача
            return 42;
        };

        Future<Integer> future = executor.submit(task);

        try {
            Thread.sleep(1000);  // Ждём 1 секунду
            System.out.println("Попробуем отменить задачу...");
            boolean cancelled = future.cancel(true);  // Попробуем отменить задачу
            System.out.println("Задача отменена: " + cancelled);

            if (!future.isCancelled()) {
                Integer result = future.get();  // Попытаемся получить результат, если задача не была отменена
                System.out.println("Результат задачи: " + result);
            } else {
                System.out.println("Задача была отменена.");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    private static void interruptExecutor() {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Runnable task = () -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Поток выполняется");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Поток прерван");
                    Thread.currentThread().interrupt(); // Восстановление прерывания
                }
            }
            System.out.println("Поток завершен");
        };

        executor.submit(task);// Запуск задачи

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdownNow(); // Вызывает interrupt() для работаюшего потока
    }

}
