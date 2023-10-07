package grokkingpattern.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UnionFindSolution {

    public static void main(String[] args) {
        UnionFindSolution ufs = new UnionFindSolution();
        System.out.println(ufs.possibleBipartition(4, new int[][]{{1, 2}, {3, 4}, {1, 3}, {1, 4}}));
        System.out.println(ufs.findCircleNum(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));
        System.out.println(ufs.findCircleNum(new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}));
    }

    // https://leetcode.com/problems/possible-bipartition
    // dislikes people
    class UnionFind {
        int[] parent;
        int[] rank;

        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int sx = find(x), sy = find(y);
            if (sx == sy) {
                return;
            }
            if (rank[sx] > rank[sy]) {
                parent[sy] = sx;
            } else if (rank[sx] < rank[sy]) {
                parent[sx] = sy;
            } else {
                parent[sy] = sx;
                rank[sx]++;
            }
        }
    }

    boolean possibleBipartition(int n, int[][] dislikes) {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] p : dislikes) {
            graph.computeIfAbsent(p[0], value -> new ArrayList<Integer>()).add(p[1]);
            graph.computeIfAbsent(p[1], value -> new ArrayList<Integer>()).add(p[0]);
        }
        UnionFind uf = new UnionFind(n + 1);
        for (int i = 1; i <= n; i++) {
            if (!graph.containsKey(i))
                continue;
            for (int neighbour : graph.get(i)) {
                if (uf.find(i) == uf.find(neighbour)) return false;
                uf.union(graph.get(i).get(0), neighbour);
            }
        }
        return true;
    }

    // https://leetcode.com/problems/number-of-provinces
    int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        UnionFind uf = new UnionFind(n);
        int groups = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (isConnected[i][j] == 1) {
                    uf.union(i, j);
                    groups++;
                }
            }
        }
        return n - groups;
    }
}

