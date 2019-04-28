package main.java.problems.graphs;

import java.util.*;

/***
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 *
 * Example 1:
 *
 * Input: 2, [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 *
 * Input: 2, [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0, and to take course 0 you should
 *              also have finished course 1. So it is impossible.
 *
 *  https://leetcode.com/problems/course-schedule/
 */
public class LC207CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        Map<Integer, ArrayList<Integer>> adjacencyMap = initializeMap(numCourses, prerequisites, indegree);
        int count = numCourses;
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0)  // travel backwards as we have pre-requisite relationship not adjacent with us
                queue.offer(i);
        }
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int i : adjacencyMap.get(current)) {
                if (--indegree[i] == 0)
                    queue.offer(i);
            }
            count--;
        }
        return count == 0;
    }

    private Map<Integer,ArrayList<Integer>> initializeMap(int numCourses, int[][] prerequisites, int[] indegree) {
        Map<Integer, ArrayList<Integer>> adjacencyMap = new HashMap<Integer, ArrayList<Integer>>(); //for given key provides all adjacent elements.
        for (int i = 0; i < numCourses; i++) {
            adjacencyMap.put(i, new ArrayList<Integer>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            int from = prerequisites[i][1];
            int to = prerequisites[i][0];
            adjacencyMap.get(from).add(to);
            indegree[to]++;
        }
        return adjacencyMap;
    }

    public static void main(String[] args) {
        LC207CourseSchedule courseSchedule = new LC207CourseSchedule();
        System.out.println(courseSchedule.canFinish(5, new int[][]{{0,1},{1,2},{1,3},{3,4}}));
    }
}
