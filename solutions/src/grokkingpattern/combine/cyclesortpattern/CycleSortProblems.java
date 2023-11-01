package grokkingpattern.combine.cyclesortpattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CycleSortProblems {

  public static void main(String[] args) {
    smallestPostiveKnumbers(new int[] {3, -1, 4, 5, 5}, 3).forEach((a) -> System.out.print(a +","));
    System.out.println("\n");
    smallestPostiveKnumbers(new int[] {2, 3, 4}, 3).forEach((a) -> System.out.print(a +","));
    System.out.println("\n");
    smallestPostiveKnumbers(new int[] {-2, -3, 4}, 2).forEach((a) -> System.out.print(a +","));
    System.out.println("\n");
    smallestPostiveKnumbers(new int[] {2, 1, 3, 6, 5}, 2).forEach((a) -> System.out.print(a +","));

    /*System.out.println(findSmallestPositiveNumber(new int[] {-3, 1, 5, 4, 2}));
    System.out.println(findSmallestPositiveNumber(new int[] {3, -2, 0, 1, 2}));
    System.out.println(findSmallestPositiveNumber(new int[] {3, 2, 5, 1}));
    Arrays.stream(findCorruptNumbers(new int[] {3, 1, 2, 5, 2})).forEach((a) -> System.out.print(a +","));
    System.out.println("\n");
    Arrays.stream(findCorruptNumbers(new int[] {3, 1, 2, 3, 6, 4})).forEach((a) -> System.out.print(a +","));

    duplicateNumbers(new int[] {3, 4, 4, 5, 5}).stream().forEach((a) -> System.out.print(a +","));
    System.out.println("\n");
    duplicateNumbers(new int[] {5, 4, 7, 2, 3, 5, 3}).stream().forEach((a) -> System.out.print(a + ","));
    Arrays.stream(missingNumbers(new int[] {2, 3, 1, 8, 2, 3, 5, 1})).forEach((a) -> System.out.print(a + ","));
    System.out.println("\n");
    Arrays.stream(missingNumbers(new int[] {2, 4, 1, 2})).forEach((a) -> System.out.print(a + ","));
    System.out.print("\nmissing number " + findMissingNumber(new int[] {3, 1, 5, 2}));
    System.out.print("\nmissing another number  " +  findMissingNumber(new int[] {3, 1, 4, 6, 2}) + "\n");
   Arrays.stream(cycleSort(new int[] {3, 1, 5, 4, 2})).forEach((a) -> System.out.print(a + ","));
   System.out.println("\nanother problem");
   Arrays.stream(cycleSort(new int[] {2, 6, 4, 3, 1, 5})).forEach((a) -> System.out.print(a + ",")); */
  }

  static List<Integer> smallestPostiveKnumbers(int [] nums, int k) {
    Set<Integer> extraNumbers = new HashSet<>();
    List<Integer> missingNumbers = new ArrayList<>();
    int i=0;
    while(i<nums.length) {
       int j = nums[i]-1;
       if(nums[i]>0 && nums[i]<= nums.length && nums[i]!= nums[j]) {
         // swap
         int tmp = nums[i];
         nums[i] = nums[j];
         nums[j] = tmp;
       } else {
         i++;
       }
    }

    for(i=0; i< nums.length; i++) {
      if(missingNumbers.size() < k) {
        if (nums[i] != i + 1) {
          missingNumbers.add(i+1);
          extraNumbers.add(nums[i]);
        }
      }
    }

    //add the remaining missing numbers
    int j=nums.length;
    while (missingNumbers.size()<k) {
      int currentNumber = j + 1;
      //ignore if the array contains the current number
      if(!extraNumbers.contains(currentNumber)) {
        missingNumbers.add(currentNumber);
      }
      j++;
    }

    return missingNumbers;
  }
  static int findSmallestPositiveNumber(int [] nums) {
    for(int i=0; i< nums.length; i++) {
      int j = nums[i]-1;
      // check number not same index as well less than length
      if (nums[i]>0 && nums[i]<=nums.length && nums[i]!=nums[j]) {
        //swap
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
      }
    }
    for(int i=0; i<nums.length; i++) {
      if(nums[i]!=i+1) {
        return i+1;
      }
    }

    return nums.length+1;
  }
  static int[] findCorruptNumbers(int[] nums) {
    int i=0;
    while (i<nums.length) {
      int j= nums[i]-1;
      if(nums[i] != nums[j]) {
        //swap
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
      } else {
        i++;
      }
    }

    for(i=0; i< nums.length; i++) {
      if(nums[i] != i+1) {
        return new int[] {nums[i],i+1};
      }
    }
    return new int[] {-1,-1};
  }
  static List<Integer> duplicateNumbers(int[] nums) {
    int i=0;
    while (i<nums.length) {
      int j = nums[i]-1;
      if(nums[i] != nums[j]) {
        // swap
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
      } else {
        i++;
      }
    }

    List<Integer> dups = new ArrayList<>();
    for (i=0; i<nums.length;i++) {
      if(nums[i] != i+1) {
        dups.add(nums[i]);
      }
    }
    return dups;
  }
  static int findDuplicateNumber(int[] nums) {
    int i=0;
    while (i<nums.length) {
      if(nums[i] != i+1) {
        int j= nums[i]-1;
        if(nums[j] != nums[i]) {
             // swap
          int temp = nums[i];
          nums[i] = nums[j];
          nums[j] = temp;
        }else  {
          // found duplicate
          return nums[i];
        }
      }else {
        i++;
      }
    }
    return -1;
  }
  static int[] missingNumbers(int [] nums) {
  nums = cycleSort(nums);
  List<Integer> missingNumbers= new ArrayList<>();
  for(int i=0; i<nums.length; i++) {
    if(nums[i]!= i+1) {
      missingNumbers.add(i+1);
    }
  }
  return missingNumbers.stream().mapToInt(Integer:: intValue).toArray();
  }

  static int findMissingNumber(int[] nums) {
    //Sort
    Arrays.sort(nums);
    for(int i=0; i< nums.length; i++) {
      if(nums[i]!=i+1) {
        return i+1;
      }
    }
    return 0;
  }
  static int[] cycleSort(int[] nums) {
    if(nums== null || nums.length==0) return nums;
    int i=0;
    while (i< nums.length) {
      int j = nums[i]-1; // number at index.
      if(nums[i] != nums[j]) {
        int temp = nums[i];
        nums[i] =  nums[j];
        nums[j] = temp;
      } else  {
        i++;
      }
    }
    return nums;
   }
}
