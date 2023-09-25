package blindsolutions.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

    public static void main(String[] args) {
        Solution sl = new Solution();
        //System.out.println(sl.containsDuplicate(new int[] {2,14,18, 22,22}));
        //System.out.println(sl.containsDuplicateBySet(new int[] {2,14,18, 22,22}));
        //int[] productResult = sl.productExceptSelf(new int[]{1, 2, 3, 4});
        //System.out.println(Arrays.toString(productResult));
        //int[] productResult1 = sl.productExceptSelfOptimized(new int[]{1, 2, 3, 4});
        //System.out.println(Arrays.toString(productResult1));
        //System.out.println(sl.findMin(new int[]{11, 13, 15, 17}));
        //System.out.println(sl.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        //System.out.println(sl.mostFrequent(new int[]{1, 3, 2, 2, 3, 4, 1, 3, 3}));
        //System.out.println(sl.nonRepeatedChar("abbca"));
        //System.out.println(sl.nonRepeatedChar("aabb"));
        //System.out.println(sl.nonRepeatedChar("a"));
        //System.out.println(sl.isOneAway("abcde", "abcd"));  // should return true
        //System.out.println(sl.isOneAway("abde", "abcde"));  // should return true
        //System.out.println(sl.isOneAway("a", "a"));  // should return true
        //System.out.println(sl.isOneAway("abcdef", "abqdef"));  // should return true
        //System.out.println(sl.isOneAway("aaa", "abc"));  // should return false
        //System.out.println(sl.isOneAway("abcde", "abc"));  // should return false
        //int[][] bombs1 = {{0, 2}, {2, 0}};
        //int[][] mineSweeps = sl.mineSweeper(bombs1, 3, 3);
        //for (int[] mineSweep : mineSweeps) {
        //System.out.println(Arrays.toString(mineSweep));
        //}

       /* int[][] field1 = {{0, 0, 0, 0, 0}, {0, 1, 1, 1, 0}, {0, 1, -1, 1, 0}};

        int[][] field = sl.mineSweeperClick(field1, 3, 5, 2, 2);

        for (int[] mineSweep : field) {
            System.out.println(Arrays.toString(mineSweep));
        }

        field = sl.mineSweeperClick(field1, 3, 5, 1, 4);

        for (int[] mineSweep : field) {
            System.out.println(Arrays.toString(mineSweep));
        }*/
    }

    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length <= 1) return new int[]{-1, -1};

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if (!map.containsKey(diff)) {
                map.put(nums[i], i);
            } else {
                return new int[]{i, map.get(diff)};
            }
        }

        return new int[]{-1, -1};

    }

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        int buy = prices[0], max_profit = Integer.MIN_VALUE;
        for (int i = 1; i < prices.length; i++) {
            if (buy > prices[i]) {
                buy = prices[i];
            }
            max_profit = Math.max(prices[i] - buy, max_profit);
        }
        return max_profit;
    }

    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length <= 1) return false;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(nums[0], 1);
        for (int i = 1; i < nums.length; i++) {
            if (map.containsKey(nums[i])) return true;
            else {
                map.put(nums[i], 1);
            }
        }
        return false;
    }

    public boolean containsDuplicateBySet(int[] nums) {
        if (nums == null || nums.length <= 1) return false;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) return true;
            else {
                set.add(nums[i]);
            }
        }
        return false;
    }

    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) return new int[0];
        int[] preFixProduct = new int[nums.length];
        int[] suffixProduct = new int[nums.length];
        int[] resultProduct = new int[nums.length];
        preFixProduct[0] = 1;
        suffixProduct[nums.length - 1] = 1;
        for (int i = 1; i < nums.length; i++) {
            preFixProduct[i] = nums[i - 1] * preFixProduct[i - 1];
        }
        for (int i = nums.length - 2; i >= 0; i--) {
            suffixProduct[i] = nums[i + 1] * suffixProduct[i + 1];
        }


        for (int i = 0; i < nums.length; i++) {
            resultProduct[i] = preFixProduct[i] * suffixProduct[i];
        }
        return resultProduct;
    }

    public int[] productExceptSelfOptimized(int[] nums) {
        if (nums == null || nums.length == 0) return new int[0];
        int[] productSelf = new int[nums.length];
        productSelf[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            productSelf[i] = nums[i - 1] * productSelf[i - 1];
        }
        int suffixProduct = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            productSelf[i] *= suffixProduct;
            suffixProduct *= nums[i];
        }
        return productSelf;
    }

    public int maxSubArraySum(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int maxSum = Integer.MIN_VALUE, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            maxSum = Math.max(maxSum, sum);
            if (sum < 0) sum = 0;
        }
        return maxSum;
    }

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int curr = nums[0], maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curr = Math.max(nums[i], curr + nums[i]);
            maxSum = Math.max(curr, maxSum);
        }
        return maxSum;
    }

    //
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int ans = 0, min = 1, max = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                // reset values
                max = 1;
                min = 1;
                ans = Math.max(ans, 0);
            } else {
                int temp = min;
                min = Math.min(nums[i], Math.min(min * nums[i], max * nums[i]));
                max = Math.max(nums[i], Math.max(temp * nums[i], max * nums[i]));
                ans = Math.max(ans, max);
            }
        }

        return ans;
    }

    // Find in a Rotated Array
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums[0] < nums[nums.length - 1]) return nums[0];
        if (nums.length == 2) return Math.min(nums[0], nums[1]);
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            // Modified Binary Search
            int mid = l + (r - l) / 2;
            // decreasing order with mid & mid -1
            if (nums[mid] > nums[mid + 1]) return nums[mid + 1];
            // decreasing order with mid & mid-1
            if (nums[mid - 1] > nums[mid]) return nums[mid];

            // check sorting order
            if (nums[l] < nums[mid]) l = mid + 1;
            else r = mid - 1;
        }
        return 0;
    }

    // Search in Rotated Array
    // Given the array nums after the possible rotation and an integer target,
    // return the index of target if it is in nums, or -1 if it is not in nums.

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1 && nums[0] == target) return 1;
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) return mid;
            if (nums[l] <= nums[mid]) { // left sub array sorted
                if (nums[l] <= target && target < nums[mid]) r = mid - 1;
                else l = mid + 1;
            } else { // right sub array sorted
                if (nums[mid] < target && target <= nums[r]) l = mid + 1;
                else r = mid - 1;
            }
        }
        return -1;
    }

    // 3 Sum (a+b+c=0)

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length && nums[i] <= 0; i++) {
            // check i= 0 or duplicate
            if (i == 0 || nums[i - 1] != nums[i]) {
                twoSumHelper(nums, i, result);
            }
        }
        return result;
    }

    void twoSumHelper(int[] nums, int i, List<List<Integer>> resultList) {
        int low = i + 1;
        int high = nums.length - 1;
        while (low < high) {
            int sum = nums[i] + nums[low] + nums[high];
            if (sum > 0) {
                high--;
            } else if (sum < 0) {
                ++low;
            } else {
                resultList.add(Arrays.asList(nums[i], nums[low++], nums[high--]));
                // duplicate check
                while (low < high && nums[low] == nums[low - 1]) {
                    ++low;
                }
            }
        }
    }

    // Return the maximum amount of water a container can store.
    public int maxArea(int[] height) {
        int ans = 0;
        int hi = height.length - 1, lo = 0;
        while (lo < hi) {
            ans = Math.max(ans, Math.min(height[lo], height[hi]) * (hi - lo));
            if (height[lo] < height[hi]) {
                lo++;
            } else {
                --hi;
            }
        }
        return ans;
    }

    // Most Frequently Occurring Item in an Array
    // I/P [1,3,1,3,2,1,1]  and O/p=1

    public int mostFrequent(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        HashMap<Integer, Integer> map = new HashMap<>();
        Integer mostFrequentNumber = null;
        int mostCount = -1;
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            if (map.get(nums[i]) > mostCount) {
                mostCount = map.get(nums[i]);
                mostFrequentNumber = nums[i];
            }
        }
        return mostFrequentNumber;
    }

    // Common Elements in Sorted Arrays
    public int[] commonElements(int[] arr1, int[] arr2) {
        int idx1 = 0, idx2 = 0;
        List<Integer> result = new ArrayList<>();
        while (idx1 < arr1.length && idx2 < arr2.length) {
            if (arr1[idx1] == arr2[idx2]) {
                result.add(arr1[idx1]);
                idx1++;
                idx2++;
            } else if (arr1[idx1] < arr2[idx2]) {
                idx1++;
            } else {
                idx2++;
            }
        }
        return result.stream().mapToInt(i -> i).toArray();
    }

    // Is Rotated
    public boolean isRotation(int[] arr1, int[] arr2) {
        int key = arr1[0], key_i = -1;
        for (int i = 0; i < arr2.length; i++) {
            if (key == arr2[i]) {
                key_i = i;
                break;
            }
        }
        if (key_i == -1) return false;
        for (int i = 0; i < arr1.length; i++) {
            int j = (key_i + i) % arr1.length;
            if (arr1[i] != arr2[j]) return false;
        }
        return true;
    }

    // Non Repeated Char
    public Character nonRepeatedChar(String s) {
        if (s == null || s.length() == 0) return null;
        if (s.length() == 1) return s.charAt(0);
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c : s.toCharArray()) {
            if (map.get(c) == 1) return c;
        }
        return null;
    }

    // One Away Strings I/p : abcdef & abcdf => true   abcde & abcde => true  abcde & abcd => true

    boolean isOneAway(String s1, String s2) {
        if (s1 == null && s2 == null) return true;
        if ((s1.length() == 0 && s2.length() == 1) || (s2.length() == 0 && s1.length() == 1)) return true;
        if (s1.length() - s2.length() >= 2 || s2.length() - s1.length() >= 2) return false;

        if (s1.length() == s2.length()) return isSameLengthStrings(s1, s2);
        else if (s1.length() > s2.length()) return isSameInDiffLengthStrings(s1, s2);
        else return isSameInDiffLengthStrings(s2, s1);
    }

    boolean isSameLengthStrings(String s1, String s2) {
        int idx1 = 0, idx2 = 0, countDiff = 0;
        while (idx1 < s1.length()) {
            if (s1.charAt(idx1) != s2.charAt(idx2)) countDiff++;
            if (countDiff > 1) return false;
            idx1++;
            idx2++;
        }
        return true;
    }

    boolean isSameInDiffLengthStrings(String s1, String s2) {
        int countDiff = 0;
        /*int idx = 0;
        while (idx < s2.length()) {
            if (s1.charAt(idx + countDiff) == s2.charAt(idx))
                idx++;
            else {
                countDiff++;
                if (countDiff > 1)
                    return false;
            }
        }*/
        for (int i = 0; i < s2.length(); i++) {
            if (s1.charAt(i + countDiff) != s2.charAt(i)) countDiff++;
            if (countDiff > 1) return false;
        }
        return true;
    }

    /*
    Assign Numbers in Minesweeper (Java)
    Implement a function that assigns correct numbers in a field of Minesweeper,
    which is represented as a 2 dimensional array.

    Example: The size of the field is 3x4, and there are bombs at the positions [0, 0]
    (row index = 0, column index = 0) and [0, 1] (row index = 0, column index = 1).

    Then, the resulting field should be:

        [[-1, -1, 1, 0],
         [2, 2, 1, 0],
         [0, 0, 0, 0]]


     Your function should return the resulting 2D array after taking the following three arguments:

    bombs: A list of bomb locations.  Given as an array of arrays.  Example: [[0, 0], [0, 1]]
    ([row index = 0, column index = 0], [row index = 0, column index = 1].  Assume that there are no duplicates.
    num_rows: The number of rows in the resulting field.
    num_columns: The number of columns in the resulting field.
    In the resulting field:

    -1 represents that there is a bomb in that location.
     1, 2, 3... etc. represents that there are 1, 2, 3... etc. bombs in the  surrounding cells
      (including diagonally adjacent cells).0 represents that there are no bombs in the surrounding cells.
     */

    int[][] mineSweeper(int[][] bombs, int numRows, int numCols) {
        int[][] result = new int[numRows][numCols];
        for (int[] bomb : bombs) {
            // setting bombs location as -1
            int row_i = bomb[0], col_i = bomb[1];
            result[row_i][col_i] = -1;
            for (int i = row_i - 1; i <= row_i + 1; i++)
                for (int j = col_i - 1; j <= col_i + 1; j++)
                    if (i >= 0 && i < numRows && j >= 0 && j < numCols && result[i][j] != -1)
                        result[i][j] = result[i][j] + 1;
        }
        return result;
    }

    /*
    Find Where to Expand in Minesweeper (Java)
    Implement a function that turns revealed cells into -2 given a location the user wants to click.

    For simplicity, only reveal cells that have 0 in them.  If the user clicks on any other type of cell
    (for example, -1 / bomb or 1, 2, or 3), just ignore the click and return the original field.
    If a user clicks 0, reveal all other 0's that are connected to it.

        Example 1:

        Given field:
        [[0, 0, 0, 0, 0],
        [0, 1, 1, 1, 0],
        [0, 1, -1, 1, 0]]

        Click location: (2, 2: row index = 2, column index = 2)

        Resulting field:
        [[0, 0, 0, 0, 0],
        [0, 1, 1, 1, 0],
        [0, 1, -1, 1, 0]] (same as the given field)
     */

    int[][] mineSweeperClick(int[][] field, int numRows, int numCols, int givenI, int givenJ) {
        Queue<int[]> queue = new LinkedList<>();
        if (field[givenI][givenJ] == 0) {
            field[givenI][givenJ] = -2;
            queue.add(new int[]{givenI, givenJ});
        } else return field;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int i = current[0] - 1; i <= current[0] + 1; i++)
                for (int j = current[1] - 1; j <= current[1] + 1; j++) {
                    if (i >= 0 && i < numRows && j >= 0 && j < numCols && field[i][j] == 0) {
                        field[i][j] = -2;
                        queue.add(new int[]{i, j});
                    }
                }
        }
        return field;
    }

    // Binary Execution
    int sumOfTwoNumber(int a, int b) {
        while (b != 0) {
            int carry = a & b; // And (&) Operator
            a = a ^ b; // XOR operator
            b = carry << 1;
        }
        return a;
    }

    // Number of 1 bits
    int numberOfOne(int a) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            int mask = 1 << i;
            if ((a & mask) != 0)
                count++;
        }
        return count;
    }

    // Count of 1s at number index
    // Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n),
    // ans[i] is the number of 1's in the binary representation of i.
    // i/p n = 2 and output = [0,1,1]
    int[] countBits(int n) {
        int[] ans = new int[n + 1];
        ans[0] = 0;
        int offset = 1;
        for (int i = 1; i <= n; i++) {
            if (offset * 2 == i) {
                // i is power of 2
                offset = i;
            }
            ans[i] = 1 + ans[i - offset];
        }
        return ans;
    }

    int[] countBitsWithOptized(int num) {
        int[] ans = new int[num + 1];
        for (int i = 1; i <= num; ++i) {
            ans[i] = ans[i & (i - 1)] + 1;
        }
        return ans;
    }

    // Missing number in Array

    int missingNumber(int[] nums) {
        int missingNum = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missingNum ^= nums[i] ^ i;
        }
        return missingNum;
    }

    int reverseBits(int n) {
        int reverse = 0;
        for (int i = 1; i <= 32; i++) {
            reverse <<= 1; // left shit
            reverse |= (1 & n);
            n >>= 1;
        }
        return reverse;
    }

}
