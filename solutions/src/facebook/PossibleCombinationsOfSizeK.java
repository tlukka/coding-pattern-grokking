package facebook;

import java.util.ArrayList;
import java.util.List;

public class PossibleCombinationsOfSizeK {
    // Find all subsets of size K from a given number N (1 to N)
    // Given two integers N and K, Write an algorithm to find subsets of size K from the numbers 1 to N.

    public static void main(String[] args) {
        PossibleCombinationsOfSizeK sl = new PossibleCombinationsOfSizeK();
        sl.findCombinations(6, 5);
    }

    void findCombinations(int N, int K) {
        backTracking(N, K, 1, new ArrayList<>());
    }

    void backTracking(int N, int K, int start, List<Integer> list) {
        if (K == 0) {
            System.out.println(list);
            return;
        }
        for (int i = start; i <= N; i++) {
            list.add(i);
            backTracking(N, K - 1, i + 1, list);
            list.remove(list.size() - 1);
        }
    }

    //Given an array of integers of size N, print all the subsets of size k. (k<=N)
    void subset(int[] A, int k, int start, int currLen, boolean[] used) {
        if (currLen == k) {
            for (int i = 0; i < A.length; i++) {
                if (used[i])
                    System.out.print(A[i] + "");
            }
            System.out.println("");
            return;
        }
        if (start == A.length)
            return;

        // For every index we have two options,
        // 1.. Either we select it, means put true in used[] and make currLen+1
        used[start] = true;
        subset(A, k, start + 1, currLen + 1, used);
        // 2.. OR we dont select it, means put false in used[] and dont increase
        // currLen
        used[start] = false;
        subset(A, k, start + 1, currLen, used);

    }

    // Find all unique combinations of exact K numbers (from 1 to 9 ) with sum to N
    // Given two integers N and K. Write an algorithm to find all the combinations of k numbers which sum to N.
    void findCombinationsWithSum(int n, int k) {
        System.out.println("N = " + n + " K = " + k);
        List<Integer> combinationList = new ArrayList<>();
        combinationUtil(k, n, 0, 1, combinationList);
    }

    void combinationUtil(int k, int n, int sum, int start, List<Integer> combinationList) {
        if (k == 0) {
            if (sum == n) {
                System.out.println(combinationList);
            }
            return;
        }
        for (int i = start; i <= 9; i++) {
            combinationList.add(i);
            combinationUtil(k - 1, n, sum + i, i + 1, combinationList);
            combinationList.remove(combinationList.size() - 1);
        }
    }

    // Given an array of positive integers and integer ‘K’. Write an algorithm to count all the possible sub arrays
    // where product of all the elements in the sub array is less than k.
    int countSubArraysProudctLessThanK(int[] arr, int k) {
        int start = 0;
        int end = 1;
        long product = arr[0];
        int count = 0;
        while (start <= end && end <= arr.length) {
            if (product < k) {
                count += end - start;
                if (end < arr.length) {
                    product *= arr[end];
                    end++;
                }
            } else {
                product /= arr[start++];
            }
        }
        return count;
    }

    // Given an array A[], write an algorithm to find the Maximum difference between two elements
    // where the larger element appears after the smaller element or in other words find A[i] and A[j]
    // such that A[j]-A[i] is maximum where j > i.
    static int maxDifference(int[] A) {
        int size = A.length;
        int maxDiff = -1;
        int max_so_far = A[size - 1]; //assign the last element
        for (int i = size - 2; i > 0; i--) {
            if (max_so_far < A[i])
                max_so_far = A[i];
            else if (max_so_far > A[i])
                maxDiff = Math.max(maxDiff, max_so_far - A[i]);
        }
        return maxDiff;
    }

    // Minimum Coin Change Problem
    int cointChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 0; // 0 coins are required to make the change for 0
        for (int amt = 1; amt <= amount; amt++) {
            // Now try taking every coin one at a time and pick the minimum
            for (int i = 0; i < coins.length; i++) {
                if (coins[i] <= amt) { // check if coin value is less than amount
                    dp[amt] = Math.min(dp[amt - coins[i]] + 1, dp[amt]);
                }
            }
        }
        return dp[amount];
    }

    // Given "n", generate all valid parenthesis strings of length "2n".
    void printParentheses(int n) {
        validparentheses(n / 2, n / 2, "");
    }

    void validparentheses(int openP, int closeP, String parenttheseString) {
        if (openP == 0 && closeP == 0) {
            System.out.println(parenttheseString);
            return;
        }
        if (openP > closeP)
            return;
        if (openP > 0)
            validparentheses(openP - 1, closeP, parenttheseString + "(");
        if (closeP > 0)
            validparentheses(openP, closeP - 1, parenttheseString + ")");
    }
}
