package main.java.problems.list;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/***
 * You have k lists of sorted integers in ascending order. Find the smallest range that includes at least one number from each of the k lists.
 *
 * We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.
 *
 *
 *
 * Example 1:
 *
 * Input: [[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
 * Output: [20,24]
 * Explanation:
 * List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
 * List 2: [0, 9, 12, 20], 20 is in range [20,24].
 * List 3: [5, 18, 22, 30], 22 is in range [20,24].
 *
 *
 * Note:
 *
 * The given list may contain duplicates, so ascending order means >= here.
 * 1 <= k <= 3500
 * -105 <= value of elements <= 105.
 */
public class LC632SmallestRangeCoveringElementKLists {
    public int[] smallestRange(List<List<Integer>> a) {
        PriorityQueue<Element> q = new PriorityQueue<>(Comparator.comparingInt(e -> a.get(e.row).get(e.col)));
        int max = Integer.MIN_VALUE, start = 0, end = Integer.MAX_VALUE;
        for (int i = 0; i < a.size(); i++) {
            q.offer(new Element(i, 0));         // q: [1,0],[0,0],[2,0]
            max = Math.max(max, a.get(i).get(0));  // max: max(4,0,5) = 5
        }
        while (q.size() == a.size()) {
            Element element = q.poll(); int row = element.row, col = element.col;
            if (end - start > max - a.get(row).get(col)) {
                start = a.get(row).get(col);
                end = max;
            }
            if (col + 1 < a.get(row).size()) {
                q.offer(new Element(row, col + 1));
                max = Math.max(max, a.get(row).get(col + 1));
            }
        }
        return new int[]{start, end};
    }

    public static void main(String[] args) {
        LC632SmallestRangeCoveringElementKLists smallestRangeCoveringElementKLists = new LC632SmallestRangeCoveringElementKLists();
        int[] result = smallestRangeCoveringElementKLists.smallestRange(Arrays.asList(Arrays.asList(4,10,15,24,26),Arrays.asList(0,9,12,20),Arrays.asList(5,18,22,30)));
        System.out.println(Arrays.toString(result));
    }
}
class Element {
    public int row;
    public int col;
    Element(int row, int col) {
        this.row = row; this.col = col;
    }
}