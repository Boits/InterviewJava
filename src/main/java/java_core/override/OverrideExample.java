package java_core.override;

import java_core.static_override.A;
import java_core.static_override.B;

public class OverrideExample {

    public static void method() {
        staticOverride_hiding();

        System.out.println("Parent parent = new Parent()");
        Parent parent = new Parent();
        parent.method(); // Вызов метода из Parent
        System.out.println(parent.getMe()); //parent
        System.out.println(parent.me2);//parent
        System.out.println(parent.getMe2()); //parent

        System.out.println("Child child = new Child()");
        Child child = new Child();
        child.method(); //Вызов переопределенного метода из Child
        System.out.println(child.getMe()); //child
        System.out.println(child.me2);//child
        System.out.println(child.getMe2()); //child

        System.out.println("Parent child1 = new Child()");
        Parent child1 = new Child();
        child1.method(); // Полиморфизм: вызов переопределенного метода из Child
        System.out.println(child1.getMe()); //child
        System.out.println(child1.me2);//parent
        System.out.println(child1.getMe2()); //child
    }

    private static void staticOverride_hiding() {
        A a = new B();
        a.staticMethod(); // in class A

        B b = new B();
        b.staticMethod(); // in class B
    }
}
