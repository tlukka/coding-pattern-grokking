package blindpatternsolutions;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.TreeSet;

public class StringSolutions {

    public static void main(String[] args) {
        StringSolutions s1 = new StringSolutions();
        System.out.println(s1.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(s1.lengthOfLongestSubstring("bbbbb"));
        System.out.println(s1.lengthOfLongestSubstring("pwwkew"));
        System.out.println(s1.characterReplacement("ABAB", 2));
        System.out.println(s1.characterReplacement("AABABBA", 1));
        System.out.println(s1.minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(s1.minWindow("a", "a"));
        System.out.println(s1.minWindow("a", "aa"));
        List<List<String>> validAnagrams = s1.groupAnagram(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
        for (List<String> anagram : validAnagrams) {
            System.out.println(Arrays.toString(anagram.stream().toArray()));
        }

        System.out.println(s1.isValidParentheses("()[]{}"));
    }

    //659 · Encode and Decode Strings
    //  implement encode and decode


    // Palindromic Substrings
    int countSubstringsPalindrome(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count += findPalindromes(s, i, i);
            count += findPalindromes(s, i, i + 1);
        }
        return count;
    }

    int findPalindromes(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
        return count;
    }

    int start = 0, end = 0;
    String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        for (int i = 0; i < s.length(); i++) {
            expandAroundCenter(s, i, i);
            expandAroundCenter(s, i, i + 1);
        }
        return s.substring(start, end + 1);
    }

    void expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        left++;
        right++;
        if (end - start + 1 < right - left + 1) {
            start = left;
            end = right;
        }
    }


    // Longest Palindrome in a string
    String longestPalindromeWithDp(String s) {
        int len = s.length();
        String ans = "";
        int max = 0;
        boolean[][] dp = new boolean[len][len];
        for (int j = 0; j < len; j++) {
            for (int i = 0; i <= j; i++) {
                boolean isSameChar = s.charAt(i) == s.charAt(j);
                dp[i][j] = j - i > 2 ? dp[i + 1][j + 1] && isSameChar : isSameChar;

                if (dp[i][j] && j - i + 1 > max) {
                    max = j - i + 1;
                    ans = s.substring(i, j + 1);
                }
            }
        }
        return ans;
    }


    boolean isValidPalindrome(String s1) {
        if (s1 == null)
            return false;
        int p1 = 0, p2 = s1.length() - 1;
        while (p1 <= p2) {
            char c1 = s1.charAt(p1), c2 = s1.charAt(p2);
            if (Character.isLetterOrDigit(c1))
                p1++;
            else if (Character.isLetterOrDigit(c2))
                p2++;
            else {
                if (Character.toLowerCase(c1) != Character.toLowerCase(c2))
                    return false;
                p1++;
                p2--;
            }
        }

        return true;
    }

    // Valid Parentheses
    boolean isValidParentheses(String str) {
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
        Stack<Character> stack = new Stack<>();
        for (char c : str.toCharArray()) {
            if (!map.containsKey(c) && !stack.isEmpty()) {
                char popChar = stack.pop();
                if (popChar != map.get(c)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    // Group of Anagram
    List<List<String>> groupAnagram(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] ch_arr = str.toCharArray();
            Arrays.sort(ch_arr);
            String sortStr = new String(ch_arr);
            if (!map.containsKey(sortStr)) {
                map.put(sortStr, new ArrayList<>());
            }
            map.get(sortStr).add(str);
        }
        return new ArrayList<>(map.values());

    }

    // Valid Anagram
    boolean isValidAnagram(String s, String t) {
        if (s == null && t == null)
            return true;
        if (s.length() != t.length())
            return false;
        int[] map = new int[26]; // alpha bits length
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i) - 'a']++;
            map[t.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (map[i] != 0)
                return false;
        }
        return true;
    }
    //Minimum Window Substring

