package tasks;

public class ReverseString {

    public static void reverseString() {
//        reverseString1("Anastasiia Boitsova");
//        reverseString2("Anastasiia Boitsova");
//        reverseString3("Anastasiia Boitsova");
//        reverseString4("Anastasiia Boitsova");
        String s = reverseString5("Anastasiia Boitsova");
        System.out.println(s);
    }

    private static String reverseString5(String s) {
        if (s.isEmpty()) {
            return s;
        }
        // Вызываем рекурсивно функцию для оставшейся строки
        return reverseString5(s.substring(1)) + s.charAt(0);
    }

    private static void reverseString4(String s) {
        char[] original = s.toCharArray();
        String reversed = "";

        // Проход по массиву символов с конца к началу
        for (int i = original.length - 1; i >= 0; i--) {
            reversed = reversed + original[i]; //reversed += charArray[i];
        }

        System.out.println("Original: " + s);
        System.out.println("Reversed: " + reversed); // Output: !dlroW ,olleH
    }

    private static void reverseString3(String s) {
        for (int i = s.length() - 1; i >= 0; i--) {
            System.out.print(s.charAt(i));
        }
        System.out.println();
    }

    private static void reverseString2(String s) {
        StringBuilder sb = new StringBuilder();

        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        System.out.println(sb);
    }

    private static void reverseString1(String s) {
        StringBuilder sb = new StringBuilder(s);

        System.out.println(sb.reverse());
    }
}
