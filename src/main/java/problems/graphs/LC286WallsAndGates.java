package main.java.problems.graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/***
 * You are given a m x n 2D grid initialized with these three possible values.
 * a)	-1 means a wall or an obstacle.
 * b)	0 means a gate.
 * c)	INF means an empty room. We use the value 2^31 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than2147483647.
 *
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
 * INF  -1  0  INF
 * INF INF INF  -1
 * INF  -1 INF  -1
 *   0  -1 INF INF
 *
 * After running your function, the 2D grid should be:
 *   3  -1   0   1
 *  2   2   1  -1
 *  1  -1   2  -1
 *  0  -1   3   4
 */
public class LC286WallsAndGates {

    private static final int EMPTY = Integer.MAX_VALUE;
    private static final int GATE = 0;

    private List<Point> directions = Arrays.asList(
      new Point(0,1),
      new Point(0,-1),
      new Point(1,0),
      new Point(-1,0)
    );

    public void wallsAndGates(int[][] rooms) {
        Queue<Point> queue = getGates(rooms);
        while (!queue.isEmpty()){
            Point point = queue.poll();
            for (Point dir : directions) {
                int r = point.x + dir.x;
                int c = point.y + dir.y;
                if(!valid(r,c,rooms))
                    continue;
                rooms[r][c] = rooms[point.x][point.y] + 1;
                queue.add(new Point(r,c));
            }
        }
    }

    private boolean valid(int row, int col, int[][] rooms) {
        int m = rooms.length;
        int n = rooms[0].length;
        if (row < 0 || col < 0 || row >= m || col >= n || rooms[row][col] != EMPTY)
            return false;
        return true;
    }
    private Queue<Point> getGates(int[][] rooms) {
        Queue<Point> q = new LinkedList<>();
        for(int row = 0; row<rooms.length; row++) {
            for( int col = 0; col<rooms[0].length; col++){
                if(rooms[row][col] == GATE)
                    q.add(new Point(row, col));
            }
        }
        return q;
    }

}
