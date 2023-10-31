package grokkingpattern.google.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstrasShortestPath {

    static class Graph {
        private int V;
        private List<List<Integer[]>> adjList;

        Graph(int V) {
            this.V = V;
            this.adjList = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                adjList.add(new ArrayList<>());
            }
        }

        void addEdge(int u, int v, int w) {
            adjList.get(u).add(new Integer[]{v, w});
            adjList.get(v).add(new Integer[]{u, w});
        }

        int[] shortestPath(int src) {
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
            pq.add(new int[]{src, 0});
            int[] dist = new int[V];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[src]=0; // setting distance zero for source...
            while (!pq.isEmpty()) {
                int[] top = pq.poll();
                int u = top[0], w = top[1];
                for (Integer[] v : adjList.get(u)) {
                    if (dist[v[0]] > dist[u] + v[1]) {
                        dist[v[0]] = dist[u] + v[1];
                        pq.add(new int[]{v[0], dist[v[0]]});
                    }
                }
            }

            return dist;
        }
    }

    // https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/
    // I/P: { { 0, 4, 0, 0, 0, 0, 0, 8, 0 }, { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
    // { 0, 8, 0, 7, 0, 4, 0, 0, 2 }, { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
    //{ 0, 0, 0, 9, 0, 10, 0, 0, 0 }, { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
    //{ 0, 0, 0, 0, 0, 2, 0, 1, 6 }, { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
    //{ 0, 0, 2, 0, 0, 0, 6, 7, 0 } }
    //O/p:
    // Vertex          Distance from Source
    //0                  0
    //1                  4
    //2                  12
    //3                  19
    //4                  21
    //5                  11
    //6                  9
    //7                  8
    //8                  14


    public static void main(String[] args) {
        int graph[][]
                = new int[][]{{0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}};
        DijkstrasShortestPath t = new DijkstrasShortestPath();

        // Function call
        System.out.println(Arrays.toString(t.dijkstra(graph, 0, 9)));


        Graph g = new Graph(9);

        g.addEdge(0, 1, 4);
        g.addEdge(0, 7, 8);
        g.addEdge(1, 2, 8);
        g.addEdge(1, 7, 11);
        g.addEdge(2, 3, 7);
        g.addEdge(2, 8, 2);
        g.addEdge(2, 5, 4);
        g.addEdge(3, 4, 9);
        g.addEdge(3, 5, 14);
        g.addEdge(4, 5, 10);
        g.addEdge(5, 6, 2);
        g.addEdge(6, 7, 1);
        g.addEdge(6, 8, 6);
        g.addEdge(7, 8, 7);

        System.out.println(Arrays.toString(g.shortestPath(0)));
    }

    // Time Complexity is O(V2) with Adjacency Matrix
    int[] dijkstra(int[][] graph, int src, int V) {
        int[] dist = new int[V]; // result
        boolean[] visited = new boolean[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        for (int i = 0; i < V - 1; i++) {
            int u = minDistance(visited, dist, V);
            visited[u] = true;
            for (int v = 0; v < V; v++) {
                if (!visited[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        return dist;

    }

    int minDistance(boolean[] visited, int[] dist, int V) {
        int minIndex = -1, min = Integer.MAX_VALUE;
        for (int i = 0; i < V; i++) {
            if (!visited[i] && dist[i] < min) {
                min = dist[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

    // Time Complexity is O(ELogV) with Adjacency List with Heap

}
