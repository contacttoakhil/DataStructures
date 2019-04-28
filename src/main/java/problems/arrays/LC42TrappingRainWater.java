package main.java.problems.arrays;

/***
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 *
 * https://leetcode.com/problems/trapping-rain-water/
 * https://leetcode.com/problems/trapping-rain-water/discuss/17526/very-concise-java-solution-no-stack-with-explanations
 */
public class LC42TrappingRainWater {
    public int trap(int[] height) {
        int secHight = 0;
        int left = 0;
        int right = height.length - 1;
        int area = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                secHight = Math.max(height[left], secHight);
                area += secHight - height[left];
                left++;
            } else {
                secHight = Math.max(height[right], secHight);
                area += secHight - height[right];
                right--;
            }
        }
        return area;
    }
}
