package grokkingpattern.google.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class SSCProblems {

    public static void main(String[] args) {
        SSCProblems sl = new SSCProblems();
        int V = 5;
        List<List<Integer>> edges = new ArrayList<>();
        edges.add(new ArrayList<>(Arrays.asList(new Integer[]{1, 3})));
        edges.add(new ArrayList<>(Arrays.asList(new Integer[]{1, 4})));
        edges.add(new ArrayList<>(Arrays.asList(new Integer[]{2, 1})));
        edges.add(new ArrayList<>(Arrays.asList(new Integer[]{3, 2})));
        edges.add(new ArrayList<>(Arrays.asList(new Integer[]{4, 5})));

        List<List<Integer>> ans = sl.findSSCPath(edges, V);
        System.out.println("Strongly Connected Components are:");
        for (List<Integer> x : ans) {
            System.out.println(x);
        }
    }


    // https://www.geeksforgeeks.org/strongly-connected-components/
    boolean dfsScc(List<List<Integer>> adjList, boolean[] visited, int src, int dest) {
        if (src == dest)
            return true;
        visited[src] = true;
        for (int v : adjList.get(src)) {
            if (!visited[v])
                if (dfsScc(adjList, visited, v, dest))
                    return true;
        }
        return false;
    }

    //  Time complexity: O(N3) and Auxiliary Space: O(N)
    // Function to return all the strongly connected component of a graph.
    List<List<Integer>> findSSCPath(List<List<Integer>> graph, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> isSSCList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            isSSCList.add(0);
        }
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }
        for (List<Integer> edge : graph) {
            adjList.get(edge.get(0)).add(edge.get(1));
        }

        boolean[] visited = new boolean[n+1];
        for (int i = 1; i <= n; i++) {
            // If a vertex i is not a part of any SCC insert it into a new SCC list and check
            // for other vertices whether they can be the part of this list.
            if (isSSCList.get(i) == 0) {
                List<Integer> scc = new ArrayList<>();
                scc.add(i);
                for (int j = i + 1; j <= n; j++) {
                    if (isSSCList.get(j) == 0 && dfsScc(adjList, visited, i, j) && dfsScc(adjList, visited, j, i)) {
                        isSSCList.add(j, 1);
                        scc.add(j);
                    }
                }
                ans.add(scc);
            }
        }

        return ans;
    }

    // Java program to find strongly connected components in a given directed graph  using Tarjan's algorithm (single DFS)

    void sscComponents(int V) {
        //Initialize parent and visited,  and ap(articulation point) arrays
        int[] disc= new int[V];
        int[] low= new int[V];
        Arrays.fill(disc, -1);
        Arrays.fill(low, -1);
        boolean[] visited = new boolean[V];
        Stack<Integer> stack= new Stack<>();
        // recursive helper function
        for(int i=0; i<V; i++) {
            if(disc[i]==-1) {
                SSCUtil(i, low, disc, visited, stack);
            }
        }
    }

    void SSCUtil(int u, int[] low, int[] disc, boolean[] visited, Stack<Integer> st) {
        // initialize discovery time and low value;
        low[u] = 0;
        disc[u]= 0;

    }



    // Given an undirected graph, the task is to print all the connected components line by line.
    //https://www.geeksforgeeks.org/connected-components-in-an-undirected-graph/
}
