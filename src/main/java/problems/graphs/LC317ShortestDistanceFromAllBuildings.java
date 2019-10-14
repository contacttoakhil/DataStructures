package main.java.problems.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
 * Each 0 marks an empty land which you can pass by freely.
 * Each 1 marks a building which you cannot pass through.
 * Each 2 marks an obstacle which you cannot pass through.
 * For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):
 * 1 - 0 - 2 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 * The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.
 * Note:
 * There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
 *
 *
 * Solution:
 * The shortest distance from an empty-location to a building can be obtained by BFS starting from the building in O(mn) time. We can compute distance from all the buildings in t rounds where t is number of the buildings. In every round:
 * a) dist[i][j] - distance of empty-loc [i,j] to all the buildings.
 * b) grid[i][j] - reused for accessibility (number of buildings accessible from this loc)
 * In the end we just need to find the minimum value of dist[i][j] where the accessibility equals t.
 *
 * One interesting point is gird[i][j] can be used to check if [i,j] is visited in this round. At round k (zero based) if:
 * a) grid[i][j] == k is empty-loc then it is unvisited
 * b) grid[i][j] == k+1 will be visited
 *
 * What if we grid[i][j] < k?
 * We do not go into the lands with grid[i][j] < k as if it is an obstacle. Why can we do that? Because grid[i][j] < k means it is not accessible by at least one of the buildings in previous rounds. Which means not only this land is not our answer,
 * all the lands accessible from (i, j) is also not our answer.
 *
 * http://buttercola.blogspot.com/2016/01/leetcode-shortest-distance-from-all.html
 */
public class LC317ShortestDistanceFromAllBuildings {
    private final int[][] grid;
    private final int rows;
    private final int cols;
    private final int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};

    public LC317ShortestDistanceFromAllBuildings(int[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
    }

    public int shortestDistance() {
        List<Building> buildings = initializeBuildingsAndAccessibilityMatrix();
        int[][] dist = new int[rows][cols];
        for (int k = 0; k < buildings.size(); ++k)                  // BFS from every building
            bfs(buildings.get(k), k, dist);
        return findMinDistance(buildings, dist);
    }

    private List<Building> initializeBuildingsAndAccessibilityMatrix() {
        List<Building> buildings = new ArrayList<>();
        for (int r = 0; r < rows; ++r) {
            for (int c = 0; c < cols; ++c) {
                if (grid[r][c] == 1)
                    buildings.add(new Building(r, c, 0));
                grid[r][c] = -grid[r][c];                           // negate the value
            }
        }
        return buildings;
    }

    private void bfs(Building root, int k, int[][] dist) {
        Queue<Building> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            Building b = q.poll();
            dist[b.r][b.c] = dist[b.r][b.c] + b.dist;
            for (int dir = 0; dir < dx.length; ++dir) {
                int nxt_r = b.r + dx[dir], nxt_c = b.c + dy[dir];
                if (isValid(nxt_r, nxt_c, k)) {
                    grid[nxt_r][nxt_c] = k + 1;
                    q.add(new Building(nxt_r, nxt_c, b.dist + 1));
                }
            }
        }
    }

    private boolean isValid(int r, int c, int k) {
        return (c >= 0 && r >= 0 && r < rows && c < cols && grid[r][c] == k);
    }

    private int findMinDistance(List<Building> buildings, int[][] dist) {
        int ans = -1;
        for (int r = 0; r < rows; ++r) {
            for (int c = 0; c < cols; ++c) {
                if (grid[r][c] == buildings.size() && (ans < 0 || dist[r][c] < ans))
                    ans = dist[r][c];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1,0,2,0,1},
                {0,0,0,0,0},
                {0,0,1,0,0}
        };
        LC317ShortestDistanceFromAllBuildings shortestDistanceFromAllBuildings = new LC317ShortestDistanceFromAllBuildings(grid);
        System.out.println(shortestDistanceFromAllBuildings.shortestDistance());
    }
}
class Building {
    public int r;
    public int c;
    public int dist;

    public Building(int r, int c, int dist) {
        this.r = r;
        this.c = c;
        this.dist = dist;
    }
}
