package java_core.class_types;

public class ExternalClass {
    private int externalNonStaticField = 11;

    /**
     * 1 - Внутренний класс (Inner class)
     * <p>
     * Имеет доступ ко всем полям и методам внешнего класса, даже к приватным.
     * <p>
     * Создание объекта внутреннего класса через объект внешнего класса
     * ExternalClass external = new ExternalClass();
     * ExternalClass.InnerClass inner = external.new InnerClass();
     * inner.display();
     * <p>
     * Внутренние классы помогают инкапсулировать вспомогательную логику,
     * делают код короче и понятнее, и дают доступ к контексту внешнего объекта.
     */
    public class InnerClass {
        public void display() {
            System.out.println("External field: " + externalNonStaticField); // Имеет доступ к полям внешнего класса
        }
    }

    /**
     * 2 - Статический вложенный класс (Static Nested class)
     * <p>
     * Может обращаться только к статическим полям и методам внешнего класса.
     * <p>
     * Для создания объекта статического вложенного класса не нужно создавать объект внешнего класса.
     * <p>
     * ExternalClass.StaticNestedClass nestedObject = new ExternalClass.StaticNestedClass();
     * nestedObject.display();
     * <p>
     * Примеры: 1) HashMap.Entry
     * 2) объявление собственного компаратора (Comparator),
     * например компаратор по возрасту (AgeComparator) в классе сотрудники (Employee).
     */

    private static int externalStaticField = 10;

    //static parametrized method
    public static <T> void staticMethod(T item) {
        System.out.println(item);
    }

    public static class StaticNestedClass {
        public void display() {
            System.out.println("External field: " + externalStaticField); // Может получить доступ к статическим полям внешнего класса
//            System.out.println("External non static field: " + externalNonStaticField); //не может
            staticMethod("String type");
        }
    }


    /**
     * Локальный класс (Local class) — это класс, объявленный внутри метода, конструктора или блока кода.
     * Он доступен только внутри этого метода и может обращаться к переменным метода,
     * если они являются финальными (или эффективно финальными, начиная с Java 8).
     * <p>
     * Локальные классы полезны для инкапсуляции одноразовой логики внутри метода или блока кода.
     */
    public void someMethod() {
        final int localVar = 20;

        // Локальный класс внутри метода
        class LocalClass {
            public void display() {
                System.out.println("Local variable: " + localVar);
            }
        }

        LocalClass localClass = new LocalClass();
        localClass.display();
    }

    /**
     * Анонимные классы могут быть созданы на основе интерфейсов, абстрактных классов или даже обычных классов.
     * <p>
     * Удобны, когда нужно быстро создать реализацию
     * интерфейса или абстрактного класса без необходимости создания отдельного именованного класса.
     */
    public void createAnonymousClass() {
        // Создание анонимного класса на основе интерфейса Runnable
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous class running");
            }
        };
        //or
        // Runnable runnable = () -> System.out.println("Anonymous class running");

        // Вызов метода run анонимного класса
        runnable.run();  // Вывод: "Anonymous class running"
    }
}
