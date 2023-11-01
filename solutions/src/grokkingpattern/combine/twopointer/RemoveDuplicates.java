package grokkingpattern.combine.twopointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveDuplicates {

  public static void main(String[] ars) {


    System.out.println(findLengthOfShortestSubarray(new int[] {1,2,5,3,7,10,9,12}));
    System.out.println(findLengthOfShortestSubarray(new int[] {1,3,2,0,-1,7,10}));
    System.out.println(findLengthOfShortestSubarray(new int[] {1,2,5}));
    System.out.println(findLengthOfShortestSubarray(new int[] {3,2,1}));

    /*
    System.out.println(compareStrings("xy#z", "xzz#"));
    System.out.println(compareStrings("xy#z", "xyz#"));
    System.out.println(compareStrings("xp#", "xyz##"));
    System.out.println(compareStrings("xywrrmp", "xywrrmu#p"));


    List<int[]> quads = getQuads(new int[] {4,1,2,-1,1,-3}, 1);
    for (int[] quad: quads) {
      System.out.println("Quads are " + Arrays.toString(quad));
    }
    List<int[]> quads1 = getQuads(new int[] {2,0,-1,1,-2,2}, 2);
    for (int[] quad: quads1) {
      System.out.println("Quads are " + Arrays.toString(quad));
    }

    System.out.println("Dutuch problem is " + Arrays.toString(dutchFlagSort(new int [] {2, 2, 0, 1, 2, 0})));
    System.out.println("Dutuch problem is " + Arrays.toString(dutchFlagSort(new int [] {1, 0, 2, 1, 0})));


     System.out.println("1st problem");
    System.out.println("Count of Sub array with product less than k is " + findSubarraysWithProduct(new int[]{2, 5, 3, 10}, 30));
    System.out.println("Count of Sub array with product less than k is " + findSubarraysWithProduct(new int[]{8, 2, 6, 5}, 50));

    List<Integer[]> triplets1 = getAllTripletsWithSmallerSum(new int []{-1, 0, 2, 3}, 3);
    System.out.println("1st problem");

    for(Integer[] triplet: triplets1) {
      System.out.println(Arrays.toString(triplet));
    }
    //-1, 1, 2, 3, 4
    List<Integer[]> triplets2 = getAllTripletsWithSmallerSum(new int []{-1, 4, 2, 1, 3}, 5);
    System.out.println("2nd problem");
    for(Integer[] triplet: triplets2) {
      System.out.println(Arrays.toString(triplet));
    }
    List<Integer[]> triplets3 = getAllTripletsWithSmallerSum(new int []{-2,0,1,3}, 2);
    System.out.println("3rd problem");

    for(Integer[] triplet: triplets3) {
      System.out.println(Arrays.toString(triplet));
    }


    System.out.println("Triplet count sum   " + tripletCountWithSmallerSum(new int []{-1, 0, 2, 3}, 3));
    System.out.println("Triplet count sum   " + tripletCountWithSmallerSum(new int []{-1, 4, 2, 1, 3}, 5));
    System.out.println("Triplet count sum   " + tripletCountWithSmallerSum(new int []{-2,0,1,3}, 2));
    System.out.println("Triplet count sum   " + tripletCountWithSmallerSum(new int []{}, 0));
    System.out.println("Triplet count sum   " + tripletCountWithSmallerSum(new int []{0}, 0));


    List<List<Integer>> triplets = findTriplets(new int[] {-3, 0, 1, 2, -1, 1, -2});
    for(List triplet: triplets) {
      System.out.println(triplet.stream().collect(Collectors.toList()));
    }

    List<List<Integer>> triplets1 = findTriplets(new int[] {-5, 2, -1, -2, 3});
    for(List triplet: triplets1) {
      System.out.println(triplet.stream().collect(Collectors.toList()));
    }
   System.out.println(removeDupsByTwoPointApporch(new int []{2, 3, 3, 3, 6, 9, 9}));
    System.out.println(removeDupsByTwoPointApporch(new int []{2, 2, 2, 11}));


    System.out.println("Square Array is  " + Arrays.toString(squareSortArray(new int []{-2, -1, 0, 2, 3})));
    System.out.println("Square Array is  " + Arrays.toString(squareSortArray(new int []{-3, -2, -1, 0, 2, 3})));

    System.out.println("Target Sum indices are " + Arrays.toString(pairWithTargetSum(new int []{1, 2, 3, 4, 6},6)));
    System.out.println("Target Sum indices are " + Arrays.toString(pairWithTargetSum(new int []{2, 5, 9, 11},11)));

    System.out.println(removeElementKey(new int []{2, 3, 3, 3, 6, 9, 9}, 9));
    System.out.println(removeElementKey(new int []{2, 2, 2, 11}, 2)); */

  }

  static int findLengthOfShortestSubarray(int [] nums) {
   int low =0, high = nums.length-1, len=nums.length-1;
   while (low<len && nums[low]<nums[low+1]) {
     low++;
   }
    if(low==len) {
      // array already sorted
      return 0;
    }
   while (high>0 && nums[high] >= nums[high-1]) {
     high--;
   }
   int subArrayMax = Integer.MIN_VALUE;
   int subArrayMin = Integer.MAX_VALUE;

   for(int k= low; k<high+1; k++) {
     subArrayMax = Math.max(subArrayMax, nums[k]);
     subArrayMin = Math.min(subArrayMin, nums[k]);
   }

   //extend the subarray to include any number which is bigger than the minumum of the subarray
    while (low>0 && nums[low-1] > subArrayMin) {
      low--;
    }

    while (high<len && nums[high+1]<subArrayMax) {
      high++;
    }
   return high-low+1;
  }

  static boolean compareStrings(String str1, String str2) {
    int p1= str1.length()-1, p2 = str2.length()-1;
    while (p1 >=0 || p2>=0) {
      int i= getNextCharIndex(str1, p1);
      int j = getNextCharIndex(str2,p2);
      if(i<0 && j<0) {
        return true;
      }
      if(i<0 || j<0) {
        return false;
      }
      if(str1.charAt(i) != str2.charAt(j)) {
        return false;
      }
      p1 = i-1; p2= j-1;
    }
    return true;
  }

  static int getNextCharIndex(String str, int index) {
    int backspaceCount=0;
    while (index>=0) {
      if(str.charAt(index) == '#') {
        backspaceCount++;
      } else if(backspaceCount > 0) {
        //a non-backspace character
        backspaceCount--;
      } else {
        break;
      }
      index--;
    }
    return index;
  }


  static List<int[]> getQuads(int[] nums, int target) {
    Arrays.sort(nums);
    List<int[]> result = new ArrayList<>();
    for(int i=0; i<nums.length-3; i++) {
      //skip the same element to avoid duplicate quadruplets
      if(i>0 && nums[i] == nums[i-1]) {
        continue;
      }
      for(int j=i+1; j<nums.length-2; j++) {
        //skip the same element to avoid duplicate quadruplets
        if(j>i+1 && nums[j] == nums[j-1]) {
          continue;
        }
        searchPairs(nums,i, j, target, result);
      }
    }
    return result;
  }


  static void searchPairs(int[] nums, int first, int second, int target, List<int[]> result) {
    int start = second+1, end = nums.length-1;
    while (start<end) {
      int sum = nums[first] +nums[second] + nums[start] + nums[end];
      if(target == sum ) {
        result.add(new int[] {nums[first], nums[second], nums[start], nums[end]});
        start++; end--;
        while (start<end && nums[start] == nums[start-1]) {
          //skip the same element to avoid duplicate quadruplets
          start++;
        }
        while (start<end && nums[end] == nums[end-1]) {
          //skip the same element to avoid duplicate quadruplets
          end--;
        }
      } else if (target > sum) {
        start ++;
      } else  {
        end--;
      }
    }
  }
  static int[] dutchFlagSort(int[] nums) {
    int low=0, high=nums.length-1, i=0;
    while (i<=high) {
      if(nums[i] == 0) {
        // swap with 1
        int temp = nums[i];
        nums[i] = nums[low];
        nums[low] = temp;
        low++;
        i++;
      } else if(nums[i]==1) {
        i++;
      } else {
        // swap with 2
        int temp = nums[i];
        nums[i] = nums[high];
        nums[high] = temp;
        high--;
      }
    }
    return nums;
  }
  static int findSubarraysWithProduct(int[] nums, int target) {
    int result =0;
    int product = 1, left =0;
    for (int i =0; i< nums.length; i++) {
      product *= nums[i];
      //If product becomes greater than or equal to target, then we have to move left and also remove
      //nums[left] from product, so we divide it with nums[left] and increment left.
      while (product>= target && left<nums.length) {
        product /=nums[left];
        left++;
      }
       //count of contiguous subarrays where the product of all the elements in the subarray is
      //strictly less than target will be (i - left + 1).
      result += i-left+1;
    }

    return result;
  }


  static List<Integer[]> getAllTripletsWithSmallerSum(int [] nums, int target) {
    Arrays.sort(nums);
    List<Integer[]> triplets =new ArrayList<>();
    for (int i =0; i<nums.length-2; i++) {
      searchPairList(nums,target-nums[i], i, triplets);
    }

    return triplets;
  }


  static List<Integer[]> searchPairList(int [] nums, int targetSum, int first, List<Integer[]> triplets) {
    int start = first+1, end = nums.length-1;
    while (start<end) {
      if(nums[start] + nums[end] < targetSum) {
        // found triplet
        //since nums[end] >= nums[start], therefore, we can replace nums[end]
        //by any number between start and end to get a sum less than the targetSum
        for(int i=end; i>start; i--) {
          triplets.add(new Integer[] {nums[first],nums[start], nums[i]});
        }
        start++;
      } else {
        //we need a pair with a smaller sum
        end--;
      }
    }
    return triplets;
  }

  static int tripletCountWithSmallerSum(int [] nums, int target) {
    Arrays.sort(nums);
    int count =0;
    for (int i =0; i<nums.length-2; i++) {
       count += searchTriples(nums,target-nums[i], i);
    }

    return count;
  }

  static int searchTriples(int [] nums, int targetSum, int first) {
    int count =0, start = first+1, end = nums.length-1;
    while (start<end) {
      if(nums[start] + nums[end] < targetSum) {
        // found triplet
        //since nums[end] >= nums[start], therefore, we can replace nums[end]
        //by any number between start and end to get a sum less than the targetSum
        count += end-start;
       start++;
      } else {
        //we need a pair with a smaller sum
         end--;
      }
    }
    return count;
  }

  static int threeSumClosetToTarget(int[] nums, int target) {
    Arrays.sort(nums);
    int  smallDiff = Integer.MAX_VALUE;
    for (int i = 0; i<nums.length - 2; i++){
         int start = i+1, end= nums.length-1;
         while (start <end) {
           int diff = target- nums[i] - nums[start] - nums[end];
           if(diff==0) {
             //target same with triplet return target.
             return target;
           }
           if(Math.abs(smallDiff)> Math.abs(diff)) {
             //save small with diff
              smallDiff = diff;
           }
           //the second part of the followinf 'if' is to handle the smallest sum
           //when we have more than one solution
           if((Math.abs(diff) < Math.abs(smallDiff) || Math.abs(diff) == Math.abs(smallDiff) && diff > smallDiff)) {
             smallDiff =diff;
           }
           if(diff>0) {
             // need bigger difference
             start ++;
           } else  {
             // need small difference
             end--;
           }
         }
    }
    return target-smallDiff;
  }

  //Given an array of unsorted numbers, find all unique triplets in it that add up to zero.
  static List<List<Integer>> findTriplets(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> resultTriplets = new ArrayList<>();
    for(int i=0; i< nums.length; i++) {
      if(i>0 && nums[i] == nums[i-1]) {
        //skip same element to avoid duplicates
        continue;
      }
      pairSumWithTripletData(nums, -nums[i], i+1, resultTriplets);
    }
    return resultTriplets;
  }

  //Given an array of sorted numbers and a target sum, find a pair in the array whose sum is equal to the given target.
  static void pairSumWithTripletData(int[] nums, int target, int start, List<List<Integer>> result) {
    int end= nums.length-1;
    while(start<end) {
      int sum = nums[start] + nums[end];
      if(target== sum) {
        result.add(Arrays.asList(-target, nums[start], nums[end]));
        start++;
        end--;
        //skip same element to avoid duplicates
        while (start<end && nums[start] == nums[start-1]) {
          start++;
        }
        while (start<end && nums[end] == nums[end+1]) {
          end--;
        }
      } else if (target<sum) {
        end--;
      } else {
        start++;
      }
    }
  }
   // Squaring a Sorted Array  input : [-2, -1, 0, 2, 3]) and output [0, 1, 4, 4, 9]
  static int[] squareSortArray(int[] nums) {
    int start =0, end= nums.length-1, highestSquareIndex = nums.length-1;
    int[] sqArray = new int[nums.length];
    while (start<=end) {
      int startSq = nums[start] * nums[start];
      int endSq = nums[end] * nums[end];
      if(startSq> endSq) {
        sqArray[highestSquareIndex] = startSq;
        start++;
      } else {
        sqArray[highestSquareIndex] = endSq;
        end--;
      }
      highestSquareIndex --;
    }

    return sqArray;
  }
  //Given an array of sorted numbers, remove all duplicates from it. You should not use any extra space;
  // after removing the duplicates in-place return the length of the subarray that has no duplicate in it.
  static int removeElementKey(int [] nums, int key) {
   int i=1, nextNoDupe=1;
   while (i< nums.length-1) {
     if(nums[i] !=key) {
       nums[nextNoDupe] = nums[i];
       nextNoDupe++;
     }
     i++;
   }
   return nextNoDupe;
  }

  //Given an array of sorted numbers, remove all duplicates from it. You should not use any extra space;
  // after removing the duplicates in-place return the length of the subarray that has no duplicate in it.
  static int removeDupsByTwoPointApporch(int[] nums) {
    int i=1, nextDup =1;
    while (nextDup<nums.length) {
      if(nums[nextDup-1] != nums[nextDup]) {
        nums[i++] = nums[nextDup];
      }
      nextDup++;
    }
    return i;
  }

  //Given an array of sorted numbers and a target sum, find a pair in the array whose sum is equal to the given target.
  static int[] pairWithTargetSum(int[] nums, int target) {
    int start =0, end= nums.length-1;
    while(start<=end) {
      int sum = nums[start] + nums[end];
      if(target== sum) {
        return new int[] {start, end};
      } else if (target<sum) {
        end--;
      } else {
        start++;
      }
    }
    return new int[] {-1,-1};
  }

}
