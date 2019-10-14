package main.java.problems.graphs;

import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/***
 * You are asked to cut off trees in a forest for a golf event. The forest is represented as a non-negative 2D map, in this map:
 *
 * 0 represents the obstacle can't be reached.
 * 1 represents the ground can be walked through.
 * The place with number bigger than 1 represents a tree can be walked through, and this positive number represents the tree's height.
 *
 *
 * You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the tree with lowest height first. And after cutting, the original place has the tree will become a grass (value 1).
 *
 * You will start from the point (0, 0) and you should output the minimum steps you need to walk to cut off all the trees. If you can't cut off all the trees, output -1 in that situation.
 *
 * You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.
 *
 * Example 1:
 *
 * Input:
 * [
 *  [1,2,3],
 *  [0,0,4],
 *  [7,6,5]
 * ]
 * Output: 6
 *
 *
 * Example 2:
 *
 * Input:
 * [
 *  [1,2,3],
 *  [0,0,0],
 *  [7,6,5]
 * ]
 * Output: -1
 *
 *
 * Example 3:
 *
 * Input:
 * [
 *  [2,3,4],
 *  [0,0,5],
 *  [8,7,6]
 * ]
 * Output: 6
 * Explanation: You started from the point (0,0) and you can cut off the tree in (0,0) directly without walking.
 *
 *
 * Hint: size of the given matrix will not exceed 50x50.
 *
 * https://leetcode.com/articles/cutoff-trees-for-golf-event/?orderBy=most_votes&page=3
 */
public class LC675CutOffTreesForGolfEvent {
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    public int cutOffTree(List<List<Integer>> forest, int sr, int sc, int tr, int tc) {
        int R = forest.size(), C = forest.get(0).size();
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>(
                (a, b) -> Integer.compare(a[0], b[0]));
        heap.offer(new int[]{0, 0, sr, sc});

        HashMap<Integer, Integer> cost = new HashMap();
        cost.put(sr * C + sc, 0);

        while (!heap.isEmpty()) {
            int[] cur = heap.poll();
            int g = cur[1], r = cur[2], c = cur[3];
            if (r == tr && c == tc) return g;
            for (int di = 0; di < 4; ++di) {
                int nr = r + dr[di], nc = c + dc[di];
                if (0 <= nr && nr < R && 0 <= nc && nc < C && forest.get(nr).get(nc) > 0) {
                    int ncost = g + 1 + Math.abs(nr-tr) + Math.abs(nc-tr);
                    if (ncost < cost.getOrDefault(nr * C + nc, 9999)) {
                        cost.put(nr * C + nc, ncost);
                        heap.offer(new int[]{ncost, g+1, nr, nc});
                    }
                }
            }
        }
        return -1;
    }
}
