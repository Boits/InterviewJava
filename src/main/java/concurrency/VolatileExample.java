package concurrency;

public class VolatileExample {
    private static volatile String sharedString = "Initial";

    public static void res() {
        resolution1();
//        resolution2();
    }

    private static void resolution2() {
        Runnable task1 = () -> {
            sharedString = "Updated by Thread 1";
        };

        Runnable task2 = () -> {
            sharedString = "Updated by Thread 2";
        };

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Последнее значение sharedString: " + sharedString);  // Updated by Thread 2
    }

    private static volatile boolean running = true;  // volatile флаг
    private static void resolution1() {
        Runnable task = () -> {
            while (running) {
                System.out.println("Выполняем работу");
            }
            System.out.println("Работа завершена.");
        };

        Thread thread = new Thread(task);
        thread.start();

        // Ожидание
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        running = false;  // Изменение флага для завершения работы
    }
}
