package grokkingpattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class GraphSolutions {

    // Min Cost to Connect All Points
    // https://leetcode.com/problems/min-cost-to-connect-all-points/

    // Prim Algoritham
    int minCostConnectPointsByPrim(int[][] points) {
        int n = points.length;
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        pq.offer(new int[]{0, 0, 0}); // starting point

        boolean[] visited = new boolean[n];

        int selectedEdge = 0, cost = 0;
        // traverse v-1
        while (!pq.isEmpty() && selectedEdge < n - 1) {
            int[] currentEdge = pq.poll();
            if (visited[currentEdge[1]]) {
                continue; // continue if current target already visited
            }
            visited[currentEdge[1]] = true;
            cost += currentEdge[2];
            selectedEdge++;

            for (int j = 0; j < n; j++) {
                if (!visited[j]) {
                    pq.offer(new int[]{currentEdge[1], j, dist[currentEdge[1]][j]});
                }
            }
        }

        return cost;

    }


    // Network Delay in time
    // https://leetcode.com/problems/network-delay-time/
    // Bellman Ford Solution
    int networkDelayTimeByBellman(int[][] times, int n, int k) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;
        // travels on all times
        for (int i = 1; i < n; i++) {
            for (int[] edge : times) {
                int u = edge[0], v = edge[1], w = edge[2];
                if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) dist[v] = dist[u] + w;
            }
        }

        int maxWait = 0;
        for (int i = 1; i <= n; i++) {
            maxWait = Math.max(dist[i], maxWait);
        }
        return maxWait == Integer.MAX_VALUE ? -1 : maxWait;
    }

    // Time Needed to Inform All Employees
    // https://leetcode.com/problems/time-needed-to-inform-all-employees

    int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        List<List<Integer>> employees = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            employees.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            if (manager[i] != -1) {
                employees.get(manager[i]).add(i);
            }
        }

        return informTime(headID, employees, informTime);
    }

    int informTime(int currentEmployee, List<List<Integer>> employees, int[] informTime) {
        int maxTime = 0;
        for (int subOrdinate : employees.get(currentEmployee)) {
            maxTime = Math.max(maxTime, informTime(subOrdinate, employees, informTime));
        }

        return maxTime + informTime[currentEmployee];
    }

    int numOfMinutesDfs(int n, int headID, int[] manager, int[] informTime) {
        int maxTime = 0;
        for (int i = 0; i < n; i++) {
            if (informTime[i] == 0) continue;
            maxTime = Math.max(maxTime, dfsInformTime(manager, informTime, manager[i]));
        }
        return maxTime;
    }

    //Find the City With the Smallest Number of Neighbors at a Threshold Distance
    //https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/
    int dfsInformTime(int[] manager, int[] informtime, int emp) {
        if (manager[emp] != 1) {
            informtime[emp] += dfsInformTime(manager, informtime, manager[emp]);
            informtime[emp] = -1;
        }
        return informtime[emp];
    }

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        List<List<Pair>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        // Build Graph
        for (int[] edge : edges) {
            adjList.get(edge[0]).add(new Pair(edge[1], edge[2]));
            adjList.get(edge[1]).add(new Pair(edge[0], edge[2]));
        }

        int city = 0;
        int cityMax = n;

        for (int i = 0; i < n; i++) {
            int[] dists = new int[n];
            Arrays.fill(dists, Integer.MAX_VALUE);
            dijkstra(i, dists, adjList);
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (dists[j] != Integer.MAX_VALUE && dists[j] <= distanceThreshold)
                    count++;
            }

            if (count <= cityMax) {
                city = i;
                cityMax = count;
            }
        }
        return city;
    }

    void dijkstra(int src, int[] dists, List<List<Pair>> adjList) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(src, 0)); // start point
        dists[src] = 0;

        // main logic
        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            int currentNode = current.node;
            int currentDist = current.dist;

            for (Pair neighbour : adjList.get(currentNode)) {
                int newDist = currentDist + neighbour.dist;
                if (newDist <= dists[neighbour.node]) {
                    pq.add(new Pair(neighbour.node, newDist));
                    dists[neighbour.node] = newDist;
                }
            }
        }

    }

    void bellmanFord(int src, int[] dist, int[][] edges, int n) {
        // traverse n-1 vertex
        for (int i = src; i < n - 1; i++) {
            for (int[] edge : edges) {
                int u = edge[0], v = edge[1], w = edge[2];
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }

                // edges are bi-directional so need to check other direction
                if (dist[v] + w < dist[u]) {
                    dist[u] = dist[v] + w;
                }
            }
        }
    }

    // Number of Operations to Make Network Connected
    // https://leetcode.com/problems/number-of-operations-to-make-network-connected

    int connectedCableCount = 0;

    int makeConnected(int n, int[][] connections) {

        // Building graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] c : connections) {
            graph.get(c[0]).add(c[1]);
            graph.get(c[1]).add(c[0]);
        }

        dfsCable(graph);

        if (connections.length < n - 1)
            return -1;
        return connectedCableCount - 1;

    }

    void dfsCable(List<List<Integer>> graph) {
        boolean[] visited = new boolean[graph.size()];
        for (int i = 0; i < graph.size(); i++) {
            if (!visited[i]) {
                dfsCableUtility(graph, visited, i);
                connectedCableCount++;
            }
        }
    }

    void dfsCableUtility(List<List<Integer>> graph, boolean[] visited, int vertex) {
        visited[vertex] = true;
        for (int i = 0; i < graph.get(vertex).size(); i++) {
            int edge = graph.get(vertex).get(i);
            if (!visited[edge]) {
                dfsCableUtility(graph, visited, edge);
            }
        }
    }

    int makeConnectedByUnionFind(int n, int[][] connections) {
        if (connections.length < n - 1)
            return -1;
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        for (int[] c : connections)
            unionCable(c[0], c[1], parent);

        int result = 0;
        for (int j = 0; j < n; j++) {
            if (parent[j] == j)
                result++;
        }

        return result - 1;
    }

    void unionCable(int i, int j, int[] parent) {
        if (find(i, parent) != find(j, parent)) {
            parent[parent[j]] = parent[i];
        }
    }

    int find(int i, int[] parent) {
        if (parent[i] != i)
            parent[i] = find(parent[i], parent);
        return parent[i];
    }

    // Number of Closed Islands
    // https://leetcode.com/problems/number-of-closed-islands

    int closedIslands(int[][] grid, int n) {
        int islandCount = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 0) {
                    if (isClosed(grid, r, c, grid.length, grid[0].length))
                        islandCount++;
                }
            }
        }
        return islandCount;
    }

    boolean isClosed(int[][] grid, int r, int c, int rows, int cols) {
        // out of boundry
        if (r < 0 || r >= rows || c < 0 || c >= cols)
            return false;
        if (grid[r][c] == 1)
            return true;
        //shink island
        grid[r][c] = 1;
        boolean left = isClosed(grid, r, c - 1, rows, cols);
        boolean right = isClosed(grid, r, c + 1, rows, cols);
        boolean down = isClosed(grid, r - 1, c, rows, cols);
        boolean up = isClosed(grid, r + 1, c, rows, cols);
        return left && right && down && up;
    }

    // Shortest Path in Binary Matrix
    // https://leetcode.com/problems/shortest-path-in-binary-matrix
    int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1)
            return -1;
        int[][] dirs = new int[][]{{-1, -1}, {0, -1}, {-1, 0}, {0, 0}, {0, 1}, {1, 0}, {1, 1}, {-1, 1}};
        boolean[][] visited = new boolean[n][n];
        int result = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        visited[0][0] = true; // starting point
        while (!queue.isEmpty()) {
            int size = queue.size();
            result++;
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int x = current[0], y = current[1];
                if (x == n - 1 && y == n - 1)
                    return result;
                // travel all directions
                for (int[] dir : dirs) {
                    int nx = dir[0] + x;
                    int ny = dir[1] + y;
                    if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visited[nx][ny] && grid[nx][ny] == 0) {
                        queue.add(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return -1;
    }

    //As Far from Land as Possible
    // https://leetcode.com/problems/as-far-from-land-as-possible

    int maxDistance(int[][] grid) {
        int[][] dirs = new int[][]{{-1, 0}, {0, -1}, {0, 1}, {1, 0}}; // all directions left, right, down left
        int n = grid.length;
        int m = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (grid[r][c] == 1)
                    queue.add(new int[]{r, c});
            }
        }

        // grid contains full land
        if (queue.size() == n * m)
            return -1;

        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                for (int[] dir : dirs) {
                    int nx = dir[0] + current[0];
                    int ny = dir[1] + current[1];
                    if (nx >= 0 && ny >= 0 && nx < n && ny < m && grid[nx][ny] == 0) {
                        grid[nx][ny] = 1;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }
        return level - 1;
    }

    // Number of Enclaves
    // https://leetcode.com/problems/number-of-enclaves
    int[][] dirs = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}}; // all directions left, right, down & up

    int numEnclaves(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (r == 0 || c == 0 || r == rows - 1 || c == cols - 1) {
                    // edge of grid
                    if (grid[r][c] == 1) {
                        numEnclavesBoundary(grid, r, c, rows, cols);
                    }
                }
            }
        }

        int count = 0;
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (grid[i][j] == 1) {
                    count++;
                }
            }
        }

        return count;
    }

    void numEnclavesBoundary(int[][] grid, int r, int c, int rows, int cols) {
        if (r < 0 || r >= rows || c < 0 || c >= cols || grid[r][c] == 0)
            return;
        grid[r][c] = 0; // make land to water and move on

        for (int[] dir : dirs) {
            numEnclavesBoundary(grid, dir[0] + r, dir[1] + c, rows, cols);
        }
    }

    // Find the Town Judge
    // https://leetcode.com/problems/find-the-town-judge
    int findJudge(int n, int[][] trust) {
        int[] count = new int[n + 1];
        for (int[] t : trust) {
            count[t[0]]--;
            count[t[1]]++;
        }
        for (int i = 1; i < n + 1; i++) {
            if (count[i] == n - 1)
                return i;
        }
        return -1;
    }

    int findJudgeBy2Array(int n, int[][] trust) {
        int[] trusted = new int[n + 1];
        int[] untrusted = new int[n + 1];
        for (int[] t : trust) {
            untrusted[t[0]]++;
            trusted[t[1]]++;
        }
        for (int i = 1; i < n + 1; i++) {
            if (trusted[i] == n - 1 && untrusted[i] == 0)
                return i;
        }
        return -1;
    }

    // Rotting Oranges
    //https://leetcode.com/problems/rotting-oranges/

    int orangesRotting(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int freshOranges = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2)
                    queue.add(new int[]{r, c});
                if (grid[r][c] == 1)
                    freshOranges++;
            }
        }

        if (freshOranges == 0)
            return 0;  // all already rotten hence no time taken

        if (queue.size() == 0)
            return -1;  // no rotten orange
        int minutes = -1;
        int[][] dirs = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        while (!queue.isEmpty()) {
            int size = queue.size();
            minutes++;
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int x = current[0], y = current[1];
                for (int[] dir : dirs) {
                    int nx = dir[0] + x, ny = dir[1] + y;
                    if (nx >= 0 && ny >= 0 && nx < rows && ny < cols && grid[nx][ny] == 1) {
                        grid[nx][ny] = 2; // mask fresh orange as rotten
                        freshOranges--;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }
        return freshOranges == 0 ? minutes : -1;
    }

    // Satisfiability of Equality Equations
    //https://leetcode.com/problems/satisfiability-of-equality-equations
    boolean equationsPossible(String[] equations) {
        int[] parent = new int[26];
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }

        // merge operation for connect
        for (String s : equations) {
            char sign = s.charAt(1);
            if (sign == '=') {
                int c1 = s.charAt(0) - 'a';
                int c2 = s.charAt(3) - 'a';
                connect(c1, c2, parent);
            }
        }

        // traverse string and search for any != operations
        for (String s : equations) {
            char sign = s.charAt(1);
            if (sign == '!') {
                int c1 = s.charAt(0) - 'a';
                int c2 = s.charAt(3) - 'a';
                if (checkConnection(c1, c2, parent)) {
                    return false;
                }
            }
        }

        return true;

    }

    boolean checkConnection(int i, int j, int[] parent) {
        int p1 = findParent(i, parent);
        int p2 = findParent(j, parent);
        if (p1 == p2)
            return true;
        return false;
    }

    void connect(int i, int j, int[] parent) {
        int p1 = findParent(i, parent);
        int p2 = findParent(j, parent);
        if (p1 == p2)
            return;
        if (p1 != p2) {
            if (p1 < p2)
                parent[p2] = p1;
            else {
                parent[p1] = p2;
            }
        }
    }


    int findParent(int u, int[] parent) {
        if (parent[u] != u)
            parent[u] = findParent(parent[u], parent);

        return parent[u];
    }

    //Regions Cut By Slashes
    //https://leetcode.com/problems/regions-cut-by-slashes
    // Beloe solution is based on Union Find
    int regionsBySlashes(String[] grid) {
        int N = grid.length, count = 1, n = N + 1;
        int[] parent = new int[n * n];
        for (int i = 0; i < n * n; i++) {
            int r = i / n, c = i % n;
            if (r == 0 || r == n - 1 || c == 0 || c == n - 1) {
                parent[i] = 0;
            } else {
                parent[i] = i;
            }
        }

        for (int r = 0; r < N; r++) {
            String str = grid[r];
            for (int c = 0; c < str.length(); c++) {
                char ch = str.charAt(c);
                if (ch == ' ') {
                    continue;
                }

                if (ch == '/') {
                    int p1 = findParentRegion(r * n + c + 1, parent);
                    int p2 = findParentRegion((r + 1) * n + c, parent);
                    count += mergeUnionRegion(p1, p2, parent);
                } else {
                    int p1 = findParentRegion(r * n + c, parent);
                    int p2 = findParentRegion((r + 1) * n + c + 1, parent);
                    count += mergeUnionRegion(p1, p2, parent);
                }
            }
        }
        return count;
    }

    int findParentRegion(int i, int[] parent) {
        if (parent[i] != i)
            parent[i] = findParent(parent[i], parent);
        return parent[i];
    }

    int mergeUnionRegion(int p1, int p2, int[] parent) {
        if (p1 != p2) {
            parent[p2] = p1;
            return 0;
        }
        return 1; // Agar cycle hui to 1 return karega
    }

    // By DFS

    int regionsBySlashesDfs(String[] grid) {
        int n = grid.length, count = 0, m = grid[0].length();
        int[][] g = new int[n * 3][m * 3];
        // formed 3*3 extended grid
        for (int r = 0; r < n; r++) {
            String str = grid[r];
            for (int c = 0; c < str.length(); c++) {
                char ch = str.charAt(c);
                if (ch == '/') {
                    g[r * 3][c * 3 + 2] = 1;
                    g[r * 3 + 1][c * 3 + 1] = 1;
                    g[r * 3 + 2][c * 3] = 1;
                } else if (ch == '\\') {
                    g[r * 3][c * 3] = 1;
                    g[r * 3 + 1][c * 3 + 1] = 1;
                    g[r * 3][c * 3 + 1] = 1;
                }
            }
        }

        for (int r = 0; r < g.length; r++) {
            for (int c = 0; c < g[0].length; c++) {
                if (g[r][c] == 0)
                    dfsRegion(g, r, c);
                count++;
            }
        }

        return count;
    }

    void dfsRegion(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 1)
            return;
        grid[i][j] = 1; // shink region
        int[][] dirs = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        for (int[] dir : dirs) {
            dfsRegion(grid, dir[0] + i, dir[1] + j);
        }
    }

    // Most Stones Removed with Same Row or Column
    // https://leetcode.com/problems/most-stones-removed-with-same-row-or-column
    int removeStones(int[][] stones) {
        int n = stones.length;
        if (n <= 1)
            return 0;

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            int[] u = stones[i];
            for (int j = 0; j < n; j++) {
                if (i == j)
                    continue;

                int[] v = stones[j];
                if (u[0] == v[0] || u[1] == v[1])
                    graph.get(i).add(j);
            }
        }

        boolean[] visited = new boolean[n];
        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfsStones(graph, i, visited);
                ans++;
            }
        }

        return n - ans;
    }

    void dfsStones(List<List<Integer>> graph, int start, boolean[] visited) {
        visited[start] = true;
        List<Integer> neghibors = graph.get(start);
        for (int x : neghibors) {
            if (!visited[x])
                dfsStones(graph, x, visited);
        }
    }

    // Possible Bipartition
    // https://leetcode.com/problems/possible-bipartition
    boolean possibleBipartition(int n, int[][] dislikes) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] dislike : dislikes) {
            graph.get(dislike[0]).add(dislike[1]);
            graph.get(dislike[1]).add(dislike[0]);
        }

        int[] color = new int[n + 1];
        Arrays.fill(color, -1);
        for (int i = 1; i <= n; i++) {
            if (color[i] == -1) {
                if (!bfsCheckDislike(i, color, graph))
                    return false;
            }
        }

        return true;
    }


    boolean bfsCheckDislike(int start, int[] color, List<List<Integer>> graph) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        color[start] = -1;
        while (!q.isEmpty()) {
            int current = q.poll();
            for (int edge : graph.get(current)) {
                if (color[edge] != -1) {
                    q.add(edge);
                    color[edge] = -1;
                } else if (color[current] == color[edge]) {
                    return false;
                }
            }
        }
        return true;
    }

    // Keys and Rooms
    // https://leetcode.com/problems/keys-and-rooms
    boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        dfsRoom(visited, rooms, 0);
        for (int i = 0; i < visited.length; i++)
            if (!visited[i])
                return false;

        return true;
    }

    void dfsRoom(boolean[] visited, List<List<Integer>> rooms, int start) {
        visited[start] = true;
        for (int key : rooms.get(start)) {
            if (!visited[key])
                dfsRoom(visited, rooms, key);
        }
    }

    boolean canVisitAllRoomsByBfs(List<List<Integer>> rooms) {
        int total = rooms.size();
        boolean[] visited = new boolean[total];
        visited[0] = true;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < rooms.size(); i++) {
            queue.add(rooms.get(0).get(i));
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (!visited[current]) {
                visited[current] = true;
                for (int i = 0; i < rooms.get(current).size(); i++) {
                    queue.add(rooms.get(current).get(i));
                }
            }
        }

        for (int i = 0; i < total; i++) {
            if (!visited[i])
                return false;
        }

        return true;
    }

    // Is Graph Bipartite?
    // https://leetcode.com/problems/is-graph-bipartite

    boolean isBipartite(int[][] graph) {
        int[] colors = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (colors[i] == 0 && !dfsBiPartite(i, graph, colors, true))
                return false;
        }
        return true;
    }

    boolean dfsBiPartite(int s, int[][] graph, int[] colors, boolean red) {
        if (red)
            colors[s] = 1;
        else
            colors[s] = 2;
        for (int v : graph[s]) {
            if (colors[v] == (red ? 1 : 2) || colors[v] == 0 && !dfsBiPartite(v, graph, colors, !red))
                return false;
        }
        return true;
    }

    //  Find Eventual Safe States
    // https://leetcode.com/problems/find-eventual-safe-states

    List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> result = new ArrayList<>();
        if (graph == null || graph.length == 0)
            return result;
        int[] colors = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (dfsEventual(graph, colors, i))
                result.add(i);
        }
        return result;

    }

    boolean dfsEventual(int[][] graph, int[] colors, int current) {
        if (colors[current] == 2)
            return true;
        if (colors[current] == 1)
            return false;

        colors[current] = 1;
        for (int neighbor : graph[current]) {
            if (!dfsEventual(graph, colors, neighbor))
                return false;
        }
        colors[current] = 2;
        return true;
    }

    //  Number of Increasing Paths in a Grid
    // https://leetcode.com/problems/number-of-increasing-paths-in-a-grid
    int countPaths(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[][] dp = new int[n][m];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        int max = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                max += dfsCountPath(grid, dp, i, j);
                max %= mod;
            }
        }

        return max;
    }

    int[][] dir = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    int mod = 1000000007;

    int dfsCountPath(int[][] grid, int[][] dp, int r, int c) {
        int count = 1;
        if (dp[r][c] != -1)
            return dp[r][c];
        for (int[] d : dir) {
            int x = d[0] + r, y = d[1] + c;
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] > grid[r][c])
                count += dfsCountPath(grid, dp, r, c);
        }
        return dp[r][c] = count % mod;
    }

    //Flood Fill
    // https://leetcode.com/problems/flood-fill
    int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if (image[sr][sc] == color)
            return image;
        dfsFill(image, sr, sc, color, image[sr][sc]);
        return image;
    }

    void dfsFill(int[][] image, int sr, int sc, int color, int current) {
        if (sc < 0 || sc >= image[0].length || sr < 0 || sr >= image.length)
            return;
        if (image[sr][sc] != current)
            return;
        image[sr][sc] = color;
        for (int[] d : dir) {
            dfsFill(image, sr + d[0], sc + d[1], color, current);
        }
    }

    // Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
    // https://leetcode.com/problems/01-matrix
    int[][] updateMatrix(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0)
            return new int[0][0];
        int n = mat.length, m = mat[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int maxValue = n * m;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0)
                    queue.add(new int[]{i, j});
                else
                    mat[i][j] = maxValue;
            }
        }

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] d : directions) {
                int r = d[0] + cell[0], c = d[1] + cell[1];
                if (r >= 0 && r < n && c >= 0 && c < m && mat[r][c] > mat[cell[0]][cell[1]] + 1) {
                    queue.offer(new int[]{r, c});
                    mat[r][c] = mat[cell[0]][cell[1]] + 1;
                }
            }
        }

        return mat;

    }

    // https://leetcode.com/problems/number-of-provinces
    //Number of Provinces

    int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfsConnected(isConnected, visited, i);
                count++;
            }
        }
        return count;
    }

    void dfsConnected(int[][] connected, boolean[] visited, int start) {
        visited[start] = true;
        for (int i = 0; i < connected.length; i++) {
            if (connected[start][1] == 1 && !visited[i]) {
                dfsConnected(connected, visited, i);
            }
        }
    }

    int findCircleNumByBfs(int[][] grid) {
        boolean[] visited = new boolean[grid.length];
        int c = 0;
        for (int i = 0; i < grid.length; i++) {
            if (!visited[i]) {
                bfs(i, grid, visited);
                c++;
            }
        }
        return c;
    }

    void bfs(int s, int[][] grid, boolean[] visited) {
        visited[s] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        while (!queue.isEmpty()) {
            int j = queue.poll();
            for (int i = 0; i < grid[j].length; i++) {
                if (!visited[i] && grid[j][i] == 1) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }

}

class Pair implements Comparable<Pair> {
    int node;
    int dist;

    Pair(int node, int dist) {
        this.node = node;
        this.dist = dist;
    }

    @Override
    public int compareTo(Pair o) {
        return Integer.compare(this.dist, o.dist);
    }
}
