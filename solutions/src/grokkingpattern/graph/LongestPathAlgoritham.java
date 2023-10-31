package grokkingpattern.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class LongestPathAlgoritham {
    public static void main(String args[]) {
        Graph g = new Graph(6);
        g.addEdge(0, 1, 5);
        g.addEdge(0, 2, 3);
        g.addEdge(1, 3, 6);
        g.addEdge(1, 2, 2);
        g.addEdge(2, 4, 4);
        g.addEdge(2, 5, 2);
        g.addEdge(2, 3, 7);
        g.addEdge(3, 5, 1);
        g.addEdge(3, 4, -1);
        g.addEdge(4, 5, -2);
        int s = 1;
        System.out.print("Following are longest distances from source vertex " + s + " \n");
        g.longestPath(s);

    }
}

class AdjNode {
    int v, weight;

    public AdjNode(int v, int weight) {
        this.v = v;
        this.weight = weight;
    }

    int getV() {
        return v;
    }

    int getWeight() {
        return weight;
    }
}

class Graph {
    int v;
    ArrayList<ArrayList<AdjNode>> adj;

    public Graph(int v) {
        this.v = v;
        adj = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }
    }

    void addEdge(int u, int v, int weight) {
        AdjNode node = new AdjNode(v, weight);
        adj.get(u).add(node);
    }

    void topologicalSortUtil(int v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;
        //all the vertices adjacent to this vertex
        for (int i = 0; i < adj.get(v).size(); i++) {
            AdjNode ad = adj.get(v).get(i);
            if (!visited[ad.getV()]) {
                topologicalSortUtil(ad.getV(), visited, stack);
            }
        }

        stack.push(v);
    }

    void longestPath(int src) {
        Stack<Integer> stack = new Stack<Integer>();
        int[] dist = new int[v];
        Arrays.fill(dist, Integer.MIN_VALUE);
        boolean[] visited = new boolean[v];
        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }
        }

        dist[src] = 0;

        while (!stack.isEmpty()) {
            int u = stack.pop();
            if (dist[u] != Integer.MIN_VALUE) {
                for (int i = 0; i < adj.get(u).size(); i++) {
                    AdjNode adjNode = adj.get(u).get(i);
                    if (dist[adjNode.getV()] < dist[u] + adjNode.getWeight()) {
                        dist[adjNode.getV()] = dist[u] + adjNode.getWeight();
                    }
                }
            }
        }

        for (int i = 0; i < v; i++) {
            if (dist[i] == Integer.MIN_VALUE) {
                System.out.print("INF ");
            } else
                System.out.print(dist[i] + " ");
        }

    }
}
