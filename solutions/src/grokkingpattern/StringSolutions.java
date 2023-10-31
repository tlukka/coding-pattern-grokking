package grokkingpattern;

import java.util.ArrayList;
import java.util.List;

public class StringSolutions {

    //Partition Labels
    // https://leetcode.com/problems/partition-labels

    List<Integer> partitionLabels(String s) {
        int[] map = new int[26];
        // storing last occurance of index in map..
        for (int i = 0; i < s.length(); i++)
            map[s.charAt(i) - 'a'] = i;
        int maxIndex = Integer.MIN_VALUE, start = 0;

        List<Integer> partitions = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            maxIndex = Math.max(maxIndex, map[s.charAt(i) - 'a']);
            if (i == maxIndex) {
                partitions.add(i - start + 1);
                start = i + 1;
            }
        }

        return partitions;
    }

    // https://leetcode.com/problems/minimum-path-sum
    //Minimum Path Sum

    int minPathSum(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[][] dp = new int[n][m];
        dp[0][0] = grid[0][0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0]; // first column ...
        }
        for (int j = 1; j < m; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];  // first row..
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[n - 1][m - 1];
    }

    // https://leetcode.com/problems/search-a-2d-matrix-ii
    // Search a 2D Matrix II
    boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length, m = matrix[0].length;
        /*for(int i=0; i<n; i++) {
            for(int j=0;j<m; j++) {
                if(matrix[i][j]==target)
                  return true;
            }
        }

        return false;*/
        int i = 0, j = m - 1;
        while (i < n && j >= 0) {
            if (matrix[i][j] == target)
                return true;
            else if (matrix[i][j] > target)
                j--;
            else
                i++;
        }
        return false;
    }
}
