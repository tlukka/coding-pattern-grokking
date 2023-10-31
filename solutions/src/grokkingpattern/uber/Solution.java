package grokkingpattern.uber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        Solution sl = new Solution();
        //List<String> result = sl.fullJustify(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16);
        //System.out.print(result.toString());
        //System.out.print(sl.zeroFilledSubarray(new int[]{1, 3, 0, 0, 7, 0, 0, 0}));
        System.out.println(sl.minimuTimeTrips(new int[]{1, 2, 3}, 5));
    }

    long minimuTimeTrips(int[] time, int totalTrips) {
        long l = 1, r = Arrays.stream(time).min().getAsInt() * (long) totalTrips;
        while (l < r) {
            long mid = l + (r - l) / 2;
            if (numTrips(time, mid) >= totalTrips)
                r = mid;
            else
                l = mid + 1;
        }

        return l;
    }

    long numTrips(int[] time, long m) {
        return Arrays.stream(time).asLongStream().reduce(0L, (total, t) -> total + m / t);
    }

    long minimumTime(int[] time, int totalTrips) {
        long low = 1, end = 0;
        for (int i : time)
            end = Math.max(end, i);
        end = end * totalTrips;
        while (low < end) {
            long mid = low + (end - low) / 2;
            long completedTrips = 0;
            for (int i : time) {
                if (i <= mid)
                    completedTrips += mid / i;
                if (completedTrips > totalTrips)
                    break;
            }

            if (completedTrips < totalTrips)
                low = mid + 1;
            else
                end = mid;
        }

        return low;
    }

    int numDupDigitsAtMostN(int n) {
        String str = String.valueOf(n);
        int len = str.length();

        // all number with no repeat and length <len

        int unique = 0;
        for (int i = 1; i < len; i++) {
            unique += totalNoRepeat(i);
        }

        Set<Integer> seen = new HashSet<>();
        int i = 0;
        for (; i < len; i++) {
            int d = str.charAt(i) - '0';
            int temp = pivot(seen, d, i == 0);
            for (int j = i + 1; j < len; j++) {
                temp *= (10 - j);
            }

            unique += temp;
            if (!seen.add(d))
                break;
        }

        if (i == len)
            unique++;
        return n - unique;
    }

    int totalNoRepeat(int d) {
        int res = 9;
        for (int i = 1; i < d; i++) {
            res *= (10 - i);
        }
        return res;
    }

    int pivot(Set<Integer> set, int d, boolean first) {
        int res = 0;
        int i = first ? 1 : 0;
        while (i < d) {
            if (!set.contains(i++))
                res++;
        }

        return res;
    }

    long zeroFilledSubarray(int[] nums) {
        long ans = 0, c = 0;
        for (int i : nums) {
            if (i == 0) {
                c++;
                ans += c;
            } else
                c = 0;
        }
        return ans;
    }

    List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        List<String> tempList = new ArrayList<>();
        int curLen = 0;
        for (int i = 0; i < words.length; i++) {
            if (curLen + words[i].length() + tempList.size() > maxWidth) {
                int totalSpaces = maxWidth - curLen;
                int gaps = tempList.size() - 1;
                if (gaps == 0) {
                    String str = tempList.get(0);
                    for (int k = 0; k < totalSpaces; k++) {
                        str += " ";
                    }
                    result.add(str);
                } else {
                    int spacePerGap = totalSpaces / gaps;
                    int extraSpaces = totalSpaces % gaps;
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < tempList.size(); j++) {
                        sb.append(tempList.get(j));
                        if (j < gaps) {
                            while (spacePerGap-- > 0) {
                                sb.append(" ");
                            }
                            if (j < extraSpaces) {
                                sb.append(" ");
                            }
                        }
                    }
                    result.add(sb.toString());
                }
                tempList.clear();
                curLen = 0;
            }
            tempList.add(words[i]);
            curLen += words[i].length();
        }

        StringBuilder lastLine = new StringBuilder(String.join(" ", tempList));
        while (lastLine.length() < maxWidth) {
            lastLine.append(" ");
        }
        result.add(lastLine.toString());
        return result;
    }

    // https://leetcode.com/problems/bus-routes
    // Bus Routes

    int numBusesToDestination(int[][] routes, int source, int target) {

        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                int busStopNo = routes[i][j];
                ArrayList<Integer> busNos = map.getOrDefault(busStopNo, new ArrayList<>());
                busNos.add(i);
                map.put(busStopNo, busNos);
            }
        }

        // BFS
        LinkedList<Integer> queue = new LinkedList<>();
        // Keep track bus stops visited
        HashSet<Integer> busStopVisited = new HashSet<>();
        HashSet<Integer> busVisited = new HashSet<>();

        int numOfBuses = 0;

        queue.add(source);
        busStopVisited.add(source);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int currentStop = queue.removeFirst();
                if (currentStop == target)
                    return numOfBuses;

                ArrayList<Integer> buses = map.get(currentStop); // get all buses which comes to bus stop
                for (int bus : buses) {
                    // already visited contine
                    if (busVisited.contains(bus))
                        continue;


                    int[] busRoute = routes[bus];
                    for (int busStop : busRoute) {
                        if (busStopVisited.contains(busStop))
                            continue;

                        queue.addLast(busStop);
                        busStopVisited.add(busStop);
                    }
                    busVisited.add(bus);
                }
            }
            numOfBuses++;
        }

        return -1;
    }

    // https://www.geeksforgeeks.org/find-the-minimum-cost-to-reach-a-destination-where-every-station-is-connected-in-one-direction/

    int minCostByRecursion(int[][] costs, int s, int d) {
        if (s == d || s + 1 == d)
            return costs[s][d];
        int min = costs[s][d];

        for (int i = s + 1; i < d; i++) {
            min = Math.min(min, minCostByRecursion(costs, s, i) + minCostByRecursion(costs, i, d));
        }

        return min;
    }

    int minCostTopologicalSort(int[][] costs) {
        int[] dist = new int[costs.length];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        for (int i = 0; i < costs.length; i++) {
            for (int j = i + 1; j < costs.length; j++) {
                if (dist[j] > costs[i][j] + dist[i]) {
                    dist[j] = costs[i][j] + dist[i];
                }
            }
        }

        return dist[costs.length - 1];
    }

    int shortestPathByDp(int[][] graph, int u, int v) {
        int n = graph.length;
        int[][] dp = new int[n][n];
        for (int[] d : dp) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        dp[u][u] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (graph[i][j] != Integer.MAX_VALUE) {
                        dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[j][k]);
                    }
                }
            }
        }

        return dp[u][v];
    }

    // https://prepfortech.in/leetcode-solutions/minimum-costs-using-the-train-line/
    //Return the minimum time required to travel from train station 1 to train station target
    int minCost(int[][] costs) {
        if (costs == null || costs.length == 0)
            return 0;

        for (int i = 1; i < costs.length; i++) {
            costs[i][0] += Math.min(costs[i - 1][1], costs[i - 1][2]);
            costs[i][1] += Math.min(costs[i - 1][0], costs[i - 1][2]);
            costs[i][2] += Math.min(costs[i - 1][0], costs[i - 1][1]);
        }
        return Math.min(Math.min(costs[costs.length - 1][0], costs[costs.length - 1][1]), costs[costs.length - 1][2]);
    }

    //https://www.coddicted.com/solved-leetcode-2361-minimum-costs-using-the-train-line/
    //https://leetcode.com/problems/minimum-costs-using-the-train-line/
    public long[] minimumCosts(int[] regular, int[] express, int expressCost) {
        //0 express, 1 regular
        long[][] memo = new long[2][regular.length];
        dfs(regular, express, regular.length - 1, 0, 1, expressCost, memo);
        return memo[1];
    }

    private long dfs(int[] regular, int[] express, int index, long sum, int isRegular, int expressCost, long[][] memo) {
        if (index < 0) {
            return 0;
        }
        if (memo[isRegular][index] != 0) {
            return memo[isRegular][index];
        }

        if (isRegular == 1) {
            //either continue on the regular lane
            long a = regular[index] + dfs(regular, express, index - 1, sum, 1, expressCost, memo);
            //or switch to the express lane
            long b = expressCost + express[index] + dfs(regular, express, index - 1, sum, 0, expressCost, memo);
            memo[1][index] = Math.min(a, b);
            return memo[1][index];
        } else {
            //either continue on the express lane
            long a = express[index] + dfs(regular, express, index - 1, sum, 0, expressCost, memo);
            //or switch to the regular lane
            long b = regular[index] + dfs(regular, express, index - 1, sum, 1, expressCost, memo);
            memo[0][index] = Math.min(a, b);
            return memo[0][index];
        }
    }

    // https://leetcode.com/problems/minimum-cost-for-tickets
    int mincostTickets(int[] days, int[] costs) {
        /*int n= days.length;
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0]=0;

        for(int i=1; i<=n; i++) {
            dp[i]= costs[0]+ dp[i-1]; // 1 day pass
            int j= i-1;
            while(j>=0 && days[i-1]-days[j]<7) {
                j--;
                dp[i]= Math.min(dp[i], dp[j+1]+costs[1]); // 7 day pass
            }
            j=i-1;
            while(j>=0 && days[i-1]-days[j]<30) {
                j--;
                dp[i]= Math.min(dp[i], dp[j+1] + costs[2]); // 30 days pass
            }
        }

        return dp[n];
        */

        int maxDay = days[days.length - 1];
        boolean[] travelDays = new boolean[maxDay + 1];
        for (int day : days) {
            travelDays[day] = true;
        }

        int[] dp = new int[maxDay + 1];
        dp[0] = 0;
        for (int i = 1; i <= maxDay; i++) {
            if (!travelDays[i]) {
                dp[i] = dp[i - 1];
                continue;
            }
            dp[i] = dp[i - 1] + costs[0];
            dp[i] = Math.min(dp[i], dp[Math.max(0, i - 7)] + costs[1]);
            dp[i] = Math.min(dp[i], dp[Math.max(0, i - 30)] + costs[2]);
        }

        return dp[maxDay];
    }

    // https://leetcode.com/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph
    List<List<Integer>> graph = new ArrayList<>();

    public long countPairs(int n, int[][] edges) {
        // Build graph...
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        long sum = 0, squareSum = 0;

        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                long count = dfs(i, visited);
                sum += count;
                squareSum += count * count;
            }
        }
        return (sum * sum - squareSum) / 2;
    }

    long dfs(int node, boolean[] visited) {
        visited[node] = true;
        long ans = 1;
        for (int curr : graph.get(node)) {
            if (!visited[curr]) {
                ans += dfs(curr, visited);
            }
        }

        return ans;
    }

    public long countPairsUninonFind(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        long connectedPairsSum = 0;
        for (int[] edge : edges) {
            uf.uninon(edge[0], edge[1]);
        }

        List<Long> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (uf.parent[i] == i) {
                list.add(uf.rank[i] + 0L);
            }
        }

        // Math equations
        int total = n * (n - 1) / 2;
        for (long l : list) {
            connectedPairsSum += ((l) * (l - 1) / 2);
        }
        return total - connectedPairsSum;
    }

    // https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero
    int minReorder(int n, int[][] connections) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] conn : connections) {
            graph.get(conn[0]).add(conn[1]); // index[1] == 1 road present.
            graph.get(conn[1]).add(-conn[0]); // index[1] == 0 road absent.
        }

        boolean[] visited = new boolean[n];
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = true;
        int reorderRoads = 0;
        while (!queue.isEmpty()) {
            int city = queue.poll();
            for (int neighbor : graph.get(Math.abs(city))) {
                if (!visited[Math.abs(neighbor)]) {
                    visited[Math.abs(neighbor)] = true;
                    queue.add(neighbor);
                    if (neighbor > 0)
                        reorderRoads++;
                }
            }
        }
        return reorderRoads;
    }


    // https://leetcode.com/problems/minimum-score-of-a-path-between-two-cities
    int minScore = Integer.MAX_VALUE;

    public int minScore(int n, int[][] roads) {
        List<int[]>[] graph = new List[n + 1];
        boolean[] visited = new boolean[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] r : roads) {
            graph[r[0]].add(new int[]{r[1], r[2]});
            graph[r[1]].add(new int[]{r[0], r[2]});
        }
        visited[1] = true;
        dfs(1, graph, visited);
        return minScore;
    }

    void dfs(int curr, List<int[]>[] graph, boolean[] visited) {
        for (int[] nei : graph[curr]) {
            int nxt = nei[0], dist = nei[1];
            minScore = Math.min(minScore, dist);
            if (!visited[nxt]) {
                visited[nxt] = true;
                dfs(nxt, graph, visited);
            }
        }
    }

    class UnionFind {
        int[] parent;
        int[] rank;

        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return x;
        }

        void uninon(int x, int y) {
            int nx = find(x);
            int ny = find(y);
            if (nx == ny)
                return;

            parent[ny] = nx;
            rank[nx] += rank[ny];
        }
    }
}
