package java_core.class_types;

public final class UtilClass {

    private UtilClass() {
    }

    //static parametrized method
    public static <T> void staticMethod(T item) {
        System.out.println(item);
    }

}
