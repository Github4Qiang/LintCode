package dstructure.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Polylanger on 9/13/2017.
 */
public class CombinationSum {

    public static void main(String[] args) {
        CombinationSum cs = new CombinationSum();
        int[] candidates = {2, 3, 5};
        int target = 6;
        System.out.println(cs.combinationSum(candidates, target));
    }

    /**
     * @param candidates: A list of integers
     * @param target:An   integer
     * @return: A list of lists of integers
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // write your code here
        ArrayList<Integer> sigleton = new ArrayList<Integer>();
        for (int value : candidates) {
            if (!sigleton.contains(value))
                sigleton.add(value);
        }

        int n = sigleton.size();
        Integer[] temp = new Integer[n];
        int[] descend = new int[n];
        sigleton.toArray(temp);
        Arrays.sort(temp);
        for (int i = 0; i < n; i++) {
            descend[i] = temp[n - i - 1];
        }

        List<List<Integer>> combinations = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        combine(descend, target, 0, combinations, list);
        return combinations;
    }

    private void combine(int[] candidates, int target, int start,
                         List<List<Integer>> combinations, List<Integer> list) {
        if (start >= candidates.length) return;

        if (target == 0) {
            List<Integer> combination = new ArrayList<Integer>(list);
            combinations.add(combination);
            return;
        } else if (target < 0) {
            return;
        } else {
            list.add(0, candidates[start]);
            combine(candidates, target - candidates[start], start, combinations, list);
            if (start < candidates.length - 1) {
                list.remove(0);
                combine(candidates, target, start + 1, combinations, list);
            } else {
                list.remove(0);
            }
        }
    }

}
