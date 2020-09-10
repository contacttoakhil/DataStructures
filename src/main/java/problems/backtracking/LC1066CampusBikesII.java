package main.java.problems.backtracking;

import java.util.Arrays;

/***
 * On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is
 * a 2D coordinate on this grid.
 *
 * We assign one unique bike to each worker so that the sum of the Manhattan distances between each worker and
 * their assigned bike is minimized.
 *
 * The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.
 *
 * Return the minimum possible sum of Manhattan distances between each worker and their assigned bike.
 *
 * Example 1:
 *
 * Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
 * Output: 6
 * Explanation:
 * We assign bike 0 to worker 0, bike 1 to worker 1. The Manhattan distance of both assignments is 3, so the output is 6.
 *
 * Note:
 *
 *     0 <= workers[i][0], workers[i][1], bikes[i][0], bikes[i][1] < 1000
 *     All worker and bike locations are distinct.
 *     1 <= workers.length <= bikes.length <= 10
 */
public class LC1066CampusBikesII {
    int result = Integer.MAX_VALUE;
    public int assignBikes(int[][] workers, int[][] bikes) {
        backtrack(workers, bikes, 0, new boolean[bikes.length], 0);
        return result;
    }
    public void backtrack(int[][] ws, int[][] bs, int index, boolean[] vb, int sum) {
        if(index == ws.length) {
            result = Math.min(result, sum);
            return;
        }
        if(sum >= result) return; // pruning
        for(int i = 0; i < bs.length; i++) {
            if(vb[i]) continue;
            vb[i] = true;
            //int d = manhattanDist(ws[index], bs[i]);
            backtrack(ws, bs, index+1, vb, sum + manhattanDist(ws[index], bs[i]));
            vb[i] = false;
        }
    }
    private int manhattanDist(int[] worker, int[] bike) {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }

    public static void main(String[] args) {
        LC1066CampusBikesII campusBikesII = new LC1066CampusBikesII();
        int[][] workers = new int[][] {{0,0}, {2,1}};
        int[][] bikes = new int[][] {{1,2},{3,3}};
        System.out.println(campusBikesII.assignBikes(workers,bikes));   // [0,2,1]
    }
}
