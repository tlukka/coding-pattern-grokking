package grokkingpattern.combine.slidewindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PermutationPatternExits {

  public static void main(String[] args) {
    /*System.out.println("Permutation of string exists or not " +isPatternPermutation("oidbcaf", "abc"));
    System.out.println("Permutation of string exists or not " +isPatternPermutation("odicf", "dc"));
    System.out.println("Permutation of string exists or not " +isPatternPermutation("bcdxabcdy", "bcdxabcdy"));
    System.out.println("Permutation of string exists or not " +isPatternPermutation("aaacb", "abc"));
    System.out.println("Permutation of string exists or not " +isPatternPermutation("eidbaooo", "ab"));
    System.out.println("Permutation of string exists or not " +isPatternPermutation("eidboaoo", "ab"));
    System.out.println("Permutation of string exists or not " +isPatternPermutation("ccccbbbbaaaa", "abc"));


    System.out.println("Permutation of string exists in Index " +findStringAnagrams("cbaebabacd", "abc").stream().collect(
        Collectors.toList()));
    System.out.println("Permutation of string exists in Index " +findStringAnagrams("abab", "ab").stream().collect(
        Collectors.toList()));
    System.out.println("Permutation of string exists in Index " +findStringAnagrams("abbcabc", "abc").stream().collect(
        Collectors.toList())); */

    System.out.println("Permutation of string match sub string " +findMinSubstringWithPatternMatch("aabdec", "abc"));
    System.out.println("Permutation of string match sub string " +findMinSubstringWithPatternMatch("abdbca", "abc"));
    System.out.println("Permutation of string match sub string " +findMinSubstringWithPatternMatch("adcad", "abc"));

  }

  //https://leetcode.com/problems/permutation-in-string/
  static boolean isPatternPermutation(String str, String pattern) {
    int windowStart =0, isMatch = 0;
    HashMap<Character, Integer> map = new HashMap<>();
    for (char c: pattern.toCharArray()) {
      // insertting partten in map
      map.put(c, map.getOrDefault(c,0) +1);
    }
    // math all the characters from map with the current window
    for(int windowEnd=0; windowEnd<str.length(); windowEnd++) {
     char ch = str.charAt(windowEnd);
     if(map.containsKey(ch)) {
       // decrement frequency of matched char
       map.put(ch, map.get(ch)-1);
       if(map.get(ch) == 0) {
         isMatch++;
       }
     }
     if(isMatch == map.size()) {
       //number of characters matched is equal to the number of distinct characters in the pattern
       return true;
     }
      //shrink the sliding window
      if(windowEnd>=pattern.length()-1) {
       char chStart = str.charAt(windowStart++);
       if(map.containsKey(chStart)) {
          if(map.get(chStart) == 0) {
            isMatch--;
          }
          map.put(chStart, map.get(chStart) +1);
       }
      }
    }
    return false;
  }


  static List<Integer> findStringAnagrams(String str, String pattern) {
    int windowStart =0, isMatch = 0;
    HashMap<Character, Integer> map = new HashMap<>();
    List<Integer> result = new ArrayList<>();
    for (char c: pattern.toCharArray()) {
      // insertting partten in map
      map.put(c, map.getOrDefault(c,0) +1);
    }
    // math all the characters from map with the current window
    for(int windowEnd=0; windowEnd<str.length(); windowEnd++) {
      char ch = str.charAt(windowEnd);
      if(map.containsKey(ch)) {
        // decrement frequency of matched char
        map.put(ch, map.get(ch)-1);
        if(map.get(ch) == 0) {
          isMatch++;
        }
      }
      if(isMatch == map.size()) {
        //number of characters matched is equal to the number of distinct characters in the pattern
        result.add(windowStart);
      }
      //shrink the sliding window
      if(windowEnd>=pattern.length()-1) {
        char chStart = str.charAt(windowStart++);
        if(map.containsKey(chStart)) {
          if(map.get(chStart) == 0) {
            isMatch--;
          }
          map.put(chStart, map.get(chStart) +1);
        }
      }
    }
    return result;
  }

  static String findMinSubstringWithPatternMatch(String str, String pattern) {
    int windowStart = 0, subStrwindowtart = 0, minLength = str.length() + 1, matched = 0;
    HashMap<Character, Integer> map = new HashMap<>();
    // Inserting map in pattern
    for (char ch : pattern.toCharArray()) {
      map.put(ch, map.getOrDefault(ch, 0) + 1);
    }

    for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
      char ch = str.charAt(windowEnd);
      if (map.containsKey(ch)) {
        map.put(ch, map.get(ch) - 1);
        if (map.get(ch) >= 0) {
          //count every matching of a character
          matched++;
        }
      }

      while (matched == pattern.length()) {
        if (minLength > windowEnd - windowStart + 1) {
          minLength = windowEnd - windowStart + 1;
          subStrwindowtart = windowStart;
        }
        char stChar = str.charAt(windowStart++);
        if (map.containsKey(stChar)) {
          if (map.get(stChar) == 0) {
            matched--;
          }
          map.put(stChar, map.get(stChar) + 1);
        }
      }
    }
    if (minLength > str.length()) {
      return " ";
    }
    return str.substring(subStrwindowtart, subStrwindowtart + minLength);
  }
}
