package main.java.problems.others;

import main.java.ds.queue.MaxQueue;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/***
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.
 *
 * Example:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 *
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.
 *
 * Follow up:
 * Could you solve it in linear time?
 *
 * https://leetcode.com/problems/sliding-window-maximum/
 *
 * Solution:
 * We enqueue the element at the end of the deque, if the end of the deque is less than the current element, delete the current element. Then we enqueue the element at end of deque.
 * If the size of deque is greater than k we remove the element at head as element at head would always be max.
 *
 */
public class LC239SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return new int[0];
        }
        MaxQueue<Integer> mq = new MaxQueue();
        int [] res = new int[nums.length-k+1];
        int idx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                mq.push(nums[i]);
                continue;
            }
            mq.push(nums[i]);
            res[idx++] = mq.front();
            mq.pop(nums[i - k + 1]);
        }
        return res;
    }

    public static void main(String[] args) {
        LC239SlidingWindowMaximum swm = new LC239SlidingWindowMaximum();
        int[] result = swm.maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3);
        System.out.println(Arrays.toString(result));
    }
}
