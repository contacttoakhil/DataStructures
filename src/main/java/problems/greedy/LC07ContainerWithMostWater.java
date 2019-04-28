package main.java.problems.greedy;

/***
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with r-axis forms a container,
 * such that the container contains the most water.
 *
 * Note: You may not slant the container and n is at least 2.
 *
 * Input: [1,8,6,2,5,4,8,3,7]
 * Output: 49
 *
 * https://leetcode.com/problems/container-with-most-water/
 *
 * Solution
 * The intuition behind this approach is that the area formed between the lines will always be limited by the height of the shorter line. Further, the farther the lines, the more will be the area obtained.
 *
 * We take two pointers, one at the beginning and one at the end of the array constituting the length of the lines. Futher, we maintain a variable maxareamaxarea to store the maximum area obtained till now. At every step, we find out the
 * area formed between them, update maxareamaxarea and move the pointer pointing to the shorter line towards the other end by one step.
 *
 */
public class LC07ContainerWithMostWater {

    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1, maxArea = 0;
        while (l < r) {
            maxArea = Math.max(maxArea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        LC07ContainerWithMostWater containerWithMostWater = new LC07ContainerWithMostWater();
        System.out.println(containerWithMostWater.maxArea(new int[] {1,8,6,2,5,4,8,3,7}));
    }
}
