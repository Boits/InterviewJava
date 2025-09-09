package java_core.constructors_exceptions;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

public class ConstructorsExample {

    public static void constructors() {
        Bird b1 = new Bird();
        b1.fly();

        Bird b2 = new Bird("Синица");
        b2.fly();

        Bird b3 = new Bird("Орел", 200);
        b3.fly();

        /**
         * Статический блок класса Bird
         * Не статический блок класса Bird
         * Конструктор Bird по умолчанию
         * fly(): null летит 0 км/ч
         * Не статический блок класса Bird
         * Конструктор Bird c 1 параметром
         * fly(): Синица летит 0 км/ч
         * Не статический блок класса Bird
         * Конструктор Bird c 2мя параметрами
         * fly(): Орел летит 200 км/ч
         */

        System.out.println();
        System.out.println("При наследовании классов: ");

        Animal d = new Dog("Brain Dog", "Heart Dog", "Tail Dog");
        d.sound();

        /**
         * Статический блок суперкласса Animal
         * Статический блок подкласса Dog
         * Не статический блок суперкласса Animal
         * Конструктор суперкласса Animal c параметрами
         * Не статический блок подкласса Dog
         * Конструктор подкласса Dog с параметрами
         * sound() dog
         */
    }

    public static void exceptions() {
        MyExceptions ex = new MyExceptions();
        ex.exception();
//        ex.exception2();
//        ex.tryWithResources();
//        ex.inheritanceException();
    }
}
