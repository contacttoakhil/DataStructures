package main.java.onsite.facebook;

/***
 * Given an array of positive integers, is it possible to split the array in two parts such that the sum of parts is equal.
 * Hint: Prefix Sum.
 */
public class SplitArrayEqualSum {

    // no solution if result is -1 or nums.length
    public int IsSplitPossible(int[] nums) {
        int leftSum = 0;
        for (int i=0; i<nums.length; i++)
            leftSum += nums[i];
        int rightSum = 0;
        for (int i=nums.length-1; i>=0; i--){
            rightSum = rightSum + nums[i];
            leftSum = leftSum - nums[i];
            if(rightSum == leftSum)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        SplitArrayEqualSum splitArrayEqualSum = new SplitArrayEqualSum();
        System.out.println(splitArrayEqualSum.IsSplitPossible(new int[] {1,2,3,4,5,5}));
    }
}
