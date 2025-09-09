package collections;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueExample {

    public static void method() {
//        priorityQueue();
        arrayDeque();
    }

    private static void priorityQueue() {
        Queue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(6);
        System.out.println(priorityQueue); //[6]
        priorityQueue.add(1);
        System.out.println(priorityQueue); //[1, 6]
        priorityQueue.add(2);
        System.out.println(priorityQueue); //[1, 6, 2]
        priorityQueue.add(4);
        System.out.println(priorityQueue); //[1, 4, 2, 6]
        priorityQueue.add(3);
        System.out.println(priorityQueue);//[1, 3, 2, 6, 4]
        priorityQueue.add(0);
        System.out.println(priorityQueue);//[0, 3, 1, 6, 4, 2]

        System.out.println("Head: " + priorityQueue.peek()); //1
        System.out.println("PriorityQueue с Comparable: "); //1 2 3 4
        while (priorityQueue.size() > 0) {
            System.out.print(priorityQueue.remove() + " ");
        }
        System.out.println();

        Queue<Integer> integerQueueWithComparator =
                new PriorityQueue<>(Comparator.comparingInt((Integer c) -> c));
        integerQueueWithComparator.add(2);
        integerQueueWithComparator.add(1);
        integerQueueWithComparator.add(4);
        integerQueueWithComparator.add(3);

        System.out.println("Head: " + integerQueueWithComparator.peek()); //1
        System.out.println("PriorityQueue с Comparator по возрастанию: "); //1 2 3 4
        while (integerQueueWithComparator.size() > 0) {
            System.out.print(integerQueueWithComparator.remove() + " ");
        }
        System.out.println();

        Queue<Integer> reversedQueue = new PriorityQueue<>(Collections.reverseOrder());
        reversedQueue.add(1);
        reversedQueue.add(2);
        reversedQueue.add(4);
        reversedQueue.add(5);
        reversedQueue.add(3);

        System.out.println("Head: " + reversedQueue.peek()); //5
        System.out.println("PriorityQueue с Comparator по убыванию: "); //5 4 3 2 1
        while (reversedQueue.size() > 0) {
            System.out.print(reversedQueue.remove() + " ");
        }
        System.out.println();
    }

    private static void arrayDeque() {
        Deque<String> arrayDeque = new ArrayDeque<>();
        // стандартное добавление элементов
        arrayDeque.add("Germany");
        arrayDeque.addFirst("France"); // добавляем элемент в самое начало
        arrayDeque.push("Great Britain"); // добавляем элемент в самое начало
        arrayDeque.addLast("Spain"); // добавляем элемент в конец коллекции
        arrayDeque.add("Italy");
        System.out.println("ArrayDeque: " + arrayDeque); //[Great Britain, France, Germany, Spain, Italy]

        // получаем первый элемент без удаления
        String sFirst = arrayDeque.getFirst();
        System.out.println(sFirst);     // Great Britain
        // получаем последний элемент без удаления
        String sLast = arrayDeque.getLast();
        System.out.println(sLast);      // Italy

        System.out.printf("Queue size: %d \n", arrayDeque.size());  // 5

        // перебор коллекции
        while (arrayDeque.peek() != null) {
            // извлечение c начала
            System.out.print(arrayDeque.pop() + "; ");
        }
    }
}
