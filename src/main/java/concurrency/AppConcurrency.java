package concurrency;

import concurrency.problems.ResourceStarvation_1;
import concurrency.thread_pool.CompletableFutureExample;
import concurrency.thread_pool.ExecutorExample;
import concurrency.thread_pool.ForkJoinExample;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AppConcurrency {

    public static void mainConcurrency() {
//        safeMap();

        //Какие проблемы создаёт многопоточность:
        ResourceStarvation_1.res();
//        DeadLock_2.res();
//        RaceCondition_4.res();

//        VolatileExample.res();

//        ThreadLocalExample.res();

//        CloseThread.res();
//        WaitNotifyExample.res();

//        ExecutorExample.res();
//        ForkJoinExample.res();
//        CompletableFutureExample.res();

//        BlockingQueueExample.res();
    }

    /**
     * synchronizedMap - безопасная, но во время модификации по итерации, не безопасная (решение проблемы ниже)
     * ConcurrentHashMap - безопасная для всех методов
     * <p>
     * Неэффективность полной синхронизации:
     * В отличие от структур, таких как ConcurrentHashMap, где синхронизация происходит на уровне сегментов,
     * в synchronizedMap приходится блокировать всю карту на время выполнения любого метода.
     * Это приводит к снижению производительности при работе в многопоточной среде,
     * так как синхронизация блокирует доступ всем другим потокам до тех пор,
     * пока текущий поток не завершит выполнение операций.
     */
    private static void safeMap() {
        Map<String, Integer> map = Collections.synchronizedMap(new HashMap<>());
        //приходится блокировать всю карту на время выполнения любого метода.
        // Это приводит к снижению производительности при работе в многопоточной среде,
        // так как синхронизация блокирует доступ всем другим потокам до тех пор,
        // пока текущий поток не завершит выполнение операций.
        map.put("key1", 1); //Потокобезопасно добавляет
        map.put("key2", 2);

        synchronized (map) {
            map.entrySet().removeIf(entry -> entry.getKey().equals("key1"));
        }
        // =========================================
        Map<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        //ConcurrentHashMap использует блокировки на уровне сегментов или бакетов, что позволяет нескольким
        // потокам одновременно безопасно добавлять или обновлять элементы без блокировки всей коллекции.
        concurrentHashMap.put("key", 42);  // Потокобезопасно добавляет или заменяет значение
        concurrentHashMap.entrySet().removeIf(entry -> entry.getKey().equals("key1")); // Безопасное удаление во время итерации
    }

}
