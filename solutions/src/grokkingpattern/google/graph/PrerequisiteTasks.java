package grokkingpattern.google.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class PrerequisiteTasks {
    // https://www.geeksforgeeks.org/find-whether-it-is-possible-to-finish-all-tasks-or-not-from-given-dependencies/
    // There are a total of n tasks you have to pick, labelled from 0 to n-1. Some tasks may have prerequisites,
    // for example to pick task 0 you have to first pick task 1, which is expressed as a pair: [0, 1]
    //Given the total number of tasks and a list of prerequisite pairs, is it possible for you to finish all tasks?

    public static void main(String[] args) {
        PrerequisiteTasks pl = new PrerequisiteTasks();
        System.out.println(pl.bfs(new int[][]{{1, 0}, {0, 1}}, 2));
        System.out.println(pl.bfs(new int[][]{{1, 0}}, 2));
        System.out.println(pl.canFinish(new int[][]{{1, 0}, {0, 1}}, 2));
        System.out.println(pl.canFinish(new int[][]{{1, 0}}, 2));
        System.out.println(pl.canFinish(new int[][]{{1, 0}, {2, 1}, {3, 2}}, 4));
        System.out.println(pl.bfs(new int[][]{{1, 0}, {2, 1}, {3, 2}}, 4));
    }

    boolean canFinish(int[][] tasks, int numCourses) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int[] task : tasks) {
            adjList.get(task[1]).add(task[0]);
        }
        boolean[] visited = new boolean[numCourses];
        boolean[] onpath = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i] && dfs(adjList, visited, onpath, i)) {
                return false;
            }
        }
        return true;
    }

    // Time O(V*(V+E)) and Space: O(V+E)
    boolean dfs(List<List<Integer>> graph, boolean[] visited, boolean[] onpath, int current) {
        // base cases...
        if (visited[current])
            return false;
        onpath[current] = visited[current] = true; // visiting
        for (int nbr : graph.get(current)) {
            if (onpath[nbr] || dfs(graph, visited, onpath, nbr))
                return true;
        }
        return onpath[current] = false; // back track
    }

    // Time Complexity: O(V+E) and  Space: O(V+E)
    boolean bfs(int[][] tasks, int numCourses) {
        List<List<Integer>> adjList = new ArrayList<>();
        int[] indegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int[] task : tasks) {
            adjList.get(task[1]).add(task[0]);
            indegree[task[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        int courseEnrolled = 0;
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.add(indegree[i]);
                courseEnrolled++;
            }
        }

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            for (Integer v : adjList.get(vertex)) {
                indegree[v]--;
                if (indegree[v] == 0) {
                    queue.add(v);
                    courseEnrolled++;
                }
            }

        }

        return numCourses == courseEnrolled;
    }

    int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : prerequisites) {
            indegree[edge[0]]++;
            graph.computeIfAbsent(edge[1], value -> new ArrayList<>()).add(edge[0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0)
                queue.add(i);
        }

        int i = 0;
        int[] result = new int[numCourses];
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            result[i++] = vertex;
            List<Integer> list = graph.get(vertex);
            if (list == null)
                continue;
            for (int child : list) {
                indegree[child]--; // reduce
                if (indegree[child] == 0) {
                    queue.add(child);
                }
            }
        }
        if (i == numCourses)
            return result;

        return new int[0];
    }
}
