package blindsolutions.dp;

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

public class Solution {
    public static void main(String[] args) throws ParseException {
        Solution sl = new Solution();
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

         */

        int[][] nodes =  {{7,11}, {5,11}, {3,8}, {11,2}, {11,9}, {8,9},{3,10}};
        System.out.println(sl.findNodes(nodes, 2));
    }

    // Design an ecommerce search auto complete suggestion. (Type ahead).

    // https://leetcode.com/problems/top-k-frequent-words/
    // Lowest Commen Ancestor for Binary Tree
    //Lowest Commen Ancestor for Binary Search Tree
    // https://leetcode.com/problems/first-missing-positive/
    // https://leetcode.com/problems/group-anagrams/
    // https://leetcode.com/problems/subarray-sum-equals-k/
    // https://leetcode.com/problems/merge-k-sorted-lists/
    // Given a adjucency list: (users -> list of friends), return the shortest friend-path between two users.
    // https://leetcode.com/problems/longest-substring-without-repeating-characters/
    // Given this tree, Not necessarily it is a binary tree ( A node can have any number of children nodes).
    // Return a list that is in the reverse order. (https://leetcode.com/problems/binary-tree-level-order-traversal/)
    // https://leetcode.com/problems/n-ary-tree-level-order-traversal/
    // https://leetcode.com/problems/least-number-of-unique-integers-after-k-removals/
    // Randomize an array in place. (https://leetcode.com/problems/shuffle-an-array/)
    // Problem: You are given an array of n integers, sort the array in ascending order first by their frequency then by their value.
        //Similar LC Problem: Sort Array by Increasing Frequency
        //Sample Input: [3, 4, 5, 6, 4, 3]
        //Sample Output: [5, 6, 3, 3, 4, 4]
    // Suppose we have an api to put list of names in the DB. It's only responsibility is to put data in DB.
    // Once the DB starts to grow it starts to have higher latency. How'd you proceed to find the problem in the system.
    //https://leetcode.com/problems/valid-parentheses/
    // https://leetcode.com/problems/find-median-from-data-stream/description/



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
