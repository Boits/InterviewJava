package tasks.leet_code;

import java.util.Arrays;

public class AppTasksLeetCode {

    public static void mainTasksLeetCode() {
//        task1();
//        task2();
//        task3();
//        task4();
//        task5();
//        task6();
        task7();
    }

    private static void task7() {
        var romanToInteger = new RomanToInteger();

//        String s = "III"; //3
//        String s = "LVIII"; //58
//        String s = "MCMXCIV"; //1994
        String s = "DCXXI"; //621

        var res = romanToInteger.romanToInt(s);

        System.out.println(res);
    }

    private static void task6() {
        var buyAndSellStock = new BestTimeToBuyAndSellStock();

        int[] num = new int[]{7, 1, 5, 3, 6, 4}; //5
//        int[] num = new int[]{7, 6, 4, 3, 1};//0
//        int[] num = new int[]{1};//0
//        int[] num = new int[]{1, 2};//1
//        int[] num = new int[]{1, 2, 4};//3
//        int[] num = new int[]{2, 4, 1};//2

        var profit = buyAndSellStock.maxProfit(num);

        System.out.println(profit);
    }

    private static void task5() {
        var majorityElement = new MajorityElement();

        int[] num = new int[]{3, 2, 3}; //3
//        int[] num = new int[]{2, 2, 1, 1, 1, 2, 2};//2
        var k = majorityElement.majorityElement(num);

        System.out.println(k);
        System.out.println(Arrays.toString(num));
    }

    private static final RemoveDuplicatesFromSortedArray removeDuplicates = new RemoveDuplicatesFromSortedArray();

    private static void task4() {
//        int[] num = new int[]{1, 1, 1, 2, 2, 3}; //5
//        int[] num = new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3};//7
//        int[] num = new int[]{1, 1, 1, 1};//2
//        int[] num = new int[]{1, 2, 2, 2};//3
        int[] num = new int[]{1, 1, 1, 1, 2, 2, 3}; //5

        System.out.println(Arrays.toString(num));
        var k = removeDuplicates.removeDuplicates2(num);

        System.out.println(k);
        System.out.println(Arrays.toString(num));
    }

    private static void task3() {
//        int[] num = new int[]{1, 1, 2};//2
        int[] num = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}; //5
        System.out.println(Arrays.toString(num));
        var k = removeDuplicates.removeDuplicates(num);

        System.out.println(k);
        System.out.println(Arrays.toString(num));
    }

    private static void task2() {
        var removeElement = new RemoveElement();

//        int[] num = new int[]{0, 1, 2, 2, 3, 0, 4, 2};
//        var k = removeElement.removeElement(num, 2); //5

        int[] num = new int[]{3, 2, 2, 3};
        var k = removeElement.removeElement(num, 3); //2

        System.out.println(k);
        System.out.println(Arrays.toString(num));
    }

    private static void task1() {
        int[] num1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] num2 = new int[]{2, 5, 6};

        var mergeSortedArray = new MergeSortedArray();
        mergeSortedArray.merge(num1, 3, num2, 3);
    }
}
