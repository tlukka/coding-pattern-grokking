package grokkingpattern.combine.slidewindow;

public class LengthOfSubArrayEqualsToSum {

  public static void main(String[] args) {
    System.out.println("Max Sum out put is by effiency way " + lenWindowSumEqual(new int[] {2, 1, 5, 2, 3, 2}, 7));
    System.out.println("Max Sum out put is by effiency way " + lenWindowSumEqual(new int[] {2, 1, 5, 2, 8}, 7));
    System.out.println("Max Sum out put is by effiency way " + lenWindowSumEqual(new int[] {3, 4, 1, 1, 6}, 76));
    System.out.println("Max Sum out put is by effiency way " + lenWindowSumEqual(new int[] {3, 4, 1, 1, 6}, 8));

  }

  static int lenWindowSumEqual(int[] arr, int sum) {
    int windowStart=0, windowSum=0, minLength = Integer.MAX_VALUE;
    for(int windowEnd = 0; windowEnd<arr.length; windowEnd++) {
      windowSum += arr[windowEnd];
      while (windowSum>=sum) {
        minLength = Math.min(minLength, windowEnd-windowStart+1);
        windowSum -= arr[windowStart++];
      }
    }
    return minLength == Integer.MAX_VALUE? 0: minLength;
  }
}
