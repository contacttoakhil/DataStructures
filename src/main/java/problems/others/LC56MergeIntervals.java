package main.java.problems.others;

import java.util.*;

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
        if(intervals == null || intervals.isEmpty() || intervals.size()<=1) return intervals;
        Collections.sort(intervals, Comparator.comparing((Interval i) -> i.start));
        List<Interval> result = new ArrayList<>();
        Interval prev = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval curr = intervals.get(i);
            if(curr.start <= prev.end)
                prev.end = Math.max(prev.end, curr.end);
            else {
                result.add(prev);
                prev = curr;
            }
        }
        result.add(prev);
        return result;
    }

    public List<Interval> union(List<Interval> intervals){
        return merge(intervals);
    }

    public List<Interval> intersection(List<Interval> intervals){
        if(intervals == null || intervals.isEmpty()) return intervals;
        Collections.sort(intervals, Comparator.comparing((Interval i) -> i.start));
        LinkedList<Interval> result = new LinkedList<>();
        Interval prev = intervals.get(0);
        for(int i = 1; i < intervals.size(); i++){
            Interval curr = intervals.get(i);
            if(curr.start < prev.end) {
                Interval intersection = new Interval(Math.max(prev.start, curr.start), Math.min(prev.end, curr.end));
                if(!result.isEmpty() && intersection.start < result.peekLast().end){
                    Interval last = result.pollLast();
                    intersection.start = last.start;
                    intersection.end = Math.max(last.end, intersection.end);
                }
                result.add(intersection);
            }
            prev = new Interval(Math.max(prev.start, curr.start), Math.max(prev.end, curr.end));
        }
        return result;
    }

    public static void main(String[] args) {
        LC56MergeIntervals mergeIntervals = new LC56MergeIntervals();
        List<Interval> list = Arrays.asList(new Interval(1,10),new Interval(2,6),new Interval(9,12),new Interval(14,16),new Interval(16,17));
        System.out.println(mergeIntervals.union(copyList(list)));               //[ [1,12], [14,17] ]
        System.out.println(mergeIntervals.intersection(copyList(list)));        //[ [2,6], [9,10] ]

        List<Interval> list2 = Arrays.asList(new Interval(1,10),new Interval(2,6),new Interval(7,12),new Interval(9,13), new Interval(15,18),new Interval(16,20));
        System.out.println(mergeIntervals.union(copyList(list2)));               //[ [1,13], [15,20] ]
        System.out.println(mergeIntervals.intersection(copyList(list2)));        //[ [2,6], [7,12], [16,18] ]

        List<Interval> list3 = Arrays.asList(new Interval(1,10),new Interval(2,6),new Interval(3,7),new Interval(4,8));
        System.out.println(mergeIntervals.union(copyList(list3)));               //[ [1,10]]
        System.out.println(mergeIntervals.intersection(copyList(list3)));        //[ [2,8]]
    }

    private static List<Interval> copyList(List<Interval> list) {
        List<Interval> copy  = new ArrayList<>();
        for(Interval it : list) {
            copy.add(new Interval(it.start, it.end));
        }
        return copy;
    }
}

class Interval {
    int start;
    int end;
    Interval() { this(0,0); }
    Interval(int s, int e) { start = s; end = e; }

    @Override
    public String toString() {
        return "Interval{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
