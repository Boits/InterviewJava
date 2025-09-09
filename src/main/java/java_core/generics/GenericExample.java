package java_core.generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class GenericExample {

    public static void method() {
        Box<Double> box = new Box<>(1.1);
        System.out.println("Box: " + box.getItem());

        pecsExtendsExample();
        pecsSuperExample();

        upperLowerWildcards();

        List<String> strings = List.of("Hello", "World");
        List<Integer> numbers = List.of(1, 2, 3);
        unboundedWildcards(strings);
        unboundedWildcards(numbers);

        CovarianceContravarianceInvarianceExample.example();
    }

    private static void pecsExtendsExample() {
        System.out.println("============= PECS extends ===============");
        List<? extends Number> list = new ArrayList<>(List.of(14.5, 56));
//        list.add(10);      // Нельзя добавить Integer
//        list.add(3.14);    // Нельзя добавить Double
        list.add(null); // можно добавить только NULL

        for (Number num : list) {
            if (Objects.nonNull(num)) {
                System.out.println("Type: " + num.getClass().getTypeName() + "; Value: " + num);
            }
        }

        for (Object num : list) {
            if (Objects.nonNull(num)) {
                System.out.println("Type: " + num.getClass().getTypeName() + "; Value: " + num);
            }
        }

        /**
         * Нельзя читать подтипы Number
         */
//        for (Double num : list) {
//            System.out.println("Type: " + num.getClass().getTypeName() + "; Value: " + num);
//        }
    }

    private static void pecsSuperExample() {
        System.out.println("============= PECS super ===============");
        List<? super Number> list = new ArrayList<>(List.of(18.4, 20, 4));
        list.add(10);      // Можно добавить Integer
        list.add(3.14);    // Можно добавить Double

//        Чтение запрещено
//        for (Number num : list) { //error
//            System.out.println(num);
//        }

        for (Object element : list) { // При чтении мы получим Object, так как тип конкретного элемента неизвестен
            System.out.println(element);
        }
    }

    private static void upperLowerWildcards() {
        System.out.println("============== Wildcards ============");

        //PECS
        List<Integer> integerList = new ArrayList<>(List.of(1, 3));
        List<Number> numberList = new ArrayList<>(List.of(1, 3.2, 24f));
        List<Object> objectList = new ArrayList<>(List.of(new Object(), new Object()));

        producer(integerList);
        producer(numberList);
//        producer(objectList); //error

//        consumer(integerList); //error
        consumer(numberList);
        consumer(objectList);
    }


    /**
     * Upper Bounded Wildcards (<? extends Number>)
     */
    private static void producer(List<? extends Number> numbers) { //Producer extends (только читает)
        System.out.println("PECS, Upper Bounded Wildcards(<? extends Number>): ");
//    Запись запрещена
//        numbers.add(1.1); //ошибка

        numbers.add(null);

//      Чтение разрешено
        for (Number num : numbers) {
            System.out.println("Type: Number; Value: " + num);
        }
    }

    /**
     * Lower Bounded Wildcards (<? super Number>)
     */
    private static void consumer(List<? super Number> numbers) { //Consumer super (только добавляет)
        System.out.println("PECS, Lower Bounded Wildcards(<? super Number>): ");
//        Запись разрешена
        numbers.add(10);
        numbers.add(20.2);
//        numbers.add(new Object());//error

//        Чтение запрещено
//        for (Number num : numbers) { //error
//            System.out.println(num);
//        }
        for (Object num : numbers) {
            System.out.println("Type: Object; Value: " + num);
        }
    }

    /**
     * Wildcard <?>
     * ? - любой совместимый тип тип может передаваться в метод.
     * Ограничен сверху, т.е. Collection<?> равносильна Collection<? extends Object>
     */
    private static void unboundedWildcards(List<?> list) {
        System.out.println("============== Unbounded Wildcard <?> ============");

//        list.add(new Object()); нельзя добавлять

        for (Object element : list) { // Доступ только как Object
            System.out.println(element);
        }
    }

    //<T> идет до возвращаемого типа
    private static <T> T typedMethod(Object obj) {
        return (T) obj;
    }

    //дженерик указывается после имени класса
    private <T> void typedClass(Collection<T> collection) {
        for (T element : collection) {
            System.out.println(element);
        }
    }
}