    String minWindow(String s, String t) {
        if (s == null || s.length() == 0) return "";
        HashMap<Character, Integer> frequencyMap = new HashMap<>();
        for (char ch : t.toCharArray()) {
            frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0) + 1);
        }
        HashMap<Character, Integer> windowMap = new HashMap<>();
        int count = 0, required = frequencyMap.size(), left = 0, leftIndex = -1, rightIndex = -1, minLength = Integer.MAX_VALUE;
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            windowMap.put(ch, windowMap.getOrDefault(ch, 0) + 1);
            if (frequencyMap.containsKey(ch) && frequencyMap.get(ch).intValue() == windowMap.get(ch).intValue()) {
                count++;
            }
            while (count == required) {
                if (minLength > right - left + 1) {
                    minLength = right - left + 1;
                    leftIndex = left;
                    rightIndex = right;
                }
                char ch_left = s.charAt(left);
                windowMap.put(ch_left, windowMap.getOrDefault(ch_left, 0) - 1);
                if (frequencyMap.containsKey(ch_left) && frequencyMap.get(ch_left).intValue() > windowMap.get(ch_left).intValue()) {
                    count--;
                }
                left++;
            }

        }

        if (leftIndex == -1 || rightIndex == -1) return "";
        return s.substring(leftIndex, rightIndex + 1);
    }

    String minWindowWithArray(String s, String t) {
        int[] map = new int[128];
        for (char c : t.toCharArray())
            map[c]++;

        int begin = 0, left = 0, minLen = Integer.MAX_VALUE, count = t.length();
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            map[c]--;
            if (map[c] >= 0)
                count--;

            while (count == 0) {
                char leftc = s.charAt(left);
                map[leftc]++;
                if (map[leftc] > 0) {
                    if (right - left + 1 < minLen) {
                        begin = left;
                        minLen = right - left + 1;
                    }
                    count++;
                }
                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(begin, begin + minLen);
    }

    // https://leetcode.com/problems/sliding-window-maximum
    // Return the max sliding window.
    int[] maxSlidWithQueue(int[] nums, int k) {
        Deque<Integer> q = new ArrayDeque<Integer>();
        int[] res = new int[nums.length - k + 1];
        int c = 0;
        for (int j = 0; j < nums.length; j++) {
            while (!q.isEmpty() && nums[q.getLast()] <= nums[j]) {
                q.removeLast();
            }
            q.addLast(j);
            if (q.getFirst() == j - k) {
                q.removeFirst();
            }
            if (j >= k - 1)
                res[c++] = nums[q.peek()];
        }
        return res;
    }

    int[] maxSlidingWindow(int[] nums, int k) {
        int i = 0, c = 0;
        int[] res = new int[nums.length - k + 1];
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int j = 0; j < nums.length; j++) {
            q.add(nums[j]);
            if (j - i + 1 > k) {
                map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
                i++;
            }

            while (map.containsKey(q.peek())) {
                int cur = q.poll();
                map.put(cur, map.get(cur) - 1);
                if (map.get(cur) == 0)
                    map.remove(cur);
            }

            if (j - i + 1 == k)
                res[c++] = q.peek();
        }

        return res;

    }

    // Longest Substring Without Repeating Characters
    int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int len = s.length();
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0;
        int ans = 0;
        for (int right = 0; right < len; right++) {
            char ch = s.charAt(right);
            if (map.containsKey(ch)) {
                left = Math.max(left, map.get(ch) + 1);
            }
            map.put(ch, right);
            ans = Math.max(right - left + 1, ans);
        }
        return ans;
    }

    int characterReplacement(String s, int k) {
        int left = 0, maxLength = Integer.MIN_VALUE, maxFreq = Integer.MIN_VALUE;
        int[] frequency = new int[26]; // English characters
        for (int right = 0; right < s.length(); right++) {
            int index = s.charAt(right) - 'A';
            ++frequency[index];
            maxFreq = Math.max(maxFreq, frequency[index]);
            while (right - left + 1 - maxFreq > k) {
                --frequency[s.charAt(left) - 'A'];
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength == Integer.MIN_VALUE ? 0 : maxLength;
    }


    int[] closestStores(int[] houses, int[] stores) {
        Arrays.sort(stores);
        int[] result = new int[houses.length];
        Arrays.fill(result, Integer.MAX_VALUE);
        for (int i = 0; i < houses.length; i++) {
            int lo = 0;
            int hi = stores.length - 1;
            while (lo <= hi) {
                int middle = (lo + hi) / 2;
                if (result[i] == Integer.MAX_VALUE || Math.abs(stores[middle] - houses[i]) < Math.abs(result[i] - houses[i]))
                    result[i] = stores[middle];
                if (houses[i] < stores[middle]) hi = middle - 1;
                else lo = middle + 1;
            }
        }
        return result;
    }

    int[] nearestStores(int[] houses, int[] stores) {
        TreeSet<Integer> treeset = new TreeSet<>();
        int lenH = houses.length;
        if (stores.length == 0) return new int[0];
        for (int store : stores)
            treeset.add(store);

        int[] res = new int[lenH];
        for (int i = 0; i < lenH; ++i) {
            int val = houses[i];
            Integer left = treeset.floor(val), right = treeset.ceiling(val);
            if (left == null || right == null)
                res[i] = left == null ? right : left;
            else
                res[i] = (val - left <= right - val) ? left : right;
        }
        return res;
    }


    // Given an int array nums of length n. Split it into strictly decreasing subsequences.
    // Output the min number of subsequences you can get by splitting.
    int decreasingSubSeq(int[] arr) {
        int[] piles = new int[arr.length];
        int size = 0;
        for (int num : arr) {
            int l = 0, r = size;
            while (l < r) {
                int mid = (l + r) / 2;
                if (num >= piles[mid])
                    l = mid + 1;
                else
                    r = mid;
            }

            piles[l] = num;

            if (l == size)
                size++;
        }

        return size;

    }

    // There are n guests who are invited to a party. The k-th guest will
    // attend the party at time S[k] and leave the party at time E[k].
    //Given an integer array S and an integer array E, both of length n,
    // return an integer denoting the minimum number of chairs you need such that everyone attending the party can sit down.

    int minChairs(int[] S, int[] E) {
        Arrays.sort(S);
        Arrays.sort(E);

        int i = 0, j = 0, chairs = 1, count = 0;
        while (i < S.length) {
            if (S[i] < E[j]) {
                chairs = Math.max(chairs, count++);
                i++;
            } else {
                count--;
                j++;
            }
        }

        return chairs;
    }
}
