package java8;

@FunctionalInterface //имеет только 1 абстрактный метод
public interface ConverterFunctionalInterface<T, F> {

    T convert(F from);

    // Методы по умолчанию
    default void defaultMethod() {
        System.out.println("This is a default method.");
    }

    // Статический метод
    static void staticMethod() {
        System.out.println("This is a static method.");
    }

    boolean equals(Object obj); // метод класса Object
}
