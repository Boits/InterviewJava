package java_core.constructors_exceptions;

import javax.naming.AuthenticationException;
import java.io.IOException;

public class Animal {
    String brain;
    String heart;

    static {
        //Выполняется во время загрузки класса 1 раз в самом начале
        System.out.println("Статический блок суперкласса Animal");
    }

    {
        //Выполняется каждый раз перед конструктором суперкласса, при создании объекта суперкласса
        System.out.println("Не статический блок суперкласса Animal");
    }

//    public Animal() {
//        System.out.println("Конструктор суперкласса Animal по умолчанию");
//    }

//    public Animal(String brain) {
//        this.brain = brain;
//        System.out.println("Конструктор суперкласса Animal c 1 параметром");
//    }

    // Перегрузка конструктора
    public Animal(String brain, String heart) {
        this.brain = brain;
        this.heart = heart;
        System.out.println("Конструктор суперкласса Animal c параметрами");
    }

    public void sound() {
        System.out.println("sound() animal; " + "brain: " + brain + "; heart: " + heart);
    }

    public void testException() throws IOException {
        System.out.println("testException() в суперклассе Animal");
        throw new IOException();
    }
}

