package java_core.interface_abstract_class;

public class SomeBird implements FlyableInterface {
    @Override
    public void fly() {
        System.out.println("Лети, птичка! in SomeBird");
    }
}
