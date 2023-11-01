package grokkingpattern.combine.slidewindow;

import java.util.HashMap;

public class LongestSubstringWithKdistinct {

    public static void main(String[] args) {
    /*System.out.println("Longest Substring length with K distinct  " + longestSubstringLenWithKdistinct("araaci", 2));
    System.out.println("Longest Substring length with K distinct  " + longestSubstringLenWithKdistinct("araaci", 1));
    System.out.println("Longest Substring length with K distinct  " + longestSubstringLenWithKdistinct("cbbebi", 3));
    System.out.println("Longest Substring length with 2 distinct  " + longestSubstringLenWith2distinct("cbbebi"));
    System.out.println("Longest Substring length with 2 distinct  " + longestSubstringLenWith2distinct("ccaabbb"));
    System.out.println("Longest Substring with no repeat in String  " + longestSubstringNoRepeat("aabccbb"));
    System.out.println("Longest Substring with no repeat in String  " + longestSubstringNoRepeat("abbbb"));
    System.out.println("Longest Substring with no repeat in String  " + longestSubstringNoRepeat("abccde"));
    System.out.println("Longest Substring with no repeat in String  " + longestSubstringNoRepeat("pwwkew"));
    System.out.println("Longest Substring with K replacement  String  " + longestSubstringNoRepeatKReplacement("aabccbb",2));
    System.out.println("Longest Substring with K replacement  String  " + longestSubstringNoRepeatKReplacement("abccde",1));
    System.out.println("Longest Substring with K replacement  String  " + longestSubstringNoRepeatKReplacement("abbcb",1));
    System.out.println("Longest Substring with K replacement  String  " + longestSubstringNoRepeatKReplacement("AABABBA",1));
    System.out.println("Longest Substring with K replacement  String  " + longestSubstringNoRepeatKReplacement("ABAB",2));
    */
        System.out.println("Longest Ones with K replacement in array  " + longestOnesAfterKReplacement(new int[]{0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1}, 2));
        System.out.println("Longest Ones with K replacement in array  " + longestOnesAfterKReplacement(new int[]{0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1}, 3));

    }

    static int longestSubstringLenWithKdistinct(String str, int k) {
        int windowStart = 0, maxLength = Integer.MIN_VALUE;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            // insert char into with count
            map.put(str.charAt(windowEnd), map.getOrDefault(str.charAt(windowEnd), 0) + 1);
            if (map.size() <= k) {
                maxLength = Math.max(maxLength, windowEnd - windowStart + 1); // storing max len
            } else {
                while (map.size() > k) {
                    char l = str.charAt(windowStart);
                    map.put(l, map.get(l) - 1);
                    if (map.get(l) == 0)
                        map.remove(l);
                    windowStart++;
                }
            }
        }
        return maxLength;
    }

    // Fruits into Baskets (medium) or Longest Substring with at most 2 distinct characters

    static int longestSubstringLenWith2distinct(String str) {
        int windowStart = 0, maxLength = Integer.MIN_VALUE;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            // insert char into with count
            map.put(str.charAt(windowEnd), map.getOrDefault(str.charAt(windowEnd), 0) + 1);
            if (map.size() <= 2) {
                maxLength = Math.max(maxLength, windowEnd - windowStart + 1); // storing max len
            } else {
                while (map.size() > 2) {
                    char l = str.charAt(windowStart);
                    map.put(l, map.get(l) - 1);
                    if (map.get(l) == 0)
                        map.remove(l);
                    windowStart++;
                }
            }
        }
        return maxLength;
    }

    static int longestSubstringNoRepeat(String str) {
        int windowStart = 0, maxLength = Integer.MIN_VALUE;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char ch = str.charAt(windowEnd);
            // char contains in map and move window start
            if (map.containsKey(ch)) {
                windowStart = Math.max(windowStart, map.get(ch) + 1);
            }
            map.put(ch, windowEnd); // insert char with index
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }
        return maxLength;
    }

    static int longestSubstringNoRepeatKReplacement(String str, int k) {
        int windowStart = 0, maxLength = Integer.MIN_VALUE, maxRepeatLetterCount = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char ch = str.charAt(windowEnd);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            maxRepeatLetterCount = Math.max(maxRepeatLetterCount, map.get(ch));
            if (windowEnd - windowStart + 1 - maxRepeatLetterCount > k) {
                map.remove(str.charAt(windowStart)); // shrink from left
                windowStart++; //  increase windowstart
            }
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }
        return maxLength;
    }

    static int longestOnesAfterKReplacement(int[] nums, int k) {
        int windowStart = 0, maxOnes = 0, maxLength = 0;
        for (int windowEnd = 0; windowEnd < nums.length; windowEnd++) {
            if (nums[windowEnd] == 1)
                maxOnes++;
            if (windowEnd - windowStart + 1 - maxOnes > k) {
                if (nums[windowStart] == 1)
                    // reduce ones
                    maxOnes--;
                windowStart++;
            }
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }
        return maxLength;
    }
}
