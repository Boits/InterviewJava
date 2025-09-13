package concurrency;

/**
 * Before start producer: producerThread NEW
 * Before start consumer: consumerThread NEW
 * After start producer: producerThread RUNNABLE
 * After start consumer: consumerThread RUNNABLE
 * Before lock in consumer: consumerThread RUNNABLE
 * Before join producer: producerThread RUNNABLE
 * Before lock in producer: producerThread RUNNABLE
 * Before join producer: consumerThread TIMED_WAITING
 * Synchronized in producer: producerThread RUNNABLE
 * Producer is waiting...
 * After sleep in consumer: consumerThread RUNNABLE
 * Synchronized in consumer: consumerThread RUNNABLE
 * Consumer is notifying...
 * After notify in consumerconsumerThread RUNNABLE
 * After wait in producer: producerThread RUNNABLE
 * Producer resumed
 * After join producer: producerThread TERMINATED
 * After join producer: consumerThread TERMINATED
 * After join consumer: producerThread TERMINATED
 * After join consumer: consumerThread TERMINATED
 */
public class WaitNotifyExample {
    private final Object lock = new Object();

    public static void res() {
        WaitNotifyExample example = new WaitNotifyExample();

        Thread producerThread = new Thread(() -> {
            try {
                example.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        producerThread.setName("producerThread");

        Thread consumerThread = new Thread(() -> {
            try {
                example.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        consumerThread.setName("consumerThread");

        System.out.println("Before start producer: " + producerThread.getName() + " " + producerThread.getState());
        System.out.println("Before start consumer: " + consumerThread.getName() + " " + consumerThread.getState());

        producerThread.start();
        consumerThread.start();

        System.out.println("After start producer: " + producerThread.getName() + " " + producerThread.getState());
        System.out.println("After start consumer: " + consumerThread.getName() + " " + consumerThread.getState());

        try {
            System.out.println("Before join producer: " + producerThread.getName() + " " + producerThread.getState());
            System.out.println("Before join producer: " + consumerThread.getName() + " " + consumerThread.getState());

            producerThread.join();

            System.out.println("After join producer: " + producerThread.getName() + " " + producerThread.getState());
            System.out.println("After join producer: " + consumerThread.getName() + " " + consumerThread.getState());

            consumerThread.join();

            System.out.println("After join consumer: " + producerThread.getName() + " " + producerThread.getState());
            System.out.println("After join consumer: " + consumerThread.getName() + " " + consumerThread.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void producer() throws InterruptedException {
        var name = Thread.currentThread().getName();
        var state = Thread.currentThread().getState();

        System.out.println("Before lock in producer: " + name + " " + state);
        synchronized (lock) {
            System.out.println("Synchronized in producer: " + name + " " + state);
            System.out.println("Producer is waiting...");
            lock.wait();  // Поток блокируется до вызова notify()
            System.out.println("After wait in producer: " + name + " " + state);
            System.out.println("Producer resumed");
        }
    }

    private void consumer() throws InterruptedException {
        var name = Thread.currentThread().getName();
        var state = Thread.currentThread().getState();

        System.out.println("Before lock in consumer: " + name + " " + state);

        Thread.sleep(1000);

        System.out.println("After sleep in consumer: " + name + " " + state);

        synchronized (lock) {
            System.out.println("Synchronized in consumer: " + name + " " + state);

            System.out.println("Consumer is notifying...");
            lock.notify();  // Разблокируем поток producer

            System.out.println("After notify in consumer" + name + " " + state);
        }
    }
}
