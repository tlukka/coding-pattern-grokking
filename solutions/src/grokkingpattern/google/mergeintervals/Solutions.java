package grokkingpattern.google.mergeintervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Solutions {


    // https://leetcode.com/problems/interval-list-intersections
    // Interval List Intersections
    int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        if(firstList.length==0 || secondList.length==0) return new int[0][0];
        int i = 0;
        int j = 0;
        int startMax = 0, endMin = 0;
        List<int[]> ans = new ArrayList<>();

        while(i<firstList.length && j<secondList.length){
            startMax = Math.max(firstList[i][0],secondList[j][0]);
            endMin = Math.min(firstList[i][1],secondList[j][1]);

            //you have end greater than start and you already know that this interval is sorrounded with startMin and
            // endMax so this must be the intersection
            if(endMin>=startMax){
                ans.add(new int[]{startMax,endMin});
            }

            //the interval with min end has been covered completely and have no chance to intersect with any
            // other interval so move that list's pointer
            if(endMin == firstList[i][1]) i++;
            if(endMin == secondList[j][1]) j++;
        }

        return ans.toArray(new int[ans.size()][2]);
    }

    // https://leetcode.com/problems/car-pooling/
    // Car Pooling
    static  boolean carPooling(int[][] trips, int capacity) {
        int[] lengthOfTrips = new int[1001]; // there are 1000 trips
        for(int[] trip: trips) {
            lengthOfTrips[trip[1]] +=trip[0]; // increment
            lengthOfTrips[trip[2]] -=trip[0]; // decrement
        }

        int carLoad = 0;
        for(int i=0; i<1001; i++) {
            carLoad +=lengthOfTrips[i];
            if(carLoad>capacity)
                return false;
        }
        return true;
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
        Arrays.sort(meetings, ((a, b) -> a.start-b.start)); // sort
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
