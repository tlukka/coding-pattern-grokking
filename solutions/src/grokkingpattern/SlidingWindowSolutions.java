package grokkingpattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class SlidingWindowSolutions {
    public static void main(String[] args) {
        SlidingWindowSolutions sol = new SlidingWindowSolutions();
        /*
        System.out.println(sol.findMaxAverage(new int[]{1, 12, -5, -6, 50, 3}, 4));
        System.out.println(sol.findMaxAverage(new int[]{-1}, 1));
        System.out.println(sol.maxSumSubArray(new int[]{1, 4, 2, 10, 2, 3, 1, 0, 20}, 4));
        System.out.println(sol.minSubArrayLen(11, new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1}));



        System.out.println(sol.longestkSubstr("aabb", 1));
        System.out.println(sol.longestkSubstr("aabb", 2));
        System.out.println(sol.longestkSubstr("araaci", 2));
        System.out.println(sol.longestkSubstr("cbbebi", 3));
        System.out.println(sol.longestSubstringWithKdistinct("aabb", 1));
        System.out.println(sol.longestSubstringWithKdistinct("aabb", 2));
        System.out.println(sol.longestSubstringWithKdistinct("araaci", 2));
        System.out.println(sol.longestSubstringWithKdistinct("cbbebi", 3));


        //System.out.println(sol.longestkSubstr("aabacbebebe", 3));
        //System.out.println(sol.longestkSubstr("aaabbb", 3));

        // abcbdbdbbdcdabd

        System.out.println(sol.findLengthOfLongestSubstringWithKUniqueCharacters("aabacbebebe", 3));
        System.out.println(sol.findLongestSubstringKUniqueCharacters("aabacbebebe", 3));
        System.out.println(sol.findLengthOfLongestSubstringWithKUniqueCharacters("abcbdbdbbdcdabd", 3));
        System.out.println(sol.findLongestSubstringKUniqueCharacters("abcbdbdbbdcdabd", 3));
        System.out.println(sol.findLengthOfLongestSubstringWithKUniqueCharacters("abcbdbdbbdcdabd", 5));
        System.out.println(sol.findLongestSubstringKUniqueCharacters("abcbdbdbbdcdabd", 5));
        //System.out.println(sol.longestSubstringWithKdistinct("aaabbb", 3));


        System.out.println(sol.totalFruit(new int[]{1, 2, 1}));
        System.out.println(sol.totalFruit(new int[]{0, 1, 2, 2}));
        System.out.println(sol.totalFruit(new int[]{1, 2, 3, 2, 2}));

        System.out.println(sol.totalFruit(new int[]{3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4}));


        System.out.println(sol.lengthOfLongestSubstringWith2DistinctChars("ccaabbb"));
        System.out.println(sol.lengthOfLongestSubstringWith2DistinctChars("eceba"));


        System.out.println(sol.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(sol.lengthOfLongestSubstring("bbb"));
        System.out.println(sol.lengthOfLongestSubstring("pwwkew"));


        System.out.println(sol.lengthOfLongestSubstringWithOutSpace("abcabcbb"));
        System.out.println(sol.lengthOfLongestSubstringWithOutSpace("bbb"));
        System.out.println(sol.lengthOfLongestSubstringWithOutSpace("pwwkew"));



        System.out.println(sol.characterReplacement("ABAB", 2));
        System.out.println(sol.characterReplacement("AABABBA", 1));


        System.out.println(sol.checkInclusion("ab", "eidbaooo"));



        System.out.println(Arrays.toString(sol.findAnagrams("ppqp", "pq").stream().toArray()));
        System.out.println(Arrays.toString(sol.findAnagrams("cbaebabacd", "abc").stream().toArray()));

         */
        System.out.println(sol.minWindow("abdbca", "abc"));
        System.out.println(sol.minWindow("a", "aa"));
        System.out.println(sol.minWindow("adcad", "abc"));
    }

    // Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value
    // Input: nums = [1,12,-5,-6,50,3], k = 4 ===> Output: 12.75000
    double findMaxAverage(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0.0;
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

    // Given an array nums and a target value k, find the maximum length of a subarray that sums to k
    // nums = [1, -1, 5, -2, 3], k = 3

    int maxSumSubArray(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length < k) return -1;
        int sum = 0, maxSum = 0, start = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (i >= k - 1) {
                maxSum = Math.max(sum, maxSum);
                sum -= nums[start++];
            }
        }
        return maxSum;
    }

    // Given an array of positive integers nums and a positive integer target, return the minimal length of a
    //subarray  whose sum is greater than or equal to target
    // Input: target = 7, nums = [2,3,1,2,4,3] == >Output: 2
    // Input: target = 11, nums = [1,1,1,1,1,1,1,1] ==> Output: 0
    int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
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

    //Find the longest substring with k unique characters in a given string
    //Input: Str = “aabbcc”, k = 1 => Output: 2
    // Input: Str = “aabbcc”, k = 2 ==> Output: 4
    int longestkSubstr(String S, int k) {
        HashMap<Character, Integer> freqMap = new HashMap<>();
        for (char ch : S.toCharArray())
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        PriorityQueue<Character> minHeap = new PriorityQueue<>((a, b) -> freqMap.get(b) - freqMap.get(a));
        minHeap.addAll(freqMap.keySet());
        int longestKSubString = 0;
        if (minHeap.size() < k)
            return longestKSubString;

        while (k-- > 0) {
            longestKSubString += freqMap.get(minHeap.poll());
        }
        return longestKSubString;
    }

    int longestSubstringWithKdistinct(String S, int k) {
        HashMap<Character, Integer> freqMap = new HashMap<>();
        int start = 0, maxLength = 0;
        for (int end = 0; end < S.length(); end++) {
            char ch = S.charAt(end);
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
            while (freqMap.size() > k) {
                char ch_start = S.charAt(start);
                freqMap.put(ch_start, freqMap.get(ch_start) - 1);
                if (freqMap.get(ch_start) == 0)
                    freqMap.remove(ch_start);
                start++;
            }
            if (freqMap.size() == k) {
                maxLength = Math.max(maxLength, end - start + 1);
            }
        }
        return maxLength;
    }


    String findLongestSubstringKUniqueCharacters(String str, int k) {
        if (str == null || str.length() == 0) {
            return str;
        }

        // stores the longest substring boundaries
        int end = 0, begin = 0;

        // set to store distinct characters in a window
        Set<Character> window = new HashSet<>();

        // Count array `freq` stores the frequency of characters present in the
        // current window. We can also use a map instead of a count array.
        int[] freq = new int[128];

        // `[low…high]` maintains the sliding window boundaries
        for (int low = 0, high = 0; high < str.length(); high++) {
            window.add(str.charAt(high));
            freq[str.charAt(high)]++;
            // if the window size is more than `k`, remove characters from the left
            while (window.size() > k) {
                if (--freq[str.charAt(low)] == 0)
                    window.remove(str.charAt(low));
                low++;        // reduce window size
            }
            // update the maximum window size if necessary
            if (end - begin < high - low) {
                end = high;
                begin = low;
            }
        }
        return str.substring(begin, end + 1);
    }

    //Given the integer array fruits, return the maximum number of fruits you can pick.

    int totalFruit(int[] fruits) {
        if (fruits == null || fruits.length == 0) return 0;
        HashMap<Integer, Integer> fruitsMap = new HashMap<>();
        int start = 0, maxFruitPicked = 0;
        for (int end = 0; end < fruits.length; end++) {
            fruitsMap.put(fruits[end], fruitsMap.getOrDefault(fruits[end], 0) + 1);
            while (fruitsMap.size() > 2) {
                Integer startFruit = fruits[start];
                fruitsMap.put(fruits[start], fruitsMap.get(startFruit) - 1);
                if (fruitsMap.get(startFruit) == 0)
                    fruitsMap.remove(startFruit);
                start++;
            }
            maxFruitPicked = Math.max(maxFruitPicked, end - start + 1);
        }
        return maxFruitPicked;
    }

    //Longest Substring with At Most Two Distinct Characters
    // Input Str = ccaabbb ==> output : 5
    int lengthOfLongestSubstringWith2DistinctChars(String s) {
        if (s == null || s.length() == 0) return 0;

        HashMap<Character, Integer> map = new HashMap<>();
        int maxLength = 0, start = 0;
        for (int end = 0; end < s.length(); end++) {
            map.put(s.charAt(end), map.getOrDefault(s.charAt(end), 0) + 1);
            while (map.size() > 2) {
                map.put(s.charAt(start), map.get(s.charAt(start)) - 1);
                if (map.get(s.charAt(start)) == 0)
                    map.remove(s.charAt(start));
                start++;
            }
            maxLength = Math.max(maxLength, end - start + 1);
        }
        return maxLength;
    }

    //Given a string s, find the length of the longest  substring without repeating characters.
    // Input: "abcabcbb" and output is : 3

    int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        int left = 0, maxLength = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            if (map.containsKey(ch)) {
                left = Math.max(left, map.get(ch) + 1); // getting index of previous know character
            }
            map.put(ch, right); // inserting character with right index;
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    int lengthOfLongestSubstringWithoutRepeatedChars(String s) {
        int maxLen = 0, l = 0;
        HashSet<Character> seen = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (!seen.contains(s.charAt(i))) {
                maxLen = Math.max(maxLen, i - l + 1);
            }
            while (seen.contains(s.charAt(i))) {
                seen.remove(s.charAt(l));
                l++;
            }
            seen.add(s.charAt(i));
        }
        return maxLen;
    }

    int lengthOfLongestSubstringWithOutSpace(String s) {
        int maxLength = 0;
        for (int right = 0, left = 0; right < s.length(); right++) {
            int index = s.indexOf(s.charAt(right), left);
            if (index != right)
                left = index + 1;
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    // Longest Repeating Character Replacement
    // You are given a string s and an integer k. You can choose any character of the string and change it to
    // any other uppercase English character. You can perform this operation at most k times.
    //Return the length of the longest substring containing the same letter you can get after performing the above operations.
    // Input: s = "ABAB", k = 2 ==> Output: 4

    int characterReplacement(String s, int k) {
        int[] freqMap = new int[26]; // Assuming Alpha bits chars only
        int left = 0, maxFrequency = Integer.MIN_VALUE, maxLength = Integer.MIN_VALUE;
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            ++freqMap[ch - 'A'];
            maxFrequency = Math.max(maxFrequency, freqMap[ch - 'A']);
            // Sliding window to get validate other chars
            while (right - left + 1 - maxFrequency > k) {
                --freqMap[s.charAt(left) - 'A'];
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength == Integer.MIN_VALUE ? 0 : maxLength;
    }

    //Given a binary array nums and an integer k, return the maximum number of consecutive 1's in
    // the array if you can flip at most k 0's.
    //Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2 ==> Output: 6
    int longestOnes(int[] nums, int k) {
        int left = 0, maxOnes = 0, maxLength = 0;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 1)
                maxOnes++;
            while (right - left + 1 - maxOnes > k) {
                if (nums[left] == 1)
                    --maxOnes;
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    int longestOnesWithZeros(int[] nums, int k) {
        // zeroesCount i.e number of zeroes in current interval
        int start = 0;
        int end = 0;
        int zeros = 0;

        while (end < nums.length) {
            if (nums[end] == 0) {
                zeros++;
            }
            end++;
            if (zeros > k) {
                if (nums[start] == 0) {
                    zeros--;
                }
                start++;
            }
        }

        return end - start;
    }

    // Given a binary array nums, return the maximum number of consecutive 1's in the array.
    // Input: nums = [1,1,0,1,1,1] ==> Output: 3

    int findMaxConsecutiveOnes(int[] nums) {
        int countOnes = 0, maxCount = 0;
        for (int n : nums) {
            if (n == 1) {
                maxCount = Math.max(maxCount, ++countOnes);
            } else {
                countOnes = 0;
            }
        }
        return maxCount;
    }

    // Permutation in String
    // Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
    // Given a string and a pattern, find out if the string contains any permutation of the pattern.
    // Input: s1 = "ab", s2 = "eidbaooo"  ==> Output: true
    // Input: s1 = "ab", s2 = "eidboaoo" ==> Output: false
    boolean checkInclusion(String s1, String s2) {
        HashMap<Character, Integer> freqMap = new HashMap<>();
        for (char ch : s1.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        // Match all chars with current window
        int left = 0, isMatch = 0;
        for (int right = 0; right < s2.length(); right++) {
            char ch = s2.charAt(right);
            if (freqMap.containsKey(ch)) {
                freqMap.put(ch, freqMap.get(ch) - 1); // decrement character count in map
                if (freqMap.get(ch) == 0) {
                    isMatch++;
                }
            }
            if (isMatch == freqMap.size())
                return true;

            // Slide window
            if (right >= s1.length() - 1) {
                char ch_left = s2.charAt(left++);
                if (freqMap.containsKey(ch_left)) {
                    if (freqMap.get(ch_left) == 0)
                        isMatch--;
                    freqMap.put(ch_left, freqMap.get(ch_left) + 1);
                }
            }
        }
        return false;
    }

    boolean checkInclusionWithArray(String s1, String s2) {
        if (s1.length() > s2.length()) return false;
        int[] source = new int[26];
        for (char ch : s1.toCharArray())
            source[ch - 'a']++;
        int[] dest = new int[26];
        for (int i = 0; i < s1.length() - 1; i++)
            dest[s2.charAt(i) - 'a']++;

        for (int i = s1.length() - 1, j = 0; i < s2.length(); i++, j++) {
            dest[s2.charAt(i) - 'a']++;
            if (Arrays.equals(source, dest))
                return true;
            dest[s2.charAt(j) - 'a']--;
        }
        return false;
    }


    // Find All Anagrams in a String
    // Given two strings s and p, return an array of all the start indices of p's anagrams in s..
    // Input: s = "cbaebabacd", p = "abc" ===> Output: [0,6]
    // Input: s = "abab", p = "ab" Output: [0,1,2]
    List<Integer> findAnagrams(String s, String p) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char ch : p.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        List<Integer> result = new ArrayList<>();
        int start = 0, matched = 0;
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) - 1); // decrement count
                if (map.get(ch) == 0)
                    matched++;
            }

            if (matched == map.size())
                result.add(start);

            // sliding window...
            if (right >= p.length() - 1) {
                char ch_start = s.charAt(start++);
                if (map.containsKey(ch_start)) {
                    if (map.get(ch_start) == 0)
                        matched--; //before putting the character back decrement the matched count
                    map.put(ch_start, map.get(ch_start) + 1);
                }
            }
        }
        return result;
    }

    // Given two strings s and t of lengths m and n respectively, return the minimum window
    //substring of s such that every character in t (including duplicates) is included in the window
    String minWindow(String s, String t) {
        if (s.length() < t.length())
            return "";
        HashMap<Character, Integer> freqMap = new HashMap<>();
        for (char ch : t.toCharArray())
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);

        int minLength = Integer.MAX_VALUE, leftIndex = -1, rightIndex = -1, count = 0, left = 0, requiredCount = freqMap.size();
        HashMap<Character, Integer> windowMap = new HashMap<>();
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            windowMap.put(ch, windowMap.getOrDefault(ch, 0) + 1);
            if (freqMap.containsKey(ch) && freqMap.get(ch).intValue() == windowMap.get(ch).intValue())
                count++;

            while (requiredCount == count) {
                if (minLength > right - left + 1) {
                    minLength = right - left + 1;
                    leftIndex = left;
                    rightIndex = right;
                }

                char ch_left = s.charAt(left);
                windowMap.put(ch_left, windowMap.getOrDefault(ch_left, 0) - 1);
                if (freqMap.containsKey(ch_left) && freqMap.get(ch_left).intValue() > windowMap.get(ch_left).intValue())
                    count--;
                left++;
            }
        }

        if (leftIndex == -1 || rightIndex == -1)
            return "";
        return s.substring(leftIndex, rightIndex + 1);

    }
}
