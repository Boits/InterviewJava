package java_core.interface_abstract_class;

public abstract class AbstractBird {

    private String species;
    private int age;

    public abstract void fly();

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    //не может содержать default метод
//    default void defaultMethod() {
//    }

    final void finalMethod() {
        System.out.println("Call finalMethod() in AbstractBird");
        privateMethod();
    }

    private void privateMethod() {
        System.out.println("Call privateMethod() in AbstractBird");
    }

    static void staticMethod() {
        System.out.println("Call staticMethod() in AbstractBird");
        privateStaticMethod();
    }

    private static void privateStaticMethod() {
        System.out.println("Call privateStaticMethod() in AbstractBird");
    }
}
