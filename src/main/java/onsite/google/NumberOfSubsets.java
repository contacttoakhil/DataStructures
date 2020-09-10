package main.java.onsite.google;


/**
 * For a given list of integers and integer K, find the number of non-empty subsets S such that min(S) + max(S) <= K.
 *
 * Example 1:
 *
 * nums = [2, 4, 5, 7]
 * k = 8
 * Output: 5
 * Explanation: [2], [4], [2, 4], [2, 4, 5], [2, 5]
 * Example 2:
 *
 * nums = [1, 4, 3, 2]
 * k = 8
 * Output: 15
 * Explanation: 16 (2^4) - 1 (empty set) = 15
 * Example 3:
 *
 * nums = [2, 4, 2, 5, 7]
 * k = 10
 * Output: 27
 * Explanation: 31 (2^5 - 1) - 4 ([7], [5, 7], [4, 5, 7], [4, 7]) = 27
 * Expected O(n^2) time solution or better.
 *
 * Solution: Two pointer approach - leftIndex is element that must be included, rightIndex is biggest element that can be included in that set.
 *
 *
 * Facebook Variation:
 * Given an array A of positive, sorted, no duplicate integer and a positive integer k count of all such subsets of A such that min(S) + max(S) = K
 * Note: subset should contain at least two elements
 */
public class NumberOfSubsets {

    public int minMaxSubset(int[] nums, int k){
        int left = 0, right = nums.length - 1, count = 0;
        while (left <= right) {
            if(nums[left] + nums [right] > k)
                right--;
            else
            {
                count  = count + (1 << (right - left));
                left++;
            }
        }
        return count;
    }

    // Facebook variation
    public int minMaxSubset2(int[] nums, int k){
        int left = 0, right = nums.length - 1, count = 0;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if(sum > k)
                right--;
            else if (sum < k)
                left++;
            else                                                // sum == k
            {
                count  += (1 << (right - left - 1));            // Math.pow(2,(right - left) - 1);
                left++;
                right--;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        NumberOfSubsets nos = new NumberOfSubsets();
        System.out.println(nos.minMaxSubset2(new int[]{1,2,3,4,5},5));
        System.out.println(nos.minMaxSubset(new int[]{2,4,5,7}, 8) + ":" + nos.minMaxSubset2(new int[]{2,4,5,7}, 8));        // 5
        System.out.println(nos.minMaxSubset(new int[]{1, 4, 3, 2}, 8) + ":" + nos.minMaxSubset2(new int[]{1, 4, 3, 2}, 8));     // 15
        System.out.println(nos.minMaxSubset(new int[]{2, 4, 2, 5, 7}, 10) + ":" + nos.minMaxSubset2(new int[]{2, 4, 2, 5, 7}, 10)); // 23 and not 27
    }
}
