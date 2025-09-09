package tasks.leet_code;

import java.util.Arrays;

public class RemoveElement {

    /*
Вывести количество элементов в массиве num, после удаления всех значений val.


Example 1:

Input: nums = [3,2,2,3], val = 3
Output: 2, nums = [2,2,_,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 2.
It does not matter what you leave beyond the returned k (hence they are underscores).


Example 2:

Input: nums = [0,1,2,2,3,0,4,2], val = 2
Output: 5, nums = [0,1,4,0,3,_,_,_]
Explanation: Your function should return k = 5, with the first five elements of nums containing 0, 0, 1, 3, and 4.
Note that the five elements can be returned in any order.
It does not matter what you leave beyond the returned k (hence they are underscores).
     */

    public int removeElement(int[] num, int val) {
        int k = 0;

        for (int i = 0; i < num.length; i++) {
            if (val == num[i]) {
                num[i] = 100;
                k++;
            }
        }

        Arrays.sort(num);

        return num.length - k;
    }

    public int removeElement2(int[] num, int val) {
        int k = 0;

        for (int i = 0; i < num.length; i++) {
            if (num[i] != val) {
                num[k] = num[i];  // Place the non-val element at the index k
                k++;
            }
        }

        return k;
    }
}
