package main.java.problems.arrays;

import java.util.HashSet;
import java.util.Set;

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
        Set<Integer> set = new HashSet<>();
        for (int num : nums)
            set.add(num);
        int longestStreak = 0;
        for (int num : set) {
            if (set.contains(num - 1)) continue;
            int currentNum = num;
            int currentStreak = 1;
            while (set.contains(currentNum + 1)) {
                currentNum++;
                currentStreak++;
            }
            longestStreak = Math.max(longestStreak, currentStreak);
        }
        return longestStreak;
    }

    public static void main(String[] args) {
        int[] array = new int[] {100, 4, 200, 1, 3, 2, 10, 5};
        LC128LongestConsecutiveSequence lc128LongestConsecutiveSequence = new LC128LongestConsecutiveSequence();
        System.out.println(lc128LongestConsecutiveSequence.longestSequence(array));
    }
}
