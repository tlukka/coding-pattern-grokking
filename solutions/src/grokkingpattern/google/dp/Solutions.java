package grokkingpattern.google.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solutions {

    public static void main(String[] args) {
        Solutions sl = new Solutions();

        List<String> wordList = Arrays.asList("mobile", "samsung", "sam", "sung",
                "man", "mango", "icecream", "and",
                "go", "i", "love", "ice", "cream");

        System.out.println(sl.wordBreakBF(wordList, "ilikesamsung"));
        System.out.println(sl.wordBreakDp("ilikesamsung", wordList.toArray(new String[wordList.size()])));
        String str1 = "iloveicecreamandmango";
        sl.wordSearchBackTrack(str1, wordList);
    }

    //https://www.geeksforgeeks.org/word-break-problem-dp-32
    boolean wordBreakBF(List<String> wordList, String word) {
        if (word.isEmpty()) return true;
        int len = word.length();
        // Check if the word can be broken down into words from the wordList
        for (int i = 1; i <= len; i++) {
            String prefix = word.substring(0, i);
            if (wordList.contains(prefix) && wordBreakBF(wordList, word.substring(i))) {
                return true;
            }
        }
        return false;
    }

    boolean wordBreakDp(String str, String[] dictionary) {
        int len = str.length();
        if (len == 0) return true;
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for (int i = 0; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (!dp[j] && dictionaryContains(str.substring(j, i), dictionary)) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];
    }

    boolean dictionaryContains(String word, String[] dictionary) {
        int size = dictionary.length;
        for (int i = 0; i < size; i++) {
            if (dictionary[i].compareTo(word) == 0) return true;
        }
        return false;
    }

    // https://www.geeksforgeeks.org/word-break-problem-using-backtracking/
    void wordSearchBackTrack(String str, List<String> dict) {
        backTrackWordSearch(str, dict, " ", str.length());
    }

    void backTrackWordSearch(String str, List<String> dict, String ans, int length) {
        for (int i = 1; i <= length; i++) {
            String prefix = str.substring(0, i);
            if (dict.contains(prefix)) {
                if (i == length) {
                    ans += prefix;
                    System.out.println(ans);
                    return;
                }
                backTrackWordSearch(str.substring(i, length), dict, ans + prefix + " ", length - i);
            }
        }
    }

    // https://www.geeksforgeeks.org/trapping-rain-water/
    // Time Complexity: O(N) and Auxiliary Space: O(1)
    //  Two Pointer Approach
    long trappingWater(int arr[], int n) {
        long level = 0, water = 0;
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            level = Math.max(level, Math.min(arr[left], arr[right]));
            water += level - Math.min(arr[left], arr[right]);
            if (arr[left] < arr[right]) {
                left++;
            } else {
                right--;
            }
        }

        return water;
    }

    // https://www.geeksforgeeks.org/maximum-product-subarray/#
    // Maximum Product Subarray

    long maxProduct(int[] nums, int n) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        long ans = 0, min = 1, max = 1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                // reset values
                max = 1;
                min = 1;
                ans = Math.max(ans, 0);
            } else {
                long temp = min;
                min = Math.min(nums[i], Math.min(min * nums[i], max * nums[i]));
                max = Math.max(nums[i], Math.max(temp * nums[i], max * nums[i]));
                ans = Math.max(ans, max);
            }
        }

        return ans;
    }


    long[] productItselfArray(int[] arr, int n) {
        // code here
        long prefix = 1, suffix = 1;
        long[] prefixProduct = new long[n];
        prefixProduct[0] = 1;
        for (int i = 1; i < n; i++) {
            prefixProduct[i] = prefix * arr[i - 1];
        }
        for (int i = n - 2; i >= 0; i--) {
            prefixProduct[i] = suffix * arr[i + 1];
            suffix *= arr[i + 1];
        }

        return prefixProduct;

    }

    // https://leetcode.com/problems/minimum-path-sum
    // Minimum Path Sum
    int minimumPathSum(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (r == 0 && c == 0) {
                    matrix[r][c] = matrix[r][c];
                } else if (r == 0 && c != 0) {
                    matrix[r][c] += matrix[r][c - 1];
                } else if (r != 0 && c == 0) {
                    matrix[r][c] += matrix[r - 1][c];
                } else {
                    matrix[r][c] += Math.min(matrix[r][c - 1], matrix[r - 1][c]);
                }
            }
        }

        return matrix[n - 1][m - 1];
    }

    int minimumPathSumDp(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        int[][] dp = new int[n][m];
        dp[0][0] = matrix[0][0];
        // if first row
        for (int c = 0; c < m; c++) {
            dp[0][c] = matrix[0][c] + dp[0][c - 1];
        }

        // if first column
        for (int r = 0; r < n; r++) {
            dp[r][0] = matrix[r][0] + dp[r - 1][0];
        }

        for (int r = 1; r < n; r++) {
            for (int c = 1; c < m; c++) {
                dp[r][c] = matrix[r][c] + Math.min(dp[r - 1][c], dp[r][c - 1]);
            }
        }

        return dp[n][m];
    }

    // Given a matrix where a cell has the value of 1 if it's a wall and 0 if not,
    // find the number of ways to go from the top-left cell to the bottom-right cell,
    // knowing that it's not possible to pass from a wall and we can only go to the right or to the bottom
    //https://leetcode.com/problems/unique-paths-ii/
    int path(int[][] matrix) {
        return path(matrix, 0, 0);
    }

    int path(int[][] matrix, int i, int j) {
        int n = matrix.length, m = matrix[0].length;
        if (i == n || j == m || matrix[i][j] == 1)
            return 0;
        if (i == n - 1 && j == m - 1)
            return 1;
        return path(matrix, i, j + 1) + path(matrix, i + 1, j);
    }

    int uniquePathsByDp(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[][] dp = new int[n][m];
        dp[0][0] = grid[0][0] == 0 ? 1 : 0;
        // first row
        for (int c = 1; c < m; c++) {
            if (grid[0][c] == 1) {
                dp[0][c] = 0;
            } else {
                dp[0][c] = dp[0][c - 1];
            }
        }
        // first column
        for (int r = 1; r < n; r++) {
            if (grid[r][0] == 1) {
                dp[r][0] = 0;
            } else {
                dp[r][0] = dp[r - 1][0];
            }
        }
        for (int r = 1; r < n; r++) {
            for (int c = 1; c < m; c++) {
                if (grid[r][c] == 1) {
                    dp[r][c] = 0;
                } else {
                    dp[r][c] = dp[r][c - 1] + dp[r - 1][c];
                }
            }
        }
        return dp[n - 1][m - 1];
    }

    int pathByDpSimplied(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[][] dp = new int[n + 1][m + 1];
        if (grid[0][0] != 1)
            dp[1][1] = 1;
        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= m; c++) {
                if (grid[r - 1][c - 1] != 1) {
                    dp[r][c] += dp[r - 1][c] + dp[r][c - 1];
                }
            }
        }
        return dp[n][m];
    }

    int uniquePathsOptimized(int[][] grid) {
        if (grid[0][0] == 1)
            return 0;
        int n = grid.length, m = grid[0].length;
        grid[0][0] = 1;
        // top row
        for (int c = 1; c < m; c++) {
            grid[0][c] = grid[0][c] == 0 ? grid[0][c - 1] : 0;
        }
        // first column
        for (int r = 1; r < n; r++) {
            grid[r][0] = grid[r][0] == 0 ? grid[r - 1][0] : 0;
        }
        for (int r = 1; r < n; r++) {
            for (int c = 1; c < m; c++) {
                if (grid[r][c] != 1) {
                    grid[r][c] = grid[r - 1][c] + grid[r][c - 1];
                } else {
                    grid[r][c] = 0;
                }
            }
        }
        return grid[n - 1][m - 1];
    }

    //https://www.geeksforgeeks.org/stock-buy-sell/
    ArrayList<ArrayList<Integer>> stockBuySell(int A[], int n) {
        // code here
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            if (A[i] > A[i - 1]) {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(i - 1);
                temp.add(i);
                result.add(temp);
            }
        }

        return result;
    }

    int maxProfit(int[] arr, int n) {
        int maxProfit = 0;
        for (int i = 1; i < n; i++) {
            if (arr[i] > arr[i - 1])
                maxProfit += arr[i] - arr[i - 1];
        }
        return maxProfit;
    }

    // Given three strings A, B and C. Write a function that checks whether C is an interleaving of A and B.
    // C is said to be interleaving A and B,
    //https://www.geeksforgeeks.org/find-if-a-string-is-interleaved-of-two-other-strings-dp-33/#
    boolean isInterleaved(String A, String B, String C) {
        if (A.isEmpty() && B.isEmpty() && C.isEmpty())
            return true;
        if (C.isEmpty())
            return false;
        return (C.charAt(0) == A.charAt(0) && isInterleaved(A.substring(1), B, C.substring(1))) ||
                (C.charAt(0) == B.charAt(0) && isInterleaved(A, B.substring(1), C.substring(1)));
    }

    boolean interLeaveOneDp(String s1, String s2, String s3) {
        if (s1.isEmpty() && s2.isEmpty() && s3.isEmpty()) {
            return true;
        }
        int n = s1.length(), m = s2.length(), k = s3.length();
        if (n + m != k)
            return false;
        boolean[] dp = new boolean[m + 1];
        dp[0] = true;
        for (int i = 1; i <= m; i++) {
            dp[i] = dp[i - 1] && s2.charAt(i - 1) == s3.charAt(i - 1);
        }
        for (int i = 1; i <= n; i++) {
            dp[0] = dp[0] && s1.charAt(i - 1) == s3.charAt(i - 1);
            for (int j = 1; j <= m; j++) {
                dp[j] = (dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }
        return dp[m];
    }

    boolean interLeaveDp(String s1, String s2, String s3) {
        if (s1.isEmpty() && s2.isEmpty() && s3.isEmpty()) {
            return true;
        }
        int n = s1.length(), m = s2.length(), k = s3.length();
        if (n + m != k)
            return false;
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }
        for (int i = 1; i <= m; i++) {
            dp[0][i] = dp[0][i - 1] && s2.charAt(i - 1) == s3.charAt(i - 1);
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }
        return dp[n][m];
    }


    // https://www.geeksforgeeks.org/find-maximum-possible-stolen-value-houses/
    // There are N houses built in a line, each of which contains some value in it. A thief is going to steal the
    // maximum value of these houses, but he can’t steal in two adjacent houses because the owner of the stolen houses
    // will tell his two neighbors left and right sides. The task is to find what is the maximum stolen value.

    int maxLoot(int[] houses) {
        return maxLoot(houses, houses.length - 1);
    }

    int maxLoot(int[] houses, int n) {
        if (n < 0)
            return 0;
        if (n == 0)
            return houses[0];

        int pickHouse = houses[0] + maxLoot(houses, n - 2);
        int notPickHouse = maxLoot(houses, n - 1);
        return Math.max(pickHouse, notPickHouse);
    }

    int maxLootByWithOptimized(int[] arr, int n) {
        if (n == 0)
            return 0;

        int rob = arr[0];
        if (n == 1)
            return rob;

        int notRob = Math.max(arr[0], arr[1]);
        if (n == 2)
            return notRob;

        // contains maximum stolen value at the end
        int max_val = 0;

        // Fill remaining positions
        for (int i = 2; i < n; i++) {
            max_val = Math.max(arr[i] + rob, notRob);
            rob = notRob;
            notRob = max_val;
        }

        return max_val;
    }

    int maxLootByDp(int[] houses, int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return houses[0];
        if (n == 2) {
            return Math.max(houses[0], houses[1]);
        }

        int[] dp = new int[n];
        dp[0] = houses[0];
        dp[1] = Math.max(houses[0], houses[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 2] + houses[i], dp[i - 1]);
        }

        return dp[n - 1];
    }

    // https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
    //  Longest Increasing Path in a Matrix
    int longestIncreasingPath(int[][] matrix) {
        int n=matrix.length, m=matrix[0].length;
        int[][] dp=new int[n][m];
        for(int[] d: dp)
            Arrays.fill(d, -1);
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(dp[i][j] ==-1) {
                    dfsIncreasingPath(matrix,-1, i,j, dp);
                }
            }
        }

        int maxPath = Integer.MIN_VALUE;
        for(int[] d: dp) {
            maxPath= Math.max(maxPath, Arrays.stream(d).max().getAsInt());
        }
        return maxPath;
    }

    int[][] dirs = new int[][]{{-1,0}, {0,-1}, {0,1}, {1,0}};
    int dfsIncreasingPath(int[][] matrix, int parent, int r, int c, int[][] dp) {
        if(r<0 || r>=matrix.length || c<0 || c>=matrix[0].length || matrix[r][c]<=parent)
            return 0;
        parent = matrix[r][c];
        if(dp[r][c] !=-1)
            return dp[r][c];

        // back track
        int[] ans = new int[4];
        for(int i=0; i<dirs.length; i++) {
            ans[i]= dfsIncreasingPath(matrix,parent, r+dirs[i][0], c+dirs[i][1], dp);
        }
        return dp[r][c]= 1+Arrays.stream(ans).max().getAsInt();
    }

// https://www.geeksforgeeks.org/word-break-problem-trie-solution/
// Given an input string and a dictionary of words, find out if the input string can be segmented into
// a space-separated sequence of dictionary words


}
