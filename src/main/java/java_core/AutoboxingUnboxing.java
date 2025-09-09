package java_core;

import java.util.Arrays;

public class AutoboxingUnboxing {

    public static void autoboxingUnboxing() {
        AutoboxingUnboxing.compareInteger();

        Integer i1 = 10;
        AutoboxingUnboxing.methodOverrideInt(i1); // "Integer"

        int i2 = 20;
        AutoboxingUnboxing.methodOverrideInt(i2); //"int"

        AutoboxingUnboxing.boxArray();
    }

    private static void compareInteger() {
        /**
         * Autoboxing
         * Кэширует значения от -128 до 127 (экономия памяти).
         */
        Integer iOb1 = 100; //кешируется
        Integer iOb2 = 100; //ссылается на кешированный из Integer Pool
        System.out.println("iOb1 == iOb2: "+ (iOb1 == iOb2)); //true

        /**
         * Всегда создает новый объект, даже если значение уже есть в кэше.
         */
//        Integer iOb3 = new Integer(120); //deprecated from Java 9
//        Integer iOb4 = new Integer(120); //deprecated from Java 9
//        System.out.println("iOb3 == iOb4: "+ (iOb3 == iOb4)); //false

        /**
         * Кэширует значения от -128 до 127 (экономия памяти).
         */
        Integer iOb3 = Integer.valueOf(120); //<=> = 120 //кешируется
        Integer iOb4 = Integer.valueOf(120); //ссылается на кешированный из Integer Pool
        System.out.println("iOb3 == iOb4: "+ (iOb3 == iOb4)); //true

        Integer iOb5 = 200; // НЕ кэшируется
        Integer iOb6 = 200; // новый объект
        System.out.println("iOb5 == iOb6: " + (iOb5 == iOb6));//false
        System.out.println("iOb5.equals(iOb6): " + (iOb5.equals(iOb6))); //true
        System.out.println("=============================");

        Integer a = 200;
        int b = 200;
        System.out.println("a == b: " + (a == b)); // true - a распаковывается в int

        Integer a2 = 300;
        int b2 = 300;
        System.out.println("a2 == b2: " + (a2 == b2)); // true: распаковка a2 в int и сравнение
        System.out.println("a2.equals(b2): " + (a2.equals(b2))); // true: упаковка b2 в Integer и сравнение
        System.out.println("=============================");
    }

    /**
     * Если оба метода есть, то будет вызван methodOverrideInt(int i)
     * При передаче int i2
     */
    private static void methodOverrideInt(int i) {
        System.out.println("int");
    }

    /**
     * Если оба метода есть, то будет вызван methodOverrideInt(Integer i)
     * При передаче Integer i1
     */
    private static void methodOverrideInt(Integer i) {
        System.out.println("Integer");
    }

    private static void boxArray() {
        /**
         * Автоупаковка для массивов не работает
         */
//        int[] primArray = {1, 2, 3};
//        Integer[] objArray = primArray; // Ошибка компиляции!

        //Упаковка массива
        int[] primArray1 = {1, 2, 3};
        Integer[] objArray1 = Arrays.stream(primArray1) // IntStream
                .boxed()           // Автоупаковка в Stream<Integer>
                .toArray(Integer[]::new); // Преобразование в массив

        //Распакавка массива
        Integer[] objArray2 = {1, 2, 3};
        int[] primArray2 = Arrays.stream(objArray2)  // Stream<Integer>
                .mapToInt(Integer::intValue) // Распаковка в int
                .toArray(); // Преобразование в int[]
    }
}
