package main.java.problems.arrays;

/***
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Determine if you are able to reach the last index.
 *
 * Example 1:
 *
 * Input: [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 *
 * Input: [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum
 *              jump length is 0, which makes it impossible to reach the last index.
 *
 * https://leetcode.com/problems/jump-game/
 */
public class LC55JumpGame {

    public boolean canJump(int[] nums) {
        if(nums.length <= 1)
            return true;

        int max = nums[0]; //max stands for the largest index that can be reached.

        for(int i=0; i<nums.length; i++){
            //if not enough to go to next
            if(max <= i && nums[i] == 0)
                return false;

            //update max
            if(i + nums[i] > max){
                max = i + nums[i];
            }

            //max is enough to reach the end
            if(max >= nums.length-1)
                return true;
        }

        return false;
    }
}
