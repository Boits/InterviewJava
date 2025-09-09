package concurrency;

import java.util.concurrent.*;

public class CloseThread {

    public static void res() {
        resolution1(); //interrupt()
//        resolution2(); //ExecutorService и shutdown()
    }

    private static void resolution2() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Runnable task1 = () -> System.out.println("Задача 1 выполняется");
        Runnable task2 = () -> System.out.println("Задача 2 выполняется");

        executorService.submit(task1);
        executorService.submit(task2);

        // Завершаем работу пула потоков
        executorService.shutdown();  // Не принимает новые задачи, но завершает текущие

        try {
            if (executorService.awaitTermination(1, TimeUnit.MINUTES)) {
                System.out.println("Все задачи завершены");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void resolution1() {
        Thread thread = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("Поток выполняется");
                    System.out.println("isInterrupted() = " + Thread.currentThread().isInterrupted()); //false
                    Thread.sleep(1000); // Имитируем работу
                }
            } catch (InterruptedException e) {
                System.out.println("Поток прерван");
                System.out.println("isInterrupted = " + Thread.currentThread().isInterrupted()); //false
                Thread.currentThread().interrupt(); // Восстановление прерывания
                System.out.println("isInterrupted() = " + Thread.currentThread().isInterrupted()); //true
            }
        });

        thread.start(); // Запуск потока

        try {
            Thread.sleep(3000); // Ждем 3 секунды
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt(); // Прерывание потока
    }
}
