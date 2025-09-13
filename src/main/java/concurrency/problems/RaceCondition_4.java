package concurrency.problems;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RaceCondition_4 {

    private static int counter = 0;

    public static void res() {
        problem();
//        resolution1(); //synchonized
//        resolution2(); //ReentrantLock
//        resolution3(); //Использование атомарных переменных
    }

    private final static AtomicInteger counterAtomic = new AtomicInteger(0);

    private static void resolution3() {
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counterAtomic.incrementAndGet();  // Увеличиваем баланс
            }
        };

        execute(task);

        System.out.println("counterAtomic = " + counterAtomic);
    }

    private static final Lock LOCK = new ReentrantLock();

    private static void resolution2() {
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                LOCK.lock();  // Захватываем блокировку
                try {
                    counter++;
                } finally {
                    LOCK.unlock();  // Освобождаем блокировку
                }
            }
        };

        execute(task);

        System.out.println("counter = " + counter);
    }

    private static final Object LOCK_OBJECT = new Object();

    private static void resolution1() {
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                synchronized (LOCK_OBJECT) {
                    counter++; //операция не атомарна (not atomic operation) (не выполняется как единое целое)
                }
            }
        };

        execute(task);

        System.out.println("counter = " + counter);
    }

    /**
     * counter++ :
     * 1) Чтение текущего значения переменной.
     * 2) Увеличение значения на единицу.
     * 3) Запись нового значения обратно.
     * <p>
     * Если эти шаги не синхронизированы между потоками, один поток может перезаписать результат работы другого.
     */
    private static void problem() {
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter++; //операция не атомарна (not atomic operation) (не выполняется как единое целое)
            }
        };

        execute(task);

        System.out.println("counter = " + counter); // Не гарантировано, что будет 2000
    }

    private static void execute(Runnable task) {
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        try {
            t1.join();  // Ожидаем завершения потоков
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
