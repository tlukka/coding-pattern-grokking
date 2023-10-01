package grokkingpattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class TopKElementsSolutions {

    public static void main(String[] args) {
        TopKElementsSolutions sol = new TopKElementsSolutions();
        /*
        System.out.println(sol.topKLargestElement(new int[]{3, 2, 1, 5, 6, 4}, 2));
        System.out.println(sol.topKLargestElement(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));


        System.out.println(sol.topKLargeElementByHeap(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
        System.out.println(sol.topKSmallElementByHeap(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
        sol.kLargest(new int[]{11, 3, 2, 1, 15, 5, 4, 45, 88, 96, 50, 45}, 3);
        System.out.println(sol.kthSmallest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
        System.out.println(Arrays.toString(sol.findKSmallest(new int[][]{{4, 9, 13, 25}, {1, 3, 19, 36}, {2, 5, 12, 45}}, 6)));
        System.out.println(Arrays.toString(sol.findKSmallestDoubleHeap(new int[][]{{4, 9, 13, 25}, {1, 3, 19, 36}, {2, 5, 12, 45}}, 6)));
        int[][] kpoints1 = sol.kClosestPointsToOrigin(new int[][]{{1, 3}, {-2, 2}}, 1);
        System.out.println(Arrays.deepToString(kpoints1));
        int[][] kpoints2 = sol.kClosestPointsToOrigin(new int[][]{{3, 3}, {5, -1}, {-2, 4}}, 2);
        System.out.println(Arrays.deepToString(kpoints2));
        System.out.println(sol.minimumCost(Arrays.asList(new Integer[]{2, 4, 3})));
        System.out.println(sol.minimumCost(Arrays.asList(new Integer[]{1, 8, 3, 5})));
        System.out.println(sol.minimumCost(Arrays.asList(new Integer[]{3, 4, 5, 6})));



        System.out.println(Arrays.toString(sol.topKFrequentWithMapQueue(new int[]{1, 1, 1, 2, 2, 3}, 2)));
         System.out.println(Arrays.toString(sol.topKFrequentOptimized(new int[]{1, 1, 1, 2, 2, 3}, 2)));
        System.out.println(sol.frequencySort("tree"));
        System.out.println(sol.frequencySort("cccaaa"));
        System.out.println(sol.frequencySort("Aabb"));


        System.out.println(sol.frequencySort("Programming"));

        KthLargest obj = new KthLargest(3, new int[]{4, 5, 8, 2});
        System.out.println(obj.add(4));
        System.out.println(obj.add(10));
        System.out.println(obj.add(9));
        System.out.println(obj.add(23));


        System.out.println(sol.findClosestElementsWithIIPointer(new int[]{10, 2, 14, 4, 7, 6}, 3, 5));
        System.out.println(sol.findClosestElementsWithBinarySearch(new int[]{10, 2, 14, 4, 7, 6}, 3, 5));
        System.out.println(sol.findClosestElementsWithHeap(new int[]{10, 2, 14, 4, 7, 6}, 3, 5));


        System.out.println(sol.findLeastNumOfUniqueInts(new int[]{4, 3, 1, 1, 3, 3, 2}, 3));
        System.out.println(sol.findLeastNumOfUniqueInts(new int[]{4, 4, 5}, 1));

         */

        System.out.println(sol.sumBetweenTwoKth(new int[]{20, 8, 22, 4, 12, 10, 14}, 3, 6));
        System.out.println(sol.reorganizeString("aab"));
        System.out.println(sol.reorganizeString("aaab"));
    }

    // https://leetcode.com/problems/kth-largest-element-in-an-array/

    // By sorting elements and return kth element
    int topKLargestElement(int[] nums, int k) {
        if (k > nums.length)
            return -1;
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    // by using Heap
    int topKLargeElementByHeap(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            minHeap.add(nums[i]);
            if (minHeap.size() > k)
                minHeap.poll(); // remove any elements which are less than
        }
        return minHeap.peek();
    }

    // Function to print k largest array elements
    void kLargest(int a[], int k) {
        // Implementation using a Priority Queue
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        int n = a.length;
        for (int i = 0; i < n; ++i) {

            // Insert elements into the priority queue
            pq.add(a[i]);

            // If size of the priority  queue exceeds k
            if (pq.size() > k) {
                pq.poll();
            }
        }

        // Print the k largest element
        while (!pq.isEmpty()) {
            System.out.print(pq.poll() + " ");
        }
        System.out.println();
    }

    // by using Heap
    int topKSmallElementByHeap(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < nums.length; i++) {
            maxHeap.add(nums[i]);
            if (maxHeap.size() > k)
                maxHeap.poll(); // remove any elements which are less than
        }
        return maxHeap.peek();
    }

    // Counting Sort Algorithm
    int kthSmallest(int[] arr, int k) {
        // Find Max element in array
        int max_element = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max_element = Math.max(max_element, arr[i]);
        }

        // Initialize array with maxElement +1
        int[] freqMap = new int[max_element + 1];
        //count frequency of element
        for (int i = 0; i < arr.length; i++) {
            freqMap[arr[i]]++;
        }
        // Keep track of the cumulative frequency of elements in the input array
        int count = 0;
        for (int i = 0; i < max_element; i++) {
            if (freqMap[i] != 0) {
                count += freqMap[i];
                if (count > k) {
                    return i;
                }
            }
        }
        return -1;
    }

    // Given N sorted arrays find k smallest elements
    // Input is {{4 9 13 25}, {1 3 19 36},  {2 5 12 45}} and o/p is {1,2,3,4,5,9}
    int[] findKSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0)
            return null;

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                maxHeap.add(matrix[i][j]);
            }
        }
        int[] kThElements = new int[k];
        int i = 0;
        while (!maxHeap.isEmpty() && k-- > 0) {
            kThElements[i++] = maxHeap.poll();
        }
        return kThElements;
    }

    int[] findKSmallestDoubleHeap(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0)
            return null;

        Queue<Queue<Integer>> queue = new PriorityQueue<Queue<Integer>>((a, b) -> a.peek() - b.peek());

        for (int[] mtx : matrix) {
            Queue<Integer> list = new LinkedList();
            for (int i : mtx) {
                list.add(i);
            }
            queue.add(list);
        }


        List<Integer> result = new ArrayList<>();
        while (result.size() < k && !queue.isEmpty()) {
            Queue<Integer> list = queue.poll();
            result.add(list.poll());
            queue.add(list);
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    int[][] kClosestPointsToOrigin(int[][] points, int k) {
        if (points == null || points.length == 0)
            return new int[0][];

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        for (int i = 0; i < points.length; i++) {
            queue.add(new int[]{i, points[i][0] * points[i][0] + points[i][1] * points[i][1]});
        }

        int[][] result = new int[k][];
        int i = 0;
        while (k > 0) {
            result[--k] = points[queue.poll()[0]];
        }

        return result;
    }

    // Given N ropes with different lengths, we need to connect these ropes into one big rope with minimum cost.
    // The cost of connecting two ropes is equal to the sum of their lengths.
    // Input: [2,4,3] and o/p - 14 (First connect 2 and 3 to 5 and cost 5; then connect 5 and 4 to 9; total cost is 14)
    // input: [1,8,3,5] and o/p 30 -> 1+3 =4 +5 -> (9+4) -> 9+8 => 17 + 13
    int minimumCost(List<Integer> sticks) {
        Collections.sort(sticks);
        // Create Queue
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int stick : sticks)
            queue.add(stick);

        int totalCoust = 0;
        // While size of priority queue is more than 1
        while (queue.size() > 1) {
            int firstNum = queue.poll();
            int secondNum = queue.poll();
            totalCoust += firstNum + secondNum;
            queue.add(firstNum + secondNum);
        }
        return totalCoust;
    }

    //Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
    // input: [1,1,1,2,2,3] and k =2 -> output is [1,2]
    int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        ArrayList<Integer>[] bucket = new ArrayList[nums.length + 1];
        for (Integer key : freqMap.keySet()) {
            int freq = freqMap.get(key);
            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();
            }
            bucket[freq].add(key);
        }

        List<Integer> list = new ArrayList<>();
        for (int i = bucket.length - 1; i >= 0; i--) {
            if (bucket[i] != null) {
                list.addAll(bucket[i]);
            }
            if (list.size() == k) {
                break;
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    int[] topKFrequentWithMapQueue(int[] nums, int k) {
        HashMap<Integer, Integer> mapFrequency = new HashMap<>();
        for (int num : nums) {
            mapFrequency.put(num, mapFrequency.getOrDefault(num, 0) + 1);
        }
        Queue<Integer[]> queue = new PriorityQueue<Integer[]>((a, b) -> a[1] - b[1]);
        for (int key : mapFrequency.keySet()) {
            queue.add(new Integer[]{key, mapFrequency.get(key)});
            if (queue.size() > k) {
                queue.poll();
            }
        }
        int[] result = new int[k];
        while (!queue.isEmpty()) {
            result[--k] = queue.poll()[0];
        }
        return result;
    }

    int[] topKFrequentOptimized(int[] nums, int k) {
        HashMap<Integer, Integer> mapFrequency = new HashMap<>();
        for (int num : nums) {
            mapFrequency.put(num, mapFrequency.getOrDefault(num, 0) + 1);
        }
        Queue<Integer> queue = new PriorityQueue<Integer>((a, b) -> mapFrequency.get(b) - mapFrequency.get(a));
        queue.addAll(mapFrequency.keySet());

        int[] result = new int[k];
        int i = 0;
        while (k > 0) {
            result[i++] = queue.poll();
            k--;
        }
        return result;
    }

    // Sort Characters By Frequency
    // Given a string s, sort it in decreasing order based on the frequency of the characters.
    // The frequency of a character is the number of times it appears in the string.
    // Return the sorted string. If there are multiple answers, return any of them.
    String frequencySort(String s) {
        HashMap<Character, Integer> frequenceMap = new HashMap<>();
        for (char ch : s.toCharArray()) {
            frequenceMap.put(ch, frequenceMap.getOrDefault(ch, 0) + 1);
        }

        // Queue with frequency values decending order
        Queue<Character> queue = new PriorityQueue<>((a, b) -> frequenceMap.get(b) - frequenceMap.get(a));
        queue.addAll(frequenceMap.keySet());

        String ans = "";
        while (!queue.isEmpty()) {
            char ch = queue.poll();
            for (int i = 0; i < frequenceMap.get(ch); i++) {
                ans += ch + "";
            }
        }
        return ans;
    }

    // Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array.
    // The result should also be sorted in ascending order.
    // Input : arr = [1,2,3,4,5], k = 4, x = 3 ===> output {1,2,3,4}
    // Input: arr[] = {10, 2, 14, 4, 7, 6}, X = 5, K = 3  === > Output: 4 6 7

    List<Integer> findClosestElementsWithIIPointer(int[] arr, int k, int x) {
        if (arr == null || arr.length == 0)
            return null;

        int start = 0, end = arr.length - 1;
        while (end - start >= k) {
            if (Math.abs(arr[start] - x) > Math.abs(arr[end] - x)) {
                start++;
            } else {
                end--;
            }
        }

        List<Integer> result = new ArrayList<>(k);
        for (int i = start; i <= end; i++) {
            result.add(arr[i]);
        }

        return result;
    }

    List<Integer> findClosestElementsWithBinarySearch(int[] arr, int k, int x) {
        if (arr == null || arr.length == 0)
            return null;

        int start = 0, end = arr.length - k;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (x - arr[mid] > arr[mid + k] - x)
                start = mid + 1;
            else
                end = mid;
        }

        List<Integer> result = new ArrayList<>(k);
        for (int i = start; i < start + k; i++) {
            result.add(arr[i]);
        }

        return result;
    }

    List<Integer> findClosestElementsWithHeap(int[] arr, int k, int x) {
        if (arr == null || arr.length == 0)
            return null;

        // Queue defination with condition
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> {
            int a1 = Math.abs(a - x), b1 = Math.abs(b - x);
            return a1 == b1 ? a - b : a1 - b1;
        });
        for (int a : arr) {
            minHeap.add(a);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++)
            result.add(minHeap.poll());
        Collections.sort(result);
        return result;
    }

    //Given an array of integers arr and an integer k. Find the least number of unique integers after removing exactly k elements.
    // Input: arr = [4,3,1,1,3,3,2], k = 3  == > Output: 2
    int findLeastNumOfUniqueInts(int[] arr, int k) {
        // Frequence Mapper
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        for (int a : arr) {
            freqMap.put(a, freqMap.getOrDefault(a, 0) + 1);
        }

        // Queue with keys
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            queue.add(new int[]{entry.getKey(), entry.getValue()});
        }

        // polling k elements
        for (int i = 0; i < k; i++) {
            int[] element = queue.poll();
            if (element[1] > 1) {
                element[1]--;
                queue.add(element);
            }
        }

        return queue.size();

    }

    //Find the sum of all elements between given two k1’th and k2’th smallest elements of the array
    // Input : arr[] = {20, 8, 22, 4, 12, 10, 14},  k1 = 3,  k2 = 6   == > Output : 26
    int sumBetweenTwoKth(int[] arr, int k1, int k2) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int a : arr)
            maxHeap.add(a);
        if (maxHeap.size() > k2)
            maxHeap.poll();
        maxHeap.poll();
        int ans = 0;
        while (maxHeap.size() > k1) {
            ans += maxHeap.poll();
        }
        return ans;
    }

    // Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
    // Return any possible rearrangement of s or return "" if not possible.
    // Input: s = "aab"  ===> Output: "aba"
    // Input: s = "aaab"  ==> Output: ""

    String reorganizeString(String s) {
        HashMap<Character, Integer> frequency = new HashMap<>();
        for (char ch : s.toCharArray())
            frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);

        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> frequency.get(b) - frequency.get(a));
        maxHeap.addAll(frequency.keySet());
        StringBuilder result = new StringBuilder();
        while (maxHeap.size() >= 2) {
            char ch1 = maxHeap.poll(), ch2 = maxHeap.poll();
            result.append(ch1);
            result.append(ch2);
            frequency.put(ch1, frequency.getOrDefault(ch1, 0) - 1);
            frequency.put(ch2, frequency.getOrDefault(ch2, 0) - 1);
            if (frequency.get(ch1) > 0)
                maxHeap.add(ch1);
            if (frequency.get(ch2) > 0)
                maxHeap.add(ch2);
        }

        if(!maxHeap.isEmpty()) {
            char ch= maxHeap.poll();
            if(frequency.get(ch)>1)
                return "";
            result.append(ch);
        }

        return result.toString();
    }

}

class KthLargest {

    PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
    int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        for (int num : nums)
            add(num);
    }

    public int add(int val) {
        queue.add(val);
        if (queue.size() > k)
            queue.poll();
        return queue.peek();
    }
}
