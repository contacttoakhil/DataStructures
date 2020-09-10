package main.java.problems.heap;

import java.util.*;

/***
 * There are n different online courses numbered from 1 to n. Each course has some duration(course length) t and closed on dth day. A course should be taken continuously for t days and must be finished before or on the dth day. You will start at the 1st day.
 *
 * Given n online courses represented by pairs (t,d), your task is to find the maximal number of courses that can be taken.
 *
 * Example:
 *
 * Input: [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
 * Output: 3
 * Explanation:
 * There're totally 4 courses, but you can take 3 courses at most:
 * First, take the 1st course, it costs 100 days so you will finish it on the 100th day, and ready to take the children course on the 101st day.
 * Second, take the 3rd course, it costs 1000 days so you will finish it on the 1100th day, and ready to take the children course on the 1101st day.
 * Third, take the 2nd course, it costs 200 days so you will finish it on the 1300th day.
 * The 4th course cannot be taken now, since you will finish it on the 3300th day, which exceeds the closed date.
 *
 *
 * Note:
 *
 * The integer 1 <= d, t, n <= 10,000.
 * You can't take two courses simultaneously.
 *
 * https://leetcode.com/problems/course-schedule-iii/
 */
public class LC630CourseScheduleIII {

    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, Comparator.comparingInt(a -> a[1]));   // sort courses based on duration so that courses ending early will appear early
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());      // max-heap => course with max duration on top
        int time=0;
        for (int[] course:courses) {
            time = time + course[0]; // add current course to a priority queue
            maxHeap.add(course[0]);
            if (time>course[1]) time = time - maxHeap.poll(); //If time exceeds, drop the previous course which costs the most time. (That must be the best choice!)
        }
        return maxHeap.size();
    }

    public static void main(String[] args) {
        LC630CourseScheduleIII courseScheduleIII = new LC630CourseScheduleIII();
        System.out.println(courseScheduleIII.scheduleCourse(new int[][]{ {100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200} }));
    }
}
