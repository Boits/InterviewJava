package concurrency.problems;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

public class ResourceStarvation_1 {

    public static void res() {
        resolve1();
//        resolve2();
    }

    /**
     * Использование Semaphore
     */
    private static final Semaphore semaphore = new Semaphore(1, true); // Справедливый семафор
    private static void resolve1() {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Task(), "Thread " + i);
            thread.start();
        }
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            try {
                semaphore.acquire();  // Захват ресурса
                System.out.println(Thread.currentThread().getName() + " получил доступ к ресурсу.");
                Thread.sleep(2000);   // Имитируем работу с ресурсом
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + " освобождает ресурс.");
                semaphore.release();  // Освобождение ресурса
            }
        }
    }

    private static final Lock lock = new ReentrantLock();

    /**
     * Использование таймаутов, чтобы блоки не блокировались постоянно
     */
    private static void resolve2() {
        Runnable task = () -> {
            try {
                if (lock.tryLock(500, TimeUnit.MILLISECONDS)) {  // Пытаемся получить блокировку на 500 млсек
                    try {
                        System.out.println(Thread.currentThread().getName() + " acquired the lock.");
                        Thread.sleep(1000);  // Имитируем выполнение задачи
                    } finally {
                        lock.unlock();  // Обязательно освобождаем блокировку
                        System.out.println(Thread.currentThread().getName() + " released the lock.");
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + " could not acquire the lock.");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        // Запуск потоков
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
    }
}
