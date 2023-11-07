package grokkingpattern;

import java.util.Arrays;

public class GreedySolutions {


    // Given a binary array nums, return the maximum number of consecutive 1's in the array.
    // Input: nums = [1,1,0,1,1,1] ==> Output: 3
    int findMaxConsecutiveOnes(int[] nums) {
        int countOnes = 0, maxCount = 0;
        for (int n : nums) {
            if (n == 1) {
                maxCount = Math.max(maxCount, ++countOnes);
            } else {
                countOnes = 0;
            }
        }
        return maxCount;

    }

    // Return the maximum profit you can achieve from this transaction
    // Given array prices where prices[i] is the price of a given stock on the ith day.
    // Input: prices = [7,1,5,3,6,4] ===> Output: 5
    int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        int min_price = prices[0], max_profit = Integer.MIN_VALUE;
        for (int i = 1; i < prices.length; i++) {
            min_price = Math.min(prices[i], min_price);
            max_profit = Math.max(prices[i] - min_price, max_profit);
        }
        return max_profit;
    }

    // given an integer array nums Return true if you can reach the last index, or false otherwise.
    // Input: nums = [2,3,1,1,4] ==> Output: true

    boolean canJump(int[] nums) {
        int reachable = 0;
        for (int i = 0; i < nums.length; i++) {
            reachable = Math.max(reachable, i + nums[i]);
            if (reachable >= nums.length - 1)
                return true;
        }
        return false;
    }

    int jump(int[] nums) {
        int jums = 0, currenEnd = 0, farestCurrent = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            farestCurrent = Math.max(farestCurrent, i + nums[i]);
            if (i == currenEnd) {
                currenEnd = farestCurrent;
                jums++;
            }
        }
        return jums;
    }

    // Given an integer array bills where bills[i] is the bill the ith customer pays,
    // return true if you can provide every customer with the correct change, or false otherwise.
    // https://leetcode.com/problems/lemonade-change
    boolean lemonadeChange(int[] bills) {
        int fives = 0, tens = 0;
        for (int bill : bills) {
            if (bill == 5)
                fives++;
            else if (bill == 10) {
                if (fives == 0)
                    return false;
                fives--;
                tens++;
            } else {
                if (tens > 0 && fives > 0) {
                    tens--;
                    fives--;
                } else if (fives >= 3) {
                    fives -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }


    // Given two integer arrays gas and cost, return the starting gas station's index
    // if you can travel around the circuit once in the clockwise direction, otherwise return -1
    // https://leetcode.com/problems/gas-station
    int canCompleteCircuit(int[] gas, int[] cost) {
        int total = 0;
        int curr = 0;
        int startIndex = 0;
        for (int i = 0; i < gas.length; i++) {
            total += gas[i] - cost[i];
            curr += gas[i] - cost[i];
            if (curr < 0) {
                curr = 0;
                startIndex = i + 1;
            }

        }
        if (total < 0)
            return -1;
        return startIndex;
    }

    // Return the minimum number of candies you need to have to distribute the candies to the children.
    // https://leetcode.com/problems/candy/
    int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];
        Arrays.fill(candies, 1);
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1])
                candies[i] = candies[i - 1] + 1;
        }

        int total = candies[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1])
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            total += candies[i];
        }

        return total;
    }
}
