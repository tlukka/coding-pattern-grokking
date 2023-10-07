package grokkingpattern;

import java.util.Arrays;
import java.util.Random;

public class RecursionSolutions {

    public static void main(String[] args) {
        RecursionSolutions rs = new RecursionSolutions();
        //rs.towerOfHanoi(2, "A", "B", "C");
        //System.out.println(rs.reverse("Geeks For Geeks"));
        //System.out.println(rs.reverseWithRecursion("Geeks For Geeks"));

        //System.out.println(rs.binarySearchRecursion(new int[]{1, 10, 25, 4, 5}, 5));
        //System.out.println(rs.binarySearchRecursion(new int[]{1, 10, 25, 4, 5}, 6));
        //System.out.println(rs.binarySearchRecursion(new int[]{1, 10, 25, 4}, 4));
        //{-2,1,5,6,7,8,10}
        /*System.out.println(rs.quickSelectKthIndex(new int[]{1, -2, 10, 8, 6, 7, 5}, 1));
        System.out.println(rs.quickSelectKthIndex(new int[]{1, -2, 10, 8, 6, 7, 5}, 2));
        System.out.println(rs.quickSelectKthIndex(new int[]{1, -2, 10, 8, 6, 7, 5}, 3));
        System.out.println(rs.quickSelectKthIndex(new int[]{1, -2, 10, 8, 6, 7, 5}, 4));
        System.out.println(rs.quickSelectKthIndex(new int[]{1, -2, 10, 8, 6, 7, 5}, 5));
        System.out.println(rs.quickSelectKthIndex(new int[]{1, -2, 10, 8, 6, 7, 5}, 6));
        System.out.println(rs.quickSelectKthIndex(new int[]{1, -2, 10, 8, 6, 7, 5}, 7));

         */

        rs.sort(new int[]{1, -2, 10, 8, 6, 7, 5});
    }

    public int headFibannoci(int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        return headFibannoci(n - 1) + headFibannoci(n - 2);
    }

    public int tailFibannoci(int n, int a, int b) {
        if (n == 0)
            return a;
        if (n == 1)
            return b;
        return tailFibannoci(n - 1, b, a + b);
    }

    public void towerOfHanoi(int n, String source, String middle, String destination) {
        if (n == 0) {
            System.out.println("Plate " + n + " moved from " + source + " to " + destination);
            return;
        }
        towerOfHanoi(n - 1, source, destination, middle);
        System.out.println("Plate " + n + " moved from " + source + " to " + destination);
        towerOfHanoi(n - 1, middle, source, destination);
    }

    String reverse(String s) {
        // your algorithm here
        int len = s.length() - 1;
        char[] chArr = s.toCharArray();
        for (int i = 0; i <= len / 2; i++) {
            char temp = chArr[i];
            chArr[i] = chArr[len - i];
            chArr[len - i] = temp;
        }

        return new String(chArr);
    }

    String reverseWithRecursion(String s) {
        if (s.equals(""))
            return s;
        return reverseWithRecursion(s.substring(1)) + s.charAt(0);
    }

    int gcd(int a, int b) {
        if (a % b == 0)
            return b;
        return gcd(b, a % b);
    }

    int gcd_iteration(int a, int b) {
        int temp;
        while (b != 0) {
            temp = b;
            b = a % b;
            a = temp;

        }

        return a;
    }

    int binarySearchRecursion(int[] nums, int item) {
        Arrays.sort(nums);
        return binary_recursion(nums, 0, nums.length, item);
    }

    int binary_recursion(int[] nums, int start, int end, int target) {
        if (start > end)
            return -1;

        int mid = start + (end - start) / 2;
        if (nums[mid] == target)
            return mid;
        if (nums[mid] < target) {
            return binary_recursion(nums, mid + 1, end, target);
        } else {
            return binary_recursion(nums, start, mid - 1, target);
        }
    }

    public void sort(int[] nums) {
        for (int i = 1; i < nums.length + 1; i++) {
            System.out.println(quickSelectKthIndex(nums, i));
        }
    }

    int quickSelectKthIndex(int[] nums, int k) {
        return quickSelect(nums, k - 1, 0, nums.length - 1);
    }

    int quickSelect(int[] nums, int k, int start, int end) {
        int pivot = partition(nums, start, end);
        if (pivot == k) {
            return nums[pivot];
        } else if (pivot > k) {
            return quickSelect(nums, k, start, pivot - 1);
        } else {
            return quickSelect(nums, k, pivot + 1, end);
        }
    }

    int partition(int[] nums, int firstIndex, int lastIndex) {
        int pivot = new Random().nextInt(lastIndex - firstIndex + 1) + firstIndex;
        return partition(nums, firstIndex, lastIndex, pivot);
    }

    int partition(int[] nums, int firstIndex, int lastIndex, int pivot) {
        swap(nums, pivot, lastIndex);

        for (int i = firstIndex; i < lastIndex; ++i) {
            if (nums[i] < nums[lastIndex]) {
                swap(nums, i, firstIndex);
                firstIndex++;
            }
        }
        swap(nums, firstIndex, lastIndex);
        return firstIndex;
    }

    void swap(int[] nums, int start, int end) {
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
    }

    // Secretary Problem, also known as the Optimal Stopping Problem or the Marriage Problem,
    // is a classic problem in probability theory and decision theory.

    double secretaryProblem(int[] candidates) {
        int n = candidates.length;
        int bestCandidate = candidates[0];
        int hires = 1;

        for (int i = 1; i < n; i++) {
            if (candidates[i] > bestCandidate) {
                bestCandidate = candidates[i];
                hires++;
            }
        }

        return (double) hires / n;
    }

    int selectPivot(int[] nums, int start, int end) {
        if (end - start < 5) {
            return partition(nums, start, end, (start + end) / 2);
        }

        int numGroups = (int) Math.ceil((end - start + 1) / 5.0);
        int[] medians = new int[numGroups];

        for (int i = 0; i < numGroups; i++) {
            int groupStart = start + i * 5;
            int groupEnd = Math.min(groupStart + 4, end);
            int medianIndex = medianOf5(nums, groupStart, groupEnd);
            medians[i] = nums[medianIndex];
        }

        return medianOfMedians(medians, 0, medians.length - 1, medians.length / 2);
    }

    int medianOfMedians(int[] nums, int start, int end, int k) {
        if (start == end) {
            return nums[start];
        }

        int pivotIndex = selectPivot(nums, start, end);
        pivotIndex = partition(nums, start, end, pivotIndex);

        if (k == pivotIndex) {
            return nums[k];
        } else if (k < pivotIndex) {
            return medianOfMedians(nums, start, pivotIndex - 1, k);
        } else {
            return medianOfMedians(nums, pivotIndex + 1, end, k);
        }
    }

    int medianOf5(int[] nums, int start, int end) {
        Arrays.sort(nums, start, end + 1);
        return (start + end) / 2;
    }

    int findKthSmallest(int[] nums, int k) {
        if (k >= 0 && k < nums.length) {
            return medianOfMedians(nums, 0, nums.length - 1, k);
        }
        return -1;
    }
}
