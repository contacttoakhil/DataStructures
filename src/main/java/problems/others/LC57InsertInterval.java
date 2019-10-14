package main.java.problems.others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC57InsertInterval {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        for (int[] interval : intervals) {
            if(interval[1] < newInterval[0])            // interval.end < newInterval.start
                result.add(interval);
            else if (interval[0] > newInterval[1]) {    // interval.start > newInterval.end
                result.add(newInterval);
                newInterval = interval;
            }
            else if (interval[1] >= newInterval[0] || interval[0] <= newInterval[1]) {                 // interval.end >= newInterval.start || interval.start <= newInterval.end
                newInterval[0] = Math.min(interval[0], newInterval[0]);
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            }
        }
        result.add(newInterval);
        return result.toArray(new int[result.size()][2]);
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][] {
                {1, 2},
                {3, 5},
                {6, 7},
                {8, 10},
                {12, 16}
        };
        LC57InsertInterval insertInterval = new LC57InsertInterval();
        int[][] result = insertInterval.insert(intervals, new int[] {4, 8});
        System.out.println(Arrays.deepToString(result));
    }
}
