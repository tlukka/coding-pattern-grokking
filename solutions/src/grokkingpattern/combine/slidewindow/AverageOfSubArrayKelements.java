package grokkingpattern.combine.slidewindow;

import java.util.Arrays;

public class AverageOfSubArrayKelements {

  public static void main(String[] args) {
    double[] results = bruteForceApporach(new int[] {1, 3, 2, 6, -1, 4, 1, 8, 2}, 5);
    System.out.println("out put is by brute force" + Arrays.toString(results));
    double[] results1 = slideWindowAverage(new int[] {1, 3, 2, 6, -1, 4, 1, 8, 2}, 5);
    System.out.println("out put is by effiency way " + Arrays.toString(results1));
  }

  static double[] slideWindowAverage(int[] arr, int k) {
    int len = arr.length;
    int windowStart =0;
    double windowSum = 0;
    double[] result = new double[len-k+1];
    for(int windowEnd =0; windowEnd<len;windowEnd++) {
      windowSum += arr[windowEnd];
      if(windowEnd>=k-1) {
        result[windowStart] = windowSum/k;
        windowSum -=arr[windowStart];
        windowStart++;
      }
    }
    return  result;
  }
  static double[] bruteForceApporach(int [] arr, int k) {
    int len = arr.length;
    double[] result = new double[len-k+1];
    for(int i=0; i<=len-k; i++) {
      double sum = 0;
      for(int j=i; j<k+i; j++) {
        sum += arr[j];
      }
      result[i] =sum/k;
    }
    return result;
  }
}
