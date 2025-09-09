package java_core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Java всегда передает параметры по значению
 */
public class ParameterTransfer {

    public static void cases() {
//        case1();
        case2();
//        case3();
    }

    /**
     * Для изменения ссылки fruitRef мы использовали оператор new.
     * Теперь fruitRef указывает на новый объект, и любые изменения,
     * которые вы вносите в него, не повлияют на исходный объект списка фруктов.
     */
    private static void case3() {
        List<String> fruits = new ArrayList<>(List.of("Apple", "Banana", "Mango"));

        System.out.println("Before " + Arrays.toString(fruits.toArray())); // [Apple, Banana, Mango]
        processData3(fruits);
        System.out.println("After " + Arrays.toString(fruits.toArray())); // [Apple, Banana, Mango]
    }

    private static void processData3(List<String> fruitsRef) {
        fruitsRef = new ArrayList<>(fruitsRef);
        fruitsRef.add("Orange");
    }

    /**
     * Список fruits передается методу processData.
     * Переменная fruitRef — это копия параметра fruits.
     * И fruits и fruitsRef размещаются в стеке.
     * Это две разные ссылки, но они указывают на один и тот же объект в куче.
     * То есть, любое изменение, которое вы вносите с помощью любой из этих ссылок, влияет на объект.
     */
    private static void case2() {
        List<String> fruits = new ArrayList<>(List.of("Apple", "Banana", "Mango"));

        System.out.println("Before " + Arrays.toString(fruits.toArray())); // [Apple, Banana, Mango]
        processData(fruits);
        System.out.println("After " + Arrays.toString(fruits.toArray())); // [Apple, Banana, Mango, Orange]
    }

    private static void processData(List<String> fruitsRef) {
        fruitsRef.add("Orange");
    }

    /**
     * Метод processData работает с копией данных.
     * Следовательно, в исходных данных (в методе case1) не произошло никаких изменений.
     */
    private static void case1() {
        int data = 10;
        System.out.println("Before " + data); //10
        processData(data);
        System.out.println("After " + data); //10
    }

    private static void processData(int data) {
        data = data * 10;
    }
}
