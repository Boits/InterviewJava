package collections;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;

public class IterableExample {

    public static void method() {
        iterator();
//        iterator2();
//        listIterator();
//        spliterator();
    }

    private static void iterator() {
        List<String> list = new ArrayList<>(List.of("My", "name", "is", "Java"));
        System.out.println("List: " + list);

        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()) { //true, если итерация содержит следующий элемент. Не передвигает указатель итератора.
            String next = iterator.next(); //Передвигает указатель на следующий элемент и возвращает его.
            System.out.println("iterator.next(): " + next);
            iterator.remove(); //Удаляет из коллекции последний элемент, возвращенный методом next()
//            iterator.forEachRemaining(System.out::print); //nameisJava
        }
        System.out.println("List: " + list); //[]
    }

    private static void iterator2() {
        List<String> list = List.of("A", "B", "C");
        System.out.println(list);

        // for-each использует Iterable под капотом
        for (String s : list) {
            System.out.println("s = " + s);
        }

        // то же самое вручную через iterator()
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            System.out.println("it.next() = " + it.next());
//            it.remove(); //UnsupportedOperationException, т.к. коллекция неизменяемая
        }
    }

    private static void listIterator() {
        List<String> list = new ArrayList<>(List.of("A", "B", "C"));

        // Получаем ListIterator, который стоит перед элементом с индексом 0
        ListIterator<String> it = list.listIterator();

        System.out.println("Идём вперёд:");
        while (it.hasNext()) {
            int index = it.nextIndex();
            String value = it.next();
            System.out.println(index + " -> " + value);
        }

        System.out.println("\nИдём назад:");
        while (it.hasPrevious()) {
            int index = it.previousIndex();
            String value = it.previous();
            System.out.println(index + " -> " + value);
        }

        System.out.println("\nМодификация списка:");
        ListIterator<String> it2 = list.listIterator();
        while (it2.hasNext()) {
            String value = it2.next();
            if (value.equals("B")) {
                it2.set("X"); // заменяем "B" на "X"
                it2.add("Y"); // вставляем "Y" сразу после "X"
            }
        }
        System.out.println(list); // [A, X, Y, C]
    }

    private static void spliterator() {
        List<String> list = List.of("A", "B", "C", "D", "E");
        System.out.println("List: " + list);

        Spliterator<String> spliterator1 = list.spliterator();
        // Разделяем на два
        Spliterator<String> spliterator2 = spliterator1.trySplit();

        System.out.println("Первая часть:");
        spliterator1.forEachRemaining(System.out::println); //CDE

        System.out.println("Вторая часть:");
        spliterator2.forEachRemaining(System.out::println); //AB
    }
}
