package java_core.constructors_exceptions;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

public class Dog extends Animal {

    String tail; //хвост

    static {
        //Выполняется во время загрузки класса 1 раз в самом начале
        System.out.println("Статический блок подкласса Dog");
    }

    {
        //Выполняется каждый раз перед конструктором подскласса, при создании объекта подскласса
        System.out.println("Не статический блок подкласса Dog");
    }

//    public Dog() {
//        System.out.println("Конструктор подкласса Dog по умолчанию");
//    }

    public Dog(String brain, String heart, String tail) {
        super(brain, heart); //обязательно используем, если есть какой-то конструктор в Animal
        this.tail = tail;
        System.out.println("Конструктор подкласса Dog с параметрами");
    }

    @Override
    public void sound() {
        System.out.println("sound() dog");
    }

    @Override
    public void testException() throws IOException, AccessDeniedException, RuntimeException {
        super.testException();
        System.out.println("testException() в подклассе Dog");
        throw new AccessDeniedException("");
    }

}
