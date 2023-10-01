package grokkingpattern;

public class SlidingWindowSolutions {
    public static void main(String[] args) {
        SlidingWindowSolutions sol = new SlidingWindowSolutions();

        System.out.println(sol.findMaxAverage(new int[]{1, 12, -5, -6, 50, 3}, 4));
        System.out.println(sol.findMaxAverage(new int[]{-1}, 1));
        System.out.println(sol.maxSumSubArray(new int[]{1, 4, 2, 10, 2, 3, 1, 0, 20}, 4));
        System.out.println(sol.minSubArrayLen(11, new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1}));
    }

    // Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value
    // Input: nums = [1,12,-5,-6,50,3], k = 4 ===> Output: 12.75000
    double findMaxAverage(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return 0.0;
        int start = 0;
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            if (right >= k - 1) {
                maxSum = Math.max(sum, maxSum);
                sum -= nums[start];
                start++;
            }
        }
        return (double) maxSum / k;

    }

    // Given an array nums and a target value k, find the maximum length of a subarray that sums to k
    // nums = [1, -1, 5, -2, 3], k = 3

    int maxSumSubArray(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length < k)
            return -1;
        int sum = 0, maxSum = 0, start = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (i >= k - 1) {
                maxSum = Math.max(sum, maxSum);
                sum -= nums[start++];
            }
        }
        return maxSum;
    }

    // Given an array of positive integers nums and a positive integer target, return the minimal length of a
    //subarray  whose sum is greater than or equal to target
    // Input: target = 7, nums = [2,3,1,2,4,3] == >Output: 2
    // Input: target = 11, nums = [1,1,1,1,1,1,1,1] ==> Output: 0
    int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int minLength = Integer.MAX_VALUE;
        int start = 0, sum = 0;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= target) {
                minLength = Math.min(minLength, right - start + 1);
                sum -= nums[start++];
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    //Find the longest substring with k unique characters in a given string
    //Input: Str = “aabbcc”, k = 1 => Output: 2
    // Input: Str = “aabbcc”, k = 2 ==> Output: 4
    int longestkSubstr(String S, int k) {
        return -1;
    }
}
