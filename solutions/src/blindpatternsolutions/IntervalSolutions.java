package blindpatternsolutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class IntervalSolutions {
    class Interval {
        int startTime;
        int endTime;

        public Interval(int start, int end) {
            this.startTime = start;
            this.endTime = end;
        }
    }

    List<Interval> findFreeTime(List<Interval> meetings) {
        meetings.sort(Comparator.comparingInt(a -> a.startTime));
        List<Interval> freeTime = new ArrayList<>();
        int endTime = 1;
        for (Interval meeting : meetings) {
            if (endTime < meeting.startTime) {
                freeTime.add(new Interval(endTime, meeting.startTime));
            }
            endTime = Math.max(endTime, meeting.endTime);
        }
        freeTime.add(new Interval(endTime, 24));
        return freeTime;
    }

    Interval[] insertIntervals(Interval[] intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();
        int i = 0;
        // find non overlapping intervals in starting
        while (i < intervals.length && intervals[i].endTime < newInterval.startTime) {
            result.add(intervals[i]);
            i++;
        }
        // overlapping
        while (i < intervals.length && intervals[i].startTime < newInterval.endTime) {
            int startTime = Math.min(intervals[i].startTime, newInterval.startTime);
            int endTime = Math.max(intervals[i].endTime, newInterval.endTime);
            newInterval.startTime = startTime;
            newInterval.endTime = endTime;
            i++;
        }
        result.add(newInterval);
        while (i < intervals.length) {
            result.add(intervals[i]);
            i++;
        }
        return result.toArray(new Interval[result.size()]);
    }

    int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null || intervals.length == 0) {
            return new int[][]{newInterval};
        }

        int i = 0;
        List<int[]> result = new ArrayList<>();

        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }

        // add new interval
        result.add(newInterval);

        while (i < intervals.length) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }

    int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        LinkedList<int[]> mergedLst = new LinkedList<>();
        mergedLst.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] currentInterval = intervals[i];
            if (mergedLst.getLast()[1] < currentInterval[0]) {
                mergedLst.add(currentInterval);
            } else {
                mergedLst.getLast()[1] = Math.max(currentInterval[1], mergedLst.getLast()[1]);
            }
        }
        return mergedLst.toArray(new int[mergedLst.size()][2]);
    }

    Interval[] merge(Interval[] intervals) {
        Arrays.sort(intervals, (a, b) -> a.startTime - b.startTime);
        LinkedList<Interval> mergeList = new LinkedList<>();
        mergeList.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            Interval curr = intervals[i];
            if (mergeList.getLast().endTime < curr.startTime)
                mergeList.add(curr);
            else
                mergeList.getLast().endTime = Math.max(curr.endTime, mergeList.getLast().endTime);
        }
        return mergeList.toArray(new Interval[mergeList.size()]);
    }

    int countNonOverlapping(int[][] intervals) {
        int count = 1, prev = 0;
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        for (int i = 1; i < intervals.length; i++) {
            int endTimePrev = intervals[prev][1];
            int startTimeCurr = intervals[i][0];
            if (endTimePrev <= startTimeCurr) {
                count++;
                prev = i;
            }
        }
        return intervals.length - count;

    }

    // Can attend all meetings
    boolean canAttendMeeting(Interval[] intervals) {
        Arrays.sort(intervals, (a, b) -> a.startTime - b.endTime);
        int prev = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[prev].endTime > intervals[i].startTime)
                return false;
            prev = i;
        }

        return true;
    }

    // Minimum number of rooms
    int minMeetingRoom(Interval[] intervals) {
        Arrays.sort(intervals, (a, b) -> a.startTime - b.startTime);
        PriorityQueue<Interval> heap = new PriorityQueue<>((a, b) -> a.endTime - b.endTime);
        heap.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int endTime = heap.peek().endTime;
            int startTime = intervals[i].startTime;
            if (endTime <= startTime) {
                heap.poll();
            }
        }
        return heap.size();
    }
}


