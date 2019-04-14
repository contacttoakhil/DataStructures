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
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num-1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum+1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }

    public static void main(String[] args) {
        int[] array = new int[] {100, 4, 200, 1, 3, 2, 10, 5};
        LC128LongestConsecutiveSequence lc128LongestConsecutiveSequence = new LC128LongestConsecutiveSequence();
        System.out.println(lc128LongestConsecutiveSequence.longestSequence(array));
    }
}
