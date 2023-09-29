package blindpatternsolutions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class DpSolutions {
    public static void main(String[] args) throws ParseException {
        DpSolutions sl = new DpSolutions();
        /*System.out.println(sl.claimStairsWithDp(5));
        System.out.println(sl.claimStairs(5));
        System.out.println(sl.coinExchange(new int[]{1, 2, 5}, 11));
        System.out.println(sl.coinExchange(new int[]{2}, 3));
        System.out.println(sl.coinExchange(new int[]{1}, 0));
        System.out.println(sl.lengthOfLIS(new int[]{7, 7, 7, 7}));
        System.out.println(sl.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        System.out.println(sl.longestCommonSubsequence("abcde", "ace"));
        System.out.println(sl.longestCommonSubsequence("abc", "abc"));
        System.out.println(sl.longestCommonSubsequence("abc", "def"));
        System.out.println(sl.wordBreak("leetcode", Arrays.asList("leet", "code")));
        System.out.println(sl.wordBreak("applepenapple", Arrays.asList("apple", "pen")));
        System.out.println(sl.wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));



        List<String> list = new ArrayList<>(Arrays.asList("Bob,134,44.0,14,March,2018",
                "Mark,134,44.0,15,March,2018",
                "Mark,134,22.0,16,March,2018"));

        String startDate1 = "03/14/2018";
        String endDate1 = "03/14/2018";
        System.out.println("Result: " + sl.findRecords(list, startDate1, endDate1));
        System.out.println("Result: " + sl.formatLog(list, startDate1, endDate1));

        String startDate2 = "03/14/2018";
        String endDate2 = "03/20/2018";
        System.out.println("Result: " + sl.findRecords(list, startDate2, endDate2));
        System.out.println("Result: " + sl.formatLog(list, startDate2, endDate2));



        int[][] nodes = {{7, 11}, {5, 11}, {3, 8}, {11, 2}, {11, 9}, {8, 9}, {3, 10}};
        System.out.println(sl.findNodes(nodes, 2));

         */
        System.out.println(sl.numDecodings("226"));
        System.out.println(sl.numDecodings("10011"));
        System.out.println(sl.numDecodingsWithDp("226"));
        System.out.println(sl.numDecodingsWithDp("10011"));
    }

    //  Decode Ways
    // Given a string s containing only digits, return the number of ways to decode it.
    int numDecodings(String s) {
        int len = s.length();
        if (s == null || len == 0) return 0;
        int[] dp = new int[len + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;
        for (int i = 2; i <= len; i++) {
            int first = Integer.valueOf(s.substring(i - 1, i));
            int second = Integer.valueOf(s.substring(i - 2, i));
            if (first >= 1 && first <= 9)
                dp[i] += dp[i - 1];
            if (second >= 10 && second <= 26)
                dp[i] += dp[i - 2];

        }
        return dp[len];
    }

    int numDecodingsWithDp(String s) {
        int len = s.length();
        if (s == null || len == 0) return 0;
        int[] dp = new int[len + 1];
        dp[len] = 1;
        for (int i = len - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                dp[i] = 0;
            } else {
                dp[i] = dp[i + 1];
                if (i < len - 1 && (s.charAt(i) == '1' || s.charAt(i) == '2' && s.charAt(i + 1) <= '6')) {
                    dp[i] += dp[i + 2];
                }
            }
        }
        return dp[0];
    }


    // House Robber
    // Given an integer array nums representing the amount of money of each house,
    // constraint stopping you from robbing each of them is that adjacent houses
    // return the maximum amount of money you can rob tonight without alerting the police.

    int robCircularHouse(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int len = nums.length;
        if (len == 1) return nums[0];
        if (len == 2) return Math.max(nums[0], nums[1]);
        return Math.max(robHouse1(nums, 0, len - 2), robHouse1(nums, 1, len - 1));
    }

    int robHouse1(int[] nums, int start, int end) {
        int prev = nums[start];
        int rob = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            int currRob = Math.max(rob, prev + nums[i]);
            prev = rob;
            rob = currRob;
        }

        return rob;
    }

    int robHouseWithOutSpace(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        int prev = nums[0];
        int rob = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            int currRob = Math.max(rob, prev + nums[i]);
            prev = rob;
            rob = currRob;
        }
        return rob;
    }

    int robHouse1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        int[] dp = new int[nums.length + 1];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }

        return dp[nums.length - 1];
    }

    // Given an array of distinct integers nums and a target integer target, return the number of
    // possible combinations that add up to target.
    int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1; // base case with 1
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i >= num) {
                    dp[i] += dp[i - num];
                }
            }
        }

        return dp[target];
    }

    // Unique Paths
    int uniquePathMoveByRobotWithRecursion(int m, int n) {
        if (m == 1 || n == 1)
            return 1;
        return uniquePathMoveByRobotWithRecursion(m, n - 1) + uniquePathMoveByRobotWithRecursion(m - 1, n);
    }

    int uniquePathDp(int m, int n) {
        int[][] grid = new int[m][n];
        for (int[] arr : grid)
            Arrays.fill(arr, 1); // set first row and column as 1;
        for (int r = 1; r < m; r++) {
            for (int c = 1; c < n; c++) {
                grid[r][c] = grid[r - 1][c] + grid[r][c - 1];
            }
        }
        return grid[m - 1][n - 1];
    }

    // You are given an integer array nums. You are initially positioned at the array's first index,
    // and each element in the array represents your maximum jump length at that position.
    //Return true if you can reach the last index, or false otherwise.
    // i/p : nums = [2,3,1,1,4] -> o/p: true , [3,2,1,0,4] -> o/p: false
    boolean canJumpWithReverse(int[] nums) {
        int lastPosition = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] + i >= lastPosition) {
                lastPosition = i;
            }
        }

        return lastPosition == 0;
    }

    boolean canJump(int[] nums) {
        int reachable = 0;
        for (int i = 0; i < nums.length && i <= reachable; i++) {
            reachable = Math.max(reachable, i + nums[i]);
            if (reachable >= nums.length - 1)
                return true;
        }
        return false;
    }

    // Array is divided into four sub arrays consecutively named A,B,C,D in such a way that
    //"Sum(A) - Sum(B) + Sum(C) - Sum(D)" is maximum.These subarrays sizes can be '0' also in some cases
    // as listed in below(Array has both positive and negative elements) Can anyone share a detailed coded
    // solution and its logic ?
    //Example: ar = {-1,-2,-3,1,2,3}
    //A={ } , B={-1,-2,-3} , C={1,2,3} , D={ }
    //Output: maximum sum possible for the above expression is "1+2+3+1+2+3+ = 12"

    int fourPartsMax(int[] A) {
        int n = A.length;
        int[] nums = new int[n + 1];
        int m = nums.length;
        //padding extra 0 to deal with the 4th subarry is empty, make it easy coding
        nums[n] = 0;
        for (int i = 0; i < n; i++) {
            nums[i] = A[i];
        }
        int[][] dp = new int[5][m + 1];
        int[] sum = new int[m + 1];
        //init
        for (int i = 1; i <= m; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
            dp[1][i] = sum[i];
        }
        //dp[i][j] := the maximum result can be formed using first j elements partitoned  into i parts
        // dp[i][j] = max(dp[i - 1][k] - sum(k, j)) i == 2 || i == 4;
        //          = max(dp[i - 1][k] + sum(k, j)) i == 3;
        for (int i = 2; i <= 4; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = Integer.MIN_VALUE;
                // k starts with 0, means this partition can be empty,
                for (int k = 0; k <= j; k++) {
                    if (i == 2 || i == 4) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][k] - (sum[j] - sum[k]));
                    }
                    if (i == 3) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][k] + (sum[j] - sum[k]));
                    }
                }
            }
        }
        return dp[4][m];
    }

    //To design algorithm to find dependencies of a specific node.
    // Say for exampe there is an input stream 2d array in which nodes are
    // given as [[7,11], [5,11], [3,8], [11,2], [11,9], [8,9],[3,10]] . Now for a given node say 2,
    // the output should be [7,5,11,2] or [5,7,11,2]. Instead of numbers I was given characters.
    List<Integer> findNodes(int[][] nodes, int startNode) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] n : nodes) {
            map.putIfAbsent(n[1], new HashSet<>());
            map.get(n[1]).add(n[0]);
        }
        Set<Integer> visited = new HashSet<>();
        bfsNode(map, res, visited, startNode);
        Collections.reverse(res);
        return res;
    }

    void bfsNode(Map<Integer, Set<Integer>> map, List<Integer> res, Set<Integer> visited, int startNode) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(startNode);
        while (!q.isEmpty()) {
            int cur = q.poll();
            if (!visited.contains(cur)) {
                visited.add(cur);
                res.add(cur);
                for (int nei : map.getOrDefault(cur, new HashSet<>())) {
                    if (!visited.contains(nei)) {
                        q.offer(nei);
                    }
                }
            }
        }
    }


    //Given some pair as a input such that A is dependent on Z and so on,
    // we have to print the characters in such a way that dependency is not violated (basically DFS using Topology sort)
    //
    //Input:
    //{ A -> Z , C -> R, B -> S, S -> N, N -> K, E -> K }
    //
    //Output:
    //Z A K E N S B C R - (This is one of the accepted output as for A the dependency is Z so printed first.
    // for E and N dependency is K so K is printed first and so on)


    //Given start and end date, how would you return the sum of all the 'value' columns per user between
    // those dates inclusive. The dates could be spanning across months and years.
    //
    //example start: 03/14/2018 - end: 03/14/2018 - should return only one record which belongs to bob
    //example start: 03/14/2018 - end: 03/20/2018 - should return only one record for bob with value 44
    // and one record for Mark but with value 66

    //log format
    //name, id, value, day, month, year
    //"Bob,134,44.0,14,March,2018"
    //"Mark,134,44.0,15,March,2018"
    //"Mark,134,22.0,16,March,2018"

    Map<String, Double> findRecords(List<String> list, String startDate, String endDate) {
        Map<String, Integer> monthMap = new HashMap<>();
        monthMap.put("January", 1);
        monthMap.put("February", 2);
        monthMap.put("March", 3);
        monthMap.put("April", 4);
        monthMap.put("May", 5);
        monthMap.put("June", 6);
        monthMap.put("July", 7);
        monthMap.put("August", 8);
        monthMap.put("September", 9);
        monthMap.put("October", 10);
        monthMap.put("November", 11);
        monthMap.put("December", 12);
        String[] startDateArr = startDate.split("/");
        String[] endDateArr = endDate.split("/");
        Map<String, Double> result = new HashMap<>();
        List<String[]> recordList = new ArrayList<>();

        for (String str : list) {
            String[] tokens = str.split(",");
            recordList.add(tokens);
        }
        for (String[] arr : recordList) {
            if (Integer.parseInt(arr[3]) >= Integer.parseInt(startDateArr[1]) &&
                    Integer.parseInt(arr[3]) <= Integer.parseInt(endDateArr[1]) &&
                    monthMap.get(arr[4]) >= Integer.parseInt(startDateArr[0]) &&
                    monthMap.get(arr[4]) <= Integer.parseInt(endDateArr[0]) &&
                    Integer.parseInt(arr[5]) >= Integer.parseInt(startDateArr[2]) &&
                    Integer.parseInt(arr[5]) <= Integer.parseInt(endDateArr[2])) {

                if (result.containsKey(arr[0])) {
                    result.put(arr[0], result.get(arr[0]) + Double.parseDouble(arr[2]));
                } else {
                    result.put(arr[0], Double.parseDouble(arr[2]));
                }
            }
        }

        return result;
    }

    Map<String, Integer> formatLog(List<String> list, String startDate, String endDate) throws ParseException {
        Map<String, Integer> result = new HashMap<>();

        for (String record : list) {
            String[] recordArray = record.split(",");
            StringBuilder sb = new StringBuilder();
            sb.append(recordArray[3]).append(",").append(recordArray[4]).append(",").append(recordArray[5]);
            if (dateFormat(true, sb.toString()) >= dateFormat(false, startDate)
                    && dateFormat(true, sb.toString()) <= dateFormat(false, endDate)) {
                if (result.containsKey(recordArray[0])) {
                    result.put(recordArray[0], result.get(recordArray[0]) + (int) Double.parseDouble(recordArray[2]));
                } else result.put(recordArray[0], (int) Double.parseDouble(recordArray[2]));
            }

        }
        return result;
    }

    public static long dateFormat(boolean isSourceDate, String date) throws ParseException {

        long epocDate = 0;
        if (isSourceDate) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd,MMMM,yyyy");
            epocDate = sdf.parse(date).getTime();
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            epocDate = sdf.parse(date).getTime();
        }

        return epocDate;
    }

    // Given a string s and a dictionary of strings wordDict, return true if s can be segmented
    // into a space-separated sequence of one or more dictionary words.

    boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        Set<String> set = new HashSet<>(wordDict);

        // Looping
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                String suffix = s.substring(j, i);
                if (set.contains(suffix) && dp[j] == true) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[len];
    }

    //Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
    int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][];
        for (int index = 0; index < dp.length; index++) {
            dp[index] = new int[n + 1];
        }

        for (int index = 1; index <= m; index++) {
            char letter1 = text1.charAt(index - 1); // char
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

    // Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence
    // longest substring in a array
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 1;
        int[] dp = new int[nums.length];
        int ans = 0;
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            int currMax = 0;
            for (int j = 1; j <= i; j++) {
                if (nums[j] < nums[i])
                    currMax = Math.max(dp[j], currMax);
            }
            dp[i] = 1 + currMax;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public int claimStairsWithDp(int n) {
        if (n <= 2) return n;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int claimStairs(int n) {
        if (n <= 2) return n;
        int first = 1, second = 2;
        for (int i = 3; i <= n; i++) {
            int temp = first + second;
            first = second;
            second = temp;
        }
        return second;
    }

    public int coinExchange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j] && dp[i - coins[j]] != -1)
                    min = Math.min(min, dp[i - coins[j]]);
            }
            dp[i] = (min == Integer.MAX_VALUE) ? -1 : 1 + min;
        }
        return dp[amount];
    }
}
