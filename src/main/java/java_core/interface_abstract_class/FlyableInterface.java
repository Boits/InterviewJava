package java_core.interface_abstract_class;

public interface FlyableInterface {
    String species = new String();
    int age = 10;

    void fly();

    //не может быть final методов
//    final void finalMethod() {
//    }

    default void defaultMethod() {
        System.out.println("Call defaultMethod() in FlyableInterface");
        privateMethod();
    }

    private static void privateMethod() {
        System.out.println("Call privateMethod() in FlyableInterface");
    }

    static void staticMethod() {
        System.out.println("Call staticMethod() in FlyableInterface");
        privateStaticMethod();
    }

    private static void privateStaticMethod() {
        System.out.println("Call privateStaticMethod() in FlyableInterface");
    }

}
