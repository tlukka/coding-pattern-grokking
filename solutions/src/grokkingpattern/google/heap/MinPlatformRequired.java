package grokkingpattern.google.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MinPlatformRequired {

    public static void main(String[] args) {
        MinPlatformRequired mp = new MinPlatformRequired();
        int[] arr = {900, 940, 950, 1100, 1500, 1800};
        int[] dep = {910, 1200, 1120, 1130, 1900, 2000};


        System.out.println(mp.countPlatformsByHeap(arr, dep));
        System.out.println(mp.countPlatformBySort(arr, dep));
        System.out.println(mp.countPlatformBySweepLine(arr, dep));

    }

    // Minimum Number of Platforms Required for a Railway/Bus Station
    // https://www.geeksforgeeks.org/minimum-number-platforms-required-railwaybus-station/#
    // Time Complexity: O(N*log(N)) and Auxiliary Space: O(N),
    int countPlatformsByHeap(int[] arr, int[] dep) {
        TrainSchedule[] trains
                = new TrainSchedule[arr.length];
        // Store the arrival and departure time
        for (int i = 0; i < arr.length; i++) {
            trains[i] = new TrainSchedule(arr[i], dep[i]);
        }
        // Sort trains based on arrival time
        Arrays.sort(trains, new Comparator<TrainSchedule>() {
            @Override
            public int compare(TrainSchedule o1, TrainSchedule o2) {
                return o1.arrivalTime - o2.arrivalTime;
            }
        });
        PriorityQueue<Integer> q = new PriorityQueue<>();
        q.add(trains[0].deptTime);
        int count = 1;
        for (int i = 1; i < arr.length; i++) {
            TrainSchedule curr = trains[i];
            //  Check if arrival time of current train  is less than or equals to departure time of previous train
            if (curr.arrivalTime <= q.peek()) {
                count++;
            } else {
                q.poll();
            }
            q.add(curr.deptTime);
        }

        return count;
    }

    static class TrainSchedule {
        int arrivalTime, deptTime;

        TrainSchedule(int arrivalTime, int deptTime) {
            this.arrivalTime = arrivalTime;
            this.deptTime = deptTime;
        }

        public String toString() {
            return "(" + this.arrivalTime + "," + this.deptTime + ")";
        }

    }

    // Time Complexity: O(N * log N) and Auxiliary space: O(1),
    int countPlatformBySort(int[] arr, int[] dep) {
        Arrays.sort(arr);
        Arrays.sort(dep);
        int platformNeed = 1, result = 1, i = 1, j = 0, n = arr.length;

        while (i < n && j < n) {
            if (arr[i] <= dep[j]) {
                platformNeed++;
                i++;
            } else if (arr[i] > dep[j]) {
                platformNeed--;
                j++;
            }

            // update result
            result = Math.max(platformNeed, result);
        }
        return result;
    }

    // Time Complexity: O(N * log N) and Auxiliary space: O(maxDepartureTime),
    int countPlatformBySweepLine(int[] arr, int[] dep) {
        int count = 0, maxPlatforms = 0, maxDepetureTime = dep[0], n = arr.length;
        for (int i = 1; i < dep.length; i++) {
            maxDepetureTime = Math.max(maxDepetureTime, dep[i]);
        }
        // maxDepetureTime = Arrays.stream(dep).max().getAsInt(); // Single line to get max
        List<Integer> list = new ArrayList<>(maxDepetureTime + 2);
        for (int i = 0; i < maxDepetureTime + 2; i++) {
            list.add(0);
        }

        // Increment the count at the arrival time and decrement at the departure time
        for (int i = 0; i < n; i++) {
            list.set(arr[i], list.get(arr[i]) + 1);
            list.set(dep[i] + 1, list.get(dep[i] + 1) - 1);
        }
        //Iterate over the vector and keep track of the maximum sum seen so far
        for (int i = 0; i < maxDepetureTime + 1; i++) {
            count += list.get(i);
            maxPlatforms = Math.max(maxPlatforms, count);
        }

        return maxPlatforms;
    }

    // https://leetcode.com/problems/find-right-interval/
    // Find Right Interval
    int[] findByHeaps(int[][] intervals) {
        PriorityQueue<int[]> minEndHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> minStartHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        //Build maps
        for (int i = 0; i < intervals.length; i++) {
            minStartHeap.add(new int[]{intervals[i][0], i});
            minEndHeap.add(new int[]{intervals[i][1], i});
        }

        int[] result = new int[intervals.length];
        Arrays.fill(result, -1);

        //logic
        while (!minEndHeap.isEmpty()) {
            int[] currEnd = minEndHeap.poll();
            int currEndValue = currEnd[0];
            int currEndIdx = currEnd[1];

            //find first start idx which is greater than the current end
            while (!minStartHeap.isEmpty() && currEndValue > minStartHeap.peek()[0]) {

                minStartHeap.poll();
                // no more elements left in minheapStart rest all indices in result[] will be mapped to -1
                if (minStartHeap.isEmpty())
                    break;

                // When minheapStart is not empty, then the top most element of minheapStart must be >= currEndVal
                // So we place the corresponding index of the top most element of minheapStart in the corresponding
                // currEndIdx of the result[]
                result[currEndIdx] = minStartHeap.peek()[1];
            }
        }
        return result;
    }

    // https://leetcode.com/problems/ipo/
    // Pick a list of at most k distinct projects from given projects to maximize your final capital, and return the final maximized capital.
    int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>((c1, c2) -> capital[c1] - capital[c2]);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((p1, p2) -> profits[p2] - profits[p1]);
        for (int i = 0; i < capital.length; i++) {
            minHeap.add(i); // Index of capital...
        }
        while (k-- > 0) {
            while (!minHeap.isEmpty() && capital[minHeap.peek()] <= w) {
                maxHeap.add(minHeap.poll()); // adding in max heap..
            }
            if (maxHeap.isEmpty())
                break;
            w += profits[maxHeap.poll()];
        }
        return w;
    }

    // https://leetcode.com/problems/sliding-window-median/
    //Sliding Window Median

    double[] medianSlidingWindow(int[] nums, int k) {
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

    void process(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap, int current) {
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

    double getMedian(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap, int k) {
        if (k % 2 == 0) {
            return (double) ((long) minHeap.peek() + (long) maxHeap.peek()) / 2.0;
        } else if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else
            return minHeap.peek();
    }

    void remove(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap, int current) {
        if (minHeap.contains(current))
            minHeap.remove(current);
        else
            maxHeap.remove(current);
    }

    // Find Median from Data Stream
    // https://leetcode.com/problems/find-median-from-data-stream/
    static class MedianFinder {
        PriorityQueue<Integer> minHeap;
        PriorityQueue<Integer> maxHeap;
        public MedianFinder() {
            minHeap = new PriorityQueue<>();
            maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        }

        public void addNum(int num) {
            minHeap.add(num);
            maxHeap.add(minHeap.poll());
            if(minHeap.size()<maxHeap.size()) {
                minHeap.add(maxHeap.poll());
            }
        }

        public double findMedian() {
            if(minHeap.size() == maxHeap.size())
                return (maxHeap.peek() + minHeap.peek())/2.0;
            return minHeap.peek();
        }
    }

}
