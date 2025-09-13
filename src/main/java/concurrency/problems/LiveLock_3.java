package concurrency.problems;

import java.util.concurrent.locks.ReentrantLock;

public class LiveLock_3 {
    private static final ReentrantLock LOCK1 = new ReentrantLock();
    private static final ReentrantLock LOCK2 = new ReentrantLock();

    public static void res() {
        Thread t1 = new Thread(() -> {
            while (true) {
                try {
                    if (LOCK1.tryLock()) {
                        try {
                            Thread.sleep(50); // имитация работы
                            if (LOCK2.tryLock()) {
                                try {
                                    System.out.println("Поток 1: Успешно захватил оба локa");
                                    return;
                                } finally {
                                    LOCK2.unlock();
                                }
                            } else {
                                System.out.println("Поток 1: Не смог захватить LOCK2, отпускаю LOCK1");
                            }
                        } finally {
                            LOCK1.unlock();
                        }
                    }
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                try {
                    if (LOCK2.tryLock()) {
                        try {
                            Thread.sleep(50); // имитация работы
                            if (LOCK1.tryLock()) {
                                try {
                                    System.out.println("Поток 2: Успешно захватил оба локa");
                                    return;
                                } finally {
                                    LOCK1.unlock();
                                }
                            } else {
                                System.out.println("Поток 2: Не смог захватить LOCK1, отпускаю LOCK2");
                            }
                        } finally {
                            LOCK2.unlock();
                        }
                    }
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
