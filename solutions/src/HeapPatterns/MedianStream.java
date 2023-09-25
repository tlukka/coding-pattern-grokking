package HeapPatterns;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

// Design a class to calculate the median of a number stream. The class should have the following two methods:
//insertNum(int num): stores the number in the class
// findMedian(): returns the median of all numbers inserted in the class If the count of numbers inserted
// in the class is even, the median will be the average of the middle two numbers.
public class MedianStream {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    ;
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    ;

    void insertNum(int num) {
        if (minHeap.isEmpty() && maxHeap.isEmpty()) {
            maxHeap.add(num);
        } else if (maxHeap.peek() < num) {
            // Top value is less than num
            minHeap.add(num);
            if (minHeap.size() - maxHeap.size() >= 2) {
                maxHeap.add(minHeap.poll());
            }
        } else {
            maxHeap.add(num);
            if (maxHeap.size() - minHeap.size() >= 2) {
                minHeap.add(maxHeap.poll());
            }
        }

    }

    int findMedian() {
        if (minHeap.size() == maxHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2;
        } else {
            return maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek();
        }
    }

    // Given an array of numbers and a number ‘k’,
    // find the median of all the ‘k’ sized sub-arrays (or windows) of the array.
    double[] findWindowMedian_Bf(int[] nums, int k) {
        double[] medians = new double[nums.length - k + 1];
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        int index = 0;
        medians[index++] = (double) sum / k;
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k];
            medians[index++] = (double) sum / k;
        }
        return medians;
    }


    public static void main(String[] args) {
        MedianStream as = new MedianStream();
        as.insertNum(3);
        as.insertNum(1);
        System.out.println(as.findMedian());
        as.insertNum(6);
        System.out.println(as.findMedian());
        as.insertNum(5);
        System.out.println(as.findMedian());
        double[] medians = as.findWindowMedian_Bf(new int[]{1, 2, -1, 3, 5}, 2);
        System.out.println(Arrays.toString(medians));
        medians = as.findWindowMedian_Bf(new int[]{1, 2, -1, 3, 5}, 3);
        System.out.println(Arrays.toString(medians));
    }

}
