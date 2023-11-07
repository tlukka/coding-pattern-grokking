package grokkingpattern.google.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;

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
    // https://leetcode.com/problems/word-break/
    // Given a string s and a dictionary of strings wordDict, return true if s can be segmented into
    // a space-separated sequence of one or more dictionary words.
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
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && dictionaryContains(str.substring(j, i), dictionary)) {
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

    boolean wordBreakDp(String s, List<String> wordDict) {
        int len = s.length();
        Set<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;

        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                // Sub string
                String temp = s.substring(j, i);
                if (set.contains(temp) && dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[len];
    }

    // https://leetcode.com/problems/word-break-ii/
    // Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where
    // each word is a valid dictionary word. Return all such possible sentences in any order.

    List<String> wordBreak(String s, List<String> wordDict) {
        List<List<String>> listOfResults = new ArrayList<>();
        Set<String> hashSet = new HashSet<>(wordDict);
        dfsBackTrack(listOfResults, new ArrayList<>(), hashSet, s, 0);
        List<String> result = new ArrayList<>();
        for (List<String> list : listOfResults) {
            StringBuilder sb = new StringBuilder();
            for (String str : list) {
                if (sb.length() > 0)
                    sb.append(" ");
                sb.append(str);
            }
            result.add(sb.toString());
        }
        return result;
    }

    void dfsBackTrack(List<List<String>> result, List<String> list, Set<String> wordDict, String s, int start) {
        if (start == s.length()) {
            result.add(new ArrayList<>(list));
        } else {
            for (int i = start; i < s.length(); i++) {
                String tempWord = s.substring(start, i + 1);
                if (wordDict.contains(tempWord)) {
                    list.add(tempWord);
                    dfsBackTrack(result, list, wordDict, s, i + 1);
                    list.remove(list.size() - 1);
                }
            }
        }
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

    int uniuePathByDpSimplied(int[][] grid) {
        if (grid[0][0] == 1)
            return 0;
        int n = grid.length, m = grid[0].length;
        int[][] dp = new int[n + 1][m + 1];
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
    // https://leetcode.com/problems/interleaving-string/
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
        int n = matrix.length, m = matrix[0].length;
        int[][] dp = new int[n][m];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dp[i][j] == -1) {
                    dfsIncreasingPath(matrix, -1, i, j, dp);
                }
            }
        }

        int maxPath = Integer.MIN_VALUE;
        for (int[] d : dp) {
            maxPath = Math.max(maxPath, Arrays.stream(d).max().getAsInt());
        }
        return maxPath;
    }

    int[][] dirs = new int[][]{{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    int dfsIncreasingPath(int[][] matrix, int parent, int r, int c, int[][] dp) {
        if (r < 0 || r >= matrix.length || c < 0 || c >= matrix[0].length || matrix[r][c] <= parent)
            return 0;
        parent = matrix[r][c];
        if (dp[r][c] != -1)
            return dp[r][c];

        // back track
        int[] ans = new int[4];
        for (int i = 0; i < dirs.length; i++) {
            ans[i] = dfsIncreasingPath(matrix, parent, r + dirs[i][0], c + dirs[i][1], dp);
        }
        return dp[r][c] = 1 + Arrays.stream(ans).max().getAsInt();
    }

    // Below are Knap Sack problems

    // ->Subset sum
    //->Equal sum partition
    //->Count of subsets sum with a given sum
    //->Minimum subset sum difference
    //->Count the number of subset with a given difference
    //->Target sum

    // Bounded 0/1 Knapsack problems
    //LC 416. Partition Equal Subset Sum
    //LC 494. Target Sum
    //LC 474. Ones and Zeroes
    //LC 343. Integer Break

    //Unbounded 0/1 Knapsack problems
    //LC 322. Coin Change
    //LC 518. Coin Change 2
    //LC 377. Combination Sum IV
    //LC 983. Minimum Cost For Tickets

    // https://leetcode.com/problems/maximum-earnings-from-taxi/
    // Maximum Earnings From Taxi
    long maxTaxiEarnings(int[][] rides) {
        Arrays.sort(rides, (a, b) -> a[0] - b[0]);
        PriorityQueue<long[]> queue = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        long max = 0;
        long maxProfit = 0;
        for (int[] r : rides) {
            int profit = r[1] - r[0] + r[2];
            while (!queue.isEmpty() && r[0] > queue.peek()[0]) {
                max = Math.max(max, queue.poll()[1]);
            }
            queue.offer(new long[]{r[1], profit + max});
            maxProfit = Math.max(profit + max, maxProfit);
        }
        return maxProfit;
    }

    long maxEarningByDp(int n, int[][] rides) {
        Arrays.sort(rides, (a, b) -> (a[1] - b[1]));
        TreeMap<Integer, Long> dp = new TreeMap<>();
        dp.put(0, 0L);
        for (int[] r : rides) {
            long curEarning = r[1] - r[0] + r[2] + dp.floorEntry(r[0]).getValue();
            if (curEarning > dp.lastEntry().getValue())
                dp.put(r[1], curEarning);
        }
        return dp.lastEntry().getValue();
    }

    // https://leetcode.com/problems/minimum-cost-for-tickets/
    // Minimum Cost For Tickets
    int mincostTicketsWithTreeMap(int[] days, int[] costs) {
        TreeMap<Integer, Integer> dp = new TreeMap<>();
        dp.put(-30, 0);

        for (int day : days) {
            int cur = Integer.MAX_VALUE;
            cur = Math.min(cur, dp.get(dp.floorKey(day - 1)) + costs[0]);
            cur = Math.min(cur, dp.get(dp.floorKey(day - 7)) + costs[1]);
            cur = Math.min(cur, dp.get(dp.floorKey(day - 30)) + costs[2]);
            dp.put(day, cur);
        }

        return dp.get(dp.lastKey());
    }

    int mincostTicketsWithDp(int[] days, int[] costs) {
        int maxDay = days[days.length - 1];
        boolean[] travelDays = new boolean[maxDay + 1];
        for (int day : days) {
            travelDays[day] = true;
        }

        int[] dp = new int[maxDay + 1];
        dp[0] = 0;
        for (int i = 1; i <= maxDay; i++) {
            if (!travelDays[i]) {
                dp[i] = dp[i - 1];
                continue;
            }
            dp[i] = dp[i - 1] + costs[0];
            dp[i] = Math.min(dp[i], dp[Math.max(0, i - 7)] + costs[1]);
            dp[i] = Math.min(dp[i], dp[Math.max(0, i - 30)] + costs[2]);
        }

        return dp[maxDay];
    }

    // https://leetcode.com/problems/target-sum/description/
    //  Target Sum
    int findTargetSumWays(int[] nums, int target) {
        int sum = Arrays.stream(nums).sum();
        if ((sum - target) % 2 == 1 || target > sum)
            return 0;
        int n = nums.length;
        int s2 = (sum - target) / 2;
        int[][] dp = new int[n + 1][s2 + 1];
        dp[0][0] = 1;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < s2 + 1; j++) {
                if (nums[i - 1] <= j)
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[n][s2];
    }

    int findTargetSumWaysRecursion(int[] nums, int target) {
        return helperTargetSum(nums, 0, 0, target);
    }

    int helperTargetSum(int[] nums, int index, int curSum, int target) {
        // Base case: when we reach the end of the array
        if (index == nums.length) {
            // Check if we have reached the target sum
            if (curSum == target) {
                return 1; // Return 1 to indicate that we have found a valid combination
            } else {
                return 0; // Return 0 to indicate that we have not found a valid combination
            }
        }

        // Recursive case: we can either add or subtract the current number to the current sum
        int left = helperTargetSum(nums, index + 1, curSum + nums[index], target); // Add the current number to the current sum
        int right = helperTargetSum(nums, index + 1, curSum - nums[index], target); // Subtract the current number from the current sum
        return left + right; // Return the sum of the results obtained from the left and right subproblems
    }

    // https://leetcode.com/problems/coin-change/
    // Coin Change
    int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i - coin >= 0 && dp[i - coin] != -1) {
                    min = Math.min(min, dp[i - coin]);
                }
            }
            dp[i] = (min == Integer.MAX_VALUE) ? -1 : 1 + min;
        }

        return dp[amount];
    }

    // https://leetcode.com/problems/coin-change-ii/
    // Coin Change II
    int coinChangeII(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = dp[i - coin] + dp[i];
            }
        }
        return dp[amount];
    }

    // https://leetcode.com/problems/last-stone-weight-ii
    // Last Stone Weight II
    int lastStoneWeightII(int[] stones) {
        int n = stones.length;
        int sum = Arrays.stream(stones).sum();
        int[] dp = new int[sum / 2 + 1];

        for (int i = 1; i <= n; i++) {
            int stone = stones[i - 1];
            for (int j = sum / 2; j >= stone; j--) {
                dp[j] = Math.max(dp[j], dp[j - stone] + stone);
            }
        }
        return sum - 2 * dp[sum / 2];
    }

    // https://leetcode.com/problems/combination-sum-iv/
    // Combination Sum IV
    int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i >= num) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];

    }

    // https://leetcode.com/problems/integer-break/
    //  Integer Break
    int intergerBreakWithDp(int n) {
        if (n <= 3) return n - 1;
        int[] dp = new int[n + 1];
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 4; i <= n; i++) {
            dp[i] = Math.max(dp[i - 2] * 2, dp[i - 3] * 3);
        }
        return dp[n];
    }

    int intergerBreakWithoutDp(int n) {
        if (n == 2)
            return 1;
        if (n == 3)
            return 2;

        // divide n int many threes as possible
        int threes = n / 3;
        int reminder = n % 3;
        if (reminder == 1) {
            threes -= 1; // remove 3*1;
            reminder = 4; // create 2*2;
        } else if (reminder == 0) {
            reminder = 1; //
        }

        return (int) (Math.pow(3, threes) * reminder);
    }

    //https://leetcode.com/problems/ones-and-zeroes/
    //  Ones and Zeroes
    int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String s : strs) {
            int zeros = 0, ones = 0;
            for (char c : s.toCharArray()) {
                if (c == '0')
                    zeros++;
                else
                    ones++;
            }
            for (int i = m; i >= zeros; i--) {
                for (int j = n; j >= ones; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
                }
            }
        }
        return dp[m][n];
    }

    //https://leetcode.com/problems/partition-equal-subset-sum/
    //Partition Equal Subset Sum
    boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0)
            return false;
        sum = sum / 2;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int i = sum; i > 0; i--) {
                if (i >= num) // means required sum greater than num in nums
                    // dp[i] -  means if  num not inlcude
                    // dp[i-num] -  means if num included , ans will now depend on value of i-num in dp
                    dp[i] = dp[i] || dp[i - num];
            }
        }
        return dp[sum];
    }

    // Given a set of positive integers and an integer k, check if there is any non-empty subset that sums to k.
    // I/p A = { 7, 3, 2, 5, 8 } k = 14 and Output: Subset with the given sum exists Subset { 7, 2, 5 } sums to 14
    static boolean subsetSum(int[] A, int k) {
        int n = A.length;

        // `T[i][j]` stores true if subset with sum `j` can be attained using items up to first `i` items
        boolean[][] dp = new boolean[n + 1][k + 1];

        // if the sum is zero
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        // do for i'th item
        for (int i = 1; i <= n; i++) {
            // consider all sum from 1 to sum
            for (int j = 1; j <= k; j++) {
                // don't include the i'th element if `j-A[i-1]` is negative
                if (A[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // find the subset with sum `j` by excluding or including
                    // the i'th item
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - A[i - 1]];
                }
            }
        }

        // return maximum value
        return dp[n][k];
    }

    // https://leetcode.com/problems/combination-sum
    // Combination Sum
    List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>>[] dp = new List[target + 1];
        dp[0] = new ArrayList<>();
        dp[0].add(new ArrayList<>());
        for (int c : candidates) {
            for (int i = c; i <= target; i++) {
                if (dp[i - c] != null) {
                    if (dp[i] == null)
                        dp[i] = new ArrayList<>();
                    for (List<Integer> list : dp[i - c]) {
                        List<Integer> copy = new ArrayList<>(list);
                        copy.add(c);
                        dp[i].add(copy);
                    }
                }
            }
        }
        return dp[target] == null ? new ArrayList<>() : dp[target];
    }

    // https://www.geeksforgeeks.org/count-ofdifferent-ways-express-n-sum-1-3-4/
    // Count of different ways to express N as the sum of 1, 3 and 4
    int countWays(int n) {
        int[] dp = new int[n + 1];

        // base cases
        dp[0] = dp[1] = dp[2] = 1;
        dp[3] = 2;

        // iterate for all values from 4 to n
        for (int i = 4; i <= n; i++)
            dp[i] = dp[i - 1] + dp[i - 3] + dp[i - 4];

        return dp[n];
    }

    int countWaysWithOutDp(int n) {
        int dp_i = 0, dp_i_1, dp_i_2, dp_i_3, dp_i_4;

        if (n == 0 || n == 1 || n == 2) return 1;
        else if (n == 3) return 2;

        // base cases
        dp_i_4 = dp_i_3 = dp_i_2 = 1;
        dp_i_1 = 2;

        // iterate for all values from 4 to n
        for (int i = 4; i <= n; i++) {
            dp_i = dp_i_1 + dp_i_3 + dp_i_4;
            // Updating Variable value so in next Iteration they become relevant
            dp_i_4 = dp_i_3;
            dp_i_3 = dp_i_2;
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }

        return dp_i;
    }

    // Partern 4: Palindromic sequences
    // https://leetcode.com/problems/longest-palindromic-subsequence/
    int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i + 1][j + 1] = s.charAt(i) == s.charAt(n - 1 - j) ? dp[i][j] + 1 : Math.max(dp[i + 1][j], dp[i][j + 1]);
            }
        }
        return dp[n][n];
    }

    // https://www.geeksforgeeks.org/minimum-number-deletions-make-string-palindrome
    // Minimum number of deletions to make a string palindrome
    int minimumNumberOfDeletions(String str) {
        int n = str.length();
        // Find longest palindromic subsequence
        int len = longestPalindromicString(str);
        // After removing characters other than the lps, we get palindrome.
        return (n - len);
    }

    int longestPalindromicString(String str) {
        int n = str.length();
        int[][] dp = new int[n][n];

        // Strings of length 1 are palindrome of length 1
        for (int i = 0; i < n; i++)
            dp[i][i] = 1;

        // Build the table. Note that the lower diagonal values of table are useless and not filled in the process. c1 is length of substring
        for (int cl = 2; cl <= n; cl++) {
            for (int i = 0; i < n - cl + 1; i++) {
                int j = i + cl - 1;
                if (str.charAt(i) == str.charAt(j) && cl == 2)
                    dp[i][j] = 2;
                else if (str.charAt(i) == str.charAt(j))
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                else
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
            }
        }

        // length of longest palindromic subsequence
        return dp[0][n - 1];
    }

    int minDeletionToPalindrome(String s) {
        int[][] dp = new int[s.length()][s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return s.length() - dp[0][s.length() - 1];
    }

    // https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/
    // Return the minimum number of steps to make s palindrome.
    int minInsertions(String s) {
        String s2 = new StringBuilder(s).reverse().toString();
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = (s.charAt(i - 1) == s2.charAt(j - 1)) ? 1 + dp[i - 1][j - 1] : Math.max(dp[i][j - 1], dp[i - 1][j]);
            }
        }

        return n - dp[n][n];
    }

    // https://leetcode.com/problems/palindromic-substrings/
    // Palindromic Substrings
    int countSubstrings(String s) {
        int count = 0;
        if (s == null || s.length() == 0)
            return count;

        for (int i = 0; i < s.length(); i++) {
            count += findPalindroms(s, i, i);
            count += findPalindroms(s, i, i + 1);
        }
        return count;
    }

    int findPalindroms(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
        return count;
    }

    // https://leetcode.com/problems/palindrome-partitioning-ii/
    // Palindrome Partitioning II
    // Given a string s, partition s such that every substring of the partition is a palindrome
    //Return the minimum cuts needed for a palindrome partitioning of s.
    int minCut(String s) {
        char[] c = s.toCharArray();
        int n = c.length;
        int[] dp = new int[n];
        boolean[][] pal = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = 0; j <= i; j++) {
                if (c[j] == c[i] && (j + 1 > i - 1 || pal[j + 1][i - 1])) {
                    pal[j][i] = true;
                    min = j == 0 ? 0 : Math.min(min, dp[j - 1] + 1);
                }
            }
            dp[i] = min;
        }
        return dp[n - 1];
    }

    // https://www.geeksforgeeks.org/longest-common-substring-dp-29/
    // Longest Common Substring
    int longestSubStr(char[] X, char[] Y, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        int result = 0;
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = 0;
                else if (X[i - 1] == Y[j - 1]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    result = Math.max(result, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        return result;
    }

    // Longest Common Subsequence
    // https://leetcode.com/problems/longest-common-subsequence/
    int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        // Initialization of 2 Dimentional Array
        int[][] dp = new int[m + 1][n + 1];
        for (int index = 1; index <= m; index++) {
            char letter1 = text1.charAt(index - 1);
            for (int search = 1; search <= n; search++) {
                char letter2 = text2.charAt(search - 1);
                if (letter1 == letter2) {
                    dp[index][search] = 1 + dp[index - 1][search - 1];
                } else {
                    dp[index][search] = Math.max(dp[index][search - 1], dp[index - 1][search]);
                }
            }
        }
        return dp[m][n];
    }

    // https://leetcode.com/problems/longest-increasing-subsequence/
    //Longest Increasing Subsequence
    int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 1;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int ans = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (nums[j] < nums[i])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            //dp[i] = 1+currMax;
            ans = Math.max(dp[i], ans);
        }

        return ans;
    }

    // Maximum Sum Increasing Subsequence
    // https://www.geeksforgeeks.org/maximum-sum-increasing-subsequence-dp-14/
    int maxSumIncreaseingequence(int[] arr, int n) {
        int i, j, max = 0;
        int[] msis = new int[n];
        /* Initialize msis (Maximum Sum Increasing Subsequence)  values for all indexes */
        for (i = 0; i < n; i++)
            msis[i] = arr[i];
        /* Compute maximum sum values  in bottom up manner */
        for (i = 1; i < n; i++)
            for (j = 0; j < i; j++)
                if (arr[i] > arr[j] && msis[i] < msis[j] + arr[i])
                    msis[i] = msis[j] + arr[i];

        /* Pick maximum of all  msis values */
        for (i = 0; i < n; i++)
            if (max < msis[i])
                max = msis[i];

        return max;
    }

    // Minimum number of deletions to make a sorted sequence
    // https://www.geeksforgeeks.org/minimum-number-deletions-make-sorted-sequence/
    int minimumNumberOfDeletions(int[] arr, int n) {
        // Find longest  increasing subsequence
        int len = lengthOfIncreaseSequence(arr, n);

        // After removing elements other than the lis, we get sorted sequence.
        return (n - len);
    }

    // length of the longest increasing
    int lengthOfIncreaseSequence(int[] arr, int n) {
        int result = 0;
        int[] lis = new int[n];

        /* Initialize LIS values for all indexes */
        for (int i = 0; i < n; i++)
            lis[i] = 1;

        /* Compute optimized LIS  values in bottom up manner */
        for (int i = 1; i < n; i++)
            for (int j = 0; j < i; j++)
                if (arr[i] > arr[j] && lis[i] < lis[j] + 1)
                    lis[i] = lis[j] + 1;

        /* Pick result imum of all LIS values */
        for (int i = 0; i < n; i++)
            if (result < lis[i])
                result = lis[i];

        return result;
    }

    int minDeletions(int[] arr) {
        int n = arr.length;
        int[] lis = new int[n];
        Arrays.fill(lis, 1); // Initialize the LIS array with all 1's

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    lis[i] = Math.max(lis[i], lis[j] + 1);
                }
            }
        }

        return n - Arrays.stream(lis).max().getAsInt(); // Return the number of elements to delete
    }

    // https://www.geeksforgeeks.org/longest-repeating-subsequence/

    int findLongestRepeatingSubSeq(String str) {
        int n = str.length();

        // Create and initialize DP table
        int[][] dp = new int[n + 1][n + 1];

        // Fill dp table (similar to LCS loops)
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                // If characters match and indexes are not same
                if (str.charAt(i - 1) == str.charAt(j - 1) && i != j)
                    dp[i][j] = 1 + dp[i - 1][j - 1];

                    // If characters do not match
                else
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
            }
        }
        return dp[n][n];
    }

    int findLongestRepeatingSubSeq(StringBuilder s1, int i, int j, int[][] dp) {
        if (i >= s1.length() || j >= s1.length()) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        if (dp[i][j] == -1) {
            if (s1.charAt(i) == s1.charAt(j) && i != j) {
                dp[i][j] = 1 + findLongestRepeatingSubSeq(s1, i + 1, j + 1, dp);
            } else {
                dp[i][j] = Math.max(findLongestRepeatingSubSeq(s1, i, j + 1, dp), findLongestRepeatingSubSeq(s1, i + 1, j, dp));
            }
        }
        return dp[i][j];
    }

    // Find number of times a string occurs as a subsequence in given string
    // https://www.geeksforgeeks.org/find-number-times-string-occurs-given-string/
    // Given two strings, find the number of times the second string occurs in the first string,
    // whether continuous or discontinuous.
    int count(String a, String b) {
        int m = a.length();
        int n = b.length();
        // Create a table to store results of sub-problems
        int lookup[][] = new int[m + 1][n + 1];
        // If first string is empty
        for (int i = 0; i <= n; ++i)
            lookup[0][i] = 0;
        // If second string is empty
        for (int i = 0; i <= m; ++i)
            lookup[i][0] = 1;
        // Fill lookup[][] in  bottom up manner
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // If last characters are  same, we have two options -
                // 1. consider last characters   of both strings in solution
                // 2. ignore last character of first string
                if (a.charAt(i - 1) == b.charAt(j - 1))
                    lookup[i][j] = lookup[i - 1][j - 1] + lookup[i - 1][j];
                else
                    // If last character are   different, ignore last character of first string
                    lookup[i][j] = lookup[i - 1][j];
            }
        }
        return lookup[m][n];
    }

    // Longest Bitonic Subsequence
    // https://www.geeksforgeeks.org/longest-bitonic-subsequence-dp-15/
    int longestBitonicSubsequence(int[] arr, int n) {

        /* Allocate memory for LIS[] and initialize LIS values as 1 for all indexes */
        int[] lis = new int[n];
        for (int i = 0; i < n; i++)
            lis[i] = 1;

        /* Compute LIS values from left to right */
        for (int i = 1; i < n; i++)
            for (int j = 0; j < i; j++)
                if (arr[i] > arr[j] && lis[i] < lis[j] + 1)
                    lis[i] = lis[j] + 1;

        /* Allocate memory for lds and initialize LDS values for all indexes */
        int[] lds = new int[n];
        for (int i = 0; i < n; i++)
            lds[i] = 1;

        /* Compute LDS values from right to left */
        for (int i = n - 2; i >= 0; i--)
            for (int j = n - 1; j > i; j--)
                if (arr[i] > arr[j] && lds[i] < lds[j] + 1)
                    lds[i] = lds[j] + 1;


        /* Return the maximum value of lis[i] + lds[i] - 1*/
        int max = lis[0] + lds[0] - 1;
        for (int i = 1; i < n; i++)
            if (lis[i] + lds[i] - 1 > max)
                max = lis[i] + lds[i] - 1;

        return max;
    }

    // https://www.geeksforgeeks.org/longest-alternating-subsequence/
    // Longest alternating subsequence
    // Input: arr[] = {10, 22, 9, 33, 49, 50, 31, 60} Output: 6
    //Explanation: The subsequences {10, 22, 9, 33, 31, 60} or {10, 22, 9, 49, 31, 60} or {10, 22, 9, 50, 31, 60}
    // are longest subsequence of length 6
    int longestAlertingSequence(int[] arr, int n) {

        // "inc" and "dec" initialized as 1, as single element is still LAS
        int inc = 1;
        int dec = 1;
        // Iterate from second element
        for (int i = 1; i < n; i++) {

            if (arr[i] > arr[i - 1]) {
                // "inc" changes if "dec" changes
                inc = dec + 1;
            } else if (arr[i] < arr[i - 1]) {
                // "dec" changes if "inc" changes
                dec = inc + 1;
            }
        }
        // Return the maximum length
        return Math.max(inc, dec);
    }

    // https://leetcode.com/problems/edit-distance/
    //Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
    //You have the following three operations permitted on a word:
    //Insert a character Delete a character Replace a character
    int minDistanceDp(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int[][] dp = new int[n + 1][m + 1];

        // Base cases
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= m; j++) {
            dp[0][j] = j;
        }

        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= m; c++) {
                int insert = 0, update = 0, delete = 0;
                if (word1.charAt(r - 1) == word2.charAt(c - 1)) {
                    dp[r][c] = dp[r - 1][c - 1];
                } else {
                    insert = 1 + dp[r][c - 1];
                    update = 1 + dp[r - 1][c];
                    delete = 1 + dp[r - 1][c - 1];
                    dp[r][c] = Math.min(insert, Math.min(update, delete));
                }
            }
        }

        return dp[n][m];
    }


    //  https://leetcode.com/problems/minimum-window-subsequence/
    // https://www.geeksforgeeks.org/problems/minimum-window-subsequence
    // Given strings str1 and str2, find the minimum (contiguous) substring W of str1, so that str2 is a subsequence of W.
    String minWindow(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int j = 0; j <= m; j++) {
            dp[0][j] = j + 1;
        }

        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++) {
                if (str2.charAt(i - 1) == str1.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else
                    dp[i][j] = dp[i][j - 1];
            }

        int left = 0, minLen = Integer.MAX_VALUE;
        for (int j = 1; j <= n; j++) {
            if (dp[m][j] > 0 && j - dp[m][j] < 1 + minLen) {
                left = dp[m][j] - 1;
                minLen = j - dp[m][j] + 1;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : str1.substring(left, left + minLen);
    }

    // https://leetcode.com/discuss/interview-question/algorithms/125014/microsoft-minimum-window-subsequence
    //Given an array nums and a subsequence sub, find the shortest subarray of nums that contains sub.
    // Input: nums = [1, 2, 3, 5, 8, 7, 6, 9, 5, 7, 3, 0, 5, 2, 3, 4, 4, 7], sub = [5, 7] and Output: start = 8, size = 2

    int[] findSmallestSubstring(int[] nums, int[] sub) {
        int n = nums.length, m = sub.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int j = 0; j <= m; j++) {
            dp[0][j] = j + 1;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (sub[i - 1] == nums[j - 1])
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = dp[i][j - 1];
            }
        }

        int l = 0, minLen = Integer.MAX_VALUE;
        for (int j = 1; j <= n; j++) {
            if (dp[m][j] >= 0 && j - dp[m][j] < 1 + minLen) {
                l = dp[m][j] - 1;
                minLen = j - dp[m][j] + 1;
            }
        }

        return minLen == Integer.MAX_VALUE ? new int[]{-1, -1} : new int[]{l, minLen};
    }

    // https://leetcode.com/problems/dungeon-game
    // Dungeon Game
    int calculateMinimumHP(int[][] dungeon) {
        int n = dungeon.length, m = dungeon[0].length;
        int[][] dp = new int[n + 1][m + 1];
        for (int[] r : dp) {
            Arrays.fill(r, Integer.MAX_VALUE);
        }

        int princess = dungeon[n - 1][m - 1];
        dp[n - 1][m - 1] = princess < 0 ? 1 - princess : 1;
        for (int r = n - 1; r >= 0; r--) {
            for (int c = m - 1; c >= 0; c--) {
                if (r == n - 1 && c == m - 1)
                    continue;
                int health = Math.min(dp[r + 1][c], dp[r][c + 1]) - dungeon[r][c];
                if (dungeon[r][c] >= dp[r + 1][c] || dungeon[r][c] >= dp[r][c + 1]) {
                    health = 1;
                }
                dp[r][c] = health;
            }
        }
        return dp[0][0];
    }
}
