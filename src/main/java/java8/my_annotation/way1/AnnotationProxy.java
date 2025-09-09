package java8.my_annotation.way1;

import java.lang.reflect.*;

public class AnnotationProxy implements InvocationHandler {
    private final Object target;

    public AnnotationProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method ifaceMethod, Object[] args) throws Throwable {
        Method impl = target.getClass().getMethod(ifaceMethod.getName(), ifaceMethod.getParameterTypes());
        MyCustomAnnotation ann = impl.getAnnotation(MyCustomAnnotation.class);

        if (ann != null) {
            System.out.println("[BEFORE] " + ann.value());
        }
        Object res = impl.invoke(target, args);

        if (ann != null) {
            System.out.println("[AFTER]");
        }
        return res;
    }

    @SuppressWarnings("unchecked")
    public static <T> T wrap(T target, Class<T> api) {
        return (T) Proxy.newProxyInstance(api.getClassLoader(), new Class[]{api}, new AnnotationProxy(target));
    }
}
