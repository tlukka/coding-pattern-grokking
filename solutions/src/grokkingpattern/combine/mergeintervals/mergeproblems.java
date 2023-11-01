package grokkingpattern.combine.mergeintervals;

import grokking.CpuJob;
import grokking.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class mergeproblems {

  public static void main(String [] args) {

    List<List<Interval>> firstList = new ArrayList<>();
    firstList.add(Arrays.asList(new Interval[] {new Interval(1,3), new Interval(5,6)}));
    firstList.add(Arrays.asList(new Interval[] {new Interval(2,3), new Interval(6,8)}));
    List<Interval> freeTimes = findEmployeeFreeTime(firstList);
    List<Interval> freeTime5 = findEmployeeFreeTimeByHeap(firstList);
    freeTime5.forEach((a) -> System.out.println(a.getInterval()));
    freeTimes.forEach((a) -> System.out.println(a.getInterval()));
    System.out.println("next prbolem");
    firstList = new ArrayList<>();
    firstList.add(Arrays.asList(new Interval(1,3), new Interval(9,12)));
    firstList.add(Arrays.asList(new Interval(2,4), new Interval(6,8)));
    List<Interval> freeTimes1 = findEmployeeFreeTime(firstList);
    List<Interval> freeTimes4= findEmployeeFreeTimeByHeap(firstList);
    freeTimes4.forEach((a) -> System.out.println(a.getInterval()));
    freeTimes1.forEach((a) -> System.out.println(a.getInterval()));
    System.out.println("next prbolem");
    firstList = new ArrayList<>();
    firstList.add(Arrays.asList(new Interval(1,3), new Interval(2,4)));
    firstList.add(Arrays.asList(new Interval(3,5), new Interval(7,9)));
    List<Interval> freeTimes2 = findEmployeeFreeTime(firstList);
    List<Interval> freeTimes3= findEmployeeFreeTimeByHeap(firstList);
    freeTimes2.forEach((a) -> System.out.println(a.getInterval()));
    freeTimes3.forEach((a) -> System.out.println(a.getInterval()));
    /*
   System.out.println("Max Cpu load " + findMaxCpuLoad(new ArrayList<>(Arrays.asList((new CpuJob[] {
        new CpuJob(1,4,3), new CpuJob(2,5,4), new CpuJob(7,9,6)
    })))));
    System.out.println("Max Cpu load " + findMaxCpuLoad(new ArrayList<>(Arrays.asList(new CpuJob[] {
        new CpuJob(6,7,10), new CpuJob(2,4,11), new CpuJob(8,12,15)
    }))));
    System.out.println("Max Cpu load " + findMaxCpuLoad(new ArrayList<>(Arrays.asList(new CpuJob[] {
        new CpuJob(1,4,2), new CpuJob(2,4,1), new CpuJob(3,6,5)
    }))));
    System.out.println("min rooms " + minMeetingRooms(null));
    System.out.println("min rooms " + minMeetingRooms(new Interval[] {
        new Interval(1,1)
    }));
    System.out.println("min rooms " + minMeetingRooms(new Interval[] {
        new Interval(1,4),new Interval(2,5),new Interval(7,9)
    }));
    System.out.println("min rooms " + minMeetingRooms(new Interval[] {
        new Interval(6,7),new Interval(2,4),new Interval(8,12)
    }));
    Map<Interval,Interval> conflicts= conflictIntervals(new Interval[] {
        new Interval(4,5), new Interval(2,3), new Interval(3,6),new Interval(5,7), new Interval(7,8)
    });
    conflicts.forEach((a,b) -> System.out.println("Conficts key" + a.getInterval() + " with " + b.getInterval()));
    System.out.println("can attend all meetings " + canAttendAllMeeting(new Interval[] {
        new Interval(1,4), new Interval(2,5), new Interval(7,9)
    }));
    System.out.println("can attend all meetings " + canAttendAllMeeting(new Interval[] {
        new Interval(6,7), new Interval(2,4), new Interval(8,12)
    }));
    System.out.println("can attend all meetings " + canAttendAllMeeting(new Interval[] {
        new Interval(4,5), new Interval(2,3), new Interval(3,6)
    }));
    Interval[] intersectionIntervals = intervalIntersection(new Interval[] {
        new Interval(1,3),new Interval(5,6),new Interval(7,9)
    }, new Interval[] {
        new Interval(2,3), new Interval(5,7)
    });
    System.out.println("first prb");
    Arrays.stream(intersectionIntervals).forEach((a) -> System.out.println(a.getInterval()));
    Interval[] intersectionIntervals1 = intervalIntersection(new Interval[] {
        new Interval(1,3),new Interval(5,6),new Interval(9,12)
    }, new Interval[] {
        new Interval(5,10)
    });
    System.out.println("second prb");
    Arrays.stream(intersectionIntervals1).forEach((a) -> System.out.println(a.getInterval()));
    Interval[] intersectionIntervals2 = intervalIntersection(new Interval[] {
        new Interval(0,2),new Interval(5,10),new Interval(13,23), new Interval(24,25)
    }, new Interval[] {
        new Interval(1,5), new Interval(8,12),new Interval(15,24),new Interval(25,26)
    });
    System.out.println("3rd prb");
    Arrays.stream(intersectionIntervals2).forEach((a) -> System.out.println(a.getInterval()));
    Interval[] intersectionIntervals3 = intervalIntersection(new Interval[] {
        new Interval(0,2),new Interval(5,10)
    }, new Interval[] {});
    System.out.println("4th prb");
    Arrays.stream(intersectionIntervals3).forEach((a) -> System.out.println(a.getInterval()));
    Interval[] intervals = insertInterval(new Interval[] { new Interval(1,3), new Interval(5,7),
        new Interval(8,12)}, new Interval(4,6));
   Arrays.stream(intervals).forEach((a) -> System.out.println(a.getInterval()));
    Interval[] intervals1 = insertInterval(new Interval[] { new Interval(1,2), new Interval(3,5),
        new Interval(6,7),new Interval(8,10),new Interval(12,16)}, new Interval(4,8));
    Arrays.stream(intervals1).forEach((a) -> System.out.println(a.getInterval()));
    List<Interval> intervals = new ArrayList<>();
    intervals.add(new Interval(1,4));
    intervals.add(new Interval(2,5));
    intervals.add(new Interval(7,9));
    System.out.println("Overlap is " + overLapIntervals(intervals));
    List<Interval> result1 = mergeIntervals(intervals);
    result1.forEach( a -> System.out.println(a.getInterval()));
    List<Interval> intervals1 = new ArrayList<>();
    intervals1.add(new Interval(6,7));
    intervals1.add(new Interval(2,4));
    intervals1.add(new Interval(5,9));
    System.out.println("Overlap is " + overLapIntervals(intervals1));

    List<Interval> result2 = mergeIntervals(intervals1);
    result2.forEach( a -> System.out.println(a.getInterval()));

    List<Interval> intervals2 = new ArrayList<>();
    intervals2.add(new Interval(1,4));
    intervals2.add(new Interval(2,6));
    intervals2.add(new Interval(3,5));
    System.out.println("Overlap is " + overLapIntervals(intervals2));

    List<Interval> result3 = mergeIntervals(intervals2);
    result3.forEach( a -> System.out.println(a.getInterval()));

    List<Interval> intervals3 = new ArrayList<>();
    intervals3.add(new Interval(1,4));
    System.out.println("Overlap is " + overLapIntervals(intervals3));

    List<Interval> result4 = mergeIntervals(intervals3);
    result4.forEach( a -> System.out.println(a.getInterval()));

    List<Interval> intervals4 = new ArrayList<>();
    intervals4.add(new Interval(1,2));
    intervals4.add(new Interval(3,4));
    intervals4.add(new Interval(5,6));
    System.out.println("Overlap is " + overLapIntervals(intervals4));
    List<Interval> result5 = mergeIntervals(intervals4);
    result5.forEach( a -> System.out.println(a.getInterval())); */
  }

  static List<Interval> findEmployeeFreeTimeByHeap(List<List<Interval>> schedules) {
    List<Interval> freeTimes = new ArrayList<>();
    if(schedules == null || schedules.size()==0) {
      return freeTimes;
    }

    // Add all into list
    List<Interval> list = new ArrayList<>();
    for(List<Interval> time: schedules) {
      for(Interval interval: time)
        list.add(interval);
    }
    // sort list
    Collections.sort(list, new Comparator<Interval>() {
      @Override
      public int compare(Interval o1, Interval o2) {
        return o1.start== o2.start? o1.end-o2.end: o1.start-o2.start;
      }
    });

    PriorityQueue<Integer> minHeapEndTime = new PriorityQueue<>(Collections.reverseOrder());
    minHeapEndTime.add(list.get(0).end);
    for(int i=1; i< list.size();i++) {
      if(minHeapEndTime.size()>0 && minHeapEndTime.peek()<list.get(i).start) {
        freeTimes.add(new Interval(minHeapEndTime.peek(), list.get(i).start));
      }
      minHeapEndTime.add(list.get(i).end);
    }
    return freeTimes;
  }

  //Employee Free Time (https://leetcode.com/problems/employee-free-time/)
  static List<Interval> findEmployeeFreeTime(List<List<Interval>> schedules) {
    List<Interval> freeTimes = new ArrayList<>();
    if(schedules == null || schedules.size()==0) {
      return freeTimes;
    }

    // Add all into list
    List<Interval> list = new ArrayList<>();
    for(List<Interval> time: schedules) {
      for(Interval interval: time)
        list.add(interval);
    }
    Collections.sort(list, (a,b) -> a.start-b.start);
    for(int i=1; i<list.size(); i++) {
      Interval current = list.get(i), earliest= list.get(i-1);
      if(earliest.end<current.start) {
        freeTimes.add(new Interval(earliest.end, current.start));
      }
    }
    return freeTimes;
  }

  //Maximum CPU Load
  static int findMaxCpuLoad(ArrayList<CpuJob> jobs) {
    //sort the jobs by start time
    Collections.sort(jobs, (a,b) -> a.start-b.start);
    //consolidate jobs that overlap
    for (int i=1; i<jobs.size(); i++) {
      CpuJob current = jobs.get(i);
      CpuJob earliest = jobs.get(i-1);
      if(earliest.end>current.start) {
        // add cpu load
        jobs.get(i).start = earliest.start;
        jobs.get(i).end = current.end;
        jobs.get(i).cpuLoad = current.cpuLoad + earliest.cpuLoad;
        jobs.remove(i-1);
        i--;
      }
    }

    // set max load
    int maxCpuLoad= 0;
    for(int i = 0; i < jobs.size(); i++) {
      maxCpuLoad = Math.max(maxCpuLoad, jobs.get(i).cpuLoad);
    }
    return maxCpuLoad;
  }

  //Given a list of intervals, find the point where the maximum number of intervals overlap.
  //Given a list of intervals representing the arrival and departure times of trains/bus to a train/bus station,
  // our goal is to find the minimum number of platforms required for the train station so that no train has to wait.
  static int minMeetingRooms(Interval[] meetings) {
    if(meetings==null) return 0;
    if(meetings.length<=1) return meetings.length;
    Arrays.sort(meetings, ((a,b) -> a.start-b.start)); // sort
    PriorityQueue<Interval> minHeap = new PriorityQueue<>((a,b) -> a.end-b.end); // min heap
    minHeap.add(meetings[0]);
    for(int i=1; i<meetings.length;i++) {
      Interval current = meetings[i];
      Interval earliest = minHeap.remove();
      if(earliest.end<=current.start) {
        // earliest end is less than start of current and can use same room
        earliest.end = current.end;
        // add back earlist
        minHeap.add(earliest);
      } else {
        minHeap.add(current);
      }

    }

    return minHeap.size();
  }
  static Map<Interval, Interval> conflictIntervals(Interval[] meetings) {
    Arrays.sort(meetings, (a,b) -> a.start-b.start);
    Map<Interval, Interval> conflicts= new HashMap<>();
    for(int i=0; i<meetings.length-1; i++) {
      for (int j=i+1; j<meetings.length; j++) {
        if(j!=i && meetings[i].end> meetings[j].start) {
          conflicts.put(meetings[j], meetings[i]);
        }
      }
    }
    return conflicts;
  }

  static boolean canAttendAllMeeting(Interval[] meetings) {
    Arrays.sort(meetings, (a,b) -> a.start-b.start);
    for(int i=1; i<meetings.length; i++) {
      // over lap can't attend all meetings
      if(meetings[i-1].end>meetings[i].start) {
        return false;
      }
    }
    return true;
  }
  static Interval[] intervalIntersection(Interval[] firstIntervals, Interval[] secondIntervals) {
    List<Interval> result = new ArrayList<>();
    int i=0, j=0;
    while (i<firstIntervals.length && j<secondIntervals.length) {

     // first interval overlap
      boolean firstOverlap = firstIntervals[i].start >= secondIntervals[j].start
          && firstIntervals[i].start <= secondIntervals[j].end;
      // second overlap
      boolean secondOverlap = firstIntervals[i].start <= secondIntervals[j].start
          && secondIntervals[j].start <= firstIntervals[i].end;

      //store the intersection part
      if(firstOverlap || secondOverlap) {
        result.add(new Interval(Math.max(firstIntervals[i].start, secondIntervals[j].start),
            Math.min(firstIntervals[i].end, secondIntervals[j].end)));
      }
      //move next from the interval which is finishing first
      if(firstIntervals[i].end<secondIntervals[j].end) {
        i++;
      } else {
        j++;
      }
    }
    return result.toArray(new Interval[result.size()]);
  }

  static Interval[] insertInterval(Interval[] intervals, Interval newInterval) {
    List<Interval> result = new ArrayList<>();
    // Intervals null or empty
    if(intervals == null || intervals.length==0) {
      return new Interval[] {newInterval};
    }

    int i=0;
    while (i<intervals.length && intervals[i].end< newInterval.start) {
      // end is less than start of new intervals
        result.add(intervals[i]);
        i++;
      }

    // Merge the overlapping intervals with newInterval
    while (i<intervals.length && intervals[i].start<= newInterval.end) {
      newInterval.start = Math.min(intervals[i].start, newInterval.start);
      newInterval.end = Math.max(intervals[i].end, newInterval.end);
      i++;
    }
    //add the new interval
    result.add(newInterval);
    while (i<intervals.length) {
      result.add(intervals[i]);
      i++;
    }

    return result.toArray(new Interval[result.size()]);
  }
  static boolean overLapIntervals(List<Interval> intervals) {
    if(intervals.size()<2) return false;
    Collections.sort(intervals, ((a,b) -> a.start-b.start));
    for(int i=1; i<intervals.size(); i++) {
      Interval current = intervals.get(i), previous = intervals.get(i-1);
      if(current.start<=previous.end) {
        return true;
      }
    }
    return false;
  }
  static List<Interval> mergeIntervals(List<Interval> intervals) {
    if(intervals.size() <2) return intervals;
    Collections.sort(intervals, ((a, b) -> a.start -b.start)); // Sort intervals
    for(int i=1; i<intervals.size(); i++) {
      Interval current = intervals.get(i), previous = intervals.get(i-1);
      // Compare start of current & end of previous
      if(current.start <= previous.end) {
        intervals.get(i).start =previous.start;
        intervals.get(i).end = Math.max(previous.end, current.end);
        // remove from list
        intervals.remove(i-1);
        i--;
      }
    }
   return intervals;
  }
}
