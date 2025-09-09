package java_core.override;

public class Child extends Parent {

    private static final String me = "child";
    String me2 = "child";

    @Override
    public void method() {
        System.out.println("Child");
    }

    @Override
    public String getMe() {
        return me;
    }

    @Override
    Object getMe2() {
        return me2;
    }

    /**
     * В переопределенном методе можно использовать подтип возвращаемого значения.
     */
    @Override
    Child getObject() {
        return new Child();
    }
}
