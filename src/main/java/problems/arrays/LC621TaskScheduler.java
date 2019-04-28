package main.java.problems.arrays;

import java.util.Arrays;

/***
 * Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks. Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.
 *
 * However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.
 *
 * You need to return the least number of intervals the CPU will take to finish all the given tasks.
 *
 *
 *
 * Example:
 *
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 *
 *
 * Note:
 *
 * a) The number of tasks is in the range [1, 10000].
 * b) The integer n is in the range [0, 100].
 *
 * https://leetcode.com/problems/task-scheduler/
 *
 * Solution: If we can determine the number of idle slots then time required to execute all the tasks is idle_slots + TotalNumberOfTasks. Thus, the idea is to find out the idle time first.
 */
public class LC621TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c: tasks)
            map[c - 'A']++;
        Arrays.sort(map);
        int max_val = map[25] - 1, idle_slots = max_val * n;
        for (int i = 24; i >= 0 && map[i] > 0; i--) {
            idle_slots = idle_slots - map[i]; //Math.min(map[i], max_val);
        }
        return idle_slots > 0 ? idle_slots + tasks.length : tasks.length;
    }

    public static void main(String[] args) {
        LC621TaskScheduler taskScheduler = new LC621TaskScheduler();
        System.out.println(taskScheduler.leastInterval(new char[]{'A','A','A','B','B','B'}, 2));
    }
}
