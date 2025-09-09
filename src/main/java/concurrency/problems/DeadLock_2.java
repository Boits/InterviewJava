package concurrency.problems;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock_2 {

    private static final Object resource_a = new Object();
    private static final Object resource_b = new Object();

    public static void res() {
//        problem();
//        resolve1();
        resolve2();
    }

    /**
     * Всегда захватывать ресурсы в одном и том же порядке
     */
    private static void resolve1() {
        Thread t1 = new Thread(() -> {
            synchronized (resource_a) {
                System.out.println("Thread 1: Holding resource_a...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 1: Waiting for resource_b...");
                synchronized (resource_b) {
                    System.out.println("Thread 1: Acquired resource_b!");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (resource_a) {  // Изменили порядок на resource_a -> resource_b
                System.out.println("Thread 2: Holding resource_a...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 2: Waiting for resource_b...");
                synchronized (resource_b) {
                    System.out.println("Thread 2: Acquired resource_b!");
                }
            }
        });

        t1.start();
        t2.start();
    }

    /**
     * Добавить таймаут
     */
    private static final Lock reentrantLock1 = new ReentrantLock();
    private static final Lock reentrantLock2 = new ReentrantLock();
    private static void resolve2() {
        Thread t1 = new Thread(() -> {
            try {
                if (reentrantLock1.tryLock(1000, TimeUnit.MILLISECONDS)) {
                    try {
                        System.out.println("Thread 1: Acquired lock1");
                        Thread.sleep(50);
                        if (reentrantLock2.tryLock(1000, TimeUnit.MILLISECONDS)) {
                            try {
                                System.out.println("Thread 1: Acquired lock2");
                            } finally {
                                reentrantLock2.unlock();
                            }
                        } else {
                            System.out.println("Thread 1: Could not acquire lock2, releasing lock1");
                        }
                    } finally {
                        reentrantLock1.unlock();
                    }
                } else {
                    System.out.println("Thread 1: Could not acquire lock1");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                if (reentrantLock2.tryLock(1000, TimeUnit.MILLISECONDS)) {
                    try {
                        System.out.println("Thread 2: Acquired lock2");
                        Thread.sleep(50);
                        if (reentrantLock1.tryLock(1000, TimeUnit.MILLISECONDS)) {
                            try {
                                System.out.println("Thread 2: Acquired lock1");
                            } finally {
                                reentrantLock1.unlock();
                            }
                        } else {
                            System.out.println("Thread 2: Could not acquire lock1, releasing lock2");
                        }
                    } finally {
                        reentrantLock2.unlock();
                    }
                } else {
                    System.out.println("Thread 2: Could not acquire lock2");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
    }

    private static void problem() {
        Runnable task1 = () -> {
            synchronized (resource_a) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (resource_b) {
                    System.out.println("Task 1 completed");
                }
            }
        };

        Runnable task2 = () -> {
            synchronized (resource_b) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (resource_a) {
                    System.out.println("Task 2 completed");
                }
            }
        };

        new Thread(task1).start();
        new Thread(task2).start();
    }
}
