package main.java.problems.arrays;

import java.util.HashSet;

/***
 * Given N axis-aligned rectangles where N > 0, determine if they all together form an exact cover of a rectangular region.
 *
 * Each rectangle is represented as a bottom-left point and a top-right point. For example, a unit square is represented as [1,1,2,2]. (coordinate of bottom-left point is (1, 1) and top-right point is (2, 2)).
 *
 *
 * Example 1:
 *
 * rectangles = [
 *   [1,1,3,3],
 *   [3,1,4,2],
 *   [3,2,4,4],
 *   [1,3,2,4],
 *   [2,3,3,4]
 * ]
 *
 * Return true. All 5 rectangles together form an exact cover of a rectangular region.
 *
 * https://leetcode.com/problems/perfect-rectangle/
 * https://leetcode.com/problems/perfect-rectangle/discuss/87180/on-solution-by-counting-corners-with-detailed-explaination
 * https://leetcode.com/problems/perfect-rectangle/discuss/87181/Really-Easy-Understanding-Solution(O(n)-Java)
 * http://massivealgorithms.blogspot.com/2016/08/leetcode-391-perfect-rectangle.html
 *
 * Solution:
 * The right answer must satisfy two conditions:
 * a) the large rectangle area should be equal to the sum of small rectangles
 * b) count of all the points should be even, and that of all the four corner points should be one
 */
public class LC391PerfectRectangle {
    private static final String SEPARATOR = "-";
    public boolean isRectangleCover(int[][] rectangles) {
        if (rectangles.length == 0 || rectangles[0].length == 0) return false;

        int x1 = Integer.MAX_VALUE;
        int x2 = Integer.MIN_VALUE;
        int y1 = Integer.MAX_VALUE;
        int y2 = Integer.MIN_VALUE;

        HashSet<String> set = new HashSet<String>();
        int area = 0;

        for (int[] rect : rectangles) {
            x1 = Math.min(rect[0], x1);
            y1 = Math.min(rect[1], y1);
            x2 = Math.max(rect[2], x2);
            y2 = Math.max(rect[3], y2);

            area += (rect[2] - rect[0]) * (rect[3] - rect[1]);

            String s1 = rect[0] + SEPARATOR + rect[1];
            String s2 = rect[0] + SEPARATOR + rect[3];
            String s3 = rect[2] + SEPARATOR + rect[3];
            String s4 = rect[2] + SEPARATOR + rect[1];

            if (!set.add(s1)) set.remove(s1);
            if (!set.add(s2)) set.remove(s2);
            if (!set.add(s3)) set.remove(s3);
            if (!set.add(s4)) set.remove(s4);
        }

        if (!set.contains(x1 + SEPARATOR + y1) || !set.contains(x1 + SEPARATOR + y2) || !set.contains(x2 + SEPARATOR + y1) || !set.contains(x2 + SEPARATOR + y2) || set.size() != 4) return false;

        return area == (x2-x1) * (y2-y1);
    }

    public static void main(String[] args) {
        LC391PerfectRectangle perfectRectangle = new LC391PerfectRectangle();
        int[][] rectangle = new int[][] {
                {1,1,3,3},
                {3,1,4,2},
                {3,2,4,4},
                {1,3,2,4},
                {2,3,3,4}
        };
        System.out.println(perfectRectangle.isRectangleCover(rectangle));
    }
}
