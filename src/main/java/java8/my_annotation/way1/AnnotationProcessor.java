package java8.my_annotation.way1;

import java.lang.reflect.Method;

public class AnnotationProcessor {

    public static void main(String[] args) throws Exception {
        MyServiceApi original = new MyService();
        MyServiceApi proxied  = AnnotationProxy.wrap(original, MyServiceApi.class);
        // ВАЖНО: вызывать через proxied, а не original
        proxied.myMethod();

//        checkAnnotation();
    }

    private static void checkAnnotation() throws Exception {
        MyService myService = new MyService();
        Method method = myService.getClass().getMethod("myMethod");

        // Проверяем, есть ли на методе аннотация
        if (method.isAnnotationPresent(MyCustomAnnotation.class)) {
            MyCustomAnnotation annotation = method.getAnnotation(MyCustomAnnotation.class);
            System.out.println("Annotation value: " + annotation.value());
        }
        // Вызов метода
        method.invoke(myService);
    }
}

