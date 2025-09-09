package concurrency;

public class ThreadLocalExample {

    // Создаем ThreadLocal переменную, которая будет хранить отдельное значение для каждого потока
    private static final ThreadLocal<Integer> threadLocalValue = ThreadLocal.withInitial(() -> 0);

    public static void res() {
        // Поток 1
        Thread thread1 = new Thread(() -> {
            threadLocalValue.set(100);
            System.out.println("Thread 1 value: " + threadLocalValue.get());  // 100
        });

        // Поток 2
        Thread thread2 = new Thread(() -> {
            threadLocalValue.set(200);
            System.out.println("Thread 2 value: " + threadLocalValue.get());  // 200
        });

        thread1.start();
        thread2.start();
    }
}
