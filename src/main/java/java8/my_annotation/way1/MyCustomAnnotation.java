package java8.my_annotation.way1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Создаем аннотацию MyCustomAnnotation
@Retention(RetentionPolicy.RUNTIME) // Аннотация доступна во время выполнения
@Target(ElementType.METHOD) // Аннотация может применяться к методам
public @interface MyCustomAnnotation {
    String value() default "default value";
}