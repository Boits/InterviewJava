package java8.my_annotation.way0;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

@Retention(RetentionPolicy.RUNTIME) // Аннотация доступна во время выполнения
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR}) // Аннотация может применяться к методам и конструкторам
public @interface Alarm {

    public static final int DEFAULT_VALUE = 1;

    String kind(); //обязательный элемент (нет default значения)

    int level() default DEFAULT_VALUE;

    String[] tags() default {};

    MyDescriptionEnum description() default MyDescriptionEnum.DESC_TYPE1;

    Class<?> value() default SecureChannel.class;

//    Integer level(); //не скомпилируется
//    List<String> tags(); //не скомпилируется
//    MyDescriptionClass description(); //не скомпилируется
}
