package dstructure.array;

import java.util.HashMap;

/**
 * Created by Polylanger on 10/3/2017.
 */
public class SlidingWindowUniqueElementsSum {

    public static void main(String[] args) {
        SlidingWindowUniqueElementsSum sum = new SlidingWindowUniqueElementsSum();
        System.out.println(sum.slidingWindowUniqueElementsSum(new int[]{1, 1, 1, 1, 1, 1}, 2));
    }

    /**
     * @param : the given array
     * @param : the window size
     * @return: the sum of the count of unique elements in each window
     */
    public int slidingWindowUniqueElementsSum(int[] nums, int k) {
        int sum = 0, last = 0;
        HashMap<Integer, Integer> table = new HashMap<Integer, Integer>();
        int start = 0;
        do {
            last = uniqueElementSum(nums, start, k, last, table);
            sum += last;
        } while (++start <= nums.length - k);
        return sum;
    }

    private int uniqueElementSum(int[] nums, int start, int k, int uniques, HashMap<Integer, Integer> table) {
        if (start == 0) {
            for (int i = start; i < start + k && i < nums.length; i++) {
                int count = 0;
                if (table.containsKey(nums[i]))
                    count = table.get(nums[i]);
                table.put(nums[i], count + 1);
            }
            for (Integer key : table.keySet()) {
                if (table.get(key) == 1) {
                    uniques += 1;
                }
            }
        } else {
            int numOfPrev = table.get(nums[start - 1]);
            table.put(nums[start - 1], numOfPrev - 1);
            if (numOfPrev == 2) {
                uniques += 1;
            } else if (numOfPrev == 1) {
                uniques -= 1;
            }

            int numOfNext = 0;
            if (table.containsKey(nums[start + k - 1]))
                numOfNext = table.get(nums[start + k - 1]);
            table.put(nums[start + k - 1], numOfNext + 1);
            if (numOfNext == 0) {
                uniques += 1;
            } else if (numOfNext == 1) {
                uniques -= 1;
            }
        }

        return uniques;
    }

}
