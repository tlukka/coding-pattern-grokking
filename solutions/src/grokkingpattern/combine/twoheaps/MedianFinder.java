package grokkingpattern.combine.twoheaps;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianFinder {

  // min & max heaps
  PriorityQueue<Integer> minHeap = null;
  PriorityQueue<Integer> maxHeap = null;

  public MedianFinder() {
    minHeap = new PriorityQueue<>();
    maxHeap = new PriorityQueue<>(Collections.reverseOrder());
  }

  public void addNum(int num) {
    if (minHeap.isEmpty() && maxHeap.isEmpty()) {
      maxHeap.add(num); // add maxheap.
    } else if (maxHeap.peek() < num) {
      minHeap.add(num);
      if (minHeap.size() - maxHeap.size() >= 2) {
        maxHeap.add(minHeap.poll());
      }
    } else {
        maxHeap.add(num);
        if(maxHeap.size()-minHeap.size()>=2) {
          minHeap.add(maxHeap.poll());
        }
    }
  }

  public double findMedian() {
    if(minHeap.size()==maxHeap.size()) {
      double ans = (double) maxHeap.peek() + (double) minHeap.peek();
      return ans/(double) 2;
    } else {
      return minHeap.size ()> maxHeap.size() ? (double) minHeap.peek(): (double) maxHeap.peek();
    }
  }

}
