package java8.my_annotation.way1;

public class MyService implements MyServiceApi {
    @MyCustomAnnotation(value = "custom value")
    public void myMethod() {
        System.out.println("This is a method with a custom annotation.");
    }
}

