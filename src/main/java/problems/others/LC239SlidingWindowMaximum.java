package main.java.problems.others;

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
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }

        Deque<Integer> deque = new LinkedList<Integer>();
        int[] result = new int[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {

            // remove smaller numbers in k range as they are useless
            while (!deque.isEmpty() && nums[deque.getLast()] < nums[i]) {       // getLast retrieves, but does not remove, the last element of this deque.
                deque.removeLast();
            }

            deque.addLast(i);

            // Remove if the size of the deque is greater than k
            if (i - deque.getFirst() + 1 > k) {
                deque.removeFirst();
            }

            // Add into the result
            if (i + 1 >= k) {
                result[i + 1 - k] = nums[deque.getFirst()]; // getFirst retrieves, but does not remove, the first element of this deque.
            }
        }

        return result;

    }

}
