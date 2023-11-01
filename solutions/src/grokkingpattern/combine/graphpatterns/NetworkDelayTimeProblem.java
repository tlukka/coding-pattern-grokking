package grokkingpattern.combine.graphpatterns;

//You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed
// edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes
// for a signal to travel from source to target.

//We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the signal.
// If it is impossible for all the n nodes to receive the signal, return -1.
//Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2  Output: 2
//Input: times = [[1,2,1]], n = 2, k = 1 Output: 1
// Input: times = [[1,2,1]], n = 2, k = 2 //Output: -1

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class NetworkDelayTimeProblem {
    // Dijkstra Algorithm
    public static int networkDelayTimeFirst(int[][] times, int n, int k) {
        // Create the weighted directed graph (adjacency list).
        Map<Integer, List<int[]>> graphMap = new HashMap<>();
        for (int i = 1; i <= n; ++i) {
            graphMap.put(i, new ArrayList<>());
        }
        for (int[] time : times) {
            // Edge from node time[0] to node time[1] with weight time[2].
            graphMap.get(time[0]).add(new int[]{time[1], time[2]});
        }

        // Build Priority Queue
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        priorityQueue.add(new int[]{k, 0});

        // all shortest path from k
        Integer[] cost = new Integer[n + 1];

        while (priorityQueue.size() > 0) {
            int[] current = priorityQueue.remove(); // pop value
            if (cost[current[0]] != null)
                continue;
            cost[current[0]] = current[1]; // adding cost.
            for (int[] adjNode : graphMap.get(current[0])) {
                if (cost[adjNode[0]] == null) {
                    priorityQueue.add(new int[]{adjNode[0], current[1] + adjNode[1]});
                }
            }
        }

        int result = 0;
        for (int i = 1; i < cost.length; ++i) {
            if (cost[i] == null) return -1;
            result = Math.max(result, cost[i]);
        }

        return result;
    }

    // Bellman-Ford (SPFA).
    public static int networkDelayTime(int[][] times, int n, int k) {
        // Create the weighted directed graph (adjacency list).
        Map<Integer, List<int[]>> graphMap = new HashMap<>();
        for (int i = 1; i <= n; ++i) {
            graphMap.put(i, new ArrayList<>());
        }
        for (int[] time : times) {
            // Edge from node time[0] to node time[1] with weight time[2].
            graphMap.get(time[0]).add(new int[]{time[1], time[2]});
        }

        // Bellman-Ford (SPFA) to compute the shortest path from K to all other nodes.
        Integer[] cost = new Integer[n + 1];

        Deque<Integer> queue = new ArrayDeque<>();
        cost[k] = 0;
        queue.add(k);
        while (!queue.isEmpty()) {
            int current = queue.remove();
            for (int[] adjNode : graphMap.get(current)) {
                if (cost[adjNode[0]] == null || adjNode[1] + cost[current] < cost[adjNode[0]]) {
                    cost[adjNode[0]] = adjNode[1] + cost[current];
                    queue.add(adjNode[0]);
                }
            }
        }

        int result = 0;
        for (int i = 1; i < cost.length; ++i) {
            if (cost[i] == null) return -1;
            result = Math.max(result, cost[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(networkDelayTimeFirst(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2));
        System.out.println(networkDelayTime(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2));
        System.out.println(networkDelayTimeFirst(new int[][]{{1, 2, 1}}, 2, 2));
        System.out.println(networkDelayTime(new int[][]{{1, 2, 1}}, 2, 2));
        System.out.println(networkDelayTimeFirst(new int[][]{{1, 2, 1}}, 2, 1));
        System.out.println(networkDelayTime(new int[][]{{1, 2, 1}}, 2, 1));
    }
}
