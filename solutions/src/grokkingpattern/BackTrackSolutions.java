package grokkingpattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class BackTrackSolutions {
    // NOTE all below problems are defined in article at leet code
    // https://leetcode.com/problems/combination-sum/solutions/16502/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partitioning/
    public static void main(String[] args) {
        BackTrackSolutions s1 = new BackTrackSolutions();
        /*List<List<Integer>> result = s1.combinationSum(new int[]{2, 3, 6, 7}, 7);
        for (List<Integer> rs : result) {
            System.out.println(Arrays.toString(rs.stream().toArray()));
        }

        List<List<Integer>> result1 = s1.subsets(new int[]{2, 3, 6, 7});
        for (List<Integer> rs : result1) {
            System.out.println(Arrays.toString(rs.stream().toArray()));
        }

        List<List<Integer>> result2 = s1.subsetsWithDup(new int[]{1, 2, 2});
        for (List<Integer> rs : result2) {
            System.out.println(Arrays.toString(rs.stream().toArray()));
        }

        List<List<Integer>> result3 = s1.permute(new int[]{1, 2, 3});
        for (List<Integer> rs : result3) {
            System.out.println(Arrays.toString(rs.stream().toArray()));
        }



        List<List<Integer>> result4 = s1.permuteDup(new int[]{1, 2, 2});
        for (List<Integer> rs : result4) {
            System.out.println(Arrays.toString(rs.stream().toArray()));
        }

        List<List<Integer>> result5 = s1.permuteDup(new int[]{1, 2, 3});
        for (List<Integer> rs : result5) {
            System.out.println(Arrays.toString(rs.stream().toArray()));
        }



        List<List<Integer>> result5 = s1.combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
        for (List<Integer> rs : result5) {
            System.out.println(Arrays.toString(rs.stream().toArray()));
        }
        List<List<Integer>> result6 = s1.combinationSum2(new int[]{2, 5, 2, 1, 2}, 5);
        for (List<Integer> rs : result6) {
            System.out.println(Arrays.toString(rs.stream().toArray()));
        }



        List<List<String>> result6 = s1.partitionPalindrome("aab");
        for (List<String> rs : result6) {
            System.out.println(Arrays.toString(rs.stream().toArray()));
        }
        List<List<String>> result7 = s1.partitionPalindrome("a");
        for (List<String> rs : result7) {
            System.out.println(Arrays.toString(rs.stream().toArray()));
        }



        List<String> anagarms = s1.anagrams("abc");
        System.out.println(Arrays.toString(anagarms.stream().toArray()));

        List<List<Integer>> combinations = s1.combine(4, 2);
        for (List<Integer> rs : combinations) {
            System.out.println(Arrays.toString(rs.stream().toArray()));
        }



        List<List<Integer>> solveQueenMoves = s1.getNQueen(4);
        for (List<Integer> rs : solveQueenMoves) {
            System.out.println(Arrays.toString(rs.stream().toArray()));
        }



        List<List<Integer>> combinationSum3s = s1.combinationSum3(3, 7);
        for (List<Integer> rs : combinationSum3s) {
            System.out.println(Arrays.toString(rs.stream().toArray()));
        }
        List<List<Integer>> combinationSum31 = s1.combinationSum3(3, 9);
        for (List<Integer> rs : combinationSum31) {
            System.out.println(Arrays.toString(rs.stream().toArray()));
        }
        List<List<Integer>> combinationSum32 = s1.combinationSum3(4, 1);
        for (List<Integer> rs : combinationSum32) {
            System.out.println(Arrays.toString(rs.stream().toArray()));
        }



        System.out.println(s1.numDistinct("rabbbit", "rabbit"));
        System.out.println(s1.numDistinct("babgbag", "bag"));
        System.out.println(s1.numDistinct("subsub", "sub"));


        System.out.println(Arrays.toString(s1.generateParenthesis(3).stream().toArray()));


        System.out.println(Arrays.toString(s1.letterCombinationsForPhone("23").toArray()));



        System.out.println(s1.wordBreak("leetcode", Arrays.asList("leet", "code")));
        System.out.println(Arrays.toString(s1.wordBreakIIRecursion("catsanddog", Arrays.asList(new String[]{"cat", "cats", "and", "sand", "dog"})).stream().toArray()));
        System.out.println(Arrays.toString(s1.wordBreakIIWithBackTracking("catsanddog", Arrays.asList(new String[]{"cat", "cats", "and", "sand", "dog"})).stream().toArray()));

        System.out.println(Arrays.toString(s1.wordBreakIIRecursion("pineapplepenapple", Arrays.asList(new String[]{"apple", "pen", "applepen", "pine", "pineapple"})).stream().toArray()));
        System.out.println(Arrays.toString(s1.wordBreakIIRecursion("pineapplepenapple", Arrays.asList(new String[]{"apple", "pen", "applepen", "pine", "pineapple"})).stream().toArray()));
         */
        List<List<String>> nQueens = s1.solveNQueens(4);
        for (List<String> queenPlace : nQueens) {
            System.out.println(Arrays.toString(queenPlace.toArray()));
        }
        List<List<Integer>> nRowQueens = s1.getNQueen(4);
        for (List<Integer> queenPlace : nRowQueens) {
            System.out.println(Arrays.toString(queenPlace.toArray()));
        }
    }

    // Find all valid combinations of k numbers that sum up to n such that the following conditions are true:
    //Return a list of all possible valid combinations.
    //The list must not contain the same combination twice, and the combinations may be returned in any order.
    // https://leetcode.com/problems/combination-sum-iii/

    List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> list = new ArrayList<>();
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        backTrackCombinationSum3(list, new ArrayList<>(), nums, k, n, 0);
        return list;
    }

    void backTrackCombinationSum3(List<List<Integer>> result, List<Integer> tempList, int[] nums, int k, int remain, int start) {
        if (remain < 0) {
            return;
        } else if (remain == 0 && tempList.size() == k)
            result.add(new ArrayList<>(tempList));
        else {
            for (int i = start; i < nums.length; i++) {
                if (i > start && nums[i] == nums[i - 1]) continue; // skip duplicates
                tempList.add(nums[i]);
                backTrackCombinationSum3(result, tempList, nums, k, remain - nums[i], i + 1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }
    // Given a collection of candidate numbers (candidates) and a target number (target),
    // find all unique combinations in candidates where the candidate numbers sum to target.
    //Each number in candidates may only be used once in the combination.
    // candidates = [10,1,2,7,6,1,5], target = 8 and o/p [
    //[1,1,6],
    //[1,2,5],
    //[1,7],
    //[2,6]
    //]

    int flag = 0;

    List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backTrackcomibationSum2(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }

    void backTrackcomibationSum2(List<List<Integer>> result, List<Integer> tmpList, int[] nums, int remain, int start) {
        if (remain < 0) {
            flag = 1;
            return;
        } else if (remain == 0)
            result.add(new ArrayList<>(tmpList));
        else {
            for (int i = start; i < nums.length; i++) {
                if (i > start && nums[i] == nums[i - 1])
                    continue;
                tmpList.add(nums[i]);
                backTrackcomibationSum2(result, tmpList, nums, remain - nums[i], i + 1);
                tmpList.remove(tmpList.size() - 1);
                if (flag == 1) {
                    flag = 0;
                    break;
                }
            }
        }
    }

    // Given an array of distinct integers candidates and a target integer target, return a list of all unique
    // combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
    List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backTrackingCombinationSum(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }

    void backTrackingCombinationSum(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start) {
        if (remain < 0)  // got over flow
            return;
        else if (remain == 0) // base case
            list.add(new ArrayList<>(tempList));
        else {
            for (int i = start; i < nums.length; i++) {
                tempList.add(nums[i]);
                // not i + 1 because we can reuse same elements
                backTrackingCombinationSum(list, tempList, nums, remain - nums[i], i);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    //  https://leetcode.com/problems/subsets/
    // Given an integer array nums of unique elements, return all possible subsets (the power set).
    // i/p : nums = [1,2,3] and o/p: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
    List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        backTrackinSubsets(result, new ArrayList<>(), nums, 0);
        return result;
    }

    void backTrackinSubsets(List<List<Integer>> result, List<Integer> tmpList, int[] nums, int start) {
        result.add(new ArrayList<>(tmpList));
        for (int i = start; i < nums.length; i++) {
            tmpList.add(nums[i]);
            backTrackinSubsets(result, tmpList, nums, i + 1);
            tmpList.remove(tmpList.size() - 1);
        }
    }

    // https://leetcode.com/problems/subsets-ii/
    // Input: nums = [1,2,2] and Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
    List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        backTrackingSubsetWithDups(result, new ArrayList<>(), nums, 0);
        return result;
    }

    void backTrackingSubsetWithDups(List<List<Integer>> result, List<Integer> tmpList, int[] nums, int start) {
        result.add(new ArrayList<>(tmpList));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1])
                continue;
            tmpList.add(nums[i]);
            backTrackingSubsetWithDups(result, tmpList, nums, i + 1);
            tmpList.remove(tmpList.size() - 1);
        }
    }

    // https://leetcode.com/problems/permutations/
    // Given an array nums of distinct integers, return all the possible permutations.
    // Input: nums = [1,2,3] and Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
    List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackPermutation(result, new ArrayList<>(), nums);
        return result;
    }

    void backtrackPermutation(List<List<Integer>> list, List<Integer> tempList, int[] nums) {
        if (tempList.size() == nums.length)
            list.add(new ArrayList<>(tempList));
        else {
            for (int i = 0; i < nums.length; i++) {
                if (tempList.contains(nums[i])) {
                    continue;
                }
                tempList.add(nums[i]);
                backtrackPermutation(list, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    List<List<Integer>> permuteDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        backtrackPermutationWithDup(result, new ArrayList<>(), nums, new boolean[nums.length]);
        return result;
    }

    void backtrackPermutationWithDup(List<List<Integer>> list, List<Integer> tempList, int[] nums, boolean[] used) {
        if (tempList.size() == nums.length)
            list.add(new ArrayList<>(tempList));
        else {
            for (int i = 0; i < nums.length; i++) {
                if (used[i] || (i > 0 && nums[i] == nums[i - 1]) && !used[i - 1]) {
                    continue;
                }
                used[i] = true;
                tempList.add(nums[i]);
                backtrackPermutationWithDup(list, tempList, nums, used);
                used[i] = false;
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    //  https://leetcode.com/problems/palindrome-partitioning/
    // Given a string s, partition s such that every  substring of the partition is a  palindrome
    //. Return all possible palindrome partitioning of s.
    // Input: s = "aab" and Output: [["a","a","b"],["aa","b"]]
    List<List<String>> partitionPalindrome(String s) {
        List<List<String>> result = new ArrayList<>();
        backTrackPalindrome(result, new ArrayList<>(), s, 0);
        return result;
    }

    void backTrackPalindrome(List<List<String>> result, List<String> tmpList, String s, int start) {
        if (s.length() == start)
            result.add(new ArrayList<>(tmpList));
        else {
            for (int i = start; i < s.length(); i++) {
                if (isPalindrome(s, start, i)) {
                    tmpList.add(s.substring(start, i + 1));
                    backTrackPalindrome(result, tmpList, s, i + 1);
                    tmpList.remove(tmpList.size() - 1);
                }
            }
        }
    }

    boolean isPalindrome(String s, int low, int high) {
        while (low < high)
            if (s.charAt(low++) != s.charAt(high--)) return false;
        return true;
    }

    // generate anagrams of a Given String using same structure
    List<String> anagrams(String str) {
        char[] inp = str.toCharArray();
        List<String> list = new ArrayList<>();
        backtrackAnagram(inp, list, inp.length, "");
        return list;
    }

    void backtrackAnagram(char[] input, List<String> result, int size, String str) {
        if (str.length() == size)
            result.add(str);
        else {
            for (int i = 0; i < input.length; i++) {
                if (str.contains("" + input[i])) {
                    continue;
                }
                String newString = str + input[i];
                backtrackAnagram(input, result, size, newString);
            }
        }
    }

    // Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n]
    // Input: n = 4, k = 2 Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
    //Explanation: There are 4 choose 2 = 6 total combinations.
    //Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.
    List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        backTrackDfsCombination(res, new ArrayList<>(), n, k, 1);
        return res;
    }

    void backTrackDfsCombination(List<List<Integer>> result, List<Integer> tmpList, int n, int k, int start) {
        if (tmpList.size() == k) {
            result.add(new ArrayList<>(tmpList));
        } else {
            for (int i = start; i <= n; i++) {
                tmpList.add(i);
                backTrackDfsCombination(result, tmpList, n, k, i + 1);
                tmpList.remove(tmpList.size() - 1);
            }
        }
    }

    // https://leetcode.com/problems/n-queens/solutions/2107776/Explained-with-Diagrams-or-Backtracking-and-Bit-manipulation/
    // Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.

    List<List<String>> solveNQueens(int n) {
        char[][] emptyBoard = new char[n][n];
        for (char[] row : emptyBoard)
            Arrays.fill(row, '.');
        List<List<String>> result = new ArrayList<>();
        backTrackNQueens(result, emptyBoard, 0, 0, 0, 0, n);
        return result;
    }

    void backTrackNQueens(List<List<String>> result, char[][] board, int row, int cols, int diags, int antiDiags, int n) {
        if (row == n) {
            result.add(toBoard(board));
            return;
        }
        for (int col = 0; col < n; col++) {
            int diag = row - col + n;
            int antidiag = row + col;

            // check queen placed in valid placement
            if ((cols & (1 << col)) != 0 || (diags & (1 << diag)) != 0 && (antiDiags & (1 << antidiag)) != 0)
                continue;

            // update board
            board[row][col] = 'Q';
            cols |= (1 << col);
            antiDiags |= (1 << antidiag);
            diags |= (1 << diag);

            // continue to below row
            backTrackNQueens(result, board, row + 1, cols, diags, antiDiags, n);

            // undo
            board[row][col] = '.';
            cols ^= (1 << col);
            antiDiags ^= (1 << antidiag);
            diags ^= (1 << diag);
        }

    }

    static List<String> toBoard(char[][] board) {
        List<String> resultBoard = new ArrayList<>();
        for (char[] row : board)
            resultBoard.add(new String(row));
        return resultBoard;
    }

    List<List<Integer>> getNQueen(int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackNQueenMove(result, new ArrayList<>(), n, 0);
        return result;
    }

    void backtrackNQueenMove(List<List<Integer>> list, List<Integer> tempList, int n, int row) {
        if (row == n)
            list.add(new ArrayList<>(tempList));
        else {
            for (int i = 0; i < n; i++) {
                tempList.add(i);
                if (isValid(tempList)) {
                    backtrackNQueenMove(list, tempList, n, i + 1);
                }
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    boolean isValid(List<Integer> tempList) {
        int validateRow = tempList.size() - 1;
        for (int i = 0; i < validateRow; i++) {
            int diff = Math.abs(tempList.get(i) - tempList.get(validateRow));
            if (diff == 0 || diff == validateRow - i)
                return false;
        }
        return true;
    }

    // Distinct Subsequences
    // https://leetcode.com/problems/distinct-subsequences/description/Given/
    // Given two strings s and t, return the number of distinct subsequences of s which equals t.
    // Input: s = "rabbbit", t = "rabbit" and Output: 3
    //Explanation:
    //As shown below, there are 3 ways you can generate "rabbit" from s.
    //rabbbit
    //rabbbit
    //rabbbit

    int numDistinct(String src, String target) {
        int[][] dp = new int[src.length()][target.length()];
        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }
        return backTrackDistinctSequence(src, target, 0, 0, dp);
    }

    int backTrackDistinctSequence(String s, String t, int i, int j, int[][] dp) {
        if (j >= t.length())
            return 1;
        if (i >= s.length())
            return 0;
        if (dp[i][j] >= 0)
            return dp[i][j];

        int count = 0;
        for (int k = i; k < s.length(); k++) {
            if (s.charAt(k) == t.charAt(j)) {
                count += backTrackDistinctSequence(s, t, k + 1, j + 1, dp);
            }
        }
        dp[i][j] = count;
        return count;

    }

    // https://leetcode.com/problems/generate-parentheses/description/
    // Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
    // Input: n = 3 and Output: ["((()))","(()())","(())()","()(())","()()()"]

    List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backTrackParentheses(result, "", 0, 0, n);
        return result;
    }

    void backTrackParentheses(List<String> result, String str, int open, int close, int max) {
        if (max * 2 == str.length()) {
            result.add(str);
            return;
        }
        if (open < max) {
            backTrackParentheses(result, str + "(", open + 1, close, max);
        }
        if (close < open)
            backTrackParentheses(result, str + ")", open, close + 1, max);

    }

    // Letter Combinations of a Phone Number
    // https://leetcode.com/problems/letter-combinations-of-a-phone-number/
    // Input: digits = "23" and Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]

    List<String> letterCombinationsForPhone(String digits) {
        if (digits.isEmpty()) return Collections.emptyList();
        String[] phone_map = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> output = new ArrayList<>();
        backTrackLetterCombinationForPhone(output, "", digits, phone_map);
        return output;
    }

    void backTrackLetterCombinationForPhone(List<String> result, String combination, String nextDigits, String[] phone_map) {
        if (nextDigits.isEmpty())
            result.add(combination);
        else {
            String letters = phone_map[nextDigits.charAt(0) - '2'];
            for (char c : letters.toCharArray()) {
                backTrackLetterCombinationForPhone(result, combination + c, nextDigits.substring(1), phone_map);
            }
        }
    }

    // Given a string s and a dictionary of strings wordDict,
    // return true if s can be segmented into a space-separated sequence of one or more dictionary words.
    boolean wordBreak(String s, List<String> dictWord) {
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        Set<String> hashSet = new HashSet<>(dictWord);
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (hashSet.contains(s.substring(j, i)) && dp[j] == true) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];
    }

    // Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence
    // where each word is a valid dictionary word
    // https://leetcode.com/problems/word-break-ii/
    // Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"] /Output: ["cats and dog","cat sand dog"]
    List<String> wordBreakIIRecursion(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        Set<String> set = new HashSet<>(wordDict);
        for (int i = 1; i <= s.length(); i++) {
            String sub = s.substring(0, i);
            if (set.contains(sub)) {
                List<String> tmp = wordBreakIIRecursion(s.substring(i), wordDict);
                for (String x : tmp)
                    res.add(sub + " " + x);
                if (tmp.size() == 0 && i == s.length())
                    res.add(sub);
            }
        }
        return res;
    }

    List<String> wordBreakIIWithBackTracking(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        List<List<String>> result = new ArrayList<>();
        backTrackFindNextWord(result, new ArrayList<>(), s, set, 0);
        List<String> rt = new ArrayList<>();
        for (List<String> l : result) {
            StringBuilder sb = new StringBuilder();
            for (String str : l) {
                if (sb.length() > 0)
                    sb.append(" ");
                sb.append(str);
            }
            rt.add(sb.toString());
        }
        return rt;
    }

    void backTrackFindNextWord(List<List<String>> result, List<String> tempList, String s, Set<String> wordDict, int start) {
        if (s.length() == start) {
            result.add(new ArrayList<>(tempList));
        }
        for (int i = start; i < s.length(); i++) {
            String word = s.substring(start, i + 1);
            if (wordDict.contains(word)) {
                tempList.add(word);
                backTrackFindNextWord(result, tempList, s, wordDict, i + 1);
                tempList.remove(tempList.size() - 1);
            }
        }

    }


    // Cracking the Safe
    // Return any string of minimum length that will unlock the safe at some point of entering it.
    // https://leetcode.com/problems/cracking-the-safe

    // Input: n = 1, k = 2 == > Output: "10"
    //Explanation: The password is a single digit, so enter each digit. "01" would also unlock the safe.

    // Input: n = 2, k = 2 ==> Output: "01100"
    //Explanation: For each possible password:
    //- "00" is typed in starting from the 4th digit.
    //- "01" is typed in starting from the 1st digit.
    //- "10" is typed in starting from the 3rd digit.
    //- "11" is typed in starting from the 2nd digit.
    //Thus "01100" will unlock the safe. "10011", and "11001" would also unlock the safe.
    String crackSafe(int n, int k) {
        int target = (int) (Math.pow(n, k)); // permutations
        StringBuilder result = new StringBuilder();
        Random ran = new Random();
        for (int i = 0; i < n; i++) {
            result.append(ran.nextInt(k));
        }

        Set<String> visited = new HashSet<>();
        visited.add(result.toString());
        dfsCrackSafe(result, target, n, k, visited);
        return result.toString();
    }

    boolean dfsCrackSafe(StringBuilder result, int target, int n, int k, Set<String> visited) {
        // Base case...
        if (visited.size() == target)
            return true;

        String prev = result.substring(result.length() - n + 1, result.length());
        for (int i = 0; i < k; i++) {
            String next = prev + i;
            if (!visited.contains(next)) {
                visited.add(next);
                result.append(i);
                // checking to safe crack
                if (dfsCrackSafe(result, target, n, k, visited))
                    return true;
                else {
                    // reset
                    visited.remove(next);
                    result.delete(result.length() - 1, result.length());
                }
            }
        }

        return false;
    }

    // Longest Increasing Path in a Matrix
    // Given an m x n integers matrix, return the length of the longest increasing path in matrix.
    // input [[9,9,4],[6,6,8],[2,1,1]] ==> output 4

    int longestIncreasingPath(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        int[][] dp = new int[rows][cols];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (dp[row][col] == -1)
                    dfsLongestIncreaseingPath(row, col, -1, matrix, dp);
            }
        }

        int maxPath = Integer.MIN_VALUE;
        for (int[] d : dp) {
            maxPath = Math.max(maxPath, Arrays.stream(d).max().getAsInt());
        }

        return maxPath;

    }

    int dfsLongestIncreaseingPath(int row, int col, int target, int[][] matrix, int[][] dp) {
        // base case...
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length || matrix[row][col] <= target)
            return 0;
        if (dp[row][col] != -1)
            return dp[row][col];

        target = dp[row][col];
        int up = dfsLongestIncreaseingPath(row - 1, col, target, matrix, dp); // up
        int down = dfsLongestIncreaseingPath(row + 1, col, target, matrix, dp); // dow
        int left = dfsLongestIncreaseingPath(row, col - 1, target, matrix, dp); // left
        int right = dfsLongestIncreaseingPath(row, col + 1, target, matrix, dp); // right

        dp[row][col] = 1 + Math.max(Math.max(left, right), Math.max(up, down));
        return dp[row][col];
    }


}
