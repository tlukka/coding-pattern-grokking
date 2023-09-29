package blindpatternsolutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class GraphSolutions {
    public static void main(String[] args) {
        GraphSolutions s1 = new GraphSolutions();
        /*System.out.println(s1.canFinish(2, new int[][]{{1, 0}}));
        System.out.println(s1.canFinish(2, new int[][]{{1, 0}, {0, 1}}));
        List<List<Integer>> waterGrid = s1.pacificAtlantic(new int[][]{{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}});
        for (List<Integer> wg : waterGrid) {
            System.out.println(Arrays.toString(wg.stream().toArray()));
        }

        System.out.println(s1.numIslands(new char[][]{{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}}));
        System.out.println(s1.numIslands(new char[][]{{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}}));

        System.out.println(s1.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2, 4})); */
        //System.out.println(s1.alienOrder(new String[]{"wrt", "wrf", "er", "ett", "rftt"}));
        System.out.println(s1.validGraphTree(5, new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 4}}));
        System.out.println(s1.validGraphTree(5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {2, 4}, {3, 4}}));
    }

    // Graph Valid Tree
    // graph of n nodes labels from 0 to  n-1, given a list of edges where edges[i]=[ai,bi] indicates undirected edge
    // Find is it valid graph tree i.e. No self loops or repeated edges
    boolean validGraphTree(int n, int[][] edges) {
        int[] rank = new int[n];
        int[] root = new int[n];
        for (int i = 0; i < n; i++) {
            root[i] = i;
            rank[i] = 0;
        }

        int count = n; // connected components
        for (int[] edge : edges) {
            int A = edge[0];
            int B = edge[1];
            int rootA = find(root, A);
            int rootB = find(root, B);
            if (rootA == rootB) // Cycle in graph
                return false;

            // union
            if (rank[rootA] >= rank[rootB]) {
                root[rootB] = rootA;
                rank[rootA]++;
            } else {
                root[rootA] = rootB;
                rank[rootB]++;
            }
            count--;
        }
        // If we need to return number of connected components just return count.
        return (count == 1);
    }

    int find(int[] root, int x) {
        if (root[x] != x) {
            root[x] = find(root, root[x]);
        }
        return root[x];
    }

    // Alien Dictionary new alien language
    // Return a string of unique letters is new alien language sorted in lexicographically increasing order
    // I/p: ["wrt","wrf","er","ett","rftt"]

    String alienOrder(String[] words) {
        if (words == null || words.length == 0)
            return " ";
        Map<Character, List<Character>> adjMap = new HashMap<>();
        int[] indegree = new int[26];
        for (int i = 1; i < words.length; i++) {
            String prevWord = words[i - 1];
            String currWord = words[i];
            // case
            if (prevWord.length() == currWord.length() + 1 && prevWord.substring(0, currWord.length()).equals(currWord))
                return " ";
            for (int k = 0; k < Math.min(prevWord.length(), currWord.length()); k++) {
                char c1 = prevWord.charAt(k);
                char c2 = currWord.charAt(k);
                if (c1 != c2) {
                    if (!adjMap.containsKey(c1)) {
                        adjMap.put(c1, new ArrayList<>());
                    }
                    adjMap.get(c1).add(c2);
                    indegree[c2 - 'a']++;
                    break;
                }
            }
        }


        Set<Character> uniqueChars = new HashSet<>();
        for (String word : words) {
            for (char c : word.toCharArray())
                uniqueChars.add(c);
        }

        PriorityQueue<Character> minHeap = new PriorityQueue<>();
        for (char c : uniqueChars) {
            if (indegree[c - 'a'] == 0)
                minHeap.add(c);
        }
        // Main logic
        String topSort = "";
        while (!minHeap.isEmpty()) {
            char currChar = minHeap.poll();
            topSort += currChar;
            if (adjMap.get(currChar) == null)
                continue;
            for (char neighbor : adjMap.get(currChar)) {
                indegree[neighbor - 'a']--;
                if (indegree[neighbor - 'a'] == 0)
                    minHeap.add(neighbor);
            }

        }

        return topSort.length() == uniqueChars.size() ? topSort : " ";
    }


    //  Longest Consecutive Sequence
    int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++)
            set.add(nums[i]);

        int longestSequence = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                // No element with start sequence
                int currentSeqence = 1;
                int currNum = num;
                while (set.contains(currNum + 1)) {
                    // next numbers exist
                    currentSeqence++;
                    currNum++;
                }
                longestSequence = Math.max(longestSequence, currentSeqence);
            }
        }

        return longestSequence;
    }

    //Number of Islands
    int numIslands(char[][] grid) {
        int count = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == '1') {
                    count += 1;
                    dfsIsland(grid, r, c);
                }
            }
        }

        return count;

    }

    void dfsIsland(char[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == '0')
            return;
        grid[r][c] = '0';
        dfsIsland(grid, r - 1, c);  // down
        dfsIsland(grid, r + 1, c);  // up
        dfsIsland(grid, r, c + 1);  // right
        dfsIsland(grid, r, c - 1);  // left
    }

    // Pacific Atlantic Water Flow
    List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        int rows = heights.length;
        int cols = heights[0].length;
        boolean[][] pacific = new boolean[rows][cols];
        boolean[][] atlantic = new boolean[rows][cols];

        // first row, last row, first col and last col
        for (int c = 0; c < cols; c++) {
            dfsWaterFlow(heights, pacific, 0, c, Integer.MIN_VALUE);
            dfsWaterFlow(heights, atlantic, rows - 1, c, Integer.MIN_VALUE);
        }
        for (int r = 0; r < rows; r++) {
            dfsWaterFlow(heights, pacific, r, 0, Integer.MIN_VALUE);
            dfsWaterFlow(heights, atlantic, r, cols - 1, Integer.MIN_VALUE);
        }

        // Logic
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (pacific[r][c] && atlantic[r][c]) {
                    result.add(Arrays.asList(r, c));
                }
            }
        }
        return result;
    }

    void dfsWaterFlow(int[][] matrix, boolean[][] oceans, int r, int c, int curr) {
        if (r < 0 || r >= matrix.length || c < 0 || c >= matrix[0].length)
            return;
        if (matrix[r][c] < curr || oceans[r][c])
            return;
        oceans[r][c] = true;

        dfsWaterFlow(matrix, oceans, r - 1, c, matrix[r][c]); // down
        dfsWaterFlow(matrix, oceans, r + 1, c, matrix[r][c]); // up
        dfsWaterFlow(matrix, oceans, r, c - 1, matrix[r][c]); // left
        dfsWaterFlow(matrix, oceans, r, c + 1, matrix[r][c]); // right
    }

    // Can user finish all courses??
    boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null) return false;
        if (prerequisites.length == 0) return true;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        // filling data.
        for (int[] edge : prerequisites) {
            adj.get(edge[1]).add(edge[0]);
        }

        int[] inDegree = new int[numCourses];
        for (int[] edge : prerequisites) {
            inDegree[edge[0]]++;
        }

        int coursesEnrolled = 0;
        Queue<Integer> q = new LinkedList<>();
        for (int node = 0; node < inDegree.length; node++) {
            if (inDegree[node] == 0) {
                q.add(node);
                coursesEnrolled++;
            }
        }

        while (!q.isEmpty()) {
            int curNode = q.poll();
            for (int neighbors : adj.get(curNode)) {
                inDegree[neighbors]--;
                if (inDegree[neighbors] == 0) {
                    q.add(neighbors);
                    coursesEnrolled++;
                }
            }
        }

        return coursesEnrolled == numCourses;
    }
}
