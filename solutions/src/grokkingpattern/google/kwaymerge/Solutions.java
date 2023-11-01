package grokkingpattern.google.kwaymerge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solutions {

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {

        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // https://leetcode.com/problems/merge-k-sorted-lists/
    // Merge k Sorted Lists
    ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        int length = lists.length;
        int interval = 1;
        while (interval < length) {
            for (int i = 0; (i + interval) < length; i = i + interval * 2) {
                lists[i] = mergeTwoSortedLists(lists[i], lists[i + interval]);
            }
            interval *= 2;
        }
        return lists[0];
    }

    ListNode mergeTwoSortedLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(-1);
        ListNode temp = head;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                temp.next = list1;
                list1 = list1.next;
            } else {
                temp.next = list2;
                list2 = list2.next;
            }
            temp = temp.next;
        }

        if (list1 != null) temp.next = list1;
        if (list2 != null) temp.next = list2;

        return head.next;
    }

    //  Kth Smallest Element in a Sorted Matrix
    // https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
    int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0) return -1;

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                maxHeap.add(matrix[i][j]);
            }
        }

        while (!maxHeap.isEmpty() && k-- > 1) {
            maxHeap.poll();
        }

        return maxHeap.peek();
    }

    // https://leetcode.com/problems/median-of-two-sorted-arrays
    //

    double findMedianSortedArraysWithPointer(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int i = 0, j = 0, m1 = 0, m2 = 0;

        // Median
        for (int c = 0; c <= (n + m) / 2; c++) {
            m2 = m1;
            if (i != n && j != m) {
                if (nums1[i] > nums2[j]) m1 = nums2[j++];
                else m1 = nums1[i++];
            } else if (i < n) {
                m1 = nums1[i++];
            } else m1 = nums2[j++];
        }

        // if sum of n and m is odd
        return (n + m) % 2 == 1 ? (double) m1 : ((double) m1 + (double) m2) / 2.0;
    }

    double findMedianSortedArraysWithBs(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        if (n > m) return findMedianSortedArraysWithBs(nums2, nums1);
        int len = n + m;
        int left = (n + m + 1) / 2;
        int low = 0, high = n;
        while (low <= high) {
            int mid1 = low + (high - low) / 2;
            int mid2 = left - mid1;
            int l1 = Integer.MIN_VALUE, l2 = Integer.MIN_VALUE, r1 = Integer.MAX_VALUE, r2 = Integer.MAX_VALUE;

            if (mid1 < n) r1 = nums1[mid1];
            if (mid2 < m) r2 = nums2[mid2];
            if (mid1 - 1 >= 0) l1 = nums1[mid1 - 1];
            if (mid2 - 1 >= 0) l2 = nums2[mid2 - 1];

            if (l1 <= r2 && l2 <= r1) {
                return len % 2 == 1 ? Math.max(l1, l2) : ((double) (Math.max(l1, l2) + Math.min(r1, r2))) / 2.0;
            } else if (l1 > r2) high = mid1 - 1;
            else low = mid1 + 1;
        }
        return 0;
    }

    // Kth Largest Element in an Array
    // https://leetcode.com/problems/kth-largest-element-in-an-array/
    int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> a - b);
        for (int i = 0; i < nums.length; i++) {
            queue.add(nums[i]);
            if (queue.size() > k) {
                queue.poll();
            }
        }

        return queue.peek();
    }

    // https://leetcode.com/problems/k-closest-points-to-origin/
    // K Closest Points to Origin
    int[][] kClosest(int[][] points, int k) {
        if (points == null || points.length == 0) return new int[0][];

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        for (int i = 0; i < points.length; i++) {
            queue.add(new int[]{i, points[i][0] * points[i][0] + points[i][1] * points[i][1]});
        }

        int[][] result = new int[k][k];
        int i = 0;
        while (k > 0) {
            result[--k] = points[queue.poll()[0]];
        }

        return result;
    }

    // https://leetcode.com/problems/top-k-frequent-elements/
    // Top K Frequent Elements
    int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> mapFrequency = new HashMap<>();
        for (int num : nums) {
            mapFrequency.put(num, mapFrequency.getOrDefault(num, 0) + 1);
        }
        Queue<Integer> queue = new PriorityQueue<Integer>((a, b) -> mapFrequency.get(b) - mapFrequency.get(a));
        queue.addAll(mapFrequency.keySet());

        int[] result = new int[k];
        int i = 0;
        while (k-- > 0) {
            result[i++] = queue.poll();
        }
        return result;
    }

    //https://leetcode.com/problems/sort-characters-by-frequency/
    // Sort Characters By Frequency
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

    // Kth Largest Element in a Stream
    // https://leetcode.com/problems/kth-largest-element-in-a-stream/
    static class KthLargest {

        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        int k;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            for (int num : nums)
                add(num);
        }

        public int add(int val) {
            queue.add(val);
            if (queue.size() > k) queue.poll();
            return queue.peek();
        }
    }

    // https://leetcode.com/problems/find-k-closest-elements/
    //  Find K Closest Elements
    List<Integer> findClosestElements(int[] arr, int k, int x) {
        if (arr == null || arr.length == 0) return null;

        int start = 0, end = arr.length - k;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (x - arr[mid] > arr[mid + k] - x) start = mid + 1;
            else end = mid;
        }

        List<Integer> result = new ArrayList<>(k);
        for (int i = start; i < start + k; i++) {
            result.add(arr[i]);
        }

        return result;
    }

    // https://leetcode.com/problems/least-number-of-unique-integers-after-k-removals/
    // Least Number of Unique Integers after K Removals
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

    // https://www.geeksforgeeks.org/sum-elements-k1th-k2th-smallest-elements/
    // Sum of all elements between k1’th and k2’th smallest elements
    long sumBetweenTwoKth(long[] A, long N, long K1, long K2) {
        // Using max heap to find K1'th and K2'th smallest elements
        PriorityQueue<Long> maxH = new PriorityQueue<>(Collections.reverseOrder());
        // Using this for loop we eliminate the extra  elements which are greater than K2'th smallest element as they are not required for us
        for (int i = 0; i < N; i++) {
            maxH.add(A[i]);
            if (maxH.size() > K2) {
                maxH.remove();
            }
        }
        // popping out the K2'th smallest element
        maxH.remove();
        long ans = 0;
        // adding the elements to ans until we reach the K1'th smallest element
        while (maxH.size() > K1) {
            ans += maxH.peek();
            maxH.remove();
        }
        return ans;
    }

    // https://leetcode.com/problems/reorganize-string/
    // Reorganize String
    String reorganizeString(String s) {
        HashMap<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray())
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> freqMap.get(b) - freqMap.get(a));
        maxHeap.addAll(freqMap.keySet());
        StringBuilder result = new StringBuilder();
        while (maxHeap.size() >= 2) {
            char ch1 = maxHeap.poll();
            char ch2 = maxHeap.poll();
            result.append(ch1);
            result.append(ch2);
            freqMap.put(ch1, freqMap.getOrDefault(ch1, 0) - 1);
            freqMap.put(ch2, freqMap.getOrDefault(ch2, 0) - 1);

            if (freqMap.get(ch1) > 0)
                maxHeap.add(ch1);
            if (freqMap.get(ch2) > 0)
                maxHeap.add(ch2);
        }
        if (!maxHeap.isEmpty()) {
            char ch = maxHeap.poll();
            if (freqMap.get(ch) > 1)
                return "";
            result.append(ch);
        }
        return result.toString();
    }

    // https://leetcode.com/problems/task-scheduler/
    // Task Scheduler
    int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char c : tasks) {
            freq[c - 'A']++;
        }
        Arrays.sort(freq);
        int maxFreq = freq[25] - 1; // maximum frequencey of any task
        int idleSlot = maxFreq * n; // slots
        for (int i = 24; i >= 0 && freq[i] > 0; i--) {
            idleSlot -= Math.min(maxFreq, freq[i]);
        }
        return Math.max(idleSlot + tasks.length, tasks.length);

    }
}
