package java_core.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Ковариантность, контравариантность и инвариантность
 * Covariance, contravariance and invariance
 */
public class CovarianceContravarianceInvarianceExample {

    public static void example() {
//        covarianceArrays();
        invarianceGenerics();
        covarianceGenerics();
        contravarianceGenerics();
    }

    /**
     * Массивы - ковариантны
     * Тип S[] является подтипом T[], если S — подтип T.
     * Минусы мы не можем выполнить проверки на этапе компиляции,
     * и что-то может сломаться уже в рантайме.
     */
    private static void covarianceArrays() {
        String[] strings = new String[]{"a", "b", "c"};

        //Мы присвоили ссылку на массив строк переменной arr, тип которой – «массив объектов».
        Object[] arr = strings;
        arr[0] = 42; // ArrayStoreException. Проблема обнаружилась на этапе выполнения программы.
    }

    private static void invarianceGenerics() {
        List<Integer> ints = Arrays.asList(1, 2, 3);
//        List<Number> nums = ints; // compile-time error. Проблема обнаружилась на этапе компиляции
    }

    /**
     * Ковариантность. List<Integer> — подтип List<? extends Number>
     * Number <= ? extends Object
     * ? extends Number <=? extends Object
     * extends B — символ подстановки с указанием верхней границы B
     */
    private static void covarianceGenerics() {
        List<Integer> ints = new ArrayList<>();
        List<? extends Number> nums = ints;
    }

    /**
     * Контравариантность. List<Number> является подтипом List<? super Integer>.
     * ? super Object <=? super Number
     * super B — символ подстановки с указанием нижней границы B
     */
    private static void contravarianceGenerics() {
        List<Number> nums = new ArrayList<>();
        List<? super Integer> ints = nums;
    }
}
