package concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExample {

    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition turnChanged = lock.newCondition();
    private static boolean pingTurn = true; // флаг "чья очередь"

    public static void res() {
        Thread ping = new Thread(() -> play("PING", true));
        Thread pong = new Thread(() -> play("PONG", false));

        ping.start();
        pong.start();

        try {
            ping.join();
            pong.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("\nГотово");
    }

    private static void play(String word, boolean isPing) {
        for (int i = 0; i < 5; i++) { // напечатаем по 5 раз
            lock.lock();
            try {
                // ВСЕГДА while, а не if: возможны ложные пробуждения
                while (pingTurn != isPing) {
                    try {
                        turnChanged.await();  // освобождает lock и ждёт сигнала
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return; // корректно выходим по прерыванию
                    }
                }

                // наша очередь
                System.out.print(word + " ");

                // передаём ход другой стороне
                pingTurn = !pingTurn;
                turnChanged.signal(); // разбудим ровно одного ожидающего
            } finally {
                lock.unlock();
            }
        }
    }
}
