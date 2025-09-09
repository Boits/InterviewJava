package java_core.interface_abstract_class;

public class InterfaceAbstractExample {

    public static void method() {
        System.out.println("======= Abstract class: ");
        AbstractBird mockingjay = new Mockingjay();
        mockingjay.setAge(19);
        System.out.println(mockingjay.getAge());

        mockingjay.fly();
        mockingjay.finalMethod();
        AbstractBird.staticMethod();


        System.out.println("======= Interface class: ");
        FlyableInterface someBird = new SomeBird();

        someBird.fly();
        someBird.defaultMethod();
        FlyableInterface.staticMethod();
    }
}
