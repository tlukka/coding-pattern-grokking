package grokkingpattern.combine.subsetproblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problems {

  public static void main(String[] args) {
     subSets(new int []{1,2,3}).forEach((l) -> System.out.println(Arrays.toString(l.toArray())));
  }

  static List<List<Integer>> subSets(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    subsets(nums, new ArrayList<>(), 0, result);
    return result;
  }

  static void subsets(int[] nums, ArrayList<Integer> currentList, int index, List<List<Integer>> result) {
    // base condition
    if(index>= nums.length) {
      result.add(new ArrayList<>(currentList));
      return;
    }

    // case 1 pick element
    currentList.add(nums[index]);
    subsets(nums, currentList,index+1, result);
    currentList.remove(currentList.size()-1); // remove last value

    // case 2 continue with out first element
    subsets(nums,currentList, index+1, result);
  }
}
