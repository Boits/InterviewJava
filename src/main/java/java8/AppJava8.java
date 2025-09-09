package java8;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class AppJava8 {

    public static void mainJava8() {
//        lambda();
//        lambda2();
//        basicFunctionalInterfaces();
//        stringJoiner();

//        optional();
//        mapOperations();

        MyStreams.method();
    }

    private static void lambda() {
        int a = 10;
        WorkerInterface workerInterface = () -> {
//            a++; //ошибка, нельзя менять
            System.out.println("Это лямбда-выражение. " + a);
        };
        workerInterface.doSomeWork();
        //or
        execute(new WorkerInterface() {
            @Override
            public void doSomeWork() {
                System.out.println("Worker вызван через анонимный класс");
            }
        });
        //or
        execute(() -> System.out.println("Worker вызван через Lambda"));
    }

    /**
     * Метод execute() способен принимать lambda-выражения в качестве аргумента.
     */
    private static void execute(WorkerInterface worker) {
        worker.doSomeWork();
    }

    private static void lambda2() {
        ConverterFunctionalInterface<String, Integer> converter =
                from -> Integer.toString(from);

        String converted = converter.convert(123);
        converter.defaultMethod();
        ConverterFunctionalInterface.staticMethod();

        System.out.println(converted); //123

        ConverterFunctionalInterface<Integer, String> converter2 = Integer::valueOf;
        Integer converted2 = converter2.convert("2020");
        System.out.println(converted2);//2020
    }

    private static void basicFunctionalInterfaces() {
        System.out.println("======================Comparator=========================");

        Comparator<Integer> comparator = Integer::compareTo;
        int compare = comparator.compare(12, 15);
        System.out.println(compare);   // -1

        System.out.println("======================Predicate=========================");
        Predicate<Integer> isPositive = x -> x > 0;

        System.out.println(isPositive.test(5));  // Вывод: true
        System.out.println(isPositive.test(-5)); // Вывод: false

        Predicate<Integer> isNull = Objects::isNull;
        System.out.println(isNull.test(12)); //false

        System.out.println("=====================Function==========================");

        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);

        System.out.println(backToString.apply("124"));

        System.out.println("=====================Supplier==========================");

        Supplier<String> personSupplier = () -> "Hello";
        String resSupplier = personSupplier.get();
        System.out.println(resSupplier);

        System.out.println("=====================Consumer==========================");

        Consumer<String> greeter = (p) -> System.out.println("Hello, " + p);
        greeter.accept("Luke");
    }

    private static void optional() {
        System.out.println("======================Not Null============================");
        Optional<String> optional = Optional.of("value");

        if (optional.isPresent()) {
            System.out.println("get(): " + optional.get()); //value
        }
        String orElseT = optional.orElse("Other");
        System.out.println("orElse(T): " + orElseT); // value

        optional.ifPresent(s -> System.out.println("ifPresent(): " + s.charAt(0)));     // "v"

        String dynamicValue1 = optional.orElseGet(() -> "Calculated value");
        System.out.println("orElseGet(): " + dynamicValue1); //value

        Optional<Integer> optional2 = Optional.of(100);
        optional2
                .filter(val -> val.equals(100))
                .ifPresent(val -> System.out.println("Optional2 = " + val));

        System.out.println("======================Null============================");
        Optional<String> nullableOpt = Optional.ofNullable(null);

        String s = nullableOpt.orElse("Other");
        System.out.println("orElse(T): " + s); //"Other"

        nullableOpt.ifPresent(ss -> System.out.println("ifPresent(): " + ss.charAt(0))); //-

        String dynamicValue2 = nullableOpt.orElseGet(() -> "Calculated value");
        System.out.println("orElseGet(): " + dynamicValue2); //Calculated value
    }

    private static void mapOperations() {
        Map<Integer, String> map = new HashMap<>();
        map.put(0, null);
        for (int i = 0; i < 10; i++) {
            // putIfAbsent()
            // Добавляет пару (key, value) в карту, если ключа ещё нет или если по этому ключу хранится null.
            //Если ключ уже есть и там лежит не-null значение, то ничего не меняет.
            map.putIfAbsent(i, "val " + i);
        }
        map.forEach((id, val) -> System.out.println(val));
    }

    private static void stringJoiner() {
        StringJoiner sj = new StringJoiner(".");
        sj.add("Hello").add("World").add("!!!");
        System.out.println(sj); //Hello.World.!!!
    }
}
