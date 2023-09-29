package blindsolutions.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Solution {

    public static void main(String[] args) {
        Solution s1 = new Solution();
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

    // Longest Palindrome in a string
    String longestPalindromeWithDp(String s) {
        int len = s.length();
        String ans = "";
        int max = 0;
        boolean[][] dp = new boolean[len][len];
        for (int j = 0; j < len; j++) {
            for (int i = 0; i <= j; i++) {
                boolean judge = s.charAt(i) == s.charAt(j);
                dp[i][j] = j - i > 2 ? dp[i + 1][j + 1] && judge : judge;

                if (dp[i][j] && j - i + 1 > max) {
                    max = j - i + 1;
                    ans = s.substring(i, j + 1);
                }
            }
        }
        return ans;
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
}
