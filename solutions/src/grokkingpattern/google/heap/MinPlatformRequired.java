package grokkingpattern.google.heap;

import java.util.ArrayList;
import java.util.Arrays;
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
}
