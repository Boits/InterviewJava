package java8;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MyStreams {

    private static final List<Integer> LIST_1 = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    private static final List<String> LIST_2 = List.of("ddd2", "aaa2", "bbb1", "aaa1", "bbb3", "ccc", "bbb2", "ddd1");

    public static void method() {
//        howStreamWork();

//        filter();
//        sorted();
//        map();
//        match();
//        count();

//        reduce();
        flatMap();

//        groupingBy();
//        comparing();

//        parallelStream();
    }

    /**
     * Сначала берется первый элемент и проходит по всем методам по очереди сверху вниз (1-4),
     * Затем берется следующий элемент.
     * <p>
     * Как только появился терминальный оператор forEach,
     * он стал запрашивать элементы у стоящего перед ним оператора limit.
     * Тот в свою очередь обращается к map, map к filter, а filter уже обращается к источнику.
     * Затем элементы поступают в прямом порядке: источник, filter, map, limit и forEach.
     * Пока какой-либо из операторов не обработает элемент должным образом, новые запрошены не будут.
     * Как только через оператор limit прошло 3 элемента,
     * он переходит в закрытое состояние и больше не будет запрашивать элементы у map.
     * forEach запрашивает очередной элемент, но limit сообщает, что больше не может поставить элементов,
     * поэтому forEach делает вывод, что элементы закончились и прекращает работу.
     */
    private static void howStreamWork() {
        IntStream.of(120, 410, 85, 32, 314, 12)
                .filter(x -> {                      //1
                    System.out.println("filter(): x = " + x);
                    return x < 300;
                })
                .map(x -> {                         //2
                    System.out.println("map(): x = " + x);
                    return x + 10;
                })
                .limit(3)                    //3
                .forEach((x) -> System.out.println("forEach(): x = " + x));//4
        //130 95 42

//        filter(): x = 120
//        map(): x = 120
//        forEach(): x = 130
//        filter(): x = 410
//        filter(): x = 85
//        map(): x = 85
//        forEach(): x = 95
//        filter(): x = 32
//        map(): x = 32
//        forEach(): x = 42
    }

    private static void filter() {
        System.out.println("================ filter(): ");

        AtomicInteger i = new AtomicInteger();

        LIST_2.stream()
                .filter(el -> {
                    i.getAndIncrement();
                    System.out.println("i = " + i.get() + "; el = " + el);
                    return el.startsWith("aaa");
                })
                .forEach(val -> System.out.println("res: " + val)); //aaa2, aaa1
    }

    private static void sorted() {
        System.out.println("================ sorted(): ");
        //LIST_2 = List.of("ddd2", "aaa2", "bbb1", "aaa1", "bbb3", "ccc", "bbb2", "ddd1")

        String case1 = LIST_2.stream()
                .sorted() //сначала сортирует весь поток, попом делает для каждого эл-та filter() и т.д.
                .filter(el -> el.startsWith("aaa"))
                .collect(Collectors.joining(", "));
        System.out.println("Case1: " + case1); //aaa1, aaa2

        String case2 = LIST_2.stream()
                .limit(2)
                .sorted() //сначала делает для каждого эл-та промежуточные операции выше, потом сортирует весь поток.
                .collect(Collectors.joining(", "));
        System.out.println("Case2: " + case2); //aaa2, ddd2
    }

    private static void map() {
        System.out.println("================ map(): ");
        //LIST_2 = List.of("ddd2", "aaa2", "bbb1", "aaa1", "bbb3", "ccc", "bbb2", "ddd1")

        LIST_2.stream()
                .map(String::toUpperCase) //сначала для каждого элемента меняем на большой символ
//                .limit(3)
                .sorted(Comparator.reverseOrder()) //потом все сортируем уже большое
                .forEach(System.out::println); //и после этого выводим все элементы поочереди
        //если без limit(3): DDD2,DDD1,CCC,BBB3,BBB2,BBB1,AAA2,AAA1
        //если c limit(3): DDD2,BBB1,AAA2
    }

    private static void match() {
        System.out.println("================ match(): ");
        //LIST_2 = List.of("ddd2", "aaa2", "bbb1", "aaa1", "bbb3", "ccc", "bbb2", "ddd1")

        boolean anyStartsWithA = LIST_2
                .stream()
                .anyMatch(s -> s.startsWith("a"));
        System.out.println("anyMatch(): " + anyStartsWithA);      // true

        boolean allStartsWithA = LIST_2
                .stream()
                .allMatch(s -> s.startsWith("a"));
        System.out.println("allMatch(): " + allStartsWithA);      // false

        boolean noneStartsWithZ = LIST_2
                .stream()
                .noneMatch(s -> s.startsWith("z"));
        System.out.println("noneMatch(): " + noneStartsWithZ);      // true
    }

    private static void count() {
        System.out.println("================ count(): ");
        //LIST_2 = List.of("ddd2", "aaa2", "bbb1", "aaa1", "bbb3", "ccc", "bbb2", "ddd1")

        long startsWithB = LIST_2
                .stream()
                .filter(s -> s.startsWith("b"))
                .count();
        System.out.println(startsWithB); //3
    }

    /**
     * Позволяет получить один результат из последовательности элементов,
     * неоднократно применяя операцию комбинирования к элементам в последовательности.
     */
    private static void reduce() { //Преобразует все элементы в один
        System.out.println("================ reduce(): ");
        //LIST_2 = List.of("ddd2", "aaa2", "bbb1", "aaa1", "bbb3", "ccc", "bbb2", "ddd1")

        Optional<String> reduced = LIST_2
                .stream()
                .sorted()
                .reduce((s1, s2) -> s1 + "#" + s2);
        reduced.ifPresent(res -> System.out.println("Объединение строк: " + res));// "aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2"

        //считает сумму чисел
        Optional<Integer> sum = LIST_1
                .stream()
                .reduce((s1, s2) -> {
//                    System.out.println("s1 = " + s1 + "; s2 = " + s2); //первое + второе -> результат + третье и т.д.
//                    System.out.println(s1 + s2);
                    return s1 + s2;
                });
        //or .reduce(Integer::sum);
        sum.ifPresent(res -> System.out.println("Сумма чисел: " + res));//55

        int sumWithRange = IntStream.range(1, 10)
                .sum();
        System.out.println("Сумма: " + sumWithRange);  //45

        //Max value
        Optional<Integer> max = LIST_1.stream()
                .reduce((a, b) -> Integer.max(a, b));
//or            .reduce(Integer::max);
//or            .max(Integer::compare);
        max.ifPresent(res -> System.out.println("Максимальное число: " + res)); // 10

        String result = Stream.of("A", "B", "C")
                .reduce("", (a, b) -> a + b);
        System.out.println(result); // "ABC"
    }

    /**
     * Сопоставляет поток с развернутым потоком.
     * Как map(), но можно преобразовать один элемент в ноль/один/множество других элементов.
     * Т.е. каждый элемент можно преобразовать в список элементов.
     * <p>
     * + используется для разглаживания вложенных структур данных,
     * превращая Stream<Stream<T>> в Stream<T>.
     */
    private static void flatMap() {
        System.out.println("================ flatMap(): ");

        List<List<String>> listLists = Arrays.asList(
                List.of("a"),
                List.of("b"));
        System.out.println("listLists: " + listLists); //[[a], [b]]

        List<String> list = listLists.stream()
                .flatMap(stringList -> stringList.stream())
//or                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        System.out.println("list: " + list); // [a, b]

        //===============================================

        List<String> sentences = Arrays.asList("Hello World", "Java Stream API");
        List<String> words = sentences.stream()
                .flatMap(sentence -> Arrays.stream(sentence.split(" ")))  // Преобразуем каждое предложение в поток слов
                .toList();

        System.out.println(words);  // Вывод: [Hello, World, Java, Stream, API]

        List<String> list1 = sentences.stream()
                .flatMap(sentence -> Stream.of(sentence + "-hehe"))  // Преобразуем каждое предложение в поток слов
                .toList();
        System.out.println(list1); //[Hello World-hehe, Java Stream API-hehe]
    }

    private static final List<Person> PEOPLE = Arrays.asList(
            new Person("Alice", 30),
            new Person("Bob", 25),
            new Person("Baby", 25),
            new Person("Charlie", 35)
    );

    private static void groupingBy() {
        System.out.println("================ groupingBy(): ");

        // Группируем Person по возрасту
        Map<Integer, List<Person>> groupedByAge = PEOPLE.stream()
                .sorted(Comparator.comparing(Person::getName)) //сортируем Value в Map по имени
                .collect(Collectors.groupingBy(Person::getAge, TreeMap::new, Collectors.toList()));
        //key - age + сортировка ключей

        System.out.println("groupedByAge: " + groupedByAge);
        //{25=[Baby (25), Bob (25)], 30=[Alice (30)], 35=[Charlie (35)]}
        //==============================================

        List<String> words = Arrays.asList("apple", "banana", "cherry", "date", "fig", "grape");

        // Группируем слова по их длине
        Map<Integer, List<String>> groupedByLength = words.stream()
                .collect(Collectors.groupingBy(String::length));//длина строки - key

        System.out.println("groupedByLength: " + groupedByLength);
        //{3=[fig], 4=[date], 5=[apple, grape], 6=[banana, cherry]}
        //==============================================

        // Группируем слова по длине и считаем количество слов в каждой группе
        Map<Integer, Long> groupedByLengthCount = words.stream()
                .collect(Collectors.groupingBy(String::length, Collectors.counting()));
        //длина строки - key
        //количество слов с такой длинной - value

        System.out.println("groupedByLengthCount: " + groupedByLengthCount);
        //{3=1, 4=1, 5=2, 6=2}
        //==============================================

        // Группируем слова по длине и собираем их в Set
        Map<Integer, Set<String>> groupedByLengthSet = words.stream()
                .collect(Collectors.groupingBy(String::length, Collectors.toSet()));

        System.out.println("groupedByLengthSet: " + groupedByLengthSet);
        //{3=[fig], 4=[date], 5=[apple, grape], 6=[banana, cherry]}
        //==============================================

        // Группируем слова по длине, используя TreeMap для сортировки по ключам
        Map<Integer, List<String>> groupedByLengthSorted = words.stream()
                .collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.toList()));

        System.out.println("groupedByLengthSorted: " + groupedByLengthSorted);//гарантирует сортировку по ключам
        //{3=[fig], 4=[date], 5=[apple, grape], 6=[banana, cherry]}
        //==============================================

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // Группируем числа на чётные и нечётные
        Map<Boolean, List<Integer>> groupedByParity = numbers.stream()
                .collect(Collectors.groupingBy(num -> num % 2 == 0)); //num % 2 == 0 - key

        System.out.println("groupedByParity: " + groupedByParity);
        // {false=[1, 3, 5, 7, 9], true=[2, 4, 6, 8, 10]}
        //==============================================

        List<Integer> list = List.of(1, 1);
        Map<Integer, Integer> collect = list.stream()
                .collect(Collectors.toMap(val -> val, val -> val + 4, (old, new1) -> new1));

        collect.forEach((k, v) -> System.out.println(k + " " + v)); //1 5
    }

    private static void comparing() {
        System.out.println("================ comparing(): ");

        // Сортируем список по возрасту, а потом по имени
        List<Person> sortedByCriteria = PEOPLE.stream()
                .sorted(Comparator.comparing(Person::getAge)
                        .thenComparing(Person::getName))
                .toList();
        //or
        // .collect(Collectors.toList());
        System.out.println(sortedByCriteria); //[Baby (25), Bob (25), Alice (30), Charlie (35)]

        Optional<String> min = Stream.of("ab", "c", "defgh", "ijk", "l")
                .min(Comparator.comparing(String::length));
        min.ifPresent(System.out::println); //c
    }

    private static void parallelStream() {
        System.out.println("================ parallelStream(): ");

        String sentence = LIST_2.stream()
                .parallel()
                .reduce("Результат:", (x, y) -> x + " " + y);
        System.out.println(sentence);

        LIST_1.parallelStream()
                .sorted()
                .forEachOrdered(System.out::println);
        //.forEach() - может выводить элементы в произвольном порядке

        // Так не надо делать:
        //        final List<Integer> ints = new ArrayList<>();
        //        IntStream.range(0, 1000000)
        //                .parallel()
        //                .forEach(i -> ints.add(i));
        //        System.out.println(ints.size());
        //ArrayList не потокобезопасен, а ты добавляешь в него элементы из параллельного стрима.
    }

    private static class Person {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        String getName() {
            return this.name;
        }

        int getAge() {
            return this.age;
        }

        @Override
        public String toString() {
            return name + " (" + age + ")";
        }
    }

    public List<String> filterStartingWithAaa(List<String> src) {
        return src.stream()
                .filter(el -> el.startsWith("aaa"))
                .toList();
    }
}
