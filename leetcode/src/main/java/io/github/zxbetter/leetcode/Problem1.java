package io.github.zxbetter.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 *
 * @author zxbetter
 */
public class Problem1 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(16);
        int a;

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(a = target - nums[i])) {
                return new int[]{map.get(a), i};
            }

            map.put(nums[i], i);
        }

        return new int[2];
    }

    public static void main(String[] args) {
        int[] input = {2, 7, 11, 15};
        int target = 9;

        int[] result = new Problem1().twoSum(input, target);
        for (int i : result) {
            System.out.println(i);
        }
    }
}
