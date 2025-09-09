package java_core.clone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CloneExample {

    public static void method() {
        deepCloneObject();

        //Как клонируются разные конструкции
        cloneWrapperForbidden();
        cloneFinalPrimitiveAndImmutable();
        cloneArrayPrimitive();
        cloneArrayObjects();
        deepCloneMap();

        //Лучшие практики, чем clone():
        copyConstructor();
        copyFactoryMethod();
    }

    private static void deepCloneObject() {
        System.out.println("======== DeepCloneObject: ");

        Wheel originalWheel = new Wheel(0, 0);
        Car originalCar = new Car(10, "Blue", 4, originalWheel);

        Car deepCopyCar = originalCar.clone();

        //Изменение клонированного объекта
        deepCopyCar.setColor("Red");
        deepCopyCar.setWheelCount(200);
        deepCopyCar.getWheel().setX(150);
        deepCopyCar.getWheel().setY(150);
        deepCopyCar.getSb().append(" Hello!");

        System.out.println("Original: " + originalCar);
        //Original: Car: param = 10; color = Blue; wheelCount = 4; Wheel: x = 0; y = 0; sb = Test Hello!
        System.out.println("DeepCopy: " + deepCopyCar);
        //DeepCopy: Car: param = 10; color = Red; wheelCount = 200; Wheel: x = 150; y = 150; sb = Test Hello!
    }

    private static void copyConstructor() {
        System.out.println("======== CopyConstructor: ");

        Person original = new Person("Alice", 25, new Address("New York"));
        Person copy = new Person(original); // Глубокая копия
        copy.setName("Kate");
        copy.setAge(26);
        copy.getAddress().setCity("Los Angeles");

        System.out.println(original); // Alice (25) from New York
        System.out.println(copy); // Kate (26) from Los Angeles
    }

    private static void copyFactoryMethod() {
        System.out.println("======== CopyFactoryMethod: ");

        CarWithFabricMethod original = CarWithFabricMethod.of("Tesla", 100);
        CarWithFabricMethod copy = original.withSpeed(150); // Копируем, но изменяем скорость

        System.out.println(original); // Tesla moving at 100 km/h
        System.out.println(copy); // Tesla moving at 150 km/h
    }

    /**
     * Примитивные обёртки (Integer, Double и др.) неизменяемые (immutable),
     * поэтому глубокое клонирование не требуется.
     * Достаточно просто передать значение в новый объект — это безопасно.
     */
    private static void cloneWrapperForbidden() {
        System.out.println("======== CloneWrapperForbidden: ");
        Integer a = 200;
        Integer b = Integer.valueOf(a); // Новый объект
        System.out.println(a.equals(b)); //true
        Integer c = a; // Копирование ссылки
//        a.clone(); //нельзя

        String s = "Hello";
//        s.clone();//нельзя
    }

    private static void cloneFinalPrimitiveAndImmutable() {
        System.out.println("======== ClonePrimitiveAndImmutable:");

        FinalPrimitiveAndImmutable original = new FinalPrimitiveAndImmutable();
        FinalPrimitiveAndImmutable clone = original.clone();
        System.out.println(original); //FinalPrimitiveAndImmutable: number = 10; text = Hello
        System.out.println(clone); //FinalPrimitiveAndImmutable: number = 10; text = Hello
    }

    /**
     * В Java массивы являются объектами,
     * поэтому при обычном копировании копируется только ссылка.
     * <p>
     * Можно использовать clone() или Arrays.copyOf(), так как они копируют значения.
     */
    private static void cloneArrayPrimitive() {
        System.out.println("======== CloneArrayPrimitive: ");

        int[] original = {1, 2, 3};
        int[] deepCopy = original.clone();  // Новый массив с теми же значениями
        int[] deepCopy2 = Arrays.copyOf(original, original.length); // Новый массив с теми же значениями

        deepCopy[0] = 100;

        System.out.println(original[0]); // 1
        System.out.println(deepCopy[0]); // 100

        deepCopy2[0] = 200;

        System.out.println(original[0]); // 1
        System.out.println(deepCopy2[0]); // 200
    }

    /**
     * В Java массивы являются объектами,
     * поэтому при обычном копировании копируется только ссылка.
     * <p>
     * Нужно копировать каждый элемент вручную.
     * Для String/Integer... не обязательно, но важно для других объектов.
     */
    private static void cloneArrayObjects() {
        System.out.println("======== CloneArrayObjects: ");

        String[] original = {"A", "B", "C"};
        String[] deepCopy = new String[original.length];
        for (int i = 0; i < original.length; i++) {
            deepCopy[i] = new String(original[i]); // Копируем значения для других объектов, не String/Integer...
        }
        deepCopy[1] = "Qwe";
        System.out.println(Arrays.toString(original)); //[A, B, C]
        System.out.println(Arrays.toString(deepCopy)); //[A, Qwe, C]

        String[] deepCopy2 = original.clone();
        deepCopy2[1] = "Asd";
        System.out.println(Arrays.toString(original)); //[A, B, C]
        System.out.println(Arrays.toString(deepCopy2)); //[A, Asd, C]

        String[] deepCopy3 = Arrays.copyOf(original, original.length);
        deepCopy3[1] = "Zxc";
        System.out.println(Arrays.toString(original)); //[A, B, C]
        System.out.println(Arrays.toString(deepCopy3)); //[A, Zxc, C]
    }

    /**
     * clone() у HashMap создаёт поверхностную копию (копируются только ссылки).
     * Нужно рекурсивно клонировать все ключи и значения.
     * В deepCopy содержатся независимые списки, а не ссылки на оригинальные.
     */
    private static void deepCloneMap() {
        System.out.println("======== DeepCloneMap: ");

        Map<String, List<Integer>> original = new HashMap<>();
        original.put("One", new ArrayList<>(List.of(1, 2, 3)));
        original.put("Two", new ArrayList<>(List.of(4, 5, 6)));

        // Глубокое клонирование
        Map<String, List<Integer>> deepCopy = new HashMap<>();
        for (Map.Entry<String, List<Integer>> entry : original.entrySet()) {
            deepCopy.put(entry.getKey(), new ArrayList<>(entry.getValue())); // Копируем список
        }

        // Проверка: изменения в оригинале не влияют на копию
        deepCopy.get("One").add(99);
        System.out.println("Original: " + original); // Original: {One=[1, 2, 3], Two=[4, 5, 6]}
        System.out.println("Deep Copy: " + deepCopy); // Deep Copy: {One=[1, 2, 3, 99], Two=[4, 5, 6]}
    }
}
