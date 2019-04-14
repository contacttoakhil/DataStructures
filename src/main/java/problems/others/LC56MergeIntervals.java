package main.java.problems.others;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/***
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * Example 1:
 *
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 *
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 *
 * https://leetcode.com/problems/merge-intervals/
 */
public class LC56MergeIntervals {

    public List<Interval> merge(List<Interval> intervals) {
        if(intervals == null || intervals.size()<=1){
            return intervals;
        }

        Collections.sort(intervals, Comparator.comparing((Interval itl)->itl.start));

        List<Interval> result = new ArrayList<>();
        Interval t = intervals.get(0);

        for(int i=1; i<intervals.size(); i++){
            Interval c = intervals.get(i);
            if(c.start <= t.end){
                t.end = Math.max(t.end, c.end);
            }else{
                result.add(t);
                t = c;
            }
        }

        result.add(t);

        return result;
    }
}

class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
 }
