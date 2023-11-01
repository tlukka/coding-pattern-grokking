package grokkingpattern.combine.graphpatterns;
//  Cheapest Flights Within K Stops
// There are n cities connected by some number of flights. You are given an array flights where
// flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.

//You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.
//Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1  Output: 700
//Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1 Output: 200
//Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0 Output: 500
//https://leetcode.com/problems/cheapest-flights-within-k-stops/discuss/361711/Java-DFSBFSBellman-Ford-Dijkstra's

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class CheapestFlightWithStopsProblem {
    int ans_dfs;

    public int findCheapestPriceByDFS(int n, int[][] flights, int src, int dst, int K) {
        ans_dfs = Integer.MAX_VALUE;
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int[] i : flights) {
            map.putIfAbsent(i[0], new ArrayList<>());
            map.get(i[0]).add(new int[]{i[1], i[2]});
        }
        dfs(map, src, dst, K + 1, 0);
        return ans_dfs == Integer.MAX_VALUE ? -1 : ans_dfs;
    }

    public void dfs(Map<Integer, List<int[]>> map, int src, int dst, int k, int cost) {
        if (k < 0)
            return;
        if (src == dst) {
            ans_dfs = cost;
            return;
        }
        if (!map.containsKey(src))
            return;
        for (int[] i : map.get(src)) {
            if (cost + i[1] > ans_dfs)
                //Pruning, check the sum of current price and next cost. If it's greater then the ans so far, continue
                continue;
            dfs(map, i[0], dst, k - 1, cost + i[1]);
        }
    }

    public int findCheapestPriceByBFS(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int[] i : flights) {
            map.putIfAbsent(i[0], new ArrayList<>());
            map.get(i[0]).add(new int[]{i[1], i[2]});
        }
        int step = 0;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{src, 0}); // storing node and cost.
        int ans = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();
                if (curr[0] == dst)
                    ans = Math.min(ans, curr[1]);
                for (int[] f : map.getOrDefault(curr[0], Collections.emptyList())) {
                    if (curr[1] + f[1] > ans)      //Pruning
                        continue;
                    q.offer(new int[]{f[0], curr[1] + f[1]});
                }
            }
            if (step++ > K)
                break;
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    // Dijkstra Algorithm
    public static int findCheapestPriceDijkstra(int n, int[][] flights, int src, int dst, int k) {
        // Create the weighted directed graph (adjacency list).
        Map<Integer, List<int[]>> graphMap = new HashMap<>();
        for (int[] flight : flights) {
            // Edge from node time[0] to node time[1] with weight time[2].
            graphMap.putIfAbsent(flight[0], new ArrayList<>());
            graphMap.get(flight[0]).add(new int[]{flight[1], flight[2]});
        }
        // Build Priority Queue
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        priorityQueue.offer(new int[]{0, src, k + 1});
        while (!priorityQueue.isEmpty()) {
            int[] current = priorityQueue.poll();
            int costPrice = current[0], currentStop = current[1], stopValue = current[2];
            if (currentStop == dst) return costPrice;
            if (stopValue > 0) {
                for (int[] next : graphMap.getOrDefault(currentStop, new ArrayList<>())) {
                    priorityQueue.add(new int[]{costPrice + next[1], next[0], stopValue - 1});
                }
            }
        }

        return -1;
    }

    // Bellman-Ford (SPFA).
    public static int findCheapestPriceByBellmanFord(int n, int[][] flights, int src, int dest, int k) {
        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[src] = 0;
        for (int i = 0; i <= k; i++) {
            int[] temp = Arrays.copyOf(cost, n);
            for (int[] flight : flights) {
                int curr = flight[0], next = flight[1], price = flight[2];
                if (temp[curr] != Integer.MAX_VALUE)
                    cost[next] = Math.min(cost[next], temp[curr] + price);
            }
        }
        return cost[dest] == Integer.MAX_VALUE ? -1 : cost[dest];
    }

    public static void main(String[] args) {

    }
}
