package java_core;

import java.nio.charset.Charset;

public class StringExample {

    public static void stringExample() {
        stringPool();
//        stringBuffer();
    }

    private static void stringPool() {
        String str1 = new String("Hello"); //Создается в Heap (не в String Pool)
        String str2 = str1.intern();  // Помещаем строку в String Pool
        System.out.println(str1 == str2);  // false

        String str3 = "Hello";
        System.out.println(str2 == str3);  // true

        String str4 = "TopJava";
        String str5 = "Java";
        String str6 = "Top" + str5;
        System.out.println("Строка str4 равна строке str6? " + (str4 == str6)); //false
        // т.к. интернирование происходит во время компиляции, а тут в рантайме
        String str7 = ("Top" + str5).intern();
        System.out.println("Строка str4 равна строке str7? " + (str4 == str7)); //true

        String s = " Wake up, Neo! "; //новый объект
        s = s.toUpperCase(); //новый объект
        s = s.trim(); //новый объект
        System.out.println("\"" + s + "\"");

        byte[] habrAsArrayOfBytes = {104, 97, 98, 114, 97, 104, 97, 98, 114}; //"habrahabr"
        String string = new String(habrAsArrayOfBytes, Charset.forName("UTF-16BE"));
        System.out.println(string); //桡扲慨慢�
    }

    private static void stringBuffer() {
        String numbers = "0123456789";

        StringBuffer sb = new StringBuffer(numbers);

        System.out.println(sb.substring(3)); //3456789
        System.out.println(sb.substring(4, 8)); //4567
        System.out.println(sb.replace(3, 5, "ABCDE")); //012ABCDE56789

        sb = new StringBuffer(numbers);
        System.out.println(sb.reverse()); //9876543210

        sb = new StringBuffer(numbers);
        System.out.println(sb.delete(5, 9)); //012349
        System.out.println(sb.deleteCharAt(1)); //02349
        System.out.println(sb.insert(1, "One")); //0One2349
    }
}
