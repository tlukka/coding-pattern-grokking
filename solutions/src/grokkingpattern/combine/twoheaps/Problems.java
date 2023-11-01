package grokkingpattern.combine.twoheaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.PriorityQueue;

public class Problems {

    public static void main(String[] args) {
    /*MedianFinder medianFinder = new MedianFinder();
    medianFinder.addNum(3);
    medianFinder.addNum(1);
    System.out.println("Median of numbers " + medianFinder.findMedian());
    medianFinder.addNum(5);
    System.out.println("Median of numbers " + medianFinder.findMedian());

    medianFinder.addNum(4);
    System.out.println("Median of numbers " + medianFinder.findMedian());

    double[] medians= medianSlidingWindow_TwoHeaps(new int[]{1,3,-1,-3,5,3,6,7}, 3);
    System.out.println(Arrays.toString(medians));
    double[] medians_bf= medianSlidingWindow_BF(new int[]{1,3,-1,-3,5,3,6,7}, 3);
    System.out.println(Arrays.toString(medians_bf));

    System.out.println(findMaximizedCapital(2, 0, new int[]{1, 2, 3}, new int[]{0, 1, 1}));
    System.out.println(findMaximizedCapital(3, 0, new int[]{1, 2, 3}, new int[]{0, 1, 1}));
   */

        System.out.println(Arrays.toString(findRightInterval(new int[][]{{3, 4}, {2, 3}, {1, 2}})));
        System.out.println(Arrays.toString(findRightInterval(new int[][]{{1, 4}, {2, 3}, {3, 4}})));

    }

    //Next Interval
    static int[] findRightInterval(int[][] intervals) {
        PriorityQueue<int[]> minEndHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> minStartHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        //Build maps
        for (int i = 0; i < intervals.length; i++) {
            minStartHeap.add(new int[]{intervals[i][0], i});
            minEndHeap.add(new int[]{intervals[i][1], i});
        }

        int[] result = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            result[i] = -1;
        }

        //logic
        while (!minEndHeap.isEmpty()) {
            int[] currEnd = minEndHeap.poll();
            int currEndValue = currEnd[0];
            int currEndIdx = currEnd[1];

            //find first start idx which is greater than or equal to the current end
            while (!minStartHeap.isEmpty() && currEndValue > minStartHeap.peek()[0]) {
                minStartHeap.poll();
                // no more elements left in minheapStart rest all indices in result[] will be mapped to -1
                if (minStartHeap.isEmpty())
                    return result;

                // When minheapStart is not empty, then the top most element of minheapStart must be >= currEndVal
                // So we place the corresponding index of the top most element of minheapStart in the corresponding
                // currEndIdx of the result[]
                result[currEndIdx] = minStartHeap.peek()[1];
            }
        }
        return result;
    }

    //Pick a list of at most k distinct projects from given projects to maximize your final capital,
    // and return the final maximized capital. Maximize Capital
    static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        PriorityQueue<Integer> minHeapCapitals = new PriorityQueue<Integer>((c1, c2) -> capital[c1] - capital[c2]);
        PriorityQueue<Integer> maxHeapProfits = new PriorityQueue<Integer>((p1, p2) -> profits[p2] - profits[p1]);
        for (int i = 0; i < capital.length; i++) {
            minHeapCapitals.add(i); // inserted all capitals index
        }

        while (k-- > 0) {
            // logic
            while (minHeapCapitals.size() > 0 && capital[minHeapCapitals.peek()] <= w) {
                maxHeapProfits.add(minHeapCapitals.poll());
            }
            if (maxHeapProfits.size() != 0) {
                w += profits[maxHeapProfits.poll()];
            }
        }
        return w;
    }

    //Sliding Window Median
    static double[] medianSlidingWindow_BF(int[] nums, int k) {
        double[] retInt = new double[nums.length - k + 1];
        LinkedHashSet<Integer> data = new LinkedHashSet<>();
        int start = 0;
        int end = 0;
        int index = 0;
        while (end < nums.length) {
            data.add(end);
            if (data.size() == k) {
                retInt[index++] = getMedian_bf(data, nums);
                data.remove(start);
                start++;
            }
            end++;
        }
        return retInt;

    }

    static double getMedian_bf(LinkedHashSet<Integer> data, int[] nums) {
        List<Integer> dataList = new ArrayList<>();
        for (int dt : data) {
            dataList.add(nums[dt]);
        }
        Collections.sort(dataList);
        // if dataList is Even than
        if (dataList.size() % 2 == 0) {
            double val1 = (double) dataList.get(dataList.size() / 2);
            double val2 = (double) dataList.get(dataList.size() / 2 - 1);
            return (val1 + val2) / 2;
        } else {
            return (double) dataList.get(dataList.size() / 2);
        }
    }

    // https://leetcode.com/problems/sliding-window-median
    public static double[] medianSlidingWindow_TwoHeaps(int[] nums, int k) {
        double[] ans = new double[nums.length - k + 1];
        int index = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        for (int i = 0; i < nums.length; i++) {
            process(minHeap, maxHeap, nums[i]);
            // shrink elements
            if (maxHeap.size() + minHeap.size() == k) {
                ans[index] = getMedian(minHeap, maxHeap, k);
                remove(minHeap, maxHeap, nums[index]);
                index++;
            }
        }
        return ans;
    }

    // remove Element to shrink array O(k)
    static void remove(PriorityQueue<Integer> minHeap,  PriorityQueue<Integer> maxHeap, int current) {
        if (minHeap.contains(current)) {
            minHeap.remove(current);
        } else {
            maxHeap.remove(current);
        }
    }

    // O(1)
    static double getMedian(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap, int k) {
        if (k % 2 == 0) {
            return ((double) maxHeap.peek() + (double) minHeap.peek()) / 2;
        } else if (minHeap.size() > maxHeap.size()) {
            return minHeap.peek();
        } else {
            return maxHeap.peek();
        }
    }

    // Process elements to insert
    static void process(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap, int current) {
        if (maxHeap.isEmpty() || maxHeap.peek() >= current) {
            maxHeap.add(current);
        } else {
            minHeap.add(current);
        }
        // balance heaps
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.poll());
        } else if (maxHeap.size() < minHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }
}
