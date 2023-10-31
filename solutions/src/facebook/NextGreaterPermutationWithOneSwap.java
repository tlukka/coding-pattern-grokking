package facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NextGreaterPermutationWithOneSwap {
    //Objective: Given an array of integers (in particular order or permutation of a set of numbers),
    // write an algorithm to find the lexicographically next permutation of the given permutation with only one swap.

    //This problem can also be asked as "Given a permutation of numbers you need to find the next larger
    // permutation OR smallest permutation which is greater than the given permutation".

    //Note: In the case given permutation is largest, return the given permutation.

    //Given Array: [1, 7, 3, 4, 5]
    //smallest permutation greater than given array: [1, 7, 3, 5, 4]

    //Given Array: [5, 4, 3, 2, 1]
    //Original Array is already the largest possible permutation: [5, 4, 3, 2, 1]

    //Given Array: [4, 2, 5, 1, 0]
    //smallest permutation greater than given array: [4, 5, 0, 1, 2]

    public static void main(String[] args) {
        NextGreaterPermutationWithOneSwap n = new NextGreaterPermutationWithOneSwap();
        System.out.println(Arrays.toString(n.previousLargestPermutation(new int[]{4, 2, 5, 1, 0})));
        System.out.println(Arrays.toString(n.previousLargestPermutation(new int[]{1, 7, 3, 4, 5})));
        System.out.println(Arrays.toString(n.previousLargestPermutation(new int[]{5, 4, 3, 2, 1})));
    }

    int[] previousLargestPermutation(int[] arr) {
        int len = arr.length, firstEle = -1, secondEle = -1, i;
        for (i = len - 1; i > 0; i--) {
            if (arr[i - 1] < arr[i]) {
                firstEle = i - 1;
                break;
            }
        }
        if (firstEle == -1)
            return arr;
        for (; i < len; i++) {
            if (arr[firstEle] < arr[i]) {
                secondEle = i;
                break;
            }
        }

        // swap
        int temp = arr[firstEle];
        arr[firstEle] = arr[secondEle];
        arr[secondEle] = temp;
        Arrays.sort(arr, i, len);
        return arr;
    }
}
