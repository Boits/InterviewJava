package concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueExample {

    /**
     * Producer пытается добавить элемент в очередь.
     * Если очередь полна -> ждет, пока consumer заберёт элемент.
     * Consumer пытается забрать элемент.
     * Если очередь пуста -> ждет, пока producer положит элемент.
     */
    public static void res() {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(3);
        final int POISON_PILL = -1; // специальный сигнал для завершения

        // Producer
        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    System.out.println("Добавляем: " + i);
                    queue.put(i);  // блокируется, если очередь полна
                    Thread.sleep(500);
                }
                catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            try {
                queue.put(POISON_PILL); // сигнал завершения
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        producer.start();

        // Consumer
        Thread consumer = new Thread(() -> {
            while (true) {
                try {
                    Integer item = queue.take(); // блокируется, если очередь пуста
                    if (item == POISON_PILL) break; // завершаем поток
                    System.out.println("Забрали: " + item);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        consumer.start();
    }

}
