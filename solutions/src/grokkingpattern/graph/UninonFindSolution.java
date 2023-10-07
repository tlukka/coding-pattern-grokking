package grokkingpattern.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UninonFindSolution {

    public static void main(String[] args) {
        UninonFindSolution ufs = new UninonFindSolution();
        System.out.println(ufs.possibleBipartition(4, new int[][]{{1, 2}, {1, 3}, {1, 4}, {3,4}}));
    }

    // https://leetcode.com/problems/possible-bipartition
    // dislikes people
    class UninonFind {
        int[] parent;
        int[] rank;

        UninonFind(int size) {
            this.parent = new int[size];
            this.rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }

            return parent[x];
        }

        void union(int x, int y) {
            int root1 = find(x);
            int root2 = find(y);
            if (root1 == root2)
                return;
            if (rank[root1] > rank[root2]) {
                rank[root2] = root1;
            } else if (rank[root1] < rank[root2]) {
                parent[root1] = root2;
            } else {
                parent[root2] = root1;
                rank[root1]++;
            }
        }
    }

    boolean possibleBipartition(int n, int[][] dislikes) {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] disLike : dislikes) {
            graph.computeIfAbsent(disLike[0], value -> new ArrayList<>()).add(disLike[1]);
            graph.computeIfAbsent(disLike[1], value -> new ArrayList<>()).add(disLike[0]);
        }

        UninonFind uf = new UninonFind(n + 1);
        for (int i = 1; i <= n; i++) {
            if (!graph.containsKey(i))
                continue;
            for (int negihbor : graph.get(i)) {
                if (uf.find(i) == uf.find(negihbor))
                    return false;
                uf.union(graph.get(i).get(0), negihbor);
            }
        }
        return true;
    }
}

