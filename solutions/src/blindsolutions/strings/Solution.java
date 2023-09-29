package blindsolutions.strings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    }

    // Group of Anagram
    List<String> groupAnagram(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

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
