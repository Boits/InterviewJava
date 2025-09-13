package concurrency.problems;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock_2 {

    private static final Object RESOURCE_A = new Object();
    private static final Object RESOURCE_B = new Object();

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
            synchronized (RESOURCE_A) {
                System.out.println("Thread 1: Holding RESOURCE_A...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 1: Waiting for RESOURCE_B...");
                synchronized (RESOURCE_B) {
                    System.out.println("Thread 1: Acquired RESOURCE_B!");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (RESOURCE_A) {  // Изменили порядок на resource_a -> resource_b
                System.out.println("Thread 2: Holding RESOURCE_A...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 2: Waiting for RESOURCE_B...");
                synchronized (RESOURCE_B) {
                    System.out.println("Thread 2: Acquired RESOURCE_B!");
                }
            }
        });

        t1.start();
        t2.start();
    }

    /**
     * Добавить таймаут и одинаковый порядок
     */
    private static final Lock LOCK_1 = new ReentrantLock();
    private static final Lock LOCK_2 = new ReentrantLock();

    private static void resolve2() {
        Thread t1 = new Thread(() -> {
            try {
                if (LOCK_1.tryLock(1000, TimeUnit.MILLISECONDS)) {
                    try {
                        System.out.println("Thread 1: Acquired lock1");
                        Thread.sleep(50);
                        if (LOCK_2.tryLock(1000, TimeUnit.MILLISECONDS)) {
                            try {
                                System.out.println("Thread 1: Acquired lock2");
                            } finally {
                                LOCK_2.unlock();
                            }
                        } else {
                            System.out.println("Thread 1: Could not acquire lock2, releasing lock1");
                        }
                    } finally {
                        LOCK_1.unlock();
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
                if (LOCK_1.tryLock(1000, TimeUnit.MILLISECONDS)) {
                    try {
                        System.out.println("Thread 2: Acquired lock2");
                        Thread.sleep(50);
                        if (LOCK_2.tryLock(1000, TimeUnit.MILLISECONDS)) {
                            try {
                                System.out.println("Thread 2: Acquired lock1");
                            } finally {
                                LOCK_2.unlock();
                            }
                        } else {
                            System.out.println("Thread 2: Could not acquire lock1, releasing lock2");
                        }
                    } finally {
                        LOCK_1.unlock();
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
            synchronized (RESOURCE_A) {
                System.out.println("Thread 1: Holding RESOURCE_A...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 1: Waiting for RESOURCE_B...");
                synchronized (RESOURCE_B) {
                    System.out.println("Thread 1: Acquired RESOURCE_B!");
                }
            }
        };

        Runnable task2 = () -> {
            synchronized (RESOURCE_B) {
                System.out.println("Thread 2: Holding RESOURCE_B...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 2: Waiting for RESOURCE_A...");
                synchronized (RESOURCE_A) {
                    System.out.println("Thread 2: Acquired RESOURCE_A!");
                }
            }
        };

        new Thread(task1).start();
        new Thread(task2).start();
    }
}
