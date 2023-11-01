package grokkingpattern.google.slidingwindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Solutions {

    // https://leetcode.com/problems/maximum-average-subarray-i/
    //  Maximum Average Subarray I
    double findMaxAverage(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return 0.0;
        int start = 0;
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            if (right >= k - 1) {
                maxSum = Math.max(sum, maxSum);
                sum -= nums[start];
                start++;
            }
        }
        return (double) maxSum / k;

    }

    // Minimum Size Subarray Sum
    // https://leetcode.com/problems/minimum-size-subarray-sum/
    int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int minLength = Integer.MAX_VALUE;
        int start = 0, sum = 0;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= target) {
                minLength = Math.min(minLength, right - start + 1);
                sum -= nums[start++];
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    // https://leetcode.com/problems/fruit-into-baskets/
    //Fruit Into Baskets

    int totalFruit(int[] fruits) {
        if (fruits == null || fruits.length == 0)
            return 0;
        HashMap<Integer, Integer> fruitsMap = new HashMap<>();
        int start = 0, maxFruitsPicked = 0;
        for (int end = 0; end < fruits.length; end++) {
            fruitsMap.put(fruits[end], fruitsMap.getOrDefault(fruits[end], 0) + 1);
            while (fruitsMap.size() > 2) {
                fruitsMap.put(fruits[start], fruitsMap.get(fruits[start]) - 1);
                if (fruitsMap.get(fruits[start]) == 0)
                    fruitsMap.remove(fruits[start]);
                start++;
            }
            maxFruitsPicked = Math.max(maxFruitsPicked, end - start + 1);
        }
        return maxFruitsPicked;
    }

    // Longest Substring Without Repeating Characters
    // https://leetcode.com/problems/longest-substring-without-repeating-characters/
    int lengthOfLongestWithMap(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0;
        int ans = 0;
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            if (map.containsKey(ch)) {
                left = Math.max(left, map.get(ch) + 1);
            }
            map.put(ch, right);
            ans = Math.max(right - left + 1, ans);
        }

        return ans;
    }

    int lengthOfLongestWithArray(String s) {
        int[] charArr = new int[128]; //alpha numeric value 128.
        Arrays.fill(charArr, -1);
        int left = 0;
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if (charArr[s.charAt(i)] >= left) {
                left = charArr[s.charAt(i)] + 1;
            }
            charArr[s.charAt(i)] = i;
            ans = Math.max(ans, i - left + 1);
        }

        return ans;
    }

    // https://leetcode.com/problems/longest-repeating-character-replacement/
    // Longest Repeating Character Replacement

    int characterReplacement(String s, int k) {
        int[] freqMap = new int[26]; // Assuming Alpha bits chars only
        int left = 0, maxFrequency = Integer.MIN_VALUE, maxLength = Integer.MIN_VALUE;
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            maxFrequency = Math.max(maxFrequency, ++freqMap[ch - 'A']);
            while (right - left + 1 - maxFrequency > k) {
                freqMap[s.charAt(left) - 'A']--;
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength == Integer.MIN_VALUE ? 0 : maxLength;
    }

    // https://leetcode.com/problems/max-consecutive-ones-iii/
    //Max Consecutive Ones III

    int longestOnes(int[] nums, int k) {
        int left = 0, maxOnes = 0, maxLength = 0;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 1)
                maxOnes++;
            if (right - left + 1 - maxOnes > k) {
                if (nums[left] == 1)
                    --maxOnes;
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    // https://leetcode.com/problems/permutation-in-string/
    //  return true if one of s1's permutations is the substring of s2.
    boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;
        int[] source = new int[26];
        int[] dest = new int[26];
        for (char ch : s1.toCharArray())
            source[ch - 'a']++;
        for (int i = 0; i < s2.length(); i++) {
            dest[s2.charAt(i) - 'a']++;
            if (i >= s1.length()) {
                // remove left most chars from window...
                dest[s2.charAt(i - s1.length()) - 'a']--;
            }
            if (Arrays.equals(source, dest)) {
                return true;
            }
        }
        return false;
    }

    // https://leetcode.com/problems/find-all-anagrams-in-a-string/
    // Find All Anagrams in a String

    List<Integer> findAnagrams(String s, String p) {
        int n = s.length(), m = p.length();
        int[] l = new int[26];
        int[] k = new int[26];
        for (char c : p.toCharArray())
            k[c - 'a']++;
        int i = 0, j = 0;
        List<Integer> result = new ArrayList<>();
        while (j < n) {
            int c = s.charAt(j) - 'a';
            l[c]++;
            if (j - i + 1 < m)
                j++;
            else {
                if (Arrays.equals(k, l))
                    result.add(i);
                l[s.charAt(i) - 'a']--;
                i++;
                j++;
            }
        }
        return result;
    }

    // https://leetcode.com/problems/minimum-window-substring/
    // Minimum Window Substring

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

    String minWindowWithMap(String s, String t) {
        HashMap<Character, Integer> tagetFrequenceMap = new HashMap<>();
        for (char ch : t.toCharArray()) {
            tagetFrequenceMap.put(ch, tagetFrequenceMap.getOrDefault(ch, 0) + 1);
        }

        int required = tagetFrequenceMap.size(), left = 0, leftIndex = -1;
        int minLength = Integer.MAX_VALUE;
        HashMap<Character, Integer> windowMap = new HashMap<>();
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            windowMap.put(ch, windowMap.getOrDefault(ch, 0) + 1);
            if (tagetFrequenceMap.containsKey(ch) && tagetFrequenceMap.get(ch).intValue() == windowMap.get(ch).intValue()) {
                required--;
            }
            while (required == 0) {
                if (minLength > right - left + 1) {
                    minLength = right - left + 1;
                    leftIndex = left;
                }
                char ch_left = s.charAt(left);
                windowMap.put(ch_left, windowMap.getOrDefault(ch_left, 0) - 1);
                if (tagetFrequenceMap.containsKey(ch_left) && tagetFrequenceMap.get(ch_left).intValue() > windowMap.get(ch_left).intValue()) {
                    required++;
                }
                left++;
            }
        }
        return minLength == Integer.MAX_VALUE ? "" : s.substring(leftIndex, leftIndex + minLength);
    }

    // https://leetcode.com/problems/substring-with-concatenation-of-all-words
    //Substring with Concatenation of All Words

    List<Integer> findSubstring(String s, String[] words) {
        List<Integer> list = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for(String w: words)
            map.put(w, map.getOrDefault(w,0)+1);

        int wordSize = words[0].length();
        int windowSize = words.length* wordSize;

        for(int i=0; i<=s.length()-windowSize; i++) {
            HashMap<String, Integer> trackMap = new HashMap<>(map);
            int left = i;
            int match =0;
            for(int right = i; right<=i+windowSize-wordSize; right +=wordSize) {
                String rightWord = s.substring(right, right + wordSize);
                if (trackMap.containsKey(rightWord)) {
                    trackMap.put(rightWord, trackMap.get(rightWord) - 1);
                    if (trackMap.get(rightWord) == 0) match++;
                }
                if(match == trackMap.size()) {
                    list.add(left);
                    break;
                }
            }
        }
        return list;
    }
}
