package grokkingpattern.google.subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class subsets {
    // https://leetcode.com/problems/permutations/
    // Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
    List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfsPermute(nums, new ArrayList<>(), result);
        return result;
    }

    void dfsPermute(int[] nums, List<Integer> list, List<List<Integer>> result) {
        if (list.size() == nums.length)
            result.add(new ArrayList<>(list));
        else {
            for (int i = 0; i < nums.length; i++) {
                if (list.contains(nums[i]))
                    continue; // skip as item already exists
                list.add(nums[i]);
                dfsPermute(nums, list, result);
                list.remove(list.size() - 1);
            }
        }
    }

    // https://leetcode.com/problems/subsets/
    // Given an integer array nums of unique elements, return all possible subsets

    List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return result;

        dfsSubsets(nums, new ArrayList<>(), result, 0);
        return result;
    }

    void dfsSubsets(int[] nums, List<Integer> list, List<List<Integer>> subsets, int start) {
        subsets.add(new ArrayList<>(list));
        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            dfsSubsets(nums, list, subsets, i + 1);
            list.remove(list.size() - 1);
        }
    }

    // https://leetcode.com/problems/subsets-ii/
    // Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
    List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        dfsSubsetsII(nums, 0, new ArrayList<>(), result);
        return result;
    }

    void dfsSubsetsII(int[] nums, int start, List<Integer> list, List<List<Integer>> result) {
        result.add(new ArrayList<>(list));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i - 1] == nums[i])
                continue; // duplicate to skip
            list.add(nums[i]);
            dfsSubsetsII(nums, i + 1, list, result);
            list.remove(list.size() - 1);
        }
    }

    // https://leetcode.com/problems/generate-parentheses/
    // Generate Parentheses
    List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        backtrackParenthesis(list, "", 0, 0, n);
        return list;
    }

    void backtrackParenthesis(List<String> list, String str, int open, int close, int max) {
        if (str.length() == max * 2) {
            list.add(str);
            return;
        }
        if (open < max) {
            backtrackParenthesis(list, str + "(", open + 1, close, max);
        }
        if (close < open) {
            backtrackParenthesis(list, str + ")", open, close + 1, max);
        }
    }

    // Letter Case Permutation
    // https://leetcode.com/problems/letter-case-permutation
    // Return a list of all possible strings we could create. Return the output in any order
    List<String> ans = new ArrayList<>();

    public List<String> letterCasePermutation(String s) {
        char[] arr = s.toCharArray();
        letterPermutationDfs(0, arr);
        return ans;
    }

    void letterPermutationDfs(int i, char[] arr) {
        if (i >= arr.length) {
            ans.add(String.valueOf(arr));
            return;
        }
        letterPermutationDfs(i + 1, arr);
        if (arr[i] >= 'A') {
            arr[i] ^= 32;
            letterPermutationDfs(i + 1, arr);
        }
    }

    // Different Ways to Add Parentheses
    // https://leetcode.com/problems/different-ways-to-add-parentheses

}
