package tasks.leet_code;

import java.util.HashMap;
import java.util.Map;

public class MajorityElement {

    /*
Example 1:

Input: nums = [3,2,3]
Output: 3

Example 2:

Input: nums = [2,2,1,1,1,2,2]
Output: 2

     */
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            if (map.containsKey(num)) {
                var k = map.get(num);
                map.put(num, ++k);
            } else {
                map.put(num, 1);
            }
        }

        int w = nums.length / 2;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > w) {
//                System.out.println(entry.getKey() + " -> " + entry.getValue());
                return entry.getKey();
            }
        }

        return 0;
    }

    public int majorityElement2(int[] nums) {
        int count = 0;
        int candidate = 0;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }
}
