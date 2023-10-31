package blindpatternsolutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class HeapSolutions {

    int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        Queue<Integer> heap = new PriorityQueue<>((n1, n2) -> map.get(n1) - map.get(n2));

        for (int key : map.keySet()) {
            heap.add(key);
            if (heap.size() > k) {
                heap.poll();
            }
        }

        int[] result = new int[k];
        for (int i = k - 1; i >= 0; --i) {
            result[i] = heap.poll();
        }

        return result;
    }

    // Top K Elements in array
    int[] topKElements(int[] nums, int k) {
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

    // Largest Rectangle in Histogram
    // https://leetcode.com/problems/largest-rectangle-in-histogram
    int largestRectangleArea(int[] heights) {
        int n = heights.length, maxArea = 0;
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i <= n; i++) {
            int currHeight = i == n ? 0 : heights[i];
            // check if currHeight becomes greater then height[top] element of stack.
            // we do a push because it's an increasing sequence
            // otherwise we do pop and find area, so for that we write a while loop
            while (!s.isEmpty() && currHeight < heights[s.peek()]) {
                int top = s.pop();
                int width = s.isEmpty() ? i : i - s.peek() - 1;
                maxArea = Math.max(heights[top] * width, maxArea);
            }
            s.push(i);
        }

        return maxArea;
    }
}
