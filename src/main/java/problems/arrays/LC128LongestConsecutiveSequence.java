package main.java.problems.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Leet Code 128 : Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 *
 * Input: [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 *
 */
public class LC128LongestConsecutiveSequence {

    public int longestSequence(int[] nums) {
        if(nums == null || nums.length == 0) return 0;

        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toCollection(HashSet::new));

        int maxSeqLength = 1;

        for (int element : nums) {
            int left = element - 1;
            int right = element + 1;
            int count = 1;

            while (set.contains(left)) {
                count++;
                set.remove(left);
                left--;
            }

            while (set.contains(right)) {
                count++;
                set.remove(right);
                right++;
            }

            maxSeqLength = Math.max(count, maxSeqLength);
        }

        return maxSeqLength;
    }

    public static void main(String[] args) {
        int[] array = new int[] {100, 4, 200, 1, 3, 2, 10, 5};
        LC128LongestConsecutiveSequence lc128LongestConsecutiveSequence = new LC128LongestConsecutiveSequence();
        System.out.println(lc128LongestConsecutiveSequence.longestSequence(array));
    }
}
