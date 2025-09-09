package tasks;

public class AppTask {

    public static void mainTasks() {
//        task8();
//        task9();
        task10();

//        System.out.println(StreamSwitch.getDayName(1));
    }

    private static void task10() {
        ReverseString.reverseString();
    }

    private static void task9() {
        var solution = new CountSubstrings();

        int res = solution.countSubstrings("as-as-as-asa", "-as"); //3

        System.out.println(res);
    }

    private static void task8() {
        var palindrome = new Palindrome();

        boolean res = palindrome.isPalindrome("1132111");

        System.out.println(res);
    }
}
