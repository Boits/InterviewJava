package concurrency;

import concurrency.problems.DeadLock_2;
import concurrency.problems.LiveLock_3;
import concurrency.problems.RaceCondition_4;
import concurrency.problems.ResourceStarvation_1;
import concurrency.thread_pool.CompletableFutureExample;
import concurrency.thread_pool.ExecutorExample;
import concurrency.thread_pool.ForkJoinExample;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;

public class AppConcurrency {

    public static void mainConcurrency() {
        //Какие проблемы создаёт многопоточность:
        ResourceStarvation_1.res();
//        DeadLock_2.res();
//        LiveLock_3.res();
//        RaceCondition_4.res();

//        VolatileExample.res();

//        ThreadLocalExample.res();

//        CloseThread.res();
//        WaitNotifyExample.res();
//        ConditionExample.res();

//        ExecutorExample.res();
//        ForkJoinExample.res();
//        CompletableFutureExample.res();

//        BlockingQueueExample.res();
//        safeMap();
    }

    /**
     * 1) synchronizedMap - безопасная, но во время модификации по итерации, не безопасная (решение проблемы ниже)
     * Приходится блокировать всю карту на время выполнения любого метода.
     * 2) ConcurrentHashMap - безопасная для всех методов.
     * ConcurrentHashMap использует блокировки на уровне сегментов или бакетов, что позволяет нескольким
     * потокам одновременно безопасно добавлять или обновлять элементы без блокировки всей коллекции.
     */
    private static void safeMap() {
        Map<String, Integer> map = Collections.synchronizedMap(new HashMap<>());
        map.put("key1", 1); //Потокобезопасно добавляет
        map.put("key2", 2);

        synchronized (map) {
            map.entrySet().removeIf(entry -> entry.getKey().equals("key1"));
        }


        Map<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("key", 42);  // Потокобезопасно добавляет или заменяет значение
        concurrentHashMap.entrySet().removeIf(entry -> entry.getKey().equals("key1")); // Безопасное удаление во время итерации
    }

}
