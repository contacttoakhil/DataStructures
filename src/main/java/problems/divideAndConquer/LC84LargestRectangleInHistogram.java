package main.java.problems.divideAndConquer;

/***
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
 *
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 */
public class LC84LargestRectangleInHistogram {

    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        return getMaxArea(heights, 0, heights.length);
    }

    private int getMaxArea(int[] heights, int start, int end) {
        if (start > end) return heights[start];
        int min = start;

        // check if entries from start to end in heights are sorted? If yes invoke getMaxAreaForSorted and return area.
        // Also find the index i having min value for height i.e. height[i] < height[min].
        boolean sorted = true;
        for (int i = start; i < end; i++) {
            if (i > start && heights[i] < heights[i - 1]) sorted = false;
            if (heights[i] < heights[min]) min = i;
        }
        if (sorted) return getMaxAreaForSorted(heights, start, end);
        int left = (min > start) ? getMaxArea(heights, start, min) : 0;
        int right = (min < end - 1) ? getMaxArea(heights, min + 1, end) : 0;
        return Math.max(Math.max(left, right), (end - start) * heights[min]);
    }

    private int getMaxAreaForSorted(int[] heights, int start, int end) {
        int max = 0;
        for (int i = start; i < end; i++) {
            max = Math.max(max, heights[i] * (end - i));
        }
        return max;
    }
}
