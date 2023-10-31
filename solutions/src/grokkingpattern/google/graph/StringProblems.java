package grokkingpattern.google.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class StringProblems {

    public static void main(String[] args) {
        StringProblems sp = new StringProblems();
        Set<String> dic = new HashSet<String>();
        dic.add("poon");
        dic.add("plee");
        dic.add("same");
        dic.add("poie");
        dic.add("plie");
        dic.add("poin");
        dic.add("plea");
        String start = "toon";
        String target = "plea";
        System.out.println("Length of shortest chain is: " + sp.shortestChainLen(start, target, dic));

        int N = 30;
        int[] knightPos = {1, 1};
        int[] targetPos = {30, 30};

        // Function call
        System.out.println(sp.minStepToReachTarget(knightPos, targetPos, N));

        System.out.println(sp.assignmentProblem(new int[]{3, 5, 10, 1}, 2));
        System.out.println(sp.assignmentProblem(new int[]{2, 1, 2, 9, 8, 1, 1, 1, 1}, 2));
    }
    // Given two integers N and K. The task is to find the string S of minimum length such that it contains all
    // possible strings of size N as a substring
    // https://practice.geeksforgeeks.org/problems/find-the-string

    String findString(int n, int k) {
        return "a";
    }

    // https://www.geeksforgeeks.org/word-ladder-length-of-shortest-chain-to-reach-a-target-word/
    // Given a dictionary, and two words ‘start’ and ‘target’ (both of same length). Find length of the smallest chain
    // from ‘start’ to ‘target’ if it exists, such that adjacent words in the chain only differ by one character
    // and each word in the chain is a valid word i.e., it exists in the dictionary. It may be assumed that the
    // ‘target’ word exists in dictionary and length of all dictionary words is same.

    // This is BFS solution find the shortest path through BFS, start from the start word and push it in a queue.
    // Time Complexity: O(N² * M) and Auxiliary Space: O(M * N)
    int shortestChainLen(String start, String target, Set<String> dict) {
        if (start == target)
            return 0;
        if (!dict.contains(target))
            return 0;
        Queue<String> q = new LinkedList<>();
        q.add(start);
        int level = 0;
        while (!q.isEmpty()) {
            level++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                char[] wordInQ = q.peek().toCharArray();
                q.remove();
                for (int idx = 0; idx < wordInQ.length; idx++) {
                    char old_char = wordInQ[idx];
                    //Replace the current character with every possible lowercase alphabet
                    for (char c = 'a'; c <= 'z'; c++) {
                        wordInQ[idx] = c;
                        if (String.valueOf(wordInQ).equals(target))
                            return level + 1;
                        if (!dict.contains(String.valueOf(wordInQ)))
                            continue;
                        dict.remove(String.valueOf(wordInQ));
                        q.add(String.valueOf(wordInQ));
                    }
                    wordInQ[idx] = old_char;// replace with old char
                }
            }
        }
        return 0;
    }

    // Minimum steps to reach target by a Knight
    // https://www.geeksforgeeks.org/minimum-steps-reach-target-knight/
    // Given a square chessboard of N x N size, the position of the Knight and the position of a target are given.
    // We need to find out the minimum steps a Knight will take to reach the target position.

    // Time complexity: O(N2). and Auxiliary Space: O(N2).
    int minStepToReachTarget(int[] knightPos, int[] targetPos, int N) {
        int[] dx = {-2, -1, 1, 2, -2, -1, 1, 2};
        int[] dy = {-1, -2, -2, -1, 1, 2, 2, 1};
        Queue<cell> q = new LinkedList<>();
        q.add(new cell(knightPos[0], knightPos[1], 0));
        boolean[][] visited = new boolean[N + 1][N + 1];
        visited[knightPos[0]][knightPos[1]] = true;
        while (!q.isEmpty()) {
            cell temp = q.poll();
            // current cell is equal to target than return distance
            if (temp.x == targetPos[0] && temp.y == targetPos[1])
                return temp.dis;
            for (int i = 0; i < 8; i++) {
                int x = dx[i] + temp.x;
                int y = dy[i] + temp.y;
                if (isInside(x, y, N) && !visited[x][y]) {
                    visited[x][y] = true;
                    q.add(new cell(x, y, temp.dis + 1));
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    // returns true if (x, y) lies inside Board
    boolean isInside(int x, int y, int N) {
        if (x >= 1 && x <= N && y >= 1 && y <= N)
            return true;
        return false;
    }

    // Class for storing a cell's data
    static class cell {
        int x, y;
        int dis;

        public cell(int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }
    }


    // You are the head of a firm and you have to assign jobs to people. You have N persons working under you and
    // you have N jobs that are to be done by these persons. Each person has to do exactly one job and each job
    // has to be done by exactly one person. Each person has his own capability (in terms of time taken) to do any
    // particular job. Your task is to assign the jobs to the persons in such a way that the total time taken is minimum.
    // A job can be assigned to only one person and a person can do only one job.
    // https://practice.geeksforgeeks.org/problems/assignment-problem3016

    int assignmentProblem(int[] Arr, int N) {
        // code here
        if (N == 0)
            return 0;
        boolean[] visited = new boolean[N];
        List<List<Integer>> list = new ArrayList<>();
        int idx = 0;
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < Arr.length; i++) {
            temp.add(Arr[i]);
            idx++;
            if (idx == N) {
                idx = 0;
                list.add(new ArrayList<>(temp));
                temp = new ArrayList<>();
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                ans = Math.min(ans, list.get(i).get(0) + dfs(list, 1, visited, N));
                visited[i] = false;
            }
        }

        return ans;

    }

    int dfs(List<List<Integer>> list, int index, boolean[] visited, int N) {
        if (index == N)
            return 0;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                ans = Math.min(ans, list.get(i).get(index) + dfs(list, index + 1, visited, N));
                visited[i] = false;
            }
        }

        return ans;
    }
}
