package main.java.problems.others;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/***
 * We are given a list schedule of employees, which represents the working time for each employee.
 * Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
 * Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.
 * Example 1:
 * Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
 * Output: [[3,4]]
 * Explanation:
 * There are a total of three employees, and all common
 * free time intervals would be [-inf, 1], [3, 4], [10, inf].
 * We discard any intervals that contain inf as they aren't finite.
 *
 * Example 2:
 * Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
 * Output: [[5,6],[7,9]]
 *
 * (Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined.)
 * Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.
 * Note:
 * schedule and schedule[i] are lists with lengths in range [1, 50].
 * 0 <= schedule[i].start < schedule[i].end <= 10^8.
 *
 * https://www.leetfree.com/problems/employee-free-time.html
 * https://massivealgorithms.blogspot.com/search?q=Employee+free+time
 * https://zxi.mytechroad.com/blog/wp-content/uploads/2018/01/759-ep154.png
 *
 * Solution:
 * We can use an "events" approach present in other interval problems. For each interval [s, e], we can think of this as two events: balance++ when time = s, and balance-- when time = e. We want to know the regions where balance == 0.
 */
public class LC759EmloyeeFreeTime {
    public List<Interval> employeeFreeTime(List<List<Interval>> avails) {
        int OPEN = 0, CLOSE = 1;

        List<int[]> events = new ArrayList();
        for (List<Interval> employee: avails)
            for (Interval iv: employee) {
                events.add(new int[]{iv.start, OPEN});
                events.add(new int[]{iv.end, CLOSE});
            }

        Collections.sort(events, (a, b) -> a[0] != b[0] ? a[0]-b[0] : a[1]-b[1]);
        List<Interval> ans = new ArrayList();

        int prev = -1, bal = 0;
        for (int[] event: events) {
            // event[0] = time, event[1] = command
            if (bal == 0 && prev >= 0)
                ans.add(new Interval(prev, event[0]));
            bal += event[1] == OPEN ? 1 : -1;
            prev = event[0];
        }

        return ans;
    }
}
