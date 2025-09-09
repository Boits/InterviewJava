package collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListExample {

    public static void method() {
        arrayList();
        linkedList();

        problemConcurrentModificationException();
        copyOnWriteArrayList();
        synchronizedList();
    }

    private static void arrayList() {
        System.out.println("============ ArrayList ==========");
        List<String> list = new ArrayList<>(List.of("1", "2", "3", "4", "5", "6"));
        list.add(" world!");
        list.add(0, "Hello");
        list.add("B");

        int size = list.size();                     // количество элементов
        System.out.println("ArrayList: " + list);//[Hello, 1, 2, 3, 4, 5, 6,  world!, B]

        list.remove("Hello");
        list.remove(1);

        System.out.println("ArrayList: " + list); //[1, 3, 4, 5, 6,  world!, B]
    }

    private static void linkedList() {
        System.out.println("============ LinkedList ==========");
        List<String> list = new LinkedList<>(List.of("1", "2", "3", "4", "5", "6"));
        list.add(" world!");
        list.add(2, "Hello");

        System.out.println("LinkedList: " + list);
    }

    private static void problemConcurrentModificationException() {
        System.out.println("============ ProblemConcurrentModificationException ==========");
        List<String> list = new ArrayList<>(List.of("1", "2", "3", "4", "5", "6"));
        list.add("B");
        //Проблема:
//        for (String item : list) {  // Используется Iterator внутри
//            if (item.equals("B")) {
//                list.remove(item);  //Выбросит ConcurrentModificationException!
//            }
//        }

        //Решение 1:
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals("B")) {
                iterator.remove();  // Правильный способ удаления
            }
        }

        list.add("B");

        //Решение 2:
        list.removeIf(item -> item.equals("B"));
    }

    private static void copyOnWriteArrayList() {
        System.out.println("============ CopyOnWriteArrayList ==========");
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        System.out.println("CopyOnWriteArrayList: " + list); //[A, B, C]

        // Итератор работает с копией массива и может только получать данные, не изменять их.
        for (String item : list) {
            if (item.equals("B")) {
                list.remove(item);
            }
        }
        //или
//        list.removeIf(item -> item.equals("B"));

//        list.add("B");
//        Iterator<String> iterator = list.iterator();
//        while (iterator.hasNext()) {
//            if (iterator.next().equals("B")) {
//                iterator.remove(); // UnsupportedOperationException
//            }
//        }

        System.out.println("CopyOnWriteArrayList: " + list); // [A, C]
    }

    private static void synchronizedList() {
        System.out.println("============ Collections.synchronizedList() ============");
        List<String> sal = new ArrayList<>();
        sal.add("Geeks");
        sal.add("for");
        sal.add("Geeks");
        sal.add("Computer");
        sal.add("Science");
        sal.add("Portal");
        System.out.println(sal);

        Collections.synchronizedList(sal);

        // Synchronized block to avoid non-deterministic behavior
        synchronized (sal) { // Без synchronized будет ConcurrentModifcationException
            Iterator<String> itrobj = sal.iterator();

            while (itrobj.hasNext()) {
                System.out.println(itrobj.next());
            }
        }
    }
}
