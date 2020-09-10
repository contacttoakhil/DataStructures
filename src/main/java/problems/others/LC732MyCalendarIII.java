package main.java.problems.others;

import java.util.TreeMap;

/***
 * Implement a MyCalendarThree class to store your events. A new event can always be added.
 *
 * Your class will have one method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.
 *
 * A K-booking happens when K events have some non-empty intersection (ie., there is some time that is common to all K events.)
 *
 * For each call to the method MyCalendar.book, return an integer K representing the largest integer such that there exists a K-booking in the calendar.
 *
 * Your class will be called like this: MyCalendarThree cal = new MyCalendarThree(); MyCalendarThree.book(start, end)
 * Example 1:
 *
 * MyCalendarThree();
 * MyCalendarThree.book(10, 20); // returns 1
 * MyCalendarThree.book(50, 60); // returns 1
 * MyCalendarThree.book(10, 40); // returns 2
 * MyCalendarThree.book(5, 15); // returns 3
 * MyCalendarThree.book(5, 10); // returns 3
 * MyCalendarThree.book(25, 55); // returns 3
 * Explanation:
 * The first two events can be booked and are disjoint, so the maximum K-booking is a 1-booking.
 * The third event [10, 40) intersects the first event, and the maximum K-booking is a 2-booking.
 * The remaining events cause the maximum K-booking to be only a 3-booking.
 * Note that the last event locally causes a 2-booking, but the answer is still 3 because
 * eg. [10, 20), [10, 40), and [5, 15) are still triple booked.
 *
 *
 * Note:
 *
 * The number of calls to MyCalendarThree.book per test case will be at most 400.
 * In calls to MyCalendarThree.book(start, end), start and end are integers in the range [0, 10^9].
 *
 * https://leetcode.com/problems/my-calendar-iii/
 * https://leetcode.com/problems/my-calendar-iii/discuss/109568/Java-Solution-O(n-log(len))-beats-100-Segment-Tree
 * https://leetcode.com/problems/my-calendar-iii/discuss/109556/JavaC%2B%2B-Clean-Code
 */
public class LC732MyCalendarIII {

    private TreeNode root;
    public LC732MyCalendarIII() {
        root = new TreeNode(0, 1000000000);
    }
    public int book(int start, int end) {
        add(root, start, end, 1);
        return getMax(root);
    }
    private void add(TreeNode node, int start, int end, int val) {
        if (node == null || start >= node.end || end < node.start) return;
        if (start <= node.start && node.end <= end) {
            node.booked += val;
            node.saved += val;
            return;
        }
        int mid = node.start + (node.end - node.start) / 2;
        if (overlap(node.start, mid, start, end)) {
            if (node.left==null) node.left = new TreeNode(node.start, mid);
            add(node.left, start, end, val);
        }
        if (overlap(mid, node.end, start, end)) {
            if (node.right==null) node.right = new TreeNode(mid, node.end);
            add(node.right, start, end, val);
        }
        node.saved = node.booked + Math.max(getMax(node.left), getMax(node.right));
    }
    private int getMax(TreeNode node){
        if (node==null) return 0;
        return node.saved;
    }
    private boolean overlap(int s, int e, int l, int r) {
        if (r <= s || l >= e) return false;
        return true;
    }


    // ============================================================================== approach two ====>  https://leetcode.com/problems/my-calendar-iii/discuss/109556/JavaC%2B%2B-Clean-Code
    private TreeMap<Integer, Integer> timeline = new TreeMap<>();
    public int bookTwo(int s, int e) {
        timeline.put(s, timeline.getOrDefault(s, 0) + 1); // 1 new event will be starting at [s]
        timeline.put(e, timeline.getOrDefault(e, 0) - 1); // 1 new event will be ending at [e];
        int ongoing = 0, k = 0;
        for (int v : timeline.values())
            k = Math.max(k, ongoing += v);
        return k;
    }


}
class TreeNode {
    int start;
    int end;
    TreeNode left = null;
    TreeNode right = null;
    int booked = 0;
    int saved = 0;
    public TreeNode(int s, int t) {
        this.start = s;
        this.end = t;
    }
}
