package grokkingpattern.google.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solutions {
    // https://leetcode.com/problems/binary-search/
    // Binary Search
    int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) { // continue the loop till left pointer is less than or equal to right pointer
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    // https://leetcode.com/problems/search-insert-position/
    // Search Insert Position
    int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] > target) end = mid - 1;
            else start = mid + 1;
        }

        return start;
    }

    // Find Smallest Letter Greater Than Target
    // https://leetcode.com/problems/find-smallest-letter-greater-than-target/
    char nextGreatestLetter(char[] letters, char target) {
        int left = 0;
        int right = letters.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (letters[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (left == letters.length) {
            return letters[0];
        }

        return letters[left];
    }

    // https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
    //  Find First and Last Position of Element in Sorted Array
    int[] searchRange(int[] nums, int target) {
        int firstIndex = findIndex(nums, target, true);
        int lastIndex = findIndex(nums, target, false);
        return new int[]{firstIndex, lastIndex};

    }

    int findIndex(int[] nums, int target, boolean firstIndex) {
        int left = 0, right = nums.length - 1, index = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                if (firstIndex) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
                index = mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return index;
    }

    // https://leetcode.com/problems/minimum-absolute-difference/
    // Minimum Absolute Difference
    List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> result = new ArrayList<>();
        int absDiff = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length - 1; i++) {
            int currentDiff = arr[i + 1] - arr[i];
            if (currentDiff < absDiff) {
                result.clear();
                List<Integer> l1 = Arrays.asList(arr[i], arr[i + 1]);
                result.add(l1);
                absDiff = currentDiff;
            } else if (currentDiff == absDiff) {
                result.add(Arrays.asList(arr[i], arr[i + 1]));
            }
        }
        return result;
    }

    // https://leetcode.com/problems/search-in-rotated-sorted-array/
    // Search in Rotated Sorted Array
    int searchRotatedArray(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1 && nums[0] == target) return 0;
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target)
                return mid;
            if (nums[l] <= nums[mid]) { // left sub array sorted
                if (nums[l] <= target && target < nums[mid])
                    r = mid - 1;
                else
                    l = mid + 1;
            } else { // right sub array sorted
                if (nums[mid] < target && target <= nums[r])
                    l = mid + 1;
                else
                    r = mid - 1;
            }
        }
        return -1;
    }

    // Search in Rotated Sorted Array II
    // https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
    boolean searchRotatedArrayII(int[] ar, int t) {
        int i = 0, n = ar.length, j = n - 1, m;
        while (i <= j) {
            m = (i + j) / 2;
            if (ar[m] == t || ar[i] == t || ar[j] == t)
                return true;
            if (ar[i] == ar[j]) {
                i++;
                j--;
                continue;
            }
            if ((ar[i] <= ar[m] && (ar[i] > t || ar[m] < t)) || (ar[i] > t && ar[m] < t))
                i = m + 1;
            else j = m - 1;
        }
        return false;
    }

    // https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
    // Find Minimum in Rotated Sorted Array

    int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.min(nums[0], nums[1]);
        if (nums[0] < nums[nums.length - 1]) return nums[0];
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            // Modified Binary Search
            int mid = l + (r - l) / 2;
            // decreasing order with mid & mid -1
            if (nums[mid] > nums[mid + 1])
                return nums[mid + 1];
            // decreasing order with mid & mid-1
            if (nums[mid - 1] > nums[mid])
                return nums[mid];

            // check sorting order
            if (nums[l] < nums[mid])
                l = mid + 1;
            else
                r = mid - 1;
        }
        return 0;
    }

    // https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
    // Find Minimum in Rotated Sorted Array II
    int findMinII(int[] nums) {
        int low = 0, high = nums.length - 1;
        int mid = 0;
        int value = Integer.MAX_VALUE;
        while (low <= high) {
            mid = low + (high - low) / 2;
            value = Math.min(value, nums[mid]);

            if (nums[low] >= nums[mid] && nums[mid] > nums[high])
                low = mid + 1;
            else if (nums[low] < nums[mid] && nums[mid] <= nums[high])
                high = mid - 1;
            else if (nums[low] <= nums[mid] && nums[mid] > nums[high])
                low = mid + 1;
            else {
                int left = mid - 1, right = mid + 1;
                while (left >= 0 && right < nums.length && nums[left] == nums[right]) {
                    left--;
                    right++;
                }
                if (left >= 0 && right < nums.length && nums[left] < nums[right])
                    high = mid - 1;
                else
                    low = mid + 1;
            }
        }
        return value;
    }

}
