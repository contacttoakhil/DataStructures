package main.java.problems.others;

import java.util.TreeMap;

/***
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] find the minimum number of conference rooms required.
 * For example,
 * Given [[0, 30],[5, 10],[15, 20]],
 * return 2.
 *
 * Hint: https://leetcode.com/problems/my-calendar-iii/discuss/109556/JavaC%2B%2B-Clean-Code
 */
public class LC253MeetingRoomsII {

    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        TreeMap<Integer, Integer> times = new TreeMap<>();
        for (Interval i : intervals) {
            times.put(i.start, times.getOrDefault(i.start, 0) + 1);
            times.put(i.end, times.getOrDefault(i.end, 0) - 1);
        }

        int count = 0, res = 0;
        for (int c : times.values()) {
            count += c;
            res = Math.max(res, count);
        }

        return res;
    }

}
