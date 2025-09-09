package tasks.dynamic_task;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.Stream;

public class Solution {

    public int factrorial(int n) {
        if (n == 1) {
            return 1;
        }
        return n * factrorial(n - 1);
    }

    public BigInteger fibonacсi(int n) {
        /*
        if (n == 1) {
            return BigInteger.ZERO;
        }
        if (n == 2) {
            return BigInteger.ONE;
        }
        return fibonacсi(n - 2).add(fibonacсi(n - 1));

         */
        if (n <= 1) {
            return BigInteger.valueOf(n);
        }

        BigInteger a = BigInteger.ZERO;
        BigInteger b = BigInteger.ONE;

        for (int i = 2; i < n; i++) {
            BigInteger temp = a.add(b);
            a = b;
            b = temp;
        }

        return b;
    }

    public BigInteger fibonacсiWithStreams(int n) {
        return Stream.iterate( //создает бесконечный поток с рекурсией
                        new BigInteger[]{BigInteger.ZERO, BigInteger.ONE},  // начальные значения F(0) = 0, F(1) = 1
                        fib -> new BigInteger[]{
                                fib[1], fib[0].add(fib[1])
                        }) // создаем следующий элемент последовательности
                .limit(n)  // Ограничиваем поток n элементами
                .map(fib -> fib[0])  // Извлекаем первое число из массива (это текущее число Фибоначчи)
                .reduce((first, second) -> second)  // Берем последний элемент из потока
                .get();  // Получаем значение из Optional
    }
}
