package grokkingpattern.combine.slidewindow;

public class MaxSumSubArrayKelements {

  public static void main(String[] args) {
    System.out.println("Max Sum out put is by brute force " + bruteForceApporach(new int[] {1, 3, 2, 6, -1, 4, 1, 8, 2}, 5));
    System.out.println("Max Sum out put is by effiency way " + slideWindowAverage(new int[] {1, 3, 2, 6, -1, 4, 1, 8, 2}, 5));
  }

  static int slideWindowAverage(int[] arr, int k) {
    int len = arr.length;
    int maxSum =0;
    int windowSum = 0;
    for(int windowEnd =0; windowEnd<len;windowEnd++) {
      windowSum += arr[windowEnd];
      if(windowEnd>=k-1) {
        maxSum = Math.max(maxSum, windowSum);
        windowSum -=arr[windowEnd];
      }
    }
    return maxSum;

  }
  static int bruteForceApporach(int [] arr, int k) {
    int len = arr.length;
    int maxSum = 0;
    for(int i=0; i<len-k; i++) {
      int windowSum=0;
      for(int j=i; j<=k+i;j++) {
         windowSum += arr[j];
      }
      maxSum = Math.max(maxSum, windowSum);
    }
    return maxSum;
  }
}
