package grokkingpattern.combine.topologicalsort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Problems {

    public static void main(String[] args) {
        int[][] edges = {{3, 2}, {3, 0}, {2, 0}, {2, 1}};
        List<Integer> sortData = sort(4, edges);
        sortData.stream().forEach((a) -> System.out.print(a + ","));
   /* int[] [] prerequisites = {{0,1}, {1,2}};
    System.out.println(isSchedulingPossible(prerequisites,3));
    int[] [] prerequisites1 = {{0,1}, {1,2}, {2,0}};
    System.out.println(isSchedulingPossible(prerequisites1,3));
    */
        int[][] prerequisites = {{1, 0}};
        System.out.println(findOrder(2, prerequisites));

        int[][] prerequisites1 = {{1, 0}, {1, 2}};
        System.out.println(findOrder(3, prerequisites1));
        int[][] prerequisites2 = {{2, 5}, {0, 5}, {0, 4}, {1, 4}, {3, 2}, {1, 3}};
        System.out.println(findOrder(6, prerequisites2));
    }

    //
    //Topological Sort
    public static List<Integer> findOrder(int vertices, int[][] prerequisites) {
        //edge case
        if (prerequisites == null || vertices <= 0) return new ArrayList<>();

        List<Integer> result = new ArrayList<>();

        // Initialization Graph
        HashMap<Integer, Integer> inDegree = new HashMap<>(); //count of incoming edges for every vertex
        HashMap<Integer, List<Integer>> graph = new HashMap<>(); // adjacency list graph
        for (int i = 0; i < vertices; i++) {
            inDegree.put(i, 0);
            graph.put(i, new ArrayList<>());
        }

        // Build graph
        for (int i = 0; i < prerequisites.length; i++) {
            int parent = prerequisites[i][0];
            int child = prerequisites[i][1];
            // put the child into it's parent's list
            graph.get(child).add(parent);
            // increment child's inDegree
            inDegree.put(parent, inDegree.get(parent) + 1);
        }

        //logic all vertices with 0 in-degrees
        Queue<Integer> sources = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0)
                sources.offer(entry.getKey());
        }

        // For each source, add it to the result and subtract one from all of its children's in-degrees
        // if a child's in-degree becomes zero, add it to the sources queue
        while (!sources.isEmpty()) {
            int vertex = sources.poll();
            result.add(vertex);
            // get the node's children to decrement their in-degree
            List<Integer> children = graph.get(vertex);
            for (int child : children) {
                inDegree.put(child, inDegree.get(child) - 1);
                if (inDegree.get(child) == 0)
                    sources.add(child);
            }
        }
        if (result.size() != vertices) return new ArrayList<>();
        return result;
    }

    //Tasks Scheduling
    public static boolean isSchedulingPossible(int[][] prerequisites, int tasks) {
        if (prerequisites == null || tasks <= 0) return true;

        //Initilization Graph
        HashMap<Integer, Integer> inDegreeMap = new HashMap<>();
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < tasks; i++) {
            inDegreeMap.put(i, 0);
            graph.put(i, new ArrayList<>());
        }

        // Build graph
        for (int i = 0; i < prerequisites.length; i++) {
            int start = prerequisites[i][0], end = prerequisites[i][1];
            graph.get(start).add(end);
            inDegreeMap.put(end, inDegreeMap.get(end) + 1);
        }
        // Sources
        Queue<Integer> sources = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : inDegreeMap.entrySet()) {
            if (entry.getValue() == 0) {
                sources.add(entry.getKey());
            }
        }

        List<Integer> result = new ArrayList<>();
        // logic
        while (!sources.isEmpty()) {
            int vertex = sources.poll();
            result.add(vertex);
            // get the node's children to decrement their in-degree
            for (int child : graph.get(vertex)) {
                inDegreeMap.put(child, inDegreeMap.get(child) - 1);
                if (inDegreeMap.get(child) == 0)
                    sources.add(child);
            }
        }
        result.toArray(new Integer[tasks]);
        // if schedule doesn't contain all tasks there is a cyclic dependency between tasks,
        // therefore, we will not be able to schedule all tasks
        return result.size() == tasks;

    }

    //Topological Sort
    public static List<Integer> sort(int vertices, int[][] edges) {
        //edge case
        if (edges == null || vertices <= 0) return new ArrayList<>();

        List<Integer> result = new ArrayList<>();

        // Initialization Graph
        HashMap<Integer, Integer> inDegree = new HashMap<>(); //count of incoming edges for every vertex
        HashMap<Integer, List<Integer>> graph = new HashMap<>(); // adjacency list graph
        for (int i = 0; i < vertices; i++) {
            inDegree.put(i, 0);
            graph.put(i, new ArrayList<>());
        }

        // Build graph
        for (int i = 0; i < edges.length; i++) {
            int parent = edges[i][0], child = edges[i][1];
            // put the child into it's parent's list
            graph.get(parent).add(child);
            // increment child's inDegree
            inDegree.put(child, inDegree.get(child) + 1);
        }

        //logic all vertices with 0 in-degrees
        Queue<Integer> sources = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0)
                sources.offer(entry.getKey());
        }

        // For each source, add it to the result and
        // subtract one from all of its children's in-degrees
        // if a child's in-degree becomes zero, add it to the sources queue
        while (!sources.isEmpty()) {
            int vertex = sources.poll();
            result.add(vertex);
            // get the node's children to decrement their in-degree
            List<Integer> children = graph.get(vertex);
            for (int child : children) {
                inDegree.put(child, inDegree.get(child) - 1);
                if (inDegree.get(child) == 0)
                    sources.add(child);
            }
        }
        if (result.size() != vertices) return new ArrayList<>();
        return result;
    }

    // You are given a directed graph consisting of N vertices, numbered from 1 to N, and N edges.
    //The graph is described by two arrays, A and B, both of length N. A pair A[K], B[K] (for K from 0 to N-1)
    // describes a directed edge from vertex A[K] to vertex B[K]. Note that the graph might contain self-loops
    // (edges where A[K] = B[K]) and/or multiple edges between the same pair of vertices.
    //Your task is to check whether the given graph is a cycle. A graph is a cycle if it is possible to start at some
    // vertex and, by following the provided edges, visit all the other vertices and return to the starting point.
    //For example, A = [1,3, 2, 4] and B = [4,1, 3,2] is a cycle of length 4.
    // On the other hand, A = [1,2, 3, 4] and B = [2,1,4, 3] is not a cycle.
    //https://leetcode.com/discuss/interview-question/3333939/Question%3A-Graph-Cycle-Problem
    boolean graphCycleChcek(int[] A, int[] B) {
        int leng = A.length;
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < leng; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < leng; i++) {
            adjList.get(A[i] - 1).add(B[i] - 1);
        }
        boolean[] visited = new boolean[leng];
        boolean[] recStack = new boolean[leng];
        for (int i = 0; i < leng; i++) {
            if (!visited[i] && hasCycle(i, adjList, visited, recStack))
                return true;
        }
        return false;
    }

    boolean hasCycle(int vertex, List<List<Integer>> adjList, boolean[] visited, boolean[] recStack) {
        if (recStack[vertex])
            return true;
        if (visited[vertex])
            return false;
        visited[vertex] = true;
        recStack[vertex] = true;
        for (Integer nbr : adjList.get(vertex)) {
            if (!visited[nbr] && hasCycle(nbr, adjList, visited, recStack))
                return true;
            else if (recStack[nbr])
                return true;
        }
        recStack[vertex] = false;
        return false;

    }


}
