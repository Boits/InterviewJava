package collections;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class ArraysExample {

    public static void method() {
        arrays();
        System.out.println("===============================");
        detailsArray();
    }

    private static void arrays() {
        int[] myArray = new int[10];

        String[] seasons = {"Winter", "Spring", "Summer", "Autumn"};
        Arrays.sort(seasons, 0, seasons.length);
        String seasonsResult = Arrays.toString(seasons);
        System.out.println(seasonsResult);
        int i = Arrays.binarySearch(seasons, "Spring"); //Сначала нужно отсортировать массив
        System.out.println("Index: " + i); //1

        int[][] myTwoDimensionalArray = new int[8][8]; //двумерный массив/матрица
    }

    /**
     * Массив с нулевым размером представляет собой объект,
     * у которого есть заголовок объекта (Object header) и размер,
     * но нет места в памяти, выделенного для его элементов.
     * При этом ссылка на такой массив является рабочей и сохраняется в переменную.
     */
    private static void emptyArray() {
        String[] array1 = {};
        char[] array2 = new char[0];
    }

    /**
     * Тип массива - class [I
     * [ - говорит, что это одномерный массив
     * I, что он содержит целые числа
     * Суперкласс типа массива - class java.lang.Object
     * Доступные методы:
     * equals
     * toString
     * hashCode
     * getClass
     * notify
     * notifyAll
     * wait
     * wait
     * wait
     * clone() тоже доступен, но не отображается в списке, т.к. getMethods() возвращает только public-методы.
     */
    private static void detailsArray() {
        int[] array = {};
        System.out.println("Тип массива - " + array.getClass()); //class [I
        System.out.println("Суперкласс типа массива - " + array.getClass().getSuperclass()); //class java.lang.Object

        System.out.println("Доступные методы:");
        for (Method m : array.getClass().getMethods()) {
            System.out.println(m.getName());
        }
    }

    private static int[] removeElement(int[] arr, int index) {
        var newArr = new int[arr.length - 1];

        for (var i = 0; i < arr.length; i++) {
            if (i != index) { // Копируем все кроме index
                // Элементы стоящие дальше index смещаются влево
                var newIndex = i < index ? i : i - 1;
                newArr[newIndex] = arr[i];
            }
        }
        return newArr;
    }

    private int[] addElementToEnd(int[] arr, int newElement) {
        int[] newArr = new int[arr.length + 1];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        newArr[newArr.length - 1] = newElement;
        return newArr;

        //или
//        arr = Arrays.copyOf(arr, arr.length + 1);
//        arr[arr.length - 1] = newElement;
//        return arr;
    }
}
